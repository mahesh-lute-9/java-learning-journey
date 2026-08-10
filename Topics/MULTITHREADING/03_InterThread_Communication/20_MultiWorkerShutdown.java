/*
 * ============================================================
 * 20 - MULTIPLE WORKERS WITH GRACEFUL SHUTDOWN
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend server with multiple worker threads.
 *
 * Example:
 *
 *     Worker-1 → Process orders
 *     Worker-2 → Process payments
 *     Worker-3 → Process notifications
 *
 * The application receives a shutdown request.
 *
 * We need to stop ALL workers safely.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * With one worker, shutdown is relatively simple:
 *
 *     worker.interrupt();
 *     worker.join();
 *
 *
 * But with multiple workers:
 *
 *     Worker-1
 *     Worker-2
 *     Worker-3
 *     Worker-4
 *
 * we need to:
 *
 *     1. Request shutdown from every worker.
 *     2. Allow every worker to clean up.
 *     3. Wait until every worker has actually stopped.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DESIGN
 * ------------------------------------------------------------
 *
 * The MAIN THREAD owns the worker lifecycle.
 *
 *
 * START:
 *
 *     main
 *       |
 *       +--> worker-1.start()
 *       +--> worker-2.start()
 *       +--> worker-3.start()
 *
 *
 * SHUTDOWN:
 *
 *     main
 *       |
 *       +--> worker-1.interrupt()
 *       +--> worker-2.interrupt()
 *       +--> worker-3.interrupt()
 *
 *
 * WAIT:
 *
 *     main
 *       |
 *       +--> worker-1.join()
 *       +--> worker-2.join()
 *       +--> worker-3.join()
 *
 *
 * ------------------------------------------------------------
 * WHY BOTH interrupt() AND join()?
 * ------------------------------------------------------------
 *
 * interrupt():
 *
 *     Requests the worker to stop.
 *
 *
 * join():
 *
 *     Makes the main thread wait until the worker has actually
 *     finished.
 *
 *
 * They solve DIFFERENT problems.
 *
 *
 *     interrupt()
 *         =
 *     REQUEST STOP
 *
 *
 *     join()
 *         =
 *     WAIT FOR COMPLETION
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD CONNECTION
 * ------------------------------------------------------------
 *
 * This pattern is related to server shutdown.
 *
 * When an application shuts down:
 *
 *     Stop accepting new work
 *          ↓
 *     Ask workers to stop
 *          ↓
 *     Workers finish/cleanup
 *          ↓
 *     Wait for workers
 *          ↓
 *     Release resources
 *          ↓
 *     Application exits
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT CANCELLATION RULE
 * ------------------------------------------------------------
 *
 * A worker should NOT simply ignore interruption.
 *
 * BAD:
 *
 *     catch (InterruptedException e) {
 *         // ignore
 *     }
 *
 *
 * BETTER:
 *
 *     catch (InterruptedException e) {
 *         Thread.currentThread().interrupt();
 *         return;
 *     }
 *
 *
 * This allows the worker to terminate cooperatively.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the number of workers from:
 *
 *     3
 *
 * to:
 *
 *     10
 *
 * Observe that the shutdown mechanism doesn't fundamentally
 * change.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Remove one interrupt() call.
 *
 * Example:
 *
 *     workers[0].interrupt();
 *     workers[1].interrupt();
 *
 * but don't interrupt worker-3.
 *
 * Observe what happens when main calls:
 *
 *     workers[2].join();
 *
 * The main thread may wait indefinitely because worker-3 is
 * still running.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make one worker take longer to clean up.
 *
 * Observe that the main thread waits for it during join().
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add a shutdown message:
 *
 *     "Server stopping..."
 *
 * and:
 *
 *     "Server stopped."
 *
 * Think about where these messages should appear.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why do we use interrupt() during shutdown?
 *
 * 2. Why do we call join() after interrupt()?
 *
 * 3. Does interrupt() guarantee that the worker has stopped?
 *
 * No.
 *
 * It only requests interruption.
 *
 *
 * 4. What happens if a worker ignores interruption?
 *
 * It may continue running, causing shutdown to wait or fail
 * to complete.
 *
 *
 * 5. Why should shutdown logic be cooperative?
 *
 * Because workers may need to:
 *
 *     close resources
 *     finish safe operations
 *     release locks
 *     flush data
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Remember:
 *
 *
 *     interrupt()
 *          ↓
 *     REQUEST SHUTDOWN
 *
 *
 *     join()
 *          ↓
 *     WAIT FOR SHUTDOWN
 *
 *
 * Combined:
 *
 *
 *     interrupt()
 *          ↓
 *     cleanup
 *          ↓
 *     thread exits
 *          ↓
 *     join() returns
 *
 *
 * This pattern is foundational for understanding executors,
 * thread pools, and application shutdown.
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 21:
 *
 *     ThreadCoordinationBarrier.java
 *
 * We will introduce a different coordination problem:
 *
 *     Multiple workers must all reach a certain point before
 *     ANY of them can continue.
 *
 * This introduces the concept of a BARRIER.
 *
 * ============================================================
 */

public class MultiWorkerShutdown {

    /*
     * Worker task.
     */
    static class Worker implements Runnable {

        private final int workerId;

        Worker(int workerId) {
            this.workerId = workerId;
        }

        @Override
        public void run() {

            try {

                System.out.println(
                        "Worker-"
                                + workerId
                                + " started."
                );

                while (true) {

                    /*
                     * Simulate processing work.
                     */
                    System.out.println(
                            "Worker-"
                                    + workerId
                                    + " processing..."
                    );

                    /*
                     * Simulate a blocking operation.
                     *
                     * interrupt() can wake this worker by
                     * causing InterruptedException.
                     */
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {

                /*
                 * The worker has received the shutdown request.
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " received shutdown request."
                );

                /*
                 * Restore interrupt status.
                 */
                Thread.currentThread().interrupt();
            }

            /*
             * Cleanup.
             */
            System.out.println(
                    "Worker-"
                            + workerId
                            + " cleaning up..."
            );

            /*
             * Simulate cleanup.
             */
            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {

                /*
                 * Cleanup itself was interrupted.
                 */
                Thread.currentThread().interrupt();
            }

            System.out.println(
                    "Worker-"
                            + workerId
                            + " stopped."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        int numberOfWorkers = 3;

        Thread[] workers =
                new Thread[numberOfWorkers];

        /*
         * Create workers.
         */
        for (int i = 0;
             i < numberOfWorkers;
             i++) {

            workers[i] =
                    new Thread(
                            new Worker(i + 1),
                            "Worker-" + (i + 1)
                    );
        }

        /*
         * ----------------------------------------------------
         * START PHASE
         * ----------------------------------------------------
         */
        System.out.println(
                "Starting all workers..."
        );

        for (Thread worker : workers) {

            worker.start();
        }

        /*
         * Allow workers to process some work.
         */
        Thread.sleep(3000);

        /*
         * ----------------------------------------------------
         * SHUTDOWN PHASE
         * ----------------------------------------------------
         */
        System.out.println(
                "Server shutdown requested."
        );

        /*
         * Request shutdown from EVERY worker.
         */
        for (Thread worker : workers) {

            worker.interrupt();
        }

        /*
         * ----------------------------------------------------
         * WAIT PHASE
         * ----------------------------------------------------
         *
         * Wait until EVERY worker has actually stopped.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "All workers stopped."
        );

        System.out.println(
                "Server shutdown completed."
        );
    }
}
