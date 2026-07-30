/*
 * ============================================================================
 * Program 19 : Bank Account
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to simulate basic bank account operations and use
 * custom exceptions to handle invalid deposits and insufficient balance.
 *
 * Objective:
 * - Apply Exception Handling to multiple banking operations.
 * - Validate deposit and withdrawal amounts.
 * - Protect account state from invalid transactions.
 * - Use custom exceptions for business-specific failures.
 *
 * Concepts Covered:
 * - Custom Exceptions
 * - RuntimeException
 * - throw Keyword
 * - try-catch
 * - Business Rule Validation
 * - Encapsulation
 * - Object State
 * ============================================================================
 */

class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String message) {

        super(message);

    }

}

class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {

        super(message);

    }

}

class BankAccount {

    private final String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double balance) {

        if (balance < 0) {

            throw new InvalidAmountException(
                    "Initial balance cannot be negative."
            );

        }

        this.accountHolder = accountHolder;
        this.balance = balance;

    }

    public void deposit(double amount) {

        if (amount <= 0) {

            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );

        }

        balance += amount;

        System.out.println("Deposit Successful");
        System.out.println("Deposited Amount : " + amount);

    }

    public void withdraw(double amount) {

        if (amount <= 0) {

            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );

        }

        if (amount > balance) {

            throw new InsufficientFundsException(
                    "Insufficient funds for withdrawal."
            );

        }

        balance -= amount;

        System.out.println("Withdrawal Successful");
        System.out.println("Withdrawn Amount : " + amount);

    }

    public void displayAccount() {

        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Current Balance : " + balance);

    }

}

public class BankAccountDemo {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            BankAccount account =
                    new BankAccount("Mahesh", 10000.0);

            account.displayAccount();

            System.out.println();

            account.deposit(5000.0);

            account.displayAccount();

            System.out.println();

            account.withdraw(20000.0);

            account.displayAccount();

        }

        catch (InvalidAmountException e) {

            System.out.println(
                    "Invalid Transaction : " + e.getMessage()
            );

        }

        catch (InsufficientFundsException e) {

            System.out.println(
                    "Transaction Failed : " + e.getMessage()
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
 * Account Holder : Mahesh
 * Current Balance : 10000.0
 *
 * Deposit Successful
 * Deposited Amount : 5000.0
 * Account Holder : Mahesh
 * Current Balance : 15000.0
 *
 * Transaction Failed : Insufficient funds for withdrawal.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. InvalidAmountException represents invalid monetary values such as:
 *
 *      - Negative initial balance
 *      - Zero deposit
 *      - Negative deposit
 *      - Zero withdrawal
 *      - Negative withdrawal
 *
 * 2. InsufficientFundsException represents a withdrawal request that exceeds
 *    the available account balance.
 *
 * 3. BankAccount keeps its balance private to protect the account state.
 *
 * 4. The constructor validates the initial balance before assigning it.
 *
 * 5. The account starts with a balance of 10000.0.
 *
 * 6. deposit(5000.0) passes validation.
 *
 * 7. The balance becomes:
 *
 *      10000.0 + 5000.0 = 15000.0
 *
 * 8. The program then attempts:
 *
 *      withdraw(20000.0)
 *
 * 9. Since 20000.0 is greater than the available balance of 15000.0,
 *    InsufficientFundsException is thrown.
 *
 * 10. The subtraction operation is never executed, so the account balance
 *     remains unchanged.
 *
 * 11. The matching catch block handles the exception and displays the
 *     transaction failure message.
 *
 * 12. Because control leaves the try block when the exception occurs, the
 *     displayAccount() statement after withdraw() is not executed.
 *
 * 13. Execution continues after the catch blocks and "Program Finished"
 *     is printed.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Validate data before modifying object state.
 *
 * ✓ Custom exceptions can clearly represent different business failures.
 *
 * ✓ InvalidAmountException represents an invalid transaction amount.
 *
 * ✓ InsufficientFundsException represents insufficient account balance.
 *
 * ✓ The balance remains unchanged when a transaction fails validation.
 *
 * ✓ Encapsulation prevents direct modification of the account balance.
 *
 * ✓ Exception Handling separates failure handling from normal banking logic.
 *
 * ✓ Once an exception leaves the try block, remaining statements inside that
 *   try block are skipped.
 * ============================================================================
 */
