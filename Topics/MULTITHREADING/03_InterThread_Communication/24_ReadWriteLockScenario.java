/*
 * ============================================================
 * 24 - READ-WRITE LOCK FOR SHARED CONFIGURATION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application with shared configuration:
 *
 *     Application Configuration
 *     -------------------------
 *     maxRetries = 3
 *     timeout    = 5000
 *
 * Many worker threads need to READ this configuration.
 *
 * Occasionally, an administrator updates the configuration.
 *
 *
 * REQUIREMENTS:
 * ------------------------------------------------------------
 *
 *     MANY readers can read simultaneously.
 *
 *     ONLY ONE writer can modify at a time.
 *
 *     While writing:
 *
 *         NO reader should read the data.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM WITH synchronized
 * ------------------------------------------------------------
 *
 * A simple synchronized method would allow:
 *
 *     Reader-1
 *     Reader-2
 *     Reader-3
 *
 * only one at a time.
 *
 * But these readers are not modifying the data.
 *
 * There is no reason for Reader-2 to wait for Reader-1 if
 * both are only reading.
 *
 *
 * This can unnecessarily reduce concurrency.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Java provides:
 *
 *     ReentrantReadWriteLock
 *
 *
 * It provides two locks:
 *
 *     READ LOCK
 *     WRITE LOCK
 *
 *
 * READ LOCK:
 *
 *     Multiple readers can hold it simultaneously.
 *
 *
 * WRITE LOCK:
 *
 *     Only one writer can hold it.
 *
 *     Readers are blocked while a writer has the lock.
 *
 *
 * ------------------------------------------------------------
 * VISUALIZATION
 * ------------------------------------------------------------
 *
 *
 *        Shared Configuration
 *                 |
 *        +--------+--------+
 *        |                 |
 *        v                 v
 *    READ LOCK          WRITE LOCK
 *        |                 |
 *        v                 v
 * Reader-1             Writer-1
 * Reader-2                |
 * Reader-3                |
 * Reader-4                |
 *        |                 |
 *   Can run together      Exclusive
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Read lock:
 *
 *     shared
 *
 * Write lock:
 *
 *     exclusive
 *
 *
 * This is useful when:
 *
 *     Reads are MUCH more frequent than writes.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD EXAMPLES
 * ------------------------------------------------------------
 *
 *     Application configuration
 *     Product catalog
 *     Cached reference data
 *     Routing tables
 *     Feature flags
 *     In-memory metadata
 *
 *
 * ------------------------------------------------------------
 * BASIC PATTERN
 * ------------------------------------------------------------
 *
 * READ:
 *
 *     readLock.lock();
 *
 *     try {
 *         readData();
 *     } finally {
 *         readLock.unlock();
 *     }
 *
 *
 * WRITE:
 *
 *     writeLock.lock();
 *
 *     try {
 *         updateData();
 *     } finally {
 *         writeLock.unlock();
 *     }
 *
 *
 * ------------------------------------------------------------
 * WHY finally?
 * ------------------------------------------------------------
 *
 * If an exception occurs while holding the lock and we don't
 * unlock it, other threads may remain blocked indefinitely.
 *
 *
 * Therefore:
 *
 *     lock()
 *
 * should normally be paired with:
 *
 *     unlock()
 *
 * inside:
 *
 *     finally
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the reader count to:
 *
 *     10
 *
 * Give each reader a long sleep.
 *
 * Notice that multiple readers can still enter the read
 * section concurrently.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Add multiple writers.
 *
 * Observe that writers execute one at a time.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace the ReadWriteLock with a normal synchronized method.
 *
 * Compare the behavior.
 *
 * Think:
 *
 *     Why can ReadWriteLock provide more concurrency for
 *     read-heavy workloads?
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Make writes very frequent.
 *
 * Ask yourself:
 *
 *     Is ReadWriteLock automatically better than synchronized?
 *
 * No.
 *
 * Lock choice depends on workload and contention.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is ReentrantReadWriteLock?
 *
 * 2. Why can multiple readers execute simultaneously?
 *
 * 3. Why is the write lock exclusive?
 *
 * 4. When is ReadWriteLock useful?
 *
 * 5. Is ReadWriteLock always faster than synchronized?
 *
 * No.
 *
 * 6. Why should unlock() be inside finally?
 *
 * 7. Can a thread holding the write lock also acquire the read
 *    lock?
 *
 * ReentrantReadWriteLock supports lock downgrading in
 * appropriate usage, but upgrading from read to write requires
 * care and is not directly supported as a simple upgrade.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * synchronized:
 *
 *     Reader 1
 *         ↓
 *     Reader 2
 *         ↓
 *     Reader 3
 *
 *     One at a time.
 *
 *
 * ReadWriteLock:
 *
 *     Reader 1 ─┐
 *     Reader 2 ─┤
 *     Reader 3 ─┤──> concurrent reads
 *     Reader 4 ─┘
 *
 *
 *     Writer
 *        ↓
 *     exclusive access
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 25:
 *
 *     AtomicCounter.java
 *
 * We will solve the classic counter problem WITHOUT using
 * synchronized, introducing atomic classes and compare-and-set.
 *
 * ============================================================
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockScenario {

    static class Configuration {

        /*
         * Shared configuration data.
         */
        private int maxRetries;
        private int timeout;

        /*
         * Create a ReadWriteLock.
         */
        private final ReentrantReadWriteLock lock =
                new ReentrantReadWriteLock();

        /*
         * Separate references make the intention clear.
         */
        private final Lock readLock =
                lock.readLock();

        private final Lock writeLock =
                lock.writeLock();

        Configuration(
                int maxRetries,
                int timeout) {

            this.maxRetries = maxRetries;
            this.timeout = timeout;
        }

        /*
         * ----------------------------------------------------
         * READ OPERATION
         * ----------------------------------------------------
         *
         * Multiple threads can execute this method
         * simultaneously.
         */
        public void readConfiguration(
                String readerName) {

            readLock.lock();

            try {

                System.out.println(
                        readerName
                                + " started reading configuration."
                );

                /*
                 * Simulate reading.
                 */
                Thread.sleep(1000);

                System.out.println(
                        readerName
                                + " -> maxRetries="
                                + maxRetries
                                + ", timeout="
                                + timeout
                );

                System.out.println(
                        readerName
                                + " finished reading."
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            } finally {

                /*
                 * Always release the read lock.
                 */
                readLock.unlock();
            }
        }

        /*
         * ----------------------------------------------------
         * WRITE OPERATION
         * ----------------------------------------------------
         *
         * Only one writer can execute this section.
         *
         * Readers are also blocked while the write lock is held.
         */
        public void updateConfiguration(
                int newMaxRetries,
                int newTimeout,
                String writerName) {

            writeLock.lock();

            try {

                System.out.println(
                        writerName
                                + " started updating configuration."
                );

                /*
                 * Simulate update operation.
                 */
                Thread.sleep(1500);

                maxRetries =
                        newMaxRetries;

                timeout =
                        newTimeout;

                System.out.println(
                        writerName
                                + " updated configuration."
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            } finally {

                /*
                 * Always release the write lock.
                 */
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Shared configuration object.
         */
        Configuration configuration =
                new Configuration(
                        3,
                        5000
                );

        /*
         * Multiple readers.
         */
        Thread reader1 =
                new Thread(
                        () -> configuration
                                .readConfiguration(
                                        "Reader-1"
                                )
                );

        Thread reader2 =
                new Thread(
                        () -> configuration
                                .readConfiguration(
                                        "Reader-2"
                                )
                );

        Thread reader3 =
                new Thread(
                        () -> configuration
                                .readConfiguration(
                                        "Reader-3"
                                )
                );

        /*
         * Writer.
         */
        Thread writer =
                new Thread(
                        () -> configuration
                                .updateConfiguration(
                                        5,
                                        10000,
                                        "Admin-Writer"
                                )
                );

        /*
         * Start readers.
         *
         * These readers can execute concurrently.
         */
        reader1.start();
        reader2.start();
        reader3.start();

        /*
         * Give readers a chance to acquire the read lock.
         */
        Thread.sleep(200);

        /*
         * Start writer.
         *
         * The writer must wait if readers currently hold the
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
         * Perform another read after the update.
         *
         * This should observe the new configuration.
         */
        configuration.readConfiguration(
                "Final-Reader"
        );

        System.out.println(
                "Configuration workflow completed."
        );
    }
}
