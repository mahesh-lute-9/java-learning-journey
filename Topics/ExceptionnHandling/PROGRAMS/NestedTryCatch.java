/*
 * ============================================================================
 * Program 07 : Nested Try-Catch
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate Nested try-catch blocks.
 *
 * Objective:
 * - Understand how nested try-catch blocks work.
 * - Learn that an inner catch block handles exceptions occurring inside the
 *   inner try block.
 * - Observe the execution flow of nested exception handling.
 *
 * Concepts Covered:
 * - Nested try Block
 * - Inner catch Block
 * - Outer catch Block
 * - ArithmeticException
 * - ArrayIndexOutOfBoundsException
 * ============================================================================
 */

public class NestedTryCatch {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            System.out.println("Inside Outer try");

            try {

                System.out.println("Inside Inner try");

                int result = 10 / 0;

                System.out.println("Result : " + result);

            }

            catch (ArithmeticException e) {

                System.out.println("Inner Catch : " + e.getMessage());

            }

            int[] numbers = {10, 20, 30};

            System.out.println(numbers[5]);

        }

        catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Outer Catch : " + e.getMessage());

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
 * Inside Outer try
 * Inside Inner try
 * Inner Catch : / by zero
 * Outer Catch : Index 5 out of bounds for length 3
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. Control enters the outer try block.
 * 3. Control then enters the inner try block.
 * 4. The statement 10 / 0 throws an ArithmeticException.
 * 5. The inner catch block handles the exception.
 * 6. Execution continues in the outer try block.
 * 7. Accessing numbers[5] throws an ArrayIndexOutOfBoundsException.
 * 8. Since there is no matching inner catch block, the exception propagates
 *    to the outer catch block.
 * 9. The outer catch block handles the exception.
 * 10. The program continues and prints "Program Finished".
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ A try block can be placed inside another try block.
 * ✓ The inner catch block handles exceptions from the inner try block.
 * ✓ If the inner catch block cannot handle an exception, it propagates to the
 *   outer catch block.
 * ✓ Nested try-catch blocks are useful for handling exceptions at different
 *   levels of a program.
 * ✓ Exception propagation follows the nearest matching catch block.
 * ============================================================================
 */
