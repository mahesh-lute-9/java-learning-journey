

/*Program 03 : Count Words using StringTokenizer

## Problem Statement

Write a Java program to count the total number of words in a sentence using the `StringTokenizer` class.

/*
------------------------------------------------------------
Program 03 : Count Words using StringTokenizer

Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ StringTokenizer
✔ countTokens()
✔ Word Counting
✔ Token Traversal

Expected Time : 10 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class CountWords {

    public static void main(String[] args) {

        String sentence = "Java is a platform independent programming language";

        StringTokenizer tokenizer = new StringTokenizer(sentence);

        System.out.println("Sentence");

        System.out.println(sentence);

        System.out.println();

        System.out.println("Total Words : " + tokenizer.countTokens());

    }

}

/*
------------------------------------------------------------
Output

Sentence

Java is a platform independent programming language

Total Words : 7

------------------------------------------------------------
Memory Diagram

		Original String
	
+------------------------------------------------------+
| Java is a platform independent programming language  |
+------------------------------------------------------+

                      │
                      ▼

             StringTokenizer

+------+----+---+----------+-------------+-------------+----------+
| Java | is | a | platform | independent | programming | language |
+------+----+---+----------+-------------+-------------+----------+

                      │
                      ▼

		countTokens()

Returns:

	7

------------------------------------------------------------
Explanation:

	A StringTokenizer object is created using the given sentence.

	The sentence is automatically divided into individual words because the default delimiter is whitespace.

	The countTokens() method returns the number of remaining tokens.

	Since no token has been read yet, all seven words are still available.

Therefore,

	countTokens() returns 7.

------------------------------------------------------------
Interview Notes:

	countTokens() returns the number of remaining tokens, not the total tokens originally present.

For example,

	if one token has already been consumed,

	countTokens()

	will decrease by one.

------------------------------------------------------------
Important Points:

	✔ countTokens() returns remaining tokens.

	✔ Reading tokens decreases the count.

	✔ countTokens() does not remove tokens.

	✔ Default delimiter is whitespace.

------------------------------------------------------------
Common Mistakes

❌ Assuming countTokens() always returns the
original number of words.

❌ Calling nextToken() before counting without
realizing the count changes.

❌ Confusing words with characters.

------------------------------------------------------------
Follow-up Interview Questions

1. What does countTokens() return?

2. Does countTokens() modify the tokenizer?

3. Why does the returned count decrease after
calling nextToken()?

4. Can countTokens() be called multiple times?

5. What is the difference between
countTokens() and hasMoreTokens()?

------------------------------------------------------------
Real World Use Cases

	✔ Word Counter

	✔ Resume Analysis

	✔ Search Engine Processing

	✔ Chat Message Analysis

	✔ Text Processing Applications

------------------------------------------------------------
Practice Questions

1.

Count the words in

"Spring Boot Microservices Docker Kubernetes"

------------------------------------------------------------

2.

Read one token using nextToken() and then
print countTokens().

------------------------------------------------------------

3.

Create a program that counts words entered by
the user.

------------------------------------------------------------

4.

Can countTokens() be used inside a loop?
Explain why.

------------------------------------------------------------
*/
