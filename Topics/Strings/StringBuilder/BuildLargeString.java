/*
Program 10 : Build a Large String Efficiently

Problem Statement

Write a Java program to build a large String efficiently using `StringBuilder`.

/*
------------------------------------------------------------
Program 10 : Build a Large String Efficiently
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringBuilder
✔ append()
✔ Loop
✔ Efficient String Concatenation

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class BuildLargeString {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder();

        for (int number = 1; number <= 10; number++) {

            message.append("Line ")
                   .append(number)
                   .append(": Welcome to Java Programming")
                   .append("\n");

        }

        System.out.println(message);

    }

}

/*Output:

	Line 1: Welcome to Java Programming
	Line 2: Welcome to Java Programming
	Line 3: Welcome to Java Programming
	Line 4: Welcome to Java Programming
	Line 5: Welcome to Java Programming
	Line 6: Welcome to Java Programming
	Line 7: Welcome to Java Programming
	Line 8: Welcome to Java Programming
	Line 9: Welcome to Java Programming
	Line 10: Welcome to Java Programming


Explanation:

	First, we create an empty `StringBuilder`.

	StringBuilder message = new StringBuilder();

	Using a `for` loop, we generate 10 lines of text.


	for (int number = 1; number <= 10; number++)

	Inside the loop, we use the `append()` method to add text.

	message.append("Line ")
       		.append(number)
       		.append(": Welcome to Java Programming")
       		.append("\n");

	Each call to `append()` adds new content to the same `StringBuilder` object.

	Unlike `String`, no new objects are created during each iteration.

	After the loop completes, the `StringBuilder` contains all 10 lines.

	Finally, we print the complete result.


## Use Cases:

* Generating reports.
* Creating log files.
* Building HTML or XML.
* Generating JSON responses.
* Exporting CSV data.
* Efficient text generation.

--------------------------------------------------------------------------------------------

## 💡 Why Use `StringBuilder` Instead of `String`?

	Using `String`:


	String result = "";

	for (int i = 1; i <= 10; i++) {
    		result += "Line " + i + "\n";
	}

	Every iteration creates a **new String object**, which is inefficient for large amounts of text.

	Using `StringBuilder`:


	StringBuilder result = new StringBuilder();

	for (int i = 1; i <= 10; i++) {
    		result.append("Line ").append(i).append("\n");
	}

	The same object is updated throughout the loop, making it much faster and more memory-efficient.


## 📌 Summary

| String                                   | StringBuilder                     |
| ---------------------------------------- | --------------------------------- |
| Immutable                                | Mutable                           |
| Creates new objects during concatenation | Modifies the same object          |
| Slower for repeated concatenation        | Faster for repeated concatenation |
| Higher memory usage                      | Lower memory usage                |

---

*/
