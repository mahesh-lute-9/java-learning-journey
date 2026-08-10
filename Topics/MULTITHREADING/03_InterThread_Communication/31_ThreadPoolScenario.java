/*
 * ============================================================
 * 31 - THREAD POOL USING ExecutorService
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce backend receiving thousands of requests.
 *
 * For every request, we need to perform some work:
 *
 *     Process Order
 *     Send Email
 *     Generate Invoice
 *     Update Database
 *
 *
 * A beginner might create a NEW THREAD for every task.
 *
 *
 *     Task 1 → new Thread()
 *     Task 2 → new Thread()
 *     Task 3 → new Thread()
 *     ...
 *
 *
 * This becomes expensive when thousands of tasks arrive.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM WITH CREATING THREADS MANUALLY
 * ------------------------------------------------------------
 *
 * Creating too many threads can cause:
 *
 *     - Memory overhead
 *     - Thread creation overhead
 *     - Excessive context switching
 *     - Poor resource control
 *     - Difficult lifecycle management
 *
 *
 * We need a better approach.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     ExecutorService
 *
 *
 * Instead of creating a thread for every task:
 *
 *
 *             TASKS
 *          /    |    \
 *         /     |     \
 *        v      v      v
 *     Task1  Task2  Task3 ...
 *          \    |    /
 *           \   |   /
 *            v  v  v
 *        THREAD POOL
 *        +---------+
 *        | Thread 1|
 *        | Thread 2|
 *        | Thread 3|
 *        | Thread 4|
 *        +---------+
 *
 *
 * A fixed-size pool reuses a limited number of worker threads.
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 *     Thread pool size = 3
 *
 *     10 tasks arrive.
 *
 *
 *     Task 1 → Worker-1
 *     Task 2 → Worker-2
 *     Task 3 → Worker-3
 *
 *     Task 4 → waits in queue
 *     Task 5 → waits in queue
 *     Task 6 → waits in queue
 *     ...
 *
 *
 * When Worker-1 finishes Task 1:
 *
 *     Worker-1 → Task 4
 *
 *
 * The thread is REUSED.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT CONCEPT
 * ------------------------------------------------------------
 *
 * ExecutorService separates:
 *
 *     TASK SUBMISSION
 *
 * from:
 *
 *     THREAD MANAGEMENT
 *
 *
 * You say:
 *
 *     executor.submit(task)
 *
 *
 * You don't manually decide:
 *
 *     Which thread should execute this task?
 *
 *
 * The executor manages that.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 * execute()
 *
 *     Submit a Runnable.
 *
 *
 * submit()
 *
 *     Submit a task and receive a Future.
 *
 *
 * shutdown()
 *
 *     Stop accepting new tasks.
 *
 *
 * shutdownNow()
 *
 *     Attempts to interrupt running tasks and returns tasks
 *     that never started.
 *
 *
 * awaitTermination()
 *
 *     Wait for the executor to terminate.
 *
 *
 * ------------------------------------------------------------
 * shutdown() VS shutdownNow()
 * ------------------------------------------------------------
 *
 * shutdown():
 *
 *     Finish submitted tasks.
 *
 *
 * shutdownNow():
 *
 *     Attempts to stop running tasks through interruption.
 *
 *
 * In general, graceful application shutdown should prefer
 * shutdown() first.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     POOL_SIZE = 3
 *
 * to:
 *
 *     POOL_SIZE = 2
 *
 * Observe that only two tasks execute concurrently.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     NUMBER_OF_TASKS = 20
 *
 * Keep pool size at 3.
 *
 * Observe task queuing and thread reuse.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     shutdown()
 *
 * with:
 *
 *     shutdownNow()
 *
 * Observe how interruption affects running tasks.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Submit tasks AFTER:
 *
 *     executor.shutdown()
 *
 * Observe the rejection behavior.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why use a thread pool?
 *
 * 2. What is ExecutorService?
 *
 * 3. Difference between execute() and submit()?
 *
 * 4. What happens to extra tasks when all worker threads are
 *    busy?
 *
 * They are handled according to the executor's work queue and
 * rejection policy.
 *
 * 5. Difference between shutdown() and shutdownNow()?
 *
 * 6. Why should an ExecutorService be shut down?
 *
 * To release its resources and allow the application to
 * terminate cleanly.
 *
 * 7. Can a thread pool reuse threads?
 *
 * Yes. That's one of its main benefits.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Don't think:
 *
 *     "One task = one thread."
 *
 *
 * Think:
 *
 *     "Tasks are submitted to a controlled pool of workers."
 *
 *
 * This is a major shift from low-level thread programming to
 * practical Java concurrency.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 32:
 *
 *     CallableFutureScenario.java
 *
 * We will solve a scenario where a background task must RETURN
 * a result to the calling thread.
 *
 * ============================================================
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolScenario {

    /*
     * Maximum number of concurrent worker threads.
     */
    private static final int POOL_SIZE = 3;

    /*
     * Number of tasks submitted to the pool.
     */
    private static final int NUMBER_OF_TASKS = 10;

    static class OrderTask implements Runnable {

        private final int orderId;

        OrderTask(int orderId) {

            this.orderId =
                    orderId;
        }

        @Override
        public void run() {

            /*
             * Print the worker thread executing the task.
             */
            System.out.println(
                    Thread.currentThread().getName()
                            + " processing Order-"
                            + orderId
            );

            try {

                /*
                 * Simulate order processing.
                 */
                Thread.sleep(1500);

            } catch (InterruptedException e) {

                /*
                 * Restore interrupt status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        Thread.currentThread().getName()
                                + " interrupted while processing Order-"
                                + orderId
                );

                return;
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " completed Order-"
                            + orderId
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create a fixed-size thread pool.
         *
         * Only POOL_SIZE worker threads are created for this
         * executor.
         */
        ExecutorService executor =
                Executors.newFixedThreadPool(
                        POOL_SIZE
                );

        /*
         * Submit multiple tasks.
         *
         * We do NOT create Thread objects manually.
         */
        for (int i = 1;
             i <= NUMBER_OF_TASKS;
             i++) {

            executor.execute(
                    new OrderTask(i)
            );
        }

        System.out.println(
                "All tasks submitted."
        );

        /*
         * Stop accepting new tasks.
         *
         * Already submitted tasks continue executing.
         */
        executor.shutdown();

        /*
         * Wait for the executor to finish.
         */
        boolean finished =
                executor.awaitTermination(
                        30,
                        TimeUnit.SECONDS
                );

        if (finished) {

            System.out.println(
                    "All tasks completed."
            );

        } else {

            System.out.println(
                    "Tasks did not finish within timeout."
            );

            /*
             * Attempt to interrupt running tasks.
             */
            executor.shutdownNow();
        }

        System.out.println(
                "Executor shutdown completed."
        );
    }
}
