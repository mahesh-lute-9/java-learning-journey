/*
 * ============================================================================
 * Program 16 : Try-with-Resources
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to read data from a file using try-with-resources and
 * observe how Java automatically closes the resource.
 *
 * Objective:
 * - Understand try-with-resources.
 * - Learn how resources are automatically closed.
 * - Understand the role of the AutoCloseable interface.
 * - Avoid manual resource cleanup using finally.
 *
 * Concepts Covered:
 * - Try-with-Resources
 * - AutoCloseable
 * - BufferedReader
 * - FileReader
 * - IOException
 * - Automatic Resource Management
 * ============================================================================
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try (
            BufferedReader reader =
                    new BufferedReader(new FileReader("data.txt"))
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);

            }

        }

        catch (IOException e) {

            System.out.println(
                    "Exception Caught : " + e.getMessage()
            );

        }

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample data.txt:
 * ============================================================================
 *
 * Hello Java
 * Learning Exception Handling
 * Try-with-Resources Example
 * ============================================================================
 */

/*
 * ============================================================================
 * Sample Output:
 * ============================================================================
 *
 * Program Started
 * Hello Java
 * Learning Exception Handling
 * Try-with-Resources Example
 * Program Finished
 *
 *
 * If data.txt does not exist:
 *
 * Program Started
 * Exception Caught : data.txt (No such file or directory)
 * Program Finished
 *
 * Note:
 * The exact file-related error message may differ depending on the operating
 * system and Java version.
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. FileReader opens the data.txt file.
 *
 * 2. BufferedReader wraps FileReader and provides the readLine() method for
 *    reading the file line by line.
 *
 * 3. The resource is declared inside the parentheses of the try statement:
 *
 *      try (BufferedReader reader = ...)
 *
 * 4. BufferedReader implements the AutoCloseable interface through its class
 *    hierarchy, so it can be used with try-with-resources.
 *
 * 5. readLine() reads one line at a time.
 *
 * 6. When the end of the file is reached, readLine() returns null and the
 *    loop terminates.
 *
 * 7. When execution leaves the try block, Java automatically calls close()
 *    on the BufferedReader.
 *
 * 8. The developer does not need to manually call:
 *
 *      reader.close();
 *
 * 9. If an IOException occurs while opening or reading the file, the catch
 *    block handles it.
 *
 * 10. The resource is still closed automatically when execution leaves the
 *     try-with-resources statement.
 * ============================================================================
 */

/*
 * ============================================================================
 * Before Java 7:
 * ============================================================================
 *
 * Resource cleanup was commonly performed manually:
 *
 *      BufferedReader reader = null;
 *
 *      try {
 *
 *          reader = new BufferedReader(
 *                  new FileReader("data.txt")
 *          );
 *
 *      } finally {
 *
 *          if (reader != null) {
 *              reader.close();
 *          }
 *
 *      }
 *
 * This required additional cleanup code and could become complicated when
 * multiple resources were involved.
 * ============================================================================
 */

/*
 * ============================================================================
 * Modern Approach:
 * ============================================================================
 *
 *      try (BufferedReader reader =
 *              new BufferedReader(new FileReader("data.txt"))) {
 *
 *          // Use resource
 *
 *      }
 *
 * Java automatically manages the resource lifecycle.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Try-with-resources was introduced in Java 7.
 *
 * ✓ Resources declared inside the try parentheses are automatically closed.
 *
 * ✓ A resource must implement AutoCloseable (or its subinterface Closeable)
 *   to be used with try-with-resources.
 *
 * ✓ Manual close() calls are usually unnecessary.
 *
 * ✓ Resources are closed even when an exception occurs.
 *
 * ✓ Multiple resources can be declared in the same try-with-resources
 *   statement.
 *
 * ✓ When multiple resources are declared, they are closed in reverse order
 *   of their creation.
 *
 * ✓ Try-with-resources is generally preferred over finally for closing
 *   AutoCloseable resources.
 * ============================================================================
 */
