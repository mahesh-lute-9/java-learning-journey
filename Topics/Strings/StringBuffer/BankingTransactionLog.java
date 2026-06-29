
/*
Banking Transaction Log Generator
Problem Statement

Write a Java program to simulate multiple users performing banking transactions and store all transaction details in a thread-safe transaction log using StringBuffer.

------------------------------------------------------------
Program 09 : Banking Transaction Log Generator
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuffer
✔ Multithreading
✔ Thread Safety
✔ Transaction Logging

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class BankingTransactionLog {

    public static void main(String[] args) throws InterruptedException {

        StringBuffer transactionLog = new StringBuffer();

        Thread customerOne = new Thread(() -> {

            for (int transaction = 1; transaction <= 5; transaction++) {

                transactionLog.append("Customer-1 deposited ₹1000\n");

            }

        });

        Thread customerTwo = new Thread(() -> {

            for (int transaction = 1; transaction <= 5; transaction++) {

                transactionLog.append("Customer-2 withdrew ₹500\n");

            }

        });

        customerOne.start();
        customerTwo.start();

        customerOne.join();
        customerTwo.join();

        System.out.println("Transaction Log");
        System.out.println("-------------------------------");
        System.out.println(transactionLog);

    }

}

/*Sample Output
		Transaction Log
	-------------------------------
	Customer-1 deposited ₹1000
	Customer-2 withdrew ₹500
	Customer-1 deposited ₹1000
	Customer-2 withdrew ₹500
	Customer-1 deposited ₹1000
	Customer-1 deposited ₹1000
	Customer-2 withdrew ₹500
	Customer-2 withdrew ₹500
	Customer-1 deposited ₹1000
	Customer-2 withdrew ₹500

Note: The order of transactions may change each time the program is executed because the JVM decides which thread runs first.


Explanation;

	First, we create a shared StringBuffer.

	StringBuffer transactionLog = new StringBuffer();

	This object stores all transaction messages.

	Next, we create the first thread.

	Thread customerOne = new Thread(() -> {

	It simulates a customer depositing money into the account.

	The second thread simulates another customer withdrawing money.

	Thread customerTwo = new Thread(() -> {

	Both threads write their transaction details to the same StringBuffer.

	After starting the threads,

	customerOne.start();
	customerTwo.start();

	the join() method ensures that the main thread waits until both customers finish all transactions.

	Finally, the complete transaction log is displayed.

	Since StringBuffer synchronizes its methods, every transaction is recorded safely without corrupting the shared log.


	
Use Cases:
	-Banking systems.
	-ATM transaction logs.
	-Financial applications.
	-Audit logs.
	-Enterprise backend systems.
	-Multi-user applications.


💡 Real-World Note

In actual banking applications:

	Transactions are stored in databases.
	Logs are written using logging frameworks.
	Access to shared resources is synchronized.

This example demonstrates the core concept of thread-safe shared data, which is one of the reasons StringBuffer exists.
*/
