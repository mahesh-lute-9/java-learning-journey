/*
 * ============================================================
 * 38 - CompletableFuture PIPELINE
 * ============================================================
 *
 * SCENARIO:
 * ------------------------------------------------------------
 * Imagine a backend API that needs to build a user's profile.
 *
 * The workflow is:
 *
 *
 *     User ID
 *       |
 *       v
 *   Fetch User
 *       |
 *       v
 *   Get Account ID
 *       |
 *       v
 *   Fetch Account
 *       |
 *       v
 *   Build Profile
 *
 *
 * The important part is:
 *
 *     The second asynchronous operation depends on the result
 *     of the first operation.
 *
 *
 * ------------------------------------------------------------
 * WHY THIS IS DIFFERENT
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     fetchUser()
 *
 * returns:
 *
 *     CompletableFuture<User>
 *
 *
 * Then we need to use the returned User to call:
 *
 *     fetchAccount(user.accountId)
 *
 *
 * which itself returns:
 *
 *     CompletableFuture<Account>
 *
 *
 * This is where:
 *
 *     thenCompose()
 *
 * becomes important.
 *
 *
 * ------------------------------------------------------------
 * thenApply() VS thenCompose()
 * ------------------------------------------------------------
 *
 *
 * thenApply():
 *
 *     Transform a value into another value.
 *
 *
 *     User
 *       ↓
 *     String
 *
 *
 * Result:
 *
 *     CompletableFuture<String>
 *
 *
 * ------------------------------------------------------------
 *
 * thenCompose():
 *
 *     Transform a value into another CompletableFuture and
 *     flatten the nested future.
 *
 *
 *     User
 *       ↓
 *     CompletableFuture<Account>
 *
 *
 * Without compose:
 *
 *     CompletableFuture<
 *         CompletableFuture<Account>
 *     >
 *
 *
 * With compose:
 *
 *     CompletableFuture<Account>
 *
 *
 * ------------------------------------------------------------
 * EASY WAY TO REMEMBER
 * ------------------------------------------------------------
 *
 *
 * thenApply():
 *
 *     "I have a value.
 *      Transform it."
 *
 *
 * thenCompose():
 *
 *     "I have a value.
 *      Use it to start another asynchronous operation."
 *
 *
 * ------------------------------------------------------------
 * EXAMPLE
 * ------------------------------------------------------------
 *
 *
 * CompletableFuture<User> userFuture =
 *     fetchUser();
 *
 *
 * CompletableFuture<Account> accountFuture =
 *     userFuture.thenCompose(
 *
 *         user -> fetchAccount(user.id)
 *
 *     );
 *
 *
 * This creates a clean asynchronous chain.
 *
 *
 * ------------------------------------------------------------
 * REAL-WORLD USE CASES
 * ------------------------------------------------------------
 *
 *     User → Account
 *     Order → Payment
 *     Product → Inventory
 *     Student → Marks
 *     Employee → Payroll
 *     GitHub User → Repositories
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 1
 * ------------------------------------------------------------
 *
 * Replace:
 *
 *     thenCompose()
 *
 * with:
 *
 *     thenApply()
 *
 *
 * Observe the resulting type.
 *
 *
 * You will conceptually get:
 *
 *
 *     CompletableFuture<
 *         CompletableFuture<Account>
 *     >
 *
 *
 * This is the classic reason for thenCompose().
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 2
 * ------------------------------------------------------------
 *
 * Add another stage:
 *
 *     fetchAccount()
 *         ↓
 *     generateProfile()
 *
 *
 * Build a longer asynchronous pipeline.
 *
 *
 * ------------------------------------------------------------
 * EXPERIMENT 3
 * ------------------------------------------------------------
 *
 * Make fetchAccount() throw an exception.
 *
 * Observe how the CompletableFuture completes exceptionally.
 *
 *
 * ------------------------------------------------------------
 * INTERVIEW QUESTIONS
 * ------------------------------------------------------------
 *
 * 1. Difference between thenApply() and thenCompose()?
 *
 * 2. Why does thenCompose() avoid nested CompletableFuture?
 *
 * 3. When should thenCompose() be used?
 *
 * When the next operation itself returns a CompletableFuture.
 *
 *
 * 4. What does "flattening" mean here?
 *
 * Converting:
 *
 *     CompletableFuture<CompletableFuture<T>>
 *
 * into:
 *
 *     CompletableFuture<T>
 *
 *
 * 5. Can thenCompose() be used for sequential asynchronous
 *    operations?
 *
 * Yes.
 *
 *
 * ------------------------------------------------------------
 * KEY TAKEAWAY
 * ------------------------------------------------------------
 *
 *
 * thenApply:
 *
 *     A → B
 *
 *
 * thenCompose:
 *
 *     A → CompletableFuture<B>
 *
 *
 * Memorize this:
 *
 *
 *     thenApply()
 *         = transformation
 *
 *
 *     thenCompose()
 *         = asynchronous chaining
 *
 *
 * ------------------------------------------------------------
 * NEXT:
 * ------------------------------------------------------------
 *
 * Program 39:
 *
 *     CompletableFutureCombine.java
 *
 * We will solve the opposite problem:
 *
 *     Two independent asynchronous operations must run
 *     concurrently and their results must be combined.
 *
 * This introduces thenCombine().
 *
 * ============================================================
 */

