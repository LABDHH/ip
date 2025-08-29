package geni;

import java.util.Scanner;

import geni.exception.GeniException;
import geni.parser.Parser;
import geni.storage.Storage;
import geni.task.TaskList;
import geni.ui.UI;

public class Geni {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Geni(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(ui, storage, tasks);
    }

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

    public static void main(String[] args) {
        new Geni("./geni.txt").run();
    }
}