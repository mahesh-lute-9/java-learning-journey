
/*
 * ============================================================================
 * Program 01 : First Exception
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program that performs division by zero without using Exception
 * Handling and observe the program's behavior.
 *
 * Objective:
 * - Understand what an exception is.
 * - Observe how the JVM reacts to an unhandled exception.
 * - Learn that an unhandled exception terminates the program.
 *
 * Concepts Covered:
 * - Runtime Exception
 * - ArithmeticException
 * - JVM Exception Handling
 * - Program Termination
 * ============================================================================
 */

public class FirstException {

    public static void main(String[] args) {

        System.out.println("Program Started");

        int number1 = 10;
        int number2 = 0;

        int result = number1 / number2;

        System.out.println("Result : " + result);

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
 *     at FirstException.main(FirstException.java:27)
 *
 * Note:
 * The line number may vary depending on your editor and code formatting.
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. "Program Started" is printed successfully.
 * 3. number1 and number2 are initialized.
 * 4. The statement number1 / number2 attempts to divide by zero.
 * 5. The JVM detects this invalid arithmetic operation.
 * 6. It automatically creates an ArithmeticException object.
 * 7. Since there is no try-catch block, the exception remains unhandled.
 * 8. The JVM terminates the program immediately.
 * 9. The remaining statements are never executed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ An exception is an abnormal event during program execution.
 * ✓ Dividing an integer by zero throws ArithmeticException.
 * ✓ The JVM automatically creates and throws the exception object.
 * ✓ Without Exception Handling, the program terminates immediately.
 * ✓ Statements after the exception are never executed.
 * ============================================================================
 */
