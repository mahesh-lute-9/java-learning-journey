

/*Program 04 : Using Custom Delimiters

## Problem Statement

Write a Java program to tokenize a String using a custom delimiter instead of the default whitespace delimiter.

/*
------------------------------------------------------------
Program 04 : Using Custom Delimiters

Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ StringTokenizer
✔ Custom Delimiter
✔ nextToken()
✔ hasMoreTokens()

Expected Time : 15 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class CustomDelimiterDemo {

    public static void main(String[] args) {

        String studentData = "101,John,Computer Science,89";

        StringTokenizer tokenizer =
                new StringTokenizer(studentData, ",");

        System.out.println("Student Details");

        while (tokenizer.hasMoreTokens()) {

            System.out.println(tokenizer.nextToken());

        }

    }

}

/*
------------------------------------------------------------
Output

	Student Details

	101
	John
	Computer Science
	89

------------------------------------------------------------
Memory Diagram:

		Original String

+--------------------------------------+
|101,John,Computer Science,89          |
+--------------------------------------+

                Delimiter

                     ,

                     │
                     ▼

            StringTokenizer

+------+--------+-------------------+------+
| 101  | John   | Computer Science  |  89  |
+------+--------+-------------------+------+

------------------------------------------------------------
Explanation:

	By default, StringTokenizer uses whitespace as the delimiter.

	In this program, we specify a comma (,) as the delimiter.

	StringTokenizer tokenizer = new StringTokenizer(studentData, ",");

	Now,

	every comma separates one token from the next.

	The hasMoreTokens() method checks whether another token is available.

	The nextToken() method retrieves each token one by one until all tokens have been processed.

------------------------------------------------------------
Interview Notes:

	A delimiter is the character that separates one token from another.

	Examples of delimiters

	Space

	Comma (,)

	Colon (:)

	Semicolon (;)

	Pipe (|)

	Tab (\t)

	You can use any character or set of characters as delimiters.

------------------------------------------------------------
Important Points:

	✔ Default delimiter is whitespace.

	✔ Custom delimiters can be specified using the constructor.

	✔ The delimiter itself is not returned as a token.

	✔ Multiple delimiters can also be specified.

------------------------------------------------------------
Common Mistakes

❌ Forgetting to specify the delimiter.

❌ Expecting commas to appear in the output.

❌ Assuming only one delimiter is allowed.

------------------------------------------------------------
Follow-up Interview Questions

	1. What is a delimiter?

	2. How do you specify a custom delimiter?

	3. Can multiple delimiters be used?

	4. Does StringTokenizer return delimiters as tokens?

	5. What is the default delimiter?

------------------------------------------------------------
Real World Use Cases

	✔ CSV File Parsing

	✔ Student Records

	✔ Employee Records

	✔ Product Information

	✔ Data Import Utilities

------------------------------------------------------------
Practice Questions

1.

Tokenize the following String.

"Java|Spring Boot|Docker|MySQL"

using

|

as the delimiter.

------------------------------------------------------------

2.

Tokenize

"10:30:45"

using

:

as the delimiter.

------------------------------------------------------------

3.

Store each token inside an ArrayList.

------------------------------------------------------------

4.

Count the total number of tokens after using
a custom delimiter.

------------------------------------------------------------
*/
