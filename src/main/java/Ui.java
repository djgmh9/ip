public class Ui {
    public static void showIntro() {
        String intro = """
                ____________________________________________________________
                 Hello! I'm Frenny
                 What can I do for you?
                ____________________________________________________________""";
        System.out.println(intro);
    }

    public static void showOutro() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public static void showListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void showDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public static void showAddMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    public static void showListSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void showMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public static void showUnmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}
