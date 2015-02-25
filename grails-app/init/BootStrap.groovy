import sample.PersonDefaultMarshaller
import sample.PersonCompactMarshaller

class BootStrap {

    def init = { servletContext ->
        new PersonDefaultMarshaller().init()
        new PersonCompactMarshaller().init()
    }
    def destroy = {

    }
}
