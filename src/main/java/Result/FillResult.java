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

    /**
     * @param message - message regarding fill success. If failed, explains why
     * @param success - if fill was successful or not
     */
    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
