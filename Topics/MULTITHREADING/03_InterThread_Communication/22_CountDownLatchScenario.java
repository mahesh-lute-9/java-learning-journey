/*
 * ============================================================
 * 22 - APPLICATION STARTUP USING CountDownLatch
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend server starting up.
 *
 * Before the server can accept user requests, several
 * independent services must initialize:
 *
 *     Database
 *     Cache
 *     Configuration
 *     Authentication
 *
 * These services can initialize concurrently.
 *
 * BUT:
 *
 *     The server must NOT start accepting requests until ALL
 *     required services are ready.
 *
 *
 * ------------------------------------------------------------
 * WORKFLOW
 * ------------------------------------------------------------
 *
 *                  Application
 *                       |
 *             +---------+---------+
 *             |         |         |
 *             v         v         v
 *          Database   Cache    Config
 *             |         |         |
 *             +---------+---------+
 *                       |
 *                 CountDownLatch
 *                       |
 *                       v
 *                 Server Starts
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * The initialization tasks have different execution times.
 *
 * Example:
 *
 *     Database → 3 seconds
 *     Cache    → 1 second
 *     Config   → 2 seconds
 *
 * The server must wait for ALL of them.
 *
 *
 * We could manually use:
 *
 *     thread.start()
 *     thread.join()
 *     thread.join()
 *     thread.join()
 *
 *
 * But CountDownLatch provides a cleaner abstraction when one
 * thread needs to wait for a set of independent events.
 *
 *
 * ------------------------------------------------------------
 * WHAT IS CountDownLatch?
 * ------------------------------------------------------------
 *
 * CountDownLatch starts with a count.
 *
 * Example:
 *
 *     new CountDownLatch(4)
 *
 *
 * Each initialization task calls:
 *
 *     latch.countDown();
 *
 *
 * When the count reaches:
 *
 *     0
 *
 * threads waiting on:
 *
 *     latch.await();
 *
 * are allowed to continue.
 *
 *
 * ------------------------------------------------------------
 * VISUALIZATION
 * ------------------------------------------------------------
 *
 * Initial:
 *
 *     Count = 4
 *
 *
 * Database ready
 *     ↓
 *     countDown()
 *     ↓
 * Count = 3
 *
 *
 * Cache ready
 *     ↓
 *     countDown()
 *     ↓
 * Count = 2
 *
 *
 * Config ready
 *     ↓
 *     countDown()
 *     ↓
 * Count = 1
 *
 *
 * Authentication ready
 *     ↓
 *     countDown()
 *     ↓
 * Count = 0
 *
 *
 * Server waiting on await()
 *     ↓
 * Count reaches 0
 *     ↓
 * Server continues
 *
 *
 * ------------------------------------------------------------
 * COUNTDOWN LATCH VS CYCLIC BARRIER
 * ------------------------------------------------------------
 *
 * This is an important interview comparison.
 *
 *
 * CountDownLatch:
 *
 *     One or more threads wait for a count to reach zero.
 *
 *     The count cannot be reset.
 *
 *
 * CyclicBarrier:
 *
 *     A fixed number of threads wait for each other at a
 *     synchronization point.
 *
 *     It can be reused.
 *
 *
 * Think:
 *
 *     Latch:
 *         "Wait until these events happen."
 *
 *
 *     Barrier:
 *         "Everyone meet here before continuing."
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * countDown() does NOT wait.
 *
 * await() waits.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Increase initialization times.
 *
 * Observe that the server still waits for the slowest required
 * initialization task.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Add another initialization task.
 *
 * Remember to:
 *
 *     Increase latch count.
 *
 * and call:
 *
 *     countDown()
 *
 * when that task finishes.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Remove one countDown().
 *
 * Run the program.
 *
 * The server will continue waiting because the latch never
 * reaches zero.
 *
 * This demonstrates why every required task must signal
 * completion correctly.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     await(timeout, unit)
 *
 * instead of:
 *
 *     await()
 *
 * Think about why timeouts are useful in production systems.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is CountDownLatch?
 *
 * 2. What does await() do?
 *
 * 3. What does countDown() do?
 *
 * 4. Can CountDownLatch be reset?
 *
 * No.
 *
 * 5. Difference between CountDownLatch and CyclicBarrier?
 *
 * 6. What happens if countDown() is never called enough times?
 *
 * Waiting threads can remain blocked.
 *
 * 7. Can multiple threads call await() on the same latch?
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * CountDownLatch is excellent for:
 *
 *     Application startup
 *     Test setup
 *     Waiting for workers
 *     Parallel initialization
 *     Waiting for independent tasks
 *
 *
 * Pattern:
 *
 *
 *     latch = new CountDownLatch(N)
 *
 *
 *     Worker:
 *         doWork()
 *         latch.countDown()
 *
 *
 *     Main:
 *         latch.await()
 *         startNextPhase()
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 23:
 *
 *     SemaphoreResourcePool.java
 *
 * We will handle a different real-world problem:
 *
 *     Only a LIMITED number of resources can be used at the
 *     same time.
 *
 * Example:
 *
 *     Database connection pool
 *     Parking spaces
 *     API rate-limited slots
 *
 * This introduces Semaphore.
 *
 * ============================================================
 */

