package Result;

import Dao.PersonDao;
import Model.Person;

public class PersonsResult {
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

    private Person person;

    /**
     * @param data - json array of Person objects related to current user
     * @param success - if all family members were successfully returned
     */
    public PersonsResult(Person[] data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public PersonsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
