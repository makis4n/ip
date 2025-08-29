package lebron.main;

import lebron.task.TaskList;
import lebron.exception.LeBronException;
import lebron.command.Command;

public class LeBron {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    /* Constructor for LeBron class.
     * 
     * @param filePath The path to the data file for storing tasks.
     */
    public LeBron(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LeBronException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    /* Runs the main command loop for the LeBron application.
     * Continuously reads user commands, parses them, and executes the corresponding actions
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LeBronException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    
    /* The main method to start the LeBron application.
     */
    public static void main(String[] args) {
        new LeBron("data/tasks.txt").run();
    }
}

