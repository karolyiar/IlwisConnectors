#ifndef GDALITEM_H
#define GDALITEM_H

namespace Ilwis {
namespace Gdal {
class GDALItems : public std::set<Resource>
{
public:
    GDALItems(const QUrl& path, const QFileInfo &localFile, IlwisTypes extTypes);

private:
    quint64 addItem(GdalHandle* handle, const QUrl &url, quint64 csyid, quint64 grfId, IlwisTypes tp =itRASTER, IlwisTypes extTypes=itUNKNOWN);
    quint64 addCsy(GdalHandle* handle, const QFileInfo &path, const QUrl &url, bool message=true);
    QString dimensions(GdalHandle* handle) const;
    std::map<QString, QString> kvp2Map(char **kvplist);
};
}
}

#endif // GDALITEM_H
