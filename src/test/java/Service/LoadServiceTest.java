package Service;

import Dao.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Request.LoadRequest;
import Result.ClearResult;
import Result.LoadResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadServiceTest {

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
        User testUser = new User("peteUsername", "password123", "peteEmail", "Pete", "Samson", "m", "peteID");
        User testUser2 = new User("peteUsername2", "password1232", "peteEmail2", "Pete2", "Samson2", "m", "peteID2");
        User testUser3 = new User("peteUsername3", "password1233", "peteEmail3", "Pete3", "Samson3", "m", "peteID3");

        Person testPerson = new Person("peteID","peteUsername","Pete", "Samson", "m", null, null, null);
        Person testPerson2 = new Person("peteID2","peteUsername2","Pete2", "Samson2", "m", null, null, null);
        Person testPerson3 = new Person("peteID3","peteUsername3","Pete3", "Samson3", "m", null, null, null);

        Event testEvent = new Event("eventID123", "peteUsername", "peteID",123, 123, "USA", "Provo", "Birth", 2010);
        Event testEvent2 = new Event("eventID1232", "peteUsername2", "peteID2",123, 123, "USA", "Provo", "Birth", 2010);
        Event testEvent3 = new Event("eventID1233", "peteUsername3", "peteID3",123, 123, "USA", "Provo", "Birth", 2010);

        User[] users = {testUser, testUser2, testUser3};
        Person[] persons = {testPerson, testPerson2, testPerson3};
        Event[] events = {testEvent, testEvent2,testEvent3};

        LoadRequest request = new LoadRequest(users, persons, events);
        LoadService service = new LoadService();
        LoadResult result = service.load(request);

        assertEquals(true,result.isSuccess());
        assertEquals("Successfully added 3 users, 3 persons, and 3 events to the database.",result.getMessage());
    }

    @Test
    public void nullFail() throws DataAccessException {
        User[] users = null;
        Person[] persons = null;
        Event[] events = null;

        LoadRequest request = new LoadRequest(users, persons, events);
        LoadService service = new LoadService();
        LoadResult result = service.load(request);

        assertEquals(false,result.isSuccess());
        assertEquals("Error: All data is empty",result.getMessage());
    }
}
