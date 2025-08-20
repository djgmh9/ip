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

    public static Deadline addDeadlineTask(String detail) throws FrennyException {
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
        return new Deadline(description, by);
    }
}
