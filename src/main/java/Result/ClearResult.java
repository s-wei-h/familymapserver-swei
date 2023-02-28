package Result;

public class ClearResult {
    /**
     * message = message regarding clear success. If failed, explains why
     */
    private String message;
    /**
     * success = if clear was successful or not
     */
    private boolean success;

    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
