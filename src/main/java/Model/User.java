package Model;

import java.util.Objects;
import java.util.UUID;

public class User {
    /**
     * username = string id for user object
     */
    private String username;
    /**
     * password = password for user to login
     */
    private String password;
    /**
     * email = email for user
     */
    private String email;
    /**
     * firstName = first name of user
     */
    private String firstName;
    /**
     * lastName = last name of user
     */
    private String lastName;
    /**
     * gender = can only be f or m
     */
    private String gender;
    /**
     * personID = ID of person object for the user
     */
    private String personID;

    /**
     * constructor for User object
     * @param username - string id for user object
     * @param password - password for user to login
     * @param email - email for user
     * @param firstName - first name of user
     * @param lastName - last name of user
     * @param gender - can only be f or m
     * @param personID - ID of person object for the user
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = createUserPerson(personID, username, firstName, lastName, gender).getPersonID();
    }

    /**
     * create a person object for user
     * @param associatedUsername - username associated with user
     * @param firstName - first name associated with user
     * @param lastName - last name associated with user
     * @param gender - gender associated with user
     * @return Person - person object
     */
    public Person createUserPerson(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        Person userPerson = new Person(personID, associatedUsername, firstName, lastName, gender, null, null, null);
        return userPerson;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPersonID() { return personID; }
    public void setPersonID(String personID) { this.personID = personID; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(personID, user.getPersonID()) && Objects.equals(username, user.getUsername()) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }
}
