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
    private final String arguments;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        int taskNumber = getTaskNumberIfValid();
        Task temp = taskList.removeTask(taskNumber);

        return removeTaskMessage(temp, taskList);
    }

    /**
     * Parses and validates the task number from the command arguments.
     *
     * @return The zero-based index of the task to be deleted.
     * @throws LeBronException If the task number is invalid or out of range.
     */
    private int getTaskNumberIfValid() throws LeBronException {
        try {
            return Integer.parseInt(arguments.trim()) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }

    /**
     * Formats the message to be displayed after a task is removed.
     *
     * @param task The task that was removed.
     * @param taskList The current task list.
     * @return A formatted string message.
     */
    private String removeTaskMessage(Task task, TaskList taskList) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.%n",
                task, taskList.getSize());
    }
}
