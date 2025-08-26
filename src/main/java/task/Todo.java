package task;

import exception.FrennyException;

public class Todo extends Task {
    private Todo (String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo addTodoTask(String detail, boolean isDone) throws FrennyException {
        if (detail.trim().isEmpty()) {
            throw new FrennyException("The description of a todo cannot be empty my fren :(");
        }
        return new Todo(detail, isDone);
    }

    public String getCommand() {
        return String.format("%s | todo %s", (isDone() ? "1" : "0"), this.description);
    }
}
