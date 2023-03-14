package Service;

import Dao.Database;
import Dao.EventDao;
import Dao.PersonDao;
import Dao.UserDao;
import Model.User;
import Result.FillResult;
import Result.LoadResult;
import Result.LoginResult;
import util.util;

public class FillService {

    /**
     *
     * @param username - username must be a user already registered with the server
     * @param generations - optional parameter of # of generations
     * @return FillResult - message, success
     */
    public FillResult fill(String username, Integer generations) {
        Database db = new Database();
        util util = new util();
        try {
            // Open database connection
            db.openConnection();
            //find user
            User user = new UserDao(db.getConnection()).find(username);
            //if user is not found, return result failure
            if (user == null) {
                db.closeConnection(false);
                FillResult result = new FillResult("error message", false);
                return result;
            }
            //delete any data in database related to username
            PersonDao pDao = new PersonDao(db.getConnection());
            pDao.userBasedClear(username);
            EventDao eDao = new EventDao(db.getConnection());
            eDao.userBasedClear(username);

            //generate tree


            FillResult result = new FillResult("error message", false);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            FillResult result = new FillResult("error message", false);
            return result;
        }
    }
}
