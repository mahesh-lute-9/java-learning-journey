
/*
 * ============================================================================
 * Program 03 : No Exception
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program that performs a valid division operation inside a
 * try-catch block where no exception occurs.
 *
 * Objective:
 * - Understand that the catch block executes only when an exception occurs.
 * - Observe the normal execution flow when no exception is thrown.
 * - Learn that the try block works like normal code if no exception occurs.
 *
 * Concepts Covered:
 * - try Block
 * - catch Block
 * - Normal Program Execution
 * - Exception Handling
 * ============================================================================
 */

public class NoException {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            int number1 = 20;
            int number2 = 5;

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
 * Result : 4
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
 * 4. The division operation 20 / 5 executes successfully.
 * 5. Since no exception occurs, the catch block is skipped.
 * 6. Control moves directly after the try-catch block.
 * 7. "Program Finished" is printed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ The catch block executes only when a matching exception occurs.
 * ✓ If no exception is thrown, the catch block is completely skipped.
 * ✓ A try block can contain normal executable code.
 * ✓ Exception handling does not affect normal program execution.
 * ✓ The program continues normally when no exception occurs.
 * ============================================================================
 */
