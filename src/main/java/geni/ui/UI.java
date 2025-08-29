package geni.ui;

import geni.task.Task;
import geni.task.TaskList;
import java.util.ArrayList;
/**
 * Handles all user interactions.
 * Provides methods to display messages, tasks, and errors.
 */
public class UI {
    /**
     * Returns the greeting message.
     *
     * @return greeting string
     */
    public String getGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Hello! I'm Geni\n");

        sb.append(" What can I do for you?\n");
        sb.append("____________________________________________________________\n");
        return sb.toString();
    }
    /**
     * Returns the exit message.
     *
     * @return exit string
     */
    public String getExit() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Bye. Hope to see you again soon!\n");
        sb.append("____________________________________________________________");
        return sb.toString();
    }
    /**
     * Prints the status of a task as marked or unmarked.
     *
     * @param task task to update
     * @param done true if task is done, false otherwise
     */
    public void printMark(Task task, boolean done) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        if (done) {
            sb.append("Nice! I've marked this task as done:\n");
        } else {
            sb.append("OK, I've marked this task as not done yet:\n");
        }
        sb.append("[")
                .append(task.getStatusIcon())
                .append("] ")
                .append(task.getDescription()).append('\n');
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    /**
     * Prints the full list of tasks.
     *
     * @param tasks task list to print
     */
    public void printList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    /**
     * Prints confirmation of adding a task.
     *
     * @param task  added task
     * @param count number of tasks in the list
     */
    public void printAdded(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Got it. I've added this task:\n");
        sb.append("  ").append(task).append("\n");
        sb.append("Now you have ").append(count).append(" tasks in the list.\n");
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    /**
     * Prints a divider line.
     */
    public void line() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    /**
     * Prints confirmation of deleting a task.
     *
     * @param task  deleted task
     * @param count number of tasks remaining
     */
    public void printDeleted(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Noted. I've removed this task:\n");
        sb.append("   ").append(task).append("\n");
        sb.append("Now you have ").append(count).append(" tasks in the list.\n");
        sb.append("____________________________________________________________\n");
        System.out.print(sb);

    }
    /**
     * Prints an error message when loading fails.
     */
    public void showLoadingError() {
        System.out.println("OOPS! Something went wrong! Starting with an empty task list.");
    }
}
