package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to unmark a task as not done in the task list.
 * When executed, it updates the specified task's status and displays a confirmation message.
 */
public class UnmarkCommand extends Command {
    private String arguments;

    /* Constructor for UnmarkCommand class
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim()) - 1;
            Task temp = taskList.getTasks().get(taskNumber);

            temp.markAsNotDone();
            ui.showMessage(String.format("OK, I've marked this task as not done yet:\n%s%n", temp));
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }
}
