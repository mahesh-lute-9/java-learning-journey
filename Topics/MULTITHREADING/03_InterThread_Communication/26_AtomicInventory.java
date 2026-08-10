/*
 * ============================================================
 * 26 - ATOMIC INVENTORY USING compareAndSet()
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce website selling a limited-edition
 * product.
 *
 * Example:
 *
 *     Product: Limited Edition Phone
 *     Stock:   10
 *
 * Suddenly 100 customers try to buy it simultaneously.
 *
 * REQUIREMENT:
 *
 *     A purchase should succeed ONLY if enough stock exists.
 *
 *
 * ------------------------------------------------------------
 * THE DANGER
 * ------------------------------------------------------------
 *
 * A naive implementation might be:
 *
 *
 *     if (stock > 0) {
 *
 *         stock--;
 *
 *         return true;
 *     }
 *
 *
 * This looks correct.
 *
 * But with multiple threads:
 *
 *
 * Thread A                 Thread B
 * --------                 --------
 *
 * Check stock = 1
 *                         Check stock = 1
 *
 * stock--
 *                         stock--
 *
 *
 * Multiple customers may believe that they successfully
 * purchased the last item.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     AtomicInteger
 *
 * with:
 *
 *     compareAndSet()
 *
 *
 * ------------------------------------------------------------
 * WHAT IS compareAndSet()?
 * ------------------------------------------------------------
 *
 * Conceptually:
 *
 *
 *     compareAndSet(expected, newValue)
 *
 *
 * means:
 *
 *     "Change the value to newValue ONLY IF the current value
 *      is still expected."
 *
 *
 * Example:
 *
 *     Current stock = 5
 *
 *     compareAndSet(5, 4)
 *
 *
 * If the stock is still 5:
 *
 *     5 → 4
 *
 * succeeds.
 *
 *
 * But if another thread already changed it:
 *
 *     Current stock = 4
 *
 * then:
 *
 *     compareAndSet(5, 4)
 *
 * fails.
 *
 *
 * The thread can then retry using the latest value.
 *
 *
 * ------------------------------------------------------------
 * CAS LOOP
 * ------------------------------------------------------------
 *
 * A common pattern is:
 *
 *
 *     while (true) {
 *
 *         current = stock.get();
 *
 *         if (current <= 0)
 *             return false;
 *
 *         if (stock.compareAndSet(
 *                 current,
 *                 current - 1))
 *             return true;
 *     }
 *
 *
 * ------------------------------------------------------------
 * WHY RETRY?
 * ------------------------------------------------------------
 *
 * Imagine:
 *
 *     Stock = 1
 *
 *
 * Thread A:
 *
 *     reads 1
 *
 *
 * Thread B:
 *
 *     reads 1
 *
 *
 * Thread A:
 *
 *     CAS(1 → 0)
 *
 *     SUCCESS
 *
 *
 * Thread B:
 *
 *     CAS(1 → 0)
 *
 *     FAIL
 *
 *
 * Thread B retries:
 *
 *     reads 0
 *
 *     stock <= 0
 *
 *     purchase FAILS
 *
 *
 * Therefore only ONE customer succeeds.
 *
 *
 * ------------------------------------------------------------
 * WHY THIS IS USEFUL
 * ------------------------------------------------------------
 *
 * This is a classic concurrency pattern:
 *
 *     Read
 *       ↓
 *     Validate
 *       ↓
 *     CAS
 *       ↓
 *     Retry if another thread changed the value
 *
 *
 * This can avoid traditional locking for certain simple state
 * transitions.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * CAS is not automatically better than synchronized.
 *
 * CAS is particularly useful for:
 *
 *     Small independent state
 *     Counters
 *     Flags
 *     State machines
 *     Lock-free data structures
 *
 *
 * Complex operations involving multiple shared variables can
 * become much harder to implement correctly using CAS.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change stock from:
 *
 *     10
 *
 * to:
 *
 *     1
 *
 * and create 100 buyers.
 *
 * Expected:
 *
 *     Exactly ONE successful purchase.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Remove compareAndSet() and use:
 *
 *     get()
 *     decrementAndGet()
 *
 * separately.
 *
 * Think about why this creates a race condition.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add:
 *
 *     AtomicInteger successfulPurchases
 *
 * and count successful orders.
 *
 *
 * Verify:
 *
 *     successfulPurchases == initialStock
 *
 * when enough buyers are present.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add a "restock" operation.
 *
 * Think carefully about whether:
 *
 *     stock.addAndGet(quantity)
 *
 * is sufficient for your business rule.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is CAS?
 *
 * 2. Why does compareAndSet() prevent the lost-update problem?
 *
 * 3. Why is a CAS loop necessary?
 *
 * 4. What happens when CAS fails?
 *
 * 5. Is CAS lock-free?
 *
 * CAS-based algorithms can be lock-free, but whether a complete
 * algorithm is lock-free depends on its implementation.
 *
 * 6. When is CAS preferable to synchronized?
 *
 * For suitable small atomic state transitions where avoiding
 * blocking is beneficial.
 *
 * 7. What is the ABA problem?
 *
 * It occurs in some CAS-based algorithms when a value changes:
 *
 *     A → B → A
 *
 * and another thread incorrectly assumes nothing important
 * changed because it only observes A before and after.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Remember the CAS pattern:
 *
 *
 *     Read current value
 *            ↓
 *     Check business condition
 *            ↓
 *     Attempt CAS
 *            ↓
 *       +----+----+
 *       |         |
 *    success    failure
 *       |         |
 *       v         v
 *    continue    retry
 *
 *
 * This is one of the foundations behind lock-free
 * programming.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 27:
 *
 *     VolatileFlag.java
 *
 * We will solve a different problem:
 *
 *     One thread changes a shutdown flag and another thread
 *     must reliably see that change.
 *
 * This introduces the Java Memory Model concept of
 * VISIBILITY and the volatile keyword.
 *
 * ============================================================
 */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInventory {

    /*
     * Initial inventory.
     */
    private static final int INITIAL_STOCK = 10;

    /*
     * Atomic shared stock.
     */
    private static final AtomicInteger stock =
            new AtomicInteger(INITIAL_STOCK);

    /*
     * Count successful purchases.
     */
    private static final AtomicInteger successfulPurchases =
            new AtomicInteger(0);

    static class Customer implements Runnable {

        private final int customerId;

        Customer(int customerId) {

            this.customerId =
                    customerId;
        }

        @Override
        public void run() {

            /*
             * Try to purchase one item.
             */
            boolean purchased =
                    purchase();

            if (purchased) {

                System.out.println(
                        "Customer-"
                                + customerId
                                + " successfully purchased."
                );

            } else {

                System.out.println(
                        "Customer-"
                                + customerId
                                + " failed. Out of stock."
                );
            }
        }

        private boolean purchase() {

            /*
             * CAS retry loop.
             */
            while (true) {

                /*
                 * Read the current stock.
                 */
                int currentStock =
                        stock.get();

                /*
                 * No inventory available.
                 */
                if (currentStock <= 0) {

                    return false;
                }

                /*
                 * Try to atomically change:
                 *
                 *     currentStock
                 *
                 * to:
                 *
                 *     currentStock - 1
                 */
                boolean updated =
                        stock.compareAndSet(
                                currentStock,
                                currentStock - 1
                        );

                /*
                 * If CAS succeeded, this customer owns the
                 * inventory item.
                 */
                if (updated) {

                    successfulPurchases
                            .incrementAndGet();

                    return true;
                }

                /*
                 * CAS failed.
                 *
                 * Another thread changed the stock between our
                 * get() and compareAndSet().
                 *
                 * Retry with the latest value.
                 */
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create more customers than available stock.
         */
        int numberOfCustomers = 100;

        Thread[] customers =
                new Thread[numberOfCustomers];

        /*
         * Create customer threads.
         */
        for (int i = 0;
             i < numberOfCustomers;
             i++) {

            customers[i] =
                    new Thread(
                            new Customer(i + 1),
                            "Customer-" + (i + 1)
                    );
        }

        /*
         * Start all customers.
         */
        for (Thread customer : customers) {

            customer.start();
        }

        /*
         * Wait for all customers.
         */
        for (Thread customer : customers) {

            customer.join();
        }

        /*
         * Final inventory should be zero.
         */
        System.out.println(
                "\nInitial stock: "
                        + INITIAL_STOCK
        );

        System.out.println(
                "Successful purchases: "
                        + successfulPurchases.get()
        );

        System.out.println(
                "Remaining stock: "
                        + stock.get()
        );

        /*
         * The important invariant:
         *
         * successful purchases + remaining stock
         *
         * should equal initial stock.
         */
        System.out.println(
                "Inventory consistent: "
                        + (
                        successfulPurchases.get()
                                + stock.get()
                                == INITIAL_STOCK
                )
        );
    }
}
