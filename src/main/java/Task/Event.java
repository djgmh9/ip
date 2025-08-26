package Task;

import Exception.FrennyException;
import Time.Time;

public class Event extends Task {
    private String from;
    private String to;
    private Time timeFrom;
    private Time timeTo;

    private Event(String description, String from, String to, boolean isDone, Time timeFrom, Time timeTo) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + timeFrom.getDateTime() + " to: " + timeTo.getDateTime() + ")";
    }

    public static Event addEventTask(String detail, boolean isDone) throws FrennyException {
        String[] parts = detail.split(" /from ", 2);
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = timeParts[1];
        Time timeFrom = Time.parseDateTime(from);
        Time timeTo = Time.parseDateTime(to);
        return new Event(description, from, to, isDone, timeFrom, timeTo);
    }

    public String getCommand() {
        return String.format("%s | event %s /from %s /to %s", (isDone() ? "1" : "0"), this.description, this.from, this.to);
    }
}
