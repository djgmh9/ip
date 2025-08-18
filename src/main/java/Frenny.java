import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Frenny {
    private static List<String> items = new ArrayList<>();

    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Frenny\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(intro);
        while (true) {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
                    System.out.println("____________________________________________________________\n");
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (Objects.equals(input, "list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else {
                System.out.println("added: " + input + "\n");
            }
            items.add(input);
            System.out.println("____________________________________________________________\n");
        }
    }
}
