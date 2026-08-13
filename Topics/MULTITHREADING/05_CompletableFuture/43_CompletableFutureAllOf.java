/*
 * ============================================================
 * 43 - WAITING FOR MULTIPLE CompletableFuture TASKS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a developer dashboard.
 *
 * To build the dashboard, the backend needs:
 *
 *     GitHub statistics
 *     LeetCode statistics
 *     LinkedIn statistics
 *     Project statistics
 *
 *
 * These operations are independent.
 *
 *
 * We want:
 *
 *     1. Start all operations concurrently.
 *     2. Wait until ALL operations finish.
 *     3. Build the final dashboard.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * We could manually write:
 *
 *
 *     future1.join();
 *     future2.join();
 *     future3.join();
 *     future4.join();
 *
 *
 * This works for a small number of tasks, but becomes awkward
 * as the number of asynchronous operations increases.
 *
 *
 * CompletableFuture provides:
 *
 *     allOf()
 *
 *
 * ------------------------------------------------------------
 * allOf()
 * ------------------------------------------------------------
 *
 * Example:
 *
 *
 *     CompletableFuture.allOf(
 *         future1,
 *         future2,
 *         future3
 *     );
 *
 *
 * returns:
 *
 *
 *     CompletableFuture<Void>
 *
 *
 * IMPORTANT:
 *
 * allOf() tells us:
 *
 *     "All supplied futures have completed."
 *
 *
 * It does NOT automatically return a List of their results.
 *
 *
 * This is a very important interview point.
 *
 *
 * ------------------------------------------------------------
 * FLOW
 * ------------------------------------------------------------
 *
 *
 *       Future A ──────┐
 *                      |
 *       Future B ──────┤
 *                      |
 *       Future C ──────┤
 *                      v
 *                 CompletableFuture
 *                    .allOf()
 *                      |
 *                      v
 *                  ALL DONE
 *
 *
 * ------------------------------------------------------------
 * WHY USE allOf()?
 * ------------------------------------------------------------
 *
 * It is useful when:
 *
 *     Several independent operations need to complete before
 *     the next stage can proceed.
 *
 *
 * Real-world examples:
 *
 *     Dashboard aggregation
 *     Batch API calls
 *     Parallel database queries
 *     Loading independent resources
 *     Startup tasks
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * These tasks should be started independently:
 *
 *
 *     futureA = taskA();
 *     futureB = taskB();
 *     futureC = taskC();
 *
 *
 * BEFORE waiting.
 *
 *
 * Don't accidentally write:
 *
 *
 *     taskA().join();
 *     taskB().join();
 *     taskC().join();
 *
 *
 * because that can serialize the waiting pattern.
 *
 *
 * ------------------------------------------------------------
 * allOf() RESULT
 * ------------------------------------------------------------
 *
 * allOf() returns:
 *
 *     CompletableFuture<Void>
 *
 *
 * Therefore:
 *
 *
 *     CompletableFuture<Void> all =
 *         CompletableFuture.allOf(
 *             futureA,
 *             futureB,
 *             futureC
 *         );
 *
 *
 * After:
 *
 *     all.join();
 *
 *
 * all tasks have completed.
 *
 *
 * Then we can retrieve individual results:
 *
 *
 *     futureA.join()
 *     futureB.join()
 *     futureC.join()
 *
 *
 * ------------------------------------------------------------
 * FAILURE BEHAVIOR
 * ------------------------------------------------------------
 *
 * If one of the futures completes exceptionally:
 *
 *     allOf()
 *
 * also completes exceptionally.
 *
 *
 * Therefore production code should decide:
 *
 *     Should one failure fail the entire operation?
 *
 * OR:
 *
 *     Should partial results still be accepted?
 *
 *
 * That depends on the business requirement.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Add a fifth future.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make one future fail.
 *
 * Observe the behavior of allOf().
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Compare:
 *
 *     allOf()
 *
 * with:
 *
 *     thenCombine()
 *
 *
 * Think about:
 *
 *     2 results
 *
 * versus:
 *
 *     many independent tasks.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Make one task take 5 seconds and the others take 1 second.
 *
 * Observe that allOf() completes only after the slowest task
 * finishes.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What does CompletableFuture.allOf() do?
 *
 * Creates a CompletableFuture that completes when all supplied
 * futures complete.
 *
 *
 * 2. What does allOf() return?
 *
 * CompletableFuture<Void>
 *
 *
 * 3. Does allOf() return the task results?
 *
 * No.
 *
 *
 * 4. How can you retrieve individual results?
 *
 * Use the original futures after allOf() completes.
 *
 *
 * 5. What happens if one future fails?
 *
 * The combined future can complete exceptionally.
 *
 *
 * 6. When is allOf() useful?
 *
 * When many independent asynchronous tasks must all complete
 * before continuing.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * thenCombine()
 *
 *     Combine TWO independent results.
 *
 *
 * allOf()
 *
 *     Wait for MANY independent tasks.
 *
 *
 * Think:
 *
 *
 *     A + B → thenCombine()
 *
 *
 *     A + B + C + D + ... → allOf()
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 44:
 *
 *     CompletableFutureAnyOf.java
 *
 * Opposite scenario:
 *
 *     We don't need ALL results.
 *
 *     We only need the FIRST successful/available result.
 *
 * Example:
 *
 *     Query multiple servers and use whichever responds first.
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAllOf {

    /*
     * Simulate GitHub statistics.
     */
    private static CompletableFuture<String>
    fetchGitHubStats() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching GitHub statistics..."
                    );

                    sleep(2000);

                    return "GitHub: 120 contributions";
                }
        );
    }

    /*
     * Simulate LeetCode statistics.
     */
    private static CompletableFuture<String>
    fetchLeetCodeStats() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching LeetCode statistics..."
                    );

                    sleep(3000);

                    return "LeetCode: 50 problems solved";
                }
        );
    }

    /*
     * Simulate LinkedIn statistics.
     */
    private static CompletableFuture<String>
    fetchLinkedInStats() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching LinkedIn statistics..."
                    );

                    sleep(1500);

                    return "LinkedIn: 800 connections";
                }
        );
    }

    /*
     * Simulate project statistics.
     */
    private static CompletableFuture<String>
    fetchProjectStats() {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching project statistics..."
                    );

                    sleep(2500);

                    return "Projects: 8 completed";
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
                    "Task interrupted.",
                    e
            );
        }
    }

    public static void main(String[] args) {

        /*
         * ----------------------------------------------------
         * START ALL TASKS
         * ----------------------------------------------------
         *
         * Each task starts independently.
         */
        CompletableFuture<String> github =
                fetchGitHubStats();

        CompletableFuture<String> leetcode =
                fetchLeetCodeStats();

        CompletableFuture<String> linkedin =
                fetchLinkedInStats();

        CompletableFuture<String> projects =
                fetchProjectStats();

        /*
         * ----------------------------------------------------
         * WAIT FOR ALL
         * ----------------------------------------------------
         *
         * allOf() completes only when all four futures finish.
         */
        CompletableFuture<Void> allTasks =
                CompletableFuture.allOf(
                        github,
                        leetcode,
                        linkedin,
                        projects
                );

        /*
         * ----------------------------------------------------
         * CONTINUE AFTER ALL TASKS COMPLETE
         * ----------------------------------------------------
         */
        CompletableFuture<String> dashboard =
                allTasks.thenApply(
                        ignored -> {

                            /*
                             * At this point all futures have
                             * completed.
                             *
                             * Therefore join() should not need
                             * to wait for them.
                             */
                            String githubResult =
                                    github.join();

                            String leetcodeResult =
                                    leetcode.join();

                            String linkedinResult =
                                    linkedin.join();

                            String projectResult =
                                    projects.join();

                            return
                                    "\n========== DEVELOPER DASHBOARD ==========\n"
                                            + githubResult
                                            + "\n"
                                            + leetcodeResult
                                            + "\n"
                                            + linkedinResult
                                            + "\n"
                                            + projectResult
                                            + "\n"
                                            + "==========================================";
                        }
                );

        /*
         * Get the final dashboard.
         */
        System.out.println(
                dashboard.join()
        );
    }
}
