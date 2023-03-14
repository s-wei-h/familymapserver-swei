package Dao;

import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private final Connection conn;

    public PersonDao(Connection conn){
        this.conn = conn;
    }

    /**
     * add person object into the database
     * @param person - Person object to add into database
     */
    public void insert(Person person) throws DataAccessException{
        String sql = "INSERT INTO Persons (PersonID, AssociatedUsername, FirstName, LastName," +
                " Gender, FatherID, MotherID, SpouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4,person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    /**
     * find person from database with personID
     * @param personID - String personID to search in database
     */
    public Person find(String personID) throws DataAccessException{
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                         rs.getString("FirstName"),  rs.getString("LastName"), rs.getString("gender"),
                         rs.getString("FatherID"), rs.getString("MotherID"), rs.getString("SpouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }
    }

    public Person[] findAll(String authTokenStr) throws DataAccessException {
        List<Person> PersonAll = new ArrayList<>();

        //find authToken related to authTokenStr
        AuthTokenDao aDao = new AuthTokenDao(conn);
        AuthToken authToken = aDao.find(authTokenStr);

        //find user related to authToken
        UserDao uDao = new UserDao(conn);
        User currentUser = uDao.find(authToken.getUsername());

        //find person related to user
        PersonDao pDao = new PersonDao(conn);
        Person currentPerson = pDao.find(currentUser.getPersonID());

        Person person;
        //find father if there is an ID
        if(currentPerson.getFatherID() != null) {
            person = pDao.find(currentPerson.getFatherID());
            PersonAll.add(person);
        }
        //find mother if there is an ID
        if(currentPerson.getMotherID() != null) {
            person = pDao.find(currentPerson.getMotherID());
            PersonAll.add(person);
        }
        //find spouse if there is an ID
        if(currentPerson.getSpouseID() != null) {
            person = pDao.find(currentPerson.getSpouseID());
            PersonAll.add(person);
        }

        Person[] personArray = (Person[]) PersonAll.toArray();

        return personArray;
    }

    /**
     * delete all persons where the username is associated
     * @param username
     * @throws DataAccessException
     */
    public void userBasedClear(String username) throws DataAccessException {
        String sql = "DELETE FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(2,username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table based on associated username");
        }
    }

    /**
     * delete all person from database
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Persons";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }
}
