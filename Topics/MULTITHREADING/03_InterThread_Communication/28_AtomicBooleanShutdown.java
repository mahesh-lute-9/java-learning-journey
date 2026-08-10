/*
 * ============================================================
 * 28 - GRACEFUL SHUTDOWN USING AtomicBoolean
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a server with a background worker.
 *
 * The worker continuously processes jobs.
 *
 * The application needs a thread-safe way to tell the worker:
 *
 *     "Stop processing."
 *
 *
 * We already solved this using:
 *
 *     volatile boolean
 *
 * Now we will use:
 *
 *     AtomicBoolean
 *
 *
 * ------------------------------------------------------------
 * WHY AtomicBoolean?
 * ------------------------------------------------------------
 *
 * AtomicBoolean provides atomic operations on a boolean value.
 *
 * Example:
 *
 *     AtomicBoolean running =
 *             new AtomicBoolean(true);
 *
 *
 * Read:
 *
 *     running.get();
 *
 *
 * Update:
 *
 *     running.set(false);
 *
 *
 * Conditional update:
 *
 *     running.compareAndSet(true, false);
 *
 *
 * ------------------------------------------------------------
 * VOLATILE VS AtomicBoolean
 * ------------------------------------------------------------
 *
 * volatile boolean:
 *
 *     volatile boolean running = true;
 *
 *     running = false;
 *
 *
 * AtomicBoolean:
 *
 *     AtomicBoolean running =
 *             new AtomicBoolean(true);
 *
 *     running.set(false);
 *
 *
 * For a simple flag where you only need visibility,
 * volatile is often perfectly appropriate.
 *
 *
 * AtomicBoolean becomes particularly useful when you need
 * atomic conditional operations.
 *
 *
 * Example:
 *
 *     compareAndSet(true, false)
 *
 *
 * This means:
 *
 *     "Change running from true to false ONLY if it is
 *      currently true."
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD SCENARIO
 * ------------------------------------------------------------
 *
 * Imagine multiple components may request server shutdown:
 *
 *
 *     Admin
 *       \
 *        \
 *         ---> Shutdown Controller
 *        /
 *       /
 *     Health Monitor
 *
 *
 * Multiple threads may try:
 *
 *     running.compareAndSet(true, false)
 *
 *
 * Only one thread successfully performs the state transition.
 *
 *
 * ------------------------------------------------------------
 * STATE TRANSITION
 * ------------------------------------------------------------
 *
 *
 *     RUNNING
 *        |
 *        | compareAndSet(true, false)
 *        v
 *     STOPPING
 *
 *
 * In this simple example we only have two states:
 *
 *     true  → running
 *     false → stopped
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * AtomicBoolean does NOT automatically stop a thread.
 *
 * It only provides a thread-safe shared state.
 *
 *
 * The worker must cooperate:
 *
 *
 *     while (running.get()) {
 *         process();
 *     }
 *
 *
 * When another thread changes:
 *
 *     running.set(false)
 *
 *
 * the worker eventually observes false and exits.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     running.set(false)
 *
 * with:
 *
 *     running.compareAndSet(true, false)
 *
 * Print whether the operation succeeded.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Create multiple shutdown-request threads.
 *
 * Let all of them call:
 *
 *     compareAndSet(true, false)
 *
 *
 * Ask:
 *
 *     How many threads should successfully perform the
 *     transition?
 *
 * Answer:
 *
 *     Only one.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace AtomicBoolean with:
 *
 *     volatile boolean
 *
 * and think about what you lose.
 *
 * You still have visibility.
 *
 * But you don't have atomic conditional operations such as
 * compareAndSet().
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Try:
 *
 *     if (running.get()) {
 *         running.set(false);
 *     }
 *
 *
 * Ask yourself:
 *
 *     Is this equivalent to compareAndSet(true, false)?
 *
 * No.
 *
 * Another thread could change the value between get() and set().
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is AtomicBoolean?
 *
 * 2. Difference between AtomicBoolean and volatile boolean?
 *
 * 3. What does compareAndSet() do?
 *
 * 4. Why is:
 *
 *       get() + set()
 *
 *    not always equivalent to:
 *
 *       compareAndSet()
 *
 * 5. Does AtomicBoolean stop a thread?
 *
 * No.
 *
 * 6. When would AtomicBoolean be useful?
 *
 *     State transitions
 *     Shutdown flags
 *     One-time initialization
 *     Start/stop state
 *     Lock-free coordination
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * volatile:
 *
 *     "Make this state visible."
 *
 *
 * AtomicBoolean:
 *
 *     "Make this state visible AND provide atomic operations
 *      on it."
 *
 *
 * compareAndSet():
 *
 *     "Change the state only if it still has the value I
 *      expect."
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 29:
 *
 *     OneTimeInitialization.java
 *
 * We will use AtomicBoolean for a classic concurrent problem:
 *
 *     "Initialize this resource exactly ONCE, even if many
 *      threads try to initialize it simultaneously."
 *
 * ============================================================
 */

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanShutdown {

    /*
     * Shared thread-safe shutdown state.
     *
     * true  = worker should continue
     * false = shutdown requested
     */
    private static final AtomicBoolean running =
            new AtomicBoolean(true);

    static class Worker implements Runnable {

        @Override
        public void run() {

            System.out.println(
                    Thread.currentThread().getName()
                            + " started."
            );

            /*
             * Worker cooperatively checks the shared state.
             */
            while (running.get()) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " processing..."
                );

                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                    /*
                     * Restore interruption status.
                     */
                    Thread.currentThread().interrupt();

                    return;
                }
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " detected shutdown."
            );

            System.out.println(
                    Thread.currentThread().getName()
                            + " stopped."
            );
        }
    }

    static class ShutdownRequester
            implements Runnable {

        private final String requesterName;

        ShutdownRequester(String requesterName) {

            this.requesterName =
                    requesterName;
        }

        @Override
        public void run() {

            /*
             * Try to perform the state transition:
             *
             *     true → false
             *
             * ONLY one requester can successfully perform this
             * transition.
             */
            boolean shutdownRequested =
                    running.compareAndSet(
                            true,
                            false
                    );

            if (shutdownRequested) {

                System.out.println(
                        requesterName
                                + " successfully requested shutdown."
                );

            } else {

                System.out.println(
                        requesterName
                                + " found shutdown already requested."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create the worker.
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
         * Let the worker process some jobs.
         */
        Thread.sleep(2000);

        System.out.println(
                "\nMultiple components are requesting shutdown...\n"
        );

        /*
         * Create multiple independent shutdown requesters.
         */
        Thread admin =
                new Thread(
                        new ShutdownRequester(
                                "Admin"
                        )
                );

        Thread healthMonitor =
                new Thread(
                        new ShutdownRequester(
                                "Health-Monitor"
                        )
                );

        Thread deploymentManager =
                new Thread(
                        new ShutdownRequester(
                                "Deployment-Manager"
                        )
                );

        /*
         * Start all shutdown requesters.
         */
        admin.start();
        healthMonitor.start();
        deploymentManager.start();

        /*
         * Wait for all requesters.
         */
        admin.join();
        healthMonitor.join();
        deploymentManager.join();

        /*
         * Wait for the worker to observe the shutdown state and
         * exit.
         */
        worker.join();

        System.out.println(
                "\nApplication shutdown completed."
        );
    }
}
