package lebron.command;

import lebron.task.*;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.exception.LeBronException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String commandWord;
    private String arguments;
    
    /* Constructor for AddCommand class.
     * 
     * @param arguments The arguments provided with the add command.
     */
    public AddCommand(String commandWord, String arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    /* Determines the type of task to be added based on the command arguments.
     * 
     * @return A string representing the task type.
     */
    public String taskType() {
        if (commandWord.equalsIgnoreCase("todo")) {
            return "T";
        } else if (commandWord.equalsIgnoreCase("deadline")) {
            return "D";
        } else if (commandWord.equalsIgnoreCase("event")) {
            return "E";
        } else {
            return "";
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        switch (taskType()) {
        case "T":
            String toDoDescription = arguments.trim();
            if (toDoDescription.isEmpty()) {
                throw new LeBronException("The description of a todo cannot be empty.");
            }
            Task toDoTask = new ToDo(toDoDescription);

            taskList.getTasks().add(toDoTask);
            ui.showMessage(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                            toDoTask, taskList.getTasks().size()));
            break;
        case "D":
            try {
                String deadlineDescription = arguments.substring(0, arguments.indexOf("/by")).trim();
                String by = arguments.substring(arguments.indexOf("/by") + 4).trim();
                
                if (deadlineDescription.isEmpty()) {
                    throw new LeBronException("The description of a deadline cannot be empty.");
                } else if (by.isEmpty()) {
                    throw new LeBronException("The deadline cannot be empty.");
                }

                LocalDate date = LocalDate.parse(by);
                Task deadlineTask = new Deadline(deadlineDescription, date);

                taskList.getTasks().add(deadlineTask);
                ui.showMessage(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                            deadlineTask, taskList.getTasks().size()));
            } catch (StringIndexOutOfBoundsException e) {
                throw new LeBronException("Error: Wrong format. Use: 'deadline <task> /by <date>'.");
            } catch (DateTimeParseException e) {
                throw new LeBronException("Error: Please enter the date in YYYY-MM-DD format.");
            }
            break;
        case "E":
            try {
                String eventDescription = arguments.substring(0, arguments.indexOf("/from")).trim();
                String start = arguments.substring(arguments.indexOf("/from") + 6, arguments.indexOf("/to") - 1).trim();
                String end = arguments.substring(arguments.indexOf("/to") + 4).trim();

                if (eventDescription.isEmpty()) {
                    throw new LeBronException("The description of an event cannot be empty.");
                } else if (start.isEmpty()) {
                    throw new LeBronException("The start time of an event cannot be empty.");
                } else if (end.isEmpty()) {
                    throw new LeBronException("The end time of an event cannot be empty.");
                }

                LocalDate s = LocalDate.parse(start);
                LocalDate e = LocalDate.parse(end);
                Task eventTask = new Event(eventDescription, s, e);
                
                taskList.getTasks().add(eventTask);
                ui.showMessage(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                            eventTask, taskList.getTasks().size()));
            } catch (StringIndexOutOfBoundsException e) {
                throw new LeBronException(
                        "Error: Wrong format. Use: 'event <task> /from <start time> /to <end time>'.");
            } catch (DateTimeParseException e) {
                throw new LeBronException("Error: Please enter the date in YYYY-MM-DD format.");
            }
            break;
        default:
            throw new LeBronException("I'm sorry, but I don't know what that means.");
        }
    }
}
