import sample.*

class BootStrap {

    def init = { servletContext ->
        new PersonDefaultMarshaller().init()
        new PersonCompactMarshaller().init()
        new EventDefaultMarshaller().init()
        new EventCompactMarshaller().init()
    }
    def destroy = {

    }
}
