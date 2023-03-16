package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.PersonDao;
import Model.AuthToken;
import Model.Person;
import Result.LoginResult;
import Result.PersonResult;
import util.util;

public class PersonService {
    /**
     * @param personID - person id associated with current user
     * @return PersonResult - associatedUsername, personID, firstName, lastName, gender, fatherID, motherID, spouseID, success, message
     */
    public PersonResult personSearch(String personID, String authToken) {
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
                PersonResult result = new PersonResult(false, "Error: Invalid Auth Token");
                return result;
            }
            //find person with dao
            Person person = new PersonDao(db.getConnection()).find(personID);
            //if person is not found, return result failure
            if(person == null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                PersonResult result = new PersonResult(false, "Error: This person doesn't exist");
                return result;
            }
            //check if person belongs to user
            if(!person.getAssociatedUsername().equals(token.getUsername())) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                PersonResult result = new PersonResult(false, "Error: This person doesn't belong to current user");
                return result;
            }

            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            PersonResult result = new PersonResult(person.getAssociatedUsername(),person.getPersonID(),person.getFirstName(),person.getLastName(),person.getGender(),person.getFatherID(),person.getMotherID(),person.getSpouseID(),true);
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            PersonResult result = new PersonResult(false, "Error: " + ex.getMessage());
            return result;
        }
    }
}
