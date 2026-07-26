

/*
 * ============================================================================
 * Program 04 : Exception Flow
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to understand how the control flow changes when an
 * exception occurs inside a try block.
 *
 * Objective:
 * - Understand the execution flow of try-catch.
 * - Observe that statements after an exception inside the try block are skipped.
 * - Learn how control transfers directly to the catch block.
 *
 * Concepts Covered:
 * - Exception Flow
 * - try Block
 * - catch Block
 * - ArithmeticException
 * - Control Transfer
 * ============================================================================
 */

public class ExceptionFlow {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            System.out.println("Statement 1");

            int result = 10 / 0;

            System.out.println("Statement 2");

            System.out.println("Statement 3");

        } catch (ArithmeticException e) {

            System.out.println("Exception Caught : " + e.getMessage());

        }

        System.out.println("Statement 4");

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample Output:
 * ============================================================================
 *
 * Program Started
 * Statement 1
 * Exception Caught : / by zero
 * Statement 4
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
 * 4. "Statement 1" is executed successfully.
 * 5. The expression 10 / 0 throws an ArithmeticException.
 * 6. The JVM immediately stops executing the remaining statements in the try
 *    block.
 * 7. "Statement 2" and "Statement 3" are skipped.
 * 8. Control transfers directly to the matching catch block.
 * 9. The exception message is printed.
 * 10. After the catch block, execution continues normally.
 * 11. "Statement 4" and "Program Finished" are printed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Once an exception occurs, the remaining statements inside the try block
 *   are skipped.
 * ✓ Control immediately transfers to the matching catch block.
 * ✓ After the catch block finishes, the program continues normally.
 * ✓ Only the statements before the exception are executed.
 * ✓ This demonstrates the actual execution flow of exception handling.
 * ============================================================================
 */
