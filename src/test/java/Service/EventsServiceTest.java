package Service;

import Dao.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;
import Result.EventsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsServiceTest {
    private Database db;
    private User testUser;
    private AuthToken testToken;
    private Person testPerson;
    private PersonDao pDao;
    private AuthTokenDao aDao;
    private UserDao uDao;
    private EventDao eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("peteUsername", "password123", "peteEmail", "Pete", "Samson", "m", "peteID");
        testToken = new AuthToken("testToken123", "peteUsername");
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", "joshID", null, "sallyID");
        Person testPerson2 = new Person("sallyID","peteUsername","Sally", "Samson", "f", null, null, "peteID");
        Person testPerson3 = new Person("joshID","peteUsername","Josh", "Samson", "m", null, null, null);
        Event testEvent = new Event("eventID123", "peteUsername", "peteID",123, 123, "USA", "Provo", "Birth", 2010);
        Event testEvent2 = new Event("eventID456", "peteUsername", "sallyID",123, 123, "USA", "Provo", "Birth", 2010);
        Event testEvent3 = new Event("eventID789", "peteUsername", "joshID",123, 123, "USA", "Provo", "Birth", 2010);
        Event testEvent4 = new Event("eventID000", "peteUsername", "joshID",123, 123, "USA", "Provo", "Marriage", 2010);

        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        aDao = new AuthTokenDao(conn);
        uDao = new UserDao(conn);
        eDao = new EventDao(conn);

        pDao.clear();
        aDao.clear();
        uDao.clear();
        eDao.clear();

        uDao.insert(testUser);
        aDao.insert(testToken);
        pDao.insert(testPerson);
        pDao.insert(testPerson2);
        pDao.insert(testPerson3);
        eDao.insert(testEvent);
        eDao.insert(testEvent2);
        eDao.insert(testEvent3);
        eDao.insert(testEvent4);

        db.closeConnection(true);
    }

    /**
     * user is associated with 4 events
     * @throws DataAccessException
     */
    @Test
    public void pass() throws DataAccessException {
        EventsService service = new EventsService();
        EventsResult result = service.events("testToken123");

        assertEquals(true,result.isSuccess());
        assertEquals(4,result.getData().length);
    }

    /**
     * use wrong auth token, should return fail
     * @throws DataAccessException
     */
    @Test
    public void fail() throws DataAccessException {
        EventsService service = new EventsService();
        EventsResult result = service.events("testToken1234");

        assertEquals(false,result.isSuccess());
        assertEquals("Error: Invalid Auth Token",result.getMessage());
    }
}
