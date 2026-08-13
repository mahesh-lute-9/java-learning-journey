/*
 * ============================================================
 * 47 - PRODUCER-CONSUMER USING BlockingQueue
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce application.
 *
 * Customers place orders continuously.
 *
 *     Customer
 *         ↓
 *     Order Producer
 *         ↓
 *     Order Queue
 *         ↓
 *     Order Consumer
 *         ↓
 *     Process Order
 *
 *
 * The producer and consumer work at different speeds.
 *
 *
 * Example:
 *
 *     Producer → creates 10 orders/sec
 *     Consumer → processes 5 orders/sec
 *
 *
 * We need a safe buffer between them.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     BlockingQueue
 *
 *
 * A BlockingQueue is a thread-safe queue designed for
 * producer-consumer scenarios.
 *
 *
 * ------------------------------------------------------------
 * WHY NOT ArrayList / LinkedList?
 * ------------------------------------------------------------
 *
 * A normal collection is not automatically safe for multiple
 * threads performing concurrent operations.
 *
 *
 * BlockingQueue provides:
 *
 *     Thread safety
 *     Blocking insertion
 *     Blocking removal
 *     Capacity control
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 *
 * put()
 *
 *     Adds an element.
 *
 *     If the queue is full, the producer waits.
 *
 *
 * take()
 *
 *     Removes an element.
 *
 *     If the queue is empty, the consumer waits.
 *
 *
 * ------------------------------------------------------------
 * QUEUE FLOW
 * ------------------------------------------------------------
 *
 *
 * Producer
 *     |
 *     | put(order)
 *     v
 * +----------------+
 * | BlockingQueue  |
 * |                |
 * | Order 1        |
 * | Order 2        |
 * | Order 3        |
 * +----------------+
 *     |
 *     | take()
 *     v
 * Consumer
 *
 *
 * ------------------------------------------------------------
 * WHY BLOCKING?
 * ------------------------------------------------------------
 *
 * If queue is empty:
 *
 *
 *     Consumer → take()
 *                  ↓
 *               WAIT
 *
 *
 * When producer adds an item:
 *
 *
 *     Producer → put(order)
 *                  ↓
 *             Consumer wakes
 *
 *
 * Similarly, if queue is full:
 *
 *
 *     Producer → put(order)
 *                  ↓
 *                WAIT
 *
 *
 * until a consumer removes something.
 *
 *
 * ------------------------------------------------------------
 * BACKPRESSURE
 * ------------------------------------------------------------
 *
 * A bounded BlockingQueue naturally provides a form of
 * backpressure.
 *
 *
 * If producers are too fast:
 *
 *     queue fills
 *        ↓
 *     producer waits
 *        ↓
 *     consumer catches up
 *
 *
 * This prevents unlimited queue growth.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD EXAMPLES
 * ------------------------------------------------------------
 *
 *     Order processing
 *     Email processing
 *     Log processing
 *     Image processing
 *     Background jobs
 *     Message pipelines
 *     Task queues
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change queue capacity:
 *
 *     5
 *
 * to:
 *
 *     1
 *
 *
 * Observe how frequently the producer has to wait.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make the consumer slower.
 *
 *
 * Observe the queue filling up.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Create two producers.
 *
 *
 * Ask:
 *
 *     Can both safely add to the BlockingQueue?
 *
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Create multiple consumers.
 *
 *
 * Observe that different consumers can safely process
 * different queue elements.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is BlockingQueue?
 *
 * A thread-safe queue that can block producers/consumers when
 * the queue is full/empty.
 *
 *
 * 2. What does put() do?
 *
 * Adds an element, waiting if necessary for capacity.
 *
 *
 * 3. What does take() do?
 *
 * Removes and returns an element, waiting if necessary for an
 * available element.
 *
 *
 * 4. What is backpressure?
 *
 * A mechanism that prevents a fast producer from overwhelming
 * a slower consumer.
 *
 *
 * 5. Why use a bounded queue?
 *
 * To limit memory/resource usage and provide controlled
 * backpressure.
 *
 *
 * 6. Can multiple producers and consumers use a BlockingQueue?
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * Producer
 *     ↓
 *     put()
 *     ↓
 * BlockingQueue
 *     ↓
 *     take()
 *     ↓
 * Consumer
 *
 *
 * This is one of the most important concurrency patterns in
 * backend systems.
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 48:
 *
 *     ProducerConsumerMultipleWorkers.java
 *
 * We will extend this to:
 *
 *     Multiple producers
 *     Multiple consumers
 *     Graceful shutdown
 *
 * This gets much closer to a real background-job system.
 *
 * ============================================================
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerScenario {

    /*
     * Maximum number of orders the queue can hold.
     *
     * A bounded queue provides backpressure.
     */
    private static final int QUEUE_CAPACITY = 5;

    /*
     * Number of orders produced.
     */
    private static final int TOTAL_ORDERS = 10;

    /*
     * Thread-safe bounded queue.
     */
    private static final BlockingQueue<String> orderQueue =
            new ArrayBlockingQueue<>(
                    QUEUE_CAPACITY
            );

    /*
     * Producer creates orders and puts them into the queue.
     */
    static class OrderProducer
            implements Runnable {

        @Override
        public void run() {

            try {

                for (int i = 1;
                     i <= TOTAL_ORDERS;
                     i++) {

                    String order =
                            "Order-" + i;

                    System.out.println(
                            "Producer created "
                                    + order
                    );

                    /*
                     * put() blocks if the queue is full.
                     */
                    orderQueue.put(order);

                    System.out.println(
                            "Producer added "
                                    + order
                                    + " | Queue size: "
                                    + orderQueue.size()
                    );

                    /*
                     * Simulate customers placing orders.
                     */
                    Thread.sleep(300);
                }

                System.out.println(
                        "Producer finished."
                );

            } catch (InterruptedException e) {

                /*
                 * Restore interruption status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        "Producer interrupted."
                );
            }
        }
    }

    /*
     * Consumer takes orders from the queue and processes them.
     */
    static class OrderConsumer
            implements Runnable {

        @Override
        public void run() {

            try {

                for (int i = 1;
                     i <= TOTAL_ORDERS;
                     i++) {

                    /*
                     * take() blocks if the queue is empty.
                     */
                    String order =
                            orderQueue.take();

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " processing "
                                    + order
                                    + " | Queue size: "
                                    + orderQueue.size()
                    );

                    /*
                     * Simulate slower order processing.
                     */
                    Thread.sleep(1000);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " completed "
                                    + order
                    );
                }

                System.out.println(
                        Thread.currentThread().getName()
                                + " finished."
                );

            } catch (InterruptedException e) {

                /*
                 * Restore interruption status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        Thread.currentThread().getName()
                                + " interrupted."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create producer thread.
         */
        Thread producer =
                new Thread(
                        new OrderProducer(),
                        "Order-Producer"
                );

        /*
         * Create consumer thread.
         */
        Thread consumer =
                new Thread(
                        new OrderConsumer(),
                        "Order-Consumer"
                );

        /*
         * Start producer and consumer.
         */
        producer.start();
        consumer.start();

        /*
         * Wait for producer to finish.
         */
        producer.join();

        /*
         * Wait for consumer to process all orders.
         */
        consumer.join();

        System.out.println(
                "\nAll orders processed successfully."
        );
    }
}
