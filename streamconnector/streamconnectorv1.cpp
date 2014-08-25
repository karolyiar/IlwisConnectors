#include "kernel.h"
#include "ilwisdata.h"
#include "version.h"
#include "connectorinterface.h"
#include "streamconnectorv1.h"
#include "factory.h"
#include "abstractfactory.h"
#include "domain.h"
#include "datadefinition.h"
#include "versioneddatastreamfactory.h"
#include "streamcoveragedatainterfacev1.h"

using namespace Ilwis;
using namespace Stream;

StreamConnectorV1::StreamConnectorV1( QDataStream& stream) : _stream(stream)
{
}

StreamConnectorV1::~StreamConnectorV1()
{

}

bool StreamConnectorV1::loadMetaData(IlwisObject *obj, const IOOptions &)
{
    if ( obj->isReadOnly())
        return false;
    QString var;
    _stream >> var;
    obj->name(var);
    _stream >> var;
    obj->code(var);
    _stream >> var;
    obj->setDescription(var);
    bool readonly;
    _stream >> readonly;
    obj->readOnly(readonly);
    double time;
    _stream >> time;
    obj->modifiedTime(time);
    _stream >> time;
    obj->createTime(time);

    return true;

}

bool StreamConnectorV1::store(IlwisObject *obj, int)
{
    double mtime = (double)obj->modifiedTime();
    double ctime = (double)obj->createTime();
    _stream <<  obj->ilwisType() << Version::IlwisVersion << obj->name() << obj->code() << obj->description() << obj->isReadOnly() << mtime << ctime;

    return true;
}

bool StreamConnectorV1::storeDataDefintion(const DataDefinition &def, QDataStream &stream, int options) const
{
    VersionedDataStreamFactory *factory = kernel()->factory<VersionedDataStreamFactory>("ilwis::VersionedDataStreamFactory");
    if (!factory)
        return false;
    std::unique_ptr<DataInterface> domainStreamer(factory->create(Version::IlwisVersion, itDOMAIN,_stream));
    if ( !domainStreamer)
        return false;
    domainStreamer->store(def.domain().ptr(), options);
    if ( !def.range().isNull()) // no range for textdomains
        def.range()->store(_stream);

    return true;
}

bool StreamConnectorV1::loadDataDefinition(DataDefinition &def, QDataStream &stream,const IOOptions &options)
{
    VersionedDataStreamFactory *factory = kernel()->factory<VersionedDataStreamFactory>("ilwis::VersionedDataStreamFactory");
    if (!factory)
        return false;
    IlwisTypes valueType, type;
    QString version;
    _stream >> valueType;
    _stream >> type;
    _stream >> version;
    std::unique_ptr<DataInterface> domainStreamer(factory->create(version, itDOMAIN,_stream));
    if ( !domainStreamer)
        return false;

    IDomain dom(type | valueType);
    Range *range = 0;
    domainStreamer->loadMetaData(dom.ptr(), options);
    if ( type != itTEXTDOMAIN){
        range = Range::create(dom->valueType());
        if (!range)
            return false;
        range->load(_stream);
    }
    def = DataDefinition(dom,range);
}
