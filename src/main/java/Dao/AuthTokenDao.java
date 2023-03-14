package Dao;

import Model.AuthToken;
import Model.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthTokenDao {
    private final Connection conn;

    public AuthTokenDao(Connection conn){
        this.conn = conn;
    }

    /**
     * add authToken object into the database
     * @param authToken - token that allows user to use the app
     */
    public void insert(AuthToken authToken) throws DataAccessException {
        String sql = "INSERT INTO AuthToken (authToken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthtoken());
            stmt.setString(2, authToken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    /**
     * find authToken from database with authTokenStr
     * @param authTokenStr - string with authtoken string to view an event
     */
    public AuthToken find(String authTokenStr) throws DataAccessException{
        AuthToken authToken;
        ResultSet rs;
        String sql = "SELECT * FROM AuthToken WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authTokenStr);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authToken = new AuthToken(rs.getString("authtoken"),rs.getString("username"));
                return authToken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an authToken in the database");
        }
    }

    /**
     * delete authToken from database
     * @param authToken - authtoken that is wanted to delete out of database
     */
    public void DeleteToken(AuthToken authToken) throws DataAccessException {
        ResultSet rs;
        String sql = "DELETE FROM AuthToken WHERE authToken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthtoken());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting an authToken in the database");
        }
    }

    /**
     * delete all AuthTokens from database
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM AuthToken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the authToken table");
        }
    }
}
