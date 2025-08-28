
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> load() {
        ArrayList<Task> storeTask = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String inp = sc.nextLine().trim();
                if (inp.isEmpty()) continue;

                String[] parts = inp.split("\\|");
                for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();

                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (type) {
                    case "T":
                        Task todo = new Todo(parts[2]);
                        if (isDone) todo.markAsDone();
                        storeTask.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(parts[2], parts[3]);
                        if (isDone) deadline.markAsDone();
                        storeTask.add(deadline);
                        break;
                    case "E":
                        String[] timeline = parts[3].split(" - ");
                        Task event = new Event(parts[2], timeline[0], timeline[1]);
                        if (isDone) event.markAsDone();
                        storeTask.add(event);
                        break;
                    default:
                        continue;
                }

            }
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (GeniException e) {
            System.out.println("please enter valid input");
        }
        return storeTask;
    }
    public void savedelete(Task taskToDelete) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(taskToDelete.toSaveFormat())) {
                    lines.remove(i);
                    break;
                }
            }
            Files.write(Paths.get(filePath), lines);
        }
        catch (IOException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }

    public void saveMarkReplace(Task newTask, int index) {



        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            if (index >= 0 && index < lines.size()) {
                lines.set(index, newTask.toSaveFormat());
            }

            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }

    }
    public void saveAdd(Task task) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(filePath, true));
            pw.println(task.toSaveFormat());
            pw.close();
        }
        catch (IOException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }


}
