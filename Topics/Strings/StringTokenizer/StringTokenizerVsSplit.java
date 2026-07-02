

/*Program 09 : StringTokenizer vs String.split()

## Problem Statement

Write a Java program to compare `StringTokenizer` and `String.split()` by tokenizing the same String and observing their behavior.

/*
------------------------------------------------------------
Program 09 : StringTokenizer vs String.split()

Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringTokenizer
✔ split()
✔ Regular Expressions
✔ Tokenization
✔ Performance Comparison

Expected Time : 25 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class StringTokenizerVsSplit {

    public static void main(String[] args) {

        String skills = "Java,Spring Boot,Docker,MySQL";

        System.out.println("Using StringTokenizer");
        System.out.println("-----------------------------");

        StringTokenizer tokenizer =
                new StringTokenizer(skills, ",");

        while (tokenizer.hasMoreTokens()) {

            System.out.println(tokenizer.nextToken());

        }

        System.out.println();

        System.out.println("Using split()");
        System.out.println("-----------------------------");

        String[] tokens = skills.split(",");

        for (String token : tokens) {

            System.out.println(token);

        }

    }

}

/*
------------------------------------------------------------
Output:

	Using StringTokenizer
     -----------------------------

	Java
	Spring Boot
	Docker
	MySQL

	Using split()
     -----------------------------

	Java
	Spring Boot
	Docker
	MySQL

------------------------------------------------------------
Memory Diagram:

	Original String

+--------------------------------------+
| Java,Spring Boot,Docker,MySQL        |
+--------------------------------------+

              │
              ▼

       StringTokenizer

Returns

	Java

	 ↓

    Spring Boot

	↓

     Docker

	↓

      MySQL

------------------------------------------------------------

              │
              ▼

        split()

	Creates

	String[]

+--------+-------------+---------+--------+
| Java   | Spring Boot | Docker  | MySQL  |
+--------+-------------+---------+--------+

------------------------------------------------------------
Explanation:

	Both approaches divide the String into multiple parts.

	StringTokenizer returns one token at a time.

	It maintains an internal position and moves forward whenever

	nextToken() is called.

	On the other hand, split() returns an array containing all tokens.

	The array can then be processed using loops.

	Both approaches produce the same output for simple delimiter-based tokenization.

------------------------------------------------------------
Interview Notes:

	One important difference is that split() uses Regular Expressions (Regex).

For example,

	split(",") accepts a regular expression.

	StringTokenizer

	does not support Regex.

	It simply treats each delimiter character literally.

	Generally,Use StringTokenizer for simple tokenization.

	Use

	split()

	when regular expression support is required.

------------------------------------------------------------
Important Points:

	✔ split() returns a String array.

	✔ StringTokenizer returns one token at a time.

	✔ split() supports Regex.

	✔ StringTokenizer does not support Regex.

	✔ split() is the modern and recommended approach for most applications.

------------------------------------------------------------
Common Mistakes

❌ Assuming StringTokenizer supports Regex.

❌ Using split() without escaping special
Regex characters.

Example

	split(".")

matches any character.

To split using a dot, write

	split("\\\\.")

------------------------------------------------------------
Follow-up Interview Questions

	1. Which class supports Regular Expressions?

	2. Which method returns a String array?

	3. Which approach is recommended in modern Java applications?

	4. Which approach consumes one token at a time?

	5. Why is StringTokenizer considered a legacy class?

------------------------------------------------------------
Real World Use Cases:

StringTokenizer:

	✔ Simple Configuration Files

	✔ Lightweight Token Parsing

	✔ Legacy Java Applications

split():

	✔ CSV Processing

	✔ Log Parsing

	✔ Input Validation

	✔ Modern Java Applications

	✔ Spring Boot Projects

------------------------------------------------------------
Practice Questions

1.

Split the following String using both
approaches.

Java|Spring Boot|Docker|MySQL

------------------------------------------------------------

2.

Use split() with multiple delimiters.

Example

",|;"

------------------------------------------------------------

3.

Research why split() uses Regular Expressions.

------------------------------------------------------------

4.

Which approach would you choose for a Spring
Boot application? Explain your answer.

------------------------------------------------------------
*/
