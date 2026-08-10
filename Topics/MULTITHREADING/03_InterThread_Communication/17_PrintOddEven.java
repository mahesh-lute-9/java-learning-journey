/*
 * ============================================================
 * 17 - ODD-EVEN NUMBER PRINTING USING THREAD COMMUNICATION
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a reporting system where two worker threads are
 * responsible for printing numbers.
 *
 * Thread 1:
 *
 *     Prints ODD numbers
 *
 * Thread 2:
 *
 *     Prints EVEN numbers
 *
 * We want the final output to be:
 *
 *     1 2 3 4 5 6 7 8 9 10
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * If both threads simply print numbers independently:
 *
 *     OddThread
 *     EvenThread
 *
 * the output order is not guaranteed.
 *
 * We might get:
 *
 *     1 3 2 4 5 7 6 8 ...
 *
 * But our requirement is strict:
 *
 *     1 2 3 4 5 6 7 8 ...
 *
 *
 * Therefore, the threads must COORDINATE with each other.
 *
 *
 * ------------------------------------------------------------
 * REQUIRED BEHAVIOR
 * ------------------------------------------------------------
 *
 * Odd thread:
 *
 *     Print 1
 *     Wait
 *
 * Even thread:
 *
 *     Print 2
 *     Wait
 *
 * Odd thread:
 *
 *     Print 3
 *     Wait
 *
 * Even thread:
 *
 *     Print 4
 *     Wait
 *
 * and so on.
 *
 *
 * ------------------------------------------------------------
 * THREAD COMMUNICATION
 * ------------------------------------------------------------
 *
 * We can use:
 *
 *     wait()
 *     notifyAll()
 *
 * The shared object acts as the monitor.
 *
 *
 * ------------------------------------------------------------
 * VISUALIZATION
 * ------------------------------------------------------------
 *
 *                 Shared Number
 *                      |
 *                      v
 *
 *                Current = 1
 *
 *              +-------+-------+
 *              |               |
 *              v               v
 *         Odd Thread       Even Thread
 *
 *         Current odd?     Current even?
 *              |               |
 *            YES               NO
 *              |               |
 *            print             wait
 *              |               |
 *            ++                |
 *              |               |
 *           notifyAll() -------+
 *
 *
 * ------------------------------------------------------------
 * WHY IS while() USED?
 * ------------------------------------------------------------
 *
 * The thread must verify that it is actually its turn after
 * waking up.
 *
 * Example:
 *
 *     while (number % 2 == 0) {
 *         wait();
 *     }
 *
 * This means:
 *
 *     "I am the odd thread. If the current number is not odd,
 *      keep waiting."
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * wait() and notifyAll() coordinate the threads.
 *
 * They do NOT determine which thread should print.
 *
 * The shared condition:
 *
 *     number % 2
 *
 * determines whose turn it is.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Change the limit:
 *
 *     10
 *
 * to:
 *
 *     20
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Remove the while condition and replace it with if.
 *
 * Think about why this is unsafe.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Remove synchronization and wait/notify completely.
 *
 * Let both threads print independently.
 *
 * Observe how the ordering becomes unpredictable.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Modify the program so:
 *
 *     OddThread prints:
 *         1 3 5 7 9
 *
 *     EvenThread prints:
 *         2 4 6 8 10
 *
 * while still maintaining the final sequence:
 *
 *     1 2 3 4 5 6 7 8 9 10
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why can't two independent threads guarantee ordered
 *    output?
 *
 * 2. How does wait() help here?
 *
 * 3. Why is the shared number important?
 *
 * 4. Why do we need synchronized?
 *
 * 5. Why should the condition be checked in a while loop?
 *
 * 6. What would happen if notifyAll() were removed?
 *
 * The waiting thread may never be awakened.
 *
 * 7. Can this problem be solved using Lock and Condition?
 *
 * Yes.
 *
 * We will learn that later.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Inter-thread communication is not just:
 *
 *     "wake another thread."
 *
 * It is:
 *
 *     Shared State
 *          +
 *     Condition
 *          +
 *     wait()
 *          +
 *     notifyAll()
 *
 *
 * The general pattern is:
 *
 *     while (condition is false) {
 *         wait();
 *     }
 *
 *     perform work;
 *
 *     update state;
 *
 *     notifyAll();
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 18:
 *
 *     ThreadCommunication.java
 *
 * We will build a realistic job-processing workflow where one
 * thread produces a result and another thread must wait for
 * that result.
 *
 * ============================================================
 */

public class PrintOddEven {

    /*
     * Shared coordination object.
     */
    static class NumberPrinter {

        /*
         * This is the shared state.
         *
         * Both threads access it.
         */
        private int number = 1;

        private final int limit;

        NumberPrinter(int limit) {
            this.limit = limit;
        }

        /*
         * Called by the ODD thread.
         */
        public synchronized void printOdd()
                throws InterruptedException {

            while (number <= limit) {

                /*
                 * If the current number is even, the odd
                 * thread must wait.
                 */
                while (number % 2 == 0) {

                    wait();
                }

                /*
                 * The number may have changed while waiting,
                 * so check the limit again.
                 */
                if (number > limit) {

                    notifyAll();

                    return;
                }

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> "
                                + number
                );

                /*
                 * Move to the next number.
                 */
                number++;

                /*
                 * Wake the even thread.
                 */
                notifyAll();
            }
        }

        /*
         * Called by the EVEN thread.
         */
        public synchronized void printEven()
                throws InterruptedException {

            while (number <= limit) {

                /*
                 * If the current number is odd, the even
                 * thread must wait.
                 */
                while (number % 2 != 0) {

                    wait();
                }

                /*
                 * The number may have changed while waiting,
                 * so check the limit again.
                 */
                if (number > limit) {

                    notifyAll();

                    return;
                }

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> "
                                + number
                );

                /*
                 * Move to the next number.
                 */
                number++;

                /*
                 * Wake the odd thread.
                 */
                notifyAll();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Create ONE shared NumberPrinter.
         *
         * Both threads must work on the SAME object.
         */
        NumberPrinter printer =
                new NumberPrinter(10);

        /*
         * Thread responsible for odd numbers.
         */
        Thread oddThread =
                new Thread(
                        () -> {

                            try {

                                printer.printOdd();

                            } catch (InterruptedException e) {

                                Thread.currentThread()
                                        .interrupt();
                            }

                        },
                        "Odd-Thread"
                );

        /*
         * Thread responsible for even numbers.
         */
        Thread evenThread =
                new Thread(
                        () -> {

                            try {

                                printer.printEven();

                            } catch (InterruptedException e) {

                                Thread.currentThread()
                                        .interrupt();
                            }

                        },
                        "Even-Thread"
                );

        /*
         * Start both threads.
         */
        oddThread.start();
        evenThread.start();

        /*
         * Wait until both threads finish.
         */
        oddThread.join();
        evenThread.join();

        System.out.println(
                "Printing completed."
        );
    }
}
