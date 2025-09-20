package lebron.common;

/**
 * A utility class to hold constant values used throughout the application.
 */
public final class Constants {

    // File / storage
    public static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    public static final String STORAGE_SEPARATOR = "\\|"; // for splitting
    public static final String STORAGE_SEPARATOR_WRITE = "|"; // for writing
    public static final String DONE_FLAG = "1";
    public static final String NOT_DONE_FLAG = "0";

    // Task type codes
    public static final String TASK_TYPE_T = "T";
    public static final String TASK_TYPE_D = "D";
    public static final String TASK_TYPE_E = "E";

    // Command words
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    // Argument delimiters
    public static final String BY_DELIMITER = "/by";
    public static final String FROM_DELIMITER = "/from";
    public static final String TO_DELIMITER = "/to";

    // Messages used by AddCommand
    public static final String UNKNOWN_COMMAND_ERROR = "I'm sorry, but I don't know what that means.";
    public static final String TODO_EMPTY_ERROR = "The description of a todo cannot be empty.";
    public static final String DEADLINE_EMPTY_ERROR = "The description of a deadline cannot be empty.";
    public static final String DEADLINE_BY_EMPTY_ERROR = "The deadline cannot be empty.";
    public static final String EVENT_EMPTY_ERROR = "The description of an event cannot be empty.";
    public static final String EVENT_START_EMPTY_ERROR = "The start time of an event cannot be empty.";
    public static final String EVENT_END_EMPTY_ERROR = "The end time of an event cannot be empty.";
    public static final String DATE_FORMAT_ERROR = "Error: Please enter the date in YYYY-MM-DD format.";
    public static final String EVENT_FORMAT_ERROR =
            "Error: Wrong format. Use: 'event <task> /from " + "<start time> /to <end time>'.";

    // Success / formatting strings
    public static final String SUCCESS_MESSAGE_FORMAT =
            "Got it. I've added this task:\n" + "%s\nNow you have %d tasks in the list.%n";

    // DeleteCommand messages
    public static final String INVALID_TASK_NUMBER_ERROR = "Error: Please enter valid task numbers.";
    public static final String TASK_NUMBER_OUT_OF_RANGE_ERROR = "Error: One or more task numbers out of range.";
    public static final String REMOVE_TASKS_MESSAGE_FORMAT =
            "Noted. I've removed the tasks:\n" + "%s\nNow you have %d tasks in the list.%n";

    // Exit
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // FindCommand messages
    public static final String EMPTY_KEYWORD_ERROR = "The keyword for find cannot be empty.";
    public static final String NO_MATCHING_TASKS_MESSAGE = "No matching tasks found.";
    public static final String MATCHING_TASKS_HEADER = "Here are the matching tasks in your list:\n";

    // ListCommand messages
    public static final String NO_TASKS_ERROR = "No tasks found.";
    public static final String TASK_LIST_HEADER = "Here are the tasks in your list:\n";

    // Mark/Unmark messages
    public static final String TASK_NUMBER_OUT_OF_RANGE_ERROR_SINGLE = "Error: Task number out of range.";
    public static final String INVALID_TASK_NUMBER_ERROR_SINGLE = "Error: Please enter a valid task number.";
    public static final String MARK_TASK_MESSAGE_FORMAT = "Nice! I've marked this task as done:\n%s%n";
    public static final String UNMARK_TASK_MESSAGE_FORMAT = "OK, I've marked this task as not done yet:\n%s%n";

    // UI messages
    public static final String UI_LOADING_ERROR = "Error loading tasks from file. Starting with an empty task list.";
    public static final String UI_WELCOME = "Hello! I'm LeBron.\nWhat can I do for you?";
    public static final String UI_INPUT_EMPTY_ERROR = "Input cannot be empty. Please enter a command.";

    // Generic errors
    public static final String ERROR_INVALID_TASK_FORMAT = "Invalid task format in data file.";
    public static final String ERROR_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means.";
    public static final String ERROR_EMPTY_FILEPATH = "File path should not be null or empty";

    // IO messages
    public static final String IO_ERROR_CREATING_FILE = "Error creating file: ";
    public static final String IO_ERROR_READING_FILE = "Error reading data file: ";
    public static final String IO_ERROR_WRITING_FILE = "Failed to write task to file: ";

    // Date / display formats
    public static final String DISPLAY_DATE_PATTERN = "MMM d yyyy";

    // Type prefixes in toString
    public static final String TYPE_PREFIX_T = "[T]";
    public static final String TYPE_PREFIX_D = "[D]";
    public static final String TYPE_PREFIX_E = "[E]";

    // Status icons
    public static final String STATUS_ICON_DONE = "X";
    public static final String STATUS_ICON_NOT_DONE = " ";
}
