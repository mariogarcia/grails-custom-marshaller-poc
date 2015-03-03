package sample

class EventController extends BaseController {

    def index() {

        List<Person> people = [
            new Person(name: 'john1', age: 22, address: 'blabla1'),
            new Person(name: 'john2', age: 22, address: 'blabla2'),
            new Person(name: 'john3', age: 22, address: 'blabla3'),
            new Person(name: 'john4', age: 22, address: 'blabla4'),
            new Person(name: 'john5', age: 22, address: 'blabla5'),
            new Person(name: 'john6', age: 22, address: 'blabla6'),
        ]

        responderson new Event(people: people, name: 'the event'), params.view ?: 'default'
    }
}
