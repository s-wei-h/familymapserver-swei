package Request;

import Model.AuthToken;

public class LoginRequest {
    /**
     * username = user input username
     */
    private String username;
    /**
     * password = user input password
     */
    private String password;

    /**
     * @param username - user input username for login
     * @param password - user input password for password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
