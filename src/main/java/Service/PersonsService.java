package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.PersonDao;
import Dao.UserDao;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Result.PersonsResult;

public class PersonsService {
    /**
     * @return PersonAllResult - data[], success, message
     */
    public PersonsResult persons(String authToken) {
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
                PersonsResult result = new PersonsResult(false, "Error: Invalid Auth Token");
                return result;
            }
            //find currentUser and their person object
            User currentUser = new UserDao(db.getConnection()).find(token.getUsername());
            Person currentPerson = new PersonDao(db.getConnection()).find(currentUser.getPersonID());
            //find person all with dao
            Person[] allPerson = new PersonDao(db.getConnection()).findAll(authToken);
            // Create and return SUCCESS Result object
            PersonsResult result = new PersonsResult(allPerson, true);
            result.setPerson(currentPerson);
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            PersonsResult result = new PersonsResult(false, "Error: " + ex.getMessage());
            return result;
        }
    }
}
