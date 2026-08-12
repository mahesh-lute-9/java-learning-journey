/*
 * ============================================================
 * 36 - SCHEDULED TASKS USING ScheduledExecutorService
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend server that needs to perform health checks.
 *
 * Requirement:
 *
 *     Check server health every 2 seconds.
 *
 *
 * Other real-world examples:
 *
 *     - Cache cleanup
 *     - Database cleanup
 *     - Metrics collection
 *     - Session expiration
 *     - Periodic synchronization
 *     - Monitoring
 *     - Background maintenance
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * We could manually do:
 *
 *
 *     while (true) {
 *
 *         checkHealth();
 *
 *         Thread.sleep(2000);
 *     }
 *
 *
 * But this mixes:
 *
 *     task logic
 *
 * with:
 *
 *     scheduling logic
 *
 *
 * Java provides a cleaner solution:
 *
 *     ScheduledExecutorService
 *
 *
 * ------------------------------------------------------------
 * WHAT IS ScheduledExecutorService?
 * ------------------------------------------------------------
 *
 * It is an ExecutorService capable of scheduling tasks:
 *
 *     - After a delay
 *     - At a fixed rate
 *     - With a fixed delay between executions
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 *
 * schedule()
 *
 *     Run a task once after a delay.
 *
 *
 * scheduleAtFixedRate()
 *
 *     Run periodically based on a fixed schedule.
 *
 *
 * scheduleWithFixedDelay()
 *
 *     Wait for the previous execution to finish, then wait
 *     for the specified delay before starting the next one.
 *
 *
 * ------------------------------------------------------------
 * FIXED RATE VS FIXED DELAY
 * ------------------------------------------------------------
 *
 *
 * scheduleAtFixedRate():
 *
 *
 *     Start
 *       ↓
 *     task
 *       ↓
 *     next scheduled time
 *       ↓
 *     task
 *       ↓
 *     next scheduled time
 *
 *
 * The period is measured from scheduled start times.
 *
 *
 * scheduleWithFixedDelay():
 *
 *
 *     Start
 *       ↓
 *     task
 *       ↓
 *     FINISH
 *       ↓
 *     delay
 *       ↓
 *     task
 *
 *
 * The delay is measured after the previous execution finishes.
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * Suppose task takes 1 second and period/delay is 2 seconds.
 *
 *
 * FIXED RATE:
 *
 *     start → 1s task → next scheduled start
 *                    2s interval from starts
 *
 *
 * FIXED DELAY:
 *
 *     start → 1s task → wait 2s → start again
 *
 *
 * Therefore fixed delay generally results in:
 *
 *     execution time + delay
 *
 * between starts.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT PRODUCTION DETAIL
 * ------------------------------------------------------------
 *
 * If a periodic task throws an exception and the exception
 * escapes the Runnable, future executions of that periodic task
 * can be suppressed.
 *
 *
 * Therefore periodic tasks should normally handle expected
 * exceptions internally.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     scheduleAtFixedRate()
 *
 * to:
 *
 *     scheduleWithFixedDelay()
 *
 *
 * Observe the difference.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make the health check take:
 *
 *     3000 ms
 *
 * while the period is:
 *
 *     2000 ms
 *
 *
 * Think about what happens with fixed-rate scheduling.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Throw an exception from the task.
 *
 * Observe what happens to subsequent executions.
 *
 *
 * Then catch the exception inside the Runnable and compare.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     initialDelay = 0
 *
 * to:
 *
 *     initialDelay = 5
 *
 *
 * Understand the difference between:
 *
 *     initial delay
 *
 * and:
 *
 *     recurring period
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is ScheduledExecutorService?
 *
 * 2. Difference between scheduleAtFixedRate() and
 *    scheduleWithFixedDelay()?
 *
 * 3. What happens if a periodic task throws an exception?
 *
 * 4. Why should periodic tasks handle exceptions carefully?
 *
 * 5. Can scheduled tasks be cancelled?
 *
 * Yes. The returned ScheduledFuture can be cancelled.
 *
 * 6. Why prefer ScheduledExecutorService over manually calling
 *    Thread.sleep() in a loop?
 *
 * It separates scheduling from task execution and integrates
 * with executor lifecycle and cancellation.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * schedule()
 *     ↓
 * one-time delayed task
 *
 *
 * scheduleAtFixedRate()
 *     ↓
 * periodic fixed-rate execution
 *
 *
 * scheduleWithFixedDelay()
 *     ↓
 * delay AFTER each execution
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 37:
 *
 *     CompletableFutureBasic.java
 *
 * We will move from Future to one of the most important modern
 * Java asynchronous programming APIs:
 *
 *     CompletableFuture
 *
 * ============================================================
 */

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorScenario {

    /*
     * Number of health checks we want to observe in this demo.
     */
    private static final int MAX_CHECKS = 5;

    /*
     * Count completed health checks.
     */
    private static int checkCount = 0;

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create a scheduler.
         *
         * A single worker is enough for this simple health-check
         * example because we want one check at a time.
         */
        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        /*
         * Schedule the health check.
         *
         * Initial delay:
         *
         *     1 second
         *
         * Period:
         *
         *     2 seconds
         *
         * The task runs repeatedly at a fixed rate.
         */
        scheduler.scheduleAtFixedRate(
                () -> {

                    try {

                        checkCount++;

                        System.out.println(
                                Thread.currentThread().getName()
                                        + " performing health check #"
                                        + checkCount
                        );

                        /*
                         * Simulate health-check work.
                         */
                        Thread.sleep(500);

                        System.out.println(
                                "Server status: HEALTHY"
                        );

                        /*
                         * Stop the demo after a fixed number of
                         * checks.
                         */
                        if (checkCount >= MAX_CHECKS) {

                            System.out.println(
                                    "Maximum checks reached."
                            );

                            scheduler.shutdown();
                        }

                    } catch (InterruptedException e) {

                        /*
                         * Restore interrupt status.
                         */
                        Thread.currentThread().interrupt();

                        System.out.println(
                                "Health check interrupted."
                        );

                    } catch (Exception e) {

                        /*
                         * IMPORTANT:
                         *
                         * Handle exceptions inside a periodic
                         * task so an unexpected exception does
                         * not silently terminate future executions.
                         */
                        System.out.println(
                                "Health check failed: "
                                        + e.getMessage()
                        );
                    }

                },

                /*
                 * Initial delay.
                 */
                1,

                /*
                 * Period.
                 */
                2,

                TimeUnit.SECONDS
        );

        /*
         * Wait for the scheduler to terminate.
         */
        scheduler.awaitTermination(
                20,
                TimeUnit.SECONDS
        );

        /*
         * If it is still running after the timeout, request
         * shutdown.
         */
        if (!scheduler.isShutdown()) {

            scheduler.shutdownNow();
        }

        System.out.println(
                "Scheduler stopped."
        );
    }
}
