package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final List<Task> items = new ArrayList<>();

    public List<Task> getList() {
        return items;
    }

    public int getSize() {
        return items.size();
    }

    public Task getTask(int index) {
        return items.get(index);
    }

    public void markTask(int index) {
        items.get(index).mark();
    }

    public void unmarkTask(int index) {
        items.get(index).unmark();
    }

    public void addTask(Task task) {
        items.add(task);
    }

    public void deleteTask(int index) {
        items.remove(index);
    }
}
