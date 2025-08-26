package frenny;

import command.Command;
import command.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.*;

public class Frenny {
    private static final String projectDir = System.getProperty("user.dir");
    private final Storage storage;
    private final TaskList taskList;

    public Frenny(String filePath) {
        storage = new Storage(filePath);
        Ui.showIntro();
        TaskList taskList = new TaskList();
        this.taskList = taskList;
        storage.readFile(taskList);
    }

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
                storage.writeFile(taskList.getList());
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
            Ui.showLine();
        }
    }

    public static void main(String[] args) {
        String filePath = projectDir + "/data/frenny.txt";
        new Frenny(filePath).run();
    }
}