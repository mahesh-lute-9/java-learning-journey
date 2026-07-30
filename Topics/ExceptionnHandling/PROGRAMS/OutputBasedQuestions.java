/*
 * ============================================================================
 * Program 25 : Output-Based Exception Questions
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program containing multiple exception-handling scenarios to
 * understand and predict the output before executing the program.
 *
 * Objective:
 * - Practice output-based Exception Handling questions.
 * - Understand try-catch-finally execution order.
 * - Observe exception flow after an exception occurs.
 * - Understand return and finally behavior.
 * - Strengthen interview-level exception tracing skills.
 *
 * Concepts Covered:
 * - try-catch
 * - finally
 * - Exception Flow
 * - Multiple catch Blocks
 * - return with finally
 * - Nested try-catch
 * ============================================================================
 */

public class OutputBasedQuestions {

    /*
     * ------------------------------------------------------------------------
     * Scenario 1 : Exception Inside try
     * ------------------------------------------------------------------------
     */

    public static void scenarioOne() {

        System.out.println("Scenario 1");

        try {

            System.out.println("A");

            int result = 10 / 0;

            System.out.println("B");

        }

        catch (ArithmeticException e) {

            System.out.println("C");

        }

        System.out.println("D");

    }


    /*
     * ------------------------------------------------------------------------
     * Scenario 2 : No Exception
     * ------------------------------------------------------------------------
     */

    public static void scenarioTwo() {

        System.out.println("Scenario 2");

        try {

            System.out.println("A");

            int result = 10 / 2;

            System.out.println("B");

        }

        catch (ArithmeticException e) {

            System.out.println("C");

        }

        finally {

            System.out.println("D");

        }

        System.out.println("E");

    }


    /*
     * ------------------------------------------------------------------------
     * Scenario 3 : Exception with finally
     * ------------------------------------------------------------------------
     */

    public static void scenarioThree() {

        System.out.println("Scenario 3");

        try {

            System.out.println("A");

            int result = 10 / 0;

            System.out.println("B");

        }

        catch (ArithmeticException e) {

            System.out.println("C");

        }

        finally {

            System.out.println("D");

        }

        System.out.println("E");

    }


    /*
     * ------------------------------------------------------------------------
     * Scenario 4 : Multiple catch Blocks
     * ------------------------------------------------------------------------
     */

    public static void scenarioFour() {

        System.out.println("Scenario 4");

        try {

            int[] numbers = {10, 20, 30};

            System.out.println(numbers[5]);

        }

        catch (ArithmeticException e) {

            System.out.println("Arithmetic");

        }

        catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Array");

        }

