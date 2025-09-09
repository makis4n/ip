package lebron.main;

import lebron.command.Command;
import lebron.exception.LeBronException;
import lebron.task.TaskList;

/**
 * The main class for the LeBron application.
 * It initializes the application components and starts the command loop.
 */
public class LeBron {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructs a LeBron application with the specified file path for storage.
     * Initializes the UI, storage, and task list components.
     * If loading tasks from storage fails, initializes an empty task list.
     *
     * @param filePath The file path for storing tasks.
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
    public LeBron() {
        this("data/tasks.txt");
    }

    /**
     * The main method to start the LeBron application.
     */
    public static void main(String[] args) {
        new LeBron("data/tasks.txt").run();
    }

    /**
     * Starts the command loop for the LeBron application.
     * Displays a welcome message and processes user commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand); // Parse the command or throw exception
                String response = c.execute(tasks, ui, storage);
                ui.showMessage(response);
                isExit = c.isExit();
            } catch (LeBronException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (LeBronException e) {
            return e.getMessage();
        }
    }
}

