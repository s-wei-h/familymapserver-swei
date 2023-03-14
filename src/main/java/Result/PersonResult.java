package Result;

import Model.User;

import java.util.Objects;

public class PersonResult {
    /**
     * associatedUsername = username associated with person
     */
    private String associatedUsername;
    /**
     * personID = personID associated with person
     */
    private String personID;
    /**
     * firstName = first name associated with person
     */
    private String firstName;
    /**
     * lastName = last name associated with person
     */
    private String lastName;
    /**
     * gender = the person's gender
     */
    private String gender;
    /**
     * fatherID = (optional) personID of the person's father
     */
    private String fatherID;
    /**
     * fatherID = (optional) personID of the person's mother
     */
    private String motherID;
    /**
     * fatherID = (optional) personID of the person's spouse
     */
    private String spouseID;
    /**
     * success = if person was found
     */
    private boolean success;
    /**
     * message = if the person search failed, why
     */
    private String message;

    /**
     *
     * @param associatedUsername - username associated with person
     * @param personID - personID associated with person
     * @param firstName -  first name associated with person
     * @param lastName - last name associated with person
     * @param gender - the person's gender
     * @param fatherID - (optional) personID of the person's father
     * @param motherID - (optional) personID of the person's mother
     * @param spouseID - (optional) personID of the person's spouse
     * @param success - if person was found
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
        this.success = success;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    /**
     * @param success - if person was found
     * @param message - why it failed or succeeded
     */
    public PersonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
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
}
