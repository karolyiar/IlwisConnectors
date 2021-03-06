#ifndef INIFILE_H
#define INIFILE_H

#include <QHash>
#include <QString>
#include <QFileInfo>
#include <map>

using namespace std;

namespace Ilwis {

typedef std::map<QString, QString> SectionEntries;
typedef std::map<QString, SectionEntries> Sections;

class CatalogConnector;
typedef std::unique_ptr<Ilwis::CatalogConnector> UPCatalogConnector;

class IniFile
{
public:
    IniFile();
    IniFile(const QFileInfo &url, bool loadfile=true);
    bool setIniFile(const QFileInfo &url, bool loadfile=true);
    virtual ~IniFile();

    void setKeyValue(const QString& section, const QString& key, const QString& value);
    void setKeyValue(const QString &section, const QString &key, double value);
    void setKeyValue(const QString &section, const QString &key, int value);

    QString value(const QString& section, const QString& key) const;

    void removeKeyValue(const QString& section, const QString& key);
    void removeSection(const QString& section);
    QString url()const;
    QString filepath() const;
    QStringList childKeys(const QString& section) const;

    void store(const QString &ext, const QFileInfo& url);

private:
    QFileInfo _filename;
    Sections _sections;

    bool load();

    void setValue(const QString &section, const QString &key, const QString &value);
};

typedef QScopedPointer<IniFile> ODF;

}

#endif
