package Service;

import Dao.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Result.PersonResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonServiceTest {
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
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", null, null, null);

        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        aDao = new AuthTokenDao(conn);
        uDao = new UserDao(conn);

        pDao.clear();
        aDao.clear();
        uDao.clear();

        pDao.insert(testPerson);
        aDao.insert(testToken);
        uDao.insert(testUser);

        db.closeConnection(true);
    }

    @Test
    public void pass() throws DataAccessException {
        PersonResult passResult = new PersonResult("peteUsername", "peteID","Pete", "Samson", "m", null, null, null,true);

        PersonService service = new PersonService();
        PersonResult result = service.personSearch("peteID", "testToken123");

        assertEquals(passResult.getAssociatedUsername(),result.getAssociatedUsername());
        assertEquals(passResult.getPersonID(),result.getPersonID());
    }

    @Test
    public void authFail() throws DataAccessException {
        PersonResult failResult = new PersonResult(false, "Error: Invalid Auth Token");

        PersonService service = new PersonService();
        PersonResult result = service.personSearch("peteID", "fakeToken");

        assertEquals(result.getMessage(),failResult.getMessage());
    }

    @Test
    public void findFail() throws DataAccessException {
        PersonResult failResult = new PersonResult(false, "Error: This person doesn't exist");

        PersonService service = new PersonService();
        PersonResult result = service.personSearch("fakePerson", "testToken123");

        assertEquals(false, result.isSuccess());
    }
}
