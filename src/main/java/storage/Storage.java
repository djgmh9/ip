package storage;

import command.Parser;
import exception.CorruptedFileException;
import exception.FrennyException;
import exception.InvalidCommandException;
import exception.TimeFormatException;
import task.Task;
import task.TaskList;

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

    public void reinitFile() throws IOException {
        File historyFile = new File(filePath);
        if (historyFile.exists()) {
            historyFile.delete();
        }
        historyFile.getParentFile().mkdirs();
        historyFile.createNewFile();
    }

    private void handleCorruptedFile() {
        System.out.println("""
                            Corrupted history file.
                            Attempt to recover some saved tasks
                            (totally depends on your luck)? (y/n)
                            """);

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            try {
                reinitFile();
                System.out.println("Old history deleted. A new file has been created.");
            } catch (IOException e) {
                System.out.println("An error occurred while reinitializing the file.");
            }
        }
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
                } catch (InvalidCommandException | TimeFormatException | FrennyException | CorruptedFileException e) {
                    handleCorruptedFile();
                }
            } else {
                historyFile.getParentFile().mkdirs();
                historyFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            handleCorruptedFile();
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
