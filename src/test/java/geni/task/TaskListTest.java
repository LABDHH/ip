package geni.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Read book");
        tasks.add(todo);
        assertEquals(1, tasks.size());
        assertEquals(todo, tasks.get(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Read book");
        tasks.add(todo);
        tasks.delete(0);
        assertEquals(0, tasks.size());
    }
}
