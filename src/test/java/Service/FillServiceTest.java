package Service;

import Dao.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Request.RegisterRequest;
import Result.ClearResult;
import Result.FillResult;
import Result.RegisterResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FillServiceTest {
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

    @AfterEach
    public void tearDown() {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
    }

    @Test
    public void pass() throws DataAccessException {
        FillRequest request = new FillRequest("peteUsername",4);
        FillService service = new FillService();
        FillResult result = service.fill(request);

        assertEquals(true,result.isSuccess());
        assertEquals("Successfully added 31 persons and 92 events to the database.",result.getMessage());

    }

    @Test
    public void fail() throws DataAccessException {
        FillRequest request = new FillRequest("fakeUsername",4);
        FillService service = new FillService();
        FillResult result = service.fill(request);

        assertEquals(false,result.isSuccess());
        assertEquals("Error: User doesn't exist.",result.getMessage());

    }
}
