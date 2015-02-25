package sample

import grails.converters.JSON

class BaseController {

    def responderson(Object o, String view) {
        JSON.use(o.getClass().name + view) {
            respond o
        }
    }

}
