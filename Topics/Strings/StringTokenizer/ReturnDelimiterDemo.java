
/*Program 06 : Returning Delimiters as Tokens

## Problem Statement

Write a Java program to demonstrate how the `returnDelims` parameter allows delimiters to be returned as tokens.

/*
------------------------------------------------------------
Program 06 : Returning Delimiters as Tokens

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringTokenizer
✔ returnDelims
✔ nextToken()
✔ Custom Delimiter

Expected Time : 20 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class ReturnDelimiterDemo {

    public static void main(String[] args) {

        String expression = "10+20-5*2";

        StringTokenizer tokenizer =
                new StringTokenizer(expression, "+-*", true);

        System.out.println("Expression");

        System.out.println(expression);

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

	Expression

	10+20-5*2

	Tokens

	10
	+
	20
	-
	5
	*
	2

------------------------------------------------------------
Memory Diagram:

	 Original String

	+----------------+
	| 10+20-5*2      |
	+----------------+

	  Delimiters

	+   -   *

      returnDelims = true

             │
             ▼

+-----+---+-----+---+---+---+---+
| 10  | + | 20  | - | 5 | * | 2 |
+-----+---+-----+---+---+---+---+

------------------------------------------------------------
Explanation:
	The String contains mathematical operators.

	Normally, StringTokenizer removes delimiters.

For example,

	new StringTokenizer(expression, "+-*")

	would produce

	10

	20

	5

	2

	The operators would not appear.

	However, this program uses

	new StringTokenizer(expression, "+-*", true);

	The third argument

	true

	tells the tokenizer to return delimiters as tokens.

	As a result, both numbers and operators are returned.

	The output becomes

	10

	+

	20

	-

	5

	*

	2

------------------------------------------------------------
Interview Notes:

	Constructor Used

	StringTokenizer(

    		String str,

    		String delim,

    		boolean returnDelims

	)

	If returnDelims = false (default behavior) Delimiters are ignored.

	If returnDelims = true Delimiters are returned as separate tokens.

------------------------------------------------------------
Important Points:

	✔ The third constructor controls whether delimiters are returned.

	✔ true returns delimiters.

	✔ false ignores delimiters.

	✔ Delimiters become individual tokens.

------------------------------------------------------------
Common Mistakes:

❌ Forgetting the third constructor exists.

❌ Assuming delimiters are always discarded.

❌ Expecting consecutive delimiters to be merged.

------------------------------------------------------------
Follow-up Interview Questions

	1. Why is returnDelims used?

	2. What happens when it is false?

	3. What happens when it is true?

	4. Can operators be treated as tokens?

	5. Which constructor supports returnDelims?

------------------------------------------------------------
Real World Use Cases

	✔ Mathematical Expression Parsing

	✔ Calculator Applications

	✔ Simple Compilers

	✔ Expression Evaluators

	✔ Text Parsing

------------------------------------------------------------
Practice Questions

1.

Tokenize

100/20+15-8

using

/, + and -

as delimiters.

Return delimiters as tokens.

------------------------------------------------------------

2.

Replace

true

with

false

and compare both outputs.

------------------------------------------------------------

3.

Create a tokenizer for

A,B,C,D

that returns commas as tokens.

------------------------------------------------------------

4.

Explain why expression parsers require
delimiters as tokens.

------------------------------------------------------------
*/
