/*
 * ============================================================
 * 46 - LIMITING CONCURRENT TASKS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend receiving 1,000 image-processing requests.
 *
 * We want to process them asynchronously.
 *
 * But image processing is expensive.
 *
 * If we allow all 1,000 operations to execute simultaneously:
 *
 *     CPU usage may spike
 *     Memory usage may increase
 *     External services may be overloaded
 *     Application performance may collapse
 *
 *
 * REQUIREMENT:
 * ------------------------------------------------------------
 *
 *     Allow only 3 expensive operations to run at the same
 *     time.
 *
 *
 * Example:
 *
 *     10 tasks
 *     concurrency limit = 3
 *
 *
 *     Task 1 ──┐
 *     Task 2 ──┤
 *     Task 3 ──┤  RUNNING
 *              │
 *     Task 4 ──┤
 *     Task 5 ──┤  WAITING
 *     Task 6 ──┤
 *     ...
 *
 *
 * When Task 1 finishes:
 *
 *
 *     Task 4 starts.
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS DIFFERENT FROM THREAD POOL SIZE?
 * ------------------------------------------------------------
 *
 * A thread pool already limits how many tasks execute using
 * its available worker threads.
 *
 *
 * But concurrency control can also represent a BUSINESS or
 * RESOURCE limit.
 *
 *
 * Example:
 *
 *     Thread pool = 20 threads
 *
 *     Database allows only 5 expensive queries concurrently.
 *
 *
 * Then:
 *
 *     Thread pool size = 20
 *     Database concurrency = 5
 *
 *
 * These are different limits.
 *
 *
 * ------------------------------------------------------------
 * COMMON TOOLS
 * ------------------------------------------------------------
 *
 * Java provides several ways to control concurrency:
 *
 *
 *     Fixed-size ExecutorService
 *     Semaphore
 *     Bounded queues
 *     Rate limiting
 *     Custom executors
 *
 *
 * This program uses:
 *
 *     Semaphore
 *
 *
 * ------------------------------------------------------------
 * SEMAPHORE
 * ------------------------------------------------------------
 *
 * A Semaphore manages a number of permits.
 *
 *
 * Example:
 *
 *     Semaphore semaphore =
 *         new Semaphore(3);
 *
 *
 * This means:
 *
 *     3 permits are available.
 *
 *
 * A task calls:
 *
 *     acquire()
 *
 *
 * to obtain a permit.
 *
 *
 * When finished:
 *
 *     release()
 *
 *
 * returns the permit.
 *
 *
 * ------------------------------------------------------------
 * FLOW
 * ------------------------------------------------------------
 *
 *
 *             Semaphore
 *             3 permits
 *                 |
 *        +--------+--------+
 *        |        |        |
 *        v        v        v
 *      Task     Task     Task
 *       1        2        3
 *
 *
 * Task 4:
 *
 *     acquire()
 *
 *         ↓
 *
 *     waits
 *
 *
 * When Task 1 finishes:
 *
 *     release()
 *
 *         ↓
 *
 *     Task 4 gets permit
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * ALWAYS release a permit in a finally block.
 *
 *
 * Correct:
 *
 *     semaphore.acquire();
 *
 *     try {
 *         doWork();
 *     } finally {
 *         semaphore.release();
 *     }
 *
 *
 * Otherwise an exception could permanently consume a permit.
 *
 *
 * Eventually:
 *
 *     no permits
 *
 *     ↓
 *
 *     all future tasks wait forever.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Database concurrency
 *     External API calls
 *     File processing
 *     CPU-heavy work
 *     Limited hardware resources
 *     Browser automation
 *     AI inference requests
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     MAX_CONCURRENT = 3
 *
 * to:
 *
 *     MAX_CONCURRENT = 1
 *
 *
 * Now tasks should execute one at a time.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change it to:
 *
 *     MAX_CONCURRENT = 5
 *
 *
 * Observe that up to five tasks can run simultaneously.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add an exception inside processImage().
 *
 *
 * Verify that the permit is still released.
 *
 *
 * This demonstrates why finally is important.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Remove semaphore control entirely.
 *
 *
 * Compare the number of simultaneous operations.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a Semaphore?
 *
 * A synchronization mechanism that manages a set of permits.
 *
 *
 * 2. What does acquire() do?
 *
 * Obtains a permit, waiting if none is available.
 *
 *
 * 3. What does release() do?
 *
 * Returns a permit.
 *
 *
 * 4. Why should release() be in finally?
 *
 * To guarantee permit release even when the operation throws.
 *
 *
 * 5. Difference between Semaphore and synchronized?
 *
 * synchronized generally provides mutual exclusion for one
 * lock at a time.
 *
 * A Semaphore can allow a configurable number of concurrent
 * holders.
 *
 *
 * 6. Can Semaphore(3) allow three threads concurrently?
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * Thread pool:
 *
 *     Controls worker threads.
 *
 *
 * Semaphore:
 *
 *     Controls access to a limited resource/operation.
 *
 *
 * Think:
 *
 *
 *     1,000 tasks
 *          ↓
 *     concurrency limit = 3
 *          ↓
 *     only 3 expensive operations at once
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 47:
 *
 *     ProducerConsumerScenario.java
 *
 * We will build one of the most important concurrency patterns:
 *
 *     Producer → Queue → Consumer
 *
 * Example:
 *
 *     Orders arrive
 *         ↓
 *     BlockingQueue
 *         ↓
 *     Workers process orders
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrencyLimitScenario {

    /*
     * Maximum number of expensive operations that may run
     * concurrently.
     */
    private static final int MAX_CONCURRENT = 3;

    /*
     * Number of tasks submitted.
     */
    private static final int TOTAL_TASKS = 10;

    /*
     * Semaphore represents the limited resource.
     */
    private static final Semaphore semaphore =
            new Semaphore(MAX_CONCURRENT);

    /*
     * Executor provides worker threads.
     *
     * Notice:
     *
     *     6 worker threads
     *
     * but only:
     *
     *     3 permits
     *
     *
     * Therefore six threads may exist, but only three may enter
     * the protected operation simultaneously.
     */
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(6);

    /*
     * Simulate an expensive image-processing operation.
     */
    private static void processImage(
            int imageId) {

        System.out.println(
                Thread.currentThread().getName()
                        + " processing Image-"
                        + imageId
        );

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Image processing interrupted.",
                    e
            );
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " completed Image-"
                        + imageId
        );
    }

    /*
     * Process one image asynchronously while respecting the
     * concurrency limit.
     */
    private static CompletableFuture<Void>
    processWithLimit(
            int imageId) {

        return CompletableFuture.runAsync(
                () -> {

                    boolean acquired = false;

                    try {

                        /*
                         * Wait until one of the limited permits
                         * becomes available.
                         */
                        semaphore.acquire();

                        acquired = true;

                        /*
                         * At this point this task is one of the
                         * MAX_CONCURRENT operations allowed to run.
                         */
                        processImage(imageId);

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                        throw new RuntimeException(
                                "Failed to acquire permit.",
                                e
                        );

                    } finally {

                        /*
                         * ALWAYS release the permit if we acquired
                         * it.
                         */
                        if (acquired) {

                            semaphore.release();
                        }
                    }
                },
                executor
        );
    }

    public static void main(String[] args) {

        /*
         * Store all asynchronous tasks.
         */
        CompletableFuture<?>[] tasks =
                new CompletableFuture<?>[
                        TOTAL_TASKS
                ];

        /*
         * Submit all tasks.
         */
        for (int i = 0;
             i < TOTAL_TASKS;
             i++) {

            tasks[i] =
                    processWithLimit(i + 1);
        }

        /*
         * Wait until every submitted operation completes.
         */
        CompletableFuture
                .allOf(tasks)
                .join();

        System.out.println(
                "\nAll image-processing tasks completed."
        );

        /*
         * Shutdown executor.
         */
        executor.shutdown();
    }
}
