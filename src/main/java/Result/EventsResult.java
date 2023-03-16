package Result;

import Model.Event;

public class EventsResult {
    /**
     * data = json array of event objects for all family members of current user
     */
    private Event[] data;
    /**
     * success = if events were successfully found
     */
    private boolean success;
    /**
     * message = if event search failed, why
     */
    private String message;

    /**
     *
     * @param data - json array of event objects for all family members of current user
     * @param success - if events were successfully found
     * @param message - if event search failed, why
     */
    public EventsResult(Event[] data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public EventsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
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
