package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.EventDao;
import Model.AuthToken;
import Model.Event;
import Result.EventsResult;

public class EventsService {
    /**
     * return all events for all family members
     * @return EventAllResult - data (json array of events), success
     */
    public EventsResult events(String authToken) {
        Database db = new Database();
        try {
            // Open database connection
            db.openConnection();
            //find token
            AuthToken token = new AuthTokenDao(db.getConnection()).find(authToken);
            //if token not found, return result failure
            if (token == null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                EventsResult result = new EventsResult(false, "Error: Invalid Auth Token");
                return result;
            }
            //find event all with dao
            Event[] allEvent = new EventDao(db.getConnection()).findAll(authToken);
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            EventsResult result = new EventsResult(allEvent, true);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            EventsResult result = new EventsResult(false, "Error: " + ex.getMessage());
            return result;
        }
    }
}
