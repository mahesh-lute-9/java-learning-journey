/*
 * ============================================================
 * 51 - COMPARE-AND-SET (CAS) WITH AtomicBoolean
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine your application has a limited resource:
 *
 *     A report generator
 *
 * Only ONE thread is allowed to generate the report at a time.
 *
 *
 * Multiple threads may try:
 *
 *     Thread A → generate report
 *     Thread B → generate report
 *     Thread C → generate report
 *
 *
 * Requirement:
 *
 *     Exactly ONE thread gets access.
 *
 *
 * ------------------------------------------------------------
 * NAIVE APPROACH
 * ------------------------------------------------------------
 *
 * You might think:
 *
 *
 *     if (!busy) {
 *         busy = true;
 *         generateReport();
 *         busy = false;
 *     }
 *
 *
 * But this is NOT thread-safe.
 *
 *
 * Two threads could do:
 *
 *
 * Thread A:
 *     reads busy = false
 *
 * Thread B:
 *     reads busy = false
 *
 * Thread A:
 *     busy = true
 *
 * Thread B:
 *     busy = true
 *
 *
 * Both enter the critical section.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     AtomicBoolean
 *
 *
 * with:
 *
 *     compareAndSet()
 *
 *
 * ------------------------------------------------------------
 * compareAndSet()
 * ------------------------------------------------------------
 *
 * Example:
 *
 *
 *     busy.compareAndSet(
 *         false,
 *         true
 *     );
 *
 *
 * Means:
 *
 *     "If the current value is false,
 *      change it to true atomically."
 *
 *
 * The operation succeeds for only one thread.
 *
 *
 * ------------------------------------------------------------
 * WHY?
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     busy = false
 *
 *
 * Thread A:
 *
 *     CAS(false, true)
 *
 * succeeds.
 *
 *
 * Thread B:
 *
 *     CAS(false, true)
 *
 * fails because the value is already:
 *
 *     true
 *
 *
 * Therefore only Thread A enters.
 *
 *
 * ------------------------------------------------------------
 * CAS PATTERN
 * ------------------------------------------------------------
 *
 *
 *     current value?
 *           |
 *           v
 *        expected?
 *         /     \
 *       YES      NO
 *        |        |
 *      update    fail
 *
 *
 * This is called:
 *
 *     Compare-And-Set
 *
 *
 * ------------------------------------------------------------
 * WHY IS CAS IMPORTANT?
 * ------------------------------------------------------------
 *
 * CAS is a fundamental technique used in many concurrent
 * algorithms.
 *
 *
 * It allows threads to attempt an update without using a
 * traditional synchronized block for that individual state
 * transition.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * CAS does NOT magically make an entire multi-step operation
 * thread-safe.
 *
 *
 * Here we are atomically changing:
 *
 *     false → true
 *
 *
 * But everything that happens AFTER that still needs proper
 * lifecycle handling.
 *
 *
 * ------------------------------------------------------------
 * FINALLY
 * ------------------------------------------------------------
 *
 * Notice:
 *
 *
 *     if (acquired) {
 *
 *         try {
 *             generateReport();
 *         } finally {
 *             busy.set(false);
 *         }
 *     }
 *
 *
 * We MUST release the state even if report generation fails.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Initialization flags
 *     One-time startup operations
 *     Simple state machines
 *     Resource ownership
 *     Lock-free data structures
 *     Cache initialization
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     compareAndSet(false, true)
 *
 * with:
 *
 *     if (!busy.get()) {
 *         busy.set(true);
 *     }
 *
 *
 * Ask:
 *
 *     Why is this unsafe?
 *
 *
 * Because:
 *
 *     get()
 *
 * and:
 *
 *     set()
 *
 * are separate operations.
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
 * to:
 *
 *     100
 *
 *
 * Observe that still only one thread enters the critical
 * section at a time.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Throw an exception inside generateReport().
 *
 *
 * Verify that the state is reset because of finally.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is CAS?
 *
 * Compare-And-Set.
 *
 *
 * 2. What does compareAndSet(false, true) mean?
 *
 * Change false to true only if the current value is still
 * false.
 *
 *
 * 3. Why is get() followed by set() unsafe?
 *
 * Because another thread can change the value between those
 * two operations.
 *
 *
 * 4. What is AtomicBoolean?
 *
 * A class providing atomic operations on a boolean value.
 *
 *
 * 5. Is CAS always better than locks?
 *
 * No.
 *
 * The correct mechanism depends on the problem.
 *
 *
 * 6. What is a CAS failure?
 *
 * The expected value no longer matches the current value.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * UNSAFE:
 *
 *     if (!busy.get()) {
 *         busy.set(true);
 *     }
 *
 *
 * SAFE STATE TRANSITION:
 *
 *     busy.compareAndSet(
 *         false,
 *         true
 *     );
 *
 *
 * Remember:
 *
 *
 *     GET + SET
 *          ≠
 *     ATOMIC CHECK + UPDATE
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 52:
 *
 *     ReadWriteLockScenario.java
 *
 * We will move into advanced locks.
 *
 * Scenario:
 *
 *     Thousands of threads read configuration data, but only
 *     occasionally does one thread update it.
 *
 * We'll learn why ReadWriteLock can be better than a normal
 * exclusive lock for read-heavy workloads.
 *
 * ============================================================
 */

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicCompareAndSetScenario {

    /*
     * Represents whether the report generator is currently busy.
     *
     * false = available
     * true  = occupied
     */
    private static final AtomicBoolean busy =
            new AtomicBoolean(false);

    /*
     * Number of threads attempting to generate the report.
     */
    private static final int THREAD_COUNT = 10;

    /*
     * Simulate expensive report generation.
     */
    private static void generateReport(
            String threadName) {

        System.out.println(
                threadName
                        + " is generating the report..."
        );

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    threadName
                            + " was interrupted."
            );

            return;
        }

        System.out.println(
                threadName
                        + " finished generating the report."
        );
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Store all worker threads.
         */
        Thread[] workers =
                new Thread[THREAD_COUNT];

        /*
         * Create multiple threads that all try to acquire
         * the report-generation resource.
         */
        for (int i = 0;
             i < THREAD_COUNT;
             i++) {

            workers[i] =
                    new Thread(
                            () -> {

                                String threadName =
                                        Thread.currentThread()
                                                .getName();

                                /*
                                 * Attempt the atomic state
                                 * transition:
                                 *
                                 *     false → true
                                 *
                                 * Only ONE thread can successfully
                                 * perform this transition.
                                 */
                                boolean acquired =
                                        busy.compareAndSet(
                                                false,
                                                true
                                        );

                                if (!acquired) {

                                    System.out.println(
                                            threadName
                                                    + " could not acquire "
                                                    + "the report generator."
                                    );

                                    return;
                                }

                                /*
                                 * This thread successfully acquired
                                 * the resource.
                                 */
                                try {

                                    generateReport(
                                            threadName
                                    );

                                } finally {

                                    /*
                                     * Release the resource.
                                     *
                                     * The next thread can now attempt
                                     * to acquire it.
                                     */
                                    busy.set(false);

                                    System.out.println(
                                            threadName
                                                    + " released the "
                                                    + "report generator."
                                    );
                                }
                            },
                            "Report-Worker-"
                                    + (i + 1)
                    );

            workers[i].start();
        }

        /*
         * Wait for every worker to finish.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "\nAll workers completed."
        );
    }
}
