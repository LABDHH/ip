package geni.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import geni.exception.GeniException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String fromStr, String toStr) throws GeniException {
        super(description);

        try {
            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(fromStr.trim(), inputFmt);
            this.to = LocalDateTime.parse(toStr.trim(), inputFmt);

        } catch (DateTimeParseException e) {
            throw new GeniException("Invalid date-time format! Please use format: yyyy-MM-dd HHmm");
        }


    }

    @Override
    public String toString() {
        DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[E]" + super.toString() + " (from: "
                + from.format(outputFmt) + " to: " + to.format(outputFmt) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter saveFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (super.getStatusIcon().equals("X") ? "1" : "0")
                + " | " + super.getDescription() + " | "
                + from.format(saveFmt) + " - " + to.format(saveFmt);
    }
}