package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.EventDao;
import Model.AuthToken;
import Model.Event;
import Result.EventResult;
import Result.PersonResult;
import util.util;

public class EventService {
    /**
     * return single event with specified ID
     *
     * @return EventResult - associatedUsername, eventID, personID, latitude, longitude, country, city, eventType, year, success
     */
    public EventResult eventSearch(String eventID, String authToken) {
        Database db = new Database();
        util util = new util();
        try {
            // Open database connection
            db.openConnection();
            //check if authToken exists
            AuthToken token = new AuthTokenDao(db.getConnection()).find(authToken);
            //if token not found, return result failure
            if(token == null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                EventResult result = new EventResult(false, "Error: Invalid Auth Token");
                return result;
            }
            //find event with dao
            Event event = new EventDao(db.getConnection()).find(eventID);
            //if event is not found, return result failure
            if(event == null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                EventResult result = new EventResult(false, "Error: This event doesn't exist");
                return result;
            }
            //check if event belongs to current user
            if(!event.getAssociatedUsername().equals(token.getUsername())) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                EventResult result = new EventResult(false, "Error: This Event doesn't belong to current user");
                return result;
            }
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            EventResult result = new EventResult(event.getAssociatedUsername(),event.getEventID(),event.getPersonID(),event.getLatitude(),event.getLongitude(),event.getCountry(),event.getCity(),event.getEventType(),event.getYear(),true);
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            EventResult result = new EventResult(false, "Error: " + ex.getMessage());
            return result;
        }
    }
}
