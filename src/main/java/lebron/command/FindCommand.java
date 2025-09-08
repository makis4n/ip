package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to find and list tasks that contain a specific keyword in their description.
 */
public class FindCommand extends Command {
    private final String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        String keyword = arguments.trim();
        validateKeyword(keyword);
        return getFoundTasksString(taskList, keyword);
    }

    /**
     * Validates that the keyword is not empty.
     *
     * @param keyword The keyword to validate.
     * @throws LeBronException If the keyword is empty.
     */
    private void validateKeyword(String keyword) throws LeBronException {
        if (keyword.isEmpty()) {
            throw new LeBronException("The keyword for find cannot be empty.");
        }
    }
    /**
     * Searches for tasks containing the keyword and formats the result.
     *
     * @param taskList The task list to search.
     * @param keyword The keyword to search for.
     * @return A formatted string of found tasks or a message indicating no matches.
     */
    private String getFoundTasksString(TaskList taskList, String keyword) {
        StringBuilder foundTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                count++;
                foundTasks.append(String.format("%d. %s\n", i + 1, task));
            }
        }
        if (count == 0) {
            return "No matching tasks found.";
        }
        return foundTasks.toString();
    }
}
