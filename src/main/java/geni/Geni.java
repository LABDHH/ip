package geni;
import geni.ui.UI;
import geni.storage.Storage;
import geni.task.TaskList;
import geni.parser.Parser;
import geni.exception.GeniException;
import java.util.Scanner;
/**
 * Entry point of the Geni application.
 * Initializes components and runs the main loop.
 */
public class Geni {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a {@code Geni} instance.
     *
     * @param filePath path to the data file
     */
    public Geni(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(ui, storage, tasks);
    }
    /**
     * Starts the main execution loop of the Geni application.
     * <p>
     * Continuously reads user input, parses it, and executes commands until
     * the user chooses to exit. Handles errors gracefully by printing
     * appropriate error messages.
     * </p>
     */
    public void run() {
        System.out.print(ui.getGreeting());
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String inp = sc.nextLine();
                parser.parseAndExecute(inp);
            } catch (GeniException e) {
                if (e.getMessage().equals("exit")) {
                    break;
                }
                ui.line();
                System.out.println("OOPS! " + e.getMessage());
                ui.line();
            }
        }
        System.out.println(ui.getExit());
    }
    /**
     * The main entry point of the Geni application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Geni("./geni.txt").run();
    }
}