/*
 * ============================================================
 * 30 - THREAD-SAFE SINGLETON
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application that needs exactly ONE shared
 * configuration manager.
 *
 * Example:
 *
 *     ConfigurationManager
 *     Logger
 *     MetricsRegistry
 *     CacheManager
 *     ConnectionManager
 *
 *
 * Requirement:
 *
 *     No matter how many threads request the object,
 *     only ONE instance should ever exist.
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS A CONCURRENCY PROBLEM?
 * ------------------------------------------------------------
 *
 * A naive Singleton might look like:
 *
 *
 *     if (instance == null) {
 *         instance = new Singleton();
 *     }
 *
 *
 * With multiple threads:
 *
 *
 * Thread A                 Thread B
 * --------                 --------
 *
 * instance == null
 *                         instance == null
 *
 * create object
 *                         create object
 *
 *
 * Now TWO objects exist.
 *
 *
 * ------------------------------------------------------------
 * REQUIREMENT
 * ------------------------------------------------------------
 *
 * We need:
 *
 *     1. Lazy initialization
 *     2. Thread safety
 *     3. Good performance
 *
 *
 * ------------------------------------------------------------
 * APPROACH
 * ------------------------------------------------------------
 *
 * We will use:
 *
 *     volatile
 *
 * plus:
 *
 *     synchronized
 *
 *
 * This pattern is called:
 *
 *     Double-Checked Locking
 *
 *
 * ------------------------------------------------------------
 * WHY volatile?
 * ------------------------------------------------------------
 *
 * The Singleton reference is:
 *
 *     volatile
 *
 *
 * This ensures proper visibility and publication of the
 * initialized object across threads.
 *
 *
 * Without volatile, double-checked locking can be incorrect
 * under the Java Memory Model.
 *
 *
 * ------------------------------------------------------------
 * DOUBLE-CHECKED LOCKING
 * ------------------------------------------------------------
 *
 * First check:
 *
 *     if (instance == null)
 *
 *
 * Only if it appears uninitialized do we enter synchronized.
 *
 *
 * Inside synchronized:
 *
 *     if (instance == null)
 *
 *
 * We check AGAIN.
 *
 *
 * Why?
 *
 * Because multiple threads may have passed the first check
 * before one of them acquired the lock.
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * Thread A                 Thread B
 * --------                 --------
 *
 * instance == null
 *                         instance == null
 *
 * acquire lock
 *
 *                         waits
 *
 * create instance
 *
 * release lock
 *
 *                         acquire lock
 *
 *                         check again
 *
 *                         instance != null
 *
 *                         don't create
 *
 *
 * Therefore only one instance is created.
 *
 *
 * ------------------------------------------------------------
 * WHY NOT SYNCHRONIZE EVERYTHING?
 * ------------------------------------------------------------
 *
 * We could write:
 *
 *
 *     synchronized getInstance()
 *
 *
 * This is simpler and correct.
 *
 *
 * But every call would acquire the monitor.
 *
 *
 * Double-checked locking avoids synchronization after the
 * instance has already been created.
 *
 *
 * IMPORTANT:
 *
 * Don't use double-checked locking just because it looks
 * "advanced".
 *
 * Prefer the simplest correct design.
 *
 *
 * ------------------------------------------------------------
 * EVEN BETTER ALTERNATIVE
 * ------------------------------------------------------------
 *
 * Java also provides an elegant Singleton approach using:
 *
 *     Initialization-on-demand holder idiom
 *
 *
 * Example:
 *
 *
 *     private static class Holder {
 *         static final Singleton INSTANCE =
 *                 new Singleton();
 *     }
 *
 *     public static Singleton getInstance() {
 *         return Holder.INSTANCE;
 *     }
 *
 *
 * The JVM class initialization mechanism provides thread-safe
 * initialization.
 *
 *
 * ------------------------------------------------------------
 * ENUM SINGLETON
 * ------------------------------------------------------------
 *
 * Another famous approach:
 *
 *
 *     enum Singleton {
 *         INSTANCE
 *     }
 *
 *
 * This is particularly useful when you need a robust Singleton
 * with serialization/enum guarantees.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove volatile.
 *
 * Understand why this can break the correctness guarantees of
 * double-checked locking.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Replace getInstance() with:
 *
 *     synchronized
 *
 * on the entire method.
 *
 * Compare simplicity and performance characteristics.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Implement the Holder idiom.
 *
 * Compare it with double-checked locking.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Create 100 threads.
 *
 * Each thread calls:
 *
 *     getInstance()
 *
 *
 * Verify:
 *
 *     Every thread receives the SAME object.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is Singleton thread safety important?
 *
 * 2. Why is volatile required in double-checked locking?
 *
 * 3. Why do we check instance == null twice?
 *
 * 4. Why not simply synchronize getInstance()?
 *
 * 5. What is the initialization-on-demand holder idiom?
 *
 * 6. What is an enum Singleton?
 *
 * 7. Can Singleton still be broken using reflection,
 *    serialization, or cloning?
 *
 * Depending on the implementation, yes.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Double-checked locking:
 *
 *
 *     if (instance == null) {
 *
 *         synchronized (...) {
 *
 *             if (instance == null) {
 *
 *                 instance = new Singleton();
 *
 *             }
 *         }
 *     }
 *
 *
 * The important concepts are:
 *
 *     volatile
 *     synchronized
 *     safe publication
 *     lazy initialization
 *     race conditions
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 31:
 *
 *     ThreadPoolScenario.java
 *
 * We will stop manually creating one thread per task and
 * introduce one of the MOST IMPORTANT concurrency concepts in
 * Java backend development:
 *
 *     ExecutorService
 *
 * ============================================================
 */