import java.util.concurrent.CompletableFuture;

public class CompletableFuturePipeline {

    /*
     * Simple User model.
     */
    static class User {

        private final String name;
        private final int accountId;

        User(
                String name,
                int accountId) {

            this.name = name;
            this.accountId = accountId;
        }
    }

    /*
     * Simple Account model.
     */
    static class Account {

        private final int accountId;
        private final double balance;

        Account(
                int accountId,
                double balance) {

            this.accountId = accountId;
            this.balance = balance;
        }
    }

    /*
     * Fetch user asynchronously.
     */
    private static CompletableFuture<User> fetchUser(
            int userId) {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching user "
                                    + userId
                    );

                    sleep(1500);

                    return new User(
                            "Mahesh",
                            101
                    );
                }
        );
    }

    /*
     * Fetch account asynchronously.
     *
     * This operation depends on the user data.
     */
    private static CompletableFuture<Account> fetchAccount(
            int accountId) {

        return CompletableFuture.supplyAsync(
                () -> {

                    System.out.println(
                            "Fetching account "
                                    + accountId
                    );

                    sleep(2000);

                    return new Account(
                            accountId,
                            25000.00
                    );
                }
        );
    }

    /*
     * Helper method for simulated delay.
     */
    private static void sleep(
            long milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Task interrupted.",
                    e
            );
        }
    }

    public static void main(String[] args) {

        /*
         * Start the first asynchronous operation.
         */
        CompletableFuture<User> userFuture =
                fetchUser(1);

        /*
         * Chain another asynchronous operation.
         *
         * fetchAccount() returns:
         *
         *     CompletableFuture<Account>
         *
         *
         * Therefore we use thenCompose().
         */
        CompletableFuture<Account> accountFuture =
                userFuture.thenCompose(
                        user ->
                                fetchAccount(
                                        user.accountId
                                )
                );

        /*
         * Continue the pipeline after the account has been
         * retrieved.
         */
        CompletableFuture<String> profileFuture =
                accountFuture.thenApply(
                        account -> {

                            return "User profile: "
                                    + "Account ID = "
                                    + account.accountId
                                    + ", Balance = ₹"
                                    + account.balance;
                        }
                );

        /*
         * Wait for the final pipeline result.
         */
        String profile =
                profileFuture.join();

        System.out.println(
                "\n========== PROFILE =========="
        );

        System.out.println(
                profile
        );

        System.out.println(
                "=============================="
        );
    }
}
