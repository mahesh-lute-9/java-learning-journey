/*
 * ============================================================
 * 25 - THREAD-SAFE COUNTER USING AtomicInteger
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an analytics service tracking the number of API
 * requests received by a server.
 *
 * Every request is handled by a different thread.
 *
 * Example:
 *
 *     Request-1 → counter++
 *     Request-2 → counter++
 *     Request-3 → counter++
 *     ...
 *
 * Suppose 100 threads each process 10,000 requests.
 *
 * Expected:
 *
 *     100 × 10,000 = 1,000,000
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * A normal int is NOT enough to make:
 *
 *     counter++
 *
 * thread-safe.
 *
 *
 * Remember:
 *
 *     counter++
 *
 * is effectively:
 *
 *     1. Read counter
 *     2. Add 1
 *     3. Write counter
 *
 *
 * Two threads can interleave:
 *
 *
 * Thread A                 Thread B
 * --------                 --------
 *
 * Read 100
 *                         Read 100
 *
 * Add 1
 *                         Add 1
 *
 * Write 101
 *                         Write 101
 *
 *
 * Expected:
 *
 *     102
 *
 * Actual:
 *
 *     101
 *
 *
 * This is a LOST UPDATE.
 *
 *
 * ------------------------------------------------------------
 * TRADITIONAL SOLUTION
 * ------------------------------------------------------------
 *
 * We could use:
 *
 *     synchronized
 *
 *
 * Example:
 *
 *     synchronized void increment() {
 *         counter++;
 *     }
 *
 *
 * This works.
 *
 *
 * But Java also provides atomic classes for simple atomic
 * operations.
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
 *
 * Example:
 *
 *     AtomicInteger counter =
 *             new AtomicInteger(0);
 *
 *
 * Increment:
 *
 *     counter.incrementAndGet();
 *
 *
 * Read:
 *
 *     counter.get();
 *
 *
 * ------------------------------------------------------------
 * HOW DOES AtomicInteger WORK?
 * ------------------------------------------------------------
 *
 * AtomicInteger uses low-level atomic operations provided by
 * the JVM/hardware, commonly based on:
 *
 *     CAS
 *
 * CAS = Compare-And-Set / Compare-And-Swap style operation.
 *
 *
 * Conceptually:
 *
 *
 *     Current value = 100
 *
 *     "Change 100 → 101 only if the current value is still 100."
 *
 *
 * If another thread changed it first:
 *
 *     Retry.
 *
 *
 * This avoids using a traditional monitor lock for these
 * operations.
 *
 *
 * ------------------------------------------------------------
 * CAS CONCEPT
 * ------------------------------------------------------------
 *
 * Conceptually:
 *
 *
 *     expected = 100
 *     newValue = 101
 *
 *     if (actual == expected)
 *         actual = newValue;
 *     else
 *         retry;
 *
 *
 * The comparison and update happen atomically.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Atomic classes are NOT magic replacements for synchronized.
 *
 * AtomicInteger is excellent for operations such as:
 *
 *     increment
 *     decrement
 *     add
 *     compare-and-set
 *     simple counters
 *
 *
 * But if you need to protect a larger multi-step business
 * operation involving several variables, a lock may still be
 * appropriate.
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * AtomicInteger is good for:
 *
 *     requestCount.incrementAndGet();
 *
 *
 * But consider:
 *
 *     if (balance >= amount) {
 *         balance -= amount;
 *         transactionCount++;
 *     }
 *
 *
 * Multiple related pieces of state may require a stronger
 * synchronization strategy.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace AtomicInteger with:
 *
 *     int counter = 0;
 *
 * and increment it from multiple threads.
 *
 * Observe whether the final result is always correct.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace AtomicInteger with:
 *
 *     synchronized
 *
 * Compare both approaches.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Try:
 *
 *     incrementAndGet()
 *     getAndIncrement()
 *
 *
 * Understand the difference:
 *
 *
 * incrementAndGet():
 *
 *     increment first
 *     return new value
 *
 *
 * getAndIncrement():
 *
 *     return old value
 *     then increment
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Try:
 *
 *     compareAndSet()
 *
 * to implement a conditional update.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is AtomicInteger?
 *
 * 2. Why is int++ not atomic?
 *
 * 3. What is CAS?
 *
 * 4. Does AtomicInteger use synchronized?
 *
 * Atomic classes are designed around atomic CPU/JVM
 * primitives such as compare-and-set rather than requiring
 * a traditional synchronized block for each operation.
 *
 *
 * 5. Difference between:
 *
 *     incrementAndGet()
 *
 * and:
 *
 *     getAndIncrement()
 *
 * 6. When should you prefer AtomicInteger?
 *
 * For simple independent atomic state updates such as counters.
 *
 * 7. When is synchronized/Lock better?
 *
 * When multiple operations or multiple variables must change
 * together as one atomic business operation.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * For a simple shared counter:
 *
 *
 *     int
 *       ↓
 *     race condition
 *
 *
 *     synchronized
 *       ↓
 *     thread-safe
 *
 *
 *     AtomicInteger
 *       ↓
 *     thread-safe atomic operations
 *
 *
 * Think:
 *
 *     Simple atomic state → Atomic classes
 *
 *     Complex critical section → Lock/synchronized
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 26:
 *
 *     AtomicInventory.java
 *
 * We will take CAS one step further and implement a limited
 * inventory where a purchase should succeed ONLY if enough
 * stock is available.
 *
 * ============================================================
 */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    /*
     * Thread-safe counter.
     */
    private static final AtomicInteger requestCount =
            new AtomicInteger(0);

    /*
     * Number of worker threads.
     */
    private static final int NUMBER_OF_THREADS = 100;

    /*
     * Requests processed by each worker.
     */
    private static final int REQUESTS_PER_THREAD = 10_000;

    static class RequestWorker implements Runnable {

        @Override
        public void run() {

            /*
             * Simulate processing many requests.
             */
            for (int i = 0;
                 i < REQUESTS_PER_THREAD;
                 i++) {

                /*
                 * Atomic increment.
                 *
                 * Multiple threads can safely execute this.
                 */
                requestCount.incrementAndGet();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread[] workers =
                new Thread[NUMBER_OF_THREADS];

        /*
         * Create worker threads.
         */
        for (int i = 0;
             i < NUMBER_OF_THREADS;
             i++) {

            workers[i] =
                    new Thread(
                            new RequestWorker(),
                            "Worker-" + (i + 1)
                    );
        }

        /*
         * Start all workers.
         */
        for (Thread worker : workers) {

            worker.start();
        }

        /*
         * Wait for all workers to finish.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        /*
         * Calculate expected result.
         */
        int expected =
                NUMBER_OF_THREADS
                        * REQUESTS_PER_THREAD;

        /*
         * Read the final atomic value.
         */
        int actual =
                requestCount.get();

        System.out.println(
                "Expected requests: "
                        + expected
        );

        System.out.println(
                "Actual requests:   "
                        + actual
        );

        System.out.println(
                "Counter correct:   "
                        + (expected == actual)
        );
    }
}
