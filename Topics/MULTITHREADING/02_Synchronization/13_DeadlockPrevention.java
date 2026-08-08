/*
 * ============================================================
 * 13 - DEADLOCK PREVENTION USING LOCK ORDERING
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Program 12 intentionally created a deadlock.
 *
 * We had two accounts:
 *
 *     Account-A
 *     Account-B
 *
 * And two transfers:
 *
 *     Thread-1: A -> B
 *     Thread-2: B -> A
 *
 * The problem was that each thread acquired the locks in a
 * different order.
 *
 *
 * Thread-1:
 *
 *     Lock A
 *     Lock B
 *
 *
 * Thread-2:
 *
 *     Lock B
 *     Lock A
 *
 *
 * This created:
 *
 *     Thread-1 waits for B
 *     Thread-2 waits for A
 *
 *              ↓
 *
 *           DEADLOCK
 *
 *
 * ------------------------------------------------------------
 * GOAL
 * ------------------------------------------------------------
 *
 * Make the bank transfer system safe so that:
 *
 *     A -> B
 *
 * and
 *
 *     B -> A
 *
 * can execute concurrently without creating a deadlock.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use CONSISTENT LOCK ORDERING.
 *
 * We assign every account a unique ID.
 *
 * Example:
 *
 *     Account-A → ID 1
 *     Account-B → ID 2
 *
 *
 * Whenever a transaction needs both locks, it ALWAYS acquires
 * the lock with the smaller ID first.
 *
 *
 * Therefore:
 *
 *     A -> B
 *
 * locks:
 *
 *     A first
 *     B second
 *
 *
 * And:
 *
 *     B -> A
 *
 * ALSO locks:
 *
 *     A first
 *     B second
 *
 *
 * ------------------------------------------------------------
 * VISUALIZATION
 * ------------------------------------------------------------
 *
 * WITHOUT ORDERING:
 *
 *     Thread-1             Thread-2
 *
 *     Lock A               Lock B
 *       ↓                    ↓
 *     Wait B               Wait A
 *
 *            ↓
 *
 *         DEADLOCK
 *
 *
 * WITH ORDERING:
 *
 *     Thread-1             Thread-2
 *
 *     Lock A               Lock A
 *       ↓                    ↓
 *
 *     Wait for A           Wait for A
 *
 *                            ↓
 *                       Gets A later
 *
 *     Lock B
 *
 *     Transfer
 *
 *     Unlock B
 *     Unlock A
 *
 *
 * Only one thread can acquire A first.
 *
 * Therefore, the circular waiting condition is eliminated.
 *
 *
 * ------------------------------------------------------------
 * WHY DOES THIS PREVENT DEADLOCK?
 * ------------------------------------------------------------
 *
 * Deadlock requires a circular wait.
 *
 * With a global lock ordering:
 *
 *     A < B
 *
 * every thread must acquire:
 *
 *     A before B
 *
 * Therefore, no thread can hold B while waiting for A.
 *
 * The circular dependency cannot form.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Lock ordering is one common deadlock-prevention technique.
 *
 * Other techniques include:
 *
 *     tryLock()
 *     Timed lock acquisition
 *     Avoiding nested locks
 *     Reducing lock scope
 *     Lock hierarchy
 *
 *
 * We will learn tryLock() later when we study ReentrantLock.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove the account ordering logic.
 *
 * Make the program lock:
 *
 *     from
 *     then to
 *
 * just like Program 12.
 *
 * Run it repeatedly.
 *
 * You may reproduce the deadlock.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Add a third account:
 *
 *     Account-C
 *
 * Perform transfers:
 *
 *     A -> B
 *     B -> C
 *     C -> A
 *
 * Make sure ALL transfers follow the same ordering rule.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add many concurrent transfers.
 *
 * Example:
 *
 *     100 threads
 *
 * with random source/destination accounts.
 *
 * The application should still avoid lock-order deadlock.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. How can deadlock be prevented?
 *
 * 2. What is lock ordering?
 *
 * 3. Why does consistent lock ordering prevent circular wait?
 *
 * 4. What is the difference between deadlock prevention and
 *    deadlock detection?
 *
 * 5. What is another way to avoid deadlock when using
 *    ReentrantLock?
 *
 * Answer:
 *
 *     tryLock()
 *
 * 6. Why is nested locking dangerous?
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * When multiple locks are required:
 *
 *     Define a global ordering.
 *
 * Then ALWAYS acquire locks in that order.
 *
 *
 * Example:
 *
 *     Account A < Account B < Account C
 *
 * Every thread must acquire:
 *
 *     A before B
 *     A before C
 *     B before C
 *
 *
 * This prevents circular waiting.
 *
 *
 * ============================================================
 */

