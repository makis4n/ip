public class DeleteCommand extends Command {
    private String arguments;
    
    /**
     * Constructor for DeleteCommand.
     * @param arguments The arguments provided with the delete command, typically the task number to delete.
     */
    public DeleteCommand(String arguments) {
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
            Task temp = taskList.tasks.remove(taskNumber);

            ui.showMessage(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.%n",
                    temp, taskList.tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }
}
