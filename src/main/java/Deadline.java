public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline addDeadlineTask(String detail) {
        String[] parts = detail.split(" /by ", 2);
        String description = parts[0];
        String by = parts[1];
        return new Deadline(description, by);
    }
}
