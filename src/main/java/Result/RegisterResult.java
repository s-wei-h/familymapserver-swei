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
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success) {
        this.success = success;
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }

    public RegisterResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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
