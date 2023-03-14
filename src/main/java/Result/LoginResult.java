package Result;

/**
 * return result for login
 *  "Success" -- also creates authtoken for user
 *  "Failure" -- return failure message and ask user to try again
 */
public class LoginResult {
    /**
     * authtoken = authorization string created after login
     */
    private String authtoken;
    /**
     * username = account username for login
     */
    private String username;
    /**
     * username = personID associated with account
     */
    private String personID;
    /**
     * success - if user was properly logged in
     */
    private boolean success;
    /**
     * message - if login fails, return error why
     */
    private String message;

    /**
     * @param authtoken - authorization string created after login
     * @param username - account username for login
     * @param personID - personID associated with account
     * @param success - if user was properly logged in
     * @param message - if login fails, return error why
     */
    public LoginResult(String authtoken, String username, String personID, boolean success) {
        this.success = success;
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }

    public LoginResult(boolean success, String message) {
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
