package lebron.task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    
    /* Constructor to initialize the TaskList with an existing list of tasks.
     * 
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
    /* Default constructor to initialize an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
}
