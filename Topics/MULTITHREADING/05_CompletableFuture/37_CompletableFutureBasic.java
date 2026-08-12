/*
 * ============================================================
 * 37 - BASIC ASYNCHRONOUS PROGRAMMING WITH CompletableFuture
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an application that needs to generate a user's
 * profile summary.
 *
 * The operation is expensive:
 *
 *     Fetch user data
 *     ↓
 *     Generate summary
 *
 *
 * We don't want the calling thread to perform the expensive
 * operation directly.
 *
 *
 * ------------------------------------------------------------
 * TRADITIONAL APPROACH
 * ------------------------------------------------------------
 *
 * Earlier we used:
 *
 *     ExecutorService
 *     Callable
 *     Future
 *
 *
 * Example:
 *
 *     Future<String> future =
 *             executor.submit(task);
 *
 *     String result = future.get();
 *
 *
 * This works, but asynchronous workflows can become difficult
 * to compose when there are many dependent operations.
 *
 *
 * ------------------------------------------------------------
 * CompletableFuture
 * ------------------------------------------------------------
 *
 * CompletableFuture represents a value that may be available
 * now, later, or may complete exceptionally.
 *
 *
 * It supports:
 *
 *     Asynchronous execution
 *     Callbacks
 *     Chaining
 *     Combining tasks
 *     Exception handling
 *     Timeouts
 *
 *
 * ------------------------------------------------------------
 * BASIC FLOW
 * ------------------------------------------------------------
 *
 *
 *     supplyAsync()
 *          |
 *          v
 *     Background task
 *          |
 *          v
 *       result
 *          |
 *          v
 *     thenApply()
 *          |
 *          v
 *     transformed result
 *
 *
 * ------------------------------------------------------------
 * supplyAsync()
 * ------------------------------------------------------------
 *
 * Use supplyAsync() when the asynchronous task RETURNS a value.
 *
 *
 *     CompletableFuture.supplyAsync(
 *         () -> calculate()
 *     );
 *
 *
 * ------------------------------------------------------------
 * runAsync()
 * ------------------------------------------------------------
 *
 * Use runAsync() when the asynchronous task does NOT return
 * a value.
 *
 *
 *     CompletableFuture.runAsync(
 *         () -> performTask()
 *     );
 *
 *
 * ------------------------------------------------------------
 * thenApply()
 * ------------------------------------------------------------
 *
 * thenApply() transforms the result.
 *
 *
 * Example:
 *
 *     "mahesh"
 *
 *       ↓ thenApply()
 *
 *     "MAHESH"
 *
 *
 * It is similar to a transformation pipeline.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * thenApply() is generally used when the next operation is
 * synchronous with respect to the completion stage.
 *
 *
 * Later we will learn:
 *
 *     thenApply()
 *     thenCompose()
 *     thenCombine()
 *     exceptionally()
 *     handle()
 *     whenComplete()
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     supplyAsync()
 *
 * with:
 *
 *     runAsync()
 *
 *
 * Observe that runAsync() doesn't produce a result value.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Add another thenApply():
 *
 *
 *     thenApply(...)
 *         .thenApply(...)
 *
 *
 * Build a multi-step transformation pipeline.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Print:
 *
 *     Thread.currentThread().getName()
 *
 * inside each stage.
 *
 *
 * Observe which threads execute the stages.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     thenApply()
 *
 * with:
 *
 *     thenApplyAsync()
 *
 *
 * Observe the difference in execution behavior.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is CompletableFuture?
 *
 * 2. Difference between runAsync() and supplyAsync()?
 *
 * 3. What does thenApply() do?
 *
 * 4. Is CompletableFuture automatically non-blocking?
 *
 * The asynchronous stages can execute without blocking the
 * calling thread, but methods such as join() and get() are
 * blocking when the result is not ready.
 *
 *
 * 5. Difference between Future and CompletableFuture?
 *
 * CompletableFuture provides a richer API for composing
 * asynchronous workflows and handling completion stages.
 *
 *
 * 6. What is the difference between get() and join()?
 *
 * Both can wait for completion.
 *
 * get() uses checked exceptions.
 *
 * join() throws unchecked CompletionException for exceptional
 * completion.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Think of CompletableFuture as:
 *
 *
 *     ASYNC VALUE
 *          |
 *          v
 *     transform
 *          |
 *          v
 *     transform
 *          |
 *          v
 *       final value
 *
 *
 * This is the beginning of asynchronous pipelines.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 38:
 *
 *     CompletableFuturePipeline.java
 *
 * We will build a realistic multi-step asynchronous workflow:
 *
 *     Fetch User
 *         ↓
 *     Fetch Account
 *         ↓
 *     Generate Profile
 *
 * and learn why thenCompose() is different from thenApply().
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFutureBasic {

    /*
     * Simulates fetching a user from a database/service.
     */
    private static String fetchUser() {

        System.out.println(
                Thread.currentThread().getName()
                        + " fetching user..."
        );

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "User fetch interrupted.",
                    e
            );
        }

        return "Mahesh";
    }

    public static void main(String[] args) {

        System.out.println(
                "Main thread: "
                        + Thread.currentThread().getName()
        );

        /*
         * Start an asynchronous computation.
         *
         * supplyAsync() returns a CompletableFuture<String>.
         */
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(
                        CompletableFutureBasic::fetchUser
                );

        System.out.println(
                "Main thread continues doing other work..."
        );

        /*
         * Transform the result when the asynchronous operation
         * completes.
         *
         * fetchUser()
         *
         * returns:
         *
         *     "Mahesh"
         *
         * thenApply() transforms it into:
         *
         *     "MAHESH"
         */
        CompletableFuture<String> upperCaseFuture =
                future.thenApply(
                        name -> {

                            System.out.println(
                                    "thenApply running on: "
                                            + Thread.currentThread()
                                            .getName()
                            );

                            return name.toUpperCase();
                        }
                );

        /*
         * join() waits for the final result.
         *
         * In a real asynchronous application, you would often
         * continue composing stages instead of immediately
         * blocking.
         */
        String result =
                upperCaseFuture.join();

        System.out.println(
                "Final result: "
                        + result
        );

        System.out.println(
                "Main thread completed."
        );
    }
}
