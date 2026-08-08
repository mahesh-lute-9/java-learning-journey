/*
 * ============================================================
 * 04 - THREAD LIFECYCLE
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a server that creates worker threads to process
 * customer requests.
 *
 * A monitoring system wants to know what each worker thread
 * is currently doing.
 *
 * For example:
 *
 *     Has the thread started?
 *     Is it currently running?
 *     Is it waiting?
 *     Is it sleeping?
 *     Is it blocked?
 *     Has it finished?
 *
 * Java provides Thread.State to represent these lifecycle
 * states.
 *
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * A thread does not simply have two states:
 *
 *     STARTED
 *     STOPPED
 *
 * It can move through several states during its lifetime.
 *
 *
 * JAVA THREAD STATES:
 * ------------------------------------------------------------
 *
 *     NEW
 *       |
 *       | start()
 *       v
 *   RUNNABLE
 *       |
 *       +----------------------+
 *       |                      |
 *       v                      v
 *    BLOCKED              WAITING
 *       |                      |
 *       |                      |
 *       +----------+-----------+
 *                  |
 *                  v
 *           TIMED_WAITING
 *                  |
 *                  v
 *              RUNNABLE
 *                  |
 *                  v
 *             TERMINATED
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 * Java officially defines six Thread.State values:
 *
 *     NEW
 *     RUNNABLE
 *     BLOCKED
 *     WAITING
 *     TIMED_WAITING
 *     TERMINATED
 *
 *
 * STATE 1: NEW
 * ------------------------------------------------------------
 * A Thread object has been created but start() has not been
 * called yet.
 *
 *
 * STATE 2: RUNNABLE
 * ------------------------------------------------------------
 * The thread is ready to run or is currently running.
 *
 * IMPORTANT:
 * Java does not have a separate RUNNING state in Thread.State.
 *
 * RUNNABLE includes both:
 *
 *     Ready to run
 *     Actually running
 *
 *
 * STATE 3: BLOCKED
 * ------------------------------------------------------------
 * A thread is waiting to acquire a monitor lock, usually when
 * entering a synchronized block/method whose lock is already
 * owned by another thread.
 *
 *
 * STATE 4: WAITING
 * ------------------------------------------------------------
 * A thread waits indefinitely for another thread to perform
 * some action.
 *
 * Examples:
 *
 *     Object.wait()
 *     Thread.join()
 *     LockSupport.park()
 *
 *
 * STATE 5: TIMED_WAITING
 * ------------------------------------------------------------
 * A thread waits for a specified amount of time.
 *
 * Examples:
 *
 *     Thread.sleep()
 *     Object.wait(timeout)
 *     Thread.join(timeout)
 *
 *
 * STATE 6: TERMINATED
 * ------------------------------------------------------------
 * The thread has completed execution.
 *
 *
 * GOAL:
 * ------------------------------------------------------------
 * Observe different thread states while a thread moves through
 * its lifecycle.
 *
 *
 * CONCEPTS:
 * ------------------------------------------------------------
 * - Thread.State
 * - NEW
 * - RUNNABLE
 * - TIMED_WAITING
 * - TERMINATED
 * - Thread.sleep()
 * - Thread.join()
 *
 *
 * IMPORTANT OBSERVATION:
 * ------------------------------------------------------------
 * Thread states can change very quickly.
 *
 * Therefore, the exact state printed at a particular instant
 * can depend on thread scheduling.
 *
 *
 * EXPERIMENT 1:
 * ------------------------------------------------------------
 * Print the state before start():
 *
 *     System.out.println(thread.getState());
 *
 * Expected:
 *
 *     NEW
 *
 *
 * EXPERIMENT 2:
 * ------------------------------------------------------------
 * Print the state immediately after start().
 *
 * You will normally observe:
 *
 *     RUNNABLE
 *
 * But timing can affect exactly what you observe.
 *
 *
 * EXPERIMENT 3:
 * ------------------------------------------------------------
 * Increase the sleep time from 3000 ms to 10000 ms.
 *
 * This gives you more time to observe TIMED_WAITING.
 *
 *
 * EXPERIMENT 4:
 * ------------------------------------------------------------
 * Create two threads where one thread waits for another using
 * join().
 *
 * Observe the WAITING state.
 *
 *
 * INTERVIEW QUESTIONS:
 * ------------------------------------------------------------
 *
 * 1. How many states does a Java thread have?
 *
 * 2. What is the difference between NEW and RUNNABLE?
 *
 * 3. Does Java have a separate RUNNING state?
 *
 * 4. When does a thread enter BLOCKED state?
 *
 * 5. Difference between WAITING and TIMED_WAITING?
 *
 * 6. When does a thread enter TERMINATED state?
 *
 * 7. Can a TERMINATED thread be started again?
 *
 * Answer:
 * No.
 *
 *
 * KEY TAKEAWAY:
 * ------------------------------------------------------------
 * A thread moves through different states depending on what
 * it is doing and what resources or events it is waiting for.
 *
 * Understanding these states becomes extremely important when
 * debugging:
 *
 *     Deadlocks
 *     Performance problems
 *     Stuck threads
 *     Thread pool issues
 *
 *
 * ============================================================
 */

public class ThreadLifecycle {

    static class Worker extends Thread {

        @Override
        public void run() {

            System.out.println(
                    "Worker started."
            );

            System.out.println(
                    "Worker state from inside run(): "
                            + getState()
            );

            try {

                /*
                 * sleep() causes the current thread to enter
                 * TIMED_WAITING.
                 */
                Thread.sleep(3000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }

            System.out.println(
                    "Worker finished."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Worker worker =
                new Worker();

        /*
         * The thread object exists, but start() has not been
         * called.
         *
         * Expected:
         *
         * NEW
         */
        System.out.println(
                "1. Before start: "
                        + worker.getState()
        );

        /*
         * Start the worker thread.
         */
        worker.start();

        /*
         * Give the worker a small amount of time to enter
         * its sleep state.
         *
         * This is only for demonstration.
         */
        Thread.sleep(100);

        /*
         * The worker should normally be in TIMED_WAITING because
         * it is currently sleeping.
         */
        System.out.println(
                "2. While worker is sleeping: "
                        + worker.getState()
        );

        /*
         * join() makes the main thread wait until the worker
         * finishes.
         */
        worker.join();

        /*
         * After run() completes, the thread becomes TERMINATED.
         */
        System.out.println(
                "3. After completion: "
                        + worker.getState()
        );
    }
}
