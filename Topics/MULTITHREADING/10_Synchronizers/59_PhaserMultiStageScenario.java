/*
 * ============================================================
 * 59 - Phaser FOR MULTI-STAGE WORK
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a large data-processing application.
 *
 * Several workers process data in multiple stages:
 *
 *
 *     Stage 1 → Download data
 *          ↓
 *     Stage 2 → Process data
 *          ↓
 *     Stage 3 → Save results
 *
 *
 * But there is an additional requirement:
 *
 *     Workers may JOIN or LEAVE the process dynamically.
 *
 *
 * Example:
 *
 *     Initially:
 *
 *         Worker-1
 *         Worker-2
 *         Worker-3
 *
 *
 *     Later:
 *
 *         Worker-4 joins.
 *
 *
 *     Worker-3 finishes permanently.
 *
 *
 * This is where Phaser becomes useful.
 *
 *
 * ------------------------------------------------------------
 * WHAT IS Phaser?
 * ------------------------------------------------------------
 *
 * Phaser is a reusable synchronization mechanism designed for
 * coordinating threads across multiple phases.
 *
 *
 * It combines ideas found in:
 *
 *     CountDownLatch
 *     CyclicBarrier
 *
 *
 * while also allowing participants to be registered and
 * deregistered dynamically.
 *
 *
 * ------------------------------------------------------------
 * BASIC IDEA
 * ------------------------------------------------------------
 *
 *
 * Phaser
 *    ↓
 * Phase 0
 *    ↓
 * Phase 1
 *    ↓
 * Phase 2
 *    ↓
 * Phase 3
 *
 *
 * Threads can synchronize at each phase.
 *
 *
 * ------------------------------------------------------------
 * PARTICIPANTS
 * ------------------------------------------------------------
 *
 * A Phaser keeps track of registered parties.
 *
 *
 * Register:
 *
 *     register()
 *
 *
 * Multiple registrations:
 *
 *     bulkRegister(n)
 *
 *
 * Remove a participant:
 *
 *     arriveAndDeregister()
 *
 *
 * ------------------------------------------------------------
 * ARRIVING
 * ------------------------------------------------------------
 *
 * A thread can signal that it has reached the current phase:
 *
 *
 *     arrive()
 *
 *
 * Or:
 *
 *
 *     arriveAndAwaitAdvance()
 *
 *
 * Meaning:
 *
 *     "I am done with this phase, and I will wait until the
 *      other registered participants arrive too."
 *
 *
 * ------------------------------------------------------------
 * WHY IS IT CALLED PHASER?
 * ------------------------------------------------------------
 *
 * Because work progresses through phases.
 *
 *
 * Example:
 *
 *
 *     Phase 0
 *        ↓
 *     all workers arrive
 *        ↓
 *     Phase 1
 *        ↓
 *     all workers arrive
 *        ↓
 *     Phase 2
 *
 *
 * ------------------------------------------------------------
 * COUNTDOWNLATCH VS CYCLICBARRIER VS PHASER
 * ------------------------------------------------------------
 *
 *
 * CountDownLatch:
 *
 *     Fixed countdown
 *     One-shot
 *
 *
 * CyclicBarrier:
 *
 *     Fixed number of parties
 *     Reusable
 *
 *
 * Phaser:
 *
 *     Reusable
 *     Multiple phases
 *     Dynamic registration/deregistration
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     Multi-stage data processing
 *     Parallel simulations
 *     Batch pipelines
 *     Game simulations
 *     Iterative algorithms
 *     Dynamic worker systems
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT METHODS
 * ------------------------------------------------------------
 *
 *
 * register()
 *
 *     Add one participant.
 *
 *
 * bulkRegister(n)
 *
 *     Add multiple participants.
 *
 *
 * arrive()
 *
 *     Signal arrival without waiting.
 *
 *
 * arriveAndAwaitAdvance()
 *
 *     Signal arrival and wait for the phase to advance.
 *
 *
 * arriveAndDeregister()
 *
 *     Signal arrival and permanently leave the Phaser.
 *
 *
 * getPhase()
 *
 *     Get current phase number.
 *
 *
 * getRegisteredParties()
 *
 *     Number of registered parties.
 *
 *
 * ------------------------------------------------------------
 * TERMINATION
 * ------------------------------------------------------------
 *
 * A Phaser can be terminated.
 *
 *
 * One common way is to override:
 *
 *
 *     onAdvance()
 *
 *
 * and return true when the desired number of phases has been
 * completed.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     WORKER_COUNT
 *
 * Observe that every registered worker participates in each
 * phase.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Make one worker deregister after Phase 1.
 *
 *
 * Observe that later phases no longer wait for that worker.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add a new worker dynamically.
 *
 *
 * Register it before the next phase.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is Phaser?
 *
 * A reusable synchronization mechanism for coordinating
 * multiple threads across phases.
 *
 *
 * 2. Why is Phaser more flexible than CyclicBarrier?
 *
 * Participants can be registered and deregistered dynamically.
 *
 *
 * 3. What does arriveAndAwaitAdvance() do?
 *
 * Signals that the current party has arrived and waits for the
 * phase to advance.
 *
 *
 * 4. What does arriveAndDeregister() do?
 *
 * Signals arrival and removes the party from future phases.
 *
 *
 * 5. Can Phaser be reused?
 *
 * Yes.
 *
 *
 * 6. When would you prefer Phaser?
 *
 * When the number of participants can change across multiple
 * phases.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * CountDownLatch
 *     ↓
 * One-time countdown
 *
 *
 * CyclicBarrier
 *     ↓
 * Reusable fixed-party barrier
 *
 *
 * Phaser
 *     ↓
 * Reusable + multi-phase + dynamic parties
 *
 *
 * ============================================================
 * NEXT:
 * ============================================================
 *
 * Program 60:
 *
 *     CompletableFutureTimeoutFallback.java
 *
 * We will return to asynchronous programming and build a
 * realistic API aggregation scenario with:
 *
 *     Timeout
 *     Fallback
 *     Exception handling
 *
 * ============================================================
 */

