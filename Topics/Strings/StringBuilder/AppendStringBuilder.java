/*
Append Strings using append()
Problem Statement

Write a Java program to append multiple Strings using the append() method of StringBuilder.

Code
/*
------------------------------------------------------------
Program 02 : Append Strings using append()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringBuilder
✔ append()
✔ Method Chaining

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class AppendStringBuilder {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder();

        message.append("Java");
        message.append(" ");
        message.append("is");
        message.append(" ");
        message.append("Awesome");

        System.out.println("Final String : " + message);

    }

}

/*Output:
	Final String : Java is Awesome

	
Explanation:

	First, we create an empty StringBuilder.

	StringBuilder message = new StringBuilder();

	The append() method is used to add new text at the end of the existing content.

	message.append("Java");

	The content becomes:

	Java

	Next,

	message.append(" ");
	message.append("is");
	message.append(" ");
	message.append("Awesome");

	The final content becomes:

	Java is Awesome

	Unlike the + operator with String, the append() method modifies the same StringBuilder object instead of creating new objects.

	Finally, we print the complete String.


Use Cases:
	-Creating dynamic messages.
	-Building JSON or XML data.
	-Generating reports.
	-Building SQL queries.
	-Efficient String concatenation.
*/
