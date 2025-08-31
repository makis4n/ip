package lebron.main;

import lebron.task.*;
import lebron.command.*;
import lebron.exception.LeBronException;

import java.time.LocalDate;

public class Parser {
    /* Reads tasks from the data file for populating the task list.
     *
     * @param line A line from the data file representing a task.
     * @return A Task object created from the line; null if the line is invalid.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in data file.");
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1"); // "1" means done, "0" means not done
        String description = parts[2];

        switch (type) {
            case "T": {
                Task t = new ToDo(description);
                if (isDone) {
                    t.markAsDone();
                }
                return t;
            }
            case "D": {
                Task d = new Deadline(description, LocalDate.parse(parts[3]));
                if (isDone) {
                    d.markAsDone();
                }
                return d;
            }
            case "E": {
                Task e = new Event(description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                if (isDone) {
                    e.markAsDone();
                }
                return e;
            }
        }

        return null;
    }
    
    /* Parses user input to create the appropriate Command object.
     * 
     * @param fullCommand The full command input by the user.
     * @return A Command object corresponding to the user's command.
     * @throws LeBronException If the command is unrecognized or invalid.
     */
    public static Command parse(String fullCommand) throws LeBronException {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (commandWord) {
        case "list": 
            return new ListCommand(); 
        case "bye": 
            return new ExitCommand();
        case "mark": 
            return new MarkCommand(arguments);
        case "unmark": 
            return new UnmarkCommand(arguments);
        case "delete": 
            return new DeleteCommand(arguments);
        case "todo", "event", "deadline": 
            return new AddCommand(commandWord, arguments);
        case "find":
            return new FindCommand(arguments);
        default: 
            throw new LeBronException("I'm sorry, but I don't know what that means.");
        }
    }
}
