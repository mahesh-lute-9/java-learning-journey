/*
 * ============================================================================
 * Program 06 : Multiple Catch
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program that demonstrates handling different types of exceptions
 * using multiple catch blocks.
 *
 * Objective:
 * - Learn how to handle multiple exceptions.
 * - Understand that only one matching catch block is executed.
 * - Observe the order of catch block execution.
 *
 * Concepts Covered:
 * - Multiple catch Blocks
 * - ArithmeticException
 * - ArrayIndexOutOfBoundsException
 * - Exception Matching
 * ============================================================================
 */

public class MultipleCatch {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            int[] numbers = {10, 20, 30};

            // Uncomment only one statement at a time

            // int result = 10 / 0;          // ArithmeticException

            System.out.println(numbers[5]);  // ArrayIndexOutOfBoundsException

        }

        catch (ArithmeticException e) {

            System.out.println("ArithmeticException Caught");

        }

        catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("ArrayIndexOutOfBoundsException Caught");

        }

        catch (Exception e) {

            System.out.println("General Exception Caught");

        }

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample Output 1:
 * ============================================================================
 *
 * Program Started
 * ArrayIndexOutOfBoundsException Caught
 * Program Finished
 * ============================================================================
 *
 * Sample Output 2:
 * ============================================================================
 *
 * Program Started
 * ArithmeticException Caught
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. The JVM enters the try block.
 * 3. Depending on the statement executed, an exception may occur.
 * 4. The JVM searches for the first matching catch block.
 * 5. If an ArithmeticException occurs, the first catch block executes.
 * 6. If an ArrayIndexOutOfBoundsException occurs, the second catch block
 *    executes.
 * 7. If neither specific catch block matches, the generic Exception catch
 *    block handles the exception.
 * 8. Only one catch block executes for a single exception.
 * 9. After handling the exception, execution continues normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ A single try block can have multiple catch blocks.
 * ✓ Only the first matching catch block is executed.
 * ✓ More specific exceptions should always come before generic exceptions.
 * ✓ The generic Exception catch block should be placed last.
 * ✓ After exception handling, the program continues normally.
 * ============================================================================
 */
