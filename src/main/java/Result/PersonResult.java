package Result;

public class PersonResult {
    /**
     * associatedUsername = username associated with person
     */
    private String associatedUsername;
    /**
     * personID = personID associated with person
     */
    private String personID;
    /**
     * firstName = first name associated with person
     */
    private String firstName;
    /**
     * lastName = last name associated with person
     */
    private String lastName;
    /**
     * gender = the person's gender
     */
    private String gender;
    /**
     * fatherID = (optional) personID of the person's father
     */
    private String fatherID;
    /**
     * fatherID = (optional) personID of the person's mother
     */
    private String motherID;
    /**
     * fatherID = (optional) personID of the person's spouse
     */
    private String spouseID;
    /**
     * success = if person was found
     */
    private boolean success;
    /**
     * message = if the person search failed, why
     */
    private String message;

    /**
     *
     * @param associatedUsername - username associated with person
     * @param personID - personID associated with person
     * @param firstName -  first name associated with person
     * @param lastName - last name associated with person
     * @param gender - the person's gender
     * @param fatherID - (optional) personID of the person's father
     * @param motherID - (optional) personID of the person's mother
     * @param spouseID - (optional) personID of the person's spouse
     * @param success - if person was found
     * @param message - if the person search failed, why
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success, String message) {
        this.success = success;
        if(success) {
            this.associatedUsername = associatedUsername;
            this.personID = personID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.fatherID = fatherID;
            this.motherID = motherID;
            this.spouseID = spouseID;
        }
        else {
            this.message = message;
        }
    }
}
