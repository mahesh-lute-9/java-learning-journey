
/*Program 09 : String Pool Output Prediction (Level 1)

## Problem Statement

Predict the output of the following String Pool scenarios and understand the JVM behavior behind each one.

/*
------------------------------------------------------------
Program 09 : String Pool Output Prediction (Level 1)

Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ String Pool
✔ Heap Memory
✔ == Operator
✔ equals() Method
✔ intern()
✔ Compile-Time Concatenation
✔ Runtime Concatenation

Expected Time : 30 Minutes
------------------------------------------------------------
*/

public class StringPoolOutputPrediction {

    public static void main(String[] args) {

        // Scenario 1
        String s1 = "Java";
        String s2 = "Java";

        System.out.println("Scenario 1 : " + (s1 == s2));



        // Scenario 2
        String s3 = new String("Java");
        String s4 = "Java";

        System.out.println("Scenario 2 : " + (s3 == s4));



        // Scenario 3
        String s5 = new String("Java");
        String s6 = s5.intern();

        System.out.println("Scenario 3 : " + (s6 == s4));



        // Scenario 4
        String s7 = "Ja" + "va";
        String s8 = "Java";

        System.out.println("Scenario 4 : " + (s7 == s8));



        // Scenario 5
        String first = "Ja";
        String second = "va";

        String s9 = first + second;

        System.out.println("Scenario 5 : " + (s9 == s8));



        // Scenario 6
        final String f1 = "Ja";
        final String f2 = "va";

        String s10 = f1 + f2;

        System.out.println("Scenario 6 : " + (s10 == s8));



        // Scenario 7
        String s11 = "Java";

        s11.concat(" Programming");

        System.out.println("Scenario 7 : " + s11);



        // Scenario 8
        String s12 = "Java";

        s12 = s12.concat(" Programming");

        System.out.println("Scenario 8 : " + s12);

    }

}

/*
------------------------------------------------------------
Output

	Scenario 1 : true

	Scenario 2 : false

	Scenario 3 : true

	Scenario 4 : true

	Scenario 5 : false

	Scenario 6 : true

	Scenario 7 : Java

	Scenario 8 : Java Programming

------------------------------------------------------------
Memory Diagram:

Scenario 1:

	String Pool

	+---------+
	| "Java"  |
	+---------+
   	▲  	 ▲
   	│   	 │
  	s1  	s2

------------------------------------------------------------

Scenario 2:

	String Pool              Heap

	+---------+          +---------+
	| "Java"  |          | "Java"  |
	+---------+          +---------+
     	    ▲                    ▲
     	    │                    │
    	   s4                   s3

------------------------------------------------------------

Scenario 3:

	intern()

	Heap Object

           │
           ▼

	String Pool Object

	s6 now points to the pooled String.

------------------------------------------------------------
Explanation:

Scenario 1

	Both String literals refer to the same object in the String Pool.

Result:

	true

------------------------------------------------------------

Scenario 2:

	new String() creates a Heap object.

	The literal points to the String Pool object.

	Different references.

Result:

	false

------------------------------------------------------------

Scenario 3:

	intern() returns the String Pool reference.

	Both variables point to the pooled object.

Result:

	true

------------------------------------------------------------

Scenario 4:

	Both values are known during compilation.

	The compiler converts

	"Ja" + "va"

	into

	"Java"

Result:

	true

------------------------------------------------------------

Scenario 5:

	first and second are normal variables.

	Concatenation happens during runtime.

	A new String object is created.

Result:

	false

------------------------------------------------------------

Scenario 6:

	Both variables are final.

	The compiler treats them as constants.

	Concatenation happens during compilation.

Result:

	true

------------------------------------------------------------

Scenario 7:

	concat() never modifies the original String.

	The returned object is ignored.

Result:

	Java

------------------------------------------------------------

Scenario 8:

	The returned String is assigned back.

Result:

	Java Programming

------------------------------------------------------------
Interview Notes:

If you understand these eight scenarios,you can answer nearly 80% of String Pool questions asked in Java interviews.

Whenever you see a String question,

check these four things:

	1. Literal or new?

	2. == or equals()?

	3. Compile time or runtime?

	4. intern() used or not?

------------------------------------------------------------
Important Points

	✔ String literals use the String Pool.

	✔ new String() creates Heap objects.

	✔ intern() returns the pooled object.

	✔ == compares references.

	✔ equals() compares values.

	✔ concat() returns a new String.

	✔ final variables become compile-time constants.

------------------------------------------------------------
Common Mistakes

❌ Assuming every concatenation uses the
String Pool.

❌ Forgetting to assign concat().

❌ Comparing Strings using ==.

❌ Thinking intern() returns the Heap object.

------------------------------------------------------------
Follow-up Interview Questions

	1. Why does Scenario 5 return false?

	2. Why does final change the result?

	3. What if equals() is used instead of ==?

	4. Which scenarios create Heap objects?

	5. Which scenarios use the String Pool?

------------------------------------------------------------
Real World Use Cases

	✔ JVM Internals

	✔ Memory Optimization

	✔ Performance Tuning

	✔ Technical Interviews

	✔ Oracle Java Certification

------------------------------------------------------------
Practice Questions

Modify every scenario by replacing

==

with

equals()

and predict the output before executing.

------------------------------------------------------------
*/
