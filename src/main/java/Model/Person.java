package Model;

import java.util.Objects;
import java.util.UUID;

public class Person {
    /**
     * personID = identifier for person object
     */
    private String personID;
    /**
     * associatedUsername - username associated to person
     */
    private String associatedUsername;
    /**
     * firstName - first name of person
     */
    private String firstName;
    /**
     * lastName - last name of person
     */
    private String lastName;
    /**
     * gender - can only be f or m
     */
    private String gender;
    /**
     * fatherID - optional id of person's father
     */
    private String fatherID = null;
    /**
     * motherID - optional id of person's mother
     */
    private String motherID = null;
    /**
     * spouseID - optional id of person's spouse
     */
    private String spouseID = null;

    /**
     * @param associatedUsername - username associated to person
     * @param firstName - first name of person
     * @param lastName - last name of person
     * @param gender - can only be f or m
     * @param fatherID - optional id of person's father
     * @param motherID - optional id of person's mother
     * @param spouseID - optional id of person's spouse
     */
    public Person(String personID,String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
    }
}
