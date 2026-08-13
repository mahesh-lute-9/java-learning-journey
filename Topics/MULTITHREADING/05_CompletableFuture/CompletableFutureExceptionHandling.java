/*
 * ============================================================
 * 40 - EXCEPTION HANDLING WITH CompletableFuture
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an application fetching a user's profile from an
 * external service.
 *
 * Normally:
 *
 *     Fetch User
 *        ↓
 *     Process User
 *        ↓
 *     Return Profile
 *
 *
 * But external operations can fail:
 *
 *     Network failure
 *     Database failure
 *     Service unavailable
 *     Invalid data
 *     Timeout
 *
 *
 * We need to handle these failures without crashing the entire
 * application.
 *
 *
 * ------------------------------------------------------------
 * THREE IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 * CompletableFuture provides several ways to handle exceptions.
 *
 *
 *     exceptionally()
 *     handle()
 *     whenComplete()
 *
 *
 * They look similar, but their purposes are different.
 *
 *
 * ============================================================
 * 1. exceptionally()
 * ============================================================
 *
 * Think:
 *
 *     "If something fails, give me a fallback value."
 *
 *
 * Example:
 *
 *     future.exceptionally(
 *         error -> "Default User"
 *     );
 *
 *
 * If the previous stage succeeds:
 *
 *     exceptionally()
 *
 * is skipped.
 *
 *
 * If it fails:
 *
 *     exceptionally()
 *
 * can recover with another value.
 *
 *
 * ------------------------------------------------------------
 * 2. handle()
 * ------------------------------------------------------------
 *
 * Think:
 *
 *     "Give me both the result AND the error, and let me decide
 *      what the next result should be."
 *
 *
 * It runs whether the previous stage:
 *
 *     succeeds
 *
 * OR:
 *
 *     fails
 *
 *
 * Conceptually:
 *
 *
 *     result + error
 *          |
 *        handle()
 *          |
 *          v
 *     new result
 *
 *
 * ------------------------------------------------------------
 * 3. whenComplete()
 * ------------------------------------------------------------
 *
 * Think:
 *
 *     "I want to observe what happened."
 *
 *
 * It is useful for:
 *
 *     Logging
 *     Metrics
 *     Auditing
 *     Cleanup
 *
 *
 * It does NOT primarily mean:
 *
 *     "Convert failure into a fallback result."
 *
 *
 * ------------------------------------------------------------
 * EASY MEMORY TRICK
 * ------------------------------------------------------------
 *
 *
 * exceptionally()
 *
 *     RECOVER
 *
 *
 * handle()
 *
 *     TRANSFORM SUCCESS OR FAILURE
 *
 *
 * whenComplete()
 *
 *     OBSERVE
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     fetchUser()
 *
 * fails.
 *
 *
 * exceptionally():
 *
 *     "Return Guest User."
 *
 *
 * handle():
 *
 *     "If successful, format user.
 *      If failed, return error response."
 *
 *
 * whenComplete():
 *
 *     "Log success/failure."
 *
 *
 * ------------------------------------------------------------
 * EXCEPTION PROPAGATION
 * ------------------------------------------------------------
 *
 * CompletableFuture stages form a chain.
 *
 *
 *     Stage A
 *       ↓
 *     Stage B
 *       ↓
 *     Stage C
 *
 *
 * If Stage A fails:
 *
 *
 *     Stage A → FAILED
 *       ↓
 *     Stage B → skipped/exceptional
 *       ↓
 *     Stage C → skipped/exceptional
 *
 *
 * until an appropriate recovery/handling stage is reached.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Exceptions in CompletableFuture are represented as an
 * exceptional completion.
 *
 *
 * When using:
 *
 *     join()
 *
 * exceptional completion is commonly observed as:
 *
 *     CompletionException
 *
 *
 * When using:
 *
 *     get()
 *
 * you commonly see:
 *
 *     ExecutionException
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     fetchUser()
 *
 * so it succeeds.
 *
 *
 * Observe:
 *
 *     exceptionally()
 *
 * is not executed.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace exceptionally() with handle().
 *
 * Handle both:
 *
 *     result
 *
 * and:
 *
 *     exception
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add:
 *
 *     whenComplete()
 *
 * before exceptionally().
 *
 *
 * Observe that whenComplete() can be used for logging while
 * exceptionally() performs recovery.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add another thenApply() after exceptionally().
 *
 *
 * Example:
 *
 *     exceptionally()
 *          ↓
 *     thenApply()
 *
 *
 * Notice that after recovery, the pipeline can continue normally.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What does exceptionally() do?
 *
 * Provides a recovery value when the pipeline completes
 * exceptionally.
 *
 *
 * 2. What does handle() do?
 *
 * Handles both successful and exceptional completion and
 * produces a new result.
 *
 *
 * 3. What does whenComplete() do?
 *
 * Observes completion for side effects such as logging.
 *
 *
 * 4. Does whenComplete() normally recover from an exception?
 *
 * No. It is primarily for observation.
 *
 *
 * 5. Difference between get() and join() for exceptions?
 *
 * get() commonly wraps failures in ExecutionException.
 *
 * join() commonly wraps failures in CompletionException.
 *
 *
 * 6. Can an exception skip intermediate stages?
 *
 * Yes. An exceptional completion propagates through dependent
 * stages until handled.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * exceptionally()
 *     ↓
 * RECOVER
 *
 *
 * handle()
 *     ↓
 * RECOVER / TRANSFORM
 *
 *
 * whenComplete()
 *     ↓
 * OBSERVE / LOG
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 41:
 *
 *     CompletableFutureTimeout.java
 *
 * We will use Java's CompletableFuture timeout APIs to handle
 * slow asynchronous operations without manually managing
 * Future.get(timeout).
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandling {

    /*
     * Simulates an external user service.
     */
    private static CompletableFuture<String>
    fetchUser() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching user..."
                    );

                    sleep(1500);

                    /*
                     * Simulate service failure.
                     */
                    throw new RuntimeException(
                            "User service is unavailable."
                    );
                }
        );
    }

    /*
     * Helper method for simulated delay.
     */
    private static void sleep(
            long milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Task interrupted.",
                    e
            );
        }
    }

    public static void main(String[] args) {

        /*
         * Start asynchronous operation.
         */
        CompletableFuture<String> userFuture =
                fetchUser();

        /*
         * ----------------------------------------------------
         * whenComplete()
         * ----------------------------------------------------
         *
         * Used for observation/logging.
         *
         * It receives:
         *
         *     result
         *     error
         */
        CompletableFuture<String> loggedFuture =
                userFuture.whenComplete(
                        (result, error) -> {

                            if (error != null) {

                                System.out.println(
                                        "LOG: User fetch failed."
                                );

                                System.out.println(
                                        "LOG: "
                                                + error.getMessage()
                                );

                            } else {

                                System.out.println(
                                        "LOG: User fetched: "
                                                + result
                                );
                            }
                        }
                );

        /*
         * ----------------------------------------------------
         * exceptionally()
         * ----------------------------------------------------
         *
         * Recover from failure by providing a fallback.
         */
        CompletableFuture<String> recoveredFuture =
                loggedFuture.exceptionally(
                        error -> {

                            System.out.println(
                                    "RECOVERY: Using guest user."
                            );

                            return "Guest User";
                        }
                );

        /*
         * The pipeline can continue normally after recovery.
         */
        CompletableFuture<String> finalFuture =
                recoveredFuture.thenApply(
                        user ->
                                "Welcome, " + user
                );

        /*
         * Retrieve final result.
         */
        String result =
                finalFuture.join();

        System.out.println(
                "\nFinal result:"
        );

        System.out.println(
                result
        );
    }
}
