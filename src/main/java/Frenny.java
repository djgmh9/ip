import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Frenny {
    private static final String projectDir = System.getProperty("user.dir");
    private static final String filePath = projectDir + "/data/frenny.txt";

    public static void writeFile(List<Task> items) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task item : items) {
               // Write each task to the file
            fw.write(item.getCommand() + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        String intro = """
                ____________________________________________________________
                 Hello! I'm Frenny
                 What can I do for you?
                ____________________________________________________________""";
        System.out.println(intro);
        TaskList taskList = new TaskList();
        try {
            File historyFile = new File(filePath);
            if (historyFile.exists()) {
                try (Scanner fileScanner = new Scanner(historyFile)) {
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        taskList.processHistory(line);
                    }
                }
            } else {
                historyFile.getParentFile().mkdirs();
                historyFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        Scanner consoleScanner = new Scanner(System.in);
        while (true) {
            String input = consoleScanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            Command commandEnum = Command.fromString(command);
            System.out.println("____________________________________________________________");
            if (Objects.equals(commandEnum, Command.BYE)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                taskList.processInput(input);
            }
            try {
                writeFile(taskList.getTasks());
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
            System.out.println("____________________________________________________________");
        }
    }
}