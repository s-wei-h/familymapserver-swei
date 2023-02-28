package Dao;

import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User testUser;
    private UserDao uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("username123", "password123", "testemail123@email.com",
                "Jane", "Doe", "f", "personID123");

        Connection conn = db.getConnection();
        uDao = new UserDao(conn);
        uDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(testUser);
        User compareTest = uDao.find(testUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(testUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(testUser);
        assertThrows(DataAccessException.class, () -> uDao.insert(testUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        uDao.insert(testUser);

        User compareTest = uDao.find(testUser.getUsername());

        assertNotNull(compareTest);
        assertEquals(testUser, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        assertThrows(DataAccessException.class, () -> uDao.find(testUser.getUsername()));
    }

    @Test
    public void clearPass() throws DataAccessException {
        uDao.insert(testUser);
        uDao.clear();
        assertThrows(DataAccessException.class, () -> uDao.find(testUser.getUsername()));
    }
}
