package lebron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    
    /* Constructor for Deadline class 
    */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    
    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
