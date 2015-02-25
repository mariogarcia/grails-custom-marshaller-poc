package sample

class PersonCompactMarshaller extends NamedMarshaller<Person> {

    PersonCompactMarshaller() {
        super(Person, 'compact')
    }

    Map<?,?> render(Person person) {
        return [
            name: person.name
        ]
    }

}
