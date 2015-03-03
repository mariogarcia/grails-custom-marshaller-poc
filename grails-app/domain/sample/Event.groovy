package sample

class Event {

    static hasMany = [people: Person]

    String name
    List<Person> people

}
