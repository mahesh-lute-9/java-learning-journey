/*
Demonstrate setLength()
Problem Statement

Write a Java program to demonstrate how the setLength() method changes the length of a StringBuilder.

Code
/*
------------------------------------------------------------
Program 09 : Demonstrate setLength()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringBuilder
✔ setLength()
✔ length()

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class SetLengthDemo {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder("Java Programming");

        System.out.println("Original String : " + message);
        System.out.println("Original Length : " + message.length());

        message.setLength(4);

        System.out.println("\nAfter setLength(4)");

        System.out.println("Updated String  : " + message);
        System.out.println("Updated Length  : " + message.length());

    }
}
/*Output:
	Original String : Java Programming
	Original Length : 16

	After setLength(4)

	Updated String  : Java
	Updated Length  : 4
	
	
Explanation:

	First, we create a StringBuilder.

	StringBuilder message = new StringBuilder("Java Programming");

	Initially,

	Java Programming

	Its length is

	16

	Next, we call

	message.setLength(4);

	The setLength() method changes the length of the StringBuilder.

	Since the new length (4) is smaller than the current length (16), all characters after index 3 are removed.

	The String becomes:

	Java

	Finally, we print the updated String and its new length.


Use Cases:
	-Truncating text.
	-Clearing unwanted data.
	-Reusing the same StringBuilder.
	-Text processing.
	-Java interview questions.

--------------------------------------------------------------------------------	
💡 Bonus Example 1

Increase the length.

	StringBuilder builder = new StringBuilder("Java");

	builder.setLength(10);

	System.out.println(builder.length());
Output:
	10

The extra positions are filled with the null character (\0), which is not visible when printed.

💡 Bonus Example 2

Clear the entire StringBuilder.

	StringBuilder builder = new StringBuilder("Programming");

	builder.setLength(0);

	System.out.println(builder);
	System.out.println(builder.length());
Output:

	0

This is a fast and efficient way to reuse an existing StringBuilder instead of creating a new one.


📌 Summary:
		Method			Description
	setLength(int newLength)	Changes the length of the StringBuilder.
	Smaller 			Length	Removes extra characters.
	Larger Length			Adds null (\0) characters internally.
	setLength(0)			Clears the entire StringBuilder.

*/
