/*
Find Capacity and Length of a StringBuffer
Problem Statement

Write a Java program to find the length and capacity of a StringBuffer.

------------------------------------------------------------
Program 04 : Find Capacity and Length of a StringBuffer
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringBuffer
✔ length()
✔ capacity()

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class StringBufferCapacity {

    public static void main(String[] args) {

        StringBuffer message = new StringBuffer("Java");

        System.out.println("Content  : " + message);
        System.out.println("Length   : " + message.length());
        System.out.println("Capacity : " + message.capacity());

    }

}

/*Output:
	Content  : Java
	Length   : 4
	Capacity : 20

Explanation:

	First, we create a StringBuffer object.

	StringBuffer message = new StringBuffer("Java");

	The word "Java" contains 4 characters.

	Therefore,

	message.length();

	returns

	4

	Now let's understand the capacity() method.

	When a StringBuffer is created using a String,

	new StringBuffer("Java")

	Java allocates extra memory using the formula:

	Capacity = String Length + 16

	For the given example,

	String Length = 4

	Capacity = 4 + 16 = 20

	Therefore,

	message.capacity();

	returns

	20

	The additional capacity allows new characters to be appended efficiently without allocating memory every time.


	
Use Cases:
	-Building large text efficiently.
	-Optimizing memory usage.
	-Understanding internal memory allocation.
	-Performance tuning.
	-Java interview questions.

💡 Bonus Example 1
	
Empty StringBuffer
	StringBuffer buffer = new StringBuffer();

	System.out.println(buffer.length());
	System.out.println(buffer.capacity());
Output:
	0
	16

💡 Bonus Example 2
StringBuffer with Initial Capacity
	StringBuffer buffer = new StringBuffer(50);

	System.out.println(buffer.length());
	System.out.println(buffer.capacity());
Output:
	0
	50

💡 Bonus Example 3
StringBuffer Created from a String
	StringBuffer buffer = new StringBuffer("Programming");

	System.out.println(buffer.length());
	System.out.println(buffer.capacity());
Output:
	11
	27

Because,

	Length = 11

	Capacity = 11 + 16 = 27

	
📌 Summary:
		Method			Description
		length()		Returns the number of characters currently stored in the StringBuffer.
		capacity()		Returns the total storage capacity before memory expansion is required.

*/
