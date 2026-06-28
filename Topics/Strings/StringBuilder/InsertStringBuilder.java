/*
Insert Text using insert()
Problem Statement

Write a Java program to insert a String at a specific position using the insert() method of StringBuilder.

Code
/*
------------------------------------------------------------
Program 03 : Insert Text using insert()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringBuilder
✔ insert()
✔ Index Position

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class InsertStringBuilder {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder("Java Awesome");

        message.insert(5, "is ");

        System.out.println("Updated String : " + message);

    }

}

/*Output:
	Updated String : Java is Awesome

	
Explanation:

	First, we create a StringBuilder object.

	StringBuilder message = new StringBuilder("Java Awesome");

	The String initially looks like this:

	Java Awesome

	Now we use the insert() method.

	message.insert(5, "is ");

	Here,

	5 is the index where the new text will be inserted.
	"is " is the text to insert.

	The insertion happens before the character present at index 5.

	So,

	Before

	Java Awesome

	↓

	After

	Java is Awesome

	The existing characters are automatically shifted to the right.

	Finally, we print the updated String.


	
Use Cases:
	Inserting text into documents.
	Text editor applications.
	Generating formatted reports.
	Building dynamic messages.
	Modifying user input.



---------------------------------------------------------------------------

💡 Bonus Example

You can also insert numbers, characters, or boolean values.

	StringBuilder builder = new StringBuilder("Java");

	builder.insert(4, 21);

	System.out.println(builder);

Output:
	Java21

Another example:

	StringBuilder builder = new StringBuilder("Jva");

	builder.insert(1, 'a');

	System.out.println(builder);
	
Output:
	Java
/*/
