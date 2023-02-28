package Dao;

import Model.AuthToken;

import java.sql.Connection;

public class AuthTokenDao {
    private final Connection conn;

    public AuthTokenDao(Connection conn){
        this.conn = conn;
    }

    /**
     * add authToken object into the database
     * @param authToken
     */
    public void insert(AuthToken authToken){

    }

    /**
     * search for object with string
     * @param authStr
     * @return
     */
    public AuthToken search(String authStr){
        return null;
    }

    /**
     * find authToken from database with authTokenStr
     * @param authTokenStr
     */
    public void ViewEvent(String authTokenStr){

    }

    /**
     * delete authToken from database
     * @param authToken
     */
    public void delete(AuthToken authToken){
    }
}
