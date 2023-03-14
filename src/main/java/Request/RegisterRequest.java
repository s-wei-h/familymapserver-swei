package Request;

public class RegisterRequest {
    /**
     * username = user input for username
     */
    private String username;
    /**
     * email = user input for email
     */
    private String email;
    /**
     * firstName = user input for first name
     */
    private String firstName;
    /**
     * lastName = user input for last name
     */
    private String lastName;
    /**
     * password = user input for password
     */
    private String password;
    /**
     * gender = user input for gender can only be f or m
     */
    private String gender;

    /**
     * constructor for register request
     * @param username - user input for username
     * @param email - user input for email
     * @param firstName - user input for first name
     * @param lastName - user input for last name
     * @param password - user input for account password
     * @param gender - user input for gender can only be f or m
     */
    public RegisterRequest(String username, String email, String firstName, String lastName, String password, String gender) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
