/*
 * ============================================================
 * 34 - FUTURE TIMEOUT AND CANCELLATION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine your backend calls an external payment service.
 *
 * Normally:
 *
 *     Payment Service → responds in 2 seconds
 *
 * But today it is slow:
 *
 *     Payment Service → takes 10 seconds
 *
 *
 * Your API cannot keep the user waiting forever.
 *
 * REQUIREMENT:
 *
 *     Wait at most 3 seconds.
 *
 * If the task doesn't finish:
 *
 *     1. Stop waiting.
 *     2. Cancel the task.
 *     3. Handle the timeout.
 *     4. Return a fallback/error response.
 *
 *
 * ------------------------------------------------------------
 * WHY future.get() CAN BE A PROBLEM
 * ------------------------------------------------------------
 *
 * If we write:
 *
 *     future.get();
 *
 * the calling thread can wait indefinitely if the task never
 * finishes.
 *
 *
 * Instead:
 *
 *     future.get(3, TimeUnit.SECONDS);
 *
 *
 * means:
 *
 *     "Wait for at most 3 seconds."
 *
 *
 * ------------------------------------------------------------
 * TIMEOUT FLOW
 * ------------------------------------------------------------
 *
 *
 * Main Thread
 *      |
 *      | submit()
 *      v
 * Payment Task
 *      |
 *      | slow operation
 *      |
 *      v
 * future.get(3 seconds)
 *      |
 *      +------ result ready ------> SUCCESS
 *      |
 *      +------ timeout -----------> TimeoutException
 *                                      |
 *                                      v
 *                                  cancel(true)
 *
 *
 * ------------------------------------------------------------
 * WHAT IS cancel(true)?
 * ------------------------------------------------------------
 *
 *     future.cancel(true)
 *
 * attempts to cancel the task.
 *
 * If the task is currently running, the executor may interrupt
 * the worker thread.
 *
 *
 * IMPORTANT:
 *
 * Cancellation is cooperative.
 *
 * A task should respond properly to interruption.
 *
 *
 * ------------------------------------------------------------
 * cancel(false) VS cancel(true)
 * ------------------------------------------------------------
 *
 * cancel(false):
 *
 *     Do not interrupt a task that is already running.
 *
 *
 * cancel(true):
 *
 *     Attempt to interrupt a running task.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Cancellation does NOT guarantee that arbitrary code instantly
 * stops.
 *
 * The task must cooperate with interruption.
 *
 *
 * For example:
 *
 *     Thread.sleep()
 *
 * responds to interruption.
 *
 *
 * But poorly designed code that ignores interruption may
 * continue running.
 *
 *
 * ------------------------------------------------------------
 * TIMEOUT VS CANCELLATION
 * ------------------------------------------------------------
 *
 * These are different concepts.
 *
 *
 * TIMEOUT:
 *
 *     "I am no longer willing to wait."
 *
 *
 * CANCELLATION:
 *
 *     "Try to stop the task."
 *
 *
 * You may use both together:
 *
 *
 *     get(timeout)
 *          ↓
 *     timeout
 *          ↓
 *     cancel(true)
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD EXAMPLES
 * ------------------------------------------------------------
 *
 *     Payment API
 *     Shipping API
 *     External REST API
 *     Database operation
 *     File processing
 *     AI model request
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     TIMEOUT = 3 seconds
 *
 * to:
 *
 *     TIMEOUT = 10 seconds
 *
 *
 * The task should complete successfully.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change the task to:
 *
 *     Thread.sleep(10000);
 *
 *
 * while timeout remains:
 *
 *     3 seconds
 *
 *
 * Observe cancellation.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     cancel(true)
 *
 * with:
 *
 *     cancel(false)
 *
 *
 * Observe the difference.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Remove interruption handling from the task.
 *
 * Think about why cancellation becomes less effective when
 * the task ignores interruption.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is future.get() dangerous for external operations?
 *
 * Because it can block indefinitely.
 *
 *
 * 2. How do you apply a timeout to Future.get()?
 *
 *     future.get(timeout, unit)
 *
 *
 * 3. What exception indicates that the timeout expired?
 *
 *     TimeoutException
 *
 *
 * 4. Does TimeoutException automatically cancel the task?
 *
 * No.
 *
 * You must explicitly cancel it if appropriate.
 *
 *
 * 5. What does cancel(true) do?
 *
 * It attempts to cancel the task and interrupt its executing
 * thread.
 *
 *
 * 6. Is cancellation guaranteed?
 *
 * No. It is cooperative.
 *
 *
 * 7. Difference between timeout and cancellation?
 *
 * Timeout controls how long the caller waits.
 *
 * Cancellation requests that the underlying task stop.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Production-style asynchronous code should think about:
 *
 *     SUCCESS
 *     FAILURE
 *     TIMEOUT
 *     CANCELLATION
 *
 *
 * Don't design asynchronous code assuming:
 *
 *     "The task will always finish."
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 35:
 *
 *     CompletionServiceScenario.java
 *
 * We will solve a common problem:
 *
 *     Several tasks run concurrently, but we want to process
 *     results AS SOON AS EACH TASK FINISHES rather than waiting
 *     for them in submission order.
 *
 * ============================================================
 */

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTimeoutCancellation {

    /*
     * Maximum amount of time the caller is willing to wait.
     */
    private static final int TIMEOUT_SECONDS = 3;

    /*
     * Simulates a slow external payment service.
     */
    static class PaymentTask
            implements Callable<String> {

        @Override
        public String call()
                throws Exception {

            System.out.println(
                    Thread.currentThread().getName()
                            + " contacting payment service..."
            );

            try {

                /*
                 * Simulate a slow external API.
                 *
                 * This takes longer than our timeout.
                 */
                Thread.sleep(8000);

                /*
                 * This line should normally not be reached in
                 * this example because the caller times out.
                 */
                return "PAYMENT SUCCESS";

            } catch (InterruptedException e) {

                /*
                 * The task was cancelled/interrupted.
                 */
                System.out.println(
                        Thread.currentThread().getName()
                                + " payment task interrupted."
                );

                /*
                 * Restore the interrupted status.
                 */
                Thread.currentThread().interrupt();

                /*
                 * Re-throw so the task terminates.
                 */
                throw e;
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create executor.
         */
        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        /*
         * Submit the payment task.
         */
        Future<String> future =
                executor.submit(
                        new PaymentTask()
                );

        System.out.println(
                "Payment request submitted."
        );

        try {

            /*
             * Wait for at most 3 seconds.
             *
             * If the task finishes before the timeout, we get
             * the result.
             *
             * Otherwise TimeoutException is thrown.
             */
            String result =
                    future.get(
                            TIMEOUT_SECONDS,
                            TimeUnit.SECONDS
                    );

            System.out.println(
                    "Payment result: "
                            + result
            );

        } catch (TimeoutException e) {

            /*
             * The caller has waited long enough.
             */
            System.out.println(
                    "Payment service timed out."
            );

            /*
             * Attempt to cancel the running task.
             *
             * true means:
             *
             *     attempt to interrupt the executing worker.
             */
            boolean cancelled =
                    future.cancel(true);

            System.out.println(
                    "Cancellation requested: "
                            + cancelled
            );

        } catch (ExecutionException e) {

            /*
             * The Callable itself failed.
             */
            System.out.println(
                    "Payment task failed: "
                            + e.getCause()
            );

        } catch (CancellationException e) {

            /*
             * The Future was already cancelled.
             */
            System.out.println(
                    "Payment task was cancelled."
            );
        }

        /*
         * Stop accepting new tasks.
         */
        executor.shutdown();

        /*
         * Wait briefly for the worker to terminate.
         */
        if (!executor.awaitTermination(
                5,
                TimeUnit.SECONDS)) {

            /*
             * If it still hasn't stopped, make another shutdown
             * attempt.
             */
            executor.shutdownNow();
        }

        System.out.println(
                "Application completed."
        );
    }
}
