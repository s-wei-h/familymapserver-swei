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
}
