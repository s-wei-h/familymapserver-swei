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
}
