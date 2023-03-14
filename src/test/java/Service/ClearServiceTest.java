package Service;

import Dao.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Result.ClearResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearServiceTest {
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

        pDao.insert(testPerson);
        aDao.insert(testToken);
        uDao.insert(testUser);

        db.closeConnection(true);
    }

    @Test
    public void pass() throws DataAccessException {
        ClearService service = new ClearService();
        ClearResult result = service.clear();

        assertEquals(true, result.isSuccess());
    }

}
