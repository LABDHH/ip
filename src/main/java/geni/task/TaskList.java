package geni.task;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void add(Task task) {
        tasks.add(task);
    }
    public Task get(int index) {
        return tasks.get(index);
    }
    public Task delete(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                results.add(t);
            }
        }
        return results;
    }

}
