abstract class Command {
    /** Returns true if this command signals the application to exit.
     */
    abstract boolean isExit();
    
    /** Executes the command, performing its specific action on the given task list,
     * user interface, and storage.
     *
     * @param taskList The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     * @throws LeBronException If an error occurs during command execution.
     */
    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException;
}
