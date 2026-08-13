/*
 * ============================================================
 * 42 - thenApply() VS thenApplyAsync()
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an online shopping application.
 *
 * The backend performs:
 *
 *     1. Fetch product
 *     2. Format product data
 *     3. Perform expensive recommendation calculation
 *
 *
 * We already know:
 *
 *     thenApply()
 *
 * can transform the result of a CompletableFuture.
 *
 *
 * But Java also provides:
 *
 *     thenApplyAsync()
 *
 *
 * The question is:
 *
 *     What is the difference?
 *
 *
 * ============================================================
 * thenApply()
 * ============================================================
 *
 * thenApply() adds a synchronous continuation.
 *
 *
 * Depending on how the previous stage completes, the
 * continuation may execute on the thread that completes the
 * previous stage.
 *
 *
 * Conceptually:
 *
 *
 *     Async Task
 *          |
 *          v
 *     completes
 *          |
 *          v
 *     thenApply()
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * "Synchronous" here does NOT mean the entire program becomes
 * synchronous.
 *
 * It means the continuation is not necessarily scheduled onto
 * another asynchronous executor just because you used
 * thenApply().
 *
 *
 * ============================================================
 * thenApplyAsync()
 * ============================================================
 *
 * thenApplyAsync() schedules the continuation asynchronously.
 *
 *
 * Conceptually:
 *
 *
 *     Async Task
 *          |
 *          v
 *     completes
 *          |
 *          v
 *     Executor
 *          |
 *          v
 *     thenApplyAsync()
 *
 *
 * By default, the async form uses the
 * ForkJoinPool.commonPool().
 *
 *
 * You can also provide your own Executor.
 *
 *
 * ------------------------------------------------------------
 * CUSTOM EXECUTOR
 * ------------------------------------------------------------
 *
 * Production applications often benefit from controlling the
 * executor instead of blindly using the common pool.
 *
 *
 * Example:
 *
 *
 *     thenApplyAsync(
 *         task,
 *         myExecutor
 *     );
 *
 *
 * This gives you control over:
 *
 *     Thread count
 *     Thread naming
 *     Resource isolation
 *     Workload separation
 *
 *
 * ============================================================
 * WHEN TO USE WHICH?
 * ============================================================
 *
 *
 * thenApply()
 *
 * Good for:
 *
 *     Lightweight transformations
 *     Formatting
 *     Mapping
 *     Simple calculations
 *
 *
 * Example:
 *
 *     user -> user.getName()
 *
 *
 * ------------------------------------------------------------
 *
 *
 * thenApplyAsync()
 *
 * Useful when:
 *
 *     The continuation is expensive
 *     You want explicit asynchronous scheduling
 *     You need a dedicated executor
 *
 *
 * Example:
 *
 *     Generate large report
 *     CPU-heavy transformation
 *     Expensive processing
 *
 *
 * ============================================================
 * IMPORTANT CPU VS BLOCKING WORK
 * ============================================================
 *
 * Don't automatically assume:
 *
 *     "Async = better."
 *
 *
 * For blocking operations such as:
 *
 *     Database calls
 *     File I/O
 *     Network calls
 *
 *
 * you should generally think carefully about using an
 * appropriate executor rather than consuming the common pool
 * with many blocking operations.
 *
 *
 * ============================================================
 * EXPERIMENT 1
 * ============================================================
 *
 * Print:
 *
 *     Thread.currentThread().getName()
 *
 * in every stage.
 *
 *
 * Compare:
 *
 *     thenApply()
 *
 * and:
 *
 *     thenApplyAsync()
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     thenApplyAsync(...)
 *
 * with:
 *
 *     thenApplyAsync(..., customExecutor)
 *
 *
 * Observe the custom thread names.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add a long-running operation inside thenApply().
 *
 *
 * Ask:
 *
 *     Could this tie up the thread completing the previous
 *     stage?
 *
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Create a custom executor with only two threads.
 *
 * Submit many expensive continuation stages.
 *
 * Observe how the executor controls concurrency.
 *
 *
 * ============================================================
 * INTERVIEW QUESTIONS
 * ============================================================
 *
 * 1. Difference between thenApply() and thenApplyAsync()?
 *
 * thenApply() does not explicitly schedule the continuation
 * asynchronously.
 *
 * thenApplyAsync() schedules it asynchronously.
 *
 *
 * 2. Which executor does thenApplyAsync() use by default?
 *
 * ForkJoinPool.commonPool().
 *
 *
 * 3. Can thenApplyAsync() use a custom executor?
 *
 * Yes.
 *
 *
 * 4. Should every thenApply() be replaced with thenApplyAsync()?
 *
 * No.
 *
 *
 * 5. Why can long-running work inside thenApply() be problematic?
 *
 * It may occupy the thread completing the previous stage.
 *
 *
 * 6. Why might a custom executor be useful?
 *
 * To isolate workloads and control concurrency/resource usage.
 *
 *
 * ============================================================
 * KEY TAKEAWAY
 * ============================================================
 *
 *
 * thenApply()
 *
 *     Lightweight transformation
 *
 *
 * thenApplyAsync()
 *
 *     Asynchronously schedule the transformation
 *
 *
 * thenApplyAsync(..., executor)
 *
 *     Asynchronously execute using YOUR executor
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 43:
 *
 *     CompletableFutureAllOf.java
 *
 * We will run MANY independent asynchronous operations and wait
 * for all of them to complete using:
 *
 *     CompletableFuture.allOf()
 *
 * This is a very common backend pattern.
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureAsyncStages {

    /*
     * Custom executor used to demonstrate explicit control over
     * asynchronous continuation threads.
     */
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(
                    2,
                    runnable -> {

                        Thread thread =
                                new Thread(runnable);

                        thread.setName(
                                "Custom-Worker-"
                                        + thread.getId()
                        );

                        return thread;
                    }
            );

    /*
     * Simulate fetching a product.
     */
    private static CompletableFuture<String>
    fetchProduct() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "fetchProduct() running on: "
                                    + Thread.currentThread()
                                    .getName()
                    );

                    sleep(1000);

                    return "Laptop";
                }
        );
    }

    /*
     * Simulate expensive processing.
     */
    private static String
    generateRecommendation(
            String product) {

        System.out.println(
                "generateRecommendation() running on: "
                        + Thread.currentThread()
                        .getName()
        );

        sleep(1500);

        return "Recommended accessories for "
                + product;
    }

    /*
     * Simulate lightweight formatting.
     */
    private static String
    formatProduct(
            String product) {

        System.out.println(
                "formatProduct() running on: "
                        + Thread.currentThread()
                        .getName()
        );

        return product.toUpperCase();
    }

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
        CompletableFuture<String> productFuture =
                fetchProduct();

        /*
         * Lightweight transformation.
         *
         * thenApply() is appropriate here.
         */
        CompletableFuture<String> formattedFuture =
                productFuture.thenApply(
                        CompletableFutureAsyncStages::
                                formatProduct
                );

        /*
         * Expensive continuation.
         *
         * thenApplyAsync() explicitly schedules this stage
         * asynchronously.
         *
         * We provide our own executor so that this expensive
         * operation doesn't have to use the common pool.
         */
        CompletableFuture<String> recommendationFuture =
                formattedFuture.thenApplyAsync(
                        CompletableFutureAsyncStages::
                                generateRecommendation,
                        executor
                );

        /*
         * Wait for the final result.
         */
        String result =
                recommendationFuture.join();

        System.out.println(
                "\n========== RESULT =========="
        );

        System.out.println(
                result
        );

        System.out.println(
                "============================"
        );

        /*
         * Shutdown custom executor.
         */
        executor.shutdown();
    }
}