public class DeadlockPrevention {

    static class BankAccount {

        private final int id;
        private final String name;
        private int balance;

        BankAccount(
                int id,
                String name,
                int balance) {

            this.id = id;
            this.name = name;
            this.balance = balance;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

        public void deposit(int amount) {
            balance += amount;
        }
    }

    static class Bank {

        public void transfer(
                BankAccount from,
                BankAccount to,
                int amount) {

            /*
             * Determine the lock order.
             *
             * The account with the smaller ID MUST always
             * be locked first.
             */
            BankAccount firstLock;
            BankAccount secondLock;

            if (from.getId() < to.getId()) {

                firstLock = from;
                secondLock = to;

            } else {

                firstLock = to;
                secondLock = from;
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " wants to transfer ₹"
                            + amount
                            + " from "
                            + from.getName()
                            + " to "
                            + to.getName()
            );

            /*
             * ALWAYS acquire locks in the same global order.
             */
            synchronized (firstLock) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " locked "
                                + firstLock.getName()
                );

                /*
                 * Small delay so concurrent execution is easier
                 * to observe.
                 */
                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    return;
                }

                synchronized (secondLock) {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " locked "
                                    + secondLock.getName()
                    );

                    /*
                     * Perform the transfer.
                     */
                    from.withdraw(amount);

                    to.deposit(amount);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " transfer completed."
                    );
                }
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " released locks."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Each account has a unique ID.
         *
         * The ID establishes the global lock order.
         */
        BankAccount accountA =
                new BankAccount(
                        1,
                        "Account-A",
                        1000
                );

        BankAccount accountB =
                new BankAccount(
                        2,
                        "Account-B",
                        1000
                );

        Bank bank =
                new Bank();

        /*
         * Transfer 1:
         *
         * A -> B
         *
         * Required lock order:
         *
         *     A → B
         */
        Thread transfer1 =
                new Thread(
                        () -> bank.transfer(
                                accountA,
                                accountB,
                                100
                        ),
                        "Transfer-A-to-B"
                );

        /*
         * Transfer 2:
         *
         * B -> A
         *
         * Even though the transfer direction is reversed,
         * the LOCK order will still be:
         *
         *     A → B
         *
         * because A has the smaller ID.
         */
        Thread transfer2 =
                new Thread(
                        () -> bank.transfer(
                                accountB,
                                accountA,
                                200
                        ),
                        "Transfer-B-to-A"
                );

        /*
         * Start both transfers.
         */
        transfer1.start();
        transfer2.start();

        /*
         * Wait for both transfers to finish.
         */
        transfer1.join();
        transfer2.join();

        /*
         * If the locking strategy is correct, the program
         * reaches this point.
         */
        System.out.println(
                "Both transfers completed successfully."
        );

        System.out.println(
                accountA.getName()
                        + " balance: ₹"
                        + accountA.getBalance()
        );

        System.out.println(
                accountB.getName()
                        + " balance: ₹"
                        + accountB.getBalance()
        );

        /*
         * Total money should remain:
         *
         *     ₹1000 + ₹1000 = ₹2000
         *
         * regardless of how the transfers are scheduled.
         */
        System.out.println(
                "Total balance: ₹"
                        + (
                        accountA.getBalance()
                                + accountB.getBalance()
                )
        );
    }
}
