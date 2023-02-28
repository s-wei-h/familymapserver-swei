package Model;

import java.util.Objects;

public class User {
    /**
     * username = string id for user object
     * password = password for user to login
     * email = email for user
     * firstname, lastname = name of user
     * gender = can only be f or m
     * personID = ID of person object for the user
     */
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    /**
     * constructor for User object
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        if(gender == "f" || gender == "m") {
            this.gender = gender;
        }
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
        Person userPerson = new Person(personID,associatedUsername, firstName, lastName, gender, null, null, null);
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
