/*
 * ============================================================
 * 44 - FIRST COMPLETED RESULT USING CompletableFuture.anyOf()
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine your application has three mirror servers:
 *
 *     Server A
 *     Server B
 *     Server C
 *
 *
 * All three can provide the same data.
 *
 * Instead of waiting for every server, we want:
 *
 *     "Give me the first response."
 *
 *
 * Example:
 *
 *     Server A → 5 seconds
 *     Server B → 2 seconds
 *     Server C → 4 seconds
 *
 *
 * We only need:
 *
 *     Server B
 *
 *
 * ------------------------------------------------------------
 * anyOf()
 * ------------------------------------------------------------
 *
 * CompletableFuture.anyOf() creates a CompletableFuture that
 * completes when ANY of the supplied futures completes.
 *
 *
 * Conceptually:
 *
 *
 *     Future A ──────── 5s ──────────┐
 *                                    |
 *     Future B ─── 2s ──→ RESULT ───┤
 *                                    |
 *     Future C ─────── 4s ──────────┘
 *                                    |
 *                                    v
 *                                  anyOf()
 *
 *
 * ------------------------------------------------------------
 * allOf() VS anyOf()
 * ------------------------------------------------------------
 *
 *
 * allOf():
 *
 *     "Wait for EVERY task."
 *
 *
 * anyOf():
 *
 *     "Continue when ANY task completes."
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT SUBTLETY
 * ------------------------------------------------------------
 *
 * anyOf() completes when the FIRST future completes.
 *
 * That does NOT necessarily mean:
 *
 *     "first successful result."
 *
 *
 * If the first future completes exceptionally, anyOf() can
 * complete exceptionally even if another future succeeds later.
 *
 *
 * Therefore:
 *
 *     anyOf()
 *
 * is not automatically:
 *
 *     "first successful result."
 *
 *
 * If your requirement is specifically:
 *
 *     "Return the first SUCCESSFUL response."
 *
 *
 * you need additional failure handling/design.
 *
 *
 * ------------------------------------------------------------
 * RETURN TYPE
 * ------------------------------------------------------------
 *
 * anyOf() returns:
 *
 *     CompletableFuture<Object>
 *
 *
 * because the supplied futures may contain different result
 * types.
 *
 *
 * If all futures have the same type, you may cast the result,
 * but you should do so carefully.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Multiple mirror servers
 *     Redundant APIs
 *     Multiple cache sources
 *     Racing data providers
 *     Backup services
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the server delays.
 *
 * Predict which server wins.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make Server B fail quickly.
 *
 * Observe that anyOf() may complete exceptionally because B
 * completed first.
 *
 *
 * This demonstrates:
 *
 *     first completed
 *
 * is NOT always:
 *
 *     first successful.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make all servers have the same result type.
 *
 * Then safely convert the result to String.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Think about whether the slower servers should continue
 * running after one server has already responded.
 *
 *
 * In a production system, you may want to cancel unnecessary
 * work if appropriate.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What does anyOf() do?
 *
 * Completes when any supplied CompletableFuture completes.
 *
 *
 * 2. Difference between allOf() and anyOf()?
 *
 * allOf() waits for all.
 *
 * anyOf() waits for the first completion.
 *
 *
 * 3. What does anyOf() return?
 *
 * CompletableFuture<Object>
 *
 *
 * 4. Does anyOf() guarantee the first SUCCESSFUL result?
 *
 * No.
 *
 * It reacts to the first completion, including exceptional
 * completion.
 *
 *
 * 5. When is anyOf() useful?
 *
 * When multiple independent sources can provide an acceptable
 * answer and latency is important.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * allOf()
 *
 *     "Everyone must finish."
 *
 *
 * anyOf()
 *
 *     "The first one to finish wins."
 *
 *
 * But remember:
 *
 *
 *     FIRST COMPLETED
 *          ≠
 *     FIRST SUCCESSFUL
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 45:
 *
 *     CompletableFutureRetry.java
 *
 * We will solve a realistic scenario:
 *
 *     "An external API fails temporarily. Try again a few
 *      times before giving up."
 *
 * This introduces retry logic and backoff.
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAnyOf {

    /*
     * Simulate Server A.
     */
    private static CompletableFuture<String>
    serverA() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Server A started..."
                    );

                    sleep(5000);

                    return "Response from Server A";
                }
        );
    }

    /*
     * Simulate Server B.
     */
    private static CompletableFuture<String>
    serverB() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Server B started..."
                    );

                    sleep(2000);

                    return "Response from Server B";
                }
        );
    }

    /*
     * Simulate Server C.
     */
    private static CompletableFuture<String>
    serverC() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Server C started..."
                    );

                    sleep(4000);

                    return "Response from Server C";
                }
        );
    }

    private static void sleep(
            long milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Server request interrupted.",
                    e
            );
        }
    }

    public static void main(String[] args) {

        /*
         * Start all three servers concurrently.
         */
        CompletableFuture<String> serverA =
                serverA();

        CompletableFuture<String> serverB =
                serverB();

        CompletableFuture<String> serverC =
                serverC();

        /*
         * anyOf() completes when the FIRST supplied future
         * completes.
         */
        CompletableFuture<Object> fastestResponse =
                CompletableFuture.anyOf(
                        serverA,
                        serverB,
                        serverC
                );

        /*
         * Retrieve the first completed result.
         */
        Object result =
                fastestResponse.join();

        System.out.println(
                "\n========== FASTEST RESPONSE =========="
        );

        System.out.println(
                result
        );

        System.out.println(
                "======================================="
        );
    }
}
