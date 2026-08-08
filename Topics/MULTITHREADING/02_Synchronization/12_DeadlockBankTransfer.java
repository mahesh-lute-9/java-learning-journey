/*
 * ============================================================
 * 12 - DEADLOCK: BANK TRANSFER
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a banking system with two accounts:
 *
 *     Account-A
 *     Account-B
 *
 * Two transfers happen at the same time:
 *
 *     Transfer 1:
 *         A → B
 *
 *     Transfer 2:
 *         B → A
 *
 * To safely transfer money, each transaction needs to lock
 * both accounts.
 *
 *
 * ------------------------------------------------------------
 * THE PROBLEM
 * ------------------------------------------------------------
 *
 * Consider this execution:
 *
 *
 * Thread-1                     Thread-2
 * --------                     --------
 *
 * Lock Account-A
 *                              Lock Account-B
 *
 * Try to lock Account-B
 *                              Try to lock Account-A
 *
 * Waiting...                   Waiting...
 *
 *
 * Thread-1 is waiting for B.
 *
 * Thread-2 is waiting for A.
 *
 * Neither can continue.
 *
 *
 * This is a DEADLOCK.
 *
 *
 * ------------------------------------------------------------
 * WHAT IS DEADLOCK?
 * ------------------------------------------------------------
 *
 * Deadlock occurs when two or more threads are permanently
 * waiting for resources held by each other.
 *
 *
 * Classic example:
 *
 *
 *     Thread A
 *        |
 *        | owns Lock 1
 *        |
 *        | waits for
 *        v
 *     Lock 2
 *
 *     Thread B
 *        |
 *        | owns Lock 2
 *        |
 *        | waits for
 *        v
 *     Lock 1
 *
 *
 * Nobody can proceed.
 *
 *
 * ------------------------------------------------------------
 * FOUR CONDITIONS OF DEADLOCK
 * ------------------------------------------------------------
 *
 * Deadlock can occur when these four conditions exist:
 *
 *
 * 1. MUTUAL EXCLUSION
 *
 * A resource can be held by only one thread at a time.
 *
 *
 * 2. HOLD AND WAIT
 *
 * A thread holds one resource while waiting for another.
 *
 *
 * 3. NO PREEMPTION
 *
 * A resource cannot simply be forcibly taken away from the
 * thread holding it.
 *
 *
 * 4. CIRCULAR WAIT
 *
 * Thread A waits for Thread B.
 *
 * Thread B waits for Thread A.
 *
 *
 * ------------------------------------------------------------
 * WHY THIS PROGRAM MAY APPEAR TO "FREEZE"
 * ------------------------------------------------------------
 *
 * We intentionally create the deadlock.
 *
 * Therefore, the program may never reach:
 *
 *     "Transfers completed."
 *
 * The Java process may remain alive because both worker threads
 * are blocked waiting for locks.
 *
 *
 * If the program appears stuck, THAT IS THE EXPECTED RESULT.
 *
 *
 * To terminate it in WSL:
 *
 *     Ctrl + C
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * This program is intentionally BROKEN.
 *
 * Do not try to "fix" it yet.
 *
 * First understand exactly why it deadlocks.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the order of lock acquisition so both transfers
 * acquire the accounts in the SAME order.
 *
 * Example:
 *
 *     Always lock Account-A first.
 *     Then lock Account-B.
 *
 * Ask:
 *
 *     Does the deadlock disappear?
 *
 *
 * Don't permanently change the program yet.
 *
 * We'll implement the proper solution in Program 13.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Remove the Thread.sleep() statements.
 *
 * The deadlock may become harder to reproduce because timing
 * changes.
 *
 * This demonstrates an important fact:
 *
 *     A deadlock can depend on thread scheduling.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add logging before and after every lock acquisition.
 *
 * Example:
 *
 *     "Thread-1 waiting for Account-A"
 *     "Thread-1 acquired Account-A"
 *
 * This is a useful debugging technique for concurrency issues.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is deadlock?
 *
 * 2. What are the four necessary conditions for deadlock?
 *
 * 3. Why does this bank transfer program deadlock?
 *
 * 4. How can deadlock be prevented?
 *
 * 5. Can deadlock occur with only one lock?
 *
 * A classic lock-order deadlock requires multiple resources,
 * though other forms of thread waiting can cause hangs.
 *
 * 6. Is deadlock the same as starvation?
 *
 * No.
 *
 * Deadlock:
 *     Threads wait for each other and cannot progress.
 *
 * Starvation:
 *     A thread keeps getting denied access to resources or CPU
 *     while other threads continue making progress.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * The dangerous pattern is:
 *
 *
 *     Thread A:
 *
 *         Lock A
 *         ↓
 *         Wait for B
 *
 *
 *     Thread B:
 *
 *         Lock B
 *         ↓
 *         Wait for A
 *
 *
 *              ↓
 *
 *           DEADLOCK
 *
 *
 * The most common simple prevention technique is:
 *
 *     ALWAYS ACQUIRE MULTIPLE LOCKS IN A CONSISTENT ORDER.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 13:
 *
 *     DeadlockPrevention.java
 *
 * We will fix this exact banking problem using consistent
 * lock ordering.
 *
 * ============================================================
 */

public class DeadlockBankTransfer {

    static class BankAccount {

        private final String name;
        private int balance;

        BankAccount(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        /*
         * Deposit and withdrawal are not the focus of this
         * example.
         *
         * The focus is lock acquisition.
         */
        public void deposit(int amount) {

            balance += amount;
        }

        public void withdraw(int amount) {

            balance -= amount;
        }
    }

    static class Bank {

        /*
         * This method intentionally acquires locks in the
         * order in which the accounts are passed.
         *
         * That is what creates the deadlock possibility.
         */
        public void transfer(
                BankAccount from,
                BankAccount to,
                int amount) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " attempting transfer "
                            + from.getName()
                            + " -> "
                            + to.getName()
            );

            /*
             * Lock the SOURCE account first.
             */
            synchronized (from) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " locked "
                                + from.getName()
                );

                /*
                 * Artificial delay.
                 *
                 * This gives the other thread a chance to
                 * acquire the other account's lock.
                 */
                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    return;
                }

                /*
                 * Now try to lock the DESTINATION account.
                 */
                synchronized (to) {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " locked "
                                    + to.getName()
                    );

                    /*
                     * Perform transfer.
                     */
                    from.withdraw(amount);

                    to.deposit(amount);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " transfer completed."
                    );
                }
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create two bank accounts.
         */
        BankAccount accountA =
                new BankAccount(
                        "Account-A",
                        1000
                );

        BankAccount accountB =
                new BankAccount(
                        "Account-B",
                        1000
                );

        Bank bank =
                new Bank();

        /*
         * Thread 1:
         *
         * A -> B
         *
         * It will lock A first, then try B.
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
         * Thread 2:
         *
         * B -> A
         *
         * It will lock B first, then try A.
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
         * IMPORTANT:
         *
         * These join() calls will also wait forever if the
         * deadlock occurs.
         *
         * Therefore, the program may appear frozen here.
         */
        transfer1.join();

        transfer2.join();

        /*
         * Normally this line will NOT be reached when the
         * deadlock occurs.
         */
        System.out.println(
                "Both transfers completed."
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
    }
}