public class ThreadSafeSingleton {

    /*
     * volatile is important for correct publication when using
     * double-checked locking.
     */
    private static volatile ThreadSafeSingleton instance;

    /*
     * Private constructor prevents external code from creating
     * objects directly.
     */
    private ThreadSafeSingleton() {

        System.out.println(
                "Singleton instance created by: "
                        + Thread.currentThread().getName()
        );
    }

    /*
     * Thread-safe lazy initialization.
     */
    public static ThreadSafeSingleton getInstance() {

        /*
         * ----------------------------------------------------
         * FIRST CHECK
         * ----------------------------------------------------
         *
         * Most calls will return here after the instance has
         * already been initialized.
         *
         * No synchronization is required on this fast path.
         */
        if (instance == null) {

            /*
             * Only threads that observe null enter this block.
             */
            synchronized (ThreadSafeSingleton.class) {

                /*
                 * ------------------------------------------------
                 * SECOND CHECK
                 * ------------------------------------------------
                 *
                 * Another thread may have created the instance
                 * while this thread was waiting for the lock.
                 *
                 * Therefore we MUST check again.
                 */
                if (instance == null) {

                    /*
                     * Create exactly one instance.
                     */
                    instance =
                            new ThreadSafeSingleton();
                }
            }
        }

        /*
         * Return the shared instance.
         */
        return instance;
    }

    static class SingletonWorker
            implements Runnable {

        @Override
        public void run() {

            /*
             * Every worker requests the Singleton.
             */
            ThreadSafeSingleton singleton =
                    ThreadSafeSingleton.getInstance();

            /*
             * Print the object's identity.
             *
             * All threads should receive the same identity.
             */
            System.out.println(
                    Thread.currentThread().getName()
                            + " received instance: "
                            + System.identityHashCode(
                            singleton
                    )
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Number of concurrent callers.
         */
        int numberOfThreads = 20;

        Thread[] workers =
                new Thread[numberOfThreads];

        /*
         * Create many threads.
         */
        for (int i = 0;
             i < numberOfThreads;
             i++) {

            workers[i] =
                    new Thread(
                            new SingletonWorker(),
                            "Worker-" + (i + 1)
                    );
        }

        /*
         * Start all threads.
         */
        for (Thread worker : workers) {

            worker.start();
        }

        /*
         * Wait for all threads.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        /*
         * Get another reference from main.
         */
        ThreadSafeSingleton instance1 =
                ThreadSafeSingleton.getInstance();

        ThreadSafeSingleton instance2 =
                ThreadSafeSingleton.getInstance();

        /*
         * Verify both references point to the same object.
         */
        System.out.println(
                "\nSame instance: "
                        + (instance1 == instance2)
        );
    }
}
