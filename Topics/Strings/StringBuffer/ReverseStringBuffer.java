/*
Reverse a String using reverse()
Problem Statement

Write a Java program to reverse a String using the reverse() method of StringBuffer.

------------------------------------------------------------
Program 03 : Reverse a String using reverse()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ StringBuffer
✔ reverse()
✔ Mutable Strings

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class ReverseStringBuffer {

    public static void main(String[] args) {

        String input = "Java Programming";

        StringBuffer reversedString = new StringBuffer(input);

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

	Next, we pass the String to the StringBuffer constructor.

	StringBuffer reversedString = new StringBuffer(input);

	The reverse() method reverses all the characters stored in the StringBuffer.

	reversedString.reverse();

	The original text

	Java Programming

	becomes

	gnimmargorP avaJ

	Unlike String, the reverse() method modifies the same StringBuffer object instead of creating a new one.

	Finally, the reversed String is printed.


Use Cases:
	Palindrome checking.
	Text manipulation.
	Data formatting.
	Reverse message generation.
	Multi-threaded text processing.
-------------------------------------------------------------------------------------------------------------------

💡 Bonus Example
	
	StringBuffer buffer = new StringBuffer("OpenAI");

	buffer.reverse();

	System.out.println(buffer);
Output:
	IAnepO


📌 StringBuilder vs StringBuffer

Both classes provide the same reverse() method.

	new StringBuilder("Java").reverse();
	new StringBuffer("Java").reverse();

The difference is not in the method—it is in thread safety.

*/
