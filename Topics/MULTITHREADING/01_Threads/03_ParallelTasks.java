/*
 * ============================================================
 * 03 - PARALLEL TASKS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce application opening a user dashboard.
 *
 * The dashboard needs three independent pieces of information:
 *
 *     1. User Profile
 *     2. Notifications
 *     3. Product Recommendations
 *
 * Each operation takes some time.
 *
 * If we execute them one after another:
 *
 *     Profile
 *         ↓
 *     Notifications
 *         ↓
 *     Recommendations
 *
 * the total execution time becomes the sum of all operations.
 *
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * The three operations are independent.
 *
 * There is no reason for:
 *
 *     Notifications
 *
 * to wait for:
 *
 *     User Profile
 *
 * to finish.
 *
 * Similarly, recommendations don't need to wait for either
 * operation.
 *
 *
 * GOAL:
 * ------------------------------------------------------------
 * Execute independent tasks concurrently using multiple
 * threads.
 *
 *
 * CONCEPTS:
 * ------------------------------------------------------------
 * - Multiple threads
 * - Runnable
 * - Parallel execution
 * - Thread.sleep()
 * - Thread.join()
 * - Thread scheduling
 * - Comparing sequential vs concurrent execution
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 * "Concurrent" does not always mean "simultaneously".
 *
 * Multiple threads can make progress independently, but the
 * actual execution depends on CPU cores and OS/JVM scheduling.
 *
 *
 * SEQUENTIAL EXECUTION:
 * ------------------------------------------------------------
 *
 * Profile
 *     |
 *     | 2 seconds
 *     v
 * Notifications
 *     |
 *     | 2 seconds
 *     v
 * Recommendations
 *     |
 *     | 2 seconds
 *     v
 * Total ≈ 6 seconds
 *
 *
 * CONCURRENT EXECUTION:
 * ------------------------------------------------------------
 *
 * Profile          ──────────
 *
 * Notifications    ──────────
 *
 * Recommendations  ──────────
 *
 * Total ≈ 2 seconds
 *
 * The exact time will vary depending on the system.
 *
 *
 * WHY ARE WE USING join()?
 * ------------------------------------------------------------
 * The main thread needs to wait until all three dashboard
 * tasks are finished before printing:
 *
 *     "Dashboard ready"
 *
 * join() allows the main thread to wait for a particular
 * thread to finish.
 *
 *
 * EXPERIMENT 1:
 * ------------------------------------------------------------
 * Remove all join() calls.
 *
 * Run the program several times.
 *
 * Ask yourself:
 *
 *     Can "Dashboard ready" appear before all tasks finish?
 *
 *
 * EXPERIMENT 2:
 * ------------------------------------------------------------
 * Change the sleep times:
 *
 *     Profile          -> 3000 ms
 *     Notifications    -> 1000 ms
 *     Recommendations  -> 2000 ms
 *
 * Predict which task finishes first.
 *
 *
 * EXPERIMENT 3:
 * ------------------------------------------------------------
 * Start the threads in this order:
 *
 *     profile.start();
 *     notifications.start();
 *     recommendations.start();
 *
 * Then change the order.
 *
 * Ask yourself:
 *
 *     Does changing start() order guarantee completion order?
 *
 * Answer: No.
 *
 *
 * EXPERIMENT 4:
 * ------------------------------------------------------------
 * Comment out the join() for only one thread.
 *
 * Observe what happens.
 *
 *
 * INTERVIEW QUESTIONS:
 * ------------------------------------------------------------
 *
 * 1. What is the difference between concurrency and
 *    parallelism?
 *
 * 2. Why does starting multiple threads not guarantee execution
 *    order?
 *
 * 3. What does join() do?
 *
 * 4. Does join() create a new thread?
 *
 * 5. What happens if the main thread doesn't call join()?
 *
 * 6. Why are independent tasks good candidates for parallel
 *    execution?
 *
 *
 * KEY TAKEAWAY:
 * ------------------------------------------------------------
 * If multiple operations are independent, they can potentially
 * execute concurrently instead of waiting for one another.
 *
 * But concurrency introduces a new problem:
 *
 *     What happens when multiple threads access the SAME data?
 *
 * That problem leads us to RACE CONDITIONS.
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 * Program 04 -> Thread Lifecycle
 *
 * ============================================================
 */

public class ParallelTasks {

    static class DashboardTask implements Runnable {

        private final String taskName;
        private final int duration;

        DashboardTask(String taskName, int duration) {
            this.taskName = taskName;
            this.duration = duration;
        }

        @Override
        public void run() {

            System.out.println(
                    taskName
                            + " started | Thread: "
                            + Thread.currentThread().getName()
            );

            try {

                /*
                 * Simulate work such as:
                 *
                 * database query
                 * API call
                 * file operation
                 */
                Thread.sleep(duration);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        taskName + " was interrupted"
                );

                return;
            }

            System.out.println(
                    taskName
                            + " completed | Thread: "
                            + Thread.currentThread().getName()
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        long startTime =
                System.currentTimeMillis();

        System.out.println(
                "Loading user dashboard..."
        );

        /*
         * Create independent tasks.
         */
        Thread profileThread =
                new Thread(
                        new DashboardTask(
                                "User Profile",
                                2000
                        ),
                        "Profile-Thread"
                );

        Thread notificationThread =
                new Thread(
                        new DashboardTask(
                                "Notifications",
                                2000
                        ),
                        "Notification-Thread"
                );

        Thread recommendationThread =
                new Thread(
                        new DashboardTask(
                                "Recommendations",
                                2000
                        ),
                        "Recommendation-Thread"
                );

        /*
         * Start all three tasks.
         *
         * They can now execute concurrently.
         */
        profileThread.start();

        notificationThread.start();

        recommendationThread.start();

        /*
         * Wait until all three tasks finish.
         *
         * Without these join() calls, the main thread could
         * continue before the dashboard data is ready.
         */
        profileThread.join();

        notificationThread.join();

        recommendationThread.join();

        long endTime =
                System.currentTimeMillis();

        System.out.println(
                "Dashboard ready!"
        );

        System.out.println(
                "Total time: "
                        + (endTime - startTime)
                        + " ms"
        );
    }
}
