package geni.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, get, delete tasks, and check the size of the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Creates a TaskList with the given list of tasks.
     *
     * @param tasks initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index of the task
     * @return task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }
    /**
     * Removes and returns the task at the specified index.
     *
     * @param index index of the task to remove
     * @return the removed task
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }
    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */

    public int size() {
        return tasks.size();
    }

}
