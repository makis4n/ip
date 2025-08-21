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
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                Task temp = taskList.get(taskNumber);

                temp.markAsDone();
                System.out.printf("Nice! I've marked this task as done:\n%s%n", temp);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                Task temp = taskList.get(taskNumber);

                temp.markAsNotDone();
                System.out.printf("OK, I've marked this task as not done yet:\n%s%n", temp);
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                Task t = new ToDo(description);

                taskList.add(t);
                System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n", t, taskList.size());
            } else if (userInput.startsWith("deadline ")) {
                String description = userInput.substring(9, userInput.indexOf("/by") - 1);
                String by = userInput.substring(userInput.indexOf("/by") + 4);
                Task t = new Deadline(description, by);

                taskList.add(t);
                System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n", t, taskList.size());
            } else if (userInput.startsWith("event ")) {
                String description = userInput.substring(6, userInput.indexOf("/from") - 1);
                String start = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
                String end = userInput.substring(userInput.indexOf("/to") + 4);
                Task t = new Event(description, start, end);

                taskList.add(t);
                System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n", t, taskList.size());
            } else {
                Task t = new Task(userInput);

                taskList.add(t);
                System.out.printf("Added: %s%n", userInput);
            }
        }
    }
}
