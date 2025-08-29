package geni.task;
import geni.exception.GeniException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a task that occurs over a specific time interval.
 * Stores the description, start time, and end time of the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * Creates an Event task.
     *
     * @param description task description
     * @param fromStr     start time in format "yyyy-MM-dd HHmm"
     * @param toStr       end time in format "yyyy-MM-dd HHmm"
     * @throws GeniException if the date-time format is invalid
     */
    public Event(String description, String fromStr, String toStr) throws GeniException{
        super(description);

        try {
            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(fromStr.trim(), inputFmt);
            this.to = LocalDateTime.parse(toStr.trim(), inputFmt);

        }
        catch (DateTimeParseException e) {
            throw new GeniException("Invalid date-time format! Please use format: yyyy-MM-dd HHmm");
        }


    }
    /**
     * Returns a string representation of the event for display.
     *
     * @return formatted event string
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[E]" + super.toString() + " (from: "
                + from.format(outputFmt) + " to: " + to.format(outputFmt) + ")";
    }
    /**
     * Returns a string representation suitable for saving to a file.
     *
     * @return formatted save string
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter saveFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (super.getStatusIcon().equals("X") ? "1" : "0")
                + " | " + super.getDescription() + " | "
                + from.format(saveFmt) + " - " + to.format(saveFmt);
    }
}