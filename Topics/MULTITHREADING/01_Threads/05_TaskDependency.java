/*
 * ============================================================
 * 05 - TASK DEPENDENCY USING join()
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce application.
 *
 * A customer places an order.
 *
 * The application performs these operations:
 *
 *     1. Process Payment
 *     2. Generate Order Confirmation
 *
 * Order confirmation must NOT be generated before payment
 * processing is completed.
 *
 *
 * PROBLEM:
 * ------------------------------------------------------------
 * We want payment processing to happen on a separate thread.
 *
 * But the main thread has a dependency:
 *
 *     Payment
 *        ↓
 *     Confirmation
 *
 * If the main thread continues immediately, it may generate
 * the confirmation before payment has finished.
 *
 *
 * QUESTION:
 * ------------------------------------------------------------
 * How can one thread wait for another thread to complete?
 *
 *
 * SOLUTION:
 * ------------------------------------------------------------
 * Use:
 *
 *     Thread.join()
 *
 *
 * WHAT DOES join() DO?
 * ------------------------------------------------------------
 * If Thread A calls:
 *
 *     threadB.join();
 *
 * then Thread A waits until Thread B finishes.
 *
 *
 * VISUALIZATION:
 * ------------------------------------------------------------
 *
 * Main Thread
 *     |
 *     | start payment
 *     v
 * Payment Thread
 *     |
 *     | process payment
 *     |
 *     v
 * Payment Complete
 *
 * Main Thread
 *     |
 *     | join()
 *     | waits
 *     v
 * Confirmation
 *
 *
 * WITHOUT join():
 * ------------------------------------------------------------
 *
 * Payment starts
 *      |
 *      +--------------------+
 *                           |
 * Main Thread               |
 *      |                    |
 *      v                    |
 * Confirmation              |
 *                           |
 *                    Payment finishes
 *
 * This can produce incorrect behavior.
 *
 *
 * WITH join():
 * ------------------------------------------------------------
 *
 * Payment starts
 *      |
 *      v
 * Main waits using join()
 *      |
 *      |
 * Payment finishes
 *      |
 *      v
 * Main continues
 *      |
 *      v
 * Confirmation generated
 *
 *
 * CONCEPTS:
 * ------------------------------------------------------------
 * - Thread dependency
 * - join()
 * - Waiting for another thread
 * - Sequential dependency between concurrent tasks
 *
 *
 * IMPORTANT:
 * ------------------------------------------------------------
 * join() does NOT stop the other thread.
 *
 * It only makes the calling thread wait.
 *
 *
 * EXPERIMENT 1:
 * ------------------------------------------------------------
 * Comment out:
 *
 *     paymentThread.join();
 *
 * Run the program.
 *
 * Observe whether confirmation can be generated before
 * payment completes.
 *
 *
 * EXPERIMENT 2:
 * ------------------------------------------------------------
 * Change payment processing time to:
 *
 *     5000 ms
 *
 * Observe how long the main thread waits.
 *
 *
 * EXPERIMENT 3:
 * ------------------------------------------------------------
 * Add another independent task:
 *
 *     sendNotification()
 *
 * Ask yourself:
 *
 * Should notification wait for payment?
 *
 * If yes:
 *     join() can establish the dependency.
 *
 * If no:
 *     it could run independently.
 *
 *
 * INTERVIEW QUESTIONS:
 * ------------------------------------------------------------
 *
 * 1. What does join() do?
 *
 * 2. Which thread actually waits when join() is called?
 *
 * 3. Does join() create a new thread?
 *
 * 4. Can join() throw InterruptedException?
 *
 * 5. What is the difference between:
 *
 *        thread.start();
 *        thread.join();
 *
 * 6. Can join() be used to establish ordering between threads?
 *
 *
 * KEY TAKEAWAY:
 * ------------------------------------------------------------
 * Multithreading does NOT mean that everything should run
 * independently.
 *
 * Some tasks have dependencies.
 *
 * Example:
 *
 *     Payment
 *        ↓
 *     Confirmation
 *
 *     Download
 *        ↓
 *     Process File
 *
 *     Database Insert
 *        ↓
 *     Generate Report
 *
 * join() is one basic mechanism for expressing such
 * dependencies.
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 * Program 06 -> Background Cleanup using Daemon Thread
 *
 * ============================================================
 */

public class TaskDependency {

    static class PaymentTask extends Thread {

        @Override
        public void run() {

            System.out.println(
                    "Payment processing started..."
            );

            try {

                /*
                 * Simulate payment gateway processing.
                 */
                Thread.sleep(3000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        "Payment processing interrupted."
                );

                return;
            }

            System.out.println(
                    "Payment completed successfully."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println(
                "Order placed."
        );

        /*
         * Create payment processing thread.
         */
        PaymentTask paymentThread =
                new PaymentTask();

        /*
         * Start payment processing.
         */
        paymentThread.start();

        /*
         * The main thread must NOT generate the order
         * confirmation until payment is complete.
         *
         * Therefore, wait for paymentThread.
         */
        paymentThread.join();

        /*
         * This statement executes only after paymentThread
         * finishes.
         */
        System.out.println(
                "Payment verified."
        );

        System.out.println(
                "Order confirmation generated."
        );

        System.out.println(
                "Order processing completed."
        );
    }
}
