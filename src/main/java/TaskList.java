import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private static final List<Task> items = new ArrayList<>();

    public List<Task> getTasks() {
        return items;
    }

    public void processHistory(String input) {
        String[] parts = input.split(" \\| ", 2);
        boolean isDone = parts[0].equals("1");
        String[] taskParts = parts[1].split(" ", 2);
        String taskType = taskParts[0];
        Command commandEnum = Command.fromString(taskType);
        if (Objects.equals(commandEnum, Command.TODO)) {
            try {
                Todo todo = Todo.addTodoTask(taskParts[1], isDone);
                items.add(todo);
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
            try {
                Deadline deadline = Deadline.addDeadlineTask(taskParts[1], isDone);
                items.add(deadline);
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(commandEnum, Command.EVENT)) {
            try {
                Event event = Event.addEventTask(taskParts[1], isDone);
                items.add(event);
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Corrupted data in file :(");
        }
    }

    public void processInput(String input) {
        // Implementation for adding a task
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        Command commandEnum = Command.fromString(taskType);
        if (Objects.equals(commandEnum, Command.LIST)) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        } else if (Objects.equals(commandEnum, Command.DELETE)) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(items.get(Integer.parseInt(parts[1]) - 1));
            items.remove(Integer.parseInt(parts[1]) - 1);
            System.out.println("Now you have " + items.size() + " tasks in the list.");
        } else if (Objects.equals(commandEnum, Command.MARK)) {
            System.out.println("Nice! I've marked this task as done:");
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task task = items.get(taskNumber);
            task.mark();
            System.out.println(task);
        } else if (Objects.equals(commandEnum, Command.UNMARK)) {
            System.out.println("OK, I've marked this task as not done yet:");
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task task = items.get(taskNumber);
            task.unmark();
            System.out.println(task);
        } else if (Objects.equals(commandEnum, Command.TODO)) {
            try {
                Todo todo = Todo.addTodoTask(parts[1], false);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                items.add(todo);
                System.out.println("Now you have " + items.size() + " tasks in the list.");
            } catch (FrennyException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of a task cannot be empty my fren :(");
            }
        } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
            try {
                Deadline deadline = Deadline.addDeadlineTask(parts[1], false);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                items.add(deadline);
                System.out.println("Now you have " + items.size() + " tasks in the list.");
            } catch (FrennyException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of a deadline cannot be empty my fren :(");
            }
        } else if (Objects.equals(commandEnum, Command.EVENT)) {
            try {
                Event event = Event.addEventTask(parts[1], false);
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                items.add(event);
                System.out.println("Now you have " + items.size() + " tasks in the list.");
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of a event cannot be empty my fren :(");
            }
        } else {
            System.out.println("Idk what you mean :(");
        }
    }
}