        catch (Exception e) {

            System.out.println("General");

        }

    }


    /*
     * ------------------------------------------------------------------------
     * Scenario 5 : Nested try-catch
     * ------------------------------------------------------------------------
     */

    public static void scenarioFive() {

        System.out.println("Scenario 5");

        try {

            System.out.println("Outer Try");

            try {

                System.out.println("Inner Try");

                int result = 10 / 0;

                System.out.println("After Division");

            }

            catch (ArithmeticException e) {

                System.out.println("Inner Catch");

            }

            System.out.println("After Inner Block");

        }

        catch (Exception e) {

            System.out.println("Outer Catch");

        }

    }


    /*
     * ------------------------------------------------------------------------
     * Scenario 6 : return with finally
     * ------------------------------------------------------------------------
     */

    public static int scenarioSix() {

        System.out.println("Scenario 6");

        try {

            System.out.println("Inside Try");

            return 10;

        }

        finally {

            System.out.println("Inside Finally");

        }

    }


    public static void main(String[] args) {

        System.out.println("Program Started");

        System.out.println();

        scenarioOne();

        System.out.println();

        scenarioTwo();

        System.out.println();

        scenarioThree();

        System.out.println();

        scenarioFour();

        System.out.println();

        scenarioFive();

        System.out.println();

        int result = scenarioSix();

        System.out.println("Returned Value : " + result);

        System.out.println();

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
 * Scenario 1
 * A
 * C
 * D
 *
 * Scenario 2
 * A
 * B
 * D
 * E
 *
 * Scenario 3
 * A
 * C
 * D
 * E
 *
 * Scenario 4
 * Array
 *
 * Scenario 5
 * Outer Try
 * Inner Try
 * Inner Catch
 * After Inner Block
 *
 * Scenario 6
 * Inside Try
 * Inside Finally
 * Returned Value : 10
 *
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * SCENARIO 1
 * ----------
 *
 * 1. "A" is printed.
 *
 * 2. The expression 10 / 0 throws ArithmeticException.
 *
 * 3. "B" is skipped because execution immediately leaves the try block.
 *
 * 4. The ArithmeticException catch block executes and prints "C".
 *
 * 5. Execution continues after try-catch and prints "D".
 *
 * Output:
 *
 *      A
 *      C
 *      D
 *
 *
 * SCENARIO 2
 * ----------
 *
 * 1. "A" is printed.
 *
 * 2. 10 / 2 executes successfully.
 *
 * 3. "B" is printed.
 *
 * 4. No exception occurs, so the catch block is skipped.
 *
 * 5. finally executes and prints "D".
 *
 * 6. Execution continues and prints "E".
 *
 * Output:
 *
 *      A
 *      B
 *      D
 *      E
 *
 *
 * SCENARIO 3
 * ----------
 *
 * 1. "A" is printed.
 *
 * 2. 10 / 0 throws ArithmeticException.
 *
 * 3. "B" is skipped.
 *
 * 4. The catch block prints "C".
 *
 * 5. The finally block prints "D".
 *
 * 6. Execution continues and prints "E".
 *
 * Output:
 *
 *      A
 *      C
 *      D
 *      E
 *
 *
 * SCENARIO 4
 * ----------
 *
 * 1. numbers contains only three elements.
 *
 * 2. Accessing numbers[5] throws ArrayIndexOutOfBoundsException.
 *
 * 3. The ArithmeticException catch block does not match.
 *
 * 4. The ArrayIndexOutOfBoundsException catch block matches.
 *
 * 5. "Array" is printed.
 *
 * 6. The general Exception catch block is skipped because an earlier matching
 *    handler has already handled the exception.
 *
 * Output:
 *
 *      Array
 *
 *
 * SCENARIO 5
 * ----------
 *
 * 1. Control enters the outer try block.
 *
 * 2. "Outer Try" is printed.
 *
 * 3. Control enters the inner try block.
 *
 * 4. "Inner Try" is printed.
 *
 * 5. 10 / 0 throws ArithmeticException.
 *
 * 6. "After Division" is skipped.
 *
 * 7. The inner catch block handles the exception.
 *
 * 8. "Inner Catch" is printed.
 *
 * 9. Because the exception was handled internally, execution continues in
 *    the outer try block.
 *
 * 10. "After Inner Block" is printed.
 *
 * 11. The outer catch block does not execute.
 *
 *
 * SCENARIO 6
 * ----------
 *
 * 1. Control enters the try block.
 *
 * 2. "Inside Try" is printed.
 *
 * 3. return 10 prepares the value 10 for return.
 *
 * 4. Before the method completes, the finally block executes.
 *
 * 5. "Inside Finally" is printed.
 *
 * 6. Since finally does not contain another return statement, the previously
 *    prepared value 10 is returned.
 *
 * 7. main() receives and prints the returned value.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Statements after the point where an exception is thrown inside a try
 *   block are skipped.
 *
 * ✓ Only the first compatible catch block executes.
 *
 * ✓ finally executes whether an exception occurs or not in these normal
 *   execution scenarios.
 *
 * ✓ A handled exception allows execution to continue after the corresponding
 *   try-catch-finally structure.
 *
 * ✓ An inner catch block can prevent an exception from propagating to an
 *   outer catch block.
 *
 * ✓ A finally block executes before a pending return completes.
 *
 * ✓ Output-based questions should be solved by tracing control flow one
 *   statement at a time rather than guessing the output.
 *
 * ✓ Pay special attention to:
 *
 *      - Where the exception occurs
 *      - Which statements are skipped
 *      - Which catch type matches
 *      - Whether finally exists
 *      - Whether return is involved
 *      - Whether try-catch blocks are nested
 * ============================================================================
 */
