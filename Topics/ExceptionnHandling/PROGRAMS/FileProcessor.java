/*
 * ============================================================================
 * Program 20 : File Processor
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to process a text file using try-with-resources and
 * handle possible file-related exceptions appropriately.
 *
 * Objective:
 * - Apply Exception Handling to a real-world file-processing scenario.
 * - Use try-with-resources for automatic resource management.
 * - Handle specific file-related exceptions.
 * - Process file content safely without resource leaks.
 *
 * Concepts Covered:
 * - File Handling
 * - Try-with-Resources
 * - FileReader
 * - BufferedReader
 * - FileNotFoundException
 * - IOException
 * - Checked Exceptions
 * ============================================================================
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

    public static void processFile(String fileName) {

        try (
            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName))
        ) {

            System.out.println("Processing File : " + fileName);
            System.out.println("--------------------------------");

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {

                System.out.println(
                        lineNumber + " : " + line
                );

                lineNumber++;

            }

            System.out.println("--------------------------------");
            System.out.println("File Processed Successfully");

        }

        catch (FileNotFoundException e) {

            System.out.println(
                    "File Error : File '" + fileName + "' was not found."
            );

        }

        catch (IOException e) {

            System.out.println(
                    "I/O Error : " + e.getMessage()
            );

        }

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        String fileName = "data.txt";

        processFile(fileName);

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample data.txt:
 * ============================================================================
 *
 * Java Exception Handling
 * Learning Try-with-Resources
 * Processing Files Safely
 * ============================================================================
 */

/*
 * ============================================================================
 * Sample Output 1 - File Exists:
 * ============================================================================
 *
 * Program Started
 * Processing File : data.txt
 * --------------------------------
 * 1 : Java Exception Handling
 * 2 : Learning Try-with-Resources
 * 3 : Processing Files Safely
 * --------------------------------
 * File Processed Successfully
 * Program Finished
 * ============================================================================
 *
 *
 * Sample Output 2 - File Does Not Exist:
 * ============================================================================
 *
 * Program Started
 * File Error : File 'data.txt' was not found.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. main() defines the file name and passes it to processFile().
 *
 * 2. FileReader attempts to open the specified file.
 *
 * 3. BufferedReader wraps FileReader and allows the file to be read
 *    efficiently line by line.
 *
 * 4. Both FileReader and BufferedReader work with resources that must be
 *    closed after use.
 *
 * 5. BufferedReader is declared inside a try-with-resources statement:
 *
 *      try (BufferedReader reader = ...)
 *
 * 6. Java automatically closes the reader when execution leaves the try
 *    block.
 *
 * 7. readLine() reads one line from the file at a time.
 *
 * 8. When the end of the file is reached, readLine() returns null.
 *
 * 9. lineNumber keeps track of the current line being processed.
 *
 * 10. If the requested file does not exist, FileReader throws
 *     FileNotFoundException.
 *
 * 11. FileNotFoundException is handled by the first catch block.
 *
 * 12. Other input/output problems are handled by the IOException catch block.
 *
 * 13. FileNotFoundException must appear before IOException because
 *     FileNotFoundException is a subclass of IOException.
 *
 * 14. After the exception is handled, execution returns to main() and the
 *     program continues normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * Exception Hierarchy Used Here:
 * ============================================================================
 *
 * Exception
 *     |
 *     +-- IOException
 *             |
 *             +-- FileNotFoundException
 *
 * Therefore:
 *
 *      catch (FileNotFoundException e)
 *
 * must appear before:
 *
 *      catch (IOException e)
 *
 * If IOException were placed first, the FileNotFoundException catch block
 * would become unreachable and the compiler would report an error.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ File operations commonly involve checked exceptions.
 *
 * ✓ Try-with-resources automatically closes file resources.
 *
 * ✓ FileNotFoundException specifically represents failure to open a
 *   non-existent file.
 *
 * ✓ IOException represents broader input/output failures.
 *
 * ✓ Specific exception handlers should appear before their parent exception
 *   handlers.
 *
 * ✓ Reading files line by line avoids loading the entire file into memory at
 *   once.
 *
 * ✓ Resource cleanup still happens automatically if an exception occurs
 *   while processing the file.
 *
 * ✓ Real-world file-processing code should distinguish expected failures
 *   instead of catching Exception for everything.
 * ============================================================================
 */
