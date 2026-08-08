/*
 * ============================================================
 * 09 - TICKET BOOKING USING synchronized BLOCK
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an online movie-ticket booking system.
 *
 * There is only ONE seat remaining:
 *
 *     Seat A1
 *
 * Two customers try to book the same seat at almost exactly
 * the same time.
 *
 *     Customer-1 → wants Seat A1
 *     Customer-2 → wants Seat A1
 *
 *
 * EXPECTED RESULT:
 * ------------------------------------------------------------
 *
 * Only ONE customer should successfully book the seat.
 *
 * The other customer should receive:
 *
 *     "Seat unavailable"
 *
 *
 * ------------------------------------------------------------
 * THE PROBLEM
 * ------------------------------------------------------------
 *
 * Booking a seat is NOT just one operation.
 *
 * It consists of:
 *
 *     1. Check whether the seat is available.
 *     2. If available, reserve the seat.
 *
 *
 * These operations must be treated as ONE logical operation.
 *
 *
 * BAD APPROACH:
 * ------------------------------------------------------------
 *
 * if (seatAvailable) {
 *
 *     // some delay
 *
 *     bookSeat();
 * }
 *
 *
 * Two threads can execute:
 *
 *
 * Customer-1                    Customer-2
 * -----------                   -----------
 *
 * Check seat → available
 *                               Check seat → available
 *
 * Book seat
 *                               Book seat
 *
 *
 * Now BOTH customers think they successfully booked the same
 * seat.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Protect the complete CHECK + BOOK operation using:
 *
 *     synchronized
 *
 *
 * Here we intentionally use a synchronized BLOCK instead of a
 * synchronized method.
 *
 *
 * WHY A synchronized BLOCK?
 * ------------------------------------------------------------
 *
 * Synchronizing an entire method may protect more code than
 * necessary.
 *
 * For example:
 *
 *     synchronized method
 *     {
 *         log()
 *         validate()
 *         checkSeat()
 *         bookSeat()
 *         sendNotification()
 *     }
 *
 * Maybe only:
 *
 *     checkSeat()
 *     bookSeat()
 *
 * need synchronization.
 *
 * A synchronized block allows us to protect only the critical
 * section.
 *
 *
 * ------------------------------------------------------------
 * CRITICAL SECTION
 * ------------------------------------------------------------
 *
 * The critical section is:
 *
 *     Check availability
 *          +
 *     Reserve seat
 *
 * These operations must happen atomically from the perspective
 * of competing booking threads.
 *
 *
 * ------------------------------------------------------------
 * VISUALIZATION
 * ------------------------------------------------------------
 *
 *              Shared Seat
 *                   |
 *             synchronized
 *                   |
 *          +--------+--------+
 *          |                 |
 *          v                 v
 *     Customer-1        Customer-2
 *          |
 *       Check
 *          |
 *       Reserve
 *          |
 *       Unlock
 *                            |
 *                            v
 *                         Check
 *                            |
 *                       Unavailable
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove synchronized from the block.
 *
 * Run the program multiple times.
 *
 * You may observe both customers getting a successful booking.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Increase the artificial delay:
 *
 *     Thread.sleep(1000);
 *
 * This makes the race window larger.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Create 10 customers competing for 3 seats.
 *
 * Ask yourself:
 *
 *     How many customers should succeed?
 *
 * Answer:
 *
 *     Maximum 3.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Move the Thread.sleep() outside the synchronized block.
 *
 * Observe the difference.
 *
 * Think about why keeping the critical section small is
 * important for performance.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Why is checking and booking treated as one critical
 *    section?
 *
 * 2. Why is a synchronized block useful here?
 *
 * 3. What object are we synchronizing on?
 *
 * 4. Why should we avoid synchronizing unnecessarily large
 *    sections of code?
 *
 * 5. What problem occurs if the check and update are not
 *    protected together?
 *
 * 6. Is this problem only about visibility?
 *
 * No.
 *
 * It is primarily an atomicity/mutual exclusion problem.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Synchronization is not about randomly putting the
 * synchronized keyword everywhere.
 *
 * First identify:
 *
 *     SHARED STATE
 *          ↓
 *     CRITICAL SECTION
 *          ↓
 *     PROTECT THE WHOLE OPERATION
 *
 *
 * Real-world examples:
 *
 *     Check balance + withdraw
 *     Check seat + book
 *     Check stock + purchase
 *     Check username + create account
 *
 *
 * ------------------------------------------------------------
 * NEXT
 * ------------------------------------------------------------
 *
 * Program 10:
 *
 *     ThreadSafeInventory.java
 *
 * We will move from a single seat to a shared product inventory
 * accessed by multiple warehouse workers.
 *
 * ============================================================
 */

public class TicketBooking {

    static class TicketCounter {

        /*
         * Shared resource.
         *
         * Only one seat is available.
         */
        private boolean seatAvailable = true;

        public void bookSeat(String customerName) {

            /*
             * Only the check + booking operation needs to be
             * protected.
             */
            synchronized (this) {

                System.out.println(
                        customerName
                                + " is checking seat availability..."
                );

                /*
                 * Artificial delay to make the race condition
                 * easier to understand.
                 */
                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    return;
                }

                /*
                 * Check and update are protected by the SAME
                 * monitor.
                 */
                if (seatAvailable) {

                    /*
                     * Reserve the seat.
                     */
                    seatAvailable = false;

                    System.out.println(
                            customerName
                                    + " successfully booked Seat A1."
                    );

                } else {

                    System.out.println(
                            customerName
                                    + " failed. Seat A1 is already booked."
                    );
                }
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * One shared ticket counter.
         */
        TicketCounter counter =
                new TicketCounter();

        /*
         * Two customers trying to book the SAME seat.
         */
        Thread customer1 =
                new Thread(() ->
                        counter.bookSeat(
                                "Customer-1"
                        )
                );

        Thread customer2 =
                new Thread(() ->
                        counter.bookSeat(
                                "Customer-2"
                        )
                );

        /*
         * Start both booking requests.
         */
        customer1.start();
        customer2.start();

        /*
         * Wait for both booking operations to finish.
         */
        customer1.join();
        customer2.join();

        System.out.println(
                "Booking process completed."
        );
    }
}
