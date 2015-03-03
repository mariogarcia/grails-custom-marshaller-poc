package sample

import groovy.util.logging.Slf4j
import grails.converters.JSON
import org.grails.web.converters.configuration.ConverterConfiguration
import org.grails.web.converters.configuration.ConvertersConfigurationHolder

@Slf4j
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

        Closure<Map<?,?>> renderRef = this.&render
        ConverterConfiguration configuration = null

        if (marshallerName == DEFAULT_VIEW) {
            log.debug "Registering marshaller for ${this.getClass()} within the default configuration"
            JSON.registerObjectMarshaller(marshallerClazz, renderRef)
            return
        }

        try {
            configuration = JSON.getNamedConfig(marshallerName)
            configuration.registerObjectMarshaller(marshallerClazz, renderRef)

        } catch(Throwable th)  {
            log.debug "Not found '${marshallerName}' configuration for ${this.getClass()}"
            log.debug "Registering marshaller for ${this.getClass()} within configuration '${marshallerName}'"

            JSON.createNamedConfig(marshallerName) { cfg ->
                cfg.registerObjectMarshaller(marshallerClazz, renderRef)
            }
        }

    }

    Map marshalWith(String configName, Object o) {
        return JSON.getNamedConfig(configName).getMarshaller(o).closure.call(o)
    }

    Map marshalWithDefault(Object o) {
        return useDefault {
            ConvertersConfigurationHolder
                .getConverterConfiguration(JSON)
                .getMarshaller(o)
                .closure
                .call(o)
        }
    }

    List marshalWith(String configName, Collection collection) {
        return collection.collect { marshalWith(configName, it) }
    }

    List marshalWithDefault(Collection collection) {
        return collection.collect(this.&marshalWithDefault)
    }

    Object useDefault(Closure<?> callable) {
        ConverterConfiguration<JSON> old = ConvertersConfigurationHolder.getThreadLocalConverterConfiguration(JSON)
        ConvertersConfigurationHolder.setThreadLocalConverterConfiguration(JSON, null)

        try {
            return callable.call()
        } finally {
            ConvertersConfigurationHolder.setThreadLocalConverterConfiguration(JSON, old)
        }

    }

    abstract Map<?,?> render(T instance)

}
