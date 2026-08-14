/*
 * ============================================================
 * 54 - FAIR VS NON-FAIR ReentrantLock
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine 5 workers waiting for access to a shared resource:
 *
 *     Database connection
 *     ↓
 *     Worker-1
 *     Worker-2
 *     Worker-3
 *     Worker-4
 *     Worker-5
 *
 *
 * Suppose they arrive in this order:
 *
 *     Worker-1
 *     Worker-2
 *     Worker-3
 *     Worker-4
 *     Worker-5
 *
 *
 * Should they always acquire the lock in that same order?
 *
 *
 * NOT NECESSARILY.
 *
 *
 * ReentrantLock supports two broad policies:
 *
 *
 *     NON-FAIR
 *     FAIR
 *
 *
 * ------------------------------------------------------------
 * NON-FAIR LOCK
 * ------------------------------------------------------------
 *
 * This is the default behavior of ReentrantLock.
 *
 *
 * A thread attempting to acquire the lock may sometimes obtain
 * it before another thread that has been waiting longer.
 *
 *
 * Conceptually:
 *
 *
 *     Waiting:
 *
 *     Worker-2
 *     Worker-3
 *     Worker-4
 *
 *
 * New Worker-5 arrives.
 *
 *
 * Worker-5 may acquire the lock before an older waiter.
 *
 *
 * This can improve throughput in some workloads because a
 * thread that is already running can reacquire the lock quickly.
 *
 *
 * ------------------------------------------------------------
 * FAIR LOCK
 * ------------------------------------------------------------
 *
 * Create it using:
 *
 *
 *     new ReentrantLock(true);
 *
 *
 * Fairness attempts to favor the longest-waiting thread when
 * multiple threads are queued for the lock.
 *
 *
 * Conceptually:
 *
 *
 *     Worker-2
 *        ↓
 *     Worker-3
 *        ↓
 *     Worker-4
 *
 *
 * They are generally served in queue order.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Fair does NOT mean:
 *
 *
 *     "Exactly FIFO under every possible scheduling situation."
 *
 *
 * Thread scheduling is controlled by the operating system/JVM,
 * and fairness has precise implementation semantics.
 *
 *
 * Fairness means the lock uses a fairness policy to reduce
 * starvation and favor waiting threads.
 *
 *
 * ------------------------------------------------------------
 * FAIR VS NON-FAIR
 * ------------------------------------------------------------
 *
 *
 * NON-FAIR:
 *
 *     Better potential throughput
 *     Less scheduling overhead
 *     Possible starvation
 *
 *
 * FAIR:
 *
 *     More predictable acquisition order
 *     Reduced starvation risk
 *     Potentially lower throughput
 *
 *
 * ------------------------------------------------------------
 * STARVATION
 * ------------------------------------------------------------
 *
 * Starvation happens when a thread waits for a very long time
 * because other threads repeatedly acquire the resource.
 *
 *
 * Fairness can help reduce this problem.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT PERFORMANCE IDEA
 * ------------------------------------------------------------
 *
 * Fairness has a cost.
 *
 *
 * If strict ordering is not important:
 *
 *     non-fair
 *
 * may provide better throughput.
 *
 *
 * If predictable access matters:
 *
 *     fair
 *
 * may be more appropriate.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD EXAMPLES
 * ------------------------------------------------------------
 *
 * Fairness may matter for:
 *
 *     User requests
 *     Job scheduling
 *     Resource allocation
 *     Queue-like workloads
 *
 *
 * Non-fair locking may be fine for:
 *
 *     High-throughput internal operations
 *     Short critical sections
 *     Work where starvation is extremely unlikely
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     FAIR = false
 *
 * to:
 *
 *     FAIR = true
 *
 *
 * Compare acquisition order.
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
 *     20 or 50.
 *
 *
 * Observe the behavior.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make the critical section very short.
 *
 *
 * Ask:
 *
 *     Does fairness still provide a meaningful benefit?
 *
 *
 * It depends on the workload.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a fair ReentrantLock?
 *
 * A ReentrantLock configured to favor threads waiting longer
 * for the lock.
 *
 *
 * 2. Is ReentrantLock fair by default?
 *
 * No.
 *
 * The default constructor creates a non-fair lock.
 *
 *
 * 3. How do you create a fair lock?
 *
 *
 *     new ReentrantLock(true)
 *
 *
 * 4. Does fair always mean faster?
 *
 * No.
 *
 *
 * 5. Why might non-fair locking have better throughput?
 *
 * It can reduce scheduling/queueing overhead and allow
 * opportunistic acquisition.
 *
 *
 * 6. What problem can fairness help reduce?
 *
 * Starvation.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * new ReentrantLock()
 *     ↓
 * NON-FAIR
 *
 *
 * new ReentrantLock(true)
 *     ↓
 * FAIR
 *
 *
 * Remember:
 *
 *
 *     Fairness
 *        ≠
 *     Performance
 *
 *
 * Fairness is a trade-off between predictable access and
 * potential throughput.
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 55:
 *
 *     ConditionProducerConsumer.java
 *
 * We will use:
 *
 *     ReentrantLock
 *     Condition
 *
 *
 * to implement producer-consumer coordination manually and
 * understand:
 *
 *     await()
 *     signal()
 *     signalAll()
 *
 * ============================================================
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFairnessScenario {

    /*
     * Change this to true and compare the behavior.
     */
    private static final boolean FAIR = false;

    /*
     * Number of worker threads.
     */
    private static final int THREAD_COUNT = 10;

    /*
     * Shared ReentrantLock.
     *
     * false → non-fair
     * true  → fair
     */
    private static final ReentrantLock lock =
            new ReentrantLock(FAIR);

    /*
     * Shared counter.
     */
    private static int counter = 0;

    /*
     * Work performed while holding the lock.
     */
    private static void performWork(
            String workerName) {

        /*
         * Acquire lock.
         */
        lock.lock();

        try {

            /*
             * Record acquisition.
             */
            counter++;

            System.out.println(
                    workerName
                            + " acquired lock | "
                            + "order = "
                            + counter
            );

            /*
             * Simulate protected work.
             */
            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        workerName
                                + " interrupted."
                );
            }

        } finally {

            /*
             * Always release the lock.
             */
            lock.unlock();
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println(
                "Lock fairness = "
                        + FAIR
        );

        System.out.println(
                "================================"
        );

        Thread[] workers =
                new Thread[THREAD_COUNT];

        /*
         * Create worker threads.
         */
        for (int i = 0;
             i < THREAD_COUNT;
             i++) {

            final int workerId =
                    i + 1;

            workers[i] =
                    new Thread(
                            () -> performWork(
                                    "Worker-"
                                            + workerId
                            )
                    );
        }

        /*
         * Start workers one after another with a tiny delay.
         *
         * This makes it easier to observe the ordering, although
         * actual scheduling is still controlled by the JVM/OS.
         */
        for (Thread worker : workers) {

            worker.start();

            Thread.sleep(20);
        }

        /*
         * Wait for all workers.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "\nAll workers completed."
        );
    }
}
