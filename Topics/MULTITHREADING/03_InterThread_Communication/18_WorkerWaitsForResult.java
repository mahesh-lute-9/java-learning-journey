/*
 * ============================================================
 * 18 - WORKER WAITS FOR A RESULT
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend application that performs a report
 * generation workflow.
 *
 * One thread:
 *
 *     DataFetcher
 *
 * fetches data from a database/API.
 *
 * Another thread:
 *
 *     ReportGenerator
 *
 * needs that data before it can generate the report.
 *
 *
 * WORKFLOW:
 *
 *     DataFetcher
 *          |
 *          | fetch data
 *          v
 *     Shared Result
 *          |
 *          | result available
 *          v
 *     ReportGenerator
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * The ReportGenerator may start before the data is ready.
 *
 * It should NOT:
 *
 *     repeatedly check the result
 *     waste CPU
 *     use Thread.sleep() to guess when data will be ready
 *
 *
 * BAD APPROACH:
 *
 *     while (result == null) {
 *         Thread.sleep(100);
 *     }
 *
 *
 * This is polling.
 *
 * Problems:
 *
 *     - Wastes time/CPU
 *     - Adds arbitrary delays
 *     - Doesn't provide proper coordination
 *
 *
 * ------------------------------------------------------------
 * BETTER APPROACH
 * ------------------------------------------------------------
 *
 * Use condition-based waiting.
 *
 * ReportGenerator:
 *
 *     while (result == null) {
 *         wait();
 *     }
 *
 *
 * DataFetcher:
 *
 *     result = data;
 *     notifyAll();
 *
 *
 * The consumer waits until the producer changes the shared
 * state and signals waiting threads.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DISTINCTION
 * ------------------------------------------------------------
 *
 * Program 15/16:
 *
 *     Producer → Queue → Consumer
 *
 *
 * This program:
 *
 *     Producer → Result → Consumer
 *
 *
 * The underlying coordination idea is the same:
 *
 *     Shared state
 *          +
 *     Condition
 *          +
 *     wait()
 *          +
 *     notifyAll()
 *
 *
 * ------------------------------------------------------------
 * WAITING FOR A CONDITION
 * ------------------------------------------------------------
 *
 * The consumer doesn't wait for:
 *
 *     "3 seconds"
 *
 * It waits for:
 *
 *     "result != null"
 *
 *
 * This is a much more important way of thinking about
 * concurrency.
 *
 *
 * ------------------------------------------------------------
 * WHY while()?
 * ------------------------------------------------------------
 *
 * The consumer uses:
 *
 *     while (result == null) {
 *         wait();
 *     }
 *
 * NOT:
 *
 *     if (result == null) {
 *         wait();
 *     }
 *
 *
 * After waking up, the condition must always be checked again.
 *
 *
 * ------------------------------------------------------------
 * WHY notifyAll()?
 * ------------------------------------------------------------
 *
 * Once the result becomes available, waiting consumers should
 * get an opportunity to re-check the condition.
 *
 * notifyAll() wakes all threads waiting on this object's
 * monitor.
 *
 * They then compete to reacquire the monitor and re-check the
 * condition.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Start ReportGenerator BEFORE DataFetcher.
 *
 * The report generator should enter WAITING state.
 *
 * Then start DataFetcher.
 *
 * Observe the communication.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Start DataFetcher first.
 *
 * Make fetching very fast.
 *
 * Ask yourself:
 *
 *     What happens if the result is already available before
 *     the consumer calls wait()?
 *
 * This is exactly why the condition must be checked BEFORE
 * calling wait().
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Replace wait/notifyAll with Thread.sleep().
 *
 * Compare the design.
 *
 * Think:
 *
 *     Which one waits for a condition?
 *
 *     Which one waits for an arbitrary amount of time?
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Create two report-generator threads.
 *
 * Both should wait for the same result.
 *
 * Change:
 *
 *     notifyAll()
 *
 * to:
 *
 *     notify()
 *
 * Observe the difference.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is polling with sleep() not ideal for this problem?
 *
 * 2. What condition is the consumer waiting for?
 *
 * 3. Why must wait() be called while holding the monitor?
 *
 * 4. What happens to the lock when wait() is called?
 *
 * 5. Why should the condition be checked in a while loop?
 *
 * 6. What happens if notifyAll() is called before the consumer
 *    starts waiting?
 *
 * This is an important question.
 *
 * wait/notify does NOT store notifications for future waiters.
 *
 * Therefore, the shared condition itself is the source of truth.
 *
 * The consumer checks the condition first.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * NEVER think:
 *
 *     "I will wake the thread."
 *
 * Think:
 *
 *     "I will change the shared state and signal threads so
 *      they can re-check their condition."
 *
 *
 * Correct mental model:
 *
 *
 *     Consumer
 *         |
 *         | condition false
 *         v
 *       wait()
 *         |
 *         | releases monitor
 *         |
 *         v
 *     Producer
 *         |
 *         | produces result
 *         |
 *         v
 *     notifyAll()
 *         |
 *         v
 *     Consumer wakes
 *         |
 *         v
 *     re-check condition
 *         |
 *         v
 *     process result
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 19:
 *
 *     ProducerConsumerWithShutdown.java
 *
 * We will solve an important real-world problem:
 *
 *     How do we STOP producer/consumer threads gracefully?
 *
 * This introduces thread interruption and graceful shutdown.
 *
 * ============================================================
 */

