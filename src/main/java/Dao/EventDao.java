package Dao;

import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDao {
    private final Connection conn;

    public EventDao(Connection conn){
        this.conn = conn;
    }

    /**
     * add event object into the database
     * @param event - event object to add into database
     */
    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * find event from database with eventID
     * @param eventID - String of id to search in database
     */
    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database: " + e.getMessage());
        }

    }

    /**
     * Returns ALL events for ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param authTokenStr - current user
     * @return results - json array of event objects
     */
    public Event[] findAll(String authTokenStr) throws DataAccessException {
        List<Event> EventAll = new ArrayList<Event>();

        //find authToken related to authTokenStr
        AuthTokenDao aDao = new AuthTokenDao(conn);
        AuthToken authToken = aDao.find(authTokenStr);

        //find user related to authToken
        UserDao uDao = new UserDao(conn);
        User currentUser = uDao.find(authToken.getUsername());

        //find person related to user
        PersonDao pDao = new PersonDao(conn);
        Person currentPerson = pDao.find(currentUser.getPersonID());

        //find all events related to user
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getUsername());
            rs = stmt.executeQuery();
            while (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                EventAll.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding multiple events in the database: " + e.getMessage());
        }

        Event[] eventArray =  EventAll.toArray(new Event[0]);

        return eventArray;
    }

    /**
     * delete all events associated to username
     * @param username
     * @throws DataAccessException
     */
    public void userBasedClear(String username) throws DataAccessException {
        String sql = "DELETE FROM Events WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing events related to username: " + e.getMessage());
        }
    }

    public Integer count(String username) throws DataAccessException {
        Integer count = 0;
        ResultSet rs;
        String sql = "SELECT COUNT(*) FROM Events WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("COUNT(*)"));
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while counting the events table based on associated username: " + e.getMessage());
        }
    }

    public Integer countAll() throws DataAccessException {
        Integer count = 0;
        ResultSet rs;
        String sql = "SELECT COUNT(*) FROM Events;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("COUNT(*)"));
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while counting all in events table: " + e.getMessage());
        }
    }

    /**
     * delete all event from database
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Events";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table: "+ e.getMessage());
        }
    }
}








