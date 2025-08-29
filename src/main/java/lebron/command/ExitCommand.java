package lebron.command;

import lebron.task.Task;
import lebron.task.TaskList;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.exception.LeBronException;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        for (Task task : taskList.getTasks()) {
            storage.writeTaskToFile(task);
        }
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
