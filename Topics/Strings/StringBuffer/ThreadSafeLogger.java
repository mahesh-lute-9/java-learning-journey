/*
Build a Thread-Safe Logging System
Problem Statement

Write a Java program to simulate a thread-safe logging system where multiple threads write log messages into a shared StringBuffer.

------------------------------------------------------------
Program 08 : Build a Thread-Safe Logging System
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuffer
✔ Multithreading
✔ Thread Safety
✔ Logging

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class ThreadSafeLogger {

    public static void main(String[] args) throws InterruptedException {

        StringBuffer log = new StringBuffer();

        Thread userOne = new Thread(() -> {

            for (int count = 1; count <= 5; count++) {

                log.append("User-1 Logged In\n");

            }

        });

        Thread userTwo = new Thread(() -> {

            for (int count = 1; count <= 5; count++) {

                log.append("User-2 Logged In\n");

            }

        });

        userOne.start();
        userTwo.start();

        userOne.join();
        userTwo.join();

        System.out.println("Application Log");
        System.out.println("---------------------------");
        System.out.println(log);

    }

}

/*Sample Output:
		Application Log
	---------------------------
	User-1 Logged In
	User-2 Logged In
	User-1 Logged In
	User-1 Logged In
	User-2 Logged In
	User-2 Logged In
	User-1 Logged In
	User-2 Logged In
	User-1 Logged In
	User-2 Logged In

Note: The order of log messages may change each time the program runs because thread scheduling is controlled by the JVM.

Explanation:

	First, we create a shared StringBuffer.

	StringBuffer log = new StringBuffer();

	Both threads will write their log messages into this same object.

	The first thread simulates User-1 logging into the application.

	Thread userOne = new Thread(() -> {

	It appends the message:

	User-1 Logged In

	five times.

	The second thread simulates User-2.

	It also appends its log message five times.

	Both threads start executing simultaneously.

	userOne.start();
	userTwo.start();

	The join() method ensures that the main thread waits until both logging threads finish.

	userOne.join();
	userTwo.join();

	Finally, the complete application log is printed.

	Since StringBuffer is synchronized, every log entry is added safely without corrupting the log.


Use Cases:
	-Application logging.
	-Server log generation.
	-Banking transaction logs.
	-Microservices logging.
	-Enterprise Java applications.

-----------------------------------------------------------------------------------------------------

💡 Real-World Connection

Frameworks such as:

	Spring Boot
	Apache Tomcat
	Jetty
	Java EE Servers

handle requests from many users simultaneously.

When multiple threads generate log messages, thread-safe mechanisms ensure the log data remains consistent. Modern logging frameworks use more advanced techniques than StringBuffer, but this example clearly demonstrates the underlying idea of safe concurrent modifications.

*/
