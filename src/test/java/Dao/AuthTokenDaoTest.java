package Dao;

import Model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDaoTest {
    private Database db;
    private AuthToken testToken;
    private AuthTokenDao aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testToken = new AuthToken("token123", "peteUsername");
        Connection conn = db.getConnection();
        aDao = new AuthTokenDao(conn);
        aDao.clear();
    }

    @AfterEach
    public void TearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        aDao.insert(testToken);
        AuthToken compareTest = aDao.find(testToken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(testToken.getAuthtoken(), compareTest.getAuthtoken());
    }

    @Test
    public void insertFail() throws DataAccessException {
        aDao.insert(testToken);
        assertThrows(DataAccessException.class, () -> aDao.insert(testToken));
    }


}
