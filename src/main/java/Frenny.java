import java.util.*;

public class Frenny {
    private static final List<Task> items = new ArrayList<>();

    public static void main(String[] args) {
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
            Command commandEnum = Command.fromString(command);
            System.out.println("____________________________________________________________");
            if (Objects.equals(commandEnum, Command.BYE)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (Objects.equals(commandEnum, Command.LIST)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else if (Objects.equals(commandEnum, Command.DELETE)) {
                System.out.println("Noted. I've removed this task:");
                System.out.println(items.get(Integer.parseInt(parts[1]) - 1));
                items.remove(Integer.parseInt(parts[1]) - 1);
                System.out.println("Now you have " + items.size() + " tasks in the list.");
            } else if (Objects.equals(commandEnum, Command.MARK)) {
                System.out.println("Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                Task task = items.get(taskNumber);
                task.mark();
                System.out.println(task);
            } else if (Objects.equals(commandEnum, Command.UNMARK)) {
                System.out.println("OK, I've marked this task as not done yet:");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                Task task = items.get(taskNumber);
                task.unmark();
                System.out.println(task);
            } else if (Objects.equals(commandEnum, Command.TODO)) {
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
            } else if (Objects.equals(commandEnum, Command.DEADLINE)) {
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
            } else if (Objects.equals(commandEnum, Command.EVENT)) {
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