import java.time.LocalDateTime;

public class Deadline extends Task {

    private final String by;
    private Time time;

    public Deadline(String description, String by, boolean isDone, Time time) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.getDateTime() + ")";
    }

    public static Deadline addDeadlineTask(String detail, boolean isDone) throws FrennyException {
        if (!detail.contains(" /by ")) {
            throw new FrennyException("The deadline must be specified with ' /by ' my fren :(");
        }
        if (detail.indexOf(" /by ") != detail.lastIndexOf(" /by ")) {
            throw new FrennyException("There should be only one ' /by ' in a deadline my fren :(");
        }
        String[] parts = detail.split(" /by ", 2);
        String description = parts[0];
        if (detail.trim().isEmpty() || description.trim().isEmpty()) {
            throw new FrennyException("The description of a deadline cannot be empty my fren :(");
        } if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new FrennyException("The deadline time cannot be empty my fren :(");
        }
        String by = parts[1];
        Time time = Time.parseDateTime(by);
        return new Deadline(description, by, isDone, time);
    }

    public String getCommand() {
        return String.format("%s | deadline %s /by %s", (isDone() ? "1" : "0"), this.description, this.by);
    }
}
