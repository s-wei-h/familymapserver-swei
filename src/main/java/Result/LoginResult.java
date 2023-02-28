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
     *
     * @param authtoken - authorization string created after login
     * @param username - account username for login
     * @param personID - personID associated with account
     * @param success - if user was properly logged in
     * @param message - if login fails, return error why
     */
    public LoginResult(String authtoken, String username, String personID, boolean success, String message) {
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
