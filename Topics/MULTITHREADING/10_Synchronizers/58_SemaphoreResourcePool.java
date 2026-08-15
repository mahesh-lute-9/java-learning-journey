/*
 * ============================================================
 * 58 - SEMAPHORE AS A RESOURCE POOL
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an application with a limited number of database
 * connections.
 *
 *
 * Database connection pool:
 *
 *     3 connections available
 *
 *
 * But:
 *
 *     10 worker threads
 *
 * want to access the database.
 *
 *
 * We cannot allow all 10 workers to use the limited resource
 * simultaneously.
 *
 *
 * REQUIREMENT:
 * ------------------------------------------------------------
 *
 *     Maximum 3 workers may use a database connection at once.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     Semaphore
 *
 *
 * Example:
 *
 *
 *     Semaphore connections =
 *         new Semaphore(3);
 *
 *
 * Think of the semaphore as:
 *
 *
 *     Connection 1 → Permit
 *     Connection 2 → Permit
 *     Connection 3 → Permit
 *
 *
 * A worker must acquire one permit before using the resource.
 *
 *
 *     acquire()
 *
 *
 * After finishing:
 *
 *
 *     release()
 *
 *
 * ------------------------------------------------------------
 * FLOW
 * ------------------------------------------------------------
 *
 *
 * Worker-1 ──→ acquire() ──→ DATABASE
 * Worker-2 ──→ acquire() ──→ DATABASE
 * Worker-3 ──→ acquire() ──→ DATABASE
 *
 *
 * Worker-4 ──→ acquire()
 *                    ↓
 *                  WAIT
 *
 *
 * When Worker-1 finishes:
 *
 *
 *     release()
 *        ↓
 *     permit available
 *        ↓
 *     Worker-4 continues
 *
 *
 * ------------------------------------------------------------
 * WHY THIS IS A RESOURCE POOL
 * ------------------------------------------------------------
 *
 * The permits represent a limited resource.
 *
 *
 * In this example:
 *
 *
 *     1 permit ≈ 1 database connection
 *
 *
 * In a real connection pool, the actual connection object would
 * normally be borrowed and returned. Here Semaphore is used to
 * demonstrate the concurrency-control concept.
 *
 *
 * ------------------------------------------------------------
 * acquire()
 * ------------------------------------------------------------
 *
 *     semaphore.acquire();
 *
 *
 * If a permit is available:
 *
 *     continue immediately.
 *
 *
 * If no permit is available:
 *
 *     thread waits.
 *
 *
 * ------------------------------------------------------------
 * release()
 * ------------------------------------------------------------
 *
 *     semaphore.release();
 *
 *
 * Returns the permit to the semaphore.
 *
 *
 * ------------------------------------------------------------
 * tryAcquire()
 * ------------------------------------------------------------
 *
 * Sometimes waiting indefinitely isn't desirable.
 *
 *
 * You can use:
 *
 *
 *     semaphore.tryAcquire();
 *
 *
 * or:
 *
 *
 *     semaphore.tryAcquire(
 *         2,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * This allows the application to implement a timeout/fallback
 * strategy.
 *
 *
 * ------------------------------------------------------------
 * FAIR SEMAPHORE
 * ------------------------------------------------------------
 *
 * You can create:
 *
 *
 *     new Semaphore(
 *         3,
 *         true
 *     );
 *
 *
 * The second argument enables fairness.
 *
 *
 * This can make permit acquisition more orderly for waiting
 * threads.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Always release the permit in finally.
 *
 *
 * Correct:
 *
 *
 *     semaphore.acquire();
 *
 *     try {
 *         useResource();
 *     } finally {
 *         semaphore.release();
 *     }
 *
 *
 * Otherwise a permit can be lost.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Database connections
 *     API concurrency limits
 *     File handles
 *     Browser sessions
 *     AI inference slots
 *     Limited hardware
 *     External service connections
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     CONNECTION_LIMIT = 3
 *
 * to:
 *
 *     CONNECTION_LIMIT = 1
 *
 *
 * Now only one worker can access the resource at a time.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change it to:
 *
 *     CONNECTION_LIMIT = 5
 *
 *
 * Observe the increased concurrency.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace acquire() with tryAcquire().
 *
 *
 * Make workers immediately report:
 *
 *
 *     "No connection available."
 *
 *
 * instead of waiting.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Intentionally remove release().
 *
 *
 * Observe that eventually all permits disappear and later
 * workers can no longer proceed.
 *
 *
 * Then restore release() in finally.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a Semaphore?
 *
 * A synchronization mechanism that manages a number of permits.
 *
 *
 * 2. What does acquire() do?
 *
 * Obtains a permit, waiting if necessary.
 *
 *
 * 3. What does release() do?
 *
 * Returns a permit.
 *
 *
 * 4. What does tryAcquire() do?
 *
 * Attempts to obtain a permit without waiting indefinitely.
 *
 *
 * 5. Can a Semaphore have multiple permits?
 *
 * Yes.
 *
 *
 * 6. Difference between Semaphore and ReentrantLock?
 *
 * ReentrantLock generally represents exclusive ownership by
 * one thread at a time.
 *
 * Semaphore can allow multiple permits and therefore multiple
 * threads to enter a protected resource concurrently.
 *
 *
 * 7. Why is finally important?
 *
 * To guarantee permit release even if the operation fails.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * Semaphore(3)
 *      ↓
 * 3 permits
 *      ↓
 * Maximum 3 workers
 *      ↓
 * use resource
 *      ↓
 * release()
 *
 *
 * Think:
 *
 *     "How many workers are allowed to use this resource
 *      simultaneously?"
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 59:
 *
 *     PhaserMultiStageScenario.java
 *
 * Scenario:
 *
 *     A team of workers performs multiple stages, but workers
 *     may dynamically join or leave the process.
 *
 * We'll learn Phaser and understand when it is more flexible
 * than CountDownLatch and CyclicBarrier.
 *
 * ============================================================
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreResourcePool {

    /*
     * Only three database connections are available.
     */
    private static final int CONNECTION_LIMIT = 3;

    /*
     * Ten workers want to access the database.
     */
    private static final int WORKER_COUNT = 10;

    /*
     * Semaphore represents the limited connection slots.
     */
    private static final Semaphore connectionPool =
            new Semaphore(
                    CONNECTION_LIMIT
            );

    /*
     * Tracks how many workers are currently using a connection.
     *
     * This is only for observing the program.
     */
    private static int activeConnections = 0;

    /*
     * Lock protecting the demonstration counter above.
     *
     * The Semaphore controls database access.
     *
     * This lock only protects our monitoring variable.
     */
    private static final Object monitor =
            new Object();

    /*
     * Simulate database work.
     */
    private static void executeDatabaseQuery(
            String workerName) {

        System.out.println(
                workerName
                        + " executing database query."
        );

        try {

            /*
             * Simulate database operation.
             */
            Thread.sleep(2000);

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            System.out.println(
                    workerName
                            + " database operation interrupted."
            );
        }

        System.out.println(
                workerName
                        + " completed database query."
        );
    }

    /*
     * Worker attempts to acquire a database connection.
     */
    private static void processRequest(
            String workerName) {

        boolean acquired = false;

        try {

            System.out.println(
                    workerName
                            + " waiting for connection."
            );

            /*
             * Wait until a database connection slot becomes
             * available.
             */
            connectionPool.acquire();

            acquired = true;

            /*
             * This worker now owns one permit.
             */
            synchronized (monitor) {

                activeConnections++;

                System.out.println(
                        workerName
                                + " acquired connection."
                                + " Active connections = "
                                + activeConnections
                );
            }

            /*
             * Use the database connection.
             */
            executeDatabaseQuery(
                    workerName
            );

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            System.out.println(
                    workerName
                            + " interrupted while waiting "
                            + "for a connection."
            );

        } finally {

            /*
             * Only release the permit if this worker actually
             * acquired one.
             */
            if (acquired) {

                synchronized (monitor) {

                    activeConnections--;

                    System.out.println(
                            workerName
                                    + " releasing connection."
                                    + " Active connections = "
                                    + activeConnections
                    );
                }

                /*
                 * Return the permit to the pool.
                 */
                connectionPool.release();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create worker threads.
         */
        Thread[] workers =
                new Thread[WORKER_COUNT];

        for (int i = 0;
             i < WORKER_COUNT;
             i++) {

            final int workerId =
                    i + 1;

            workers[i] =
                    new Thread(
                            () ->
                                    processRequest(
                                            "Worker-"
                                                    + workerId
                                    ),
                            "Worker-"
                                    + workerId
                    );

            workers[i].start();
        }

        /*
         * Wait for every worker.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "All database requests completed."
        );

        System.out.println(
                "Available permits: "
                        + connectionPool.availablePermits()
        );

        System.out.println(
                "======================================"
        );
    }
}
