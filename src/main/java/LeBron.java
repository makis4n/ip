import java.util.Scanner;
import java.util.ArrayList;

public class LeBron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm LeBron.\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task temp = taskList.get(i);
                    System.out.printf("%d. %s%n", i + 1, temp);
                }
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                    Task temp = taskList.get(taskNumber);

                    temp.markAsDone();
                    System.out.printf("Nice! I've marked this task as done:\n%s%n", temp);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Task number out of range.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid task number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    Task temp = taskList.get(taskNumber);

                    temp.markAsNotDone();
                    System.out.printf("OK, I've marked this task as not done yet:\n%s%n", temp);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Task number out of range.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid task number.");
                }
            } else if (userInput.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    Task temp = taskList.remove(taskNumber);

                    System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.%n",
                            temp, taskList.size());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Task number out of range.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid task number.");
                }
            } else if (userInput.startsWith("todo ")) {
                try {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new LeBronException("The description of a todo cannot be empty.");
                    }
                    Task t = new ToDo(description);

                    taskList.add(t);
                    System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                            t, taskList.size());
                } catch (LeBronException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("deadline ")) {
                try {
                    String description = userInput.substring(9, userInput.indexOf("/by") - 1).trim();
                    String by = userInput.substring(userInput.indexOf("/by") + 4).trim();
                    if (description.isEmpty()) {
                        throw new LeBronException("The description of a deadline cannot be empty.");
                    } else if (by.isEmpty()) {
                        throw new LeBronException("The deadline cannot be empty.");
                    }
                    Task t = new Deadline(description, by);

                    taskList.add(t);
                    System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                            t, taskList.size());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Error: Wrong format. Use: 'deadline <task> /by <date>'.");
                } catch (LeBronException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("event ")) {
                try {
                    String description = userInput.substring(6, userInput.indexOf("/from") - 1).trim();
                    String start = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1).trim();
                    String end = userInput.substring(userInput.indexOf("/to") + 4).trim();
                    if (description.isEmpty()) {
                        throw new LeBronException("The description of an event cannot be empty.");
                    } else if (start.isEmpty()) {
                        throw new LeBronException("The start time of an event cannot be empty.");
                    } else if (end.isEmpty()) {
                        throw new LeBronException("The end time of an event cannot be empty.");
                    }
                    Task t = new Event(description, start, end);

                    taskList.add(t);
                    System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                            t, taskList.size());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Error: Wrong format. Use: 'event <task> /from <start time> /to <end time>'.");
                } catch (LeBronException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new LeBronException("I'm sorry, but I don't know what that means.");
                } catch (LeBronException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
