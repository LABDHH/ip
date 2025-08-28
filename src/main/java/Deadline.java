public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + super.getDescription() + " | " + time;
    }
}