import java.util.concurrent.Phaser;

public class PhaserMultiStageScenario {

    /*
     * Number of initial workers.
     */
    private static final int WORKER_COUNT = 4;

    /*
     * Number of processing phases.
     */
    private static final int PHASE_COUNT = 3;

    /*
     * Custom Phaser.
     *
     * onAdvance() decides when the Phaser should terminate.
     */
    private static class ProcessingPhaser
            extends Phaser {

        @Override
        protected boolean onAdvance(
                int phase,
                int registeredParties) {

            /*
             * Terminate when:
             *
             *     PHASE_COUNT phases
             *
             * have completed.
             *
             * Also terminate if no parties remain.
             */
            return phase + 1 >= PHASE_COUNT
                    || registeredParties == 0;
        }
    }

    /*
     * Shared Phaser.
     */
    private static final ProcessingPhaser phaser =
            new ProcessingPhaser();

    /*
     * Simulate work for one phase.
     */
    private static void performPhaseWork(
            int workerId,
            int phase) {

        System.out.println(
                "Worker-"
                        + workerId
                        + " processing Phase "
                        + phase
        );

        try {

            /*
             * Give workers different processing times.
             */
            Thread.sleep(
                    500L
                            + workerId * 200L
            );

        } catch (InterruptedException e) {

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
     * Worker performs all phases.
     */
    private static void runWorker(
            int workerId) {

        try {

            for (int phase = 0;
                 phase < PHASE_COUNT;
                 phase++) {

                /*
                 * Work using the current phase number.
                 *
                 * Phaser starts at phase 0.
                 */
                performPhaseWork(
                        workerId,
                        phase + 1
                );

                /*
                 * Signal that this worker has completed the
                 * current phase and wait for all other registered
                 * workers.
                 */
                int previousPhase =
                        phaser
                                .arriveAndAwaitAdvance();

                System.out.println(
                        "Worker-"
                                + workerId
                                + " passed Phaser phase "
                                + previousPhase
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Worker-"
                            + workerId
                            + " stopped: "
                            + e.getMessage()
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Register all workers before they start.
         */
        phaser.bulkRegister(
                WORKER_COUNT
        );

        System.out.println(
                "Registered parties: "
                        + phaser.getRegisteredParties()
        );

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
         * Wait for every worker.
         */
        for (Thread worker : workers) {

            worker.join();
        }

        System.out.println(
                "\n================================"
        );

        System.out.println(
                "Processing completed."
        );

        System.out.println(
                "Current phase: "
                        + phaser.getPhase()
        );

        System.out.println(
                "Registered parties: "
                        + phaser.getRegisteredParties()
        );

        System.out.println(
                "Phaser terminated: "
                        + phaser.isTerminated()
        );

        System.out.println(
                "================================"
        );
    }
}
