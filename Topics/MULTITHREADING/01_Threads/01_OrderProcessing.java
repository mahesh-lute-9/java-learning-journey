/*
 * ============================================================
 * 01 - ORDER PROCESSING
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce application.
 *
 * When a customer places an order, the application needs to
 * process that order. Order processing may take some time.
 *
 * We don't want the main application thread to remain blocked
 * while the order is being processed.
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * How can we execute order processing independently from the
 * main thread?
 *
 * GOAL:
 * ------------------------------------------------------------
 * Create a separate thread for processing the order.
 *
 *
 * CONCEPTS:
 * ------------------------------------------------------------
 * - Thread
 * - start()
 * - run()
 * - Thread.currentThread()
 * - Basic concurrent execution
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 * start() and run() are NOT the same.
 *
 * start():
 *     Creates/schedules a new thread and causes run() to
 *     execute on that thread.
 *
 * run():
 *     Is just a normal method call if called directly.
 *
 *
 * EXPECTED BEHAVIOR:
 * ------------------------------------------------------------
 * The main thread should continue its work while the order
 * processing happens on another thread.
 *
 * The exact order of output may vary because thread scheduling
 * is controlled by the JVM/OS.
 *
 *
 * EXPERIMENT 1:
 * ------------------------------------------------------------
 * Replace:
 *
 *     orderThread.start();
 *
 * with:
 *
 *     orderThread.run();
 *
 * Observe which thread processes the order.
 *
 *
 * EXPERIMENT 2:
 * ------------------------------------------------------------
 * Create three OrderTask objects and start all of them.
 *
 * Observe whether they always execute in the same order in
 * which they were started.
 *
 *
 * EXPERIMENT 3:
 * ------------------------------------------------------------
 * Give each thread a meaningful name using setName().
 *
 *
 * INTERVIEW QUESTIONS:
 * ------------------------------------------------------------
 * 1. What is the difference between start() and run()?
 *
 * 2. Can start() be called twice on the same Thread object?
 *
 * 3. Does start() guarantee immediate execution?
 *
 * 4. Which thread executes run() after start()?
 *
 *
 * ============================================================
 */

public class OrderProcessing extends Thread {

    @Override
    public void run() {

        System.out.println(
                "Order processing started by: "
                        + Thread.currentThread().getName()
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }

        System.out.println(
                "Order processing completed by: "
                        + Thread.currentThread().getName()
        );
    }

    public static void main(String[] args) {

        System.out.println(
                "Main thread: "
                        + Thread.currentThread().getName()
        );

        OrderProcessing orderThread =
                new OrderProcessing();

        orderThread.start();

        System.out.println(
                "Main thread continues working..."
        );
    }
}
