package Result;

public class FillResult {
    /**
     * message = message regarding fill success. If failed, explains why
     */
    private String message;
    /**
     * success = if fill was successful or not
     */
    private boolean success;

    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
