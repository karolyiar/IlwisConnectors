#include <QString>
#include <QUrl>
#include <QFileInfo>
#include <QDir>
#include <QVector>
#include "identity.h"
#include "kernel.h"
#include "resource.h"
#include "mastercatalog.h"
#include "connectorinterface.h"
#include "ilwisobjectconnector.h"
#include "catalogconnector.h"
#include "catalogexplorer.h"
#include "connectorinterface.h"
#include "abstractfactory.h"
#include "connectorfactory.h"
#include "foldercatalogexplorer.h"
#include "catalog.h"
#include "dataformat.h"
#include "gdalmodule.h"
#include "gdalproxy.h"
#include "tranquilizer.h"
#include "gdalcatalogexplorer.h"
#include "gdalitem.h"
#include "ilwiscontext.h"
#include "mastercatalog.h"

using namespace Ilwis;
using namespace Gdal;

REGISTER_CATALOGEXPLORER(GdalCatalogExplorer)

CatalogExplorer *GdalCatalogExplorer::create(const Resource &resource, const IOOptions &options) {
    return new GdalCatalogExplorer(resource, options);

}

GdalCatalogExplorer::GdalCatalogExplorer(const Ilwis::Resource &resource, const IOOptions &options) : CatalogExplorer(resource,options), _type(resource.ilwisType())
{
}

inline uint qHash(const QFileInfo& inf ){
    return ::qHash(inf.canonicalFilePath());
}

std::vector<Resource> GdalCatalogExplorer::loadItems(const IOOptions &)
{
    QStringList filters; //gdal()->getRasterExtensions();
    std::multimap<QString, DataFormat>  formats = DataFormat::getSelectedBy(DataFormat::fpEXTENSION, "connector='gdal'");
    for(const auto& element : formats){
        for(const auto& ext : element.second.property(DataFormat::fpEXTENSION).toString().split(",",QString::SkipEmptyParts)){
                filters += "*." + ext;
        }
    }
    filters.removeOne("*.hdr");
    filters.removeDuplicates();

    std::vector<QUrl> files = FolderCatalogExplorer::loadFolders(source(), filters ,CatalogConnector::foFULLPATHS | CatalogConnector::foEXTENSIONFILTER);

    std::set<Resource> gdalitems;
    if (!gdal()->isValid()) {
        ERROR1(ERR_NO_INITIALIZED_1,"gdal library");
        return std::vector<Resource>();
    }
    kernel()->issues()->silent(true); // error messages during scan are not needed
    try{
        UPTranquilizer trq(Tranquilizer::create(context()->runMode()));
        trq->prepare("gdal connector",source().toLocalFile(),files.size());
        for(const QUrl& url : files) {
            QFileInfo file = toLocalFile(url);
            if ( file.exists()){

                if ( !file.isDir() ) {
                    IlwisTypes extendedTypes = extendedType(formats, file.suffix());
                    GDALItems items(url, file, extendedTypes);
                    for(auto item : items) {
                        gdalitems.insert(item);
                    }
                }
            }
            if (!trq->update(1))
                return std::vector<Resource>();
        }

        std::vector<Resource> items;
        for( const auto& resource : gdalitems){
            items.push_back(resource);
        }
        kernel()->issues()->silent(false);

        if (items.size() > 0)
            kernel()->issues()->log(QString(TR("Added %1 objects through the gdal connector")).arg( items.size()),IssueObject::itMessage);

        return items;
    } catch(const ErrorObject& err){
        kernel()->issues()->silent(false);
        throw err;
    }
}

IlwisTypes GdalCatalogExplorer::extendedType(const std::multimap<QString, DataFormat>& formats, const QString& ext) const{
    IlwisTypes types= itUNKNOWN;
    auto collection = formats.equal_range(ext);
    for(auto iter = collection.first; iter != collection.second; ++iter){
        types |= (*iter).second.property(DataFormat::fpEXTENDEDTYPE).toULongLong();
    }
    return types;
}

bool GdalCatalogExplorer::canUse(const Resource &resource) const
{
    if ( resource.ilwisType() != itCATALOG) // resource not marked as catalog are refused
        return false;
    if (resource.url().scheme() != "file") //  for the moment only local based catalogs are acceptable
        return false;
    QFileInfo fileInfo(resource.url().toLocalFile());
    if ( fileInfo.isDir()) { // must be a folder
        return true;
    }
    return false;
}

QString GdalCatalogExplorer::provider() const
{
    return "gdal";
}

//std::vector<QUrl> GdalCatalogExplorer::sources(const Resource& resource, const QStringList &filter, int options) const
//{
//    return FolderCatalogExplorer::loadFolders(resource, filter, options);
//}

QFileInfo GdalCatalogExplorer::toLocalFile(const QUrl &datasource) const{
    //TODO for non-file based systems
    return QFileInfo(datasource.toLocalFile());
}



