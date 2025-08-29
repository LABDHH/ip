package geni.parser;

import geni.exception.GeniException;
import geni.storage.Storage;
import geni.task.Deadline;
import geni.task.Event;
import geni.task.Task;
import geni.task.TaskList;
import geni.task.Todo;
import geni.ui.UI;


public class Parser {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    public Parser(UI ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

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
        } else {
            throw new GeniException("Sorry, I don’t know what \"" + command + "\" means.");
        }
    }

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

    private void handleAddTodo(String inp) throws GeniException {
        if (inp.length() <= 4) {
            throw new GeniException("A todo cannot be empty! Please add a description.");
        }
        Task task = new Todo(inp.substring(5).trim());
        tasks.add(task);
        storage.saveAdd(task);
        ui.printAdded(task, tasks.size());
    }

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

    public boolean isExitCommand(String input) {
        return "bye".equals(input);
    }


    public String[] splitted(String string, String splitter) {
        return string.split(splitter);
    }

    public boolean isChangingStatus(String string) {
        return string.equals("mark") || string.equals("unmark");
    }
}