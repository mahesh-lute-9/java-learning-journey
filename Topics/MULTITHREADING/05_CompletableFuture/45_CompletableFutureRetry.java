/*
 * ============================================================
 * 45 - RETRYING A FAILED ASYNCHRONOUS OPERATION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine your application calls an external payment API.
 *
 * Sometimes the API fails temporarily because of:
 *
 *     Network glitch
 *     Temporary server overload
 *     Connection failure
 *     Rate limiting
 *     Service restart
 *
 *
 * A temporary failure does NOT necessarily mean that the
 * operation should immediately fail permanently.
 *
 *
 * Example:
 *
 *     Attempt 1 → FAILED
 *     Attempt 2 → FAILED
 *     Attempt 3 → SUCCESS
 *
 *
 * ------------------------------------------------------------
 * REQUIREMENT
 * ------------------------------------------------------------
 *
 * Try the operation a limited number of times.
 *
 *
 * Example:
 *
 *     Maximum attempts = 3
 *
 *
 * If:
 *
 *     Attempt 1 → failure
 *
 * retry.
 *
 *
 *     Attempt 2 → failure
 *
 * retry.
 *
 *
 *     Attempt 3 → success
 *
 * return result.
 *
 *
 * If all attempts fail:
 *
 *     return final failure.
 *
 *
 * ------------------------------------------------------------
 * WHY NOT RETRY FOREVER?
 * ------------------------------------------------------------
 *
 * Infinite retries can cause:
 *
 *     Endless network traffic
 *     Increased server load
 *     Resource exhaustion
 *     Very long request times
 *
 *
 * Therefore production retry policies normally have:
 *
 *     Maximum attempts
 *     Timeout
 *     Backoff
 *
 *
 * ------------------------------------------------------------
 * WHAT IS BACKOFF?
 * ------------------------------------------------------------
 *
 * Instead of immediately retrying:
 *
 *
 *     FAIL
 *      ↓
 *     RETRY
 *      ↓
 *     RETRY
 *      ↓
 *     RETRY
 *
 *
 * wait between attempts:
 *
 *
 *     FAIL
 *      ↓
 *    wait
 *      ↓
 *    retry
 *      ↓
 *    wait longer
 *      ↓
 *    retry
 *
 *
 * This is called BACKOFF.
 *
 *
 * ------------------------------------------------------------
 * EXPONENTIAL BACKOFF
 * ------------------------------------------------------------
 *
 * A common strategy is:
 *
 *
 *     Attempt 1 → wait 500 ms
 *     Attempt 2 → wait 1000 ms
 *     Attempt 3 → wait 2000 ms
 *     Attempt 4 → wait 4000 ms
 *
 *
 * Conceptually:
 *
 *
 *     delay = baseDelay * 2^(attempt - 1)
 *
 *
 * ------------------------------------------------------------
 * JITTER
 * ------------------------------------------------------------
 *
 * Production systems often add random variation called JITTER.
 *
 *
 * Why?
 *
 * Imagine 10,000 clients all fail at the same time.
 *
 * If they all retry exactly after 1 second:
 *
 *
 *     10,000 requests
 *           ↓
 *       retry together
 *           ↓
 *     server overloaded
 *           ↓
 *       failures
 *           ↓
 *     retry together again
 *
 *
 * This can create a THUNDERING HERD.
 *
 *
 * Jitter spreads retries over time.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Not every error should be retried.
 *
 *
 * Usually retryable:
 *
 *     Temporary network failure
 *     Service unavailable
 *     Some rate-limit responses
 *
 *
 * Usually NOT retryable:
 *
 *     Invalid input
 *     Authentication failure
 *     Permission denied
 *     Permanent validation failure
 *
 *
 * The exact decision depends on the API/protocol/business
 * requirement.
 *
 *
 * ------------------------------------------------------------
 * THIS PROGRAM
 * ------------------------------------------------------------
 *
 * We will simulate:
 *
 *     Attempt 1 → failure
 *     Attempt 2 → failure
 *     Attempt 3 → success
 *
 *
 * The retry method recursively starts another asynchronous
 * attempt when the previous attempt fails.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     MAX_ATTEMPTS = 3
 *
 * to:
 *
 *     MAX_ATTEMPTS = 2
 *
 *
 * The operation should eventually fail.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make:
 *
 *     failuresBeforeSuccess = 1
 *
 *
 * The second attempt should succeed.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     BASE_DELAY_MS
 *
 * and observe the increasing delay.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Make every attempt fail.
 *
 * Observe that the application eventually gives up instead
 * of retrying forever.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why use retries?
 *
 * To recover from temporary failures.
 *
 *
 * 2. Why should retries have a maximum limit?
 *
 * To prevent infinite retries and resource exhaustion.
 *
 *
 * 3. What is exponential backoff?
 *
 * Increasing the delay between successive retries.
 *
 *
 * 4. What is jitter?
 *
 * Random variation added to retry delays to prevent many
 * clients from retrying simultaneously.
 *
 *
 * 5. Should every exception be retried?
 *
 * No.
 *
 *
 * 6. What is a thundering herd?
 *
 * Many clients simultaneously retrying after a common failure,
 * potentially causing another overload.
 *
 *
 * 7. What is an important production consideration besides
 *    retry count?
 *
 * Overall timeout/deadline.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * Retry strategy:
 *
 *     Operation
 *        ↓
 *     SUCCESS ─────────→ return
 *
 *        OR
 *
 *     FAILURE
 *        ↓
 *     retryable?
 *        ↓
 *     yes
 *        ↓
 *     backoff
 *        ↓
 *     retry
 *
 *
 *     Maximum attempts reached
 *        ↓
 *     final failure
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT PRODUCTION WARNING
 * ------------------------------------------------------------
 *
 * Retrying a request is only safe when you understand whether
 * the operation is idempotent.
 *
 *
 * Example:
 *
 *     GET request
 *
 * is generally easier to retry safely.
 *
 *
 * But:
 *
 *     "Charge credit card ₹50,000"
 *
 * must be designed carefully.
 *
 *
 * A retry could potentially perform the business action twice
 * unless the API supports idempotency keys or equivalent
 * protection.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 46:
 *
 *     CompletableFutureRateLimit.java
 *
 * We will handle another real backend problem:
 *
 *     "Too many asynchronous tasks are being submitted."
 *
 * This introduces concurrency limiting and controlled task
 * execution.
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFutureRetry {

    /*
     * Maximum number of attempts.
     */
    private static final int MAX_ATTEMPTS = 3;

    /*
     * Initial retry delay.
     */
    private static final long BASE_DELAY_MS = 500;

    /*
     * Simulate how many attempts should fail before success.
     */
    private static final int FAILURES_BEFORE_SUCCESS = 2;

    /*
     * Tracks the simulated external service attempts.
     */
    private static int serviceAttempts = 0;

    /*
     * Simulate an external API call.
     */
    private static CompletableFuture<String>
    callPaymentService() {

        return CompletableFuture.supplyAsync(
                () -> {

                    serviceAttempts++;

                    int currentAttempt =
                            serviceAttempts;

                    System.out.println(
                            "Payment API attempt #"
                                    + currentAttempt
                    );

                    /*
                     * Simulate temporary failures.
                     */
                    if (currentAttempt
                            <= FAILURES_BEFORE_SUCCESS) {

                        throw new RuntimeException(
                                "Temporary payment service failure."
                        );
                    }

                    /*
                     * Third attempt succeeds in this example.
                     */
                    return "PAYMENT SUCCESS";
                }
        );
    }

    /*
     * Retry the payment operation.
     *
     * attempt represents the current attempt number.
     */
    private static CompletableFuture<String>
    callWithRetry(int attempt) {

        /*
         * Start the operation.
         */
        return callPaymentService()

                /*
                 * If the operation succeeds, the result simply
                 * continues through the pipeline.
                 */
                .handle(
                        (result, error) -> {

                            /*
                             * SUCCESS
                             */
                            if (error == null) {

                                return CompletableFuture
                                        .completedFuture(
                                                result
                                        );
                            }

                            /*
                             * FAILURE
                             *
                             * Check whether we still have attempts
                             * remaining.
                             */
                            if (attempt
                                    >= MAX_ATTEMPTS) {

                                CompletableFuture<String>
                                        failed =
                                        new CompletableFuture<>();

                                failed.completeExceptionally(
                                        error
                                );

                                return failed;
                            }

                            /*
                             * Calculate exponential backoff.
                             *
                             * Attempt 1:
                             *
                             *     500 ms
                             *
                             * Attempt 2:
                             *
                             *     1000 ms
                             *
                             * Attempt 3:
                             *
                             *     2000 ms
                             */
                            long delay =
                                    BASE_DELAY_MS
                                            * (1L
                                            << (attempt - 1));

                            System.out.println(
                                    "Attempt "
                                            + attempt
                                            + " failed."
                            );

                            System.out.println(
                                    "Retrying after "
                                            + delay
                                            + " ms..."
                            );

                            /*
                             * Wait before retrying.
                             *
                             * This is a simple learning example.
                             * Production systems should generally
                             * use a dedicated scheduler rather than
                             * blocking an asynchronous worker with
                             * Thread.sleep().
                             */
                            try {

                                Thread.sleep(
                                        delay
                                );

                            } catch (
                                    InterruptedException e) {

                                Thread.currentThread()
                                        .interrupt();

                                CompletableFuture<String>
                                        interrupted =
                                        new CompletableFuture<>();

                                interrupted
                                        .completeExceptionally(
                                                e
                                        );

                                return interrupted;
                            }

                            /*
                             * Start the next attempt.
                             */
                            return callWithRetry(
                                    attempt + 1
                            );
                        }
                )

                /*
                 * handle() above produces a nested future:
                 *
                 *     CompletableFuture<
                 *         CompletableFuture<String>
                 *     >
                 *
                 * thenCompose() flattens it.
                 */
                .thenCompose(
                        future -> future
                );
    }

    public static void main(String[] args) {

        /*
         * Start with attempt #1.
         */
        CompletableFuture<String> result =
                callWithRetry(1);

        /*
         * Handle final success/failure.
         */
        result.whenComplete(
                (value, error) -> {

                    if (error != null) {

                        System.out.println(
                                "\nFinal result: PAYMENT FAILED"
                        );

                        System.out.println(
                                "Reason: "
                                        + error.getMessage()
                        );

                    } else {

                        System.out.println(
                                "\nFinal result: "
                                        + value
                        );
                    }
                }
        );

        /*
         * Wait only so this standalone console program doesn't
         * exit before the asynchronous pipeline completes.
         */
        result.join();
    }
}
