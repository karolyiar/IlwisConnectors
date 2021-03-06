#ifndef TABLECONNECTOR_H
#define TABLECONNECTOR_H

namespace Ilwis {
namespace Ilwis3{

class RawConverter;

class TableConnector : public Ilwis3Connector
{
public:
    TableConnector(const Ilwis::Resource &resource, bool load,const IOOptions& options=IOOptions());
    bool loadMetaData(IlwisObject* data,const IOOptions&);
    bool storeMetaData(Ilwis::IlwisObject *obj);
    QString type() const;
    virtual IlwisObject *create() const;
    bool loadData(IlwisObject *, const IOOptions& options = IOOptions());
    bool storeBinaryData(IlwisObject* obj);

    static ConnectorInterface *create(const Ilwis::Resource &resource, bool load,const IOOptions& options=IOOptions());
    static bool storeTable(const ITable& tbl);
    void attributeDomain(const QString& attdom);
    void selectedRecords(const std::vector<quint32> &recs);
    QString format() const;
private:
    ColumnDefinition getKeyColumn();
    ColumnDefinition makeColumn(const QString &colName, quint64 index);
    QString valueType2DataType(IlwisTypes ty);
    QString getDomainName(const IDomain &dom, bool& isSystem);

    QHash<QString, RawConverter> _converters;
    QString _attributeDomain;
    std::vector<quint32> _selected;
};
}
}

#endif // TABLECONNECTOR_H
