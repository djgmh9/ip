import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Frenny {
    private static List<Task> items = new ArrayList<>();

    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Frenny\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(intro);
        while (true) {
            Scanner s = new Scanner(System.in);
            String input = s.next();
                    System.out.println("____________________________________________________________\n");
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (Objects.equals(input, "list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else if (Objects.equals(input, "mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(s.next()) - 1;
                Task task = items.get(taskNumber);
                task.mark();
                System.out.println(task);
            } else if (Objects.equals(input, "unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int taskNumber = Integer.parseInt(s.next()) - 1;
                Task task = items.get(taskNumber);
                task.unmark();
                System.out.println(task);
            } else {
                String item = input + s.nextLine();
                Task task = new Task(item);
                items.add(task);
                System.out.println("added: " + item + "\n");
            }
            System.out.println("____________________________________________________________\n");
        }
    }
}