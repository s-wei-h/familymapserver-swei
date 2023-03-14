package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.PersonDao;
import Model.AuthToken;
import Model.Person;
import Result.PersonAllResult;

public class PersonAllService {
    /**
     * @return PersonAllResult - data[], success, message
     */
    public PersonAllResult personAll(String authToken) {
        Database db = new Database();
        try {
            // Open database connection
            db.openConnection();
            //find token
            AuthToken token = new AuthTokenDao(db.getConnection()).find(authToken);
            //if token not found, return result failure
            if(token == null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                PersonAllResult result = new PersonAllResult(false, "Invalid Auth Token");
                return result;
            }
            //find person all with dao
            Person[] allPerson = new PersonDao(db.getConnection()).findAll(authToken);
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            PersonAllResult result = new PersonAllResult(allPerson, true);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            PersonAllResult result = new PersonAllResult(false, "error message");
            return result;
        }
    }
}
