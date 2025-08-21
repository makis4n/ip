import java.util.Scanner;
import java.util.ArrayList;

public class LeBron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm LeBron.\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();
            Task t = new Task(userInput);

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    Task temp = taskList.get(i);
                    System.out.printf("%d.[%s] %s%n", i + 1, temp.getStatusIcon(), temp.description);
                }
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                Task temp = taskList.get(taskNumber);
                temp.markAsDone();
                System.out.printf("Nice! I've marked this task as done:\n[%s] %s%n", temp.getStatusIcon(), temp.description);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                Task temp = taskList.get(taskNumber);
                temp.markAsNotDone();
                System.out.printf("OK, I've marked this task as not done yet:\n[%s] %s%n", temp.getStatusIcon(), temp.description);
            } else {
                taskList.add(t);
                System.out.printf("Added: %s%n", userInput);
            }
        }
    }
}
