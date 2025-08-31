package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.TaskList;

public abstract class Command {
    /**
     * Returns true if this command signals the application to exit.
     */
    public abstract boolean isExit();

    /**
     * Executes the command, performing its specific action on the given task list,
     * user interface, and storage.
     *
     * @param taskList The current list of tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage system for saving tasks.
     * @throws LeBronException If an error occurs during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException;
}
