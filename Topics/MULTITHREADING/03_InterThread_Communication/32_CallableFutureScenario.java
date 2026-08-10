/*
 * ============================================================
 * 32 - CALLABLE + FUTURE
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce backend calculating the final price
 * of an order.
 *
 * The calculation may involve:
 *
 *     Product prices
 *     Discounts
 *     Tax
 *     Shipping
 *
 * We don't want the main thread to perform the calculation
 * itself.
 *
 * Instead:
 *
 *     Main Thread
 *          |
 *          | submit task
 *          v
 *     Thread Pool
 *          |
 *          | calculate
 *          v
 *       Result
 *          |
 *          v
 *        Future
 *          |
 *          v
 *     Main Thread
 *
 *
 * ------------------------------------------------------------
 * PROBLEM WITH Runnable
 * ------------------------------------------------------------
 *
 * Runnable's run() method returns:
 *
 *     void
 *
 *
 * So if we submit:
 *
 *     Runnable
 *
 * it performs work but does not directly return a result.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     Callable<T>
 *
 *
 * Callable is similar to Runnable, but:
 *
 *     Callable<T>.call()
 *
 * can return a value.
 *
 *
 * It can also throw checked exceptions.
 *
 *
 * ------------------------------------------------------------
 * FUTURE
 * ------------------------------------------------------------
 *
 * When we submit a Callable:
 *
 *
 *     Future<Integer> future =
 *             executor.submit(task);
 *
 *
 * Future represents the result of an asynchronous computation.
 *
 *
 * We can later call:
 *
 *     future.get()
 *
 *
 * to obtain the result.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * future.get() is BLOCKING.
 *
 *
 * If the task is still running:
 *
 *     get()
 *
 * waits until the task completes.
 *
 *
 * So:
 *
 *
 *     submit()
 *        ↓
 *     continue doing other work
 *        ↓
 *     get()
 *        ↓
 *     wait only if result isn't ready
 *
 *
 * This is more useful than immediately calling get() if there
 * is other work the calling thread can perform first.
 *
 *
 * ------------------------------------------------------------
 * FUTURE METHODS
 * ------------------------------------------------------------
 *
 * get()
 *
 *     Wait for and retrieve the result.
 *
 *
 * get(timeout, unit)
 *
 *     Wait only for a limited amount of time.
 *
 *
 * isDone()
 *
 *     Check whether the task completed.
 *
 *
 * isCancelled()
 *
 *     Check whether the task was cancelled.
 *
 *
 * cancel(true)
 *
 *     Attempt to cancel the task and interrupt its worker if
 *     it is running.
 *
 *
 * ------------------------------------------------------------
 * RUNTIME FLOW
 * ------------------------------------------------------------
 *
 *
 * Main Thread
 *      |
 *      | submit Callable
 *      v
 * Executor
 *      |
 *      v
 * Worker Thread
 *      |
 *      | calculate
 *      v
 * Future
 *      |
 *      v
 * Main Thread calls get()
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Add:
 *
 *     Thread.sleep(5000)
 *
 * inside Callable.
 *
 * Then observe that:
 *
 *     future.get()
 *
 * waits for the result.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Submit three Callable tasks:
 *
 *     calculateTax()
 *     calculateShipping()
 *     calculateDiscount()
 *
 * Retrieve all results later.
 *
 * This demonstrates parallel computation.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     future.get()
 *
 * with:
 *
 *     future.get(1, TimeUnit.SECONDS)
 *
 *
 * What happens if the calculation takes longer?
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Try:
 *
 *     future.cancel(true)
 *
 *
 * and observe the worker's InterruptedException.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Difference between Runnable and Callable?
 *
 * 2. What does Future represent?
 *
 * 3. Is future.get() blocking?
 *
 * Yes.
 *
 * 4. What happens if Callable throws an exception?
 *
 * Future.get() throws ExecutionException whose cause is the
 * original exception.
 *
 *
 * 5. What does cancel(true) attempt to do?
 *
 * It attempts to cancel the task and interrupt its executing
 * thread if appropriate.
 *
 *
 * 6. Difference between:
 *
 *     isDone()
 *
 * and:
 *
 *     get()
 *
 *
 * isDone() checks completion without waiting.
 *
 * get() waits for completion if necessary.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Runnable:
 *
 *     "Do some work."
 *
 *
 * Callable:
 *
 *     "Do some work and return a result."
 *
 *
 * Future:
 *
 *     "Give me access to the result of that asynchronous work."
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 33:
 *
 *     MultipleFutureScenario.java
 *
 * We will submit multiple independent tasks and combine their
 * results — similar to a backend API that gathers data from
 * several services.
 *
 * ============================================================
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureScenario {

    /*
     * Simulates an expensive order calculation.
     */
    static class OrderPriceCalculator
            implements Callable<Double> {

        private final int orderId;

        OrderPriceCalculator(int orderId) {

            this.orderId =
                    orderId;
        }

        @Override
        public Double call()
                throws Exception {

            System.out.println(
                    Thread.currentThread().getName()
                            + " calculating Order-"
                            + orderId
            );

            /*
             * Simulate expensive calculation.
             */
            Thread.sleep(3000);

            /*
             * Simulated order price.
             */
            double price = 2500.0;

            double tax = price * 0.18;

            double shipping = 100.0;

            double finalPrice =
                    price
                            + tax
                            + shipping;

            System.out.println(
                    Thread.currentThread().getName()
                            + " finished calculation."
            );

            /*
             * Callable returns a value.
             */
            return finalPrice;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create a small thread pool.
         */
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        /*
         * Submit Callable task.
         *
         * Future represents the pending result.
         */
        Future<Double> future =
                executor.submit(
                        new OrderPriceCalculator(101)
                );

        System.out.println(
                "Order calculation submitted."
        );

        /*
         * Main thread can perform other work while the worker
         * calculates the order price.
         */
        System.out.println(
                "Main thread performing other work..."
        );

        Thread.sleep(1000);

        System.out.println(
                "Main thread now needs the result."
        );

        try {

            /*
             * get() waits if the calculation is still running.
             */
            Double finalPrice =
                    future.get();

            System.out.println(
                    "Final order price: ₹"
                            + finalPrice
            );

        } catch (ExecutionException e) {

            /*
             * The actual exception thrown by Callable is
             * available through getCause().
             */
            System.out.println(
                    "Calculation failed: "
                            + e.getCause()
            );
        }

        /*
         * Stop accepting new tasks.
         */
        executor.shutdown();

        /*
         * Wait for executor termination.
         */
        executor.awaitTermination(
                10,
                java.util.concurrent.TimeUnit.SECONDS
        );

        System.out.println(
                "Executor shutdown completed."
        );
    }
}
