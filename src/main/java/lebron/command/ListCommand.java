package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, it displays all tasks with their corresponding numbers.
 */
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        validateTaskList(taskList);
        return getTaskListString(taskList);
    }
    /**
     * Validates that the task list is not empty.
     *
     * @param taskList The task list to validate.
     * @throws LeBronException If the task list is empty.
     */
    private void validateTaskList(TaskList taskList) throws LeBronException {
        if (taskList.isEmpty()) {
            throw new LeBronException("No tasks found.");
        }
    }
    /**
     * Formats the task list into a string for display.
     *
     * @param taskList The task list to format.
     * @return A formatted string of all tasks in the task list.
     */
    private String getTaskListString(TaskList taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task temp = taskList.getTasks().get(i);
            sb.append(String.format("%d. %s\n", i + 1, temp));
        }
        return sb.toString();
    }
}
