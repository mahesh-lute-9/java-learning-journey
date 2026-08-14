/*
 * ============================================================
 * 53 - ReentrantLock IN A REAL-WORLD SCENARIO
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine two services competing for a shared resource:
 *
 *     Report Generator
 *
 *
 * A thread may need to acquire the resource, but we DON'T want
 * it to wait forever.
 *
 *
 * Requirement:
 *
 *     "Try to acquire the resource.
 *      If it is unavailable, do something else."
 *
 *
 * This is where:
 *
 *     ReentrantLock
 *
 * becomes useful.
 *
 *
 * ------------------------------------------------------------
 * synchronized VS ReentrantLock
 * ------------------------------------------------------------
 *
 * synchronized:
 *
 *     synchronized {
 *         // critical section
 *     }
 *
 *
 * ReentrantLock:
 *
 *     lock.lock();
 *
 *     try {
 *         // critical section
 *     } finally {
 *         lock.unlock();
 *     }
 *
 *
 * ReentrantLock gives us additional control.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT FEATURES
 * ------------------------------------------------------------
 *
 *
 * 1. lock()
 *
 *     Wait until the lock becomes available.
 *
 *
 * 2. tryLock()
 *
 *     Try to acquire the lock immediately.
 *
 *
 * 3. tryLock(timeout)
 *
 *     Wait for a limited amount of time.
 *
 *
 * 4. lockInterruptibly()
 *
 *     Allow a waiting thread to be interrupted.
 *
 *
 * 5. newCondition()
 *
 *     Create Condition objects for advanced coordination.
 *
 *
 * ------------------------------------------------------------
 * tryLock()
 * ------------------------------------------------------------
 *
 * Example:
 *
 *
 *     if (lock.tryLock()) {
 *
 *         try {
 *             doWork();
 *         } finally {
 *             lock.unlock();
 *         }
 *
 *     } else {
 *
 *         System.out.println(
 *             "Resource unavailable"
 *         );
 *     }
 *
 *
 * The important idea:
 *
 *     DON'T WAIT FOREVER.
 *
 *
 * ------------------------------------------------------------
 * tryLock(timeout)
 * ------------------------------------------------------------
 *
 * Sometimes immediate failure isn't desirable.
 *
 *
 * Example:
 *
 *
 *     lock.tryLock(
 *         2,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * Meaning:
 *
 *     "Wait for at most 2 seconds."
 *
 *
 * If the lock becomes available:
 *
 *     acquire it.
 *
 *
 * Otherwise:
 *
 *     give up.
 *
 *
 * ------------------------------------------------------------
 * lockInterruptibly()
 * ------------------------------------------------------------
 *
 * Normal lock acquisition:
 *
 *     lock.lock()
 *
 *
 * can wait until the lock becomes available.
 *
 *
 * With:
 *
 *     lock.lockInterruptibly()
 *
 *
 * another thread can interrupt the waiting thread.
 *
 *
 * This is useful when your application needs cancellation or
 * shutdown responsiveness.
 *
 *
 * ------------------------------------------------------------
 * WHY IS IT CALLED REENTRANT?
 * ------------------------------------------------------------
 *
 * If a thread already owns the lock, the same thread can acquire
 * it again.
 *
 *
 * Example:
 *
 *
 *     lock.lock();
 *
 *     lock.lock();
 *
 *
 * The same thread can hold the lock twice.
 *
 *
 * It must then unlock twice:
 *
 *
 *     lock.unlock();
 *     lock.unlock();
 *
 *
 * Think:
 *
 *
 *     acquire count = 2
 *
 *     first unlock → 1
 *
 *     second unlock → 0
 *
 *
 * ------------------------------------------------------------
 * VERY IMPORTANT
 * ------------------------------------------------------------
 *
 * Never write:
 *
 *
 *     lock.lock();
 *     doWork();
 *     lock.unlock();
 *
 *
 * without finally.
 *
 *
 * Correct:
 *
 *
 *     lock.lock();
 *
 *     try {
 *         doWork();
 *     } finally {
 *         lock.unlock();
 *     }
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Resource allocation
 *     Cache updates
 *     Inventory updates
 *     Banking operations
 *     Scheduling
 *     Coordinating shared resources
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     tryLock(2, TimeUnit.SECONDS)
 *
 * to:
 *
 *     tryLock()
 *
 *
 * Observe the difference.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Increase the first task's work time to:
 *
 *     5000 ms
 *
 *
 * Keep timeout:
 *
 *     2 seconds
 *
 *
 * Observe the second thread giving up.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Try:
 *
 *     lock.lockInterruptibly()
 *
 *
 * and interrupt the waiting thread.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is ReentrantLock?
 *
 * An explicit lock implementation providing advanced locking
 * capabilities.
 *
 *
 * 2. What does tryLock() do?
 *
 * Attempts to acquire the lock without waiting indefinitely.
 *
 *
 * 3. What does tryLock(timeout) do?
 *
 * Waits up to the specified time for the lock.
 *
 *
 * 4. What does lockInterruptibly() do?
 *
 * Allows lock acquisition to respond to thread interruption.
 *
 *
 * 5. Why is finally important?
 *
 * To guarantee that the lock is released.
 *
 *
 * 6. What does reentrant mean?
 *
 * The owning thread can acquire the same lock multiple times.
 *
 *
 * 7. Does ReentrantLock automatically release itself?
 *
 * No.
 *
 * The programmer must unlock it.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * synchronized
 *     ↓
 * Simple locking
 *
 *
 * ReentrantLock
 *     ↓
 * Explicit + advanced control
 *
 *
 * Especially remember:
 *
 *
 *     tryLock()
 *     tryLock(timeout)
 *     lockInterruptibly()
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 54:
 *
 *     ReentrantLockFairnessScenario.java
 *
 * We will understand FAIR vs NON-FAIR locks and why the default
 * behavior of ReentrantLock is important in performance and
 * scheduling.
 *
 * ============================================================
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockScenario {

    /*
     * Shared lock protecting the report generator.
     */
    private static final ReentrantLock lock =
            new ReentrantLock();

    /*
     * Simulate report generation.
     */
    private static void generateReport(
            String workerName) {

        System.out.println(
                workerName
                        + " acquired the report generator."
        );

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    workerName
                            + " was interrupted."
            );
        }

        System.out.println(
                workerName
                        + " finished generating the report."
        );
    }

    /*
     * Worker that waits for the resource for a limited amount
     * of time.
     */
    private static void tryGenerateReport(
            String workerName) {

        try {

            /*
             * Wait for at most 2 seconds.
             */
            boolean acquired =
                    lock.tryLock(
                            2,
                            TimeUnit.SECONDS
                    );

            if (!acquired) {

                System.out.println(
                        workerName
                                + " could not acquire "
                                + "the report generator."
                );

                return;
            }

            /*
             * The current thread owns the lock.
             */
            try {

                generateReport(
                        workerName
                );

            } finally {

                /*
                 * Release the lock.
                 */
                lock.unlock();

                System.out.println(
                        workerName
                                + " released the lock."
                );
            }

        } catch (InterruptedException e) {

            /*
             * tryLock(timeout) is interruptible.
             */
            Thread.currentThread().interrupt();

            System.out.println(
                    workerName
                            + " was interrupted while "
                            + "waiting for the lock."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * First worker acquires the lock.
         */
        Thread worker1 =
                new Thread(
                        () ->
                                tryGenerateReport(
                                        "Worker-1"
                                )
                );

        /*
         * Second worker attempts to acquire the same lock.
         */
        Thread worker2 =
                new Thread(
                        () ->
                                tryGenerateReport(
                                        "Worker-2"
                                )
                );

        worker1.start();

        /*
         * Give Worker-1 time to acquire the lock.
         */
        Thread.sleep(200);

        worker2.start();

        /*
         * Wait for both workers.
         */
        worker1.join();
        worker2.join();

        System.out.println(
                "\nProgram completed."
        );
    }
}
