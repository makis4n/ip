package lebron.command;

import lebron.task.Task;
import lebron.task.TaskList;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.exception.LeBronException;

public class MarkCommand extends Command {
    private String arguments;
    
    public MarkCommand(String arguments) {
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
            Task temp = taskList.getTasks().get(taskNumber);

            temp.markAsDone();
            ui.showMessage(String.format("Nice! I've marked this task as done:\n%s%n", temp));
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException("Error: Task number out of range.");
        } catch (NumberFormatException e) {
            throw new LeBronException("Error: Please enter a valid task number.");
        }
    }
}
