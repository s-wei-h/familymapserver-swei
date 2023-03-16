package Result;

public class EventResult {
    /**
     * associatedUsername = username associated with specified ID
     */
    private String associatedUsername;
    /**
     * eventID = event searched up
     */
    private String eventID;
    /**
     * personID = person associated with event
     */
    private String personID;
    /**
     * latitude = latitude location for event
     */
    private float latitude;
    /**
     * longitude = longitude location for event
     */
    private float longitude;
    /**
     * country = country location for event
     */
    private String country;
    /**
     * city = city location for event
     */
    private String city;
    /**
     * eventType = type of event
     */
    private String eventType;
    /**
     * year = year for event
     */
    private int year;
    /**
     * success = if the event search was successful or not
     */
    private boolean success;
    /**
     * message = if event search failed, why
     */
    private String message;

    /**
     *
     * @param associatedUsername - username associated with specified ID
     * @param eventID - event searched up
     * @param personID - person associated with event
     * @param latitude - latitude location for event
     * @param longitude - longitude location for event
     * @param country - country location for event
     * @param city - city location for event
     * @param eventType - type of event
     * @param year - year for event
     * @param success - if the event search was successful or not
     * @param message - if event search failed, why
     */
    public EventResult(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year, boolean success) {
        this.success = success;
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public EventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
