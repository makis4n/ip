package lebron.main;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    /* Constructor to initialize the Scanner for user input
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /* Displays a loading error message when tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    /* Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm LeBron.\nWhat can I do for you?");
    }

    /* Reads a line of input from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /* Displays the given message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /* Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
