package Model;

public class Person {
    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID = null;
    private String motherID = null;
    private String spouseID = null;

    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        if(gender == "f" || gender == "m") {
            this.gender = gender;
        }
        if(fatherID != null) {
            this.fatherID = fatherID;
        }
        if(motherID != null) {
            this.motherID = motherID;
        }
        if(spouseID != null) {
            this.spouseID = spouseID;
        }
    }
}
