package Model;

public class AuthToken {
    /**
     * authtokenStr holds the string of the auth token
     */
    private String authtokenStr;
    private String username;

    public AuthToken(String authtokenStr, String username) {
        this.authtokenStr = authtokenStr;
        this.username = username;
    }

    public String getAuthtokenStr() {return authtokenStr;}

    public void setAuthtokenStr(String authtokenStr) {this.authtokenStr = authtokenStr;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}
}
