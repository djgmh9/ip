public class Todo extends Task {
    private Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo addTodoTask(String detail) {
        return new Todo(detail);
    }
}
