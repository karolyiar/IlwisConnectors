#include "kernel.h"
#include "angle.h"
#include "point.h"
#include "connectorinterface.h"
#include "inifile.h"
#include "ilwisdata.h"
#include "domainitem.h"
#include "domain.h"
#include "itemdomain.h"
#include "identifieritem.h"
#include "thematicitem.h"
#include "numericrange.h"
#include "itemrange.h"
#include "identifierrange.h"
#include "rawconverter.h"
#include "ilwisobjectconnector.h"
#include "ilwis3connector.h"
#include "numericdomain.h"
#include "binaryilwis3table.h"
#include "ilwis3range.h"
#include "domainconnector.h"

using namespace Ilwis;
using namespace Ilwis3;

ConnectorInterface *DomainConnector::create(const Resource& item, bool load) {
    return new DomainConnector(item, load);

}

DomainConnector::DomainConnector(const Resource& item, bool load) : Ilwis3Connector(item, load)
{
}

bool DomainConnector::loadMetaData(IlwisObject* data)
{
    Ilwis3Connector::loadMetaData(data);

    if (type() == itUNKNOWN) {
        kernel()->issues()->log(TR(ERR_INVALID_PROPERTY_FOR_2).arg("Domain type",_resource.name()));
        return false;
    }
    if ( type() == itNUMERICDOMAIN) {
        return handleValueDomains(data);
    } else if ( type() == itITEMDOMAIN || type() == itDOMAIN) {
        return handleItemDomains(data);
    }

    return false;
}

bool DomainConnector::handleIdDomain(IlwisObject* data) {
    ItemDomain<IndexedIdentifier> *iddomain = static_cast<ItemDomain<IndexedIdentifier> *>(data);
    bool ok;
    quint32 nritems = _odf->value("DomainIdentifier","Nr").toLong(&ok);
    if ( !ok) {
        return ERROR2(ERR_INVALID_PROPERTY_FOR_2,"domain items", data->name());
    }
    QString prefix = _odf->value("DomainSort","Prefix");
    iddomain->addItem(new IndexedIdentifier(prefix,0, nritems));

    return true;
}

bool DomainConnector::handleItemDomains(IlwisObject* data) {
    QString domtype = _odf->value("Domain","Type");
    bool hasDataFile = _odf->value("TableStore","Col1") == "Ord";
    if ( (domtype == "DomainUniqueID" || domtype ==  "DomainIdentifier") && !hasDataFile)
    { // no table found? internal domain
        return handleIdDomain(data);
    }
    Ilwis3::BinaryIlwis3Table tbl ;
    tbl.load(_odf);
    quint32 indexName = tbl.index("Name");
    if (indexName == iUNDEF) { // no name column in the table ?
        kernel()->issues()->log(TR(ERR_COLUMN_MISSING_2).arg("Name",_odf->fileinfo().baseName()));
        return false;
    }
    quint32 indexCode = tbl.index("Code"); // not mandatory
    ItemDomain<ThematicItem> *tdomain = static_cast<ItemDomain<ThematicItem> *>(data);
    QString itemName, itemCode = sUNDEF;
    for(quint32 i = 0; i < tbl.rows(); ++i) {
        tbl.get(i,indexName,itemName);
        if ( indexCode != iUNDEF)
            tbl.get(i,indexName,itemCode);
        ThematicItem *item = new ThematicItem(itemName,itemCode);
        tdomain->addItem(item);
    }

    return true;
}


