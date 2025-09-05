package lebron.command;

import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to find and list tasks that contain a specific keyword in their description.
 */
public class FindCommand extends Command {
    private String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = arguments.trim();
        if (keyword.isEmpty()) {
            return "The keyword for find cannot be empty.";
        }

        StringBuilder foundTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task task = taskList.getTasks().get(i);
            if (task.getDescription().contains(keyword)) {
                count++;
                foundTasks.append(String.format("%d. %s\n", i + 1, task));
            }
        }

        if (count == 0) {
            return "No matching tasks found.";
        } else {
            return foundTasks.toString();
        }
    }
}
