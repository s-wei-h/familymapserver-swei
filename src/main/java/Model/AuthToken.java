package Model;

public class AuthToken {
    /**
     * authtokenStr holds the string of the auth token
     * username is the User object is connected with the token
     */
    private String authtokenStr;
    private String username;

    /**
     * create an auth token object
     * @param username
     */
    public AuthToken(String username) {
        this.authtokenStr = createToken();
        this.username = username;
    }

    /**
     * generate an auth token
     * @return String with authtoeknStr
     */
    public String createToken() {
        return null;
    }

    public String getAuthtokenStr() {return authtokenStr;}

    public void setAuthtokenStr(String authtokenStr) {this.authtokenStr = authtokenStr;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}
}
