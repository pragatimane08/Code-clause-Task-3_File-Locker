import java.io.*;
import java.nio.channels.FileLock;

public class FileLocker {
    private static File file;

    public static void main(String[] args) {
        String fileName = "hi.txt";
        boolean isLocked = false;

        try {
            file = new File(fileName);

            // Check if the file exists
            
            // Do some processing on the locked file
            try {
                FileWriter writer = new FileWriter("hi.txt");
                writer.write("Hello, world!");
                writer.close();
                System.out.println("Data written to file successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            if (file.exists()) {
                // Check if the file is already locked
                isLocked = !file.renameTo(file);

                // If the file is not locked, lock it
                if (!isLocked) {
                    // Create a new file lock object
                    FileLock lock = new RandomAccessFile(file, "rw").getChannel().lock();

                    // Display a message indicating that the file is locked
                    System.out.println(fileName + " is now locked.");

                    

                    // Release the lock
                    lock.release();

                    // Display a message indicating that the lock has been released
                    System.out.println(fileName + " lock released.");
                } else {
                    System.out.println(fileName + " is already locked.");
                }
            } else {
                System.out.println(fileName + " does not exist.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}