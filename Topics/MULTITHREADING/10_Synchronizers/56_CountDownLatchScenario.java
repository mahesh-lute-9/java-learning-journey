/*
 * ============================================================
 * 56 - CountDownLatch FOR APPLICATION STARTUP
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application that depends on several
 * services before it can start accepting user requests.
 *
 *
 * Application startup:
 *
 *     Database
 *     Cache
 *     Authentication Service
 *     Configuration
 *
 *
 * These services initialize independently.
 *
 *
 * We want:
 *
 *     "Don't start the application until ALL required services
 *      are ready."
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * If the main application simply starts immediately:
 *
 *
 *     Database     → still starting
 *     Cache        → still starting
 *     Auth         → still starting
 *     Application  → STARTED ❌
 *
 *
 * Requests may arrive before dependencies are ready.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     CountDownLatch
 *
 *
 * Example:
 *
 *     CountDownLatch latch =
 *         new CountDownLatch(3);
 *
 *
 * Three services must finish initialization.
 *
 *
 * Each service calls:
 *
 *     latch.countDown();
 *
 *
 * after initialization.
 *
 *
 * The main application calls:
 *
 *     latch.await();
 *
 *
 * and waits until the count reaches zero.
 *
 *
 * ------------------------------------------------------------
 * HOW IT WORKS
 * ------------------------------------------------------------
 *
 *
 * Initial:
 *
 *     count = 3
 *
 *
 * Database finishes:
 *
 *     countDown()
 *
 *     count = 2
 *
 *
 * Cache finishes:
 *
 *     countDown()
 *
 *     count = 1
 *
 *
 * Auth finishes:
 *
 *     countDown()
 *
 *     count = 0
 *
 *
 * Then:
 *
 *     await()
 *
 * returns.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * CountDownLatch is generally a ONE-SHOT synchronization aid.
 *
 *
 * Once the count reaches:
 *
 *     0
 *
 *
 * it stays at zero.
 *
 *
 * You cannot reset the same CountDownLatch.
 *
 *
 * If you need a reusable barrier-like mechanism, other tools
 * such as CyclicBarrier or Phaser may be more appropriate.
 *
 *
 * ------------------------------------------------------------
 * await()
 * ------------------------------------------------------------
 *
 * The waiting thread blocks until:
 *
 *     count == 0
 *
 *
 * It can also be interrupted.
 *
 *
 * ------------------------------------------------------------
 * countDown()
 * ------------------------------------------------------------
 *
 * Decreases the count by one.
 *
 *
 * Calling countDown() when the count is already zero has no
 * further effect.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Application startup
 *     Test setup
 *     Waiting for worker initialization
 *     Parallel resource loading
 *     Service readiness
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     SERVICE_COUNT
 *
 *
 * Add another initialization task.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make one service take 5 seconds.
 *
 *
 * Observe that the application still waits for it.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Remove one countDown().
 *
 *
 * The main thread will keep waiting because the count never
 * reaches zero.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Interrupt the main waiting thread.
 *
 *
 * Observe that await() is interruptible.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is CountDownLatch?
 *
 * A synchronization aid that allows one or more threads to
 * wait until a count reaches zero.
 *
 *
 * 2. What does countDown() do?
 *
 * Decrements the latch count.
 *
 *
 * 3. What does await() do?
 *
 * Waits until the count reaches zero or the waiting thread is
 * interrupted.
 *
 *
 * 4. Can CountDownLatch be reset?
 *
 * No.
 *
 *
 * 5. When would you use CountDownLatch?
 *
 * When one or more threads need to wait for a fixed number of
 * events/tasks to complete.
 *
 *
 * 6. Difference between CountDownLatch and Semaphore?
 *
 * CountDownLatch coordinates completion of a fixed number of
 * events.
 *
 * Semaphore controls access using permits.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 *         Service A ──→ countDown()
 *              │
 *         Service B ──→ countDown()
 *              │
 *         Service C ──→ countDown()
 *              │
 *              ↓
 *        count = 0
 *              ↓
 *        Application starts
 *
 *
 * Think:
 *
 *     "Wait until N things are finished."
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 57:
 *
 *     CyclicBarrierScenario.java
 *
 * Scenario:
 *
 *     Several workers must reach the same checkpoint before
 *     ANY of them can continue.
 *
 * This is different from CountDownLatch because the barrier can
 * be reused for multiple rounds.
 *
 * ============================================================
 */

import java.util.concurrent.CountDownLatch;

public class CountDownLatchScenario {

    /*
     * Number of services that must initialize.
     */
    private static final int SERVICE_COUNT = 3;

    /*
     * Latch starts with one count for each required service.
     */
    private static final CountDownLatch startupLatch =
            new CountDownLatch(SERVICE_COUNT);

    /*
     * Simulate initialization of one service.
     */
    private static void initializeService(
            String serviceName,
            long initializationTime) {

        System.out.println(
                serviceName
                        + " initialization started."
        );

        try {

            /*
             * Simulate initialization work.
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
             * Restore interruption status.
             */
            Thread.currentThread()
                    .interrupt();

            System.out.println(
                    serviceName
                            + " initialization interrupted."
            );

        } finally {

            /*
             * Signal that this service has finished its startup
             * attempt.
             *
             * IMPORTANT:
             *
             * In a real application, you should decide whether
             * countDown() should represent "finished" or only
             * "successfully initialized."
             *
             * Here it represents completion for demonstration.
             */
            startupLatch.countDown();

            System.out.println(
                    serviceName
                            + " signalled startup latch."
                            + " Remaining count = "
                            + startupLatch.getCount()
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * ----------------------------------------------------
         * START DATABASE
         * ----------------------------------------------------
         */
        Thread database =
                new Thread(
                        () ->
                                initializeService(
                                        "Database",
                                        2000
                                )
                );

        /*
         * ----------------------------------------------------
         * START CACHE
         * ----------------------------------------------------
         */
        Thread cache =
                new Thread(
                        () ->
                                initializeService(
                                        "Cache",
                                        3000
                                )
                );

        /*
         * ----------------------------------------------------
         * START AUTHENTICATION SERVICE
         * ----------------------------------------------------
         */
        Thread authentication =
                new Thread(
                        () ->
                                initializeService(
                                        "Authentication",
                                        1500
                                )
                );

        /*
         * Start all initialization tasks concurrently.
         */
        database.start();
        cache.start();
        authentication.start();

        System.out.println(
                "\nApplication is waiting for dependencies..."
        );

        /*
         * ----------------------------------------------------
         * WAIT FOR ALL SERVICES
         * ----------------------------------------------------
         *
         * Main thread remains blocked until:
         *
         *     startupLatch.getCount() == 0
         */
        startupLatch.await();

        /*
         * All required initialization tasks have completed.
         */
        System.out.println(
                "\n========================================"
        );

        System.out.println(
                "All dependencies are ready."
        );

        System.out.println(
                "Application is now READY."
        );

        System.out.println(
                "========================================"
        );

        /*
         * Wait for worker threads to finish completely.
         */
        database.join();
        cache.join();
        authentication.join();
    }
}
