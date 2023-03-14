package Service;

import Dao.*;
import Model.Person;
import Result.ClearResult;
import Result.LoginResult;
import Result.RegisterResult;

public class ClearService {

    /**
     * @return ClearResult - message, success
     */
    public ClearResult clear() {
        Database db = new Database();
        try {
            //open database connection
            db.openConnection();
            //clear all tables using DAOS
            new UserDao(db.getConnection()).clear();
            new PersonDao(db.getConnection()).clear();
            new EventDao(db.getConnection()).clear();
            new AuthTokenDao(db.getConnection()).clear();
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            ClearResult result = new ClearResult("Database cleared", true);
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            ClearResult result = new ClearResult("error message", false);
            return result;
        }
    }
}
