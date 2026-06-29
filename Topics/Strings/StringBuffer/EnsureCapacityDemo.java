/*
Demonstrate ensureCapacity()
Problem Statement

Write a Java program to demonstrate how the ensureCapacity() method increases the capacity of a StringBuffer.

------------------------------------------------------------
Program 05 : Demonstrate ensureCapacity()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringBuffer
✔ ensureCapacity()
✔ capacity()

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class EnsureCapacityDemo {

    public static void main(String[] args) {

        StringBuffer message = new StringBuffer();

        System.out.println("Initial Capacity : " + message.capacity());

        message.ensureCapacity(50);

        System.out.println("Updated Capacity : " + message.capacity());

    }

}


/*Output:
	Initial Capacity : 16
	Updated Capacity : 50

Explanation:

	First, we create an empty StringBuffer.

	StringBuffer message = new StringBuffer();

	By default, a StringBuffer has a capacity of 16.

	message.capacity();

	returns

	16

	Next, we call the ensureCapacity() method.

	message.ensureCapacity(50);

	This tells Java to make sure the StringBuffer can store at least 50 characters.

	Since the current capacity (16) is smaller than 50, Java increases the capacity.

	Finally, we print the updated capacity.

	message.capacity();

	returns

	50

	
Use Cases:
	-Building large reports.
	-Processing large files.
	-Log generation.
	-Performance optimization.
	-Java interview questions.

----------------------------------------------------------------------------------------------------------------------
💡 Bonus Example 1

If the requested capacity is smaller than the current capacity, no change is made.

	StringBuffer buffer = new StringBuffer();

	System.out.println(buffer.capacity());

	buffer.ensureCapacity(10);

	System.out.println(buffer.capacity());
Output:
	16
	16

💡 Bonus Example 2

When Java expands the capacity automatically, it uses the following formula:

	New Capacity = (Old Capacity × 2) + 2

Example:

	Old Capacity = 16

		↓

	New Capacity = (16 × 2) + 2

		↓

		34

	Further expansions follow the same rule:

		16

		↓

		34

		↓

		70

		↓
	
		142

		↓

		286

This strategy reduces the number of memory reallocations and improves performance.



📌 Summary:
		Method				Description
	ensureCapacity(int minimumCapacity)	Ensures that the StringBuffer has at least the specified capacity.
	Default Capacity			16
	Capacity Growth Formula			(Old Capacity × 2) + 2


🎯 Important Note:

Up to this point, you've noticed that the API of StringBuffer is almost identical to StringBuilder.

That's expected.

The real difference between the two is thread safety, not the methods themselves.
*/
