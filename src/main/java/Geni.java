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
        ArrayList<Task> store = new ArrayList<>();

        while (!inp.equals("bye")) {

            if (inp.equals("list")) {
                StringBuilder local_output = new StringBuilder();
                int n = store.size();
                local_output.append("____________________________________________________________\n");
                for (int i = 0; i < n; i++) {
                    local_output.append(i + 1).append(". ").append(store.get(i).getDescription()).append('\n');
                }
                local_output.append("____________________________________________________________\n");
                System.out.println(local_output);
            }
            else {
                Task task = new Task(inp);
                store.add(task);

                StringBuilder output = new StringBuilder();

                output.append("____________________________________________________________\n");
                output.append("added: ").append(task.getDescription()).append('\n');
                output.append("____________________________________________________________\n");
                System.out.println(output);

            }
            inp = sc.nextLine().trim();

        }

        System.out.println(ui.getExit());
    }
    public static void main(String[] args) {
        new Geni().run();
    }
}
