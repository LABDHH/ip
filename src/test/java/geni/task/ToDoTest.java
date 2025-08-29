package geni.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testGetStatusIcon() {
        Todo todo1 = new Todo("Finish assignment");
        assertEquals(" ", todo1.getStatusIcon());
        // You can mark done and test again
        todo1.markAsDone();
        assertEquals("X", todo1.getStatusIcon());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Finish assignment");
        todo.markAsDone();
        String expected = "T | 1 | Finish assignment";
        assertEquals(expected, todo.toSaveFormat());
    }
}
