/*
 * ============================================================
 * 11 - STATIC RESOURCE AND CLASS-LEVEL SYNCHRONIZATION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an application where every object shares the same
 * application-wide resource.
 *
 * Example:
 *
 *     Global sequence number
 *     Application-wide configuration
 *     Shared ID generator
 *     Global statistics
 *
 * Suppose multiple objects of a class are created:
 *
 *     Service-1
 *     Service-2
 *
 * but both objects modify the SAME static resource.
 *
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * We learned that:
 *
 *     synchronized instance method
 *
 * locks the current OBJECT.
 *
 * But what if the shared resource is STATIC?
 *
 * Multiple objects have different object monitors:
 *
 *     Object A → its own lock
 *     Object B → its own lock
 *
 * Therefore, synchronizing an instance method does NOT
 * automatically synchronize access across different objects.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT CONCEPT
 * ------------------------------------------------------------
 *
 * Instance synchronized method:
 *
 *     public synchronized void method()
 *
 * locks:
 *
 *     this
 *
 *
 * Static synchronized method:
 *
 *     public static synchronized void method()
 *
 * locks:
 *
 *     Class object
 *
 *     StaticResource.class
 *
 *
 * ------------------------------------------------------------
 * VISUALIZATION
 * ------------------------------------------------------------
 *
 * INSTANCE SYNCHRONIZATION:
 *
 *     Object A                 Object B
 *        │                       │
 *      Lock A                  Lock B
 *        │                       │
 *        └──── independent ─────┘
 *
 *
 * STATIC SYNCHRONIZATION:
 *
 *             Class Object
 *                  │
 *             One shared lock
 *                  │
 *          +-------+-------+
 *          |               |
 *       Object A         Object B
 *
 *
 * ------------------------------------------------------------
 * SCENARIO
 * ------------------------------------------------------------
 *
 * We have a static request counter.
 *
 * Multiple Service objects represent different parts of the
 * application.
 *
 * All of them update:
 *
 *     totalRequests
 *
 * Since totalRequests is static, there is only ONE copy shared
 * by all Service objects.
 *
 *
 * ------------------------------------------------------------
 * GOAL
 * ------------------------------------------------------------
 *
 * Protect the shared static counter so that concurrent updates
 * do not lose data.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     public static synchronized void increment()
 *
 * to:
 *
 *     public static void increment()
 *
 * Run the program with many threads.
 *
 * Observe whether the final result is always correct.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Create two Service objects:
 *
 *     serviceA
 *     serviceB
 *
 * Make them both update the static counter.
 *
 * Notice that static synchronization coordinates them even
 * though they are different objects.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Create an instance synchronized method and a static
 * synchronized method.
 *
 * Ask:
 *
 *     Do they block each other?
 *
 * Normally, no.
 *
 * Why?
 *
 * Because they use different monitors:
 *
 *     instance method → this
 *
 *     static method   → Class object
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What does a synchronized instance method lock?
 *
 * Answer:
 *
 *     The current object (this).
 *
 *
 * 2. What does a synchronized static method lock?
 *
 * Answer:
 *
 *     The Class object.
 *
 *
 * 3. Why is static synchronization useful?
 *
 * Because static state is shared across all instances.
 *
 *
 * 4. Do two different objects share the same instance monitor?
 *
 * No.
 *
 *
 * 5. Do two calls to a static synchronized method use the same
 *    class-level monitor?
 *
 * Yes, for the same class.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Remember:
 *
 *     synchronized instance method
 *              ↓
 *           this lock
 *
 *
 *     synchronized static method
 *              ↓
 *         Class-level lock
 *
 *
 * This distinction becomes very important when designing
 * thread-safe classes.
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 12:
 *
 *     DeadlockBankTransfer.java
 *
 * We will intentionally create a DEADLOCK.
 *
 * Two bank transfers will wait for each other's locks and the
 * application will get stuck.
 *
 * ============================================================
 */

public class StaticResource {

    static class RequestTracker {

        /*
         * STATIC shared state.
         *
         * There is only ONE copy of this variable regardless
         * of how many RequestTracker objects are created.
         */
        private static int totalRequests = 0;

        /*
         * Static synchronized method.
         *
         * The lock is associated with:
         *
         *     RequestTracker.class
         */
        public static synchronized void increment() {

            totalRequests++;
        }

        public static synchronized int getTotalRequests() {

            return totalRequests;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Two completely different objects.
         */
        RequestTracker trackerA =
                new RequestTracker();

        RequestTracker trackerB =
                new RequestTracker();

        int threadsPerTracker = 5;

        int incrementsPerThread = 10_000;

        Thread[] workers =
                new Thread[threadsPerTracker * 2];

        int index = 0;

        /*
         * Threads using trackerA.
         */
        for (int i = 0;
             i < threadsPerTracker;
             i++) {

            workers[index++] =
                    new Thread(() -> {

                        for (int j = 0;
                             j < incrementsPerThread;
                             j++) {

                            RequestTracker.increment();
                        }
                    });
        }

        /*
         * Threads using trackerB.
         */
        for (int i = 0;
             i < threadsPerTracker;
             i++) {

            workers[index++] =
                    new Thread(() -> {

                        for (int j = 0;
                             j < incrementsPerThread;
                             j++) {

                            RequestTracker.increment();
                        }
                    });
        }

        /*
         * Start all workers.
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

        /*
         * Expected:
         *
         * 10 threads × 10,000
         *
         * = 100,000
         */
        System.out.println(
                "Expected requests: "
                        + (threadsPerTracker * 2
                        * incrementsPerThread)
        );

        System.out.println(
                "Actual requests:   "
                        + RequestTracker.getTotalRequests()
        );

        /*
         * Notice:
         *
         * trackerA and trackerB are different objects.
         *
         * Yet both threads are coordinated because the method
         * is static synchronized.
         *
         * They synchronize on the SAME class-level monitor:
         *
         *     RequestTracker.class
         */
    }
}
