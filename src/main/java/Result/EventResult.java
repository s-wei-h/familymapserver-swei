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
    public EventResult(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year, boolean success, String message) {
        this.success = success;
        if(success) {
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
        else {
            this.message = message;
        }
    }
}
