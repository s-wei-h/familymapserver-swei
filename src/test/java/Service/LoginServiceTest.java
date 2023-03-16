package Service;

import Dao.DataAccessException;
import Dao.Database;
import Dao.PersonDao;
import Dao.UserDao;
import Model.Person;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {
    private Database db;
    private User testUser;
    private Person testPerson;
    private UserDao uDao;
    private PersonDao pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("peteUsername", "password123", "peteEmail", "Pete", "Samson", "m", "peteID");
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", null, null, null);

        Connection conn = db.getConnection();
        uDao = new UserDao(conn);
        pDao = new PersonDao(conn);
        uDao.clear();
        pDao.clear();
        uDao.insert(testUser);
        pDao.insert(testPerson);

        db.closeConnection(true);
    }
    @Test
    public void pass() throws DataAccessException {
        LoginRequest request = new LoginRequest("peteUsername", "password123");
        LoginService service = new LoginService();
        LoginResult result = service.login(request);

        assertEquals(testPerson.getPersonID(),result.getPersonID());
    }

    /**
     * Test if username doesn't exist in database
     * @throws DataAccessException
     */
    @Test
    public void failUser() throws DataAccessException {
        LoginResult failResult = new LoginResult(false, "Error: This user doesn't exist");

        LoginRequest request = new LoginRequest("notUsername", "password123");
        LoginService service = new LoginService();
        LoginResult result = service.login(request);

        assertEquals(failResult.getMessage(),result.getMessage());
    }

    /**
     * Test if wrong password is checked
     * @throws DataAccessException
     */
    @Test
    public void failPassword() throws DataAccessException {
        LoginResult failResult = new LoginResult(false, "Error: Incorrect Password");

        LoginRequest request = new LoginRequest("peteUsername", "password1234");
        LoginService service = new LoginService();
        LoginResult result = service.login(request);

        assertEquals(failResult.getMessage(),result.getMessage());
    }
}
