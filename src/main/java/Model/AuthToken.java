package Model;

import java.util.UUID;

public class AuthToken {
    /**
     * authtokenStr holds the string of the auth token
     * username is the User object is connected with the token
     */
    private String authToken;
    private String username;

    /**
     * create an auth token object
     * @param username - String username with user associated with AuthToken
     */
    public AuthToken(String authToken,String username) {
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * generate an auth token
     * @return String with authtokenStr
     */
    public String createToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String getAuthtoken() {return authToken;}

    public void setAuthtoken(String authToken) {this.authToken = authToken;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}
}
