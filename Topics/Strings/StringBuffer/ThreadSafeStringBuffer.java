/*Thread-Safe String Modification using StringBuffer

Problem Statement

Write a Java program to demonstrate how multiple threads can safely modify the same StringBuffer.

------------------------------------------------------------
Program 06 : Thread-Safe String Modification
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuffer
✔ Multithreading
✔ Thread
✔ Synchronization

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class ThreadSafeStringBuffer {

    public static void main(String[] args) throws InterruptedException {

        StringBuffer message = new StringBuffer();

        Thread threadOne = new Thread(() -> {

            for (int count = 1; count <= 5; count++) {

                message.append("A");

            }

        });

        Thread threadTwo = new Thread(() -> {

            for (int count = 1; count <= 5; count++) {

                message.append("B");

            }

        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("Final Result : " + message);

    }

}

/*
Sample Output
	Final Result : AAAAABBBBB

		or

	Final Result : ABABABABAB

		or

	Final Result : AABBAABABB

All of these outputs are correct.

Explanation:

	First, we create a shared StringBuffer.

	StringBuffer message = new StringBuffer();

	Both threads will modify this same object.

	Next, we create the first thread.

	Thread threadOne = new Thread(() -> {

	It appends the character 'A' five times.

	The second thread

	Thread threadTwo = new Thread(() -> {

	appends the character 'B' five times.

	Both threads start executing.

	threadOne.start();
	threadTwo.start();

	The join() method makes the main thread wait until both worker threads finish.

	threadOne.join();
	threadTwo.join();

	Finally, the complete String is printed.

	Because StringBuffer is synchronized, every call to append() is thread-safe.

	No characters are lost, even though both threads modify the same object simultaneously.

	Why does the output change every time?

	The Java scheduler decides which thread runs first.

For example,

	Execution 1

	AAAAABBBBB

	Execution 2

	ABABABABAB

	Execution 3

	AABABBABAB

	The order may change, but one thing always remains true:

	Total A = 5

	Total B = 5

	No data is lost.


Use Cases:
	-Server-side logging.
	-Banking applications.
	-Multi-threaded report generation.
	-Shared text processing.
	-Enterprise Java applications.

-------------------------------------------------------------------------------------------------------------

💡 Why not use StringBuilder here?

If you replace

StringBuffer message = new StringBuffer();

with:

StringBuilder message = new StringBuilder();

multiple threads can modify the object at the same time.

This may lead to:

	Missing characters
	Corrupted output
	Unexpected behavior

That's exactly why StringBuffer was introduced in Java.

*/
