/*
 * ============================================================
 * 49 - ConcurrentHashMap FOR THREAD-SAFE SHARED DATA
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a web application tracking page visits.
 *
 * Thousands of users are visiting pages concurrently.
 *
 * We want to maintain:
 *
 *     Home     → number of visits
 *     Products → number of visits
 *     Careers  → number of visits
 *
 *
 * Example:
 *
 *     /home       → 1500 visits
 *     /products   → 2300 visits
 *     /careers   → 700 visits
 *
 *
 * Multiple threads may update these values at the same time.
 *
 *
 * ------------------------------------------------------------
 * THE PROBLEM WITH HashMap
 * ------------------------------------------------------------
 *
 * A normal HashMap is NOT designed for concurrent modification
 * by multiple threads.
 *
 *
 * This is unsafe:
 *
 *
 *     Map<String, Integer> visits =
 *         new HashMap<>();
 *
 *
 * Multiple threads performing:
 *
 *     get()
 *     put()
 *
 * concurrently can cause race conditions.
 *
 *
 * ------------------------------------------------------------
 * WHY synchronizedMap IS NOT ALWAYS ENOUGH
 * ------------------------------------------------------------
 *
 * Java provides:
 *
 *     Collections.synchronizedMap(...)
 *
 *
 * which synchronizes individual map operations.
 *
 *
 * But compound operations such as:
 *
 *
 *     if (!map.containsKey(key)) {
 *         map.put(key, value);
 *     }
 *
 *
 * require additional synchronization.
 *
 *
 * ConcurrentHashMap provides more concurrency-friendly
 * operations such as:
 *
 *     putIfAbsent()
 *     compute()
 *     computeIfAbsent()
 *     merge()
 *
 *
 * ------------------------------------------------------------
 * CONCURRENT HASHMAP
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     ConcurrentHashMap
 *
 *
 * when multiple threads need to safely access and modify a map.
 *
 *
 * Example:
 *
 *
 *     ConcurrentHashMap<String, Integer> visits =
 *         new ConcurrentHashMap<>();
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHOD: merge()
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     home = 10
 *
 * A new visit arrives.
 *
 *
 * We want:
 *
 *     home = 11
 *
 *
 * Instead of manually doing:
 *
 *
 *     Integer count = map.get("home");
 *
 *     map.put("home", count + 1);
 *
 *
 * we can use:
 *
 *
 *     map.merge(
 *         "home",
 *         1,
 *         Integer::sum
 *     );
 *
 *
 * This safely combines the update operation.
 *
 *
 * ------------------------------------------------------------
 * WHY get() + put() CAN BE WRONG
 * ------------------------------------------------------------
 *
 * Suppose count = 10.
 *
 *
 * Thread A:
 *
 *     get() → 10
 *
 *
 * Thread B:
 *
 *     get() → 10
 *
 *
 * Thread A:
 *
 *     put(11)
 *
 *
 * Thread B:
 *
 *     put(11)
 *
 *
 * Final result:
 *
 *     11
 *
 *
 * But two visits happened.
 *
 *
 * Correct answer:
 *
 *     12
 *
 *
 * This is a LOST UPDATE.
 *
 *
 * ------------------------------------------------------------
 * merge()
 * ------------------------------------------------------------
 *
 * merge() allows us to express:
 *
 *
 *     "Add this value to the existing value atomically."
 *
 *
 * Example:
 *
 *
 *     visits.merge(
 *         page,
 *         1,
 *         Integer::sum
 *     );
 *
 *
 * ------------------------------------------------------------
 * OTHER USEFUL METHODS
 * ------------------------------------------------------------
 *
 *
 * putIfAbsent()
 *
 *     Add only if key doesn't exist.
 *
 *
 * computeIfAbsent()
 *
 *     Create value only when key is missing.
 *
 *
 * compute()
 *
 *     Recalculate a value.
 *
 *
 * merge()
 *
 *     Combine old and new values.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Cache
 *     Metrics
 *     Counters
 *     User sessions
 *     Request statistics
 *     In-memory indexes
 *     Concurrent configuration data
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace ConcurrentHashMap with HashMap.
 *
 *
 * Run with many threads.
 *
 *
 * Compare the results.
 *
 *
 * IMPORTANT:
 *
 * A race condition may not reproduce consistently.
 *
 *
 * That is one reason concurrency bugs are difficult to debug.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace merge() with:
 *
 *
 *     get()
 *     +
 *     put()
 *
 *
 * Observe why the final count can be incorrect.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     THREAD_COUNT
 *
 * and:
 *
 *     VISITS_PER_THREAD
 *
 *
 * Increase them significantly.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Is HashMap thread-safe?
 *
 * No.
 *
 *
 * 2. What is ConcurrentHashMap?
 *
 * A thread-safe map designed for concurrent access.
 *
 *
 * 3. Why is get() + put() unsafe for counters?
 *
 * Because the read-modify-write operation can suffer from
 * lost updates.
 *
 *
 * 4. What does merge() help with?
 *
 * It provides a convenient atomic-style update operation for
 * the map entry.
 *
 *
 * 5. What is putIfAbsent()?
 *
 * Inserts a value only if the key does not already exist.
 *
 *
 * 6. What is computeIfAbsent()?
 *
 * Computes and stores a value only when the key is absent.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * Normal collection:
 *
 *     HashMap
 *         ↓
 *     not thread-safe
 *
 *
 * Concurrent collection:
 *
 *     ConcurrentHashMap
 *         ↓
 *     designed for concurrent access
 *
 *
 * For counters, think:
 *
 *
 *     merge()
 *
 *
 * instead of:
 *
 *
 *     get() + put()
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 50:
 *
 *     AtomicCounterScenario.java
 *
 * We will solve the same shared-counter problem using
 * AtomicInteger and understand why:
 *
 *     count++
 *
 * is NOT atomic.
 *
 * ============================================================
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapScenario {

    /*
     * Number of threads simulating concurrent users.
     */
    private static final int THREAD_COUNT = 10;

    /*
     * Number of visits generated by each thread.
     */
    private static final int VISITS_PER_THREAD = 1000;

    /*
     * Thread-safe map.
     */
    private static final Map<String, Integer> pageVisits =
            new ConcurrentHashMap<>();

    /*
     * Simulate a user visiting a page.
     */
    private static void recordVisit(
            String page) {

        /*
         * Safely increment the page counter.
         *
         * merge():
         *
         *     If key doesn't exist:
         *         use 1
         *
         *     If key exists:
         *         oldValue + 1
         */
        pageVisits.merge(
                page,
                1,
                Integer::sum
        );
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Pages in our application.
         */
        String[] pages = {
                "home",
                "products",
                "careers"
        };

        /*
         * Create worker threads.
         */
        Thread[] workers =
                new Thread[THREAD_COUNT];

        for (int i = 0;
             i < THREAD_COUNT;
             i++) {

            final int workerId =
                    i;

            workers[i] =
                    new Thread(
                            () -> {

                                /*
                                 * Each worker simulates many
                                 * page visits.
                                 */
                                for (int j = 0;
                                     j < VISITS_PER_THREAD;
                                     j++) {

                                    /*
                                     * Distribute visits across
                                     * pages.
                                     */
                                    String page =
                                            pages[
                                                    (workerId + j)
                                                            % pages.length
                                            ];

                                    recordVisit(
                                            page
                                    );
                                }
                            },
                            "Visitor-"
                                    + (i + 1)
                    );

            workers[i].start();
        }

        /*
         * Wait for every simulated visitor thread.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        /*
         * Display final statistics.
         */
        System.out.println(
                "========== PAGE VISITS =========="
        );

        pageVisits.forEach(
                (page, count) ->
                        System.out.println(
                                page
                                        + " → "
                                        + count
                        )
        );

        System.out.println(
                "================================="
        );

        /*
         * Total expected visits:
         *
         *     THREAD_COUNT
         *         ×
         *     VISITS_PER_THREAD
         */
        int expected =
                THREAD_COUNT
                        * VISITS_PER_THREAD;

        int actual =
                pageVisits.values()
                        .stream()
                        .mapToInt(
                                Integer::intValue
                        )
                        .sum();

        System.out.println(
                "Expected visits: "
                        + expected
        );

        System.out.println(
                "Actual visits:   "
                        + actual
        );
    }
}
