package lebron.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import lebron.exception.LeBronException;
import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.ToDo;

/**
 * The Storage class is responsible for managing the storage of tasks in a file.
 * It handles loading tasks from the file, ensuring the file exists, and writing tasks to the file.
 */
public class Storage {
    private final Path filePath;
    private final File dataFile;

    /**
     *  Constructor to initialize the Storage with the specified file path.
     * If the file does not exist, it is created along with any necessary directories.
     */
    public Storage(String pathString) {
        this.filePath = Paths.get(pathString);
        this.dataFile = filePath.toFile();

        ensureFileExists();
    }

    /**
     *  Ensures that the data file exists; creates it if it doesn't.
     * The data file is located in a "data" directory within the user's home directory.
     * If the "data" directory does not exist, it is created.
     */
    public void ensureFileExists() {
        try {
            Path parentDirectory = filePath.getParent();

            if (parentDirectory != null && !Files.exists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     *  Loads tasks from the data file into an ArrayList.
     * Each line in the data file is parsed into a Task object using the Parser class.
     *
     * @return An ArrayList of Task objects loaded from the data file.
     * @throws LeBronException If there is an error reading the data file.
     */
    public ArrayList<Task> load() throws LeBronException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(dataFile.toPath())) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new LeBronException("Error reading data file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     *  Returns the data file used for storing tasks.
     *
     * @return The data file.
     */
    public File getDataFile() {
        return dataFile;
    }

    /**
     *  Writes a task to the data file.
     *
     * @param task The task to write.
     */
    public void writeTaskToFile(Task task) {
        try {
            StringBuilder sb = new StringBuilder();
            FileWriter fw = new FileWriter(dataFile.getPath(), true);
            if (task instanceof ToDo) {
                sb.append("T|");
            } else if (task instanceof Deadline) {
                sb.append("D|");
            } else if (task instanceof Event) {
                sb.append("E|");
            }
            sb.append(task.isDone() ? "1|" : "0|");
            sb.append(task.getDescription());
            if (task instanceof Deadline) {
                sb.append("|").append(((Deadline) task).getBy());
            } else if (task instanceof Event) {
                sb.append("|").append(((Event) task).getStart()).append("|").append(((Event) task).getEnd());
            }

            fw.write(sb.toString());
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to data file: " + e.getMessage());
        }
    }
}
