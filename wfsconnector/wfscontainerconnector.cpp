
#include <sstream>
#include <QString>

#include "pugixml/pugixml_global.h"
#include "pugixml/pugixml.hpp"

#include "wfsConnector_global.h"

#include "kernel.h"
#include "ilwis.h"
#include "connectorinterface.h"
#include "catalog/containerconnector.h"
#include "wfs.h"
#include "wfsresponse.h"
#include "wfscontainerconnector.h"

using namespace Ilwis;
using namespace Wfs;

WfsContainerConnector::WfsContainerConnector()
{
}

WfsContainerConnector::WfsContainerConnector(const Resource &resource):ContainerConnector(resource)
{

}

ConnectorInterface *WfsContainerConnector::create(const Resource &res, bool)
{
    new WfsContainerConnector(res);
}

bool WfsContainerConnector::prepare()
{
    return true;
}

std::vector<QUrl> WfsContainerConnector::sources(const QStringList &filter, int options) const
{

    WebFeatureService wfs(source().url());
    WfsResponse *response = wfs.getCapabilities();
    QString capabilities = response->getContent();

    pugi::xml_document doc;
    std::istringstream cs(capabilities.toStdString());
    doc.load(cs);

    std::vector<QUrl> ret;
    pugi::xpath_node_set featureTypes = doc.select_nodes("/*/FeatureTypeList/FeatureType");
    std::for_each (featureTypes.begin(), featureTypes.end(), [](pugi::xpath_node featureType) {
        QString name(featureType.node().child("Name").text().as_string());
        QString title(featureType.node().child("Title").text().as_string());
        QString abstract(featureType.node().child("Abstract").text().as_string());

        QUrl coverageUrl(source().url())

    });



    // TODO: parse FeatureTypes from Capabilities

    // TODO: getFeature for each FeatureType name

    // TODO: create getFeature URLs for available features


    ret.push_back(source().url());
    return ret;
}

QFileInfo WfsContainerConnector::toLocalFile(const QUrl &datasource) const
{

    // TODO: what is the intent of this method?

    // TODO: Download resource here?
    QFileInfo inf = datasource.toLocalFile();
    return inf;
}

bool WfsContainerConnector::isValid() const
{
    return source().isValid();
}

QString WfsContainerConnector::provider() const
{
    return "wfs";
}

bool WfsContainerConnector::canUse(const Resource &resource) const
{
    QString scheme = resource.url().scheme();
    if ( scheme == "ilwis")
        return false; // can't use anything marked as internal

    if ( scheme != "http" && scheme != "https")
        return false; // can't read non http based data

//    QString filename = resource.url().toLocalFile();
//    if ( filename == "")
//        return false;

    IlwisTypes type = resource.ilwisType() ;
    if ( type & itCONTAINER)
        return true;

    return false;
}

