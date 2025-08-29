public class UnmarkCommand extends Command {
    private String arguments;
    
    /* Constructor for UnmarkCommand class 
    */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim()) - 1;
            Task temp = taskList.tasks.get(taskNumber);

            temp.markAsNotDone();
            ui.showMessage(String.format("OK, I've marked this task as not done yet:\n%s%n", temp));
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }
}
