package task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, and retrieve tasks from the list.
 */
public class TaskList {
    private static final List<Task> items = new ArrayList<>();

    /**
     * Gets the list of tasks.
     * @return List of tasks.
     */
    public List<Task> getList() {
        return items;
    }

    /**
     * Gets the number of tasks in the list.
     * @return Number of tasks.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Gets the task at the specified index.
     * @param index Index of the task to retrieve.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return items.get(index);
    }

    /**
     * Adds a new task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        items.add(task);
    }

    /**
     * Deletes the task at the specified index from the list.
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        items.remove(index);
    }
}
