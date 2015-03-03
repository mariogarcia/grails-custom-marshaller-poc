package sample

import grails.converters.JSON

class BaseController {

    def responderson(Object o, String view) {

        if (view == 'default') {
            respond o
            return
        }

        JSON.use(view) {
            respond o
        }
    }

}
