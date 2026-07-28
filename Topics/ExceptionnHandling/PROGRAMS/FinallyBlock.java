/*
 * ============================================================================
 * Program 08 : Finally Block
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate the execution of the finally block.
 *
 * Objective:
 * - Understand the purpose of the finally block.
 * - Learn that the finally block executes whether an exception occurs or not.
 * - Observe the order of execution of try, catch, and finally blocks.
 *
 * Concepts Covered:
 * - try Block
 * - catch Block
 * - finally Block
 * - ArithmeticException
 * - Resource Cleanup
 * ============================================================================
 */

public class FinallyBlock {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            System.out.println("Inside try Block");

            int result = 10 / 0;

            System.out.println("Result : " + result);

        }

        catch (ArithmeticException e) {

            System.out.println("Exception Caught : " + e.getMessage());

        }

        finally {

            System.out.println("Finally Block Executed");

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
 * Inside try Block
 * Exception Caught : / by zero
 * Finally Block Executed
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. Control enters the try block.
 * 3. "Inside try Block" is printed.
 * 4. The statement 10 / 0 throws an ArithmeticException.
 * 5. The remaining statements inside the try block are skipped.
 * 6. The matching catch block handles the exception.
 * 7. After the catch block, the finally block executes.
 * 8. Finally, the remaining statements after the try-catch-finally block are
 *    executed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ The finally block always executes whether an exception occurs or not.
 * ✓ It executes after the try and catch blocks.
 * ✓ The finally block is generally used for resource cleanup.
 * ✓ There can be only one finally block for a try block.
 * ✓ The finally block executes even if the catch block contains a return
 *   statement (except in cases like System.exit()).
 * ============================================================================
 */
