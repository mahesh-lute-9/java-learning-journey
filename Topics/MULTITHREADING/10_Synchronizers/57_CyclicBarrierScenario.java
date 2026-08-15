/*
 * ============================================================
 * 57 - CyclicBarrier FOR MULTI-PHASE WORK
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a data-processing system.
 *
 * We have 4 workers processing the same dataset.
 *
 * The processing has multiple phases:
 *
 *
 *     Phase 1: Load data
 *          ↓
 *     CHECKPOINT
 *          ↓
 *     Phase 2: Process data
 *          ↓
 *     CHECKPOINT
 *          ↓
 *     Phase 3: Generate result
 *
 *
 * Requirement:
 *
 *     No worker should start the next phase until ALL workers
 *     have completed the current phase.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     CyclicBarrier
 *
 *
 * A CyclicBarrier allows a fixed number of threads to wait for
 * one another at a common synchronization point.
 *
 *
 * Example:
 *
 *     CyclicBarrier barrier =
 *         new CyclicBarrier(4);
 *
 *
 * Four workers must call:
 *
 *     barrier.await();
 *
 *
 * before the barrier opens.
 *
 *
 * ------------------------------------------------------------
 * FLOW
 * ------------------------------------------------------------
 *
 *
 * Worker 1 ───── Phase 1 ─────┐
 * Worker 2 ───── Phase 1 ─────┤
 * Worker 3 ───── Phase 1 ─────┤
 * Worker 4 ───── Phase 1 ─────┘
 *                              ↓
 *                         BARRIER #1
 *                              ↓
 *       Everyone continues to Phase 2
 *
 *
 * Worker 1 ───── Phase 2 ─────┐
 * Worker 2 ───── Phase 2 ─────┤
 * Worker 3 ───── Phase 2 ─────┤
 * Worker 4 ───── Phase 2 ─────┘
 *                              ↓
 *                         BARRIER #2
 *
 *
 * The same barrier can be reused.
 *
 *
 * ------------------------------------------------------------
 * WHY "CYCLIC"?
 * ------------------------------------------------------------
 *
 * Because after the barrier opens, it can be used again.
 *
 *
 * Example:
 *
 *
 *     Round 1
 *        ↓
 *     barrier
 *        ↓
 *     Round 2
 *        ↓
 *     barrier
 *        ↓
 *     Round 3
 *
 *
 * ------------------------------------------------------------
 * CountDownLatch VS CyclicBarrier
 * ------------------------------------------------------------
 *
 *
 * CountDownLatch:
 *
 *     One or more threads wait for events.
 *
 *     countDown()
 *         ↓
 *     count reaches zero
 *         ↓
 *     waiting threads continue
 *
 *     Cannot be reset.
 *
 *
 * ------------------------------------------------------------
 *
 *
 * CyclicBarrier:
 *
 *     A group of threads wait for EACH OTHER.
 *
 *     worker → await()
 *     worker → await()
 *     worker → await()
 *     worker → await()
 *              ↓
 *        barrier opens
 *
 *     Can be reused.
 *
 *
 * ------------------------------------------------------------
 * BARRIER ACTION
 * ------------------------------------------------------------
 *
 * CyclicBarrier can optionally execute an action when the
 * barrier is reached.
 *
 *
 * Example:
 *
 *
 *     new CyclicBarrier(
 *         4,
 *         () -> {
 *             System.out.println(
 *                 "Phase completed!"
 *             );
 *         }
 *     );
 *
 *
 * This can be useful for:
 *
 *     Combining intermediate results
 *     Logging phase completion
 *     Triggering the next stage
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * If one participating thread fails to reach the barrier,
 * other threads may remain waiting.
 *
 *
 * That's why production code should consider:
 *
 *     await(timeout)
 *     interruption
 *     BrokenBarrierException
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Parallel simulations
 *     Scientific calculations
 *     Multi-stage data processing
 *     Game turns
 *     Batch processing
 *     Parallel algorithms
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     WORKER_COUNT = 6
 *
 *
 * Observe that all six workers must reach the barrier.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make one worker significantly slower.
 *
 *
 * Observe that the other workers wait at the barrier.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add a third phase.
 *
 *
 * Observe why the barrier is called cyclic.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     barrier.await()
 *
 * with:
 *
 *     barrier.await(
 *         2,
 *         TimeUnit.SECONDS
 *     );
 *
 *
 * Observe timeout-related behavior.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is CyclicBarrier?
 *
 * A synchronization aid that allows a group of threads to wait
 * for one another at a common barrier point.
 *
 *
 * 2. Why is it called cyclic?
 *
 * Because the barrier can be reused after it is released.
 *
 *
 * 3. How does it differ from CountDownLatch?
 *
 * CountDownLatch is generally one-shot and represents a
 * countdown of events.
 *
 * CyclicBarrier coordinates a fixed group of threads reaching
 * a common point and can be reused.
 *
 *
 * 4. What does await() do?
 *
 * Makes the current thread wait until all parties reach the
 * barrier.
 *
 *
 * 5. What is a barrier action?
 *
 * An optional action executed when the required parties reach
 * the barrier.
 *
 *
 * 6. What can happen if one worker never reaches the barrier?
 *
 * Other workers may remain waiting unless timeout/interruption
 * or another failure mechanism breaks the barrier.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * CountDownLatch:
 *
 *     "Wait until these events finish."
 *
 *
 * CyclicBarrier:
 *
 *     "Everyone wait here before we continue."
 *
 *
 * Think:
 *
 *
 *     Worker A ──┐
 *     Worker B ──┤
 *     Worker C ──┤──→ BARRIER → next phase
 *     Worker D ──┘
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 58:
 *
 *     SemaphoreResourcePool.java
 *
 * Scenario:
 *
 *     An application has only 3 database connections available,
 *     but 10 worker threads need to use them.
 *
 * We will use Semaphore as a resource pool limiter.
 *
 * ============================================================
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierScenario {

    /*
     * Number of workers participating in every phase.
     */
    private static final int WORKER_COUNT = 4;

    /*
     * Number of processing phases.
     */
    private static final int PHASE_COUNT = 3;

    /*
     * All workers must reach this barrier before any of them
     * can continue to the next phase.
     *
     * The barrier action runs when the final worker reaches it.
     */
    private static final CyclicBarrier barrier =
            new CyclicBarrier(
                    WORKER_COUNT,
                    () -> {

                        System.out.println(
                                "\n>>> ALL WORKERS REACHED "
                                        + "THE BARRIER <<<"
                        );

                        System.out.println(
                                ">>> Moving to next phase...\n"
                        );
                    }
            );

    /*
     * Simulate work performed during a phase.
     */
    private static void performPhase(
            int workerId,
            int phase) {

        System.out.println(
                "Worker-"
                        + workerId
                        + " started Phase "
                        + phase
        );

        try {

            /*
             * Give each worker a slightly different processing
             * time so that we can observe the synchronization.
             */
            Thread.sleep(
                    500L
                            + (workerId * 300L)
            );

        } catch (InterruptedException e) {

            /*
             * Restore interruption status.
             */
            Thread.currentThread()
                    .interrupt();

            throw new RuntimeException(
                    "Worker interrupted.",
                    e
            );
        }

        System.out.println(
                "Worker-"
                        + workerId
                        + " completed Phase "
                        + phase
        );
    }

    /*
     * Execute all processing phases for one worker.
     */
    private static void runWorker(
            int workerId) {

        try {

            for (int phase = 1;
                 phase <= PHASE_COUNT;
                 phase++) {

                /*
                 * Perform this worker's part of the phase.
                 */
                performPhase(
                        workerId,
                        phase
                );

                System.out.println(
                        "Worker-"
                                + workerId
                                + " waiting at barrier "
                                + "after Phase "
                                + phase
                );

                /*
                 * Wait until every worker has completed the
                 * current phase.
                 */
                barrier.await();

                /*
                 * This line executes only after all workers
                 * have reached the barrier.
                 */
                System.out.println(
                        "Worker-"
                                + workerId
                                + " passed barrier "
                                + "for Phase "
                                + phase
                );
            }

        } catch (InterruptedException e) {

            /*
             * Restore interruption status.
             */
            Thread.currentThread()
                    .interrupt();

            System.out.println(
                    "Worker-"
                            + workerId
                            + " interrupted."
            );

        } catch (BrokenBarrierException e) {

            /*
             * The barrier became broken because a participating
             * thread failed/interrupted or the barrier was reset.
             */
            System.out.println(
                    "Worker-"
                            + workerId
                            + " detected a broken barrier."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create workers.
         */
        Thread[] workers =
                new Thread[WORKER_COUNT];

        for (int i = 0;
             i < WORKER_COUNT;
             i++) {

            final int workerId =
                    i + 1;

            workers[i] =
                    new Thread(
                            () ->
                                    runWorker(
                                            workerId
                                    ),
                            "Worker-"
                                    + workerId
                    );

            workers[i].start();
        }

        /*
         * Wait for all workers to finish all phases.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "\nAll workers completed all phases."
        );
    }
}
