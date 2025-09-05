package geni;

import java.util.Scanner;

import geni.exception.GeniException;
import geni.parser.Parser;
import geni.storage.Storage;
import geni.task.TaskList;
import geni.ui.UI;

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
                String response = parser.parseAndExecute(inp); // now returns String
                System.out.print(response);
            } catch (GeniException e) {
                if (e.getMessage().equals("exit")) {
                    System.out.println(ui.getExit());
                    break;
                }
                ui.line();
                System.out.println("OOPS! " + e.getMessage());
                ui.line();
            }
        }

    }
    public String getGreetingMessage() {
        return ui.getGreeting();
    }
    public String getResponse(String input) {
        try {
            // Change parseAndExecute to return a String instead of printing
            return parser.parseAndExecute(input);
        } catch (GeniException e) {
            if (e.getMessage().equals("exit")) {
                return ui.getExit();
            }
            return "OOPS! " + e.getMessage();
        }
    }
    /**
     * The main entry point of the Geni application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Launcher.main(args);
       // new Geni("./geni.txt").run();
    }
}
