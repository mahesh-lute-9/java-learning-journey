
/*
Append Strings using append()
	
Problem Statement

Write a Java program to append multiple Strings using the append() method of StringBuffer.

------------------------------------------------------------
Program 02 : Append Strings using append()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringBuffer
✔ append()
✔ Method Chaining

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class AppendStringBuffer {

    public static void main(String[] args) {

        StringBuffer message = new StringBuffer();

        message.append("Java")
               .append(" ")
               .append("is")
               .append(" ")
               .append("Powerful");

        System.out.println("Final String : " + message);

    }

}

/*Output:
	Final String : Java is Powerful

Explanation:

	First, we create an empty StringBuffer.

	StringBuffer message = new StringBuffer();

	The append() method adds new text at the end of the existing content.

	message.append("Java");

	The content becomes:

	Java

	Next, we continue appending more text.

	message.append(" ")
       		.append("is")
       		.append(" ")
       		.append("Powerful");

	Each call to append() modifies the same StringBuffer object.

	Unlike String, no new object is created after every concatenation.

	The append() method returns the same StringBuffer object, allowing method chaining.

	Finally, the complete String is printed.


	
Use Cases:
	-Building log messages.
	-Creating reports.
	-Dynamic text generation.
	-Server-side applications.
	-Multi-threaded applications.
-------------------------------------------------------------------------------------------------------------------

💡 Why use StringBuffer instead of String?

Using String:

	String message = "";

	message += "Java";
	message += " is";
	message += " Powerful";

This creates multiple temporary String objects.

Using StringBuffer:

	StringBuffer message = new StringBuffer();

	message.append("Java")
       		.append(" is")
       		.append(" Powerful");

The same object is modified, making it more memory efficient.

📌 Difference from StringBuilder

The syntax is exactly the same:

	StringBuilder builder = new StringBuilder();
	builder.append("Java");
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("Java");

The key difference is:

		StringBuilder		StringBuffer
		Not Thread-Safe		Thread-Safe
		Faster			Slightly Slower
		No Synchronization	Methods are Synchronized

	*/
