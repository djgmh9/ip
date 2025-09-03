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
        for (int i = 0; i < items.size(); i++) {
            result.append(i + 1).append(". ").append(items.get(i)).append("\n");
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
     * @param indices Index of the task to retrieve.
     * @return Task at the specified index.
     */
    public Task[] getTasks(int... indices) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int index : indices) {
            int realIndex = index - 1;
            if (realIndex >= 0 && realIndex < items.size()) {
                tasks.add(items.get(realIndex));
            }
        }
        return tasks.toArray(new Task[0]); // Convert ArrayList to array
    }

    /**
     * Marks the task at the specified index as done.
     * @param indices Index of the task to be marked.
     */
    public void markTasks(int... indices) {
        for (int index : indices) {
            int realIndex = index - 1;
            if (realIndex >= 0 && realIndex < items.size()) {
                items.get(realIndex).mark();
            }
        }
    }

    /**
     * Unmarks the task at the specified index as not done.
     * @param indices Index of the task to be unmarked.
     */
    public void unmarkTasks(int... indices) {
        for (int index : indices) {
            int realIndex = index - 1;
            if (realIndex >= 0 && realIndex < items.size()) {
                items.get(realIndex).unmark();
            }
        }
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
     * @param indices Index of the task to be deleted.
     */
    public void deleteTasks(int... indices) {
        // Sort indices in descending order to avoid shifting issues
        java.util.Arrays.sort(indices); // Sort in ascending order
        for (int i = indices.length - 1; i >= 0; i--) { // Iterate in reverse order
            int realIndex = indices[i] - 1; // Convert to zero-based index
            if (realIndex >= 0 && realIndex < items.size()) {
                items.remove(realIndex);
            }
        }
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
