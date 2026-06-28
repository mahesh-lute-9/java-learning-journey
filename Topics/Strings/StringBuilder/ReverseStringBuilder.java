/*
Excellent! The reverse() method is one of the most useful methods of StringBuilder. It reverses the characters in the same object, making it much more efficient than manually reversing a String.

Program 06 : Reverse a String using reverse()
Program 06 : Reverse a String using reverse()
Problem Statement

Write a Java program to reverse a String using the reverse() method of StringBuilder.

Code
/*
------------------------------------------------------------
Program 06 : Reverse a String using reverse()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ StringBuilder
✔ reverse()
✔ toString()

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class ReverseStringBuilder {

    public static void main(String[] args) {

        String input = "Java Programming";

        StringBuilder reversedString = new StringBuilder(input);

        reversedString.reverse();

        System.out.println("Original String : " + input);
        System.out.println("Reversed String : " + reversedString);

    }

}

/*Output:
	Original String : Java Programming
	Reversed String : gnimmargorP avaJ

Explanation:

	First, we create a String.

	String input = "Java Programming";

	Next, we create a StringBuilder object by passing the String to its constructor.

	StringBuilder reversedString = new StringBuilder(input);

	The reverse() method reverses all the characters stored in the StringBuilder.

	reversedString.reverse();

	After calling this method,

	Java Programming

	↓

	gnimmargorP avaJ

	Finally, we print both the original and reversed Strings.

	Since StringBuilder overrides the toString() method, it is automatically converted to a String when printed.


	
Use Cases:
	Reversing user input.
	Palindrome checking.
	Text processing.
	Data formatting.
	Coding interview questions.

	
------------------------------------------------------------------------------------	
💡 Bonus Examples
Reverse a Single Word
	StringBuilder builder = new StringBuilder("Programming");

	builder.reverse();

	System.out.println(builder);
Output:
	gnimmargorP

Reverse a Number Stored as a String
	StringBuilder builder = new StringBuilder("123456");

	builder.reverse();

	System.out.println(builder);
Output:
	654321

Reverse and Convert Back to String
	String input = "Java";

	String reversed = new StringBuilder(input)
        	.reverse()
        	.toString();

	System.out.println(reversed);
Output:
	avaJ
*/
