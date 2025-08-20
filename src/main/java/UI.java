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
        sb.append(" Bye. Hope to see you again soon!\n");
        sb.append("____________________________________________________________");
        return sb.toString();
    }
}
