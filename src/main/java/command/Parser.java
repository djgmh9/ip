package command;

import java.util.Objects;

import exception.CorruptedFileException;
import exception.FrennyException;
import exception.InvalidCommandException;
import exception.TimeFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;


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
    public static void processHistory(TaskList taskList, String input)
            throws FrennyException, TimeFormatException, InvalidCommandException, CorruptedFileException {
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
    public static String processInput(TaskList taskList, String input) {
        // Implementation for adding a task
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        Command commandEnum = Command.fromString(taskType);
        if (Objects.equals(commandEnum, Command.LIST)) {
            StringBuilder response = new StringBuilder();
            String listMessage = Ui.showListMessage(taskList.getListSize());
            response.append(listMessage).append("\n");
            String result = taskList.printList();
            response.append(result);
            return response.toString();
        } else if (Objects.equals(commandEnum, Command.FIND)) {
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                String errorMessage = "The search keyword cannot be empty my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
            String keyword = parts[1].trim();
            StringBuilder response = new StringBuilder();
            String findMessage = Ui.showFindMessage();
            response.append(findMessage).append("\n");
            String findResult = taskList.searchTasksByKeyword(keyword).printList();
            response.append(findResult);
            return response.toString();
        } else if (Objects.equals(commandEnum, Command.DELETE)) {
            StringBuilder response = new StringBuilder();
            String deleteMessage = Ui.showDeleteMessage(taskList.getTask(Integer.parseInt(parts[1]) - 1));
            response.append(deleteMessage).append("\n");
            taskList.deleteTask(Integer.parseInt(parts[1]) - 1);
            String result = Ui.showListSize(taskList.getListSize());
            response.append(result);
            return response.toString();
        } else if (Objects.equals(commandEnum, Command.MARK)) {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            task.mark();
            return Ui.showMarkMessage(task);
        } else if (Objects.equals(commandEnum, Command.UNMARK)) {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            task.unmark();
            return Ui.showUnmarkMessage(task);
        } else if (Objects.equals(commandEnum, Command.TODO)) {
            try {
                Todo todo = Todo.addTodoTask(parts[1], false);
                StringBuilder response = new StringBuilder();
                String addMessage = Ui.showAddMessage(todo);
                response.append(addMessage).append("\n");
                taskList.addTask(todo);
                String result = Ui.showListSize(taskList.getListSize());
                response.append(result);
                return response.toString();
            } catch (FrennyException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = "The description of a task cannot be empty my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
        } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
            try {
                Deadline deadline = Deadline.addDeadlineTask(parts[1], false);
                StringBuilder response = new StringBuilder();
                String addMessage = Ui.showAddMessage(deadline);
                response.append(addMessage).append("\n");
                taskList.addTask(deadline);
                String result = Ui.showListSize(taskList.getListSize());
                response.append(result);
                return response.toString();
            } catch (FrennyException | TimeFormatException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = "The description of a task cannot be empty my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
        } else if (Objects.equals(commandEnum, Command.EVENT)) {
            try {
                Event event = Event.addEventTask(parts[1], false);
                StringBuilder response = new StringBuilder();
                String addMessage = Ui.showAddMessage(event);
                response.append(addMessage).append("\n");
                taskList.addTask(event);
                String result = Ui.showListSize(taskList.getListSize());
                response.append(result);
                return response.toString();
            } catch (FrennyException | TimeFormatException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = "The description of a task cannot be empty my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
        } else {
            String errorMessage = "Idk what you mean :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }
}
