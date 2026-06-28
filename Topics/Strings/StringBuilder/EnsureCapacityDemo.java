/*
Demonstrate ensureCapacity()
Problem Statement

Write a Java program to demonstrate how the ensureCapacity() method increases the capacity of a StringBuilder.

Code
/*
------------------------------------------------------------
Program 08 : Demonstrate ensureCapacity()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringBuilder
✔ ensureCapacity()
✔ capacity()

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class EnsureCapacityDemo {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder();

        System.out.println("Initial Capacity      : " + message.capacity());

        message.ensureCapacity(50);

        System.out.println("Updated Capacity      : " + message.capacity());

    }

}

/*Output:
	Initial Capacity      : 16
	Updated Capacity      : 50

Explanation:

	First, we create an empty StringBuilder.

	StringBuilder message = new StringBuilder();

	By default, an empty StringBuilder has a capacity of 16.

	message.capacity();

	Output:

	16

	Next, we call the ensureCapacity() method.

	message.ensureCapacity(50);

	This method ensures that the StringBuilder can store at least 50 characters without allocating additional memory.

	Since the current capacity (16) is less than the requested capacity (50), Java increases the internal capacity.

	Finally, we print the updated capacity.

	message.capacity();

	Output:

	50

	
Use Cases:
	-Building large reports.
	-Processing large text files.
	-Optimizing memory allocation.
	-Improving performance.
	-Java interview questions.

-----------------------------------------------------------------------------------

💡 Bonus Example 1

If the requested capacity is less than or equal to the current capacity, nothing changes.

	StringBuilder builder = new StringBuilder();

	System.out.println(builder.capacity());

	builder.ensureCapacity(10);

	System.out.println(builder.capacity());
Output:
	16
	16

💡 Bonus Example 2

Java automatically increases the capacity using this formula:

	New Capacity = (Old Capacity × 2) + 2

Example:

	Old Capacity = 16

	New Capacity

	↓

	(16 × 2) + 2

	↓

	34

	Similarly,

	34

	↓

	70

	↓

	142

	↓

	286

	This automatic growth minimizes the number of memory reallocations and improves performance when appending large amounts of text.

📌 Summary:
	Method					Description
	ensureCapacity(int minimumCapacity)	Ensures that the StringBuilder has at least the specified capacity.
						Default Capacity	16
						Capacity Growth Formula	(Old Capacity × 2) + 2

*/
