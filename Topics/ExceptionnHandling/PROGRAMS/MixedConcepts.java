/*
 * ============================================================================
 * Program 26 : Mixed Exception Handling Concepts
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program that combines multiple Exception Handling concepts in
 * a single program, including custom exceptions, throw, throws, multiple catch
 * blocks, exception propagation, and finally.
 *
 * Objective:
 * - Revise the major Exception Handling concepts together.
 * - Understand how exceptions propagate between methods.
 * - Use throw and throws together.
 * - Handle multiple exception types.
 * - Observe finally execution during exception handling.
 *
 * Concepts Covered:
 * - try-catch-finally
 * - throw Keyword
 * - throws Keyword
 * - Custom Checked Exception
 * - IllegalArgumentException
 * - Multiple catch Blocks
 * - Exception Propagation
 * - Method Call Stack
 * ============================================================================
 */

class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String message) {

        super(message);

    }

}

public class MixedConcepts {

    public static void validateAmount(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Transaction amount must be greater than zero."
            );

        }

    }

    public static void processTransaction(
            double balance,
            double amount
    ) throws InvalidTransactionException {

        System.out.println("Processing Transaction");

        validateAmount(amount);

        if (amount > balance) {

            throw new InvalidTransactionException(
                    "Transaction amount exceeds available balance."
            );

        }

        double remainingBalance = balance - amount;

        System.out.println("Transaction Successful");
        System.out.println(
                "Remaining Balance : " + remainingBalance
        );

    }

    public static void performTransaction(
            double balance,
            double amount
    ) throws InvalidTransactionException {

        System.out.println("Inside performTransaction()");

        processTransaction(balance, amount);

        System.out.println("Leaving performTransaction()");

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        double balance = 10000.0;
        double amount = 15000.0;

        try {

            System.out.println("Available Balance : " + balance);
            System.out.println("Transaction Amount : " + amount);

            performTransaction(balance, amount);

            System.out.println("Transaction Processing Completed");

        }

        catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid Input : " + e.getMessage()
            );

        }

        catch (InvalidTransactionException e) {

            System.out.println(
                    "Transaction Failed : " + e.getMessage()
            );

        }

        finally {

            System.out.println("Transaction Attempt Finished");

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
 * Available Balance : 10000.0
 * Transaction Amount : 15000.0
 * Inside performTransaction()
 * Processing Transaction
 * Transaction Failed : Transaction amount exceeds available balance.
 * Transaction Attempt Finished
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. InvalidTransactionException is a custom checked exception because it
 *    extends Exception.
 *
 * 2. main() initializes:
 *
 *      balance = 10000.0
 *      amount  = 15000.0
 *
 * 3. main() calls:
 *
 *      performTransaction(balance, amount);
 *
 * 4. performTransaction() declares:
 *
 *      throws InvalidTransactionException
 *
 *    because it may allow this checked exception to propagate to its caller.
 *
 * 5. performTransaction() calls processTransaction().
 *
 * 6. processTransaction() first calls:
 *
 *      validateAmount(amount);
 *
 * 7. validateAmount() checks whether the amount is zero or negative.
 *
 * 8. If the amount is invalid, it throws IllegalArgumentException.
 *
 * 9. IllegalArgumentException is unchecked, so validateAmount() does not need
 *    to declare it using throws.
 *
 * 10. In this example, 15000.0 is greater than zero, so validation succeeds.
 *
 * 11. processTransaction() then checks:
 *
 *      amount > balance
 *
 * 12. Since:
 *
 *      15000.0 > 10000.0
 *
 *     the condition is true.
 *
 * 13. A new InvalidTransactionException object is created and explicitly
 *     thrown using throw.
 *
 * 14. processTransaction() does not handle the exception.
 *
 * 15. The exception propagates to performTransaction().
 *
 * 16. performTransaction() also does not handle it.
 *
 * 17. Therefore, it propagates to main().
 *
 * 18. The matching InvalidTransactionException catch block handles it.
 *
 * 19. Because the exception interrupted performTransaction(), the statement:
 *
 *      System.out.println("Leaving performTransaction()");
 *
 *     is never executed.
 *
 * 20. The statement:
 *
 *      System.out.println("Transaction Processing Completed");
 *
 *     inside main() is also skipped.
 *
 * 21. After the catch block finishes, the finally block executes.
 *
 * 22. Finally, execution continues after the complete try-catch-finally
 *     structure and "Program Finished" is printed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Exception Flow:
 * ============================================================================
 *
 * main()
 *   |
 *   v
 * performTransaction()
 *   |
 *   v
 * processTransaction()
 *   |
 *   +-------------------------------+
 *   |                               |
 *   v                               v
 * validateAmount()          amount > balance
 *   |                               |
 *   | Valid                         | true
 *   v                               v
 * Continue              InvalidTransactionException
 *                                   |
 *                                   | Not Handled
 *                                   v
 *                         performTransaction()
 *                                   |
 *                                   | Not Handled
 *                                   v
 *                                main()
 *                                   |
 *                                   v
 *                   catch (InvalidTransactionException)
 *                                   |
 *                                   v
 *                               finally
 *                                   |
 *                                   v
 *                          Continue Program
 * ============================================================================
 */

/*
 * ============================================================================
 * Concepts Revision:
 * ============================================================================
 *
 * throw
 * -----
 *
 * Explicitly throws an exception object.
 *
 *      throw new InvalidTransactionException(...);
 *
 *
 * throws
 * ------
 *
 * Declares that a method may throw an exception.
 *
 *      void processTransaction(...)
 *              throws InvalidTransactionException
 *
 *
 * Checked Exception
 * -----------------
 *
 * InvalidTransactionException extends Exception.
 *
 * The compiler requires it to be handled or declared.
 *
 *
 * Unchecked Exception
 * -------------------
 *
 * IllegalArgumentException extends RuntimeException.
 *
 * The compiler does not require it to be handled or declared.
 *
 *
 * Exception Propagation
 * ---------------------
 *
 * An exception moves up the method call stack until a compatible handler
