package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.UserDao;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;
import Result.RegisterResult;
import util.util;

public class LoginService {

    /**
     * function to try to login
     * if the password matches, create an authToken
     * @param request
     * @return LoginResult - authtoken, username, personID, success
     */
    public LoginResult login(LoginRequest request) {
        Database db = new Database();
        util util = new util();
        try {
            // Open database connection
            db.openConnection();
            // find user with dao
            User user = new UserDao(db.getConnection()).find(request.getUsername());
            //if user is not found, return result failure
            if(user == null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                LoginResult result = new LoginResult(false, "Error: This user doesn't exist");
                return result;
            }
            //check if password matches
            if(!user.getPassword().equals(request.getPassword())) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                LoginResult result = new LoginResult(false, "Error: Incorrect Password");
                return result;
            }
            //create authtoken for the login and add into database
            AuthToken token = new AuthToken(util.createID(), user.getUsername());
            AuthTokenDao aDao = new AuthTokenDao(db.getConnection());
            aDao.insert(token);

            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            LoginResult result = new LoginResult(token.getAuthtoken(), user.getUsername(), user.getPersonID(), true);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            LoginResult result = new LoginResult(false, "Error: " + ex.getMessage());
            return result;
        }
    }
}
