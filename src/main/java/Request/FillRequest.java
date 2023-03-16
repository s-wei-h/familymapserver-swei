package Request;

public class FillRequest {

    private String associatedUsername;
    private Integer generations;

    public FillRequest(String associatedUsername, Integer generations) {
        this.associatedUsername = associatedUsername;
        this.generations = generations;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public Integer getGenerations() {
        return generations;
    }

    public void setGenerations(Integer generations) {
        this.generations = generations;
    }
}
