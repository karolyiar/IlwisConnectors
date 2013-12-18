#ifndef PYTHONAPI_ENGINE_H
#define PYTHONAPI_ENGINE_H

#include "pythonapi_object.h"

namespace pythonapi {

    class Engine{
    public:
        Engine();
        static Object* _do(std::string output_name, std::string operation,std::string c3 = "",std::string c4 = "",std::string c5 = "",std::string c6 = "",std::string c7="", std::string c8="", std::string c9="");
        static bool setWorkingCatalog(const std::string& location);
    };

}
#endif // PYTHONAPI_ENGINE_H
