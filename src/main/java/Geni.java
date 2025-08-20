import java.util.ArrayList;
import java.util.Scanner;

public class Geni {
    private UI ui;

    public Geni() {
        ui = new UI();
    }
    public void run() {

        System.out.println(ui.getGreeting());
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine().trim();
        String[] inpt = inp.split(" ");
        ArrayList<Task> store = new ArrayList<>();

        while (!inpt[0].equals("bye")) {
            if (inpt[0].equals("mark") || inpt[0].equals("unmark")) {
                int i = Integer.parseInt(inpt[1]) - 1;
                if (inpt[0].equals("mark")) {

                    if (!store.get(i).getStatusIcon().equals("X")) {
                        store.get(i).markAsDone();
                    }
                    StringBuilder local_output = new StringBuilder();
                    local_output.append("____________________________________________________________\n");
                    local_output.append("Nice! I've marked this task as done:\n");
                    local_output.append("[")
                            .append(store.get(i).getStatusIcon())
                            .append("] ")
                            .append(store.get(i).getDescription()).append('\n');
                    local_output.append("____________________________________________________________\n");
                    System.out.println(local_output);

                }
                else {
                    if (store.get(i).getStatusIcon().equals("X")) {
                        store.get(i).markAsUndone();
                    }
                    StringBuilder local_output = new StringBuilder();
                    local_output.append("____________________________________________________________\n");
                    local_output.append("OK, I've marked this task as not done yet:\n");
                    local_output.append("[")
                                    .append(store.get(i).getStatusIcon())
                                    .append("] ")
                                    .append(store.get(i).getDescription()).append('\n');

                    local_output.append("____________________________________________________________\n");
                    System.out.println(local_output);
                }
            }
            else {

                if (inpt[0].equals("list")) {
                    StringBuilder local_output = new StringBuilder();
                    int n = store.size();

                    local_output.append("____________________________________________________________\n");
                    for (int i = 0; i < n; i++) {
                        local_output.append(i + 1).append(".").append("[").append(store.get(i).getStatusIcon()).append("] ").append(store.get(i).getDescription()).append('\n');
                    }
                    local_output.append("____________________________________________________________\n");
                    System.out.println(local_output);
                } else {
                    Task task = new Task(inp);
                    store.add(task);

                    StringBuilder output = new StringBuilder();

                    output.append("____________________________________________________________\n");
                    output.append("added: ").append(task.getDescription()).append('\n');
                    output.append("____________________________________________________________\n");
                    System.out.println(output);

                }
            }
            inp = sc.nextLine().trim();
            inpt = inp.split(" ");

        }

        System.out.println(ui.getExit());
    }
    public static void main(String[] args) {
        new Geni().run();
    }
}
