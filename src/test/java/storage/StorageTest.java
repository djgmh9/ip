package storage;

import org.junit.jupiter.api.Test;

import task.TaskList;

public class StorageTest {
    private String normalFilePath = "data/test_frenny.txt"; // Use a test file path
    private Storage normalStorage = new Storage(normalFilePath);

    @Test
    public void correctFileFormat_success() {
        // Implement test for correct file format
        TaskList taskList = new TaskList();
        normalStorage.readFile(taskList);
    }
}
