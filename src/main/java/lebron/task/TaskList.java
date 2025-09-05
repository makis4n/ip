package lebron.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing for addition, removal, and retrieval of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor to initialize the TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor to initialize an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
