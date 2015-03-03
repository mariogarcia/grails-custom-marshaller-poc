package sample

class EventDefaultMarshaller extends NamedMarshaller<Event> {

    EventDefaultMarshaller() {
        super(Event)
    }

    Map render(Event event) {
        return [
            name: event.name,
            people: event.people
        ]
    }
}