public class WorkerWaitsForResult {

    /*
     * Shared object containing the result.
     */
    static class ResultHolder {

        /*
         * Shared mutable state.
         *
         * null means:
         *
         *     Result is not ready.
         */
        private String result;

        /*
         * Called by the producer.
         */
        public synchronized void publishResult(
                String result) {

            /*
             * Update the shared state FIRST.
             */
            this.result = result;

            System.out.println(
                    Thread.currentThread().getName()
                            + " published result."
            );

            /*
             * Signal waiting consumers.
             */
            notifyAll();
        }

        /*
         * Called by the consumer.
         */
        public synchronized String waitForResult()
                throws InterruptedException {

            /*
             * Wait until the actual condition becomes true.
             */
            while (result == null) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " waiting for result..."
                );

                wait();
            }

            /*
             * At this point the condition is satisfied.
             */
            return result;
        }
    }

    /*
     * Simulates a thread that fetches data.
     */
    static class DataFetcher implements Runnable {

        private final ResultHolder holder;

        DataFetcher(ResultHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {

            try {

                System.out.println(
                        Thread.currentThread().getName()
                                + " fetching data..."
                );

                /*
                 * Simulate a slow database/API operation.
                 */
                Thread.sleep(3000);

                String data =
                        "Customer report data";

                /*
                 * Publish the result.
                 *
                 * This changes the shared condition from:
                 *
                 *     result == null
                 *
                 * to:
                 *
                 *     result != null
                 */
                holder.publishResult(data);

            } catch (InterruptedException e) {

                /*
                 * Restore the interrupted status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        Thread.currentThread().getName()
                                + " interrupted."
                );
            }
        }
    }

    /*
     * Simulates a thread that needs the fetched data.
     */
    static class ReportGenerator implements Runnable {

        private final ResultHolder holder;

        ReportGenerator(ResultHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {

            try {

                /*
                 * Wait until the result becomes available.
                 */
                String data =
                        holder.waitForResult();

                System.out.println(
                        Thread.currentThread().getName()
                                + " received: "
                                + data
                );

                System.out.println(
                        Thread.currentThread().getName()
                                + " generating report..."
                );

                /*
                 * Simulate report generation.
                 */
                Thread.sleep(1000);

                System.out.println(
                        Thread.currentThread().getName()
                                + " report generated."
                );

            } catch (InterruptedException e) {

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
         * Shared result holder.
         *
         * Both worker threads use this SAME object.
         */
        ResultHolder holder =
                new ResultHolder();

        /*
         * Create producer.
         */
        Thread dataFetcher =
                new Thread(
                        new DataFetcher(holder),
                        "Data-Fetcher"
                );

        /*
         * Create consumer.
         */
        Thread reportGenerator =
                new Thread(
                        new ReportGenerator(holder),
                        "Report-Generator"
                );

        /*
         * Start the consumer FIRST.
         *
         * The result is currently null, so the consumer should
         * enter WAITING state.
         */
        reportGenerator.start();

        /*
         * Give the consumer enough time to reach wait().
         *
         * This sleep is ONLY for demonstration.
         *
         * The actual synchronization is done using wait()
         * and notifyAll().
         */
        Thread.sleep(500);

        /*
         * Start the producer.
         */
        dataFetcher.start();

        /*
         * Wait for both threads to complete.
         */
        dataFetcher.join();
        reportGenerator.join();

        System.out.println(
                "Workflow completed successfully."
        );
    }
}
