/*
Create and Print a StringBuffer
Problem Statement

Write a Java program to create a StringBuffer object and print its contents.

------------------------------------------------------------
Program 01 : Create and Print a StringBuffer
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringBuffer
✔ Constructors
✔ toString()

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class CreateStringBuffer {

    public static void main(String[] args) {

        StringBuffer message = new StringBuffer("Hello Java");

        System.out.println("StringBuffer Content : " + message);

    }

}
/*Output:
	StringBuffer Content : Hello Java

	
Explanation:

	First, we create a StringBuffer object.

	StringBuffer message = new StringBuffer("Hello Java");

	StringBuffer is a mutable sequence of characters.

	Unlike String, its content can be modified without creating a new object.

	Unlike StringBuilder, every public method of StringBuffer is synchronized, making it thread-safe.

	When we print the object,

	System.out.println(message);

	Java automatically calls the toString() method and displays its contents.


Use Cases:
	Banking software.
	Server-side applications.
	Multi-threaded systems.
	Shared log generation.
	Thread-safe text processing.

📌 What's Different from StringBuilder?

	The syntax is almost identical.

	StringBuilder builder = new StringBuilder();

	↓

	StringBuffer buffer = new StringBuffer();

	The major difference is thread safety, not the API.

*/
