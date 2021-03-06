#include "kernel.h"
#include "ilwisdata.h"
#include "gdalproxy.h"
#include "connectorinterface.h"
#include "mastercatalog.h"
#include "ilwisobjectconnector.h"
#include "catalogexplorer.h"
#include "catalogconnector.h"
#include "dataformat.h"
#include "gdalconnector.h"
#include "gdalitem.h"
#include "catalog.h"
#include "ilwiscontext.h"
#include "dataformat.h"

using namespace Ilwis;
using namespace Gdal;

GdalConnector::GdalConnector(const Resource &resource, bool load, const IOOptions &options) : IlwisObjectConnector(resource,load, options), _internalPath(sUNDEF)
{
    _handle = NULL;
    if ( resource.url().hasFragment())
        _internalPath = resource.url().fragment();
    _filename = resource.url();
}

GdalConnector::~GdalConnector()
{
//    if ( _handle)
//        delete _handle;
    //TODO: delete causes a crash for unknown reasons; research later
}


IlwisTypes GdalConnector::ilwisType(const QString &name)
{
    if (name == sUNDEF)
        return itUNKNOWN;

    QString filename = name;
    if (name.contains("?") == 0) {
        filename = name.split("?").front();
    }
    QFileInfo inf(filename);
    if ( inf.isDir())
        return itCATALOG;

    QString ext = inf.suffix();
    QString filter = "*." + ext;
    if ( gdal()->getExtensions(itRASTER).contains(filter,Qt::CaseInsensitive))
        return itRASTER;

    if ( gdal()->getExtensions(itFEATURE).contains(filter, Qt::CaseInsensitive))
        return itFEATURE;

    filename = name;
    if ( !name.contains(QRegExp("\\\\|/"))){
        if ( context()->workingCatalog().isValid() )
            filename = context()->workingCatalog()->filesystemLocation().toLocalFile() + "/" + name;
    }
    if ( gdal()->identifyDriver(filename.toLocal8Bit(), 0) != 0)
        return itRASTER;

    return itUNKNOWN; //TODO: add table formats here
}

bool GdalConnector::loadMetaData(IlwisObject *data, const IOOptions &options){
    if (data == nullptr)
        return false;

    if (!gdal()->isValid()) {
        return ERROR1(ERR_NO_INITIALIZED_1,"gdal library");
    }
    if ( !_filename.isValid()) {
        return ERROR1(ERR_MISSING_DATA_FILE_1,"Gdal reading");
    }

    QFileInfo fileinf (_filename.toLocalFile());
    _handle = gdal()->openFile(fileinf, data->id(), GA_ReadOnly,false); // no messages here
    if (!_handle){ // could be a container based object
        QString code = source().code();
        if ( code != sUNDEF){
            _handle = gdal()->openUrl(code,data->id(), GA_ReadOnly,false);
            data->name(source().name());
        } else {
            // scan our container (parent) and insert it into the mastercatalog
            QFileInfo file = fileinf.absolutePath(); // strip-off the /subdataset at the end, and check if it is a file
            if ( file.exists() && !file.isDir() ) {
                bool prev = kernel()->issues()->silent();
                kernel()->issues()->silent(true); // error messages during scan are not needed
                IlwisTypes extendedTypes = 0; // from GDALItems: IlwisTypes extendedTypes = extendedType(formats, file.suffix());
                GDALItems gdalitems(QUrl::fromLocalFile(file.absoluteFilePath()), file, extendedTypes);
                std::vector<Resource> items;
                for( const auto& resource : gdalitems){
                    items.push_back(resource);
                }
                mastercatalog()->addItems(items);
                kernel()->issues()->silent(prev);
                // read ourselves back out of the mastercatalog, and overwrite our resource
                IlwisTypes tp = data->ilwisType();
                auto resource = mastercatalog()->name2Resource(_filename.toString(),tp );
                if (resource.isValid()) {
                    source() = resource;
                    code = source().code();
                }
            }
            if ( code != sUNDEF) { // re-try with new code
                _handle = gdal()->openUrl(code,data->id(), GA_ReadOnly,false);
                data->name(source().name());
            } else { // last resort: attempt opening the parent container, but this will probably fail; if we reached here it is because we encountered a new type gdal-container-file, which is not handled correctly in GDALItems
                _handle = gdal()->openFile(file, data->id(), GA_ReadOnly);
                data->name(fileinf.fileName());
            }
        }

    } else{
        data->name(fileinf.fileName());
    }
    if (!_handle){
        return ERROR2(ERR_COULD_NOT_OPEN_READING_2,_filename.toString(),QString(gdal()->getLastErrorMsg()));
    }

    if ( data->ilwisType() == itRASTER){
        GDALDriverH driverH = gdal()->getDriverByDataSet(_handle->handle());
        if ( driverH){
            _gdalShortName = gdal()->getShortName(driverH);
        }
    }else if ( hasType( data->ilwisType(), itFEATURE)){
        OGRSFDriverH driverH = gdal()->getDriverFromDS(_handle->handle());
        if ( driverH){
            _gdalShortName = gdal()->getDriverName(driverH);
        }
    }

    QVariantList values = DataFormat::getFormatProperties(DataFormat::fpREADWRITE,itCOVERAGE,"gdal", _gdalShortName);
    if ( values.size() == 1){
        _readOnly = values[0].toString().indexOf("c")== -1;
    }
    Time tm = fileinf.created();
    data->createTime(tm);
    tm = fileinf.lastModified();
    data->modifiedTime(tm);


    return true;
}