bool DomainConnector::handleValueDomains(IlwisObject* data) {
    NumericDomain *vdata = static_cast<NumericDomain*>(data);
    IlwisTypes odfType = _resource.ilwisType();
    Range *range = 0;
    if ( (odfType & itDOMAIN) != 0){ // domain objects
        range = handleValueDomainObjects();
    } else {
        QString section;
        if ( odfType <= itGRID) { //  the 'basemaps' objects
            section = "BaseMap"    ;
        } else if (odfType == itTABLE) {
            QUrlQuery queryItem(_resource.url());
            QString colName = queryItem.queryItemValue("column");
            section = QString("Col:%1").arg(colName);
        }
        if ( section != "") {
            range = Ilwis3Range::findRange(_odf, section);

        } else {
            kernel()->issues()->log(TR("Illegal type %1 for domain").arg(type()));
            return false;
        }
    }
    // TODO columns domain handling, no seperate odf, so special type(??)

    if (!range) {
        kernel()->issues()->log(TR(ERR_NO_INITIALIZED_1).arg(data->name()));
        return false;
    }
    vdata->setRange(range);


    return true;
}

Range * DomainConnector::handleValueDomainObjects() const {
    bool isOk = true;
    double vmin, vmax;
    QString type = _odf->value("DomainValue", "Type");
    vmin = _odf->value(type, "Min").toDouble(&isOk);
    if (!isOk) {
        kernel()->issues()->log(TR(ERR_INVALID_PROPERTY_FOR_2).arg("Minimum", _resource.name()));
        return 0;
    }

    vmax = _odf->value(type ,"Max").toDouble(&isOk);
    if (!isOk) {
        kernel()->issues()->log(TR(ERR_INVALID_PROPERTY_FOR_2).arg("Maximum", _resource.name()));
        return 0;
    }
    double resolution = _odf->value(type , "Step").toDouble(&isOk);
    if (!isOk || fmod(resolution , 1.0) == 0)
        return new NumericRange(vmin, vmax,1);
    return new NumericRange(vmin, vmax, resolution);

}


QString DomainConnector::parseDomainInfo(const QString& inf) const{
    if ( inf != sUNDEF) {
        QStringList parts = inf.split(";");
        if ( parts.size() > 3) {
            QString dmtype = parts[2];
            return dmtype.toLower();
        }
    }
    return sUNDEF;
}

QString DomainConnector::storeDomain(const IDomain &dm)
{
    QString dmName = dm->name();
    QString alias = kernel()->database().findAlias(dmName,"domain","ilwis3");
    if ( alias != sUNDEF)
        return alias; // nothing to be done, already exists
    else {
        if ( dm->valueType() == itINDEXEDITEM) {
            return "UniqueID"; // nothing to be done, already exists
        } else if ( dm->valueType() == itNAMEDITEM){ // identifier domain
            dmName = dm->name();
            quint64 dmid = mastercatalog()->name2id(dmName + ".dom"); // is there an existing domain
            if ( dmid != i64UNDEF) {
                dmName = dmName + ".dom"    ;
                return dmName ; // nothing to be done, already exists
            } else {
                INamedIdDomain iddom = dm.get<NamedIdDomain>();
                QStringList names;
                for(int i=0; i < iddom->count(); ++i ) {
                    names << iddom->item(i)->name();
                }
                IniFile ini;
                ini.setIniFile(dmName, false);


            }

        }
    }
}

IlwisObject *DomainConnector::create() const
{
    //TODO other domain types time, coordinatesystem
    QString subtype = sUNDEF;
    if ( type() & itCOVERAGE) {
        subtype = parseDomainInfo( _odf->value("BaseMap","DomainInfo"));

    } else if( type() & itTABLE) {
        QUrlQuery queryItem(_resource.url());
        QString colName = queryItem.queryItemValue("column");
        if ( colName != sUNDEF) {
            subtype = parseDomainInfo( _odf->value(QString("Col:%1").arg(colName),"DomainInfo"));
        }

    }

    if ( type() == itNUMERICDOMAIN)
        return new NumericDomain(_resource);
    else if (type() == itITEMDOMAIN || type() == itDOMAIN) { // second case is for internal domains
        subtype =_odf->value("Domain", "Type");
        if ( subtype == "DomainIdentifier" || subtype == "DomainUniqueID")
            return new ItemDomain<IndexedIdentifier>(_resource);
        if ( subtype == "DomainClass")
            return new ItemDomain<ThematicItem>(_resource);
    }
    return 0;
}
