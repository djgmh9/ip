public class Event extends Task {
    protected String from;
    protected String to;

    private Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static Event addEventTask(String detail, boolean isDone) throws FrennyException {
        String[] parts = detail.split(" /from ", 2);
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = timeParts[1];
        return new Event(description, from, to, isDone);
    }

    public String getCommand() {
        return String.format("%s | event %s /from %s /to %s", (isDone() ? "1" : "0"), this.description, this.from, this.to);
    }
}
