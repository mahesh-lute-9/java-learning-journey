/*
 * ============================================================
 * 16 - BOUNDED BUFFER WITH MULTIPLE PRODUCERS & CONSUMERS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a food-delivery platform.
 *
 * Thousands of restaurants generate orders and delivery workers
 * process those orders.
 *
 * We introduce an important real-world constraint:
 *
 *     The order queue has LIMITED capacity.
 *
 * Example:
 *
 *     Queue capacity = 5
 *
 * If the queue already contains 5 orders:
 *
 *     Producers MUST WAIT.
 *
 * If the queue is empty:
 *
 *     Consumers MUST WAIT.
 *
 *
 * ------------------------------------------------------------
 * SYSTEM
 * ------------------------------------------------------------
 *
 *             PRODUCERS
 *
 *      Restaurant A
 *      Restaurant B
 *      Restaurant C
 *           │
 *           ▼
 *     +-------------+
 *     | Bounded     |
 *     | Order Queue |
 *     | Capacity=5  |
 *     +-------------+
 *           │
 *           ▼
 *       CONSUMERS
 *
 *      Delivery Worker A
 *      Delivery Worker B
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS DIFFERENT FROM PROGRAM 15?
 * ------------------------------------------------------------
 *
 * Program 15 demonstrated the basic Producer-Consumer pattern.
 *
 * Now we introduce:
 *
 *     Multiple producers
 *     Multiple consumers
 *     Bounded capacity
 *     More contention
 *
 *
 * This forces us to think carefully about:
 *
 *     wait()
 *     notifyAll()
 *     synchronized
 *     while
 *     shared state
 *
 *
 * ------------------------------------------------------------
 * CONDITIONS
 * ------------------------------------------------------------
 *
 * Producer can proceed when:
 *
 *     queue.size() < capacity
 *
 *
 * Consumer can proceed when:
 *
 *     queue is NOT empty
 *
 *
 * Therefore:
 *
 *
 * PRODUCER:
 *
 *     while (queue is full)
 *         wait();
 *
 *
 * CONSUMER:
 *
 *     while (queue is empty)
 *         wait();
 *
 *
 * ------------------------------------------------------------
 * WHY notifyAll()?
 * ------------------------------------------------------------
 *
 * Imagine:
 *
 *     3 producers are waiting because the queue is full.
 *
 * A consumer removes one item.
 *
 * Now space is available.
 *
 * We need waiting threads to wake up and re-check their
 * conditions.
 *
 *
 * Similarly:
 *
 *     3 consumers are waiting because the queue is empty.
 *
 * A producer adds an item.
 *
 * Waiting consumers need a chance to re-check the condition.
 *
 *
 * notifyAll() wakes all threads waiting on the monitor.
 *
 * IMPORTANT:
 *
 * Waking up does NOT mean the thread automatically gets the
 * lock or that its condition is guaranteed to be true.
 *
 * That's why we use:
 *
 *     while
 *
 * instead of:
 *
 *     if
 *
 *
 * ------------------------------------------------------------
 * WHY NOTIFY() CAN BE TRICKY
 * ------------------------------------------------------------
 *
 * notify() wakes only ONE waiting thread.
 *
 * With multiple producers and consumers, the awakened thread
 * might not be the type of thread that can currently make
 * useful progress.
 *
 * notifyAll() is often easier to reason about for condition
 * based coordination, although it may wake more threads than
 * necessary.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DESIGN LESSON
 * ------------------------------------------------------------
 *
 * The shared buffer controls its own synchronization.
 *
 * External threads do NOT directly manipulate the queue.
 *
 * They call:
 *
 *     put()
 *     take()
 *
 * This keeps the concurrency logic encapsulated.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     CAPACITY = 5
 *
 * to:
 *
 *     CAPACITY = 1
 *
 * Observe how frequently producers and consumers wait.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Create:
 *
 *     5 producers
 *     2 consumers
 *
 * Ask:
 *
 *     Which side is likely to wait more frequently?
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Create:
 *
 *     2 producers
 *     5 consumers
 *
 * What happens?
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     notifyAll()
 *
 * with:
 *
 *     notify()
 *
 * and observe the behavior.
 *
 * Think carefully about why notifyAll() is easier to reason
 * about in this multi-producer/multi-consumer example.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 5
 * ------------------------------------------------------------
 *
 * Temporarily change while to if.
 *
 * Run with multiple producers and consumers.
 *
 * Think about why the condition must be checked AGAIN after
 * waking up.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a bounded buffer?
 *
 * 2. Why should a producer wait when the buffer is full?
 *
 * 3. Why should a consumer wait when the buffer is empty?
 *
 * 4. Why is wait() used inside synchronized?
 *
 * 5. Why should condition checks use while?
 *
 * 6. Difference between notify() and notifyAll()?
 *
 * 7. Does notifyAll() release the lock immediately?
 *
 * No.
 *
 * The current thread continues holding the monitor until it
 * exits the synchronized section.
 *
 * 8. What happens when a waiting thread wakes up?
 *
 * It must reacquire the monitor before continuing.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * The classic pattern is:
 *
 *
 *     while (condition is NOT satisfied) {
 *         wait();
 *     }
 *
 *     perform operation
 *
 *     notifyAll();
 *
 *
 * This pattern appears in many systems:
 *
 *     Task queues
 *     Message brokers
 *     Worker pools
 *     Job schedulers
 *     Producer-consumer pipelines
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 17:
 *
 *     PrintOddEven.java
 *
 * We will use thread coordination to force two threads to
 * produce an exact sequence:
 *
 *     1 2 3 4 5 6 ...
 *
 * One thread prints odd numbers.
 * Another prints even numbers.
 *
 * ============================================================
 */

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBuffer {

    /*
     * Shared bounded buffer.
     */
    static class Buffer {

        private final Queue<Integer> queue =
                new LinkedList<>();

        private final int capacity;

        Buffer(int capacity) {
            this.capacity = capacity;
        }

        /*
         * ----------------------------------------------------
         * PRODUCER OPERATION
         * ----------------------------------------------------
         */
        public synchronized void put(
                int value)
                throws InterruptedException {

            /*
             * Wait while the buffer is FULL.
             */
            while (queue.size() == capacity) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> Buffer FULL. Waiting..."
                );

                wait();
            }

            /*
             * Add the value.
             */
            queue.offer(value);

            System.out.println(
                    Thread.currentThread().getName()
                            + " produced: "
                            + value
                            + " | Buffer size: "
                            + queue.size()
            );

            /*
             * Wake waiting producers/consumers.
             */
            notifyAll();
        }

        /*
         * ----------------------------------------------------
         * CONSUMER OPERATION
         * ----------------------------------------------------
         */
        public synchronized int take()
                throws InterruptedException {

            /*
             * Wait while the buffer is EMPTY.
             */
            while (queue.isEmpty()) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> Buffer EMPTY. Waiting..."
                );

                wait();
            }

            /*
             * Remove the oldest value.
             */
            int value =
                    queue.poll();

            System.out.println(
                    Thread.currentThread().getName()
                            + " consumed: "
                            + value
                            + " | Buffer size: "
                            + queue.size()
            );

            /*
             * Wake waiting producers/consumers.
             */
            notifyAll();

            return value;
        }
    }

    /*
     * Producer task.
     */
    static class Producer implements Runnable {

        private final Buffer buffer;
        private final int startValue;
        private final int count;

        Producer(
                Buffer buffer,
                int startValue,
                int count) {

            this.buffer = buffer;
            this.startValue = startValue;
            this.count = count;
        }

        @Override
        public void run() {

            try {

                for (int i = 0;
                     i < count;
                     i++) {

                    int value =
                            startValue + i;

                    buffer.put(value);

                    /*
                     * Simulate production time.
                     */
                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }
    }

    /*
     * Consumer task.
     */
    static class Consumer implements Runnable {

        private final Buffer buffer;
        private final int count;

        Consumer(
                Buffer buffer,
                int count) {

            this.buffer = buffer;
            this.count = count;
        }

        @Override
        public void run() {

            try {

                for (int i = 0;
                     i < count;
                     i++) {

                    int value =
                            buffer.take();

                    /*
                     * Simulate consumption time.
                     */
                    Thread.sleep(400);

                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create a small buffer so that waiting becomes easy
         * to observe.
         */
        Buffer buffer =
                new Buffer(3);

        /*
         * Two producers.
         *
         * Each produces 5 values.
         */
        Thread producer1 =
                new Thread(
                        new Producer(
                                buffer,
                                1,
                                5
                        ),
                        "Producer-1"
                );

        Thread producer2 =
                new Thread(
                        new Producer(
                                buffer,
                                101,
                                5
                        ),
                        "Producer-2"
                );

        /*
         * Two consumers.
         *
         * Each consumes 5 values.
         *
         * Total produced:
         *
         *     10
         *
         * Total consumed:
         *
         *     10
         */
        Thread consumer1 =
                new Thread(
                        new Consumer(
                                buffer,
                                5
                        ),
                        "Consumer-1"
                );

        Thread consumer2 =
                new Thread(
                        new Consumer(
                                buffer,
                                5
                        ),
                        "Consumer-2"
                );

        /*
         * Start consumers first.
         *
         * They may initially wait because the buffer is empty.
         */
        consumer1.start();
        consumer2.start();

        Thread.sleep(300);

        /*
         * Start producers.
         */
        producer1.start();
        producer2.start();

        /*
         * Wait for producers.
         */
        producer1.join();
        producer2.join();

        /*
         * Wait for consumers.
         */
        consumer1.join();
        consumer2.join();

        System.out.println(
                "Producer-Consumer system finished."
        );
    }
}
