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
                    ui.printMark(store.get(i), true);

                }
                else {
                    if (store.get(i).getStatusIcon().equals("X")) {
                        store.get(i).markAsUndone();
                    }
                    ui.printMark(store.get(i), false);
                }
            }

            else if (inpt[0].equals("list")) {
                    ui.printList(store);
                }
            else if (inpt[0].equals("todo")) {
                Task task = new Todo(inp.substring(5).trim());
                store.add(task);
                ui.printAdded(task, store.size());
            }

            else if (inpt[0].equals("deadline")) {
                String local_inp = inp.substring(9);
                String[] local_1 = local_inp.split("/by");
                Task task = new Deadline(local_1[0].trim(),local_1[1].trim());
                store.add(task);
                ui.printAdded(task, store.size());

            }
            else if (inpt[0].equals("event")) {
                String local_inp = inp.substring(6);
                String[] local_1 = local_inp.split("/from");
                String desc = local_1[0].trim();
                String[] local_2 = local_1[1].trim().split("/to");
                String from = local_2[0].trim();
                String to = local_2[1].trim();
                Task task = new Event(desc, from, to);
                store.add(task);
                ui.printAdded(task, store.size());

            }

            else {
                    Task task = new Task(inp);
                    store.add(task);

                    ui.printAdded(task, store.size());

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
