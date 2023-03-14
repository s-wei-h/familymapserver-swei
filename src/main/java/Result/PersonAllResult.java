package Result;

import Model.Person;

public class PersonAllResult {
    /**
     * data = json array of Person objects related to current user
     */
    private Person[] data;
    /**
     * success = if all family members were successfully returned
     */
    private boolean success;
    /**
     * message = if failed, why
     */
    private String message;

    /**
     * @param data - json array of Person objects related to current user
     * @param success - if all family members were successfully returned
     */
    public PersonAllResult(Person[] data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public PersonAllResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
