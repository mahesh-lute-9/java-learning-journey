/*
 * ============================================================
 * 27 - THREAD VISIBILITY USING volatile
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend server with a background worker.
 *
 * The worker continuously processes tasks:
 *
 *     while (running) {
 *         processTask();
 *     }
 *
 * The main/server thread wants to shut the worker down.
 *
 * It changes:
 *
 *     running = false;
 *
 *
 * EXPECTED:
 * ------------------------------------------------------------
 *
 * The worker should eventually observe:
 *
 *     running == false
 *
 * and exit.
 *
 *
 * ------------------------------------------------------------
 * THE CONCURRENCY PROBLEM
 * ------------------------------------------------------------
 *
 * Multiple threads may access the same variable.
 *
 * Without proper visibility guarantees, one thread's update
 * is not something another thread is automatically guaranteed
 * to observe immediately.
 *
 *
 * This is a VISIBILITY problem.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DISTINCTION
 * ------------------------------------------------------------
 *
 * Earlier we studied:
 *
 *     AtomicInteger
 *
 * which deals with atomic operations such as:
 *
 *     increment
 *     compareAndSet
 *
 *
 * Here the problem is different.
 *
 * We mainly need:
 *
 *     "When one thread changes this flag, other threads must
 *      reliably observe the latest value."
 *
 *
 * This is where volatile becomes important.
 *
 *
 * ------------------------------------------------------------
 * volatile
 * ------------------------------------------------------------
 *
 * Declaring:
 *
 *     private volatile boolean running = true;
 *
 *
 * tells Java that accesses to this variable have special
 * visibility/ordering guarantees across threads.
 *
 *
 * Conceptually:
 *
 *
 *     Main Thread
 *          |
 *          | running = false
 *          v
 *       volatile
 *          |
 *          v
 *     Worker Thread
 *          |
 *          v
 *     observes false
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT:
 * ------------------------------------------------------------
 *
 * volatile DOES NOT make every operation atomic.
 *
 *
 * For example:
 *
 *     volatile int counter;
 *
 *
 * does NOT make:
 *
 *     counter++;
 *
 * atomic.
 *
 *
 * Why?
 *
 * Because:
 *
 *     counter++
 *
 * is still:
 *
 *     read
 *     modify
 *     write
 *
 *
 * volatile mainly solves visibility and ordering concerns.
 *
 *
 * ------------------------------------------------------------
 * GOOD USE CASE
 * ------------------------------------------------------------
 *
 * A volatile flag is useful for:
 *
 *     Shutdown signals
 *     Configuration flags
 *     State indicators
 *     Simple publication/status variables
 *
 *
 * Example:
 *
 *     volatile boolean running;
 *
 *
 * One thread:
 *
 *     running = false;
 *
 *
 * Another:
 *
 *     while (running) {
 *         ...
 *     }
 *
 *
 * ------------------------------------------------------------
 * VOLATILE VS ATOMIC
 * ------------------------------------------------------------
 *
 *
 * volatile:
 *
 *     Visibility + ordering
 *
 *
 * AtomicInteger:
 *
 *     Visibility + atomic operations
 *
 *
 * Example:
 *
 *     volatile boolean running
 *
 * is appropriate for a simple flag.
 *
 *
 *     AtomicInteger counter
 *
 * is appropriate when you need atomic increments/CAS.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove:
 *
 *     volatile
 *
 * from the running variable.
 *
 *
 * IMPORTANT:
 *
 * Do NOT conclude that the program must always fail.
 *
 * Modern JVMs and hardware may still make the update visible.
 *
 * The problem is that without the required memory guarantees,
 * the program does not have a correct concurrency contract.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     volatile boolean
 *
 * with:
 *
 *     AtomicBoolean
 *
 *
 * Compare the two approaches.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Try:
 *
 *     volatile int counter = 0;
 *
 * and have multiple threads execute:
 *
 *     counter++;
 *
 *
 * You may get an incorrect result.
 *
 *
 * This demonstrates:
 *
 *     volatile ≠ atomic compound operation
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Change the worker to:
 *
 *     while (!Thread.currentThread().isInterrupted())
 *
 *
 * and stop it using:
 *
 *     interrupt()
 *
 *
 * Compare:
 *
 *     volatile shutdown flag
 *
 * versus:
 *
 *     interruption
 *
 *
 * Both are useful, but they solve slightly different
 * coordination/cancellation problems.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What problem does volatile solve?
 *
 * Visibility and memory-ordering guarantees.
 *
 *
 * 2. Does volatile make operations atomic?
 *
 * No.
 *
 *
 * 3. Is volatile int counter safe for counter++?
 *
 * No.
 *
 *
 * 4. When is volatile useful?
 *
 * When threads need a shared variable with visibility and
 * ordering guarantees, especially for simple state/flag
 * variables.
 *
 *
 * 5. Difference between volatile and AtomicInteger?
 *
 * volatile provides visibility/ordering for the variable.
 *
 * AtomicInteger additionally provides atomic operations such
 * as incrementAndGet() and compareAndSet().
 *
 *
 * 6. Is synchronized only about mutual exclusion?
 *
 * No.
 *
 * It also provides memory-visibility guarantees around
 * monitor operations.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * There are THREE different ideas you should now separate:
 *
 *
 * 1. ATOMICITY
 *
 *     "Does this operation happen as one indivisible operation?"
 *
 *
 * 2. VISIBILITY
 *
 *     "Will another thread reliably see my update?"
 *
 *
 * 3. ORDERING
 *
 *     "Can memory operations be observed/reordered in ways that
 *      violate the required happens-before relationship?"
 *
 *
 * volatile is primarily about:
 *
 *     VISIBILITY + ORDERING
 *
 *
 * AtomicInteger is about:
 *
 *     ATOMIC OPERATIONS + VISIBILITY
 *
 *
 * synchronized/Lock provides:
 *
 *     MUTUAL EXCLUSION + VISIBILITY/ORDERING
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 28:
 *
 *     AtomicBooleanShutdown.java
 *
 * We will implement the same shutdown scenario using
 * AtomicBoolean and compare it with volatile.
 *
 * ============================================================
 */

public class VolatileFlag {

    /*
     * Shared shutdown flag.
     *
     * volatile ensures that changes made by one thread are
     * visible to other threads accessing this variable.
     */
    private static volatile boolean running = true;

    static class Worker implements Runnable {

        @Override
        public void run() {

            System.out.println(
                    Thread.currentThread().getName()
                            + " started."
            );

            /*
             * Continue working while the application has not
             * requested shutdown.
             */
            while (running) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " processing..."
                );

                /*
                 * Simulate work.
                 */
                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                    /*
                     * Restore interrupted status.
                     */
                    Thread.currentThread().interrupt();

                    /*
                     * Exit because the worker was interrupted.
                     */
                    return;
                }
            }

            /*
             * The worker observed:
             *
             *     running == false
             *
             * and exits gracefully.
             */
            System.out.println(
                    Thread.currentThread().getName()
                            + " observed shutdown flag."
            );

            System.out.println(
                    Thread.currentThread().getName()
                            + " stopped."
            );
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
         * Allow the worker to process some work.
         */
        Thread.sleep(2500);

        System.out.println(
                "Main thread requesting shutdown..."
        );

        /*
         * Change the shared volatile flag.
         *
         * The worker should observe this update.
         */
        running = false;

        /*
         * Wait until the worker actually exits.
         */
        worker.join();

        System.out.println(
                "Application shutdown completed."
        );
    }
}
