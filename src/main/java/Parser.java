import java.util.Map;
import java.util.Objects;

// A simple class to store the parsed results in a structured way.
public class Parser {
    public static void processHistory(TaskList taskList, String input) {
        String[] parts = input.split(" \\| ", 2);
        boolean isDone = parts[0].equals("1");
        String[] taskParts = parts[1].split(" ", 2);
        String taskType = taskParts[0];
        Command commandEnum = Command.fromString(taskType);
        if (Objects.equals(commandEnum, Command.TODO)) {
            try {
                Todo todo = Todo.addTodoTask(taskParts[1], isDone);
                taskList.addTask(todo);
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
            try {
                Deadline deadline = Deadline.addDeadlineTask(taskParts[1], isDone);
                taskList.addTask(deadline);
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(commandEnum, Command.EVENT)) {
            try {
                Event event = Event.addEventTask(taskParts[1], isDone);
                taskList.addTask(event);
            } catch (FrennyException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Corrupted data in file :(");
        }
    }

    public static void processInput(TaskList taskList, String input) {
        // Implementation for adding a task
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        Command commandEnum = Command.fromString(taskType);
        if (Objects.equals(commandEnum, Command.LIST)) {
            Ui.showListMessage();
            for (int i = 0; i < taskList.getList().size(); i++) {
                System.out.println((i + 1) + ". " + taskList.getTask(i));
            }
        } else if (Objects.equals(commandEnum, Command.DELETE)) {
            Ui.showDeleteMessage(taskList.getTask(Integer.parseInt(parts[1]) - 1));
            taskList.deleteTask(Integer.parseInt(parts[1]) - 1);
            Ui.showListSize(taskList.getSize());
        } else if (Objects.equals(commandEnum, Command.MARK)) {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            task.mark();
            Ui.showMarkMessage(task);
        } else if (Objects.equals(commandEnum, Command.UNMARK)) {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            task.unmark();
            Ui.showUnmarkMessage(task);
        } else if (Objects.equals(commandEnum, Command.TODO)) {
            try {
                Todo todo = Todo.addTodoTask(parts[1], false);
                Ui.showAddMessage(todo);
                taskList.addTask(todo);
                Ui.showListSize(taskList.getSize());
            } catch (FrennyException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of a task cannot be empty my fren :(");
            }
        } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
            try {
                Deadline deadline = Deadline.addDeadlineTask(parts[1], false);
                Ui.showAddMessage(deadline);
                taskList.addTask(deadline);
                Ui.showListSize(taskList.getSize());
            } catch (FrennyException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of a deadline cannot be empty my fren :(");
            }
        } else if (Objects.equals(commandEnum, Command.EVENT)) {
            try {
                Event event = Event.addEventTask(parts[1], false);
                Ui.showAddMessage(event);
                taskList.addTask(event);
                Ui.showListSize(taskList.getSize());
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