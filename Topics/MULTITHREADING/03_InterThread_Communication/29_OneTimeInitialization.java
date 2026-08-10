/*
 * ============================================================
 * 29 - THREAD-SAFE ONE-TIME INITIALIZATION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application that needs to initialize an
 * expensive shared resource:
 *
 *     AI Model
 *     Database Client
 *     Cache
 *     Configuration
 *     Connection Manager
 *
 *
 * The application has many worker threads.
 *
 * Several workers may simultaneously notice:
 *
 *     "Resource has not been initialized."
 *
 *
 * REQUIREMENT:
 * ------------------------------------------------------------
 *
 *     The resource must be initialized EXACTLY ONCE.
 *
 *
 * Example:
 *
 *     Worker-1 ──┐
 *     Worker-2 ──┤
 *     Worker-3 ──┤──> initialize()
 *     Worker-4 ──┤
 *     Worker-5 ──┘
 *
 *
 * Even if all five threads try at the same time:
 *
 *     initialize() must execute only ONCE.
 *
 *
 * ------------------------------------------------------------
 * NAIVE APPROACH
 * ------------------------------------------------------------
 *
 * Imagine:
 *
 *     if (!initialized) {
 *         initialize();
 *         initialized = true;
 *     }
 *
 *
 * This is NOT thread-safe.
 *
 *
 * Two threads can execute:
 *
 *
 * Thread A                  Thread B
 * --------                  --------
 *
 * if (!initialized)
 *                           if (!initialized)
 *
 * both see:
 *
 *     false
 *
 * initialize()
 *                           initialize()
 *
 *
 * RESULT:
 *
 *     Resource initialized twice.
 *
 *
 * ------------------------------------------------------------
 * WHY THIS MATTERS
 * ------------------------------------------------------------
 *
 * Duplicate initialization can cause:
 *
 *     Duplicate database connections
 *     Duplicate cache loading
 *     Duplicate file creation
 *     Duplicate network clients
 *     Incorrect application state
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     AtomicBoolean
 *
 *
 * The important operation is:
 *
 *     compareAndSet(false, true)
 *
 *
 * Meaning:
 *
 *     "If initialization has NOT happened yet, mark it as
 *      happening now."
 *
 *
 * Only ONE thread can successfully change:
 *
 *     false → true
 *
 *
 * ------------------------------------------------------------
 * STATE TRANSITION
 * ------------------------------------------------------------
 *
 *
 *     NOT INITIALIZED
 *             |
 *             |
 *     compareAndSet(false, true)
 *             |
 *             v
 *        INITIALIZING
 *             |
 *             v
 *         INITIALIZED
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT SUBTLETY
 * ------------------------------------------------------------
 *
 * AtomicBoolean protects the decision:
 *
 *     "Who gets to initialize?"
 *
 *
 * But we must also make sure that other threads don't use a
 * partially initialized resource.
 *
 *
 * A simple solution for this example is:
 *
 *     synchronized initialization
 *
 * or use a carefully designed publication mechanism.
 *
 *
 * This program deliberately demonstrates the basic
 * "exactly-one-winner" concept using AtomicBoolean.
 *
 *
 * ------------------------------------------------------------
 * WHAT DOES compareAndSet() GIVE US?
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     initialized = false
 *
 *
 * Thread A:
 *
 *     CAS(false, true)
 *
 *     SUCCESS
 *
 *
 * Thread B:
 *
 *     CAS(false, true)
 *
 *     FAILURE
 *
 *
 * Thread C:
 *
 *     CAS(false, true)
 *
 *     FAILURE
 *
 *
 * Therefore:
 *
 *     Only Thread A enters initialization.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     NUMBER_OF_WORKERS = 10
 *
 * to:
 *
 *     NUMBER_OF_WORKERS = 100
 *
 *
 * The initialization message should still appear only once.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     compareAndSet(false, true)
 *
 * with:
 *
 *     get()
 *     set(true)
 *
 *
 * Think about why multiple threads can now initialize the
 * resource.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make initialization take:
 *
 *     5 seconds
 *
 *
 * Start many workers.
 *
 * Think:
 *
 *     What should the other workers do while initialization is
 *     happening?
 *
 *
 * This leads to a more advanced state machine:
 *
 *     NOT_STARTED
 *     INITIALIZING
 *     READY
 *     FAILED
 *
 *
 * We will encounter these ideas later.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Try to make initialization fail.
 *
 * Ask:
 *
 *     What should happen to initialized if initialization
 *     throws an exception?
 *
 *
 * IMPORTANT:
 *
 * In production code, state transitions such as:
 *
 *     NOT_STARTED → INITIALIZING → READY
 *
 * should be designed explicitly.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. How can you guarantee that only one thread performs an
 *    initialization operation?
 *
 * 2. Why is check-then-act unsafe?
 *
 * 3. How does compareAndSet() solve the race?
 *
 * 4. Is AtomicBoolean enough to publish a complex object safely
 *    in every design?
 *
 * No.
 *
 * Correct publication and initialization visibility must also
 * be considered.
 *
 * 5. What is the difference between:
 *
 *     "initialized"
 *
 * and:
 *
 *     "initialization completed successfully"?
 *
 * These are not necessarily the same state.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * The important pattern is:
 *
 *
 *     if (state.compareAndSet(false, true)) {
 *
 *         initialize();
 *
 *     }
 *
 *
 * Only one thread wins the race.
 *
 *
 * This pattern is useful for:
 *
 *     One-time startup actions
 *     Lazy initialization
 *     Metrics registration
 *     Cache initialization
 *     Resource creation
 *     Shutdown hooks
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 30:
 *
 *     ThreadSafeSingleton.java
 *
 * We will apply thread-safety concepts to one of the most
 * famous Java interview problems:
 *
 *     How do you implement a Singleton safely when multiple
 *     threads can call getInstance() simultaneously?
 *
 * ============================================================
 */

