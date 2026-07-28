/*
 * ============================================================================
 * Program 09 : Return and Finally
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate that the finally block executes even
 * when a return statement is encountered inside the try block.
 *
 * Objective:
 * - Understand the interaction between return and finally.
 * - Learn that the finally block executes before the method returns.
 * - Observe the execution order of try, finally, and return.
 *
 * Concepts Covered:
 * - return Statement
 * - finally Block
 * - Method Execution
 * - Control Flow
 * ============================================================================
 */

public class ReturnAndFinally {

    public static int calculate() {

        try {

            System.out.println("Inside try Block");

            return 100;

        }

        finally {

            System.out.println("Finally Block Executed");

        }

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        int result = calculate();

        System.out.println("Returned Value : " + result);

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
 * Finally Block Executed
 * Returned Value : 100
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. The calculate() method is invoked.
 * 3. Control enters the try block.
 * 4. "Inside try Block" is printed.
 * 5. The return statement prepares to return the value 100.
 * 6. Before returning, the JVM executes the finally block.
 * 7. "Finally Block Executed" is printed.
 * 8. The value 100 is returned to the calling method.
 * 9. The returned value is printed.
 * 10. The program finishes successfully.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ The finally block executes even when a return statement is present.
 * ✓ The return value is prepared first, but the method does not return until
 *   the finally block finishes execution.
 * ✓ The finally block is commonly used for resource cleanup before exiting a
 *   method.
 * ✓ The finally block is skipped only in exceptional cases such as
 *   System.exit() or JVM crash.
 * ============================================================================
 */
