

/*Program 02 : Using `nextToken()`

## Problem Statement

Write a Java program to retrieve each token one by one using the `nextToken()` method of the `StringTokenizer` class.

---

## Code

```java id="4fz2vk"
/*
------------------------------------------------------------
Program 02 : Using nextToken()

Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringTokenizer
✔ nextToken()
✔ hasMoreTokens()
✔ Token Traversal

Expected Time : 10 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class NextTokenDemo {

    public static void main(String[] args) {

        String technologies = "Java SpringBoot MySQL Docker";

        StringTokenizer tokenizer = new StringTokenizer(technologies);

        while (tokenizer.hasMoreTokens()) {

            String token = tokenizer.nextToken();

            System.out.println(token);

        }

    }

}

/*
------------------------------------------------------------
Output

	Java
	SpringBoot
	MySQL
	Docker

------------------------------------------------------------
Memory Diagram

	Original String

+--------------------------------+
| Java SpringBoot MySQL Docker   |
+--------------------------------+

                │
                ▼

         StringTokenizer

+--------+------------+---------+---------+
| Java   | SpringBoot | MySQL   | Docker  |
+--------+------------+---------+---------+

                │
                ▼

	nextToken()

1st Call  → Java

2nd Call  → SpringBoot

3rd Call  → MySQL

4th Call  → Docker

------------------------------------------------------------
Explanation:

	The StringTokenizer object divides the given String into multiple tokens.

	Initially, the tokenizer points to the first token.

	The hasMoreTokens() method checks whether another token is available.

	Inside the loop,

	nextToken() returns the current token and automatically moves to the next one.

	This process continues until all tokens have been processed.

	After the last token,

	hasMoreTokens() returns false,

	so the loop stops.

------------------------------------------------------------
Interview Notes:

	Think of StringTokenizer like a cursor.

	Initially,

	the cursor points to the first token.

	Every call to nextToken()

	moves the cursor one position forward.

	Once a token has been consumed,

	it cannot be accessed again unless a new StringTokenizer object is created.

------------------------------------------------------------
Important Points:

	✔ nextToken() returns one token at a time.

	✔ Every call moves to the next token.

	✔ Tokens cannot be revisited.

	✔ hasMoreTokens() should always be checked before calling nextToken().

------------------------------------------------------------
Common Mistakes:

❌ Calling nextToken() after all tokens have
been consumed.

❌ Expecting nextToken() to return all tokens
at once.

❌ Forgetting that StringTokenizer maintains
its current position internally.

------------------------------------------------------------
Follow-up Interview Questions

	1. What does nextToken() return?

	2. Can the same token be read twice?

	3. Why should hasMoreTokens() be used?

	4. What happens if nextToken() is called when no tokens are available?

	5. How does StringTokenizer know which token to return next?

------------------------------------------------------------
Real World Use Cases:

	✔ Reading Commands

	✔ Parsing Configuration Files

	✔ Processing User Input

	✔ Reading Space-Separated Data

	✔ Simple Text Processing

------------------------------------------------------------
Practice Questions

1.

Print only the first two tokens.

------------------------------------------------------------

2.

Store each token inside an ArrayList.

------------------------------------------------------------

3.

Find the longest token from a sentence.

------------------------------------------------------------

4.

Print each token along with its length.

------------------------------------------------------------
*/
