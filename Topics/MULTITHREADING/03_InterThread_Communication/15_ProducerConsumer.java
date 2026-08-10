/*
 * ============================================================
 * 15 - PRODUCER-CONSUMER USING wait() AND notifyAll()
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a food delivery application.
 *
 * There are:
 *
 *     PRODUCERS = Restaurant/Order Producers
 *     CONSUMERS = Delivery Workers
 *
 * Producers create orders and place them into a shared order
 * queue.
 *
 * Consumers take orders from the queue and process them.
 *
 *
 * Example:
 *
 *     Restaurant
 *          |
 *          | produces order
 *          v
 *     +----------------+
 *     |  Order Queue   |
 *     +----------------+
 *          |
 *          | consumes order
 *          v
 *     Delivery Worker
 *
 *
 * ------------------------------------------------------------
 * THE PROBLEM
 * ------------------------------------------------------------
 *
 * The producer and consumer run independently.
 *
 * What happens if:
 *
 *     Queue is EMPTY
 *
 * and a consumer tries to take an order?
 *
 * The consumer should WAIT.
 *
 *
 * What happens if:
 *
 *     Queue is FULL
 *
 * and a producer tries to add another order?
 *
 * The producer should WAIT.
 *
 *
 * Therefore:
 *
 *     EMPTY → Consumer waits
 *
 *     FULL  → Producer waits
 *
 *
 * ------------------------------------------------------------
 * WHY NOT JUST USE sleep()?
 * ------------------------------------------------------------
 *
 * sleep() means:
 *
 *     "Wait for this amount of time."
 *
 * But our requirement is different.
 *
 * We need:
 *
 *     "Wait until the queue has an order."
 *
 * or:
 *
 *     "Wait until space becomes available."
 *
 *
 * This is CONDITION-BASED waiting.
 *
 *
 * Java provides:
 *
 *     wait()
 *     notify()
 *     notifyAll()
 *
 * for this kind of coordination when using an object's
 * monitor.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT RULE
 * ------------------------------------------------------------
 *
 * wait(), notify(), and notifyAll() must be called while the
 * current thread owns the object's monitor.
 *
 * Therefore they are normally used inside:
 *
 *     synchronized
 *
 *
 * ------------------------------------------------------------
 * WHY wait()?
 * ------------------------------------------------------------
 *
 * When a consumer finds the queue empty:
 *
 *     queue.wait()
 *
 * does TWO important things:
 *
 *     1. The current thread enters WAITING state.
 *     2. It releases the monitor associated with queue.
 *
 *
 * This is extremely important.
 *
 * If wait() did NOT release the lock, the producer would never
 * be able to acquire the lock and add an order.
 *
 *
 * ------------------------------------------------------------
 * WHY notifyAll()?
 * ------------------------------------------------------------
 *
 * After a producer adds an order, waiting consumers may now
 * be able to continue.
 *
 * After a consumer removes an order, waiting producers may now
 * have space available.
 *
 * notifyAll() wakes up threads waiting on the same monitor.
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 *
 * notifyAll() does NOT directly give the lock to the waiting
 * thread.
 *
 * The awakened thread must still compete to reacquire the
 * monitor after the current synchronized section is exited.
 *
 *
 * ------------------------------------------------------------
 * WHY while() INSTEAD OF if()?
 * ------------------------------------------------------------
 *
 * This is VERY important.
 *
 * We use:
 *
 *     while (queue.isEmpty()) {
 *         queue.wait();
 *     }
 *
 * NOT:
 *
 *     if (queue.isEmpty()) {
 *         queue.wait();
 *     }
 *
 *
 * Why?
 *
 * Because after waking up, the condition must be checked again.
 *
 * A thread waking up does NOT guarantee that the condition it
 * needs is still true.
 *
 * This can happen because:
 *
 *     Another thread may consume the item first.
 *
 *     Or the thread may wake up spuriously.
 *
 *
 * General rule:
 *
 *     WAIT FOR A CONDITION USING while.
 *
 *
 * ------------------------------------------------------------
 * CONCEPTS
 * ------------------------------------------------------------
 *
 * - Producer-Consumer pattern
 * - Shared resource
 * - wait()
 * - notifyAll()
 * - synchronized
 * - Monitor
 * - Condition-based waiting
 * - Thread coordination
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     while (queue.isEmpty())
 *
 * to:
 *
 *     if (queue.isEmpty())
 *
 * and increase the number of consumers.
 *
 * Think about why using if is unsafe.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
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
 * Run the program.
 *
 * Ask yourself:
 *
 *     What changes when multiple producers/consumers are
 *     waiting?
 *
 *
 * notify() wakes one waiting thread, while notifyAll() wakes
 * all waiting threads.
 *
 * Which one is appropriate depends on the coordination design.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make the queue capacity smaller:
 *
 *     capacity = 1
 *
 * Observe producers being forced to wait more frequently.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Start consumers BEFORE producers.
 *
 * Consumers should initially wait because the queue is empty.
 *
 * Then start producers.
 *
 * Observe consumers waking up when orders become available.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is the Producer-Consumer problem?
 *
 * 2. Why does a consumer wait when the queue is empty?
 *
 * 3. Why does a producer wait when the queue is full?
 *
 * 4. What does wait() do to the monitor lock?
 *
 * 5. What is the difference between wait() and sleep()?
 *
 * Answer:
 *
 *     wait() releases the monitor while waiting.
 *
 *     sleep() does NOT release a monitor lock held by the
 *     sleeping thread.
 *
 * 6. Why should wait() usually be inside a while loop?
 *
 * 7. Difference between notify() and notifyAll()?
 *
 * 8. Can wait() be called outside synchronized?
 *
 * Normally no; doing so causes IllegalMonitorStateException.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Inter-thread communication is about threads coordinating
 * around CONDITIONS.
 *
 *
 * Producer:
 *
 *     while (queue is full)
 *         wait();
 *
 *     produce();
 *     notifyAll();
 *
 *
 * Consumer:
 *
 *     while (queue is empty)
 *         wait();
 *
 *     consume();
 *     notifyAll();
 *
 *
 * This pattern is one of the most important classic
 * multithreading problems.
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 16:
 *
 *     BoundedBuffer.java
 *
 * We will make the producer-consumer problem more realistic
 * with multiple producers, multiple consumers, and a bounded
 * queue.
 *
 * ============================================================
 */

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    /*
     * Shared order queue.
     *
     * Both producers and consumers access this object.
     */
    static class OrderQueue {

        private final Queue<String> orders =
                new LinkedList<>();

        /*
         * Maximum number of orders the queue can hold.
         */
        private final int capacity;

        OrderQueue(int capacity) {
            this.capacity = capacity;
        }

        /*
         * PRODUCER
         *
         * Adds a new order to the queue.
         */
        public synchronized void produce(
                String order)
                throws InterruptedException {

            /*
             * If the queue is full, the producer must wait.
             */
            while (orders.size() == capacity) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> Queue FULL. Producer waiting..."
                );

                wait();
            }

            /*
             * Add the order.
             */
            orders.add(order);

            System.out.println(
                    Thread.currentThread().getName()
                            + " produced: "
                            + order
                            + " | Queue size: "
                            + orders.size()
            );

            /*
             * The queue is no longer empty.
             *
             * Wake waiting consumers/producers so they can
             * re-check their conditions.
             */
            notifyAll();
        }

        /*
         * CONSUMER
         *
         * Removes an order from the queue.
         */
        public synchronized String consume()
                throws InterruptedException {

            /*
             * If there are no orders, the consumer must wait.
             */
            while (orders.isEmpty()) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> Queue EMPTY. Consumer waiting..."
                );

                wait();
            }

            /*
             * Remove the oldest order.
             */
            String order =
                    orders.poll();

            System.out.println(
                    Thread.currentThread().getName()
                            + " consumed: "
                            + order
                            + " | Queue size: "
                            + orders.size()
            );

            /*
             * Space is now available in the queue.
             *
             * Wake waiting producers/consumers so they can
             * re-check their conditions.
             */
            notifyAll();

            return order;
        }
    }

    /*
     * Producer task.
     */
    static class Producer implements Runnable {

        private final OrderQueue orderQueue;
        private final String producerName;

        Producer(
                OrderQueue orderQueue,
                String producerName) {

            this.orderQueue = orderQueue;
            this.producerName = producerName;
        }

        @Override
        public void run() {

            try {

                for (int i = 1; i <= 5; i++) {

                    String order =
                            producerName
                                    + "-Order-"
                                    + i;

                    orderQueue.produce(order);

                    /*
                     * Simulate time between incoming orders.
                     */
                    Thread.sleep(300);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        producerName
                                + " interrupted."
                );
            }
        }
    }

    /*
     * Consumer task.
     */
    static class Consumer implements Runnable {

        private final OrderQueue orderQueue;
        private final String consumerName;

        Consumer(
                OrderQueue orderQueue,
                String consumerName) {

            this.orderQueue = orderQueue;
            this.consumerName = consumerName;
        }

        @Override
        public void run() {

            try {

                for (int i = 1; i <= 5; i++) {

                    String order =
                            orderQueue.consume();

                    System.out.println(
                            consumerName
                                    + " processing "
                                    + order
                    );

                    /*
                     * Simulate order processing.
                     */
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        consumerName
                                + " interrupted."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create a bounded shared queue.
         *
         * Capacity = 3
         */
        OrderQueue orderQueue =
                new OrderQueue(3);

        /*
         * Create producers.
         */
        Thread producer1 =
                new Thread(
                        new Producer(
                                orderQueue,
                                "Restaurant-A"
                        ),
                        "Producer-1"
                );

        Thread producer2 =
                new Thread(
                        new Producer(
                                orderQueue,
                                "Restaurant-B"
                        ),
                        "Producer-2"
                );

        /*
         * Create consumers.
         */
        Thread consumer1 =
                new Thread(
                        new Consumer(
                                orderQueue,
                                "Delivery-Worker-1"
                        ),
                        "Consumer-1"
                );

        Thread consumer2 =
                new Thread(
                        new Consumer(
                                orderQueue,
                                "Delivery-Worker-2"
                        ),
                        "Consumer-2"
                );

        /*
         * Start consumers first.
         *
         * They may initially wait because the queue is empty.
         */
        consumer1.start();
        consumer2.start();

        /*
         * Give consumers a moment to enter waiting state.
         */
        Thread.sleep(500);

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
                "All orders processed."
        );
    }
}
