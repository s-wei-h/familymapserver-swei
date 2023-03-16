package GenerateTree;

import Dao.DataAccessException;
import Dao.EventDao;
import Dao.PersonDao;
import Model.Event;
import Model.Person;
import util.util;

import java.sql.Connection;
import java.util.Random;

import static GenerateTree.jsonData.*;

public class GeneratePerson {
    /*Parents must be born at least 13 years before their children.
    Parents must be at least 13 years old when they are married.
    Parents must not die before their child is born.
    Women must not give birth when older than 50 years old.
    Birth events must be the first event for a person chronologically.
    Death events must be the last event for a person chronologically.
    Nobody must die at an age older than 120 years old.
    Each person in a married couple has their own marriage event.
    Each event will have a unique event ID, but both marriage events must have matching years and locations.
    Event locations may be randomly selected, or you may try to make them more realistic (e.g., many people live their lives in a relatively small geographical area).*/

    /**
     *
     * @param gender
     * @param generations
     * @param username
     * @param year - year of parents marriage (birthday will be 5 years after marriage) and parents will be at least 20 by marriage, people will die at 60 years old
     * @return
     */
    public Person generatePersonStart(Connection conn, Gender gender, int generations, String username, Integer year) throws DataAccessException {
        //set up daos
        EventDao eDao = new EventDao(conn);
        PersonDao pDao = new PersonDao(conn);
        //get random names from data
        Random random = new Random();
        util util = new util();
        //set up parents
        Person mother = null;
        Person father = null;
        if (generations > 0) {
            mother = generatePersonStart(conn, Gender.f, generations - 1, username, year - 20);
            father = generatePersonStart(conn, Gender.m, generations - 1, username, year - 20);
            // Set mother's and father's spouse IDs
            mother.setSpouseID(father.getPersonID());
            father.setSpouseID(mother.getPersonID());
            //update database for spouseID
            pDao.deleteSingle(mother.getPersonID());
            pDao.deleteSingle(father.getPersonID());
            pDao.insert(mother);
            pDao.insert(father);
            // Add marriage events to mother and father
            jsonData.Location location = locData.getData()[random.nextInt(locData.getData().length)];
            // (their marriage events must be in sync with each other)
            Event marriageM = new Event(util.createID(), username,mother.getPersonID(),Float.parseFloat(location.getLatitude()),Float.parseFloat(location.getLongitude()), location.getCountry(), location.getCity(),"Marriage",year);
            Event marriageF = new Event(util.createID(), username,father.getPersonID(),Float.parseFloat(location.getLatitude()),Float.parseFloat(location.getLongitude()), location.getCountry(), location.getCity(),"Marriage",year);
            //add marriage events
            eDao.insert(marriageM);
            eDao.insert(marriageF);
        }
        String fname = null;
        String sname = snameData.getData()[random.nextInt(snameData.getData().length)];
        if(gender.equals(Gender.f)){
            fname = fnameData.getData()[random.nextInt(fnameData.getData().length)];
        }
        else {
            fname = mnameData.getData()[random.nextInt(mnameData.getData().length)];
        }
        //check if parent are null
        String fatherID = null;
        if(father != null) {
            fatherID = father.getPersonID();
        }
        String motherID = null;
        if(mother != null) {
            motherID = mother.getPersonID();
        }

        // Set person's properties
        Person person = new Person(util.createID(),username,fname,sname,gender.toString(),fatherID,motherID,null);
        // Generate events for person (except marriage for root user) - birth, death
        jsonData.Location birthLoc = locData.getData()[random.nextInt(locData.getData().length)];
        Event birthday = new Event(util.createID(),username, person.getPersonID(), Float.parseFloat(birthLoc.getLatitude()),Float.parseFloat(birthLoc.getLongitude()), birthLoc.getCountry(), birthLoc.getCity(), "Birth",year + 5);
        jsonData.Location deathLoc = locData.getData()[random.nextInt(locData.getData().length)];
        Event deathday = new Event(util.createID(),username, person.getPersonID(), Float.parseFloat(deathLoc.getLatitude()),Float.parseFloat(deathLoc.getLongitude()), deathLoc.getCountry(), deathLoc.getCity(), "Death",year + 65);
        //save birth and death to events
        eDao.insert(birthday);
        eDao.insert(deathday);
        // Save person in database
        pDao.insert(person);
        return person;
    }

    //use for first/main user
    public Person generatePersonStart(Connection conn, String firstName, String lastName, String personID, Gender gender, int generations, String username, Integer year) throws DataAccessException {
        //set up daos
        EventDao eDao = new EventDao(conn);
        PersonDao pDao = new PersonDao(conn);
        //get random names from data
        Random random = new Random();
        util util = new util();
        //set up parents
        Person mother = null;
        Person father = null;
        if (generations > 0) {
            mother = generatePersonStart(conn, Gender.f, generations - 1, username, year - 20);
            father = generatePersonStart(conn, Gender.m, generations - 1, username, year - 20);
            // Set mother's and father's spouse IDs
            mother.setSpouseID(father.getPersonID());
            father.setSpouseID(mother.getPersonID());
            //update database for spouseID
            pDao.deleteSingle(mother.getPersonID());
            pDao.deleteSingle(father.getPersonID());
            pDao.insert(mother);
            pDao.insert(father);
            // Add marriage events to mother and father
            jsonData.Location location = locData.getData()[random.nextInt(locData.getData().length)];
            // (their marriage events must be in sync with each other)
            Event marriageM = new Event(util.createID(), username,mother.getPersonID(),Float.parseFloat(location.getLatitude()),Float.parseFloat(location.getLongitude()), location.getCountry(), location.getCity(),"Marriage",year);
            Event marriageF = new Event(util.createID(), username,father.getPersonID(),Float.parseFloat(location.getLatitude()),Float.parseFloat(location.getLongitude()), location.getCountry(), location.getCity(),"Marriage",year);
            //add marriage events
            eDao.insert(marriageM);
            eDao.insert(marriageF);
        }
        String fname = null;
        String sname = snameData.getData()[random.nextInt(snameData.getData().length)];
        if(gender.equals(Gender.f)){
            fname = fnameData.getData()[random.nextInt(fnameData.getData().length)];
        }
        else {
            fname = mnameData.getData()[random.nextInt(mnameData.getData().length)];
        }
        //check if parent are null
        String fatherID = null;
        if(father != null) {
            fatherID = father.getPersonID();
        }
        String motherID = null;
        if(mother != null) {
            motherID = mother.getPersonID();
        }

        // Set person's properties
        Person person = new Person(personID,username,firstName,lastName,gender.toString(),fatherID,motherID,null);
        // Generate events for person (except marriage for root user) - birth, death
        jsonData.Location birthLoc = locData.getData()[random.nextInt(locData.getData().length)];
        Event birthday = new Event(util.createID(),username, person.getPersonID(), Float.parseFloat(birthLoc.getLatitude()),Float.parseFloat(birthLoc.getLongitude()), birthLoc.getCountry(), birthLoc.getCity(), "Birth",year + 5);
        jsonData.Location deathLoc = locData.getData()[random.nextInt(locData.getData().length)];
        Event deathday = new Event(util.createID(),username, person.getPersonID(), Float.parseFloat(deathLoc.getLatitude()),Float.parseFloat(deathLoc.getLongitude()), deathLoc.getCountry(), deathLoc.getCity(), "Death",year + 65);
        //save birth and death to events
        eDao.insert(birthday);
        eDao.insert(deathday);
        // Save person in database
        pDao.insert(person);
        return person;
    }
}
