package GenerateTree;

import Model.Person;

public class GeneratePerson() {
    Person generatePerson(Gender gender, int generations) {
        Person mother = null;
        Person father = null;
        if (generations > 1) {
            mother = generatePerson(FEMALE, generations - 1);
            father = generatePerson(MALE, generations - 1);
            // Set mother's and father's spouse IDs
            // Add marriage events to mother and father
            // (their marriage events must be in synch with each other)
        }
        Person person = new Person();
        // Set person's properties
        // Generate events for person (except marriage)
        // Save person in database
        return person;
    }
}
