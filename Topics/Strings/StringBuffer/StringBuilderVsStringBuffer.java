/*
Compare StringBuilder and StringBuffer in a Multithreaded Environment
Problem Statement

Write a Java program to compare the behavior of StringBuilder and StringBuffer when multiple threads modify the same object simultaneously.

------------------------------------------------------------
Program 07 : Compare StringBuilder and StringBuffer
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuilder
✔ StringBuffer
✔ Multithreading
✔ Thread Safety

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class StringBuilderVsStringBuffer {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Using StringBuilder");

        StringBuilder stringBuilder = new StringBuilder();

        Thread builderThreadOne = new Thread(() -> {

            for (int count = 1; count <= 1000; count++) {
                stringBuilder.append("A");
            }

        });

        Thread builderThreadTwo = new Thread(() -> {

            for (int count = 1; count <= 1000; count++) {
                stringBuilder.append("B");
            }

        });

        builderThreadOne.start();
        builderThreadTwo.start();

        builderThreadOne.join();
        builderThreadTwo.join();

        System.out.println("Length : " + stringBuilder.length());



        System.out.println("\nUsing StringBuffer");

        StringBuffer stringBuffer = new StringBuffer();

        Thread bufferThreadOne = new Thread(() -> {

            for (int count = 1; count <= 1000; count++) {
                stringBuffer.append("A");
            }

        });

        Thread bufferThreadTwo = new Thread(() -> {

            for (int count = 1; count <= 1000; count++) {
                stringBuffer.append("B");
            }

        });

        bufferThreadOne.start();
        bufferThreadTwo.start();

        bufferThreadOne.join();
        bufferThreadTwo.join();

        System.out.println("Length : " + stringBuffer.length());

    }

}

/*Sample Output:
	Using StringBuilder
	Length : 1987
	Using StringBuffer
	Length : 2000

Note: The StringBuilder length may vary each time you run the program. Sometimes it may even print 2000, depending on thread scheduling. The important point is that it is not guaranteed to be correct, whereas StringBuffer provides thread-safe operations.

Explanation:

	The program performs the same task twice.

	First, it uses a shared StringBuilder.

Two threads append:

	1000 characters 'A'
	1000 characters 'B'

	Both threads modify the same object simultaneously.

	Since StringBuilder is not synchronized, both threads can access and modify it at the same time.

	This may result in:

		-Lost updates
		-Incorrect length
	-Inconsistent output

	Next, the same logic is repeated using a shared StringBuffer.

		StringBuffer stringBuffer = new StringBuffer();

		StringBuffer synchronizes its methods internally.

	This means only one thread can execute methods like append() on the same object at a time.

	As a result, all 2000 characters are safely appended.


Use Cases:
	-Understanding thread safety.
	-Java multithreading practice.
	-Enterprise backend applications.
	-Interview demonstrations.
	-Performance comparison.

------------------------------------------------------------------------------------------------------

📌 Interview Tip:

If an interviewer asks:

"When should I use StringBuffer instead of StringBuilder?"

A good answer is:

Use StringBuilder when only one thread modifies the object because it is faster. Use StringBuffer when multiple threiads share and modify the same object because its synchronized methods make those operations thread-safe.

*/
