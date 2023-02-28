package Dao;

import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person testPerson;
    private PersonDao pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", null, null, null);

        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() { db.closeConnection(false); }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(testPerson);
        Person compareTest = pDao.find(testPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(testPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(testPerson);
        assertThrows(DataAccessException.class, () -> pDao.insert(testPerson));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(testPerson);
        Person compareTest = pDao.find(testPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(testPerson, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        assertNull(pDao.find(testPerson.getPersonID()));
    }

    @Test
    public void clearPass() throws  DataAccessException {
        pDao.insert(testPerson);
        pDao.clear();
        assertNull(pDao.find(testPerson.getPersonID()));
    }
}
