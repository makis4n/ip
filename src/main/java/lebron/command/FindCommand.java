package lebron.command;

import lebron.task.*;
import lebron.main.Storage;
import lebron.main.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = arguments.trim();
        if (keyword.isEmpty()) {
            ui.showMessage("The keyword for find cannot be empty.");
            return;
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
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage(foundTasks.toString());
        }
    }
}