QString GdalConnector::provider() const
{
    return "gdal";
}

void GdalConnector::format(const QString &f)
{
    _gdalShortName = f;
}

QString GdalConnector::format() const
{
    return _gdalShortName;
}

GDALDataType GdalConnector::ilwisType2GdalType(IlwisTypes tp) {
    switch(tp) {
    case itINT8:
    case itUINT8:
    case itCONTINUOUSCOLOR:
    case itPALETTECOLOR:
        return GDT_Byte;
    case itINT16:
        return GDT_Int16;
    case itUINT16:
        return GDT_UInt16;
    case itINT32:
        return GDT_Int32;
    case itUINT32:
        return GDT_UInt32;
    case itFLOAT:
        return GDT_Float32;
    case itDOUBLE:
        return GDT_Float64;
    default:
        return GDT_Float64;
    }
}

OGRFieldType GdalConnector::ilwisType2GdalFieldType(IlwisTypes tp) {
    if ( hasType(tp, itINTEGER))
        return OFTInteger;
    else if ( hasType(tp, (itFLOAT | itDOUBLE)))
        return OFTReal;
    else if ( tp == itTIME) // no hasType here as itDATETIME also contains itTIME
        return OFTTime;
    else if ( tp == itDATE)
        return OFTDate;
    else if ( tp == itDATETIME)
    if ( tp == itDATETIME)
        return OFTDateTime;
    return OFTString;
}

QString GdalConnector::constructOutputName(GDALDriverH hdriver) const
{
    const char *cext = gdal()->getMetaDataItem(hdriver,GDAL_DMD_EXTENSION,NULL);
    QFileInfo fileinfo =source().toLocalFile();
    QString filename = fileinfo.absoluteFilePath();
    if ( cext != 0 ) {
        QString ext(cext);
        int index = filename.lastIndexOf(".");
        if ( index != -1) {
            QString pext = filename.right(filename.size() - index);
            if ( pext.toLower() != ext) {
                filename += "." + ext;
            }
        }else
           filename += "." + ext;
    }
    return filename;
}

OGRLayerH GdalConnector::getLayerHandle() const{
    int layer = 0;
    QFileInfo inf(source().toLocalFile());
    OGRLayerH hLayer = gdal()->getLayerByName(_handle->handle(), inf.fileName().toLatin1());
    if ( !hLayer) // so this was not a gdal container connector url, then the one layer per source case
         hLayer = gdal()->getLayer(_handle->handle(), layer);
    return hLayer;
}

bool GdalConnector::isReadOnly() const
{
    return _readOnly;
}
