

/*Program 10 : StringTokenizer Interview Challenge

## Problem Statement

Predict the output of the following `StringTokenizer` programs and understand the concepts behind each scenario.

/*
------------------------------------------------------------
Program 10 : StringTokenizer Interview Challenge

Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Advanced

Concepts Covered
✔ Default Delimiter
✔ Custom Delimiter
✔ Multiple Delimiters
✔ countTokens()
✔ nextToken()
✔ hasMoreTokens()
✔ returnDelims
✔ StringTokenizer vs split()

Expected Time : 40 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class StringTokenizerInterviewChallenge {

    public static void main(String[] args) {

        // Scenario 1
        StringTokenizer tokenizer1 =
                new StringTokenizer("Java Spring Boot");

        System.out.println("Scenario 1 : "
                + tokenizer1.countTokens());



        // Scenario 2
        StringTokenizer tokenizer2 =
                new StringTokenizer("Java,Python,C++", ",");

        System.out.println("Scenario 2 : "
                + tokenizer2.nextToken());



        // Scenario 3
        System.out.println("Scenario 3 : "
                + tokenizer2.countTokens());



        // Scenario 4
        StringTokenizer tokenizer3 =
                new StringTokenizer("A|B|C", "|");

        while (tokenizer3.hasMoreTokens()) {

            System.out.println(tokenizer3.nextToken());

        }



        // Scenario 5
        StringTokenizer tokenizer4 =
                new StringTokenizer("10+20-5", "+-", true);

        while (tokenizer4.hasMoreTokens()) {

            System.out.print(tokenizer4.nextToken() + " ");

        }

        System.out.println();



        // Scenario 6
        StringTokenizer tokenizer5 =
                new StringTokenizer("Java,Spring,Docker", ",");

        tokenizer5.nextToken();

        System.out.println("Scenario 6 : "
                + tokenizer5.countTokens());



        // Scenario 7
        String skills = "Java,Spring,Docker";

        String[] tokens = skills.split(",");

        System.out.println("Scenario 7 : "
                + tokens.length);

    }

}

/*
------------------------------------------------------------
Output

	Scenario 1 : 3

	Scenario 2 : Java

	Scenario 3 : 2

	A
	B
	C

	10 + 20 - 5

	Scenario 6 : 2

	Scenario 7 : 3

------------------------------------------------------------
Memory Diagram:

Scenario 1

+----------------------+
| Java Spring Boot     |
+----------------------+

        ▼

+--------+--------+------+
| Java   | Spring | Boot |
+--------+--------+------+

------------------------------------------------------------

Scenario 5

Original String

	10+20-5

 returnDelims = true

         ▼

+-----+---+-----+---+---+
| 10  | + | 20  | - | 5 |
+-----+---+-----+---+---+

------------------------------------------------------------
Explanation

Scenario 1

countTokens() returns the total number of available tokens.

------------------------------------------------------------

Scenario 2

nextToken() returns the first token.

Result

	Java

------------------------------------------------------------

Scenario 3

	One token has already been consumed.

	Only two tokens remain.

------------------------------------------------------------

Scenario 4

hasMoreTokens() continues until every token has been processed.

------------------------------------------------------------

Scenario 5

returnDelims = true returns operators as separate tokens.

------------------------------------------------------------

Scenario 6

After reading one token, countTokens() returns the remaining tokens.

------------------------------------------------------------

Scenario 7

split() returns a String array.

The array contains three elements.

------------------------------------------------------------
Interview Notes

Before answering any StringTokenizer question,

identify

1. Which delimiter is used?

2. Has nextToken() already been called?

3. Is returnDelims true or false?

4. Is countTokens() counting total tokens or
remaining tokens?

5. Is the program using split() instead?

These five questions solve most interview
problems.

------------------------------------------------------------
Important Points

	✔ countTokens() returns remaining tokens.

	✔ nextToken() moves the internal cursor.

	✔ hasMoreTokens() prevents exceptions.

	✔ returnDelims controls delimiter visibility.

	✔ split() returns an array.

	✔ StringTokenizer is a legacy class.

------------------------------------------------------------
Common Mistakes

❌ Assuming countTokens() always returns the
original count.

❌ Forgetting that nextToken() changes the
current position.

❌ Expecting delimiters to appear when
returnDelims is false.

❌ Confusing split() with StringTokenizer.

------------------------------------------------------------
Follow-up Interview Questions

	1. Why is StringTokenizer considered a legacy class?

	2. Which method returns remaining tokens?

	3. Which constructor returns delimiters?

	4. Which API supports Regular Expressions?

	5. When should split() be preferred?

------------------------------------------------------------
Real World Use Cases

	✔ Configuration Parsing

	✔ Legacy Java Applications

	✔ Command Processing

	✔ Lightweight Token Parsing

	✔ Java Interview Preparation

------------------------------------------------------------
Practice Questions

1.

Predict the output.

StringTokenizer tokenizer =
new StringTokenizer("A:B:C:D", ":");

System.out.println(tokenizer.countTokens());

------------------------------------------------------------

2.

Create a tokenizer using

",|;"

as delimiters.

------------------------------------------------------------

3.

Use returnDelims = true

and predict the output.

------------------------------------------------------------

4.

Replace StringTokenizer with split()

and compare both approaches.

------------------------------------------------------------
*/
