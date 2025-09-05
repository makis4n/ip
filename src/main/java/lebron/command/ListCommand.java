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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        if (taskList.getTasks().isEmpty()) {
            ui.showMessage("No tasks found.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                Task temp = taskList.getTasks().get(i);
                ui.showMessage(String.format("%d. %s", i + 1, temp));
            }
        }
    }
}
