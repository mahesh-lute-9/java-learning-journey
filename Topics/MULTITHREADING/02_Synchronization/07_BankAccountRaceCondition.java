/*
 * ============================================================
 * 07 - BANK ACCOUNT RACE CONDITION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a bank account with:
 *
 *     Balance = ₹1000
 *
 * Two ATMs are connected to the same account.
 *
 * ATM 1 wants to withdraw:
 *
 *     ₹700
 *
 * ATM 2 also wants to withdraw:
 *
 *     ₹700
 *
 * Obviously, both transactions should NOT succeed.
 *
 * The account only contains ₹1000.
 *
 *
 * EXPECTED RESULT:
 * ------------------------------------------------------------
 *
 * Initial Balance = ₹1000
 *
 * ATM 1 withdraws ₹700
 * ATM 2 should fail
 *
 * Final Balance should be:
 *
 *     ₹300
 *
 *
 * BUT...
 *
 * What happens if both ATMs check the balance at exactly the
 * same time?
 *
 *
 * ------------------------------------------------------------
 * THE PROBLEM
 * ------------------------------------------------------------
 *
 * The withdrawal operation contains multiple steps:
 *
 *     1. Read balance
 *     2. Check whether enough money exists
 *     3. Subtract amount
 *     4. Update balance
 *
 *
 * These steps together form ONE logical operation.
 *
 *
 * Without synchronization, this can happen:
 *
 *
 * ATM 1                         ATM 2
 * -----                         -----
 *
 * Read balance = ₹1000
 *                               Read balance = ₹1000
 *
 * Check ₹1000 >= ₹700
 *                               Check ₹1000 >= ₹700
 *
 * Subtract ₹700
 *                               Subtract ₹700
 *
 * Balance = ₹300
 *                               Balance = ₹300
 *
 *
 * Both ATMs think the withdrawal was successful.
 *
 * This is a RACE CONDITION.
 *
 *
 * ------------------------------------------------------------
 * WHAT IS A RACE CONDITION?
 * ------------------------------------------------------------
 *
 * A race condition occurs when multiple threads access shared
 * mutable data concurrently and the final result depends on
 * the timing/interleaving of their execution.
 *
 *
 * In this program:
 *
 * Shared Resource:
 *
 *     balance
 *
 * Multiple Threads:
 *
 *     ATM-1
 *     ATM-2
 *
 * Operation:
 *
 *     withdraw()
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT CONCEPT
 * ------------------------------------------------------------
 *
 * The problem is NOT simply:
 *
 *     "Two threads are running."
 *
 * The real problem is:
 *
 *     Multiple threads are modifying SHARED MUTABLE STATE
 *     without proper coordination.
 *
 *
 * ------------------------------------------------------------
 * WHY DOES Thread.sleep() EXIST HERE?
 * ------------------------------------------------------------
 *
 * sleep() is deliberately used to make the race condition easier
 * to observe.
 *
 * It creates a larger window where another thread can enter the
 * withdrawal operation.
 *
 * NOTE:
 *
 * sleep() does NOT create the race condition.
 *
 * It only makes the timing issue easier to reproduce.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Run this program multiple times.
 *
 * Ask:
 *
 *     Is the final balance always correct?
 *
 * Because thread scheduling is nondeterministic, you may see
 * different results on different runs.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change the initial balance.
 *
 * Example:
 *
 *     ₹2000
 *
 * Then try different withdrawal amounts.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Create 10 ATM threads.
 *
 * Give each thread a withdrawal amount.
 *
 * Observe how unpredictable the result becomes.
 *
 *
 * ------------------------------------------------------------
 * THINK BEFORE MOVING ON
 * ------------------------------------------------------------
 *
 * How can we make this operation:
 *
 *     check balance
 *         +
 *     subtract money
 *
 * behave as ONE indivisible operation?
 *
 *
 * That is exactly the problem synchronization solves.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a race condition?
 *
 * 2. Why is balance a shared resource?
 *
 * 3. Why can the result differ between executions?
 *
 * 4. Does volatile solve this problem?
 *
 * Answer:
 *
 * No.
 *
 * volatile provides visibility guarantees, but it does not make
 * a compound operation such as:
 *
 *     balance -= amount
 *
 * atomic.
 *
 * 5. What is a critical section?
 *
 * 6. How would you fix this program?
 *
 * Expected answer:
 *
 *     Synchronize the withdrawal operation.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Multithreading becomes dangerous when threads access shared
 * mutable state.
 *
 *
 *     Multiple Threads
 *            +
 *     Shared Mutable Data
 *            +
 *     No Coordination
 *            ↓
 *       Race Condition
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 08:
 *
 *     SynchronizedCounter.java
 *
 * We will fix a similar race condition using the
 * synchronized keyword.
 *
 * ============================================================
 */

public class BankAccountRaceCondition {

    /*
     * Shared object.
     *
     * Both ATM threads will use the SAME BankAccount instance.
     */
    static class BankAccount {

        private int balance = 1000;

        /*
         * This method is intentionally NOT synchronized.
         *
         * That is the bug we are trying to demonstrate.
         */
        public void withdraw(
                int amount,
                String atmName) {

            System.out.println(
                    atmName
                            + " checking balance: ₹"
                            + balance
            );

            /*
             * Check whether enough money exists.
             */
            if (balance >= amount) {

                /*
                 * Artificial delay.
                 *
                 * This gives another thread a chance to read
                 * the same balance before we update it.
                 */
                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    return;
                }

                /*
                 * Modify shared state.
                 */
                balance -= amount;

                System.out.println(
                        atmName
                                + " withdrew ₹"
                                + amount
                                + " | Remaining balance: ₹"
                                + balance
                );

            } else {

                System.out.println(
                        atmName
                                + " failed: Insufficient balance."
                );
            }
        }

        public int getBalance() {

            return balance;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * ONE account.
         *
         * Both ATM threads access this same object.
         */
        BankAccount account =
                new BankAccount();

        /*
         * ATM 1.
         */
        Thread atm1 =
                new Thread(
                        () -> account.withdraw(
                                700,
                                "ATM-1"
                        )
                );

        /*
         * ATM 2.
         */
        Thread atm2 =
                new Thread(
                        () -> account.withdraw(
                                700,
                                "ATM-2"
                        )
                );

        atm1.setName("ATM-1");
        atm2.setName("ATM-2");

        /*
         * Start both ATMs.
         */
        atm1.start();
        atm2.start();

        /*
         * Wait until both ATM transactions finish.
         */
        atm1.join();
        atm2.join();

        /*
         * Display final balance.
         */
        System.out.println(
                "Final balance: ₹"
                        + account.getBalance()
        );
    }
}
