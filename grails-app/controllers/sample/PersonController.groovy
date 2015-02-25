package sample

class PersonController extends BaseController {

    def index() {
        responderson(
            new Person(name: 'john', age: 22, address: 'blabla'),
            params.view ?: 'default'
        )
    }


}
