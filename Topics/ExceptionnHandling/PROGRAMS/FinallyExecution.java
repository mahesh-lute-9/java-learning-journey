/*
 * ============================================================================
 * Program 23 : Finally Execution
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate the behavior of the finally block when
 * a return statement is present inside both the try and finally blocks.
 *
 * Objective:
 * - Understand advanced finally execution behavior.
 * - Observe how return statements interact with finally.
 * - Learn why returning from a finally block should be avoided.
 *
 * Concepts Covered:
 * - try Block
 * - finally Block
 * - return Statement
 * - Control Flow
 * - Return Value Override
 * ============================================================================
 */

public class FinallyExecution {

    public static int getValue() {

        try {

            System.out.println("Inside try Block");

            return 10;

        }

        finally {

            System.out.println("Inside finally Block");

            return 20;

        }

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        int result = getValue();

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
 * Inside finally Block
 * Returned Value : 20
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. main() calls the getValue() method.
 *
 * 2. Control enters the try block.
 *
 * 3. The try block executes:
 *
 *      return 10;
 *
 * 4. Java prepares the value 10 to be returned.
 *
 * 5. However, before the method actually returns, the finally block must
 *    execute.
 *
 * 6. Control enters the finally block.
 *
 * 7. The finally block executes:
 *
 *      return 20;
 *
 * 8. This new return statement replaces the pending return value of 10.
 *
 * 9. Therefore, getValue() returns 20 instead of 10.
 *
 * 10. main() receives the value 20 and prints:
 *
 *      Returned Value : 20
 * ============================================================================
 */

/*
 * ============================================================================
 * Important Behavior:
 * ============================================================================
 *
 * Consider:
 *
 *      try {
 *
 *          return 10;
 *
 *      }
 *
 *      finally {
 *
 *          return 20;
 *
 *      }
 *
 *
 * Execution:
 *
 *      try
 *       |
 *       v
 *   return 10
 *       |
 *       |  Pending Return
 *       v
 *    finally
 *       |
 *       v
 *   return 20
 *       |
 *       |  Overrides Previous Return
 *       v
 *      20
 *
 * Therefore:
 *
 *      Result = 20
 * ============================================================================
 */

/*
 * ============================================================================
 * Why Returning from finally is Dangerous:
 * ============================================================================
 *
 * Returning from finally can hide important information.
 *
 * Example:
 *
 *      try {
 *
 *          throw new RuntimeException("Something went wrong");
 *
 *      }
 *
 *      finally {
 *
 *          return 100;
 *
 *      }
 *
 * The return inside finally can suppress the exception that was already
 * propagating from the try block.
 *
 * This makes debugging difficult because the original failure can disappear
 * from the caller's point of view.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ The finally block normally executes before a method completes its return.
 *
 * ✓ A return value from try can be prepared before finally executes.
 *
 * ✓ If finally also returns a value, its return overrides the pending return
 *   from try.
 *
 * ✓ A return statement inside finally can also suppress a pending exception.
 *
 * ✓ Returning from finally is therefore considered poor practice and should
 *   generally be avoided.
 *
 * ✓ finally should primarily be used for cleanup logic when needed.
 *
 * ✓ Modern resource cleanup should generally use try-with-resources when the
 *   resource implements AutoCloseable.
 * ============================================================================
 */
