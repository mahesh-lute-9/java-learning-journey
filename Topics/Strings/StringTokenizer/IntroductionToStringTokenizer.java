
/*Program 01 : Introduction to StringTokenizer

## Problem Statement

Write a Java program to demonstrate how to use the `StringTokenizer` class to split a sentence into individual words.

/*
------------------------------------------------------------
Program 01 : Introduction to StringTokenizer

Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringTokenizer
✔ nextToken()
✔ hasMoreTokens()
✔ Default Delimiter

Expected Time : 10 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class IntroductionToStringTokenizer {

    public static void main(String[] args) {

        String sentence = "Java is a powerful programming language";

        StringTokenizer tokenizer = new StringTokenizer(sentence);

        System.out.println("Original String");

        System.out.println(sentence);

        System.out.println();

        System.out.println("Tokens");

        while (tokenizer.hasMoreTokens()) {

            System.out.println(tokenizer.nextToken());

        }

    }

}

/*
------------------------------------------------------------
Output

	Original String

	Java is a powerful programming language

Tokens

	Java
	is
	a
	powerful
	programming
	language

------------------------------------------------------------
Memory Diagram

		Original String

+----------------------------------------------+
| Java is a powerful programming language      |
+----------------------------------------------+

                    │
                    ▼

             StringTokenizer

+--------+--------+--------+-------------+--------------+-----------+
| Java   | is     | a      | powerful    | programming  | language  |
+--------+--------+--------+-------------+--------------+-----------+

------------------------------------------------------------
Explanation:

	The StringTokenizer class is used to divide a String into smaller parts called tokens.

	First, we create a String.

	String sentence = "Java is a powerful programming language";

	Next, we create a StringTokenizer object.

	StringTokenizer tokenizer = new StringTokenizer(sentence);

	Since no delimiter is specified, the default delimiter is whitespace.

	That means every space separates one token from another.

	The hasMoreTokens() method checks whether another token is available.

	It returns

	true

	if another token exists.

	Otherwise,

	it returns

	false.

	The nextToken() method returns the next available token.

	The while loop continues until all tokens have been processed.

------------------------------------------------------------
Interview Notes:

	StringTokenizer belongs to java.util package.

	It is considered a legacy class.

	Although modern Java prefers split()

	or

	Scanner,

	many interviews still ask StringTokenizer to test your understanding of tokenization.

------------------------------------------------------------
Important Points:

	✔ StringTokenizer breaks a String into tokens.

	✔ Default delimiter is whitespace.

	✔ Tokens are returned one by one.

	✔ nextToken() moves to the next token.

	✔ hasMoreTokens() prevents NoSuchElementException.

------------------------------------------------------------
Common Mistakes:

	❌ Calling nextToken() without checking hasMoreTokens().

	❌ Forgetting to import

	java.util.StringTokenizer

	❌ Assuming the original String is modified.

------------------------------------------------------------
Follow-up Interview Questions

1. What is StringTokenizer?

2. Which package contains StringTokenizer?

3. What is the default delimiter?

4. What happens if nextToken() is called when
no tokens remain?

5. Is StringTokenizer mutable?

------------------------------------------------------------
Real World Use Cases

	✔ Parsing Commands

	✔ Reading Configuration Files

	✔ Parsing Text Files

	✔ Processing User Input

	✔ Simple Data Extraction

------------------------------------------------------------
Practice Questions

1.

Tokenize the following String.

"Flutter Java Spring Boot"

------------------------------------------------------------

2.

Count the number of words using
StringTokenizer.

------------------------------------------------------------

3.

Print only the first three tokens.

------------------------------------------------------------

4.

Try calling nextToken() after all tokens
have been consumed.

What happens?

------------------------------------------------------------
*/
