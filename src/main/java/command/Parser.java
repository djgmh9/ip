package command;

import exception.CorruptedFileException;
import exception.InvalidCommandException;
import exception.TimeFormatException;
import task.Task;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import ui.Ui;
import exception.FrennyException;

import java.util.Objects;

/**
 * The Parser class is responsible for processing user input and history data.
 * It interprets commands and manages tasks in the TaskList.
 */
public class Parser {
    /**
     * Processes a line from the history file and adds the corresponding task to the TaskList.
     *
     * @param taskList The TaskList to which the task will be added.
     * @param input    The line from the history file representing a task.
     * @throws FrennyException         If there is a general error related to task creation.
     * @throws TimeFormatException     If there is an error in the time format.
     * @throws InvalidCommandException If the command in the file is invalid.
     * @throws CorruptedFileException  If the file is corrupted.
     */
    public static void processHistory(TaskList taskList, String input) throws FrennyException, TimeFormatException, InvalidCommandException, CorruptedFileException {
        String[] parts = input.split(" \\| ", 2);
        boolean isDone;
        if (Objects.equals(parts[0], "1")) {
            isDone = true;
        } else if (Objects.equals(parts[0], "0")) {
            isDone = false;
        } else {
            throw new CorruptedFileException("Corrupted mark status in file :(");
        }
        String[] taskParts = parts[1].split(" ", 2);
        String taskType = taskParts[0];
        Command commandEnum = Command.fromString(taskType);
        if (Objects.equals(commandEnum, Command.TODO)) {
            Todo todo = Todo.addTodoTask(taskParts[1], isDone);
            taskList.addTask(todo);
        } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
            Deadline deadline = Deadline.addDeadlineTask(taskParts[1], isDone);
            taskList.addTask(deadline);
        } else if (Objects.equals(commandEnum, Command.EVENT)) {
            Event event = Event.addEventTask(taskParts[1], isDone);
            taskList.addTask(event);
        } else {
            throw new InvalidCommandException("Invalid command in file :(");
        }
    }

    /**
     * Processes user input and performs the corresponding action on the TaskList.
     *
     * @param taskList The TaskList to be modified based on user input.
     * @param input    The user input command.
     */
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
            } catch (FrennyException | TimeFormatException e) {
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
            } catch (FrennyException | TimeFormatException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of a event cannot be empty my fren :(");
            }
        } else {
            System.out.println("Idk what you mean :(");
        }
    }
}