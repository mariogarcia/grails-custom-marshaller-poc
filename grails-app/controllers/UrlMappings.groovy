class UrlMappings {

    static mappings = {

        "/person"(controller: 'person', action: 'index')
        "/event"(controller: 'event', action: 'index')

        "500"(view:'/error')
    }
}
