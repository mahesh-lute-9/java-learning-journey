

/*Program 05 : Using Multiple Delimiters

## Problem Statement

Write a Java program to tokenize a String using multiple delimiters.

/*
------------------------------------------------------------
Program 05 : Using Multiple Delimiters

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringTokenizer
✔ Multiple Delimiters
✔ nextToken()
✔ hasMoreTokens()

Expected Time : 15 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class MultipleDelimiterDemo {

    public static void main(String[] args) {

        String employeeData = "101,Rahul|Pune;75000:Developer";

        StringTokenizer tokenizer =
                new StringTokenizer(employeeData, ",|;:");

        System.out.println("Employee Details");

        while (tokenizer.hasMoreTokens()) {

            System.out.println(tokenizer.nextToken());

        }

    }

}

/*
------------------------------------------------------------
Output

	Employee Details

	101
	Rahul
	Pune
	75000
	Developer

------------------------------------------------------------
Memory Diagram:

		Original String

+--------------------------------------------------+
| 101,Rahul|Pune;75000:Developer                   |
+--------------------------------------------------+

		Delimiters

	     ,    |    ;    :

             	   │
                   ▼

            StringTokenizer

+------+--------+------+-------+------------+
| 101  | Rahul  | Pune | 75000 | Developer  |
+------+--------+------+-------+------------+

------------------------------------------------------------
Explanation:

	The String contains multiple separators.

	101,Rahul|Pune;75000:Developer 
	
	Instead of using only one delimiter, we pass multiple delimiters to the constructor.

	StringTokenizer tokenizer = new StringTokenizer(employeeData, ",|;:");

	Every character inside ",|;:"

	is treated as an individual delimiter.
	
	Whenever any one of these characters appears, StringTokenizer starts a new token.

	The delimiters themselves are discarded and are not returned as tokens.

	The while loop continues until every token has been processed.

------------------------------------------------------------
Interview Notes:

	The delimiter String ",|;:" does not represent one complete delimiter.

	Instead, each character is considered a separate delimiter.

	That means

	,

	|

	;

	:

	are four different delimiters.

------------------------------------------------------------
Important Points:

	✔ Multiple delimiters can be specified in a single String.

	✔ Every character in the delimiter String is treated separately.

	✔ Delimiters are not included in the output.

	✔ Tokens are returned one by one.

------------------------------------------------------------
Common Mistakes

❌ Thinking ",|;:" is one delimiter.

❌ Expecting delimiters to appear in the output.

❌ Forgetting that whitespace can also be added
as a delimiter.

------------------------------------------------------------
Follow-up Interview Questions

	1. Can StringTokenizer use multiple delimiters?

	2. How are multiple delimiters specified?

	3. Does StringTokenizer support regular expressions?

	4. What happens if two delimiters appear consecutively?

	5. Can whitespace be used along with other delimiters?

------------------------------------------------------------
Real World Use Cases:

	✔ Employee Record Parsing

	✔ Configuration Files

	✔ Log File Processing

	✔ Data Migration

	✔ Command Parsing

------------------------------------------------------------
Practice Questions

1.

Tokenize the following String.

Java|Spring,Docker;MySQL:Git

using

",|;:"

as delimiters.

------------------------------------------------------------

2.

Add whitespace as another delimiter and
observe the output.

------------------------------------------------------------

3.

Create a program that accepts a String and
multiple delimiters from the user.

------------------------------------------------------------

4.

Count the number of tokens after applying
multiple delimiters.

------------------------------------------------------------
*/
