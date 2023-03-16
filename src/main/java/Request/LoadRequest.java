package Request;

import Model.Event;
import Model.Person;
import Model.User;

public class LoadRequest {
    /**
     * users - json array of user objects
     */
    User[] users;
    /**
     * persons - json array of persons objects
     */
    Person[] persons;
    /**
     * events - json array of events objects
     */
    Event[] events;

    /**
     * @param users - json array of user objects
     * @param persons - json array of persons objects
     * @param events - json array of events objects
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public Person[] getPersons(String username) {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
