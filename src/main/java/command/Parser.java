package command;

import java.util.Arrays;
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
        assert taskList != null : "TaskList should not be null";
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
        CommandType commandType = CommandType.fromString(taskType);
        switch (commandType) {
        case TODO -> {
            Todo todo = Todo.addTodoTask(taskParts[1], isDone);
            taskList.addTask(todo);
        }
        case DEADLINE -> {
            Deadline deadline = Deadline.addDeadlineTask(taskParts[1], isDone);
            taskList.addTask(deadline);
        }
        case EVENT -> {
            Event event = Event.addEventTask(taskParts[1], isDone);
            taskList.addTask(event);
        }
        default -> throw new CorruptedFileException("Corrupted task type in file :(");
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
        assert taskList != null : "TaskList should not be null";
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        CommandType commandType = CommandType.fromString(taskType);
        switch (commandType) {
        case BYE -> {
            return Ui.showOutro();
        }
        case LIST -> {
            StringBuilder response = new StringBuilder();
            String listMessage = Ui.showListMessage(taskList.getListSize());
            response.append(listMessage).append("\n");
            String result = taskList.printList();
            response.append(result);
            return response.toString();
        }
        case FIND -> {
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
        }
        case DELETE -> {
            try {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    String errorMessage = "Please provide valid task numbers to delete my fren :(";
                    System.out.println(errorMessage);
                    return errorMessage;
                }
                StringBuilder response = new StringBuilder();
                int[] taskNumbers = stringToIntArray(parts[1]);
                Task[] tasks = taskList.getTasks(taskNumbers);
                String deleteMessage = Ui.showDeleteMessage(tasks);
                response.append(deleteMessage).append("\n");
                taskList.deleteTasks(taskNumbers);
                String result = Ui.showListSize(taskList.getListSize());
                response.append(result);
                return response.toString();
            } catch (NumberFormatException e) {
                String errorMessage = "Please provide valid task numbers to delete my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
        }
        case MARK -> {
            try {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    String errorMessage = "Please provide valid task numbers to mark my fren :(";
                    System.out.println(errorMessage);
                    return errorMessage;
                }
                int[] taskNumbers = stringToIntArray(parts[1]);
                taskList.markTasks(taskNumbers);
                Task[] tasks = taskList.getTasks(taskNumbers);
                System.out.println(Arrays.toString(tasks));
                return Ui.showMarkMessage(tasks);
            } catch (NumberFormatException e) {
                String errorMessage = "Please provide valid task numbers to mark my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
        }
        case UNMARK -> {
            try {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    String errorMessage = "Please provide valid task numbers to unmark my fren :(";
                    System.out.println(errorMessage);
                    return errorMessage;
                }
                int[] taskNumbers = stringToIntArray(parts[1]);
                taskList.unmarkTasks(taskNumbers);
                Task[] tasks = taskList.getTasks(taskNumbers);
                return Ui.showUnmarkMessage(tasks);
            } catch (NumberFormatException e) {
                String errorMessage = "Please provide valid task numbers to unmark my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
        }
        case TODO -> {
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
        }
        case DEADLINE -> {
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
        }
        case EVENT -> {
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
        }
        default -> {
            String errorMessage = "Idk what you want :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
        }
    }

    private static int[] stringToIntArray(String input) {
        String[] parts = input.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }
}