import java.util.concurrent.atomic.AtomicBoolean;

public class OneTimeInitialization {

    /*
     * Tracks whether initialization has already been claimed.
     *
     * false = no thread has claimed initialization
     * true  = one thread has claimed initialization
     */
    private static final AtomicBoolean initializationStarted =
            new AtomicBoolean(false);

    /*
     * Simulated shared resource.
     */
    private static String resource;

    static class ApplicationWorker
            implements Runnable {

        private final int workerId;

        ApplicationWorker(int workerId) {

            this.workerId =
                    workerId;
        }

        @Override
        public void run() {

            /*
             * Try to become the ONE thread responsible for
             * initialization.
             */
            boolean initializationWinner =
                    initializationStarted.compareAndSet(
                            false,
                            true
                    );

            if (initializationWinner) {

                /*
                 * This thread won the race.
                 *
                 * No other thread should execute this block.
                 */
                initializeResource();

            } else {

                /*
                 * Another thread already claimed initialization.
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " did not initialize. "
                                + "Another thread won."
                );
            }

            /*
             * Simulate normal worker activity.
             */
            System.out.println(
                    "Worker-"
                            + workerId
                            + " continuing application work."
            );
        }
    }

    private static void initializeResource() {

        System.out.println(
                "\n"
                        + Thread.currentThread().getName()
                        + " WON initialization race."
        );

        System.out.println(
                "Initializing expensive resource..."
        );

        try {

            /*
             * Simulate expensive initialization.
             */
            Thread.sleep(2000);

        } catch (InterruptedException e) {

            /*
             * Restore interruption status.
             */
            Thread.currentThread().interrupt();

            System.out.println(
                    "Initialization interrupted."
            );

            return;
        }

        /*
         * Create the shared resource.
         */
        resource =
                "AI Model / Shared Resource";

        System.out.println(
                "Resource initialized by "
                        + Thread.currentThread().getName()
        );

        System.out.println(
                "Resource: "
                        + resource
        );
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create many workers.
         */
        int numberOfWorkers = 10;

        Thread[] workers =
                new Thread[numberOfWorkers];

        /*
         * Create worker threads.
         */
        for (int i = 0;
             i < numberOfWorkers;
             i++) {

            workers[i] =
                    new Thread(
                            new ApplicationWorker(i + 1),
                            "Worker-" + (i + 1)
                    );
        }

        /*
         * Start all workers.
         *
         * They will race to become the initialization winner.
         */
        for (Thread worker : workers) {

            worker.start();
        }

        /*
         * Wait for all workers.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "\nApplication initialization phase completed."
        );

        System.out.println(
                "Final resource: "
                        + resource
        );
    }
}
