/*
 * ============================================================
 * 02 - NOTIFICATION SERVICE USING RUNNABLE
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce application.
 *
 * A customer places an order successfully.
 *
 * The application now needs to:
 *
 *     1. Confirm the order.
 *     2. Send an email notification.
 *
 * Sending the email may take some time.
 *
 * We don't want the main application thread to wait for the
 * notification service to finish.
 *
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * How can we separate:
 *
 *     WHAT WORK SHOULD BE DONE?
 *
 * from:
 *
 *     WHICH THREAD SHOULD EXECUTE THAT WORK?
 *
 *
 * SOLUTION:
 * ------------------------------------------------------------
 * Use Runnable.
 *
 * Runnable represents the TASK.
 *
 * Thread represents the EXECUTION MECHANISM.
 *
 *
 * IMPORTANT DIFFERENCE FROM PROGRAM 01:
 * ------------------------------------------------------------
 * Program 01 extended Thread:
 *
 *     class OrderTask extends Thread
 *
 * Here we will use:
 *
 *     class NotificationTask implements Runnable
 *
 *
 * This is generally a better design because our task is not
 * tightly coupled to the Thread class.
 *
 *
 * CONCEPTS:
 * ------------------------------------------------------------
 * - Runnable
 * - Thread
 * - start()
 * - run()
 * - Separation of task and thread
 * - Lambda expressions (experiment)
 *
 *
 * EXECUTION FLOW:
 * ------------------------------------------------------------
 *
 * Main Thread
 *      |
 *      | creates Runnable
 *      |
 *      v
 * NotificationTask
 *      |
 *      | passed to Thread
 *      |
 *      v
 * Thread
 *      |
 *      | start()
 *      v
 * run()
 *      |
 *      v
 * Send Notification
 *
 *
 * EXPECTED OUTPUT:
 * ------------------------------------------------------------
 * The exact order may vary.
 *
 * Example:
 *
 * Main thread: main
 * Order confirmed: ORD-101
 * Main thread continues working...
 * Notification sent for order: ORD-101
 *
 *
 * EXPERIMENT 1:
 * ------------------------------------------------------------
 * Replace:
 *
 *     notificationThread.start();
 *
 * with:
 *
 *     notificationThread.run();
 *
 * Observe which thread executes the notification task.
 *
 *
 * EXPERIMENT 2:
 * ------------------------------------------------------------
 * Create two notification tasks:
 *
 *     ORD-101
 *     ORD-102
 *
 * Start both.
 *
 * Observe the execution order.
 *
 *
 * EXPERIMENT 3:
 * ------------------------------------------------------------
 * Create the Runnable using a lambda expression:
 *
 *     Runnable task = () -> {
 *         // task
 *     };
 *
 * This works because Runnable is a functional interface.
 *
 *
 * EXPERIMENT 4:
 * ------------------------------------------------------------
 * Create multiple Thread objects using the SAME Runnable object.
 *
 * Ask yourself:
 *
 *     Can one Runnable object be used by multiple threads?
 *
 *
 * INTERVIEW QUESTIONS:
 * ------------------------------------------------------------
 *
 * 1. What is Runnable?
 *
 * 2. Why is Runnable preferred over extending Thread in many
 *    situations?
 *
 * 3. What is the difference between Thread and Runnable?
 *
 * 4. Why doesn't Runnable have a return value?
 *
 * 5. Can the same Runnable object be executed by multiple
 *    threads?
 *
 * 6. What happens if run() is called directly?
 *
 *
 * KEY TAKEAWAY:
 * ------------------------------------------------------------
 * Runnable represents the WORK.
 *
 * Thread represents the WORKER.
 *
 * This separation becomes extremely important when we later
 * learn the Executor Framework.
 *
 *
 * ============================================================
 */

public class RunnableNotificationService {

    /*
     * This class represents the task.
     *
     * It does NOT represent the thread itself.
     */
    static class NotificationTask implements Runnable {

        private final String orderId;

        NotificationTask(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {

            System.out.println(
                    "Notification started for order: "
                            + orderId
                            + " | Thread: "
                            + Thread.currentThread().getName()
            );

            try {

                /*
                 * Simulate email/SMS notification processing.
                 */
                Thread.sleep(2000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        "Notification interrupted for order: "
                                + orderId
                );

                return;
            }

            System.out.println(
                    "Notification sent for order: "
                            + orderId
                            + " | Thread: "
                            + Thread.currentThread().getName()
            );
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "Main thread: "
                        + Thread.currentThread().getName()
        );

        /*
         * Create the task.
         *
         * At this point, no new thread has been created.
         */
        NotificationTask task =
                new NotificationTask("ORD-101");

        /*
         * Create a Thread and give it the task.
         */
        Thread notificationThread =
                new Thread(task);

        /*
         * Start the new thread.
         */
        notificationThread.start();

        /*
         * Main thread continues independently.
         */
        System.out.println(
                "Order confirmed: ORD-101"
        );

        System.out.println(
                "Main thread continues working..."
        );
    }
}
