package Service;

import Dao.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Result.PersonsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonAllServiceTest {
    private Database db;
    private User testUser;
    private AuthToken testToken;
    private Person testPerson;
    private PersonDao pDao;
    private AuthTokenDao aDao;
    private UserDao uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("peteUsername", "password123", "peteEmail", "Pete", "Samson", "m", "peteID");
        testToken = new AuthToken("testToken123", "peteUsername");
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", "joshID", null, "sallyID");
        Person testPerson2 = new Person("sallyID","peteUsername","Sally", "Samson", "f", null, null, "peteID");
        Person testPerson3 = new Person("joshID","peteUsername","Josh", "Samson", "m", null, null, null);

        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        aDao = new AuthTokenDao(conn);
        uDao = new UserDao(conn);

        pDao.clear();
        aDao.clear();
        uDao.clear();

        uDao.insert(testUser);
        aDao.insert(testToken);
        pDao.insert(testPerson);
        pDao.insert(testPerson2);
        pDao.insert(testPerson3);

        db.closeConnection(true);
    }

    @Test
    public void pass() throws DataAccessException {
        PersonsService service = new PersonsService();
        PersonsResult result = service.persons("testToken123");

        assertEquals(true,result.isSuccess());
        assertEquals(3,result.getData().length);
    }

    @Test
    public void fail() throws DataAccessException {
        PersonsResult failResult = new PersonsResult(true,"Error: Invalid Auth Token");
        PersonsService service = new PersonsService();
        PersonsResult result = service.persons("testToken1234");

        assertEquals(failResult.getMessage(),result.getMessage());
    }
}
