/*
 * ============================================================
 * 39 - COMBINING INDEPENDENT CompletableFuture TASKS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce product page.
 *
 * To display the final product information, the backend needs:
 *
 *     Product Details
 *     Inventory Status
 *
 *
 * These operations are INDEPENDENT.
 *
 * Therefore, they should run concurrently.
 *
 *
 * ------------------------------------------------------------
 * WORKFLOW
 * ------------------------------------------------------------
 *
 *
 *              Product Page
 *                    |
 *              +-----+-----+
 *              |           |
 *              v           v
 *        Product API   Inventory API
 *              |           |
 *              v           v
 *           Product     Stock
 *              |           |
 *              +-----+-----+
 *                    |
 *                    v
 *              Combined Result
 *
 *
 * ------------------------------------------------------------
 * WHY NOT thenCompose()?
 * ------------------------------------------------------------
 *
 * thenCompose() is useful when:
 *
 *     Task B depends on Task A.
 *
 *
 * Example:
 *
 *     fetchUser()
 *          ↓
 *     fetchAccount(user.id)
 *
 *
 * Here:
 *
 *     Inventory does NOT depend on Product.
 *
 *
 * Both can start immediately.
 *
 *
 * Therefore:
 *
 *     thenCombine()
 *
 * is a better fit.
 *
 *
 * ------------------------------------------------------------
 * thenCombine()
 * ------------------------------------------------------------
 *
 * Conceptually:
 *
 *
 *     Future<A>
 *          \
 *           \
 *            thenCombine()
 *           /
 *          /
 *     Future<B>
 *
 *             ↓
 *
 *          Future<C>
 *
 *
 * Example:
 *
 *
 *     CompletableFuture<Product> product =
 *         fetchProduct();
 *
 *
 *     CompletableFuture<Integer> stock =
 *         fetchStock();
 *
 *
 *     CompletableFuture<String> result =
 *         product.thenCombine(
 *             stock,
 *             (p, s) -> createResponse(p, s)
 *         );
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DIFFERENCE
 * ------------------------------------------------------------
 *
 *
 * thenCompose():
 *
 *     A → async B
 *
 *     Sequential dependency.
 *
 *
 * thenCombine():
 *
 *     A + B → C
 *
 *     Independent tasks.
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * Product API:
 *
 *     2 seconds
 *
 * Inventory API:
 *
 *     3 seconds
 *
 *
 * Sequential:
 *
 *     2 + 3 = 5 seconds
 *
 *
 * Concurrent:
 *
 *     approximately 3 seconds
 *
 * ignoring scheduling and other overhead.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the delays.
 *
 * Predict the approximate total execution time.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make Product API throw an exception.
 *
 * Observe the final CompletableFuture.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add a third independent operation:
 *
 *     Reviews API
 *
 *
 * Think about how you would combine three asynchronous
 * results.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Compare:
 *
 *     thenCompose()
 *
 * with:
 *
 *     thenCombine()
 *
 *
 * Ask:
 *
 *     "Does the second task need the first task's result?"
 *
 *
 * If YES:
 *
 *     thenCompose()
 *
 *
 * If NO:
 *
 *     thenCombine()
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What does thenCombine() do?
 *
 * Combines the results of two independent CompletionStages.
 *
 *
 * 2. Difference between thenCombine() and thenCompose()?
 *
 * thenCompose():
 *     sequential dependency
 *
 * thenCombine():
 *     independent asynchronous operations
 *
 *
 * 3. Can both tasks execute concurrently?
 *
 * Yes, if they are started independently.
 *
 *
 * 4. What happens if one future completes exceptionally?
 *
 * The combined stage can complete exceptionally.
 *
 *
 * 5. Why is parallel execution useful for independent
 *    operations?
 *
 * It can reduce overall latency.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * DEPENDENT:
 *
 *     A
 *     ↓
 *     B
 *
 *     Use thenCompose()
 *
 *
 * INDEPENDENT:
 *
 *     A ──┐
 *         ├──> C
 *     B ──┘
 *
 *     Use thenCombine()
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 40:
 *
 *     CompletableFutureExceptionHandling.java
 *
 * We will handle failures in asynchronous pipelines using:
 *
 *     exceptionally()
 *     handle()
 *     whenComplete()
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCombine {

    /*
     * Simple product model.
     */
    static class Product {

        private final String name;
        private final double price;

        Product(
                String name,
                double price) {

            this.name = name;
            this.price = price;
        }
    }

    /*
     * Simulate Product API.
     *
     * This operation is independent of Inventory API.
     */
    private static CompletableFuture<Product>
    fetchProduct() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching product..."
                    );

                    sleep(2000);

                    return new Product(
                            "Laptop",
                            75000
                    );
                }
        );
    }

    /*
     * Simulate Inventory API.
     *
     * This operation is independent of Product API.
     */
    private static CompletableFuture<Integer>
    fetchInventory() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching inventory..."
                    );

                    sleep(3000);

                    return 12;
                }
        );
    }

    /*
     * Simulate network delay.
     */
    private static void sleep(
            long milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Operation interrupted.",
                    e
            );
        }
    }

    public static void main(String[] args) {

        long start =
                System.currentTimeMillis();

        /*
         * Start BOTH operations immediately.
         *
         * Neither operation depends on the other.
         */
        CompletableFuture<Product> productFuture =
                fetchProduct();

        CompletableFuture<Integer> inventoryFuture =
                fetchInventory();

        /*
         * Combine the two independent results.
         *
         * This stage waits until BOTH futures complete.
         */
        CompletableFuture<String> productPage =
                productFuture.thenCombine(
                        inventoryFuture,
                        (product, stock) -> {

                            if (stock > 0) {

                                return product.name
                                        + " | ₹"
                                        + product.price
                                        + " | In Stock: "
                                        + stock;

                            }

                            return product.name
                                    + " | Out of Stock";
                        }
                );

        /*
         * Retrieve the final result.
         */
        String result =
                productPage.join();

        long end =
                System.currentTimeMillis();

        System.out.println(
                "\n========== PRODUCT PAGE =========="
        );

        System.out.println(
                result
        );

        System.out.println(
                "=================================="
        );

        System.out.println(
                "Total time: "
                        + (end - start)
                        + " ms"
        );
    }
}
