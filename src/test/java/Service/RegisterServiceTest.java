package Service;

import Dao.DataAccessException;
import Dao.Database;
import Dao.PersonDao;
import Dao.UserDao;
import Model.Person;
import Model.User;
import Request.RegisterRequest;
import Result.ClearResult;
import Result.RegisterResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterServiceTest {

    @BeforeEach
    public void setUp() throws DataAccessException {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
    }

    @AfterEach
    public void tearDown() {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
    }

    @Test
    public void pass() throws DataAccessException {
        RegisterRequest request = new RegisterRequest("peteusername","peteEmail", "Pete", "Samson", "password123", "m");
        RegisterService service = new RegisterService();
        RegisterResult result = service.register(request);

        assertEquals(true,result.isSuccess());
        assertEquals("peteusername",result.getUsername());
    }

    @Test
    public void fail() throws DataAccessException {
        RegisterRequest request = new RegisterRequest("peteusername","peteEmail", "Pete", "Samson", "password123", "m");
        RegisterService service = new RegisterService();
        RegisterResult result = service.register(request);
        //register twice
        result = service.register(request);

        assertEquals(false,result.isSuccess());
    }
}
