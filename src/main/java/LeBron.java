import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm LeBron.\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(userInput);
            }
        }

        sc.close();
    }
}
