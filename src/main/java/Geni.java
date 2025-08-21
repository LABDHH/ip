import java.util.ArrayList;
import java.util.Scanner;

public class Geni {
    private UI ui;

    public Geni() {
        ui = new UI();
    }
    public void run() {

        System.out.print(ui.getGreeting());
        Scanner sc = new Scanner(System.in);

        ArrayList<Task> store = new ArrayList<>();

        while (true) {
            try {
                String inp = sc.nextLine().trim();
                if (inp.equals("bye")) {
                    break;
                }
                if (inp.isEmpty()) {
                    throw new GeniException("invalid input, type something");
                }
                String[] inpt = inp.split(" ");


                if (inpt[0].equals("mark") || inpt[0].equals("unmark")) {
                    if (inpt.length < 2) {
                        throw new GeniException("Please provide a task number to " + inpt[0] + ".");

                    }
                    int i = Integer.parseInt(inpt[1]) - 1;
                    if (i < 0 || i > store.size()) {
                        throw new GeniException("please enter valid task numer");
                    }

                    if (inpt[0].equals("mark")) {

                        if (!store.get(i).getStatusIcon().equals("X")) {
                            store.get(i).markAsDone();
                        }
                        ui.printMark(store.get(i), true);

                    } else {
                        if (store.get(i).getStatusIcon().equals("X")) {
                            store.get(i).markAsUndone();
                        }
                        ui.printMark(store.get(i), false);
                    }
                } else if (inpt[0].equals("list")) {
                    ui.printList(store);
                } else if (inpt[0].equals("todo")) {
                    if (inp.length() <= 4) {
                        throw new GeniException("A todo cannot be empty! Please add a description.");
                    }
                    Task task = new Todo(inp.substring(5).trim());
                    store.add(task);
                    ui.printAdded(task, store.size());
                } else if (inpt[0].equals("deadline")) {
                    String local_inp = inp.substring(9);
                    String[] local_1 = local_inp.split("/by");
                    if (local_1.length < 2) {
                        throw new GeniException("incorect entry, enter both description of deadline and time it is due separated by /by ");
                    }
                    Task task = new Deadline(local_1[0].trim(), local_1[1].trim());
                    store.add(task);
                    ui.printAdded(task, store.size());

                } else if (inpt[0].equals("event")) {
                    String local_inp = inp.substring(6);
                    String[] local_1 = local_inp.split("/from");
                    String desc = local_1[0].trim();
                    String[] local_2 = local_1[1].trim().split("/to");
                    if (local_1.length < 2 || local_2.length < 2) {
                        throw new GeniException("incorrect format,  enter in format event description /from time /to time");
                    }
                    String from = local_2[0].trim();
                    String to = local_2[1].trim();
                    Task task = new Event(desc, from, to);
                    store.add(task);
                    ui.printAdded(task, store.size());

                } else if (inpt[0].equals("delete")) {
                    if (inpt.length < 2) {
                        throw new GeniException("Please provide the task number to delete.");
                    }
                    int x = Integer.parseInt(inpt[1]) - 1;
                    if (x < 0 || x >= store.size()) {
                        throw new GeniException("Task number out of range, cannot delete.");
                    }
                    Task removed = store.remove(x );
                    ui.printDeleted(removed,store.size());
                }
                else {
                    throw new GeniException("Sorry, I don’t know what \"" + inpt[0] + "\" means.");


                }



            }
            catch(GeniException e) {
                ui.line();
                System.out.println("OOPS! " + e.getMessage());
                ui.line();

            }


        }
        System.out.println(ui.getExit());
    }


    public static void main(String[] args) {
        new Geni().run();
    }
}
