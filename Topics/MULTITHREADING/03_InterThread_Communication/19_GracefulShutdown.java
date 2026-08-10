/*
 * ============================================================
 * 19 - GRACEFUL THREAD SHUTDOWN USING interrupt()
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend server with a background worker.
 *
 * The worker continuously processes jobs:
 *
 *     Job-1
 *     Job-2
 *     Job-3
 *     ...
 *
 * At some point, the application receives a shutdown request.
 *
 * We need to stop the worker safely.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * A common beginner mistake is to think:
 *
 *     "How do I kill this thread?"
 *
 * Java does NOT provide a safe general-purpose mechanism to
 * forcibly stop arbitrary thread execution.
 *
 * Instead, a thread should cooperate with its shutdown request.
 *
 *
 * The usual approach is:
 *
 *     1. Request shutdown.
 *     2. Interrupt the worker if it is blocked/waiting.
 *     3. Worker notices the request.
 *     4. Worker cleans up.
 *     5. Worker exits normally.
 *
 *
 * ------------------------------------------------------------
 * WHAT IS interrupt()?
 * ------------------------------------------------------------
 *
 * interrupt() is a mechanism for communicating:
 *
 *     "This thread should consider stopping what it is doing."
 *
 * It does NOT forcibly kill the thread.
 *
 *
 * If a thread is sleeping/waiting/joining, interrupt() can cause
 * the blocking method to throw InterruptedException.
 *
 *
 * Examples:
 *
 *     Thread.sleep()
 *     Object.wait()
 *     Thread.join()
 *
 *
 * can respond to interruption by throwing:
 *
 *     InterruptedException
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT:
 * ------------------------------------------------------------
 *
 * interrupt() is a REQUEST, not a command to instantly kill
 * the thread.
 *
 *
 * The worker must decide how to respond.
 *
 *
 * ------------------------------------------------------------
 * TWO COMMON CASES
 * ------------------------------------------------------------
 *
 * CASE 1:
 *
 * Worker is doing normal CPU work.
 *
 * It can check:
 *
 *     Thread.currentThread().isInterrupted()
 *
 *
 * CASE 2:
 *
 * Worker is blocked in:
 *
 *     sleep()
 *     wait()
 *     join()
 *
 * interrupt() can cause InterruptedException.
 *
 *
 * ------------------------------------------------------------
 * WHY RESTORE INTERRUPTED STATUS?
 * ------------------------------------------------------------
 *
 * Consider:
 *
 *     catch (InterruptedException e) {
 *         Thread.currentThread().interrupt();
 *     }
 *
 *
 * When InterruptedException is thrown, the interrupted status
 * is cleared.
 *
 * If the method cannot fully handle the interruption, restoring
 * the status allows higher-level code to know that interruption
 * occurred.
 *
 *
 * This is a very important professional Java practice.
 *
 *
 * ------------------------------------------------------------
 * BAD APPROACH
 * ------------------------------------------------------------
 *
 * Do NOT do this:
 *
 *     catch (InterruptedException e) {
 *         // ignore
 *     }
 *
 *
 * Silently swallowing interruption can prevent graceful
 * shutdown and make cancellation difficult to reason about.
 *
 *
 * ------------------------------------------------------------
 * CONCEPTS
 * ------------------------------------------------------------
 *
 * - interrupt()
 * - isInterrupted()
 * - InterruptedException
 * - Cooperative cancellation
 * - Graceful shutdown
 * - Thread lifecycle
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove:
 *
 *     worker.interrupt();
 *
 * from main().
 *
 * Observe that the worker continues running.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Remove:
 *
 *     Thread.currentThread().interrupt();
 *
 * from the catch block.
 *
 * Think about what information is lost when the interrupted
 * status is not restored.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Change the worker so that it performs a CPU-intensive loop
 * instead of sleep().
 *
 * Then check:
 *
 *     Thread.currentThread().isInterrupted()
 *
 * inside the loop.
 *
 *
 * This demonstrates that interrupt() does not automatically
 * stop CPU-bound work.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add cleanup logic:
 *
 *     close resources
 *     flush data
 *     save state
 *
 * before the worker exits.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Does interrupt() kill a thread?
 *
 * No.
 *
 * It requests interruption.
 *
 *
 * 2. What happens when sleep() is interrupted?
 *
 * InterruptedException is thrown.
 *
 *
 * 3. What is the difference between:
 *
 *     isInterrupted()
 *
 * and:
 *
 *     interrupted()
 *
 *
 * isInterrupted():
 *
 *     Checks the current thread's interrupted status without
 *     clearing it.
 *
 *
 * Thread.interrupted():
 *
 *     Checks the current thread's interrupted status AND clears
 *     it.
 *
 *
 * 4. Why should InterruptedException usually not be silently
 *    swallowed?
 *
 * Because interruption is an important cancellation/shutdown
 * signal.
 *
 *
 * 5. Why restore the interrupt status?
 *
 * So higher-level code can still observe that interruption
 * occurred.
 *
 *
 * 6. Is interrupt() guaranteed to immediately stop a thread?
 *
 * No.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Safe thread shutdown is cooperative:
 *
 *
 *     Main/Application
 *             |
 *             | interrupt()
 *             v
 *        Worker Thread
 *             |
 *       detects request
 *             |
 *          cleanup
 *             |
 *             v
 *           exit
 *
 *
 * NEVER think:
 *
 *     interrupt() = kill thread
 *
 *
 * Think:
 *
 *     interrupt() = request cancellation/interruption
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 20:
 *
 *     MultiWorkerShutdown.java
 *
 * We will extend this idea to multiple worker threads and
 * coordinate their shutdown cleanly.
 *
 * ============================================================
 */

public class GracefulShutdown {

    static class Worker implements Runnable {

        @Override
        public void run() {

            System.out.println(
                    Thread.currentThread().getName()
                            + " started."
            );

            try {

                /*
                 * Simulate a long-running background worker.
                 */
                while (true) {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " processing work..."
                    );

                    /*
                     * Simulate a blocking operation.
                     *
                     * If the thread is interrupted while
                     * sleeping, InterruptedException is thrown.
                     */
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {

                /*
                 * The worker has received a shutdown request.
                 */
                System.out.println(
                        Thread.currentThread().getName()
                                + " received shutdown request."
                );

                /*
                 * Restore interrupted status.
                 *
                 * InterruptedException clears the interrupt
                 * status when it is thrown.
                 */
                Thread.currentThread().interrupt();
            }

            /*
             * Cleanup should happen before the thread exits.
             */
            System.out.println(
                    Thread.currentThread().getName()
                            + " performing cleanup..."
            );

            System.out.println(
                    Thread.currentThread().getName()
                            + " stopped gracefully."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create the worker thread.
         */
        Thread worker =
                new Thread(
                        new Worker(),
                        "Background-Worker"
                );

        /*
         * Start the worker.
         */
        worker.start();

        /*
         * Let the worker perform some work.
         *
         * This sleep is only to make the scenario visible.
         */
        Thread.sleep(3500);

        System.out.println(
                "Main thread requesting shutdown..."
        );

        /*
         * Request interruption.
         *
         * If the worker is sleeping, sleep() will throw
         * InterruptedException.
         */
        worker.interrupt();

        /*
         * Wait until the worker has completed its cleanup
         * and exited.
         */
        worker.join();

        System.out.println(
                "Application shutdown completed."
        );
    }
}
