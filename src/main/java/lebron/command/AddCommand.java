package lebron.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.ToDo;

/**
 * Represents a command to add a task (ToDo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private final String commandWord;
    private final String arguments;

    /**
     * Constructor for AddCommand class.
     *
     * @param arguments The arguments provided with the add command.
     */
    public AddCommand(String commandWord, String arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    /**
     * Determines the type of task to be added based on the command arguments.
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = createTask();
        taskList.addTask(task);
        return formatSuccessMessage(task, taskList.getSize());
    }

    /**
     * Creates a task based on the command arguments.
     *
     * @return The created Task object.
     * @throws LeBronException If there is an error in task creation.
     */
    private Task createTask() throws LeBronException {
        switch (taskType()) {
        case "T":
            return createToDoTask();
        case "D":
            return createDeadlineTask();
        case "E":
            return createEventTask();
        default:
            throw new LeBronException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Creates a ToDo task.
     *
     * @return The created ToDo task.
     * @throws LeBronException If the description is empty.
     */
    private Task createToDoTask() throws LeBronException {
        String description = arguments.trim();
        validateNotEmpty(description, "The description of a todo cannot be empty.");
        return new ToDo(description);
    }

    /**
     * Creates a Deadline task.
     *
     * @return The created Deadline task.
     * @throws LeBronException If there is an error in parsing the date or if the description is empty.
     */
    private Task createDeadlineTask() throws LeBronException {
        String deadlineDescription = arguments.substring(0, arguments.indexOf("/by")).trim();
        String by = arguments.substring(arguments.indexOf("/by") + 4).trim();
        validateNotEmpty(deadlineDescription, "The description of a deadline cannot be empty.");
        validateNotEmpty(by, "The deadline cannot be empty.");

        try {
            LocalDate date = LocalDate.parse(by);
            return new Deadline(deadlineDescription, date);
        } catch (DateTimeParseException e) {
            throw new LeBronException("Error: Please enter the date in YYYY-MM-DD format.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new LeBronException("Error: Wrong format. Use: 'event <task> /from <start time> /to <end time>'.");
        }
    }

    /**
     * Creates an Event task.
     *
     * @return The created Event task.
     * @throws LeBronException If there is an error in parsing the dates or if any field is empty.
     */
    private Task createEventTask() throws LeBronException {
        String eventDescription = arguments.substring(0, arguments.indexOf("/from")).trim();
        String start = arguments.substring(arguments.indexOf("/from") + 6, arguments.indexOf("/to") - 1).trim();
        String end = arguments.substring(arguments.indexOf("/to") + 4).trim();
        validateNotEmpty(eventDescription, "The description of an event cannot be empty.");
        validateNotEmpty(start, "The start time of an event cannot be empty.");
        validateNotEmpty(end, "The end time of an event cannot be empty.");

        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return new Event(eventDescription, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new LeBronException("Error: Please enter the date in YYYY-MM-DD format.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new LeBronException("Error: Wrong format. Use: 'event <task> /from <start time> /to <end time>'.");
        }
    }

    /**
     * Validates that a string is not empty.
     *
     * @param value        The string to validate.
     * @param errorMessage The error message to throw if the string is empty.
     * @throws LeBronException If the string is empty.
     */
    private void validateNotEmpty(String value, String errorMessage) throws LeBronException {
        if (value.isEmpty()) {
            throw new LeBronException(errorMessage);
        }
    }

    /**
     * Formats the success message after adding a task.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks in the list.
     * @return A formatted success message.
     */
    private String formatSuccessMessage(Task task, int totalTasks) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                task, totalTasks);
    }
}
