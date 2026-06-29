/*
Chat Message Builder
Problem Statement

Write a Java program to simulate multiple users sending chat messages simultaneously and store the messages in a shared StringBuffer.

------------------------------------------------------------
Program 10 : Chat Message Builder
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuffer
✔ Multithreading
✔ Thread Safety
✔ Chat Simulation

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class ChatMessageBuilder {

    public static void main(String[] args) throws InterruptedException {

        StringBuffer chatHistory = new StringBuffer();

        Thread userOne = new Thread(() -> {

            chatHistory.append("Mahesh : Hi Everyone!\n");
            chatHistory.append("Mahesh : How are you?\n");
            chatHistory.append("Mahesh : Welcome to Java.\n");

        });

        Thread userTwo = new Thread(() -> {

            chatHistory.append("Rahul : Hello Mahesh!\n");
            chatHistory.append("Rahul : I'm doing well.\n");
            chatHistory.append("Rahul : Let's start coding.\n");

        });

        Thread userThree = new Thread(() -> {

            chatHistory.append("Priya : Hi Everyone!\n");
            chatHistory.append("Priya : Ready for today's session.\n");
            chatHistory.append("Priya : Let's begin!\n");

        });

        userOne.start();
        userTwo.start();
        userThree.start();

        userOne.join();
        userTwo.join();
        userThree.join();

        System.out.println("Chat History");
        System.out.println("-------------------------------");
        System.out.println(chatHistory);

    }

}
/*Sample Output
		Chat History
	-------------------------------
	Mahesh : Hi Everyone!
	Rahul : Hello Mahesh!
	Nayra : Hi Everyone!
	Mahesh : How are you?
	Rahul : I'm doing well.
	Priya : Ready for today's session.
	Mahesh : Welcome to Java.
	Rahul : Let's start coding.
	Priya : Let's begin!

Note: The order of messages may change every time you run the program because the JVM schedules threads differently. This is expected behavior.


Explanation:

	First, we create a shared StringBuffer.

	StringBuffer chatHistory = new StringBuffer();

	This object stores all chat messages.

	Next, we create three threads.

	Each thread represents a different user.

	Thread userOne

	represents Mahesh.

	Thread userTwo

	represents Rahul.

	Thread userThree

	represents Priya.

	Each thread appends its messages to the shared StringBuffer.

	chatHistory.append("Mahesh : Hi Everyone!\n");

	Since StringBuffer is synchronized, only one thread can execute an append() operation on the shared object at a time.

	After starting all three threads,

	userOne.start();
	userTwo.start();
	userThree.start();

	the join() method ensures that the main thread waits until all users finish sending their messages.

	Finally, the complete chat history is printed.

	Even though the order of messages may differ, no message is lost or corrupted because StringBuffer is thread-safe.


Use Cases:
	-Chat applications.
	-Messaging systems.
	-Customer support platforms.
	-Team collaboration software.
	-Enterprise communication systems.
	-Multi-threaded Java applications.
*/
