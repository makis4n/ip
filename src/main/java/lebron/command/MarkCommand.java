package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 * When executed, it updates the specified task's status and displays a confirmation message.
 */
public class MarkCommand extends Command {
    private final String arguments;

    /**
     * Constructor for MarkCommand.
     * @param arguments The arguments provided with the mark command, typically the task number to mark as done.
     */
    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task taskToMark = getTaskIfValid(taskList);
        taskToMark.markAsDone();
        return markTaskMessage(taskToMark);
    }

    /**
     * Parses and validates the task number from the command arguments.
     * @param taskList The current task list.
     * @return The task to be marked as done.
     * @throws LeBronException If the task number is invalid or out of range.
     */
    private Task getTaskIfValid(TaskList taskList) throws LeBronException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim()) - 1;
            return taskList.getTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }
    /**
     * Formats the message to be displayed after a task is marked as done.
     * @param task The task that was marked as done.
     * @return A formatted string message.
     */
    private String markTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s%n", task);
    }
}
