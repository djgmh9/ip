public class Todo extends Task {
    private Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo addTodoTask(String detail) throws FrennyException {
        if (detail.trim().isEmpty()) {
            throw new FrennyException("The description of a todo cannot be empty my fren :(");
        }
        return new Todo(detail);
    }
}
