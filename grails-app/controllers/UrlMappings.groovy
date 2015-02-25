class UrlMappings {

    static mappings = {

        "/person"(controller: 'person', action: 'index')

        "500"(view:'/error')
    }
}
