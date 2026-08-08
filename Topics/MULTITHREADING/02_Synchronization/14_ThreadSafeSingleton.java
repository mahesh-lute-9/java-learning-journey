/*
 * ============================================================
 * 14 - THREAD-SAFE SINGLETON
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an application-wide configuration manager.
 *
 * The application should have exactly ONE instance of this
 * manager.
 *
 * Multiple threads may try to access/create it at the same
 * time.
 *
 *
 * REQUIREMENTS:
 * ------------------------------------------------------------
 *
 * 1. Only one ConfigurationManager object should exist.
 *
 * 2. Multiple threads should be able to request the instance.
 *
 * 3. The implementation must be thread-safe.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * A simple Singleton implementation might look like:
 *
 *
 *     if (instance == null) {
 *         instance = new Singleton();
 *     }
 *
 *
 * But this is NOT thread-safe.
 *
 *
 * Imagine:
 *
 * Thread A                     Thread B
 * --------                     --------
 *
 * instance == null
 *                              instance == null
 *
 * Create object
 *                              Create object
 *
 *
 * Now two objects may be created.
 *
 * That violates the Singleton requirement.
 *
 *
 * ------------------------------------------------------------
 * WHY DOES THIS HAPPEN?
 * ------------------------------------------------------------
 *
 * The operation:
 *
 *     if (instance == null)
 *         instance = new Singleton();
 *
 * contains multiple steps:
 *
 *     1. Read instance
 *     2. Check null
 *     3. Create object
 *     4. Assign reference
 *
 * Multiple threads can interleave these steps.
 *
 *
 * ------------------------------------------------------------
 * SIMPLE SOLUTION
 * ------------------------------------------------------------
 *
 * Synchronize the access method.
 *
 *
 *     synchronized getInstance()
 *
 *
 * This ensures that only one thread at a time can execute the
 * creation/access logic.
 *
 *
 * ------------------------------------------------------------
 * TRADE-OFF
 * ------------------------------------------------------------
 *
 * The simple synchronized approach is easy to understand and
 * correct.
 *
 * But every call to getInstance() acquires the lock.
 *
 * Once the object has already been created, locking every call
 * may be unnecessary.
 *
 *
 * More advanced Singleton patterns exist, such as:
 *
 *     Double-Checked Locking
 *     Initialization-on-demand holder
 *     Enum Singleton
 *
 *
 * We are intentionally starting with the simple synchronized
 * approach because the goal of this program is to understand
 * the THREAD-SAFETY problem first.
 *
 *
 * ------------------------------------------------------------
 * CONCEPTS
 * ------------------------------------------------------------
 *
 * - Singleton
 * - Shared object
 * - Race condition
 * - synchronized
 * - Static state
 * - Thread safety
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove synchronized from getInstance().
 *
 * Add an artificial delay between the null check and object
 * creation.
 *
 * Then create many threads.
 *
 * Try to reproduce multiple instances.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Create 100 threads that all call:
 *
 *     ConfigurationManager.getInstance()
 *
 * Store the returned objects in an array.
 *
 * Verify that every reference points to the same object.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace the implementation with eager initialization:
 *
 *     private static final ConfigurationManager INSTANCE =
 *             new ConfigurationManager();
 *
 * Think about why this approach is naturally thread-safe.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Research and implement:
 *
 *     Double-Checked Locking
 *
 * using:
 *
 *     volatile
 *
 * This will connect this program to the memory visibility
 * concepts we will study later.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is a basic Singleton implementation not thread-safe?
 *
 * 2. How can synchronized make Singleton creation safe?
 *
 * 3. What is double-checked locking?
 *
 * 4. Why is volatile required in the modern double-checked
 *    locking implementation?
 *
 * 5. What is eager initialization?
 *
 * 6. What is the initialization-on-demand holder idiom?
 *
 * 7. Is Singleton itself a concurrency concept?
 *
 * No.
 *
 * Singleton is a design pattern.
 *
 * The concurrency problem appears when multiple threads
 * access/create the Singleton concurrently.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * A shared object accessed by multiple threads needs a clearly
 * defined thread-safety strategy.
 *
 *
 * Singleton problem:
 *
 *     Multiple Threads
 *          ↓
 *     Shared Instance Creation
 *          ↓
 *     Race Condition
 *          ↓
 *     Synchronization
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 *
 * In modern Java, an enum Singleton is often the simplest
 * robust Singleton implementation when its semantics fit:
 *
 *     enum Singleton {
 *         INSTANCE
 *     }
 *
 * But understanding synchronized Singleton creation is valuable
 * because it teaches the underlying concurrency problem.
 *
 *
 * ============================================================
 */

public class ThreadSafeSingleton {

    /*
     * Private constructor prevents external code from creating
     * ConfigurationManager objects directly.
     */
    static class ConfigurationManager {

        /*
         * The single shared instance.
         *
         * It is static because the Singleton belongs to the
         * class rather than to any particular object.
         */
        private static ConfigurationManager instance;

        /*
         * Private constructor.
         */
        private ConfigurationManager() {

            System.out.println(
                    "ConfigurationManager instance created by: "
                            + Thread.currentThread().getName()
            );
        }

        /*
         * synchronized ensures that only one thread at a time
         * can execute the instance-creation logic.
         */
        public static synchronized ConfigurationManager
        getInstance() {

            /*
             * Create the object only if it does not already exist.
             */
            if (instance == null) {

                instance =
                        new ConfigurationManager();
            }

            return instance;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        int numberOfThreads = 20;

        Thread[] threads =
                new Thread[numberOfThreads];

        /*
         * Each thread attempts to obtain the Singleton instance.
         */
        for (int i = 0;
             i < numberOfThreads;
             i++) {

            final int threadNumber = i;

            threads[i] =
                    new Thread(() -> {

                        ConfigurationManager manager =
                                ConfigurationManager
                                        .getInstance();

                        System.out.println(
                                "Thread-"
                                        + threadNumber
                                        + " received instance: "
                                        + manager.hashCode()
                        );

                    });
        }

        /*
         * Start all threads.
         */
        for (Thread thread : threads) {

            thread.start();
        }

        /*
         * Wait for all threads.
         */
        for (Thread thread : threads) {

            thread.join();
        }

        /*
         * If the implementation is thread-safe, every thread
         * should print the SAME hashCode.
         *
         * That means every thread received the SAME object.
         */
        System.out.println(
                "All threads completed."
        );
    }
}
