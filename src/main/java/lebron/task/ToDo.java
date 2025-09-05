package lebron.task;

/**
 * Represents a task that is a to-do item.
 */
public class ToDo extends Task {
    /**
     *  Constructor for ToDo class
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
