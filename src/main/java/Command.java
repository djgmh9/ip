public enum Command {
    LIST,
    DELETE,
    MARK,
    UNMARK,
    DEADLINE,
    EVENT,
    TODO,
    BYE;

    public static Command fromString(String input) {
        if (input == null) {
            return null;
        }
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // Return null for any invalid command string
        }
    }
}
