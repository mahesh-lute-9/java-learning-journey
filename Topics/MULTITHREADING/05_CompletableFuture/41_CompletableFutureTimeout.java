/*
 * ============================================================
 * 41 - CompletableFuture TIMEOUT HANDLING
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine your backend calls an external AI/payment/search API.
 *
 * Normally:
 *
 *     API responds in 1 second.
 *
 * But sometimes:
 *
 *     API becomes slow or completely hangs.
 *
 *
 * Your application should NOT wait forever.
 *
 *
 * REQUIREMENT:
 * ------------------------------------------------------------
 *
 *     Give the external operation a maximum time limit.
 *
 *
 * If it finishes in time:
 *
 *     Return the actual result.
 *
 *
 * If it takes too long:
 *
 *     Either:
 *
 *     1. Fail with a timeout
 *
 * OR
 *
 *     2. Return a fallback value.
 *
 *
 * ============================================================
 * TWO IMPORTANT METHODS
 * ============================================================
 *
 *
 * 1. orTimeout()
 *
 *
 *     future.orTimeout(
 *         3,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * Meaning:
 *
 *     "If this future does not complete within 3 seconds,
 *      complete it exceptionally with a TimeoutException."
 *
 *
 * ------------------------------------------------------------
 *
 *
 * 2. completeOnTimeout()
 *
 *
 *     future.completeOnTimeout(
 *         "Fallback",
 *         3,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * Meaning:
 *
 *     "If this future does not complete within 3 seconds,
 *      complete it normally using this fallback value."
 *
 *
 * ============================================================
 * DIFFERENCE
 * ============================================================
 *
 *
 * orTimeout()
 *
 *     Slow
 *       ↓
 *     TIMEOUT
 *       ↓
 *     exceptional completion
 *
 *
 * completeOnTimeout()
 *
 *     Slow
 *       ↓
 *     TIMEOUT
 *       ↓
 *     fallback result
 *
 *
 * ------------------------------------------------------------
 * EASY MEMORY TRICK
 * ------------------------------------------------------------
 *
 *
 * orTimeout():
 *
 *     "Timeout = ERROR"
 *
 *
 * completeOnTimeout():
 *
 *     "Timeout = FALLBACK"
 *
 *
 * ============================================================
 * IMPORTANT
 * ============================================================
 *
 * These methods control completion of the CompletableFuture.
 *
 * They should NOT be confused with:
 *
 *     automatically killing arbitrary underlying work.
 *
 *
 * Timeout and cancellation are related but different concepts.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD EXAMPLES
 * ------------------------------------------------------------
 *
 * orTimeout():
 *
 *     Payment verification
 *     Critical database operation
 *     Security check
 *
 *
 * completeOnTimeout():
 *
 *     Recommendation service
 *     Analytics
 *     Optional profile information
 *     Non-critical dashboard data
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     TASK_TIME = 2000
 *
 * and:
 *
 *     TIMEOUT = 3
 *
 *
 * The task should complete successfully.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     TASK_TIME = 5000
 *
 * and keep:
 *
 *     TIMEOUT = 3
 *
 *
 * Observe orTimeout().
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     orTimeout()
 *
 * with:
 *
 *     completeOnTimeout(
 *         "Fallback Response",
 *         ...
 *     )
 *
 *
 * Observe the difference.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add:
 *
 *     exceptionally()
 *
 * after orTimeout().
 *
 *
 * This allows you to convert the timeout failure into a custom
 * response.
 *
 *
 * ============================================================
 * INTERVIEW QUESTIONS
 * ============================================================
 *
 * 1. What does orTimeout() do?
 *
 * Completes the CompletableFuture exceptionally if it does not
 * complete before the specified timeout.
 *
 *
 * 2. What does completeOnTimeout() do?
 *
 * Completes the CompletableFuture with a fallback value if the
 * timeout expires first.
 *
 *
 * 3. Difference between timeout and cancellation?
 *
 * Timeout controls completion/waiting behavior.
 *
 * Cancellation requests that the computation be cancelled.
 *
 *
 * 4. When would you use completeOnTimeout()?
 *
 * When a fallback response is acceptable.
 *
 *
 * 5. When would you use orTimeout()?
 *
 * When failure is preferable to returning potentially invalid
 * or incomplete data.
 *
 *
 * ============================================================
 * KEY TAKEAWAY
 * ============================================================
 *
 *
 *             Slow Operation
 *                  |
 *              3 second limit
 *                  |
 *          +-------+-------+
 *          |               |
 *       SUCCESS          TIMEOUT
 *          |               |
 *          v               v
 *       Result       +------+------+
 *                    |             |
 *              orTimeout()   completeOnTimeout()
 *                    |             |
 *                  ERROR        FALLBACK
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 42:
 *
 *     CompletableFutureAsyncStages.java
 *
 * We will understand:
 *
 *     thenApply()
 *     thenApplyAsync()
 *
 * and why choosing the right one matters when expensive work
 * is added to an asynchronous pipeline.
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeout {

    /*
     * Simulated external API response time.
     */
    private static final int TASK_TIME = 5000;

    /*
     * Maximum allowed time.
     */
    private static final int TIMEOUT = 3;

    /*
     * Simulate an external service.
     */
    private static CompletableFuture<String>
    callExternalService() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Calling external service..."
                    );

                    try {

                        Thread.sleep(
                                TASK_TIME
                        );

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                        throw new RuntimeException(
                                "External call interrupted.",
                                e
                        );
                    }

                    return "External API response";
                }
        );
    }

    public static void main(String[] args) {

        System.out.println(
                "========== orTimeout() =========="
        );

        /*
         * Start the asynchronous operation.
         *
         * orTimeout() changes the completion behavior of the
         * CompletableFuture if the operation takes too long.
         */
        CompletableFuture<String> timeoutFuture =
                callExternalService()
                        .orTimeout(
                                TIMEOUT,
                                TimeUnit.SECONDS
                        )
                        .exceptionally(
                                error -> {

                                    System.out.println(
                                            "Request timed out or failed."
                                    );

                                    return "ERROR RESPONSE";
                                }
                        );

        /*
         * join() retrieves the final result.
         */
        System.out.println(
                "Result: "
                        + timeoutFuture.join()
        );


        System.out.println(
                "\n========== completeOnTimeout() =========="
        );

        /*
         * Start another asynchronous operation.
         *
         * If it doesn't complete within the timeout,
         * return a fallback value.
         */
        CompletableFuture<String> fallbackFuture =
                callExternalService()
                        .completeOnTimeout(
                                "Cached/Fallback Response",
                                TIMEOUT,
                                TimeUnit.SECONDS
                        );

        System.out.println(
                "Result: "
                        + fallbackFuture.join()
        );
    }
}
