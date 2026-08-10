/*
 * ============================================================
 * 23 - LIMITING RESOURCES USING Semaphore
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application that has a database connection
 * pool.
 *
 * Suppose:
 *
 *     20 requests
 *
 * arrive at the same time.
 *
 * But the application has only:
 *
 *     3 database connections
 *
 *
 * We CANNOT allow all 20 threads to use a database connection
 * simultaneously.
 *
 *
 * Therefore:
 *
 *     Maximum 3 threads
 *
 * can access the database at the same time.
 *
 *
 * ------------------------------------------------------------
 * SYSTEM
 * ------------------------------------------------------------
 *
 *              20 Requests
 *                   |
 *       +-----------+-----------+
 *       |           |           |
 *       v           v           v
 *    Thread-1    Thread-2    Thread-3
 *       |           |           |
 *       +-----------+-----------+
 *                   |
 *              Semaphore
 *              Permits = 3
 *                   |
 *          +--------+--------+
 *          |        |        |
 *       Connection Connection Connection
 *
 *
 * Other threads must WAIT until a permit becomes available.
 *
 *
 * ------------------------------------------------------------
 * WHAT IS Semaphore?
 * ------------------------------------------------------------
 *
 * Semaphore controls access to a limited number of resources.
 *
 *
 * Example:
 *
 *     Semaphore(3)
 *
 * means:
 *
 *     3 permits are initially available.
 *
 *
 * A thread calls:
 *
 *     acquire()
 *
 * to obtain a permit.
 *
 *
 * When finished, it calls:
 *
 *     release()
 *
 * to return the permit.
 *
 *
 * ------------------------------------------------------------
 * BASIC PATTERN
 * ------------------------------------------------------------
 *
 *     semaphore.acquire();
 *
 *     try {
 *
 *         useResource();
 *
 *     } finally {
 *
 *         semaphore.release();
 *     }
 *
 *
 * ------------------------------------------------------------
 * WHY finally?
 * ------------------------------------------------------------
 *
 * Imagine:
 *
 *     acquire()
 *     use resource
 *     exception occurs
 *     release() never happens
 *
 *
 * Now one permit is permanently lost.
 *
 * Eventually all permits may disappear.
 *
 * Therefore:
 *
 *     release()
 *
 * should normally be placed inside:
 *
 *     finally
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 * Semaphore can control:
 *
 *     Database connections
 *     API request slots
 *     File access
 *     Network connections
 *     Printer access
 *     Parking spaces
 *     Expensive hardware resources
 *
 *
 * ------------------------------------------------------------
 * COUNTING SEMAPHORE
 * ------------------------------------------------------------
 *
 *     new Semaphore(3)
 *
 * means:
 *
 *     3 concurrent users allowed.
 *
 *
 *     acquire()
 *
 * decreases available permits.
 *
 *
 *     release()
 *
 * increases available permits.
 *
 *
 * ------------------------------------------------------------
 * WHAT HAPPENS WHEN NO PERMIT IS AVAILABLE?
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     permits = 0
 *
 * and another thread calls:
 *
 *     acquire()
 *
 *
 * That thread waits until another thread calls:
 *
 *     release()
 *
 *
 * ------------------------------------------------------------
 * FAIRNESS
 * ------------------------------------------------------------
 *
 * Semaphore can optionally use fairness:
 *
 *     new Semaphore(3, true)
 *
 *
 * A fair Semaphore generally grants permits in an order that
 * gives waiting threads more predictable access.
 *
 *
 * But fairness can have performance trade-offs.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     MAX_CONNECTIONS = 3
 *
 * to:
 *
 *     MAX_CONNECTIONS = 1
 *
 * Now only one request should access the database at a time.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change it to:
 *
 *     MAX_CONNECTIONS = 10
 *
 * Create 20 request threads.
 *
 * Observe that approximately 10 requests can be inside the
 * protected section simultaneously.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Remove:
 *
 *     semaphore.release()
 *
 * Run enough requests.
 *
 * Eventually all permits will be consumed and future threads
 * will remain blocked.
 *
 *
 * This demonstrates RESOURCE LEAK / PERMIT LEAK.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     acquire()
 *
 * with:
 *
 *     tryAcquire()
 *
 * and handle the case where no connection is immediately
 * available.
 *
 *
 * This is useful when you don't want a request to wait
 * indefinitely.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is Semaphore?
 *
 * 2. What is a permit?
 *
 * 3. Difference between Semaphore(1) and synchronized?
 *
 * A Semaphore with one permit can provide exclusive access,
 * but it has different semantics and can be released by a
 * different thread.
 *
 * synchronized is tied to an object's monitor and has structured
 * monitor ownership.
 *
 *
 * 4. What does acquire() do?
 *
 * Obtains a permit or waits until one becomes available.
 *
 *
 * 5. What does release() do?
 *
 * Returns a permit.
 *
 *
 * 6. Why should release() usually be in finally?
 *
 * To avoid losing permits when exceptions occur.
 *
 *
 * 7. What is a fair Semaphore?
 *
 * A Semaphore configured to provide more orderly access to
 * waiting threads.
 *
 *
 * 8. What happens if release() is called more times than
 * acquire()?
 *
 * Semaphore permits can increase beyond the original count.
 *
 * Therefore release() must correspond correctly to acquired
 * permits in resource-pool designs.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * synchronized answers:
 *
 *     "Only ONE thread at a time."
 *
 *
 * Semaphore answers:
 *
 *     "Only N threads at a time."
 *
 *
 * Example:
 *
 *     synchronized
 *          ↓
 *     1 concurrent user
 *
 *
 *     Semaphore(3)
 *          ↓
 *     3 concurrent users
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 24:
 *
 *     ReadWriteLockScenario.java
 *
 * We will simulate a shared configuration system where:
 *
 *     Many threads READ configuration
 *
 * but only:
 *
 *     One thread at a time WRITES configuration.
 *
 * This introduces ReadWriteLock.
 *
 * ============================================================
 */

