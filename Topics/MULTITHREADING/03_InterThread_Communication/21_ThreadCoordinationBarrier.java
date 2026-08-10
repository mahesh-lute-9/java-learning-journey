/*
 * ============================================================
 * 21 - THREAD COORDINATION USING A BARRIER
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a distributed data-processing system.
 *
 * Four workers process four different parts of a large dataset:
 *
 *     Worker-1 → Part A
 *     Worker-2 → Part B
 *     Worker-3 → Part C
 *     Worker-4 → Part D
 *
 * Each worker can process its own part independently.
 *
 * BUT:
 *
 * After processing their individual parts, all workers must
 * reach a checkpoint before the next phase can begin.
 *
 *
 * PHASE 1:
 *
 *     Worker-1 ──> Process Part A ──┐
 *     Worker-2 ──> Process Part B ──┤
 *     Worker-3 ──> Process Part C ──┤
 *     Worker-4 ──> Process Part D ──┘
 *                                   |
 *                              ALL ARRIVED
 *                                   |
 *                                   v
 *                              PHASE 2
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * Suppose Worker-1 finishes very quickly.
 *
 * Worker-2, Worker-3 and Worker-4 are still processing.
 *
 * Worker-1 must NOT start Phase 2 yet.
 *
 * It must wait until EVERY worker has completed Phase 1.
 *
 *
 * This is called a:
 *
 *     BARRIER
 *
 *
 * ------------------------------------------------------------
 * WHAT IS A BARRIER?
 * ------------------------------------------------------------
 *
 * A barrier is a synchronization point where multiple threads
 * must arrive before any of them can continue.
 *
 *
 * Example:
 *
 *     Worker-1 ────────┐
 *     Worker-2 ────────┤
 *     Worker-3 ────────┤──> BARRIER ──> Phase 2
 *     Worker-4 ────────┘
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS DIFFERENT FROM join()?
 * ------------------------------------------------------------
 *
 * join() is usually:
 *
 *     Thread A waits for Thread B to finish.
 *
 *
 * Barrier:
 *
 *     Multiple threads wait for EACH OTHER at a checkpoint.
 *
 *
 * Example:
 *
 *     join():
 *
 *     Main
 *       |
 *       +--> wait for Worker-1
 *       +--> wait for Worker-2
 *
 *
 *     Barrier:
 *
 *     Worker-1 ──┐
 *     Worker-2 ──┤
 *     Worker-3 ──┤--> all wait here
 *     Worker-4 ──┘
 *
 *                 ↓
 *
 *             continue
 *
 *
 * ------------------------------------------------------------
 * CONCEPTS
 * ------------------------------------------------------------
 *
 * - Barrier
 * - Thread coordination
 * - CyclicBarrier
 * - await()
 * - Multiple-thread synchronization
 * - Phased computation
 *
 *
 * ------------------------------------------------------------
 * WHY CyclicBarrier?
 * ------------------------------------------------------------
 *
 * Java provides:
 *
 *     java.util.concurrent.CyclicBarrier
 *
 * It is designed exactly for this type of problem.
 *
 *
 * Each worker calls:
 *
 *     barrier.await();
 *
 *
 * The worker waits until the required number of threads have
 * reached the barrier.
 *
 *
 * Once all required threads arrive:
 *
 *     The barrier opens.
 *
 * All waiting workers can continue.
 *
 *
 * ------------------------------------------------------------
 * WHY "CYCLIC"?
 * ------------------------------------------------------------
 *
 * The same barrier can be reused.
 *
 * Example:
 *
 *     Phase 1
 *        ↓
 *     Barrier
 *        ↓
 *     Phase 2
 *        ↓
 *     Barrier
 *        ↓
 *     Phase 3
 *
 *
 * This is useful for repeated multi-phase computation.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD EXAMPLES
 * ------------------------------------------------------------
 *
 * - Parallel data processing
 * - Game simulation
 * - Image processing
 * - Scientific computation
 * - Distributed-style workflows
 * - Batch processing
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change:
 *
 *     numberOfWorkers = 4
 *
 * to:
 *
 *     numberOfWorkers = 6
 *
 * Remember to create six workers.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Give each worker a different processing time.
 *
 * Example:
 *
 *     Worker-1 → 500 ms
 *     Worker-2 → 3000 ms
 *     Worker-3 → 1000 ms
 *     Worker-4 → 2000 ms
 *
 * Observe:
 *
 *     Fast workers reach the barrier first.
 *
 * They must still wait.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Remove:
 *
 *     barrier.await();
 *
 * Observe how workers immediately enter Phase 2 without
 * waiting for the others.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add a second barrier.
 *
 * Make the workflow:
 *
 *     Phase 1
 *       ↓
 *     Barrier
 *       ↓
 *     Phase 2
 *       ↓
 *     Barrier
 *       ↓
 *     Phase 3
 *
 *
 * This demonstrates why the barrier is "cyclic".
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a barrier?
 *
 * 2. How is a barrier different from join()?
 *
 * 3. What does CyclicBarrier.await() do?
 *
 * 4. Why is CyclicBarrier called "cyclic"?
 *
 * 5. What happens if one required worker never reaches the
 *    barrier?
 *
 * The other workers can remain waiting.
 *
 * 6. Can a barrier be reused?
 *
 * Yes.
 *
 * 7. What happens if a waiting thread is interrupted?
 *
 * The barrier can become broken and waiting threads may receive
 * BrokenBarrierException depending on the situation.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * join():
 *
 *     "Wait for that thread to finish."
 *
 *
 * Barrier:
 *
 *     "Wait until everyone reaches this checkpoint."
 *
 *
 * This distinction is extremely important.
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 22:
 *
 *     CountDownLatchScenario.java
 *
 * We will solve another real-world problem:
 *
 *     An application should start serving users only after
 *     several independent startup tasks are complete.
 *
 * ============================================================
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadCoordinationBarrier {

    /*
     * Number of workers participating in the barrier.
     */
    private static final int NUMBER_OF_WORKERS = 4;

    /*
     * Create a barrier that requires all four workers to arrive.
     *
     * The Runnable passed to CyclicBarrier is executed once
     * when the final required worker reaches the barrier.
     */
    private static final CyclicBarrier barrier =
            new CyclicBarrier(
                    NUMBER_OF_WORKERS,
                    () -> {

                        System.out.println(
                                "\n=== ALL WORKERS REACHED THE BARRIER ===\n"
                        );
                    }
            );

    static class DataWorker implements Runnable {

        private final int workerId;
        private final int processingTime;

        DataWorker(
                int workerId,
                int processingTime) {

            this.workerId = workerId;
            this.processingTime = processingTime;
        }

        @Override
        public void run() {

            try {

                /*
                 * ------------------------------------------------
                 * PHASE 1
                 * ------------------------------------------------
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " processing its data..."
                );

                Thread.sleep(processingTime);

                System.out.println(
                        "Worker-"
                                + workerId
                                + " completed Phase 1."
                );

                /*
                 * ------------------------------------------------
                 * BARRIER
                 * ------------------------------------------------
                 *
                 * Worker waits here until ALL four workers
                 * reach this point.
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " waiting at barrier."
                );

                barrier.await();

                /*
                 * ------------------------------------------------
                 * PHASE 2
                 * ------------------------------------------------
                 *
                 * This code cannot execute until the required
                 * number of workers reach the barrier.
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " starting Phase 2."
                );

            } catch (InterruptedException e) {

                /*
                 * Restore interruption status.
                 */
                Thread.currentThread().interrupt();

                System.out.println(
                        "Worker-"
                                + workerId
                                + " interrupted."
                );

            } catch (BrokenBarrierException e) {

                /*
                 * The barrier became broken.
                 *
                 * This can happen if another participating
                 * thread leaves/gets interrupted while waiting.
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " could not cross the barrier."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Different processing times demonstrate that some
         * workers reach the barrier earlier than others.
         */
        int[] processingTimes = {
                1000,
                3000,
                1500,
                2500
        };

        Thread[] workers =
                new Thread[NUMBER_OF_WORKERS];

        /*
         * Create workers.
         */
        for (int i = 0;
             i < NUMBER_OF_WORKERS;
             i++) {

            workers[i] =
                    new Thread(
                            new DataWorker(
                                    i + 1,
                                    processingTimes[i]
                            ),
                            "Worker-" + (i + 1)
                    );
        }

        /*
         * Start all workers.
         */
        for (Thread worker : workers) {

            worker.start();
        }

        /*
         * Wait until all workers finish.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "All workers completed."
        );
    }
}
