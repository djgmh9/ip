public class Event extends Task {
    protected String from;
    protected String to;

    private Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static Event addEventTask(String detail) {
        String[] parts = detail.split(" /from ", 2);
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = timeParts[1];
        return new Event(description, from, to);
    }
}
