
/*
 * ============================================================================
 * Program 02 : Basic Try-Catch
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to handle ArithmeticException using the try-catch block.
 *
 * Objective:
 * - Learn how to use try-catch.
 * - Understand how exceptions can be handled gracefully.
 * - Observe that the program continues execution after handling an exception.
 *
 * Concepts Covered:
 * - try Block
 * - catch Block
 * - ArithmeticException
 * - Exception Handling
 * ============================================================================
 */

public class BasicTryCatch {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            int number1 = 10;
            int number2 = 0;

            int result = number1 / number2;

            System.out.println("Result : " + result);

        } catch (ArithmeticException e) {

            System.out.println("Exception Caught : " + e.getMessage());

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
 * Exception Caught : / by zero
 * Program Finished
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
 * 5. The JVM creates an ArithmeticException object.
 * 6. A matching catch block is found.
 * 7. The catch block executes and prints the exception message.
 * 8. Control moves outside the try-catch block.
 * 9. "Program Finished" is printed successfully.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Code that may throw an exception should be placed inside the try block.
 * ✓ The catch block handles matching exceptions.
 * ✓ The exception object is automatically created by the JVM.
 * ✓ getMessage() returns the exception message.
 * ✓ After handling the exception, the program continues execution normally.
 * ============================================================================
 */
