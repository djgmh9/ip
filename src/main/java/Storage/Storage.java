package Storage;

import Command.Parser;
import Task.Task;
import Task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void readFile(TaskList taskList) {
        try {
            File historyFile = new File(filePath);
            if (historyFile.exists()) {
                try (Scanner fileScanner = new Scanner(historyFile)) {
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        Parser.processHistory(taskList, line);
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
    }

    public void writeFile(List<Task> items) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task item : items) {
            // Write each task to the file
            fw.write(item.getCommand() + System.lineSeparator());
        }
        fw.close();
    }
}
