/*
 * ============================================================================
 * Program 17 : ATM Withdrawal
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to simulate an ATM withdrawal where a custom exception
 * is thrown when the withdrawal amount exceeds the available balance.
 *
 * Objective:
 * - Apply Exception Handling to a real-world banking scenario.
 * - Use a custom exception for insufficient balance.
 * - Validate withdrawal operations before modifying account balance.
 *
 * Concepts Covered:
 * - Custom Exception
 * - RuntimeException
 * - throw Keyword
 * - try-catch
 * - Business Rule Validation
 * - Encapsulation
 * ============================================================================
 */

class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {

        super(message);

    }

}

class ATMAccount {

    private double balance;

    public ATMAccount(double balance) {

        this.balance = balance;

    }

    public void withdraw(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero."
            );

        }

        if (amount > balance) {

            throw new InsufficientBalanceException(
                    "Insufficient balance for withdrawal."
            );

        }

        balance -= amount;

        System.out.println("Withdrawal Successful");
        System.out.println("Withdrawn Amount : " + amount);
        System.out.println("Remaining Balance : " + balance);

    }

    public double getBalance() {

        return balance;

    }

}

public class ATMWithdrawal {

    public static void main(String[] args) {

        System.out.println("Program Started");

        ATMAccount account = new ATMAccount(5000.0);

        double withdrawalAmount = 7000.0;

        System.out.println("Available Balance : " + account.getBalance());
        System.out.println("Withdrawal Amount : " + withdrawalAmount);

        try {

            account.withdraw(withdrawalAmount);

        }

        catch (InsufficientBalanceException e) {

            System.out.println(
                    "Transaction Failed : " + e.getMessage()
            );

        }

        catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid Transaction : " + e.getMessage()
            );

        }

        System.out.println(
                "Current Balance : " + account.getBalance()
        );

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample Output:
 * ============================================================================
 *
 * Program Started
 * Available Balance : 5000.0
 * Withdrawal Amount : 7000.0
 * Transaction Failed : Insufficient balance for withdrawal.
 * Current Balance : 5000.0
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. InsufficientBalanceException is a custom unchecked exception.
 *
 * 2. It extends RuntimeException because insufficient balance represents a
 *    business-rule violation in this example.
 *
 * 3. ATMAccount stores the account balance as a private field.
 *
 * 4. The account is created with an initial balance of 5000.0.
 *
 * 5. The program attempts to withdraw 7000.0.
 *
 * 6. withdraw() first checks whether the amount is valid.
 *
 * 7. If amount <= 0, IllegalArgumentException is thrown.
 *
 * 8. The method then checks:
 *
 *      amount > balance
 *
 * 9. Since 7000.0 > 5000.0, the condition is true.
 *
 * 10. An InsufficientBalanceException object is created and explicitly
 *     thrown using throw.
 *
 * 11. The balance subtraction is never executed.
 *
 * 12. Control transfers to the matching catch block in main().
 *
 * 13. The transaction failure message is displayed.
 *
 * 14. Since the withdrawal failed, the account balance remains 5000.0.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Exception Handling can be used to enforce real-world business rules.
 *
 * ✓ Custom exceptions make domain-specific failures easier to understand.
 *
 * ✓ Validation should happen before changing important application state.
 *
 * ✓ The balance is modified only after all withdrawal validations succeed.
 *
 * ✓ IllegalArgumentException is suitable here for an invalid withdrawal
 *   amount such as zero or a negative value.
 *
 * ✓ InsufficientBalanceException clearly represents the banking-specific
 *   failure condition.
 *
 * ✓ Different failure conditions can be handled using separate catch blocks.
 *
 * ✓ Exception Handling should not replace normal conditions. The condition
 *   detects the invalid state; the exception communicates the failure to
 *   the caller.
 * ============================================================================
 */
