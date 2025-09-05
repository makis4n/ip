package lebron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event occurring over a period of time.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for Event class
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
