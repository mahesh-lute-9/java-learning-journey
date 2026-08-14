/*
 * ============================================================
 * 52 - ReadWriteLock FOR READ-HEAVY WORKLOADS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application storing application
 * configuration:
 *
 *     Database URL
 *     API endpoint
 *     Feature flags
 *     Application settings
 *
 *
 * Most operations are READS:
 *
 *     1000 threads → read configuration
 *
 *
 * Occasionally, an administrator updates the configuration:
 *
 *     1 thread → write configuration
 *
 *
 * REQUIREMENT:
 * ------------------------------------------------------------
 *
 *     Multiple readers should be allowed at the same time.
 *
 *     But when a writer modifies the configuration:
 *
 *         no reader should read partially updated data.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM WITH synchronized
 * ------------------------------------------------------------
 *
 * If we use one synchronized method:
 *
 *
 *     synchronized read()
 *
 *     synchronized write()
 *
 *
 * then only ONE thread can enter either method at a time.
 *
 *
 * Even if:
 *
 *     Reader A is reading
 *     Reader B is reading
 *
 *
 * Reader B must wait.
 *
 *
 * This can unnecessarily reduce concurrency when reads dominate.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     ReentrantReadWriteLock
 *
 *
 * It provides two locks:
 *
 *
 *     Read Lock
 *         ↓
 *     Multiple readers allowed
 *
 *
 *     Write Lock
 *         ↓
 *     Only one writer
 *         ↓
 *     Readers must wait
 *
 *
 * ------------------------------------------------------------
 * FLOW
 * ------------------------------------------------------------
 *
 *
 * Reader 1 ──┐
 * Reader 2 ──┤
 * Reader 3 ──┤── READ LOCK ──→ read together
 * Reader 4 ──┘
 *
 *
 * Writer:
 *
 *     WRITE LOCK
 *         ↓
 *     exclusive access
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT RULE
 * ------------------------------------------------------------
 *
 * Multiple readers can hold the read lock simultaneously.
 *
 *
 * But a writer requires exclusive access.
 *
 *
 * Therefore:
 *
 *
 *     Readers + Readers
 *         ↓
 *       ALLOWED
 *
 *
 *     Reader + Writer
 *         ↓
 *       BLOCKED
 *
 *
 *     Writer + Writer
 *         ↓
 *       BLOCKED
 *
 *
 * ------------------------------------------------------------
 * WHY Reentrant?
 * ------------------------------------------------------------
 *
 * Reentrant means the same thread can acquire the same lock
 * again under the supported reentrant locking semantics.
 *
 *
 * This is useful when a method holding a lock calls another
 * method that also acquires the same lock.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Always release locks in finally:
 *
 *
 *     lock.lock();
 *
 *     try {
 *         // work
 *     } finally {
 *         lock.unlock();
 *     }
 *
 *
 * Otherwise a lock can remain permanently held.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Configuration storage
 *     In-memory cache
 *     Routing tables
 *     Metadata
 *     Reference data
 *     Read-heavy services
 *
 *
 * ------------------------------------------------------------
 * WHEN NOT TO USE IT
 * ------------------------------------------------------------
 *
 * ReadWriteLock is not automatically faster.
 *
 *
 * If:
 *
 *     writes are very frequent
 *
 * or:
 *
 *     reads are extremely short
 *
 *
 * the additional locking complexity may not provide a benefit.
 *
 *
 * Choose it based on the workload.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Increase the number of readers.
 *
 *
 * Observe that multiple readers can execute simultaneously.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make the writer sleep for 3 seconds.
 *
 *
 * Observe that readers cannot access the configuration while
 * the writer holds the write lock.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace ReadWriteLock with synchronized.
 *
 *
 * Compare how many readers can execute simultaneously.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Make writes very frequent.
 *
 *
 * Ask:
 *
 *     Is ReadWriteLock still a good choice?
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is ReadWriteLock?
 *
 * A lock that separates read access from write access.
 *
 *
 * 2. Can multiple readers hold the read lock simultaneously?
 *
 * Yes.
 *
 *
 * 3. Can a reader and writer hold their locks simultaneously?
 *
 * No.
 *
 *
 * 4. Can multiple writers hold the write lock simultaneously?
 *
 * No.
 *
 *
 * 5. Why use ReadWriteLock?
 *
 * It can improve concurrency for read-heavy workloads.
 *
 *
 * 6. What happens when a writer is waiting?
 *
 * The exact scheduling/fairness behavior depends on the lock
 * configuration and implementation policy.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * Normal exclusive lock:
 *
 *     Reader
 *        ↓
 *     Reader
 *        ↓
 *     Reader
 *
 *     One at a time
 *
 *
 * ReadWriteLock:
 *
 *     Reader ──┐
 *     Reader ──┤
 *     Reader ──┤──→ concurrent reads
 *     Reader ──┘
 *
 *     Writer → exclusive access
 *
 *
 * Think:
 *
 *     MANY readers
 *     FEW writers
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 53:
 *
 *     ReentrantLockScenario.java
 *
 * We will move from synchronized to ReentrantLock and learn
 * practical features such as:
 *
 *     tryLock()
 *     lockInterruptibly()
 *     explicit lock/unlock
 *
 * ============================================================
 */

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockScenario {

    /*
     * Shared configuration object.
     */
    private static String configuration =
            "version=1";

    /*
     * ReadWriteLock provides:
     *
     *     readLock()
     *     writeLock()
     */
    private static final ReadWriteLock lock =
            new ReentrantReadWriteLock();

    /*
     * Read configuration.
     *
     * Multiple readers can execute this method concurrently.
     */
    private static String readConfiguration() {

        /*
         * Acquire read lock.
         */
        lock.readLock().lock();

        try {

            System.out.println(
                    Thread.currentThread().getName()
                            + " started reading."
            );

            /*
             * Simulate reading configuration.
             */
            sleep(1000);

            return configuration;

        } finally {

            /*
             * Always release the read lock.
             */
            lock.readLock().unlock();

            System.out.println(
                    Thread.currentThread().getName()
                            + " finished reading."
            );
        }
    }

    /*
     * Update configuration.
     *
     * Only one writer can execute this operation at a time.
     *
     * Readers must wait while the write lock is held.
     */
    private static void updateConfiguration(
            String newConfiguration) {

        /*
         * Acquire exclusive write lock.
         */
        lock.writeLock().lock();

        try {

            System.out.println(
                    Thread.currentThread().getName()
                            + " started writing."
            );

            /*
             * Simulate an expensive update.
             */
            sleep(2000);

            configuration =
                    newConfiguration;

            System.out.println(
                    Thread.currentThread().getName()
                            + " updated configuration."
            );

        } finally {

            /*
             * Always release the write lock.
             */
            lock.writeLock().unlock();

            System.out.println(
                    Thread.currentThread().getName()
                            + " finished writing."
            );
        }
    }

    private static void sleep(
            long milliseconds) {

        try {

            Thread.sleep(
                    milliseconds
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Operation interrupted.",
                    e
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create several reader threads.
         */
        Thread reader1 =
                new Thread(
                        () -> System.out.println(
                                "Reader 1 got: "
                                        + readConfiguration()
                        ),
                        "Reader-1"
                );

        Thread reader2 =
                new Thread(
                        () -> System.out.println(
                                "Reader 2 got: "
                                        + readConfiguration()
                        ),
                        "Reader-2"
                );

        Thread reader3 =
                new Thread(
                        () -> System.out.println(
                                "Reader 3 got: "
                                        + readConfiguration()
                        ),
                        "Reader-3"
                );

        /*
         * Create a writer.
         */
        Thread writer =
                new Thread(
                        () -> updateConfiguration(
                                "version=2"
                        ),
                        "Writer"
                );

        /*
         * Start readers first.
         *
         * They can acquire the read lock concurrently.
         */
        reader1.start();
        reader2.start();
        reader3.start();

        /*
         * Give readers a small head start so the output clearly
         * demonstrates concurrent reading.
         */
        Thread.sleep(200);

        /*
         * Start writer.
         *
         * The writer must wait until active readers release the
         * read lock.
         */
        writer.start();

        /*
         * Wait for all threads.
         */
        reader1.join();
        reader2.join();
        reader3.join();
        writer.join();

        /*
         * Verify the updated configuration.
         */
        System.out.println(
                "\nFinal configuration: "
                        + readConfiguration()
        );
    }
}
