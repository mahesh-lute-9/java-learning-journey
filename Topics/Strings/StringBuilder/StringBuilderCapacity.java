/*
Find Capacity and Length of a StringBuilder
Problem Statement

Write a Java program to find the length and capacity of a StringBuilder.

Code
/*
------------------------------------------------------------
Program 07 : Find Capacity and Length of a StringBuilder
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringBuilder
✔ length()
✔ capacity()

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class StringBuilderCapacity {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder("Java");

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

	First, we create a StringBuilder object.

	StringBuilder message = new StringBuilder("Java");

	The word "Java" contains 4 characters.

	Therefore,

	message.length();

	returns

	4

	Now let's understand capacity().

	When a StringBuilder is created using a String,

	new StringBuilder("Java")

	Java automatically allocates memory using the formula:

	Capacity = String Length + 16

	Here,

	String Length = 4

	Capacity = 4 + 16 = 20

	Therefore,

	message.capacity();

	returns

	20

	The extra capacity allows new characters to be added without allocating memory every time.


Use Cases:
	Optimizing memory usage.
	Building large Strings efficiently.
	Performance tuning.
	Understanding internal working of StringBuilder.
	Java interview questions.


------------------------------------------------------------------------------------

💡 Bonus Examples
Empty StringBuilder
	StringBuilder builder = new StringBuilder();

	System.out.println(builder.length());
	System.out.println(builder.capacity());
Output:
	0
	16

StringBuilder with Initial Capacity
	StringBuilder builder = new StringBuilder(50);

	System.out.println(builder.length());
	System.out.println(builder.capacity());
Output:
	0
	50

StringBuilder Created from a String
	StringBuilder builder = new StringBuilder("Programming");

	System.out.println(builder.length());
	System.out.println(builder.capacity());
Output:
	11
	27

	Because,

	Length = 11

	Capacity = 11 + 16 = 27

📌 Summary:
	Method		Description
	length()	Returns the number of characters currently stored in the StringBuilder.
	capacity()	Returns the total number of characters the StringBuilder can store before allocating more memory.
*/
