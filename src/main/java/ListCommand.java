public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        if (taskList.tasks.isEmpty()) {
            ui.showMessage("No tasks found.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < taskList.tasks.size(); i++) {
                Task temp = taskList.tasks.get(i);
                ui.showMessage(String.format("%d. %s", i + 1, temp));
            }
        }
    }
}
