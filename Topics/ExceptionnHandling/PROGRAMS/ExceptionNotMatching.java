

/*
 * ============================================================================
 * Program 05 : Exception Not Matching
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program where an exception is thrown, but the catch block is
 * designed to handle a different type of exception.
 *
 * Objective:
 * - Understand that a catch block only handles matching exception types.
 * - Observe what happens when no matching catch block is found.
 * - Learn that an unhandled exception terminates the program.
 *
 * Concepts Covered:
 * - Exception Matching
 * - ArithmeticException
 * - ArrayIndexOutOfBoundsException
 * - Unhandled Exception
 * ============================================================================
 */

public class ExceptionNotMatching {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            int result = 10 / 0;

            System.out.println("Result : " + result);

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Array Exception Caught");

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
 *
 * Exception in thread "main"
 * java.lang.ArithmeticException: / by zero
 *     at ExceptionNotMatching.main(ExceptionNotMatching.java:29)
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. "Program Started" is printed.
 * 3. The JVM enters the try block.
 * 4. The statement 10 / 0 throws an ArithmeticException.
 * 5. The JVM searches for a matching catch block.
 * 6. The available catch block handles only
 *    ArrayIndexOutOfBoundsException.
 * 7. Since the exception types do not match, the catch block is skipped.
 * 8. The ArithmeticException remains unhandled.
 * 9. The JVM prints the stack trace and terminates the program.
 * 10. "Program Finished" is never executed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ A catch block handles only the exception type it declares.
 * ✓ If no matching catch block is found, the exception remains unhandled.
 * ✓ An unhandled exception causes the JVM to terminate the program.
 * ✓ Exception matching is based on the exception's class hierarchy.
 * ✓ The order and type of catch blocks are important in exception handling.
 * ============================================================================
 */
