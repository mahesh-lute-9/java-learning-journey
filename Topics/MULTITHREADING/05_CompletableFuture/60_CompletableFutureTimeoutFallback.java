/*
 * ============================================================
 * 60 - COMPLETABLEFUTURE TIMEOUT + FALLBACK
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce application displaying a product page.
 *
 * The page needs data from multiple services:
 *
 *     Product Service
 *     Recommendation Service
 *     Review Service
 *
 *
 * But the recommendation service is slow.
 *
 *
 * We don't want the entire page to fail just because
 * recommendations are unavailable.
 *
 *
 * REQUIREMENT:
 * ------------------------------------------------------------
 *
 *     Try to get recommendations.
 *
 *     If the service responds quickly:
 *         use the real recommendations.
 *
 *     If it takes too long:
 *         use a fallback.
 *
 *
 * Example:
 *
 *     Recommendation Service
 *             ↓
 *       response within 2 sec
 *             ↓
 *       show recommendations
 *
 *
 *     OR
 *
 *     timeout
 *             ↓
 *       use fallback
 *             ↓
 *       "Popular products"
 *
 *
 * ------------------------------------------------------------
 * WHY CompletableFuture?
 * ------------------------------------------------------------
 *
 * CompletableFuture allows us to build asynchronous pipelines
 * without manually coordinating every thread.
 *
 *
 * Important methods:
 *
 *     supplyAsync()
 *     thenApply()
 *     exceptionally()
 *     orTimeout()
 *     completeOnTimeout()
 *
 *
 * ------------------------------------------------------------
 * orTimeout()
 * ------------------------------------------------------------
 *
 * Example:
 *
 *
 *     future.orTimeout(
 *         2,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * Meaning:
 *
 *     If the future does not complete within 2 seconds,
 *     complete it exceptionally with a timeout.
 *
 *
 * ------------------------------------------------------------
 * completeOnTimeout()
 * ------------------------------------------------------------
 *
 * Example:
 *
 *
 *     future.completeOnTimeout(
 *         fallback,
 *         2,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * Meaning:
 *
 *     If the operation doesn't finish within the timeout,
 *     complete the future normally using the fallback value.
 *
 *
 * This is especially useful when the application can continue
 * with degraded/default data.
 *
 *
 * ------------------------------------------------------------
 * FALLBACK
 * ------------------------------------------------------------
 *
 * A fallback means:
 *
 *
 *     Primary service unavailable
 *             ↓
 *        alternative result
 *
 *
 * Example:
 *
 *     Recommendation Service
 *             ↓
 *          timeout
 *             ↓
 *     Popular Products
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DISTINCTION
 * ------------------------------------------------------------
 *
 * orTimeout():
 *
 *     timeout → exceptional completion
 *
 *
 * completeOnTimeout():
 *
 *     timeout → normal completion with fallback value
 *
 *
 * ------------------------------------------------------------
 * EXCEPTION HANDLING
 * ------------------------------------------------------------
 *
 * exceptionally() can provide a fallback for an exceptional
 * completion.
 *
 *
 * Example:
 *
 *
 *     future.exceptionally(
 *         error -> fallback
 *     );
 *
 *
 * This can handle failures such as:
 *
 *     RuntimeException
 *     TimeoutException
 *     Service failure
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     API aggregation
 *     Recommendation systems
 *     Search suggestions
 *     External API calls
 *     Microservices
 *     Dashboard widgets
 *     Optional application features
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     SERVICE_DELAY = 1000
 *
 * to:
 *
 *     SERVICE_DELAY = 4000
 *
 *
 * Observe the fallback.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     completeOnTimeout()
 *
 * to:
 *
 *     orTimeout()
 *
 *
 * Observe the difference in behavior.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make the service throw an exception.
 *
 *
 * Observe how exceptionally() handles it.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Remove the fallback.
 *
 *
 * Ask:
 *
 *     Should an optional recommendation service be allowed to
 *     bring down the entire product page?
 *
 *
 * Usually:
 *
 *     No.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What does orTimeout() do?
 *
 * Completes the CompletableFuture exceptionally if it does not
 * complete within the specified time.
 *
 *
 * 2. What does completeOnTimeout() do?
 *
 * Completes the CompletableFuture with a fallback value if the
 * timeout expires first.
 *
 *
 * 3. What is a fallback?
 *
 * An alternative result used when the primary operation fails
 * or becomes unavailable.
 *
 *
 * 4. What does exceptionally() do?
 *
 * Provides recovery logic for exceptional completion.
 *
 *
 * 5. Is timeout handling important in distributed systems?
 *
 * Extremely important.
 *
 * Waiting indefinitely for another service can consume threads,
 * connections, memory, and other resources.
 *
 *
 * 6. Is a timeout the same as cancelling the underlying work?
 *
 * Not necessarily.
 *
 * A CompletableFuture timeout changes the future's completion
 * behavior; it does not automatically mean that the underlying
 * task has stopped executing.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * External Service
 *       ↓
 *   CompletableFuture
 *       ↓
 *    timeout?
 *      /   \
 *    NO     YES
 *    ↓       ↓
 * real     fallback
 * result     result
 *
 *
 * Good backend systems don't assume every dependency will
 * always respond quickly.
 *
 *
 * ============================================================
 * THIS COMPLETES THE CORE 60-PROGRAM SCENARIO SET.
 * ============================================================
 *
 * The goal was NOT to create 100+ tiny programs.
 *
 * Instead, these programs progressively covered the important
 * Java concurrency concepts through realistic situations.
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeoutFallback {

    /*
     * Simulated external recommendation service delay.
     *
     * Change this to 1000 and then 4000 to observe the
     * difference.
     */
    private static final int SERVICE_DELAY =
            3000;

    /*
     * Timeout allowed for the recommendation service.
     */
    private static final int TIMEOUT_SECONDS =
            2;

    /*
     * Simulate an external recommendation service.
     */
    private static CompletableFuture<String>
    fetchRecommendations() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Recommendation service called..."
                    );

                    try {

                        /*
                         * Simulate network/service delay.
                         */
                        Thread.sleep(
                                SERVICE_DELAY
                        );

                    } catch (InterruptedException e) {

                        /*
                         * Restore interruption status.
                         */
                        Thread.currentThread()
                                .interrupt();

                        throw new RuntimeException(
                                "Recommendation service interrupted.",
                                e
                        );
                    }

                    System.out.println(
                            "Recommendation service responded."
                    );

                    return
                            "Recommended: Laptop, Mouse, Keyboard";
                }
        );
    }

    public static void main(String[] args)
            throws Exception {

        System.out.println(
                "Loading product page..."
        );

        /*
         * Start the external service asynchronously.
         */
        CompletableFuture<String> recommendations =
                fetchRecommendations();

        /*
         * If the service does not respond within the
         * configured timeout, use a fallback value.
         *
         * Notice:
         *
         *     completeOnTimeout()
         *
         * produces a NORMAL result rather than an exceptional
         * timeout result.
         */
        CompletableFuture<String> result =
                recommendations.completeOnTimeout(
                        "Fallback: Popular Products",
                        TIMEOUT_SECONDS,
                        TimeUnit.SECONDS
                );

        /*
         * Add general exception recovery as well.
         *
         * This handles failures other than the normal fallback
         * path.
         */
        result =
                result.exceptionally(
                        error ->
                                "Fallback: Recommendations unavailable"
                );

        /*
         * Wait for the final result.
         *
         * In a larger application, the result would normally
         * continue through an asynchronous pipeline instead of
         * blocking the calling thread here.
         */
        String recommendationResult =
                result.get();

        System.out.println(
                "\nProduct page result:"
        );

        System.out.println(
                recommendationResult
        );

        System.out.println(
                "\nProduct page loaded successfully."
        );
    }
}