import java.util.concurrent.Semaphore;

public class SemaphoreResourcePool {

    /*
     * Number of database connections available.
     */
    private static final int MAX_CONNECTIONS = 3;

    /*
     * Number of application requests.
     */
    private static final int TOTAL_REQUESTS = 10;

    /*
     * Semaphore represents the available database connections.
     *
     * Initially:
     *
     *     3 permits
     *
     * Therefore at most 3 threads can enter the database
     * section simultaneously.
     */
    private static final Semaphore connectionPool =
            new Semaphore(
                    MAX_CONNECTIONS
            );

    static class DatabaseRequest
            implements Runnable {

        private final int requestId;

        DatabaseRequest(int requestId) {

            this.requestId =
                    requestId;
        }

        @Override
        public void run() {

            try {

                System.out.println(
                        "Request-"
                                + requestId
                                + " waiting for database connection."
                );

                /*
                 * Try to obtain one database connection.
                 *
                 * If no permit is available, this thread waits.
                 */
                connectionPool.acquire();

                System.out.println(
                        "Request-"
                                + requestId
                                + " acquired connection."
                                + " Available permits: "
                                + connectionPool.availablePermits()
                );

                /*
                 * ------------------------------------------------
                 * DATABASE WORK
                 * ------------------------------------------------
                 *
                 * At most 3 threads should be here simultaneously.
                 */
                try {

                    System.out.println(
                            "Request-"
                                    + requestId
                                    + " executing database query..."
                    );

                    /*
                     * Simulate database operation.
                     */
                    Thread.sleep(2000);

                    System.out.println(
                            "Request-"
                                    + requestId
                                    + " query completed."
                    );

                } finally {

                    /*
                     * Return the database connection.
                     *
                     * This MUST happen even if database work
                     * throws an exception.
                     */
                    connectionPool.release();

                    System.out.println(
                            "Request-"
                                    + requestId
                                    + " released connection."
                                    + " Available permits: "
                                    + connectionPool.availablePermits()
                    );
                }

            } catch (InterruptedException e) {

                /*
                 * Restore interrupted status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        "Request-"
                                + requestId
                                + " interrupted while waiting."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread[] requests =
                new Thread[TOTAL_REQUESTS];

        /*
         * Create 10 concurrent requests.
         */
        for (int i = 0;
             i < TOTAL_REQUESTS;
             i++) {

            requests[i] =
                    new Thread(
                            new DatabaseRequest(i + 1),
                            "Request-"
                                    + (i + 1)
                    );
        }

        System.out.println(
                "Starting "
                        + TOTAL_REQUESTS
                        + " requests."
        );

        System.out.println(
                "Database connections available: "
                        + MAX_CONNECTIONS
        );

        /*
         * Start all requests.
         */
        for (Thread request : requests) {

            request.start();
        }

        /*
         * Wait for all requests to finish.
         */
        for (Thread request : requests) {

            request.join();
        }

        System.out.println(
                "All database requests completed."
        );

        System.out.println(
                "Final available permits: "
                        + connectionPool.availablePermits()
        );
    }
}
