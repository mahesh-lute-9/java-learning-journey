/*
 * ============================================================
 * 50 - ATOMIC VARIABLES AND THE count++ PROBLEM
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an application tracking the number of API requests.
 *
 * Every request is handled by a different thread.
 *
 * We want:
 *
 *     requestCount++
 *
 *
 * Example:
 *
 *     Thread 1 → request
 *     Thread 2 → request
 *     Thread 3 → request
 *     ...
 *
 *
 * After 10,000 requests:
 *
 *     Expected count = 10,000
 *
 *
 * But using a normal int with multiple threads can produce a
 * smaller value.
 *
 *
 * ------------------------------------------------------------
 * WHY IS count++ NOT SAFE?
 * ------------------------------------------------------------
 *
 * This:
 *
 *     count++;
 *
 * looks like one operation.
 *
 * But conceptually it is:
 *
 *     1. READ count
 *     2. ADD 1
 *     3. WRITE count
 *
 *
 * So:
 *
 *
 * Thread A                  Thread B
 * --------                  --------
 * read 10
 *                          read 10
 * add 1
 *                          add 1
 * write 11
 *                          write 11
 *
 *
 * Expected:
 *
 *     12
 *
 *
 * Actual:
 *
 *     11
 *
 *
 * This is a RACE CONDITION.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Java provides atomic classes in:
 *
 *     java.util.concurrent.atomic
 *
 *
 * Common classes include:
 *
 *     AtomicInteger
 *     AtomicLong
 *     AtomicBoolean
 *     AtomicReference
 *
 *
 * For our counter:
 *
 *     AtomicInteger
 *
 *
 * ------------------------------------------------------------
 * AtomicInteger
 * ------------------------------------------------------------
 *
 * Instead of:
 *
 *     int count;
 *
 *
 * use:
 *
 *     AtomicInteger count =
 *         new AtomicInteger();
 *
 *
 * Then:
 *
 *     count.incrementAndGet();
 *
 *
 * performs an atomic increment.
 *
 *
 * ------------------------------------------------------------
 * USEFUL AtomicInteger METHODS
 * ------------------------------------------------------------
 *
 *
 * get()
 *
 *     Read the current value.
 *
 *
 * incrementAndGet()
 *
 *     Increment and return the new value.
 *
 *
 * getAndIncrement()
 *
 *     Return the old value, then increment.
 *
 *
 * decrementAndGet()
 *
 *     Decrement and return the new value.
 *
 *
 * addAndGet(value)
 *
 *     Add a value and return the new result.
 *
 *
 * compareAndSet(expected, update)
 *
 *     Update only if the current value equals expected.
 *
 *
 * ------------------------------------------------------------
 * compareAndSet()
 * ------------------------------------------------------------
 *
 * This is based on the idea of:
 *
 *     CAS
 *
 *     Compare And Set
 *
 *
 * Conceptually:
 *
 *
 *     Current value = 10
 *
 *     compareAndSet(10, 20)
 *
 *
 * means:
 *
 *     "If the current value is still 10,
 *      change it to 20."
 *
 *
 * Otherwise:
 *
 *     Do nothing.
 *
 *
 * This is a fundamental building block for many lock-free and
 * concurrent algorithms.
 *
 *
 * ------------------------------------------------------------
 * ATOMIC DOES NOT MEAN EVERYTHING IS THREAD-SAFE
 * ------------------------------------------------------------
 *
 * AtomicInteger makes individual atomic operations safe.
 *
 *
 * But this sequence:
 *
 *
 *     if (count.get() < 100) {
 *
 *         count.incrementAndGet();
 *     }
 *
 *
 * may still have a concurrency problem because the CHECK and
 * UPDATE are separate operations.
 *
 *
 * This distinction is extremely important.
 *
 *
 * ------------------------------------------------------------
 * ATOMIC VS synchronized
 * ------------------------------------------------------------
 *
 *
 * AtomicInteger:
 *
 *     Good for simple atomic state updates.
 *
 *
 * synchronized:
 *
 *     Useful when multiple operations must be treated as one
 *     indivisible critical section.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Request counters
 *     Retry counters
 *     Metrics
 *     Sequence numbers
 *     Reference counters
 *     Simple shared state
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace AtomicInteger with:
 *
 *     static int counter = 0;
 *
 *
 * Then use:
 *
 *     counter++;
 *
 *
 * Run the program multiple times.
 *
 *
 * You may observe:
 *
 *     Expected: 100000
 *     Actual:   less than 100000
 *
 *
 * The exact result can vary.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     THREAD_COUNT
 *
 * and:
 *
 *     INCREMENTS_PER_THREAD
 *
 *
 * Observe the difference.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     incrementAndGet()
 *
 * with:
 *
 *     getAndIncrement()
 *
 *
 * Understand the difference in the returned value.
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
 * to implement your own conditional counter update.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is count++ not atomic?
 *
 * Because it consists of read, modify, and write operations.
 *
 *
 * 2. What is AtomicInteger?
 *
 * A class that provides atomic operations on an int value.
 *
 *
 * 3. Difference between incrementAndGet() and
 *    getAndIncrement()?
 *
 * incrementAndGet():
 *     increment first, return new value.
 *
 * getAndIncrement():
 *     return old value, then increment.
 *
 *
 * 4. What is CAS?
 *
 * Compare-And-Set.
 *
 *
 * 5. Is AtomicInteger always better than synchronized?
 *
 * No.
 *
 * It depends on the problem.
 *
 *
 * 6. Can multiple AtomicInteger operations together still have
 *    a race condition?
 *
 * Yes.
 *
 * Individual atomic operations do not automatically make a
 * multi-operation sequence atomic.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * int
 *     +
 * count++
 *     +
 * multiple threads
 *     =
 * race condition
 *
 *
 * AtomicInteger
 *     +
 * incrementAndGet()
 *     =
 * atomic increment
 *
 *
 * Remember:
 *
 *
 *     Atomic operation
 *          ≠
 *     entire algorithm is automatically thread-safe
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 51:
 *
 *     AtomicCompareAndSetScenario.java
 *
 * We will go deeper into CAS and build a small lock-like
 * mechanism using AtomicBoolean.
 *
 * ============================================================
 */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterScenario {

    /*
     * Number of threads simulating concurrent API requests.
     */
    private static final int THREAD_COUNT = 10;

    /*
     * Number of requests generated by each thread.
     */
    private static final int REQUESTS_PER_THREAD = 10_000;

    /*
     * Thread-safe atomic counter.
     */
    private static final AtomicInteger requestCount =
            new AtomicInteger(0);

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Store worker threads.
         */
        Thread[] workers =
                new Thread[THREAD_COUNT];

        /*
         * Create concurrent request-processing threads.
         */
        for (int i = 0;
             i < THREAD_COUNT;
             i++) {

            workers[i] =
                    new Thread(
                            () -> {

                                /*
                                 * Simulate many API requests.
                                 */
                                for (int j = 0;
                                     j < REQUESTS_PER_THREAD;
                                     j++) {

                                    /*
                                     * Atomic increment.
                                     *
                                     * Multiple threads can safely
                                     * execute this operation.
                                     */
                                    requestCount
                                            .incrementAndGet();
                                }
                            },
                            "Request-Worker-"
                                    + (i + 1)
                    );

            workers[i].start();
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
                THREAD_COUNT
                        * REQUESTS_PER_THREAD;

        /*
         * Read the final atomic value.
         */
        int actual =
                requestCount.get();

        System.out.println(
                "========== REQUEST COUNTER =========="
        );

        System.out.println(
                "Expected: "
                        + expected
        );

        System.out.println(
                "Actual:   "
                        + actual
        );

        System.out.println(
                "======================================"
        );

        /*
         * Verify the result.
         */
        if (expected == actual) {

            System.out.println(
                    "Counter is correct."
            );

        } else {

            System.out.println(
                    "Counter is incorrect."
            );
        }
    }
}
