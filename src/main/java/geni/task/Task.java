package geni.task;
/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Creates a Task with the given description.
     * Initially, the task is not done.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, otherwise a blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    /**
     * Returns the description of the task.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns a string representation of the task for display.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    /**
     * Returns a string representation suitable for saving to a file.
     * This method is meant to be overridden in subclasses.
     *
     * @return save format string
     */
    public String toSaveFormat() {
        return "";
    }

}
