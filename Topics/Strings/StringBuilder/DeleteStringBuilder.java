/*
Delete Characters using delete() and deleteCharAt()
Problem Statement

Write a Java program to delete a range of characters and a single character from a StringBuilder.

Code
/*
------------------------------------------------------------
Program 05 : Delete Characters using delete() and deleteCharAt()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ StringBuilder
✔ delete()
✔ deleteCharAt()

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class DeleteStringBuilder {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder("Java Programming");

        message.delete(4, 16);

        System.out.println("After delete()      : " + message);

        message.deleteCharAt(3);

        System.out.println("After deleteCharAt(): " + message);

    }

}

/*Output:
	After delete()      : Java
	After deleteCharAt(): Jav

Explanation:

	First, we create a StringBuilder.

	StringBuilder message = new StringBuilder("Java Programming");

	Initially,

	Java Programming
	Using delete()
	message.delete(4, 16);

	The delete() method removes characters from the specified range.

	Here,

	4 → Starting index (inclusive)
	16 → Ending index (exclusive)

	The characters from index 4 to 15 are removed.

	Result:

	Java
	Using deleteCharAt()
	message.deleteCharAt(3);

	The deleteCharAt() method removes the character at the specified index.

	Current String:

	Java

	Character at index 3 is:

	a

	After deleting it,

	Jav

	Finally, the updated String is printed.


Use Cases:
	Removing unwanted text.
	Text editor applications.
	Input validation.
	Cleaning user input.
	Dynamic text processing.

*/
