package Model;

import java.util.Objects;
import java.util.UUID;

public class Event {
    /**
     * eventID = identifier for the event object
     */
    private String eventID;
    /**
     * associatedusername = connection to a User object
     */
    private String associatedUsername;
    /**
     * personID = id of related PersonID
     */
    private String personID;
    /**
     * latitude - location of event
     */
    private float latitude;
    /**
     * longitude - location of event
     */
    private float longitude;
    /**
     * country - location of event
     */
    private String country;
    /**
     * city - location of event
     */
    private String city;
    /**
     * eventType - the type of event (e.g. birth, marriage, etc)
     */
    private String eventType;
    /**
     * year - when the event occurred
     */
    private int year;

    /**
     * contructor for event
     * @param associatedUsername - connection to a User object
     * @param personID - id of related PersonID
     * @param latitude - location of event
     * @param longitude - location of event
     * @param country - location of event
     * @param city - location of event
     * @param eventType - the type of event (e.g. birth, marriage, etc)
     * @param year - when the event occurred
     */
    public Event(String eventID,String associatedUsername, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() { return eventID; }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }
}
