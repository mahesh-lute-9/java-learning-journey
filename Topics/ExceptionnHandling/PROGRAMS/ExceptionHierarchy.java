/*
 * ============================================================================
 * Program 24 : Exception Hierarchy
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate how Java's exception hierarchy affects
 * exception matching and catch block execution.
 *
 * Objective:
 * - Understand the Java Exception Hierarchy.
 * - Learn how parent and child exception classes are related.
 * - Understand how the JVM searches for a matching catch block.
 * - Observe why specific exceptions must be caught before general exceptions.
 *
 * Concepts Covered:
 * - Throwable
 * - Exception
 * - RuntimeException
 * - ArithmeticException
 * - Exception Hierarchy
 * - Polymorphic Exception Handling
 * - Multiple catch Blocks
 * ============================================================================
 */

public class ExceptionHierarchy {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            int number1 = 10;
            int number2 = 0;

            int result = number1 / number2;

            System.out.println("Result : " + result);

        }

        catch (ArithmeticException e) {

            System.out.println(
                    "Caught by ArithmeticException : " + e.getMessage()
            );

        }

        catch (RuntimeException e) {

            System.out.println(
                    "Caught by RuntimeException : " + e.getMessage()
            );

        }

        catch (Exception e) {

            System.out.println(
                    "Caught by Exception : " + e.getMessage()
            );

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
 * Caught by ArithmeticException : / by zero
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The expression:
 *
 *      10 / 0
 *
 *    causes an ArithmeticException.
 *
 * 2. ArithmeticException is part of the following hierarchy:
 *
 *      Throwable
 *          |
 *          +-- Exception
 *                  |
 *                  +-- RuntimeException
 *                          |
 *                          +-- ArithmeticException
 *
 * 3. The JVM searches the catch blocks from top to bottom.
 *
 * 4. The first catch block is:
 *
 *      catch (ArithmeticException e)
 *
 * 5. Since the thrown object is an ArithmeticException, this catch block
 *    matches exactly.
 *
 * 6. The exception is handled immediately.
 *
 * 7. The remaining catch blocks are skipped.
 *
 * 8. RuntimeException could also reference an ArithmeticException object
 *    because RuntimeException is its parent class.
 *
 * 9. Similarly, Exception could reference the same object because Exception
 *    is an ancestor of ArithmeticException.
 *
 * 10. However, only the FIRST compatible catch block is executed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Exception Matching:
 * ============================================================================
 *
 * Thrown Object:
 *
 *      ArithmeticException
 *
 *              |
 *              v
 *
 *      ArithmeticException       <- Exact Match
 *              |
 *              v
 *      RuntimeException          <- Parent Match
 *              |
 *              v
 *      Exception                 <- Ancestor Match
 *              |
 *              v
 *      Throwable                 <- Root Match
 *
 *
 * Therefore, all of these types are capable of referring to an
 * ArithmeticException object:
 *
 *      ArithmeticException e
 *      RuntimeException e
 *      Exception e
 *      Throwable e
 *
 * But catch blocks are checked from top to bottom, and only the first
 * compatible handler executes.
 * ============================================================================
 */

/*
 * ============================================================================
 * Why Specific catch Blocks Must Come First:
 * ============================================================================
 *
 * Correct:
 *
 *      catch (ArithmeticException e) {
 *
 *      }
 *
 *      catch (RuntimeException e) {
 *
 *      }
 *
 *      catch (Exception e) {
 *
 *      }
 *
 *
 * Incorrect:
 *
 *      catch (Exception e) {
 *
 *      }
 *
 *      catch (ArithmeticException e) {
 *
 *      }
 *
 *
 * The second catch block is unreachable.
 *
 * Why?
 *
 * Exception is the parent of ArithmeticException.
 *
 * Therefore, any ArithmeticException would already be handled by:
 *
 *      catch (Exception e)
 *
 * Java detects this at compile time and reports an unreachable catch block.
 * ============================================================================
 */

/*
 * ============================================================================
 * Simplified Java Exception Hierarchy:
 * ============================================================================
 *
 * Object
 *   |
 *   +-- Throwable
 *         |
 *         +-- Error
 *         |     |
 *         |     +-- VirtualMachineError
 *         |     |
 *         |     +-- StackOverflowError
 *         |     |
 *         |     +-- AssertionError
 *         |
 *         +-- Exception
 *               |
 *               +-- IOException
 *               |     |
 *               |     +-- FileNotFoundException
 *               |
 *               +-- SQLException
 *               |
 *               +-- RuntimeException
 *                     |
 *                     +-- ArithmeticException
 *                     |
 *                     +-- NullPointerException
 *                     |
 *                     +-- IllegalArgumentException
 *                     |       |
 *                     |       +-- NumberFormatException
 *                     |
 *                     +-- IndexOutOfBoundsException
 *                             |
 *                             +-- ArrayIndexOutOfBoundsException
 *                             |
 *                             +-- StringIndexOutOfBoundsException
 *
 * NOTE:
 * This is only a simplified portion of Java's complete Throwable hierarchy.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Throwable is the root class for objects that can be thrown by Java's
 *   exception-handling mechanism.
 *
 * ✓ Throwable has two major branches:
 *
 *      Error
 *      Exception
 *
 * ✓ RuntimeException is a subclass of Exception.
 *
 * ✓ ArithmeticException is a subclass of RuntimeException.
 *
 * ✓ A parent exception reference can refer to an object of its child
 *   exception class.
 *
 * ✓ This is why catch(Exception e) can handle many different Exception
 *   subclasses.
 *
 * ✓ Catch blocks are examined from top to bottom.
 *
 * ✓ Only the first compatible catch block executes.
 *
 * ✓ Specific exception types must be placed before general exception types.
 *
 * ✓ Placing a parent catch before its child catch makes the child catch
 *   unreachable and results in a compile-time error.
 * ============================================================================
 */
