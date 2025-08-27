import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;

import java.io.IOException;

public class LeBron {
    /* Ensures that the data file exists; creates it if it doesn't.
     * The data file is located in a "data" directory within the user's home directory.
     * If the "data" directory does not exist, it is created.
     * 
     * @return The File object representing the data file.
     */
    private static File getOrCreateDataFile() {
        String home = System.getProperty("user.home");
        Path directoryPath = Paths.get(home, "data");
        Path filePath = directoryPath.resolve("LeBron.txt");
        
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating data file: " + e.getMessage());
        }
        
        return filePath.toFile();
    }
    
    /* Reads tasks from the data file for populating the task list.
     * 
     * @param line A line from the data file representing a task.
     * @return A Task object created from the line; null if the line is invalid.
     */
    private static Task parseTask(String line) {
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
            Task d = new Deadline(description, parts[3]);
            if (isDone) {
                d.markAsDone();
            }
            return d;
        }
        case "E": {
            Task e = new Event(description, parts[3], parts[4]);
            if (isDone) {
                e.markAsDone();
            }
            return e;
        }
        }
        
        return null;
    }
    
    /* Writes a task to the data file.
     * 
     * @param file The data file to write to.
     * @param task The task to write.
     */
    private static void writeTaskToFile(File file, Task task) {
        try {
            StringBuilder sb = new StringBuilder();
            FileWriter fw = new FileWriter(file.getPath(), true);
            if (task instanceof ToDo) {
                sb.append("T|");
            } else if (task instanceof Deadline) {
                sb.append("D|");
            } else if (task instanceof Event) {
                sb.append("E|");
            }
            sb.append(task.isDone ? "1|" : "0|");
            sb.append(task.description);
            if (task instanceof Deadline) {
                sb.append("|").append(((Deadline) task).by);
            } else if (task instanceof Event) {
                sb.append("|").append(((Event) task).start).append("|").append(((Event) task).end);
            }
            
            fw.write(sb.toString());
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to data file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hello! I'm LeBron.\nWhat can I do for you?");
        
        Scanner sc = new Scanner(System.in);
        File dataFile = getOrCreateDataFile();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(dataFile.toPath())) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }
        
        while (true) {
            String userInput = sc.nextLine();

            if (userInput.trim().equalsIgnoreCase("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("No tasks found.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task temp = taskList.get(i);
                        System.out.printf("%d. %s%n", i + 1, temp);
                    }
                }
            } else if (userInput.trim().equalsIgnoreCase("bye")) {
                for (Task task : taskList) {
                    writeTaskToFile(dataFile, task);
                }
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(5).trim()) - 1;
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
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim()) - 1;
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
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim()) - 1;
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
