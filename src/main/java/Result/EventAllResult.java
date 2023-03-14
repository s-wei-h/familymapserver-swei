package Result;

import Model.Event;

public class EventAllResult {
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
    public EventAllResult(Event[] data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public EventAllResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
