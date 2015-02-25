package sample

class PersonDefaultMarshaller extends NamedMarshaller<Person> {

    PersonDefaultMarshaller() {
        super(Person)
    }

    Map<?,?> render(Person person) {
        return [
            name: person.name,
            age: person.age,
            address: person.address
        ]
    }

}
