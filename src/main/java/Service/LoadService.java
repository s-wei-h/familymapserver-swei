package Service;

import Dao.Database;
import Dao.EventDao;
import Dao.PersonDao;
import Dao.UserDao;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.FillResult;
import Result.LoadResult;
import util.util;

public class LoadService {

    /**
     * @param request - user[], person[], event[]
     * @return LoadResult - message and success
     */
    public LoadResult load(LoadRequest request) {
        Database db = new Database();
        try {
            // Open database connection
            db.openConnection();
            //clear all database first
            new UserDao(db.getConnection()).clear();
            new PersonDao(db.getConnection()).clear();
            new EventDao(db.getConnection()).clear();
            //check if request is null
            if((request.getUsers() == null)&&(request.getEvents() == null)&&(request.getPersons() == null)) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                LoadResult result = new LoadResult("Error: All data is empty", false);
                return result;
            }
            //add users into database
            UserDao uDao = new UserDao(db.getConnection());
            for(int i = 0; i < request.getUsers().length; i++) {
                User user = request.getUsers()[i];
                uDao.insert(user);
            }
            //add persons into database
            PersonDao pDao = new PersonDao(db.getConnection());
            for(int i = 0; i < request.getPersons().length; i++) {
                Person person = request.getPersons()[i];
                pDao.insert(person);
            }
            //add events into database
            EventDao eDao = new EventDao(db.getConnection());
            for(int i = 0; i < request.getEvents().length; i++) {
                Event event = request.getEvents()[i];
                eDao.insert(event);
            }

            // Create and return SUCCESS Result object
            String message = "Successfully added " + uDao.countAll().toString() + " users, " + pDao.countAll().toString() + " persons, and " + eDao.countAll() .toString() + " events to the database.";
            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            LoadResult result = new LoadResult(message, true);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            LoadResult result = new LoadResult("Error: " + ex.getMessage(), false);
            return result;
        }
    }
}
