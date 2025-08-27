package frenny;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import command.Command;
import command.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Frenny class is the main entry point for the Frenny application.
 * It initializes the application, processes user input, and manages the task list.
 */
public class Frenny {
    private static final String projectDir = System.getProperty("user.dir");
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a Frenny application with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Frenny(String filePath) {
        storage = new Storage(filePath);
        Ui.showIntro();
        TaskList taskList = new TaskList();
        this.taskList = taskList;
        storage.readFile(taskList);
    }

    /**
     * Runs the main loop of the Frenny application, processing user input until the user decides to exit.
     */
    public void run() {
        Scanner consoleScanner = new Scanner(System.in);
        while (true) {
            String input = consoleScanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            Command commandEnum = Command.fromString(command);
            Ui.showLine();
            if (Objects.equals(commandEnum, Command.BYE)) {
                Ui.showOutro();
                break;
            } else {
                Parser.processInput(taskList, input);
            }
            try {
                storage.writeFile(taskList);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
            Ui.showLine();
        }
    }

    /**
     * The main method to start the Frenny application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String filePath = projectDir + "/data/frenny.txt";
        new Frenny(filePath).run();
    }
}
