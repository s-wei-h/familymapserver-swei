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
            throw new DataAccessException("Error encountered while inserting a person into the database: " + e.getMessage());
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
            throw new DataAccessException("Error encountered while finding a person in the database: " + e.getMessage());
        }
    }

    public Person[] findAll(String authTokenStr) throws DataAccessException {
       List<Person> PersonAll = new ArrayList<>();

        //find authToken related to authTokenStr
        AuthTokenDao aDao = new AuthTokenDao(conn);
        AuthToken authToken = aDao.find(authTokenStr);

        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,authToken.getUsername());
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                        rs.getString("FirstName"),  rs.getString("LastName"), rs.getString("gender"),
                        rs.getString("FatherID"), rs.getString("MotherID"), rs.getString("SpouseID"));
                PersonAll.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database " + e.getMessage());
        }

        Person[] personArray = PersonAll.toArray(new Person[0]);
        return personArray;
    }

    public void deleteSingle(String personID) throws DataAccessException {
        String sql = "DELETE FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,personID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting one person: " + e.getMessage());
        }
    }

    /**
     * delete all persons where the username is associated
     * @param username
     * @throws DataAccessException
     */
    public void userBasedClear(String username) throws DataAccessException {
        String sql = "DELETE FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table based on associated username: " + e.getMessage());
        }
    }

    public Integer count(String username) throws DataAccessException {
        Integer count = 0;
        ResultSet rs;
        String sql = "SELECT COUNT(*) FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("COUNT(*)"));
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while counting rows in persons table based on associated username: " + e.getMessage());
        }
    }

    public Integer countAll() throws DataAccessException {
        Integer count = 0;
        ResultSet rs;
        String sql = "SELECT COUNT(*) FROM Persons;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("COUNT(*)"));
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while counting rows in persons table: " + e.getMessage());
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
            throw new DataAccessException("Error encountered while clearing the person table: " + e.getMessage());
        }
    }
}
