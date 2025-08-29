public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        for (Task task : taskList.tasks) {
            storage.writeTaskToFile(task);
        }
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
