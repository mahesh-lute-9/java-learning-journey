/*
 * ============================================================
 * 33 - MULTIPLE FUTURES: PARALLEL SERVICE CALLS
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce "Order Details" API.
 *
 * To build the final response, the backend needs data from
 * several independent services:
 *
 *     User Service
 *     Order Service
 *     Payment Service
 *
 *
 * These operations are independent.
 *
 * There is NO reason to execute them sequentially:
 *
 *     User Service
 *          ↓
 *     Order Service
 *          ↓
 *     Payment Service
 *
 *
 * Instead, we can execute them concurrently:
 *
 *
 *              API Request
 *                  |
 *          +-------+-------+
 *          |       |       |
 *          v       v       v
 *        User    Order   Payment
 *        Task     Task     Task
 *          |       |       |
 *          +-------+-------+
 *                  |
 *                  v
 *             Combine Result
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS USEFUL?
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     User Service    → 2 seconds
 *     Order Service   → 3 seconds
 *     Payment Service → 2 seconds
 *
 *
 * Sequential execution:
 *
 *     2 + 3 + 2 = 7 seconds
 *
 *
 * Concurrent execution can approach:
 *
 *     max(2, 3, 2) = 3 seconds
 *
 * ignoring scheduling, queueing, and other overhead.
 *
 *
 * This is a major backend concurrency pattern.
 *
 *
 * ------------------------------------------------------------
 * THE PATTERN
 * ------------------------------------------------------------
 *
 *     submit()
 *       ↓
 *     Future
 *       ↓
 *     submit()
 *       ↓
 *     Future
 *       ↓
 *     submit()
 *       ↓
 *     Future
 *
 * Then:
 *
 *     future1.get()
 *     future2.get()
 *     future3.get()
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * The tasks are submitted FIRST.
 *
 * We don't immediately do:
 *
 *     submit()
 *     get()
 *     submit()
 *     get()
 *
 *
 * because that can unnecessarily serialize the work.
 *
 *
 * Better:
 *
 *     submit all
 *          ↓
 *     wait/retrieve all
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 * Imagine:
 *
 *     Future<String> userFuture
 *     Future<String> orderFuture
 *     Future<String> paymentFuture
 *
 *
 * The final API response might combine:
 *
 *     userFuture.get()
 *     orderFuture.get()
 *     paymentFuture.get()
 *
 *
 * ------------------------------------------------------------
 * FAILURE
 * ------------------------------------------------------------
 *
 * What if one service fails?
 *
 * Future.get() can throw:
 *
 *     ExecutionException
 *
 *
 * Production systems need to decide:
 *
 *     - Fail entire request?
 *     - Return partial response?
 *     - Retry?
 *     - Use fallback?
 *     - Return cached data?
 *
 *
 * This is an important distributed-systems design question.
 *
 *
 * ------------------------------------------------------------
 * TIMEOUTS
 * ------------------------------------------------------------
 *
 * Never assume an external service will respond quickly.
 *
 * Instead of:
 *
 *     future.get()
 *
 * you can use:
 *
 *     future.get(timeout, unit)
 *
 *
 * This prevents waiting indefinitely.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the service delays.
 *
 * Observe that total execution time is closer to the slowest
 * service rather than the sum of all service times.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make one service throw an exception.
 *
 * Observe:
 *
 *     ExecutionException
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     get(2, TimeUnit.SECONDS)
 *
 * for a service that takes 5 seconds.
 *
 * Observe:
 *
 *     TimeoutException
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Compare:
 *
 *     sequential version
 *
 * with:
 *
 *     concurrent version
 *
 *
 * Measure the execution time.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why submit all tasks before calling get()?
 *
 * To allow independent tasks to execute concurrently.
 *
 *
 * 2. Is Future.get() blocking?
 *
 * Yes.
 *
 *
 * 3. What happens when a Callable throws an exception?
 *
 * Future.get() throws ExecutionException.
 *
 *
 * 4. How can you prevent waiting forever?
 *
 * Use a timed get().
 *
 *
 * 5. What is the theoretical benefit of parallelizing
 *    independent tasks?
 *
 * Total latency can approach the slowest task instead of the
 * sum of all task latencies, subject to real-world overhead.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Independent work:
 *
 *     Run concurrently.
 *
 *
 * Dependent work:
 *
 *     Wait for the required result.
 *
 *
 * Backend pattern:
 *
 *
 *     API Request
 *          |
 *     +----+----+----+
 *     |    |    |    |
 *     v    v    v    v
 *    DB   User Payment Cache
 *     |    |    |    |
 *     +----+----+----+
 *          |
 *          v
 *       Response
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 34:
 *
 *     FutureTimeoutCancellation.java
 *
 * We will handle a production-style problem:
 *
 *     "What happens when a background task takes too long?"
 *
 * We'll introduce timeout and cancellation.
 *
 * ============================================================
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MultipleFutureScenario {

    /*
     * Simulates a remote service.
     */
    static class UserService
            implements Callable<String> {

        @Override
        public String call()
                throws Exception {

            System.out.println(
                    "Calling User Service..."
            );

            Thread.sleep(2000);

            return "User: Mahesh";
        }
    }

    static class OrderService
            implements Callable<String> {

        @Override
        public String call()
                throws Exception {

            System.out.println(
                    "Calling Order Service..."
            );

            Thread.sleep(3000);

            return "Orders: 5";
        }
    }

    static class PaymentService
            implements Callable<String> {

        @Override
        public String call()
                throws Exception {

            System.out.println(
                    "Calling Payment Service..."
            );

            Thread.sleep(2000);

            return "Payment Status: SUCCESS";
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create a thread pool with three workers.
         *
         * Each independent service can execute concurrently.
         */
        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        long startTime =
                System.currentTimeMillis();

        /*
         * ----------------------------------------------------
         * SUBMIT ALL TASKS FIRST
         * ----------------------------------------------------
         */

        Future<String> userFuture =
                executor.submit(
                        new UserService()
                );

        Future<String> orderFuture =
                executor.submit(
                        new OrderService()
                );

        Future<String> paymentFuture =
                executor.submit(
                        new PaymentService()
                );

        System.out.println(
                "All service calls submitted."
        );

        try {

            /*
             * Retrieve the results.
             *
             * These calls block only if the corresponding
             * result is not ready yet.
             */
            String user =
                    userFuture.get();

            String orders =
                    orderFuture.get();

            String payment =
                    paymentFuture.get();

            /*
             * Combine the independent results into one response.
             */
            System.out.println(
                    "\n========== FINAL API RESPONSE =========="
            );

            System.out.println(
                    user
            );

            System.out.println(
                    orders
            );

            System.out.println(
                    payment
            );

            System.out.println(
                    "========================================="
            );

        } catch (ExecutionException e) {

            /*
             * One of the background tasks failed.
             */
            System.out.println(
                    "One of the services failed:"
            );

            System.out.println(
                    e.getCause()
            );
        }

        long endTime =
                System.currentTimeMillis();

        System.out.println(
                "\nTotal execution time: "
                        + (endTime - startTime)
                        + " ms"
        );

        /*
         * Stop accepting new tasks.
         */
        executor.shutdown();

        /*
         * Wait for executor termination.
         */
        executor.awaitTermination(
                10,
                TimeUnit.SECONDS
        );

        System.out.println(
                "Executor shutdown completed."
        );
    }
}
