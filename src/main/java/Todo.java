public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toSaveFormat() {
        return "T | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + super.getDescription();
    }

}
