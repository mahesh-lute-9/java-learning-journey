/*
 * ============================================================
 * 06 - BACKGROUND CLEANUP USING DAEMON THREAD
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a server application that runs continuously.
 *
 * While the application is running, it generates temporary
 * files, cache entries, logs, etc.
 *
 * We want a background worker to periodically clean up
 * unnecessary temporary data.
 *
 * The cleanup worker is NOT the main purpose of the application.
 *
 * If the application shuts down, we don't want this background
 * cleanup thread to keep the JVM alive.
 *
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * A normal Java thread is a non-daemon thread by default.
 *
 * The JVM normally stays alive while non-daemon threads are
 * still running.
 *
 * For background activities such as:
 *
 *     Cleanup
 *     Monitoring
 *     Statistics
 *     Cache maintenance
 *
 * we may want a thread that does not prevent JVM shutdown.
 *
 *
 * SOLUTION:
 * ------------------------------------------------------------
 * Use a DAEMON THREAD.
 *
 * A daemon thread is a background thread whose lifetime is tied
 * to the JVM's need to keep running.
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 * Use:
 *
 *     thread.setDaemon(true);
 *
 * BEFORE:
 *
 *     thread.start();
 *
 * Once a thread has been started, its daemon status cannot
 * normally be changed.
 *
 *
 * VISUALIZATION:
 * ------------------------------------------------------------
 *
 *                 JVM
 *                  |
 *          +-------+-------+
 *          |               |
 *          v               v
 *     Main Thread     Daemon Thread
 *          |               |
 *          |               |
 *       Application     Cleanup
 *          |               |
 *          v               v
 *       Finishes        Background Work
 *
 *
 * When all NON-DAEMON threads finish:
 *
 *                 JVM
 *                  |
 *                  v
 *              TERMINATES
 *
 * The daemon thread does NOT keep the JVM alive.
 *
 *
 * IMPORTANT WARNING:
 * ------------------------------------------------------------
 * Daemon threads are NOT a replacement for proper shutdown
 * mechanisms.
 *
 * Do NOT use daemon threads for critical operations such as:
 *
 *     Database transactions
 *     Financial transactions
 *     Important file writes
 *     Payment processing
 *
 * because the JVM can terminate while the daemon thread is
 * still working.
 *
 *
 * GOOD USE CASES:
 * ------------------------------------------------------------
 * - Background monitoring
 * - Cache cleanup
 * - Non-critical maintenance
 * - Background statistics
 *
 *
 * CONCEPTS:
 * ------------------------------------------------------------
 * - Daemon thread
 * - setDaemon()
 * - JVM shutdown
 * - Background processing
 *
 *
 * EXPERIMENT 1:
 * ------------------------------------------------------------
 * Change:
 *
 *     cleanupThread.setDaemon(true);
 *
 * to:
 *
 *     cleanupThread.setDaemon(false);
 *
 * Run the program.
 *
 * Observe that the cleanup thread can keep the JVM alive.
 *
 *
 * EXPERIMENT 2:
 * ------------------------------------------------------------
 * Remove the setDaemon(true) line completely.
 *
 * Threads are non-daemon by default.
 *
 *
 * EXPERIMENT 3:
 * ------------------------------------------------------------
 * Try:
 *
 *     cleanupThread.start();
 *     cleanupThread.setDaemon(true);
 *
 * This should fail because daemon status must be configured
 * before starting the thread.
 *
 *
 * INTERVIEW QUESTIONS:
 * ------------------------------------------------------------
 *
 * 1. What is a daemon thread?
 *
 * 2. What is the difference between daemon and non-daemon
 *    threads?
 *
 * 3. Does the JVM wait for daemon threads?
 *
 * 4. When must setDaemon(true) be called?
 *
 * 5. Can a running thread be converted into a daemon thread?
 *
 * 6. Should critical business operations run on daemon threads?
 *
 *
 * KEY TAKEAWAY:
 * ------------------------------------------------------------
 * A daemon thread is useful for background work that should not
 * prevent JVM termination.
 *
 * Remember:
 *
 *     Non-Daemon Thread
 *         ↓
 *     Keeps JVM Alive
 *
 *     Daemon Thread
 *         ↓
 *     Does NOT Keep JVM Alive
 *
 *
 * ============================================================
 */

public class BackgroundCleanup {

    static class CleanupTask extends Thread {

        @Override
        public void run() {

            while (true) {

                System.out.println(
                        "Background cleanup running..."
                );

                try {

                    /*
                     * Simulate periodic cleanup.
                     */
                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    System.out.println(
                            "Cleanup thread interrupted."
                    );

                    return;
                }
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println(
                "Application started."
        );

        CleanupTask cleanupThread =
                new CleanupTask();

        /*
         * Configure this thread as a daemon BEFORE calling
         * start().
         */
        cleanupThread.setDaemon(true);

        cleanupThread.setName(
                "Background-Cleanup"
        );

        cleanupThread.start();

        /*
         * Simulate the main application running for a few
         * seconds.
         */
        Thread.sleep(3000);

        System.out.println(
                "Main application finished."
        );

        /*
         * No explicit cleanupThread.stop() is used.
         *
         * Because cleanupThread is a daemon thread, once the
         * main thread and other non-daemon threads finish, the
         * JVM is allowed to terminate.
         */
    }
}
