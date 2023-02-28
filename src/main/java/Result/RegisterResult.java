package Result;

/**
 * return result for login
 *  "Success" -- also creates user object for user
 *  "Failure" -- return failure message and ask user to try again (can fail due to: email wrong or already used)
 */
public class RegisterResult {
    /**
     * authtoken = authorization string created after registration
     */
    private String authtoken;
    /**
     * username = user input for new account's username
     */
    private String username;
    /**
     * username = ID for new account's accociated person
     */
    private String personID;
    /**
     * success - if user was properly registered
     */
    private boolean success;
    /**
     * message - if registration fails, return error why
     */
    private String message;

    /**
     *
     * @param authtoken - authorization string created after registration
     * @param username - user input for new account's username
     * @param personID - ID for new account's accociated person
     * @param success - if user was properly registered
     * @param message - if registration fails, return error why
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success, String message) {
        this.success = success;

        if(success) {
            this.authtoken = authtoken;
            this.username = username;
            this.personID = personID;
        }
        else {
            this.message = message;
        }
    }
}
