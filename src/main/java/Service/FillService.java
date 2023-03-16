package Service;

import Dao.Database;
import Dao.EventDao;
import Dao.PersonDao;
import Dao.UserDao;
import GenerateTree.Gender;
import GenerateTree.GeneratePerson;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Result.FillResult;

public class FillService {

    /**
     * @param request - username and optional parameter of # of generations
     * @return FillResult - message, success
     */
    public FillResult fill(FillRequest request) {
        Database db = new Database();
        try {
            // Open database connection
            db.openConnection();
            //find user
            User user = new UserDao(db.getConnection()).find(request.getAssociatedUsername());
            //if user is not found, return result failure
            if (user == null) {
                db.closeConnection(false);
                FillResult result = new FillResult("Error: User doesn't exist.", false);
                return result;
            }
            //save original user's person data
            PersonDao pDao = new PersonDao(db.getConnection());
            Person userPerson = pDao.find(user.getPersonID());
            //delete any data in database related to username
            pDao.userBasedClear(request.getAssociatedUsername());
            EventDao eDao = new EventDao(db.getConnection());
            eDao.userBasedClear(request.getAssociatedUsername());

            //check request gender to fit in enum
            Gender gender = null;
            if(userPerson.getGender().equals("f")) {
                gender = Gender.f;
            }
            else {
                gender = Gender.m;
            }

            //generate tree
            GeneratePerson generatePerson = new GeneratePerson();
            Person generatedPerson = generatePerson.generatePersonStart(db.getConnection(), userPerson.getFirstName(),userPerson.getLastName(),userPerson.getPersonID(),gender, request.getGenerations(), request.getAssociatedUsername(), 2000);

            //get count of events and persons
            Integer personCount = pDao.count(request.getAssociatedUsername());
            Integer eventCount = eDao.count(request.getAssociatedUsername());
            String messageString = "Successfully added " + personCount +" persons and " + eventCount + " events to the database.";
            System.out.println(messageString);

            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            FillResult result = new FillResult(messageString, true);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            FillResult result = new FillResult("Error: " + ex.getMessage(), false);
            return result;
        }
    }
}
