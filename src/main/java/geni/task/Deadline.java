package geni.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import geni.exception.GeniException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String time) throws GeniException {
        super(description);

        try {
            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.by = LocalDateTime.parse(time.trim(), inputFmt);

        } catch (DateTimeParseException e) {
            throw new GeniException("Invalid date-time format! Please use format: yyyy-MM-dd HHmm");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(outputFmt) + ")";
    }

    @Override
    public String toSaveFormat() {

        DateTimeFormatter saveFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (super.getStatusIcon().equals("X") ? "1" : "0")
                + " | " + super.getDescription() + " | " + by.format(saveFmt);
    }
}