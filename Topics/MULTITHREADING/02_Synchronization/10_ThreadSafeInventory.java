/*
 * ============================================================
 * 10 - THREAD-SAFE INVENTORY
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine an e-commerce company with multiple warehouses.
 *
 * All warehouses update the inventory of the same product.
 *
 * Example:
 *
 *     Product: Laptop
 *     Initial Stock: 100
 *
 * At the same time:
 *
 *     Warehouse-A receives 20 laptops
 *     Warehouse-B receives 15 laptops
 *     Warehouse-C sells 10 laptops
 *
 * Multiple threads are modifying the SAME inventory object.
 *
 *
 * EXPECTED RESULT:
 * ------------------------------------------------------------
 *
 * Initial stock:
 *
 *     100
 *
 * Add:
 *
 *     +20
 *     +15
 *
 * Remove:
 *
 *     -10
 *
 * Final stock:
 *
 *     125
 *
 *
 * ------------------------------------------------------------
 * PROBLEM
 * ------------------------------------------------------------
 *
 * Operations such as:
 *
 *     stock += quantity
 *
 *     stock -= quantity
 *
 * are read-modify-write operations.
 *
 * They are not automatically atomic just because the variable
 * is an int.
 *
 *
 * Example:
 *
 *     stock = 100
 *
 * Thread A:
 *
 *     Read 100
 *     Add 20
 *
 * Thread B:
 *
 *     Read 100
 *     Add 15
 *
 * Thread A:
 *
 *     Write 120
 *
 * Thread B:
 *
 *     Write 115
 *
 *
 * Expected:
 *
 *     135
 *
 * Actual:
 *
 *     115
 *
 *
 * This is a LOST UPDATE.
 *
 *
 * ------------------------------------------------------------
 * SOLUTION
 * ------------------------------------------------------------
 *
 * Protect inventory modifications using synchronized.
 *
 *
 * IMPORTANT DESIGN PRINCIPLE:
 * ------------------------------------------------------------
 *
 * The inventory itself should control access to its state.
 *
 * Instead of allowing external threads to directly modify:
 *
 *     stock
 *
 * we provide controlled methods:
 *
 *     addStock()
 *     removeStock()
 *
 *
 * This is an example of combining:
 *
 *     Encapsulation
 *          +
 *     Thread Safety
 *
 *
 * ------------------------------------------------------------
 * THREAD-SAFE CLASS
 * ------------------------------------------------------------
 *
 * A class is thread-safe when its behavior remains correct
 * when accessed concurrently by multiple threads.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Remove synchronized from:
 *
 *     addStock()
 *     removeStock()
 *
 * Run the program several times.
 *
 * Compare the final result with the expected result.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Increase:
 *
 *     number of warehouse threads
 *
 * to:
 *
 *     100
 *
 * Give each warehouse thousands of operations.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Add a method:
 *
 *     getStock()
 *
 * and access it concurrently.
 *
 * Ask:
 *
 *     Does reading shared data always require synchronization?
 *
 * The answer depends on the consistency/visibility requirements
 * and the design of the class.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 4
 * ------------------------------------------------------------
 *
 * Add a business rule:
 *
 *     Stock can never become negative.
 *
 * Example:
 *
 *     removeStock(200)
 *
 * when stock is only 100 should fail.
 *
 * Now think:
 *
 *     Should the CHECK and UPDATE be protected together?
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. What is a thread-safe class?
 *
 * 2. Why should shared state be private?
 *
 * 3. Why is stock += quantity not guaranteed to be atomic?
 *
 * 4. What is a lost update?
 *
 * 5. Why should check + update be protected together?
 *
 * 6. Does synchronized make the entire application
 *    single-threaded?
 *
 * No.
 *
 * It only serializes threads competing for the SAME monitor.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 * Thread safety is not only about using synchronized.
 *
 * Good thread-safe design combines:
 *
 *     Encapsulation
 *          +
 *     Controlled access
 *          +
 *     Proper synchronization
 *          +
 *     Small critical sections
 *
 *
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 11:
 *
 *     StaticResource.java
 *
 * We will learn an important difference:
 *
 *     synchronized instance method
 *
 * vs
 *
 *     synchronized static method
 *
 * and understand why they lock DIFFERENT objects.
 *
 * ============================================================
 */

public class ThreadSafeInventory {

    static class Inventory {

        /*
         * Shared mutable state.
         */
        private int stock = 100;

        /*
         * synchronized protects the stock modification.
         *
         * The monitor used here is the Inventory object itself.
         */
        public synchronized void addStock(
                int quantity,
                String warehouse) {

            System.out.println(
                    warehouse
                            + " adding "
                            + quantity
                            + " units."
            );

            /*
             * Simulate warehouse processing.
             */
            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                return;
            }

            stock += quantity;

            System.out.println(
                    warehouse
                            + " completed addition."
                            + " Current stock: "
                            + stock
            );
        }

        /*
         * synchronized protects:
         *
         *     Check stock
         *         +
         *     Remove stock
         *
         * as one logical operation.
         */
        public synchronized boolean removeStock(
                int quantity,
                String warehouse) {

            System.out.println(
                    warehouse
                            + " wants to remove "
                            + quantity
                            + " units."
            );

            /*
             * Business rule:
             *
             * Stock must never become negative.
             */
            if (stock < quantity) {

                System.out.println(
                        warehouse
                                + " failed. Not enough stock."
                );

                return false;
            }

            /*
             * Simulate processing delay.
             */
            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                return false;
            }

            /*
             * Update shared state.
             */
            stock -= quantity;

            System.out.println(
                    warehouse
                            + " removed "
                            + quantity
                            + " units."
                            + " Current stock: "
                            + stock
            );

            return true;
        }

        public synchronized int getStock() {

            return stock;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * One shared inventory object.
         */
        Inventory inventory =
                new Inventory();

        /*
         * Warehouse A adds 20 units.
         */
        Thread warehouseA =
                new Thread(() ->
                        inventory.addStock(
                                20,
                                "Warehouse-A"
                        )
                );

        /*
         * Warehouse B adds 15 units.
         */
        Thread warehouseB =
                new Thread(() ->
                        inventory.addStock(
                                15,
                                "Warehouse-B"
                        )
                );

        /*
         * Warehouse C removes 10 units.
         */
        Thread warehouseC =
                new Thread(() ->
                        inventory.removeStock(
                                10,
                                "Warehouse-C"
                        )
                );

        /*
         * Start all warehouse operations concurrently.
         */
        warehouseA.start();
        warehouseB.start();
        warehouseC.start();

        /*
         * Wait for all operations to finish.
         */
        warehouseA.join();
        warehouseB.join();
        warehouseC.join();

        /*
         * Initial = 100
         *
         * +20
         * +15
         * -10
         *
         * Expected = 125
         */
        System.out.println(
                "Final inventory: "
                        + inventory.getStock()
        );
    }
}
