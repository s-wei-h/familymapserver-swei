package Service;

import Dao.AuthTokenDao;
import Dao.Database;
import Dao.UserDao;
import GenerateTree.Gender;
import GenerateTree.GeneratePerson;
import Model.AuthToken;
import Model.Person;
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

            //check if username already exists
            UserDao uDao = new UserDao(db.getConnection());
            if(uDao.find(request.getUsername()) != null) {
                db.closeConnection(false);
                // Create and return FAILURE Result object
                RegisterResult result = new RegisterResult(false, "Error: username already exists");
                return result;
            }

            // Use DAO to add new user
            User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender(),util.createID());
            new UserDao(db.getConnection()).insert(newUser);

            // Use DAOs to add new authtoken
            AuthToken newToken = new AuthToken(util.createID(), newUser.getUsername());
            new AuthTokenDao(db.getConnection()).insert(newToken);

            //check request gender to fit in enum
            Gender gender = null;
            if(request.getGender().equals("f")) {
                gender = Gender.f;
            }
            else {
                gender = Gender.m;
            }

            //generate 4 generations of ancestor data
            //generate tree
            GeneratePerson generatePerson = new GeneratePerson();
            Person generatedPerson = generatePerson.generatePersonStart(db.getConnection(),request.getFirstName(),request.getLastName(), newUser.getPersonID(), gender,4,request.getUsername(),2000);

            // Close database connection, COMMIT transaction
            db.closeConnection(true);
            // Create and return SUCCESS Result object
            RegisterResult result = new RegisterResult(newToken.getAuthtoken(), newUser.getUsername(), newUser.getPersonID(), true);
            return result;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            // Close database connection, ROLLBACK transaction
            db.closeConnection(false);
            // Create and return FAILURE Result object
            RegisterResult result = new RegisterResult(false, "Error: " + ex.getMessage());
            return result;
        }

    }
}
