package ui;

import task.Task;

/**
 * Ui class handles the user interface interactions.
 */
public class Ui {
    /**
     * Displays the introduction message to the user.
     */
    public static void showIntro() {
        String intro = """
                ____________________________________________________________
                 Hello! I'm Frenny
                 What can I do for you?
                ____________________________________________________________""";
        System.out.println(intro);
    }

    /**
     * Displays the outro message to the user.
     */
    public static void showOutro() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
    }

    /**
     * Displays a line separator for better readability.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the message indicating the start of the task list.
     */
    public static String showListMessage(int listSize) {
        String message;
        if (listSize == 0) {
            message = "No task found :(";
        } else {
            message = "Here are the tasks in your list:";
        }
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating a task has been deleted.
     *
     * @param task The task that has been deleted.
     */
    public static String showDeleteMessage(Task task) {
        String message = "Noted. I've removed this task:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating a task has been added.
     *
     * @param task The task that has been added.
     */
    public static String showAddMessage(Task task) {
        String message = "Got it. I've added this task:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the current size of the task list.
     *
     * @param listSize The current number of tasks in the list.
     */
    public static String showListSize(int listSize) {
        String message;
        if (listSize == 1) {
            message = "Now you have " + listSize + " task in the list.";
        } else {
            message = "Now you have " + listSize + " tasks in the list.";
        }
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static String showMarkMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating a task has been unmarked as not done.
     *
     * @param task The task that has been unmarked as not done.
     */
    public static String showUnmarkMessage(Task task) {
        String message = "OK, I've marked this task as not done yet:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating the start of the search results.
     */
    public static String showFindMessage() {
        String message = "Here are the matching tasks in your list:";
        System.out.println(message);
        return message;
    }
}
