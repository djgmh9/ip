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
    public static void showListMessage(int listSize) {
        if (listSize == 0) {
            System.out.println("No task found :(");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
    }

    /**
     * Displays the message indicating a task has been deleted.
     *
     * @param task The task that has been deleted.
     */
    public static void showDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Displays the message indicating a task has been added.
     *
     * @param task The task that has been added.
     */
    public static void showAddMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    /**
     * Displays the current size of the task list.
     *
     * @param listSize The current number of tasks in the list.
     */
    public static void showListSize(int listSize) {
        if (listSize == 1) {
            System.out.println("Now you have " + listSize + " task in the list.");
        } else {
            System.out.println("Now you have " + listSize + " tasks in the list.");
        }
    }

    /**
     * Displays the message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void showMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays the message indicating a task has been unmarked as not done.
     *
     * @param task The task that has been unmarked as not done.
     */
    public static void showUnmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays the message indicating the start of the search results.
     */
    public static void showFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
