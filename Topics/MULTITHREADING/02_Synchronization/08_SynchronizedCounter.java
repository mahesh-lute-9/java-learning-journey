/*
 * ============================================================
 * 08 - THREAD-SAFE COUNTER USING synchronized
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a web server receiving requests.
 *
 * Every time a request arrives, we increment a counter:
 *
 *     requestCount++
 *
 * Suppose 10 threads are processing requests concurrently.
 *
 * We expect:
 *
 *     10 threads → 10 increments → counter = 10
 *
 * But if multiple threads modify the counter at the same time,
 * the result may be incorrect.
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * The operation:
 *
 *     counter++;
 *
 * looks like one operation.
 *
 * But internally, it is conceptually:
 *
 *     1. Read counter
 *     2. Add 1
 *     3. Write counter
 *
 *
 * Example:
 *
 * Initial counter = 5
 *
 *
 * Thread A                    Thread B
 * --------                    --------
 *
 * Read 5
 *                             Read 5
 *
 * Add 1
 *                             Add 1
 *
 * Write 6
 *                             Write 6
 *
 *
 * Expected:
 *
 *     7
 *
 * Actual:
 *
 *     6
 *
 *
 * This is a RACE CONDITION.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * We need to make the increment operation atomic from the
 * perspective of other threads.
 *
 * We can use:
 *
 *     synchronized
 *
 *
 * ------------------------------------------------------------
 * WHAT DOES synchronized DO?
 * ------------------------------------------------------------
 *
 * synchronized provides mutual exclusion.
 *
 * If one thread enters a synchronized method/block protected
 * by the same monitor:
 *
 *     Other competing threads must wait.
 *
 *
 * Visualization:
 *
 *                Counter
 *                   |
 *            synchronized
 *                   |
 *             +-----+-----+
 *             |           |
 *             v           v
 *          Thread A    Thread B
 *             |
 *          gets lock
 *             |
 *          increments
 *             |
 *          releases lock
 *                         |
 *                         v
 *                      gets lock
 *
 *
 * ------------------------------------------------------------
 * CRITICAL SECTION
 * ------------------------------------------------------------
 *
 * The code that accesses/modifies shared state and must not be
 * executed by multiple competing threads simultaneously is
 * called the CRITICAL SECTION.
 *
 * Here:
 *
 *     counter++;
 *
 * is the critical section.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * synchronized does TWO important things:
 *
 *     1. Mutual exclusion
 *     2. Memory visibility
 *
 * Mutual exclusion:
 *
 *     Only one thread at a time can execute the protected
 *     critical section for the same monitor.
 *
 * Visibility:
 *
 *     Changes made by one thread before releasing the monitor
 *     become visible to a thread that subsequently acquires
 *     the same monitor.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove synchronized:
 *
 *     public void increment()
 *
 * Run the program with 10 threads and many increments.
 *
 * Example:
 *
 *     10 threads
 *     100000 increments each
 *
 * Expected:
 *
 *     1,000,000
 *
 * You may get a smaller value.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Keep synchronized and run the program multiple times.
 *
 * The final count should consistently be:
 *
 *     1,000,000
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     synchronized method
 *
 * into:
 *
 *     synchronized block
 *
 * Example:
 *
 *     synchronized (this) {
 *         counter++;
 *     }
 *
 * Compare the two approaches.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add another method:
 *
 *     public void printCounter()
 *
 * and experiment with synchronized and non-synchronized
 * versions.
 *
 * Ask yourself:
 *
 *     Which methods need protection?
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is counter++ not atomic?
 *
 * 2. What is a critical section?
 *
 * 3. What does synchronized provide?
 *
 * 4. What object is used as the monitor for a synchronized
 *    instance method?
 *
 * Answer:
 *
 *     The current object, i.e. this.
 *
 * 5. Can two threads execute two synchronized instance methods
 *    on the same object at the same time?
 *
 * Generally, no, if both methods synchronize on the same
 * object's monitor.
 *
 * 6. Does synchronized only provide mutual exclusion?
 *
 * No.
 *
 * It also provides memory visibility guarantees.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Race condition:
 *
 *     Multiple threads
 *          +
 *     Shared mutable state
 *          +
 *     No proper synchronization
 *          ↓
 *     Incorrect result
 *
 *
 * synchronized:
 *
 *     Shared state
 *          ↓
 *     Protected critical section
 *          ↓
 *     One thread at a time
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 09:
 *
 *     TicketBooking.java
 *
 * We will use a synchronized BLOCK instead of a synchronized
 * method and solve a real-world double-booking problem.
 *
 * ============================================================
 */

public class SynchronizedCounter {

    /*
     * This object owns the shared counter.
     */
    static class Counter {

        private int count = 0;

        /*
         * synchronized makes this method mutually exclusive
         * for this Counter object's monitor.
         */
        public synchronized void increment() {

            count++;
        }

        public int getCount() {

            return count;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * One shared Counter object.
         *
         * All worker threads will modify this SAME object.
         */
        Counter counter =
                new Counter();

        int numberOfThreads = 10;

        int incrementsPerThread = 100_000;

        Thread[] workers =
                new Thread[numberOfThreads];

        /*
         * Create worker threads.
         */
        for (int i = 0;
             i < numberOfThreads;
             i++) {

            workers[i] =
                    new Thread(() -> {

                        for (int j = 0;
                             j < incrementsPerThread;
                             j++) {

                            counter.increment();
                        }
                    });
        }

        /*
         * Start all worker threads.
         */
        for (Thread worker : workers) {

            worker.start();
        }

        /*
         * Wait for every worker to finish.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        /*
         * Expected result:
         *
         * 10 × 100,000
         *
         * = 1,000,000
         */
        int expected =
                numberOfThreads
                        * incrementsPerThread;

        System.out.println(
                "Expected count: "
                        + expected
        );

        System.out.println(
                "Actual count:   "
                        + counter.getCount()
        );
    }
}
