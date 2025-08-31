package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, and retrieve tasks from the list.
 */
public class TaskList {
    private final List<Task> items;

    public TaskList(List<Task> items) {
        this.items = items;
    }

    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Prints the list of tasks with their indices.
     */
    public String printList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getListSize(); i++) {
            result.append(i + 1).append(". ").append(getTask(i)).append("\n");
        }
        System.out.println(result.toString().trim());
        return result.toString().trim();
    }
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
    public int getListSize() {
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

    /**
     * Searches for tasks containing the specified keyword in their description.
     * The search is case-insensitive.
     * @param keyword Keyword to search for.
     * @return A new TaskList containing tasks that match the keyword.
     */
    public TaskList searchTasksByKeyword(String keyword) {
        // Convert the search keyword to lowercase once to be efficient
        String lowerCaseKeyword = keyword.toLowerCase();

        return new TaskList(items.stream() // 1. Convert the list to a stream
                .filter(task -> task
                        .getDescription()
                        .toLowerCase()
                        .contains(lowerCaseKeyword))// 2. Keep only matching tasks
                .collect(Collectors.toList())); // 3. Collect the results back into a new list
    }
}
