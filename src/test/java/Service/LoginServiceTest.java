package Service;

import Dao.DataAccessException;
import Dao.Database;
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

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("peteUsername", "password123", "peteEmail", "Pete", "Samson", "m", "peteID");
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", null, null, null);

        Connection conn = db.getConnection();
        uDao = new UserDao(conn);
        uDao.insert(testUser);

    }

    @AfterEach
    public void tearDown() { db.closeConnection(false); }

    @Test
    public void pass() throws DataAccessException {
        LoginRequest request = new LoginRequest("peteUsername", "password123");
        LoginService service = new LoginService();
        LoginResult result = service.login(request);

        assertEquals(testPerson.getPersonID(),result.getPersonID());
    }
}
