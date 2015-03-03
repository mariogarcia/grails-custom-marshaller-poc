package sample

class EventCompactMarshaller extends NamedMarshaller<Event> {

    EventCompactMarshaller() {
        super(Event, 'compact')
    }

    Map render(Event event) {
        return [
            name: "compact: ${event.name}",
            people: event.people
        ]
    }

}
