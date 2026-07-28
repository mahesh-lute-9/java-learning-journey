/*
 * ============================================================================
 * Program 13 : Checked vs Unchecked Exceptions
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate the difference between checked and
 * unchecked exceptions.
 *
 * Objective:
 * - Understand checked and unchecked exceptions.
 * - Observe how the compiler treats checked exceptions.
 * - Understand that unchecked exceptions are not enforced by the compiler.
 *
 * Concepts Covered:
 * - Checked Exceptions
 * - Unchecked Exceptions
 * - IOException
 * - ArithmeticException
 * - Compile-Time Checking
 * - Runtime Exception
 * ============================================================================
 */

import java.io.FileReader;
import java.io.IOException;

public class CheckedVsUnchecked {

    public static void checkedExceptionExample() {

        System.out.println("Checked Exception Example");

        try {

            FileReader reader = new FileReader("data.txt");

            System.out.println("File opened successfully.");

            reader.close();

        }

        catch (IOException e) {

            System.out.println("Checked Exception Caught : " + e.getMessage());

        }

    }

    public static void uncheckedExceptionExample() {

        System.out.println("Unchecked Exception Example");

        try {

            int number1 = 10;
            int number2 = 0;

            int result = number1 / number2;

            System.out.println("Result : " + result);

        }

        catch (ArithmeticException e) {

            System.out.println("Unchecked Exception Caught : " + e.getMessage());

        }

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        checkedExceptionExample();

        System.out.println();

        uncheckedExceptionExample();

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample Output:
 * ============================================================================
 *
 * Program Started
 * Checked Exception Example
 * Checked Exception Caught : data.txt (No such file or directory)
 *
 * Unchecked Exception Example
 * Unchecked Exception Caught : / by zero
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
 * CHECKED EXCEPTION:
 *
 * 1. checkedExceptionExample() attempts to open data.txt using FileReader.
 * 2. FileReader may throw FileNotFoundException, which is a checked exception.
 * 3. FileNotFoundException is a subclass of IOException.
 * 4. The compiler requires this exception to be handled or declared.
 * 5. Here, IOException is handled using a try-catch block.
 *
 *
 * UNCHECKED EXCEPTION:
 *
 * 6. uncheckedExceptionExample() performs the operation 10 / 0.
 * 7. The code compiles even though division by zero may cause an exception.
 * 8. During execution, the JVM throws ArithmeticException.
 * 9. ArithmeticException is a subclass of RuntimeException.
 * 10. RuntimeException and its subclasses are unchecked exceptions.
 * 11. The compiler does not require unchecked exceptions to be handled or
 *     declared.
 * 12. In this program, ArithmeticException is handled explicitly so execution
 *     can continue normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * Exception Hierarchy:
 * ============================================================================
 *
 * Throwable
 * |
 * +-- Exception
 *     |
 *     +-- IOException -------------------- Checked
 *     |    |
 *     |    +-- FileNotFoundException
 *     |
 *     +-- RuntimeException --------------- Unchecked
 *          |
 *          +-- ArithmeticException
 *
 * Important:
 *
 * Checked and unchecked describe COMPILER ENFORCEMENT.
 *
 * Both types of exceptions actually occur at runtime.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Checked exceptions are checked by the compiler.
 * ✓ Checked exceptions must be handled or declared using throws.
 * ✓ IOException is an example of a checked exception.
 *
 * ✓ Unchecked exceptions are not enforced by the compiler.
 * ✓ RuntimeException and its subclasses are unchecked.
 * ✓ ArithmeticException is an unchecked exception.
 *
 * ✓ "Checked exception" does NOT mean the exception occurs at compile time.
 *   Exceptions occur during runtime; the compiler only checks whether certain
 *   exception types have been properly handled or declared.
 *
 * ✓ Checked exceptions commonly represent failures involving external
 *   operations such as files, databases, networks, and threads.
 *
 * ✓ Unchecked exceptions commonly indicate programming errors, invalid state,
 *   or invalid arguments.
 * ============================================================================
 */
