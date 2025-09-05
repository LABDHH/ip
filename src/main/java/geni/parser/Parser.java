package geni.parser;

import geni.exception.GeniException;
import java.util.ArrayList;
import geni.ui.UI;
import geni.storage.Storage;
import geni.task.Deadline;
import geni.task.Event;
import geni.task.Task;
import geni.task.TaskList;
import geni.task.Todo;


/**
 * Parses user input and executes commands.
 * Supports adding, deleting, listing, and updating tasks.
 */
public class Parser {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a {@code Parser}.
     *
     * @param ui      UI for displaying messages
     * @param storage storage handler for saving tasks
     * @param tasks   task list to operate on
     */
    public Parser(UI ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }
    /**
     * Parses and executes a command string.
     *
     * @param inp raw user input
     * @throws GeniException if input is invalid or exit is requested
     */
    public void parseAndExecute(String inp) throws GeniException {
        inp = inp.trim();
        String[] inpt = splitted(inp, " ");
        String command = inpt[0];

        if (isExitCommand(command)) {
            throw new GeniException("exit");
        }

        if (inp.isEmpty()) {
            throw new GeniException("Invalid input, type something.");
        }

        if (isChangingStatus(command)) {
            handleChangeStatus(inpt);
        } else if (command.equals("list")) {
            ui.printList(tasks);
        } else if (command.equals("todo")) {
            handleAddTodo(inp);
        } else if (command.equals("deadline")) {
            handleAddDeadline(inp);
        } else if (command.equals("event")) {
            handleAddEvent(inp);
        } else if (command.equals("delete")) {
            handleDelete(inpt);
        } else if (command.equals("find")) {
            handleFind(inp);
        } else {
            throw new GeniException("Sorry, I don’t know what \"" + command + "\" means.");
        }
    }
    /**
     * Handles marking or unmarking a task.
     *
     * @param inpt user input split into tokens
     * @throws GeniException if task number is invalid
     */
    private void handleChangeStatus(String[] inpt) throws GeniException {
        if (inpt.length < 2) {
            throw new GeniException("Please provide a task number to " + inpt[0] + ".");
        }
        int i = Integer.parseInt(inpt[1]) - 1;
        if (i < 0 || i >= tasks.size()) {
            throw new GeniException("Please enter a valid task number.");
        }

        Task task = tasks.get(i);
        if (inpt[0].equals("mark")) {
            if (!task.getStatusIcon().equals("X")) {
                task.markAsDone();
            }
            ui.printMark(task, true);
            storage.saveMarkReplace(task, i);
        } else {
            if (task.getStatusIcon().equals("X")) {
                task.markAsUndone();
            }
            ui.printMark(task, false);
            storage.saveMarkReplace(task, i);
        }
    }

    /**
     * Handles adding a {@code Todo} task.
     *
     * @param inp full input string
     * @throws GeniException if description is missing
     */
    private void handleAddTodo(String inp) throws GeniException {
        if (inp.length() <= 4) {
            throw new GeniException("A todo cannot be empty! Please add a description.");
        }
        Task task = new Todo(inp.substring(5).trim());
        tasks.add(task);
        storage.saveAdd(task);
        ui.printAdded(task, tasks.size());
    }
    /**
     * Handles adding a {@code Deadline} task.
     *
     * @param inp full input string
     * @throws GeniException if format is invalid
     */
    private void handleAddDeadline(String inp) throws GeniException {
        String local_inp = inp.substring(9);
        String[] local_1 = local_inp.split("/by");
        if (local_1.length < 2) {
            throw new GeniException("Incorrect entry, enter both description of deadline and time it is due separated by /by.");
        }
        Task task = new Deadline(local_1[0].trim(), local_1[1].trim());
        tasks.add(task);
        storage.saveAdd(task);
        ui.printAdded(task, tasks.size());
    }
    /**
     * Handles adding an {@code Event} task.
     *
     * @param inp full input string
     * @throws GeniException if format is invalid
     */
    private void handleAddEvent(String inp) throws GeniException {
        String local_inp = inp.substring(6);
        String[] local_1 = local_inp.split("/from");
        if (local_1.length < 2) {
            throw new GeniException("Incorrect format, enter in format: event description /from time /to time.");
        }
        String desc = local_1[0].trim();
        String[] local_2 = local_1[1].trim().split("/to");
        if (local_2.length < 2) {
            throw new GeniException("Incorrect format, enter in format: event description /from time /to time.");
        }
        String from = local_2[0].trim();
        String to = local_2[1].trim();
        Task task = new Event(desc, from, to);
        tasks.add(task);
        storage.saveAdd(task);
        ui.printAdded(task, tasks.size());
    }

    /**
     * Handles deleting a task.
     *
     * @param inpt user input split into tokens
     * @throws GeniException if task number is invalid
     */
    private void handleDelete(String[] inpt) throws GeniException {
        if (inpt.length < 2) {
            throw new GeniException("Please provide the task number to delete.");
        }
        int x = Integer.parseInt(inpt[1]) - 1;
        if (x < 0 || x >= tasks.size()) {
            throw new GeniException("Task number out of range, cannot delete.");
        }
        Task removed = tasks.delete(x);
        storage.savedelete(removed);
        ui.printDeleted(removed, tasks.size());
    }
    /**
     * Checks if a command is an exit command.
     *
     * @param input command string
     * @return true if input is "bye"
     */
    public boolean isExitCommand(String input) {
        return "bye".equals(input);
    }

    /**
     * Splits a string by a delimiter.
     *
     * @param string   input string
     * @param splitter delimiter
     * @return array of split strings
     */
    public String[] splitted(String string, String splitter) {
        return string.split(splitter);
    }
    /**
     * Checks if a command changes task status.
     *
     * @param string command string
     * @return true if "mark" or "unmark"
     */
    public boolean isChangingStatus(String string) {
        return string.equals("mark") || string.equals("unmark");
    }
    private void handleFind(String inp) throws GeniException {
        if (inp.length() <= 5) {
            throw new GeniException("Please specify a keyword to search for.");
        }
        String keyword = inp.substring(5).trim();
        ArrayList<Task> foundTasks = tasks.find(keyword);
        ui.printFoundTasks(foundTasks);
    }
}
