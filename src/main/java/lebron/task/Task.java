package lebron.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /* Constructor for Task class
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /* Returns status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /* Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /* Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
