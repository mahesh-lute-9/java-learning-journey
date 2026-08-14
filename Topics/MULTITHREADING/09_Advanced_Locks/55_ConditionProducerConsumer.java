/*
 * ============================================================
 * 55 - Condition WITH ReentrantLock
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a food-delivery kitchen.
 *
 *     Customer orders
 *          ↓
 *     Order Queue
 *          ↓
 *     Kitchen Worker
 *
 *
 * Sometimes:
 *
 *     Producer → queue is FULL
 *
 * So the producer must WAIT.
 *
 *
 * Sometimes:
 *
 *     Consumer → queue is EMPTY
 *
 * So the consumer must WAIT.
 *
 *
 * We want threads to sleep efficiently instead of repeatedly
 * checking the condition.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     ReentrantLock
 *          +
 *     Condition
 *
 *
 * A Condition allows threads to wait for a particular state
 * to become true.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 *
 * await()
 *
 *     Current thread waits until it is signalled.
 *
 *
 * signal()
 *
 *     Wake one waiting thread.
 *
 *
 * signalAll()
 *
 *     Wake all waiting threads.
 *
 *
 * ------------------------------------------------------------
 * BASIC PATTERN
 * ------------------------------------------------------------
 *
 *
 * lock.lock();
 *
 * try {
 *
 *     while (conditionIsFalse) {
 *
 *         condition.await();
 *     }
 *
 *     // perform work
 *
 * } finally {
 *
 *     lock.unlock();
 * }
 *
 *
 * ------------------------------------------------------------
 * WHY WHILE, NOT IF?
 * ------------------------------------------------------------
 *
 * Correct:
 *
 *
 *     while (queue.isEmpty()) {
 *
 *         notEmpty.await();
 *     }
 *
 *
 * NOT:
 *
 *
 *     if (queue.isEmpty()) {
 *
 *         notEmpty.await();
 *     }
 *
 *
 * A thread must re-check the condition after waking up.
 *
 *
 * This protects against:
 *
 *     Spurious wakeups
 *     Another thread consuming the resource first
 *     State changes between notification and reacquisition
 *
 *
 * ------------------------------------------------------------
 * TWO CONDITIONS
 * ------------------------------------------------------------
 *
 * We have a bounded queue.
 *
 *
 * Therefore we have two states:
 *
 *
 *     NOT_EMPTY
 *
 *     NOT_FULL
 *
 *
 * We can create:
 *
 *
 *     Condition notEmpty =
 *         lock.newCondition();
 *
 *
 *     Condition notFull =
 *         lock.newCondition();
 *
 *
 * ------------------------------------------------------------
 * PRODUCER
 * ------------------------------------------------------------
 *
 * If queue is full:
 *
 *
 *     notFull.await();
 *
 *
 * After adding an item:
 *
 *
 *     notEmpty.signal();
 *
 *
 * Meaning:
 *
 *     "There is now something available."
 *
 *
 * ------------------------------------------------------------
 * CONSUMER
 * ------------------------------------------------------------
 *
 * If queue is empty:
 *
 *
 *     notEmpty.await();
 *
 *
 * After removing an item:
 *
 *
 *     notFull.signal();
 *
 *
 * Meaning:
 *
 *     "There is now space available."
 *
 *
 * ------------------------------------------------------------
 * CONDITION VS wait()/notify()
 * ------------------------------------------------------------
 *
 * Traditional:
 *
 *     synchronized
 *     wait()
 *     notify()
 *
 *
 * ReentrantLock:
 *
 *     lock
 *     Condition
 *     await()
 *     signal()
 *
 *
 * Condition provides more flexible coordination because one
 * lock can have multiple condition queues.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT RULE
 * ------------------------------------------------------------
 *
 * A Condition is associated with a specific Lock.
 *
 *
 * The thread must hold the associated lock before calling:
 *
 *     await()
 *
 * or:
 *
 *     signal()
 *
 *
 * Otherwise:
 *
 *     IllegalMonitorStateException
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Producer-consumer systems
 *     Bounded buffers
 *     Resource pools
 *     Worker coordination
 *     State-dependent processing
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     BUFFER_CAPACITY = 3
 *
 * to:
 *
 *     BUFFER_CAPACITY = 1
 *
 *
 * Observe more waiting.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make the producer faster.
 *
 *
 * Observe:
 *
 *     notFull.await()
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make the consumer faster.
 *
 *
 * Observe:
 *
 *     notEmpty.await()
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     signal()
 *
 * with:
 *
 *     signalAll()
 *
 *
 * Observe the difference when multiple threads are waiting.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is Condition?
 *
 * An object associated with a Lock that allows threads to wait
 * and be signalled based on a particular condition.
 *
 *
 * 2. What does await() do?
 *
 * Atomically releases the associated lock and waits until
 * signalled/interrupted, then reacquires the lock before
 * continuing.
 *
 *
 * 3. Why use while instead of if around await()?
 *
 * Because the condition must be rechecked after waking.
 *
 *
 * 4. Difference between signal() and signalAll()?
 *
 * signal() wakes one waiting thread.
 *
 * signalAll() wakes all waiting threads.
 *
 *
 * 5. Can await() be called without holding the lock?
 *
 * No.
 *
 *
 * 6. Why might multiple Conditions be useful?
 *
 * They allow different groups of waiting threads to wait for
 * different state conditions associated with the same lock.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * ReentrantLock
 *      +
 * Condition
 *      ↓
 * Precise thread coordination
 *
 *
 * Producer:
 *
 *     queue full?
 *         ↓
 *     await()
 *
 *
 * Consumer:
 *
 *     queue empty?
 *         ↓
 *     await()
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 56:
 *
 *     CountDownLatchScenario.java
 *
 * Scenario:
 *
 *     An application should start only after several independent
 *     services have completed initialization.
 *
 * We'll learn:
 *
 *     CountDownLatch
 *
 * ============================================================
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionProducerConsumer {

    /*
     * Maximum number of items that can be stored.
     */
    private static final int BUFFER_CAPACITY = 3;

    /*
     * Number of items the producer creates.
     */
    private static final int TOTAL_ITEMS = 10;

    /*
     * Shared bounded buffer.
     */
    private static final Queue<Integer> buffer =
            new ArrayDeque<>();

    /*
     * Explicit lock protecting the buffer.
     */
    private static final ReentrantLock lock =
            new ReentrantLock();

    /*
     * Condition for consumers waiting for data.
     */
    private static final Condition notEmpty =
            lock.newCondition();

    /*
     * Condition for producers waiting for space.
     */
    private static final Condition notFull =
            lock.newCondition();

    /*
     * --------------------------------------------------------
     * PRODUCER
     * --------------------------------------------------------
     */
    static class Producer
            implements Runnable {

        @Override
        public void run() {

            try {

                for (int i = 1;
                     i <= TOTAL_ITEMS;
                     i++) {

                    lock.lock();

                    try {

                        /*
                         * If buffer is full, producer waits.
                         *
                         * IMPORTANT:
                         *
                         * Use while, not if.
                         */
                        while (
                                buffer.size()
                                        == BUFFER_CAPACITY
                        ) {

                            System.out.println(
                                    "Producer waiting: "
                                            + "buffer is full."
                            );

                            notFull.await();
                        }

                        /*
                         * Add new item.
                         */
                        buffer.add(i);

                        System.out.println(
                                "Producer added: "
                                        + i
                                        + " | Buffer size: "
                                        + buffer.size()
                        );

                        /*
                         * There is now at least one item.
                         *
                         * Wake one waiting consumer.
                         */
                        notEmpty.signal();

                    } finally {

                        /*
                         * Always release lock.
                         */
                        lock.unlock();
                    }

                    /*
                     * Simulate production time.
                     */
                    Thread.sleep(300);
                }

            } catch (InterruptedException e) {

                Thread.currentThread()
                        .interrupt();

                System.out.println(
                        "Producer interrupted."
                );
            }
        }
    }

    /*
     * --------------------------------------------------------
     * CONSUMER
     * --------------------------------------------------------
     */
    static class Consumer
            implements Runnable {

        @Override
        public void run() {

            try {

                for (int i = 1;
                     i <= TOTAL_ITEMS;
                     i++) {

                    lock.lock();

                    try {

                        /*
                         * If buffer is empty, consumer waits.
                         */
                        while (buffer.isEmpty()) {

                            System.out.println(
                                    "Consumer waiting: "
                                            + "buffer is empty."
                            );

                            notEmpty.await();
                        }

                        /*
                         * Remove an item.
                         */
                        int item =
                                buffer.remove();

                        System.out.println(
                                "Consumer processed: "
                                        + item
                                        + " | Buffer size: "
                                        + buffer.size()
                        );

                        /*
                         * There is now at least one free slot.
                         *
                         * Wake one waiting producer.
                         */
                        notFull.signal();

                    } finally {

                        /*
                         * Always release lock.
                         */
                        lock.unlock();
                    }

                    /*
                     * Simulate processing time.
                     */
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {

                Thread.currentThread()
                        .interrupt();

                System.out.println(
                        "Consumer interrupted."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create producer.
         */
        Thread producer =
                new Thread(
                        new Producer(),
                        "Producer"
                );

        /*
         * Create consumer.
         */
        Thread consumer =
                new Thread(
                        new Consumer(),
                        "Consumer"
                );

        /*
         * Start both.
         */
        producer.start();
        consumer.start();

        /*
         * Wait for both threads.
         */
        producer.join();
        consumer.join();

        System.out.println(
                "\nProducer-consumer processing completed."
        );
    }
}
