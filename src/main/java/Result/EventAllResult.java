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
     * if event search failed, why
     */
    private String message;

    public EventAllResult(Event[] data, boolean success, String message) {
        this.success = success;
        if(success) {
            this.data = data;
        }
        else {
            this.message = message;
        }
    }
}
