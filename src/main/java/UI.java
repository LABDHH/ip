import java.util.ArrayList;

public class UI {
    public String getGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Hello! I'm Geni\n");

        sb.append(" What can I do for you?\n");
        sb.append("____________________________________________________________\n");
        return sb.toString();
    }

    public String getExit() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Bye. Hope to see you again soon!\n");
        sb.append("____________________________________________________________");
        return sb.toString();
    }
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
    public void printList(ArrayList<Task> store) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < store.size(); i++) {
            sb.append(i + 1).append(".").append(store.get(i)).append("\n");
        }
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    public void printAdded(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Got it. I've added this task:\n");
        sb.append("  ").append(task).append("\n");
        sb.append("Now you have ").append(count).append(" tasks in the list.\n");
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    public void line() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        System.out.print(sb);
    }
    public void printDeleted(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Noted. I've removed this task:\n");
        sb.append("   ").append(task).append("\n");
        sb.append("Now you have ").append(count).append(" tasks in the list.\n");
        sb.append("____________________________________________________________\n");
        System.out.print(sb);

    }
}
