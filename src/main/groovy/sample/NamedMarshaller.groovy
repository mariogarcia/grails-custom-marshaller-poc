package sample

import grails.converters.JSON

abstract class NamedMarshaller<T> {

    static final String DEFAULT_VIEW = 'default'

    String marshallerName
    Class<?> marshallerClazz

    NamedMarshaller(Class<?> clazz) {
        this(clazz, DEFAULT_VIEW)
    }

    NamedMarshaller(Class<?> clazz, String name) {
        this.marshallerName = name
        this.marshallerClazz = clazz
    }

    void init() {
         String marshaller = "${marshallerClazz.name}$marshallerName"

         Closure<Map<?,?>> renderRef = this.&render

         JSON.createNamedConfig(marshaller) { json ->
             json.registerObjectMarshaller(marshallerClazz, renderRef)
         }
    }

    abstract Map<?,?> render(T instance)

}
