/*
 * ============================================================
 * 35 - PROCESSING TASKS AS THEY COMPLETE
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend system processing 5 independent jobs:
 *
 *     Job 1 → 5 seconds
 *     Job 2 → 1 second
 *     Job 3 → 4 seconds
 *     Job 4 → 2 seconds
 *     Job 5 → 3 seconds
 *
 *
 * We want to:
 *
 *     1. Run all jobs concurrently.
 *     2. Process each result as soon as it finishes.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM WITH MULTIPLE FUTURES
 * ------------------------------------------------------------
 *
 * Suppose we do:
 *
 *     Future job1
 *     Future job2
 *     Future job3
 *
 * Then:
 *
 *     job1.get()
 *
 *
 * If Job 1 takes 5 seconds:
 *
 *     Main thread waits 5 seconds.
 *
 *
 * Even if:
 *
 *     Job 2 finished after 1 second
 *
 * we don't process its result yet.
 *
 *
 * This is called HEAD-OF-LINE WAITING in this context:
 *
 *
 *     Job 1 → slow
 *     Job 2 → already finished
 *
 *     But we are waiting for Job 1 first.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     ExecutorCompletionService
 *
 *
 * It allows us to submit tasks and retrieve completed results
 * in COMPLETION ORDER.
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * Submission order:
 *
 *     Job 1
 *     Job 2
 *     Job 3
 *     Job 4
 *     Job 5
 *
 *
 * Completion order:
 *
 *     Job 2
 *     Job 4
 *     Job 5
 *     Job 3
 *     Job 1
 *
 *
 * CompletionService lets us process:
 *
 *     Job 2
 *     Job 4
 *     Job 5
 *     Job 3
 *     Job 1
 *
 * as they become available.
 *
 *
 * ------------------------------------------------------------
 * HOW IT WORKS
 * ------------------------------------------------------------
 *
 *
 *                 Tasks
 *                   |
 *       +-----------+-----------+
 *       |           |           |
 *       v           v           v
 *     Worker      Worker      Worker
 *       |           |           |
 *       +-----------+-----------+
 *                   |
 *                   v
 *            Completion Queue
 *                   |
 *                   v
 *              take()
 *                   |
 *                   v
 *             Main Thread
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 * submit()
 *
 *     Submit a Callable task.
 *
 *
 * take()
 *
 *     Wait for the next completed task.
 *
 *
 * poll()
 *
 *     Check whether a completed task is immediately available.
 *
 *
 * ------------------------------------------------------------
 * TAKE VS GET
 * ------------------------------------------------------------
 *
 * completionService.take()
 *
 *     Gets the NEXT COMPLETED Future.
 *
 *
 * future.get()
 *
 *     Gets the result of THAT PARTICULAR task.
 *
 *
 * This distinction is extremely important.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Parallel API calls
 *     Batch processing
 *     Image processing
 *     Web scraping
 *     File processing
 *     Search across multiple sources
 *     Distributed task execution
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the job delays.
 *
 * Predict the completion order BEFORE running the program.
 *
 * Then compare your prediction with the actual output.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace CompletionService with a List<Future<?>>.
 *
 * Retrieve results in submission order.
 *
 * Compare the behavior.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     take()
 *
 * with:
 *
 *     poll()
 *
 * Think about the difference.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What problem does CompletionService solve?
 *
 * It allows completed task results to be consumed in completion
 * order.
 *
 *
 * 2. Difference between ExecutorService and
 *    ExecutorCompletionService?
 *
 * ExecutorService manages task execution.
 *
 * CompletionService combines an Executor with a completion queue
 * so completed tasks can be consumed as they finish.
 *
 *
 * 3. What does take() do?
 *
 * Waits until a completed task is available and returns its
 * Future.
 *
 *
 * 4. What does poll() do?
 *
 * Returns a completed Future if one is immediately available;
 * otherwise returns null.
 *
 *
 * 5. Why can CompletionService improve responsiveness?
 *
 * Because the application can process fast results without
 * waiting for slower tasks submitted earlier.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Future list:
 *
 *     "Give me results in the order I submitted them."
 *
 *
 * CompletionService:
 *
 *     "Give me results in the order they finish."
 *
 *
 * This is especially useful when:
 *
 *     Fast results are valuable immediately.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 36:
 *
 *     ScheduledExecutorScenario.java
 *
 * We will move from executing tasks immediately to scheduling
 * work for later and periodically.
 *
 * Real-world scenario:
 *
 *     Every 2 seconds, monitor server health.
 *
 * ============================================================
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceScenario {

    static class ProcessingTask
            implements Callable<String> {

        private final int jobId;
        private final int processingTime;

        ProcessingTask(
                int jobId,
                int processingTime) {

            this.jobId =
                    jobId;

            this.processingTime =
                    processingTime;
        }

        @Override
        public String call()
                throws Exception {

            System.out.println(
                    Thread.currentThread().getName()
                            + " started Job-"
                            + jobId
                            + " ("
                            + processingTime
                            + " ms)"
            );

            /*
             * Simulate different processing times.
             */
            Thread.sleep(processingTime);

            return "Job-"
                    + jobId
                    + " completed.";
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create worker pool.
         */
        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        /*
         * Wrap the executor with CompletionService.
         *
         * Completed Futures are placed into an internal
         * completion queue.
         */
        ExecutorCompletionService<String>
                completionService =
                new ExecutorCompletionService<>(
                        executor
                );

        /*
         * Submit jobs.
         *
         * Notice that submission order is:
         *
         *     1, 2, 3, 4, 5
         *
         */
        completionService.submit(
                new ProcessingTask(
                        1,
                        5000
                )
        );

        completionService.submit(
                new ProcessingTask(
                        2,
                        1000
                )
        );

        completionService.submit(
                new ProcessingTask(
                        3,
                        4000
                )
        );

        completionService.submit(
                new ProcessingTask(
                        4,
                        2000
                )
        );

        completionService.submit(
                new ProcessingTask(
                        5,
                        3000
                )
        );

        System.out.println(
                "\nAll jobs submitted."
        );

        /*
         * We submitted 5 tasks.
         *
         * Therefore we need to consume 5 completed results.
         */
        for (int i = 0;
             i < 5;
             i++) {

            /*
             * take() waits for the NEXT completed task.
             */
            Future<String> completedFuture =
                    completionService.take();

            try {

                /*
                 * The Future returned by take() is already
                 * completed, so get() should return immediately
                 * except for exceptional/cancellation cases.
                 */
                String result =
                        completedFuture.get();

                System.out.println(
                        "RESULT RECEIVED: "
                                + result
                );

            } catch (ExecutionException e) {

                System.out.println(
                        "Job failed: "
                                + e.getCause()
                );
            }
        }

        /*
         * Stop executor.
         */
        executor.shutdown();

        System.out.println(
                "\nAll completed results processed."
        );
    }
}
