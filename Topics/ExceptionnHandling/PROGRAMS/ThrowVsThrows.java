/*
 * ============================================================================
 * Program 22 : Throw vs Throws
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate the difference between the throw and
 * throws keywords using a checked exception.
 *
 * Objective:
 * - Understand the difference between throw and throws.
 * - Learn how throw explicitly throws an exception object.
 * - Learn how throws declares that a method may throw an exception.
 * - Observe how the caller handles a declared checked exception.
 *
 * Concepts Covered:
 * - throw Keyword
 * - throws Keyword
 * - Checked Exception
 * - IOException
 * - Exception Propagation
 * - try-catch
 * ============================================================================
 */

import java.io.IOException;

public class ThrowVsThrows {

    public static void validateFile(String fileName) throws IOException {

        if (fileName == null || fileName.isBlank()) {

            throw new IOException(
                    "File name cannot be empty."
            );

        }

        System.out.println("Valid File Name : " + fileName);

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        String fileName = "";

        try {

            validateFile(fileName);

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
 * Sample Output:
 * ============================================================================
 *
 * Program Started
 * Exception Caught : File name cannot be empty.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. validateFile() declares:
 *
 *      throws IOException
 *
 * 2. The throws keyword tells the compiler and caller that this method may
 *    throw an IOException.
 *
 * 3. main() passes an empty string to validateFile().
 *
 * 4. The condition:
 *
 *      fileName == null || fileName.isBlank()
 *
 *    becomes true.
 *
 * 5. The following statement creates an IOException object:
 *
 *      new IOException("File name cannot be empty.")
 *
 * 6. The throw keyword explicitly throws that exception object:
 *
 *      throw new IOException(...);
 *
 * 7. validateFile() does not handle the exception itself.
 *
 * 8. The exception propagates back to main().
 *
 * 9. Since IOException is a checked exception, main() must either handle it
 *    using try-catch or declare it using throws.
 *
 * 10. In this program, main() handles it using a catch block.
 *
 * 11. After the exception is handled, the program continues normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * throw vs throws:
 * ============================================================================
 *
 * throw
 * -----
 *
 * - Used inside a method or block.
 * - Actually throws an exception object.
 * - Throws one exception object at a time.
 *
 * Example:
 *
 *      throw new IOException("File error");
 *
 *
 * throws
 * ------
 *
 * - Used in a method declaration.
 * - Declares that a method may throw one or more exception types.
 * - Does not itself create or throw an exception object.
 *
 * Example:
 *
 *      void readFile() throws IOException
 *
 *
 * Together:
 *
 *      void readFile() throws IOException {
 *
 *          throw new IOException("File error");
 *
 *      }
 *
 *             throws
 *                |
 *                | declares possibility
 *                v
 *        ---------------------
 *        |    readFile()     |
 *        ---------------------
 *                |
 *                | throw
 *                v
 *         IOException Object
 *                |
 *                v
 *          Calling Method
 *                |
 *                v
 *          Exception Handler
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ throw and throws serve different purposes.
 *
 * ✓ throw explicitly throws an exception object.
 *
 * ✓ throws declares possible exception types in a method signature.
 *
 * ✓ A single throw statement throws one exception object.
 *
 * ✓ A method can declare multiple exception types using throws:
 *
 *      void process() throws IOException, SQLException
 *
 * ✓ throws is especially important for checked exceptions because the
 *   compiler requires them to be handled or declared.
 *
 * ✓ Declaring an exception using throws does not mean that the exception
 *   will definitely occur.
 *
 * ✓ Remember:
 *
 *      throw  -> "Throw this exception now."
 *
 *      throws -> "This method may throw these exception types."
 * ============================================================================
 */
