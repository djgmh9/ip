import java.util.Objects;
import java.util.Scanner;

public class Frenny {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n" +
                " Hello! I'm Frenny\n" +
                " What can I do for you?\n";
        System.out.println(logo);
        while (true) {
            System.out.print("Enter a command: ");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
                    System.out.println("____________________________________________________________\n");
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
            System.out.println(input);
            System.out.println("____________________________________________________________\n");
        }
    }
}
