
/*
Great! The replace() method is useful when you want to replace a portion of the existing text without creating a new object.

Program 04 : Replace Characters using replace()
Program 04 : Replace Characters using replace()
Problem Statement

Write a Java program to replace a part of a StringBuilder using the replace() method.

Code
/*
------------------------------------------------------------
Program 04 : Replace Characters using replace()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ StringBuilder
✔ replace()
✔ Index Manipulation

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class ReplaceStringBuilder {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder("Java Awesome");

        message.replace(5, 12, "Programming");

        System.out.println("Updated String : " + message);

    }

}


/*Output:
	Updated String : Java Programming

Explanation:

	First, we create a StringBuilder object.

	StringBuilder message = new StringBuilder("Java Awesome");

	The initial String is:

	Java Awesome

	Next, we use the replace() method.

	message.replace(5, 12, "Programming");

	Here,

	5 is the starting index (inclusive).
	12 is the ending index (exclusive).
	"Programming" is the new text that will replace the existing characters.

	The characters from index 5 to 11 (Awesome) are replaced with Programming.

	The updated String becomes:

	Java Programming

	Finally, we print the updated String.


	
Use Cases:
	Updating text in editors.
	Replacing words in documents.
	Modifying templates.
	Generating dynamic reports.
	Text processing applications.
------------------------------------------------------------------------------
	
💡 Bonus Examples
Replace a Single Character
	StringBuilder builder = new StringBuilder("Jovo");

	builder.replace(1, 2, "a");

	System.out.println(builder);

Output:
	Java
	

Replace Multiple Words
	StringBuilder builder = new StringBuilder("I Like Java");

	builder.replace(2, 6, "Love");

	System.out.println(builder);

	
Output:
	I Love Java
⚠️ 

* Important Note:

The replace(start, end, text) method follows the same rule as substring():

	Start Index → Included
	End Index → Excluded

Example:

	builder.replace(2, 5, "XYZ");

Characters at index 2, 3, and 4 are replaced.

Character at index 5 is not replaced.
/*/
