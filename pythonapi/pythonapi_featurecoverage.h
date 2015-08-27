#ifndef PYTHONAPI_FEATURECOVERAGE_H
#define PYTHONAPI_FEATURECOVERAGE_H

#include "pythonapi_coverage.h"
#include "pythonapi_feature.h"
#include "pythonapi_geometry.h"
#include "pythonapi_featureiterator.h"

namespace Ilwis {
    class FeatureCoverage;
    typedef IlwisData<FeatureCoverage> IFeatureCoverage;
}

namespace pythonapi {
    class FeatureAttributeDefinition;
    class FeatureIterator;

    class FeatureCoverage : public Coverage{
        friend class FeatureIterator;
        friend class Engine;
        friend class Catalog;
    private:
        FeatureCoverage(Ilwis::IFeatureCoverage* coverage);
    public:
        FeatureCoverage();
        FeatureCoverage(const std::string& resource);
        FeatureIterator __iter__();
        IlwisTypes featureTypes() const;
        void featureTypes(IlwisTypes type);
        unsigned int featureCount() const;
        void setFeatureCount(IlwisTypes type, quint32 geomCnt);

        Feature newFeature(const std::string& wkt, const CoordinateSystem& csy, bool load = true);
        Feature newFeature(const Geometry& geometry);
        Feature newFeatureFrom(const Feature& feat, const CoordinateSystem& csy);
        void reprojectFeatures(const CoordinateSystem& csy);

        Table attributeTable() ;
        void attributesFromTable(const Table &otherTable);

        bool addColumn(const ColumnDefinition &coldef);
        bool addColumn(const std::string &name, const std::string &domainname);
        ColumnDefinition columndefinition(const std::string &nme) const;
        ColumnDefinition columndefinition(quint32 index) const;
        void setColumndefinition(const ColumnDefinition &coldef);
        quint32 columnIndex(const std::string &nme) const;
        ColumnDefinition __getitem__(quint32 index);

        PyObject* checkInput(PyObject* inputVar, quint32 columnIndex) const;
        quint32 definitionCount() const;

        void setSubDefinition(const Domain& dom, PyObject* items);
        quint32 indexOf(const std::string& variantId) const;
        quint32 indexOf(double domainItem) const;
        quint32 indexOf(PyObject* obj) const;
        std::string atIndex(quint32 idx) const;
        PyObject* indexes() const;
        quint32 countSubs() const;
        Domain subDomain() const;
        void clear();

        static FeatureCoverage* toFeatureCoverage(Object *obj);

        PyObject* select(const std::string& spatialQuery);
        FeatureCoverage *clone();
        IlwisTypes geometryType(const Geometry& geom);
        void setCoordinateSystem(const CoordinateSystem &cs);
    };
}

#endif // PYTHONAPI_FEATURECOVERAGE_H
