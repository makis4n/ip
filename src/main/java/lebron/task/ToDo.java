package lebron.task;

public class ToDo extends Task {
    /* Constructor for ToDo class 
    */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
