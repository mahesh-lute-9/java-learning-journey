

/*Program 10 : MAANG String Pool Interview Challenge

## Problem Statement

Solve the following String Pool interview questions by predicting the output before executing the program. These questions cover the most frequently asked String interview concepts in product-based companies.

/*
------------------------------------------------------------
Program 10 : MAANG String Pool Interview Challenge

Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Advanced

Concepts Covered
✔ String Pool
✔ Heap Memory
✔ == Operator
✔ equals() Method
✔ intern()
✔ Compile-Time Concatenation
✔ Runtime Concatenation
✔ final Keyword
✔ String Immutability

Expected Time : 45 Minutes
------------------------------------------------------------
*/

public class StringPoolInterviewChallenge {

    public static void main(String[] args) {

        // Question 1
        String s1 = "Java";
        String s2 = "Java";

        System.out.println("Q1 : " + (s1 == s2));



        // Question 2
        String s3 = new String("Java");
        String s4 = new String("Java");

        System.out.println("Q2 : " + (s3 == s4));



        // Question 3
        System.out.println("Q3 : " + s3.equals(s4));



        // Question 4
        String s5 = "Ja" + "va";
        String s6 = "Java";

        System.out.println("Q4 : " + (s5 == s6));



        // Question 5
        String part1 = "Ja";
        String part2 = "va";

        String s7 = part1 + part2;

        System.out.println("Q5 : " + (s7 == s6));



        // Question 6
        final String part3 = "Ja";
        final String part4 = "va";

        String s8 = part3 + part4;

        System.out.println("Q6 : " + (s8 == s6));



        // Question 7
        String s9 = new String("Java").intern();

        System.out.println("Q7 : " + (s9 == s6));



        // Question 8
        String s10 = "Java";

        s10.replace("Java", "Python");

        System.out.println("Q8 : " + s10);



        // Question 9
        s10 = s10.replace("Java", "Python");

        System.out.println("Q9 : " + s10);



        // Question 10
        String s11 = "Java";

        String s12 = s11;

        s11 = "Python";

        System.out.println("Q10 : " + s12);

    }

}

/*
------------------------------------------------------------
Output

	Q1 : true

	Q2 : false

	Q3 : true

	Q4 : true

	Q5 : false

	Q6 : true

	Q7 : true

	Q8 : Java

	Q9 : Python

	Q10 : Java

------------------------------------------------------------
Memory Diagram

Question 1

	String Pool

	+---------+
	| "Java"  |
	+---------+
   	 ▲   	 ▲
   	 │   	 │
 	s1      s2

------------------------------------------------------------

Question 2

	String Pool              Heap

	+---------+          +---------+
	| "Java"  |          | "Java"  |
	+---------+          +---------+
                   		  ▲
                       		  │
                      		 s3

                    	     +---------+
                    	     | "Java"  |
                    	     +---------+
                       		  ▲
                       		  │
                      		  s4

------------------------------------------------------------

Question 10

Initially

	String Pool

	+---------+
	| "Java"  |
	+---------+
   	 ▲   	▲
   	 │      │
	 s11  s12

After,

	s11 = "Python";

	String Pool

	+---------+      +-----------+
	| "Java"  |      | "Python"  |
	+---------+      +-----------+
     	   ▲                 ▲
     	   │                 │
    	   s12              s11

------------------------------------------------------------
Explanation:

Q1

Both literals point to the same pooled object.

------------------------------------------------------------

Q2

Each new String() creates a separate Heap object.

------------------------------------------------------------

Q3

equals() compares contents.

------------------------------------------------------------

Q4

Literal concatenation happens during compilation.

------------------------------------------------------------

Q5

Variable concatenation happens during runtime.

------------------------------------------------------------

Q6

final variables become compile-time constants.

------------------------------------------------------------

Q7

intern() returns the pooled String.

------------------------------------------------------------

Q8

replace() returns a new String.

The returned object is ignored.

------------------------------------------------------------

Q9

The returned String is assigned back.

------------------------------------------------------------

Q10

Strings are immutable.

Changing s11 does not affect s12.

------------------------------------------------------------
Interview Notes

	These ten questions cover almost every basic String Pool question asked in Java interviews.

	Before answering any String question,always identify

	✔ Literal or new

	✔ String Pool or Heap

	✔ == or equals

	✔ Compile time or runtime

	✔ Immutable or mutable

Once these are identified, the answer becomes
straightforward.

------------------------------------------------------------
Important Points

	✔ String is immutable.

	✔ String Pool stores only unique literals.

	✔ Heap can contain duplicate String objects.

	✔ intern() returns the pooled reference.

	✔ == compares references.

	✔ equals() compares values.

	✔ String methods return new objects.

------------------------------------------------------------
Common Mistakes

❌ Using == to compare values.

❌ Forgetting that replace() and concat()
return new Strings.

❌ Assuming runtime concatenation uses the
String Pool.

❌ Confusing Heap Memory with the String Pool.

------------------------------------------------------------
Follow-up Interview Questions

	1. Explain String Pool in your own words.

	2. Why is String immutable?

	3. Difference between Heap and String Pool.

	4. Why is intern() useful?

	5. How many objects are created in different String creation scenarios?

------------------------------------------------------------
Real World Use Cases

	✔ JVM Internals

	✔ Memory Optimization

	✔ Enterprise Java Applications

	✔ Performance Tuning

	✔ Technical Interviews

------------------------------------------------------------
Practice Questions

1.

Create five more String Pool questions of your
own and predict their outputs.

------------------------------------------------------------

2.

Replace every == operator with equals() and
observe the results.

------------------------------------------------------------

3.

Draw the memory diagram for every question.

------------------------------------------------------------

4.

Explain every answer without executing the
program.

------------------------------------------------------------
*/
