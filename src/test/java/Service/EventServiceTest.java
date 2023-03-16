package Service;

import Dao.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;
import Result.EventResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventServiceTest {
    private Database db;
    private User testUser;
    private AuthToken testToken;
    private Person testPerson;
    private Event testEvent;
    private PersonDao pDao;
    private AuthTokenDao aDao;
    private UserDao uDao;
    private EventDao eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("peteUsername", "password123", "peteEmail", "Pete", "Samson", "m", "peteID");
        testToken = new AuthToken("testToken123", "peteUsername");
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", null, null, null);
        testEvent = new Event("eventID123", "peteUsername", "peteID",123, 123, "USA", "Provo", "Birth", 2010);

        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        aDao = new AuthTokenDao(conn);
        uDao = new UserDao(conn);
        eDao = new EventDao(conn);

        pDao.clear();
        aDao.clear();
        uDao.clear();
        eDao.clear();

        pDao.insert(testPerson);
        aDao.insert(testToken);
        uDao.insert(testUser);
        eDao.insert(testEvent);

        User testUser2 = new User("sallyUsername", "password123", "sallyEmail", "Sally", "Samson", "f", "sallyID");
        AuthToken testToken2 = new AuthToken("testToken456", "peteUsername");
        Person testPerson2 = new Person("sallyID","sallyUsername","Sally", "Samson", "f", null, null, null);
        Event testEvent2 = new Event("eventID456", "sallyUsername", "sallyID",123, 123, "USA", "Provo", "Birth", 2010);


        pDao.insert(testPerson2);
        aDao.insert(testToken2);
        uDao.insert(testUser2);
        eDao.insert(testEvent2);

        db.closeConnection(true);
    }

    @Test
    public void pass() throws DataAccessException {
        EventResult passResult = new EventResult("peteUsername", "eventID123", "peteID",123,123,"USA","Provo", "Birth", 2010,true);

        EventService service = new EventService();
        EventResult result = service.eventSearch("eventID123","testToken123");

        assertEquals(passResult.getPersonID(),result.getPersonID());
        assertEquals(passResult.getEventID(),result.getEventID());
    }

    @Test
    public void authFail() throws DataAccessException {
        EventResult failResult = new EventResult(false, "Error: Invalid Auth Token");

        EventService service = new EventService();
        EventResult result = service.eventSearch("eventID123","testToken1234");

        assertEquals(failResult.getMessage(),result.getMessage());
        assertEquals(failResult.isSuccess(),result.isSuccess());
    }

    @Test
    public void findFail() throws DataAccessException {
        EventResult failResult = new EventResult(false, "Error: This event doesn't exist");

        EventService service = new EventService();
        EventResult result = service.eventSearch("eventID1234","testToken123");

        assertEquals(failResult.getMessage(),result.getMessage());
        assertEquals(failResult.isSuccess(),result.isSuccess());
    }

    @Test
    public void ownershipFail() throws DataAccessException {
        EventResult failResult = new EventResult(false, "Error: This Event doesn't belong to current user");

        EventService service = new EventService();
        EventResult result = service.eventSearch("eventID456","testToken123");

        assertEquals(failResult.getMessage(),result.getMessage());
        assertEquals(failResult.isSuccess(),result.isSuccess());
    }
}