import java.util.concurrent.CountDownLatch;

public class CountDownLatchScenario {

    /*
     * Number of services that must initialize before the server
     * can start.
     */
    private static final int REQUIRED_SERVICES = 4;

    static class ServiceInitializer
            implements Runnable {

        private final String serviceName;
        private final int initializationTime;
        private final CountDownLatch latch;

        ServiceInitializer(
                String serviceName,
                int initializationTime,
                CountDownLatch latch) {

            this.serviceName =
                    serviceName;

            this.initializationTime =
                    initializationTime;

            this.latch =
                    latch;
        }

        @Override
        public void run() {

            try {

                System.out.println(
                        serviceName
                                + " initialization started."
                );

                /*
                 * Simulate service initialization.
                 */
                Thread.sleep(
                        initializationTime
                );

                System.out.println(
                        serviceName
                                + " initialization completed."
                );

            } catch (InterruptedException e) {

                /*
                 * Restore interrupted status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        serviceName
                                + " initialization interrupted."
                );

            } finally {

                /*
                 * VERY IMPORTANT:
                 *
                 * countDown() must happen even if initialization
                 * is interrupted or fails in this example.
                 *
                 * In a real system, you would usually track
                 * SUCCESS/FAILURE separately rather than blindly
                 * allowing startup after a failed mandatory service.
                 */
                latch.countDown();

                System.out.println(
                        serviceName
                                + " signaled completion. "
                                + "Remaining count: "
                                + latch.getCount()
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create the latch.
         *
         * The server needs four initialization signals before
         * it can continue.
         */
        CountDownLatch startupLatch =
                new CountDownLatch(
                        REQUIRED_SERVICES
                );

        /*
         * Create initialization workers.
         */
        Thread databaseThread =
                new Thread(
                        new ServiceInitializer(
                                "Database",
                                3000,
                                startupLatch
                        ),
                        "Database-Initializer"
                );

        Thread cacheThread =
                new Thread(
                        new ServiceInitializer(
                                "Cache",
                                1000,
                                startupLatch
                        ),
                        "Cache-Initializer"
                );

        Thread configurationThread =
                new Thread(
                        new ServiceInitializer(
                                "Configuration",
                                2000,
                                startupLatch
                        ),
                        "Configuration-Initializer"
                );

        Thread authenticationThread =
                new Thread(
                        new ServiceInitializer(
                                "Authentication",
                                2500,
                                startupLatch
                        ),
                        "Authentication-Initializer"
                );

        /*
         * Start all initialization tasks concurrently.
         */
        databaseThread.start();
        cacheThread.start();
        configurationThread.start();
        authenticationThread.start();

        System.out.println(
                "\nServer is waiting for all services...\n"
        );

        /*
         * Main thread waits until the latch reaches zero.
         */
        startupLatch.await();

        /*
         * At this point all four workers have called
         * countDown().
         */
        System.out.println(
                "\nAll required services are ready."
        );

        System.out.println(
                "Server is now accepting requests."
        );

        /*
         * Wait for worker threads to finish completely.
         */
        databaseThread.join();
        cacheThread.join();
        configurationThread.join();
        authenticationThread.join();

        System.out.println(
                "Startup sequence completed."
        );
    }
}
