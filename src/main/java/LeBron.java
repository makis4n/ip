import java.util.Scanner;
import java.util.ArrayList;

public class LeBron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm LeBron.\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();

            switch (userInput.toLowerCase()) {
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.printf("%d. %s%n", i + 1, taskList.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    taskList.add(userInput);
                    System.out.printf("Added: %s%n", userInput);
            }
        }

    }
}
