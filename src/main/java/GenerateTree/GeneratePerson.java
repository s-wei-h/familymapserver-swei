package GenerateTree;

import Model.Event;
import Model.Person;
import util.util;

public class GeneratePerson {
    /*Parents must be born at least 13 years before their children.
    Parents must be at least 13 years old when they are married.
    Parents must not die before their child is born.
    Women must not give birth when older than 50 years old.
    Birth events must be the first event for a person chronologically.
    Death events must be the last event for a person chronologically.
    Nobody must die at an age older than 120 years old.
    Each person in a married couple has their own marriage event.
    Each event will have a unique event ID, but both marriage events must have matching years and locations.
    Event locations may be randomly selected, or you may try to make them more realistic (e.g., many people live their lives in a relatively small geographical area).*/
    public Person generatePerson(Gender gender, int generations) {
        util util = new util();
        Person mother = null;
        Person father = null;
        if (generations > 1) {
            mother = generatePerson(Gender.FEMALE, generations - 1);
            father = generatePerson(Gender.MALE, generations - 1);
            // Set mother's and father's spouse IDs
            mother.setSpouseID(father.getPersonID());
            father.setSpouseID(mother.getPersonID());
            // Add marriage events to mother and father
            //Event marriage = new Event();

            // (their marriage events must be in sync with each other)
        }
        Person person = null;
        // Set person's properties
        // Generate events for person (except marriage for root user) - birth, death
        // Save person in database
        return person;
    }
}
