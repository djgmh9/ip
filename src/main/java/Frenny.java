import java.util.*;

public class Frenny {
    private static List<Task> items = new ArrayList<>();

    public static void main(String[] args) throws FrennyException {
        String intro = """
                ____________________________________________________________
                 Hello! I'm Frenny
                 What can I do for you?
                ____________________________________________________________""";
        System.out.println(intro);
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            System.out.println("____________________________________________________________");
            if (Objects.equals(command, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (Objects.equals(command, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else if (Objects.equals(command, "mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                Task task = items.get(taskNumber);
                task.mark();
                System.out.println(task);
            } else if (Objects.equals(command, "unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                Task task = items.get(taskNumber);
                task.unmark();
                System.out.println(task);
            } else if (Objects.equals(command, "todo")) {
                try {
                    Todo todo = Todo.addTodoTask(parts[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    items.add(todo);
                    System.out.println("Now you have " + items.size() + " tasks in the list.");
                } catch (FrennyException e) {
                    System.out.println(e.getMessage());
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The description of a task cannot be empty my fren :(");
                    continue;
                }
            } else if (Objects.equals(command, "deadline")) {
                try {
                    Deadline deadline = Deadline.addDeadlineTask(parts[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    items.add(deadline);
                    System.out.println("Now you have " + items.size() + " tasks in the list.");
                } catch (FrennyException e) {
                    System.out.println(e.getMessage());
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The description of a deadline cannot be empty my fren :(");
                    continue;
                }
            } else if (Objects.equals(command, "event")) {
                System.out.println("Got it. I've added this task:");
                Event event = Event.addEventTask(parts[1]);
                System.out.println(event);
                items.add(event);
                System.out.println("Now you have " + items.size() + " tasks in the list.");
            } else {
                System.out.println("Idk what you mean :(");
            }
            System.out.println("____________________________________________________________");
        }
    }
}