package Service;

import Dao.Database;
import Dao.UserDao;
import Model.User;
import Request.RegisterRequest;
import Result.RegisterResult;
import util.util;

public class RegisterService {

    /**
     * @param request - username, password, email, firstname, lastname, gender
     * @return RegisterResult - authtoken, username, personID, success (if failed, message)
     */
    public RegisterResult register(RegisterRequest request) {
        Database db = new Database();
        util util = new util();
        try {
            // Open database connection
            db.openConnection();
            // Use DAOs to do requested operation
            User newUser = new User(util.createID(), request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender());
            new UserDao(db.getConnection()).insert(newUser);
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            RegisterResult result = new RegisterResult(util.createID(), newUser.getUsername(), newUser.getPersonID(), true);
            return result;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            RegisterResult result = new RegisterResult(false, "error message");
            return result;
        }

    }
}
