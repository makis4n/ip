package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private String arguments;

    /**
     * Constructor for DeleteCommand.
     *
     * @param arguments The arguments provided with the delete command, typically the task number to delete.
     */
    public DeleteCommand(String arguments) {
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
            Task temp = taskList.getTasks().remove(taskNumber);

            ui.showMessage(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.%n",
                    temp, taskList.getTasks().size()));
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }
}
