
/*Program 04 : Compile-Time vs Runtime String Concatenation

## Problem Statement

Write a Java program to demonstrate the difference between **compile-time** and **runtime** String concatenation and how they affect the String Pool.

/*
------------------------------------------------------------
Program 04 : Compile-Time vs Runtime String Concatenation

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String Pool
✔ Compile-Time Concatenation
✔ Runtime Concatenation
✔ == Operator
✔ final Keyword

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class CompileTimeVsRuntimeConcatenation {

    public static void main(String[] args) {

        String first = "Hello";
        String second = "World";

        String compileTime = "Hello" + "World";

        String runtime = first + second;

        String literal = "HelloWorld";

        final String finalFirst = "Hello";
        final String finalSecond = "World";

        String finalConcatenation = finalFirst + finalSecond;

        System.out.println("compileTime == literal      : "
                + (compileTime == literal));

        System.out.println("runtime == literal          : "
                + (runtime == literal));

        System.out.println("finalConcatenation == literal : "
                + (finalConcatenation == literal));

    }

}

/*
------------------------------------------------------------
Output

	compileTime == literal      : true

	runtime == literal          : false

	finalConcatenation == literal : true

------------------------------------------------------------
Explanation:

	Java performs compile-time optimization whenever all values are known during compilation.

Example:

	"Hello" + "World"

	is converted by the compiler into

	"HelloWorld"

Therefore,

	compileTime

	points to the same object present in the String Pool.

Hence,

	compileTime == literal returns true.

------------------------------------------------------------

Now consider

	String runtime = first + second;

Here,

	first and second are variables.

	Their values are known only when the program runs.

	Therefore, Java creates a new String object during execution.

	This object is not the same object present inside the String Pool.

Hence,

	runtime == literal
	returns false.

------------------------------------------------------------

Now look at

	final String finalFirst = "Hello";
	final String finalSecond = "World";

	Since both variables are declared as final, their values cannot change.

	The compiler treats them as compile-time constants.

Therefore,

	finalFirst + finalSecond is optimized during compilation into

	"HelloWorld"

Hence,

	finalConcatenation == literal

	returns true.

------------------------------------------------------------
Interview Notes

Remember these three rules.

Rule 1

	Literal + Literal

		↓

	Compile Time

		↓

	Stored in String Pool

------------------------------------------------------------

Rule 2

	Variable + Variable

		↓

	    Runtime

		↓

	Creates a New String Object

------------------------------------------------------------

Rule 3

	final Variable + final Variable

		↓

	Compile Time

		↓

	Uses String Pool

	These three rules are frequently asked in Java interviews.

------------------------------------------------------------
Important Points

	✔ Compile-time concatenation uses the String Pool.

	✔ Runtime concatenation creates a new String.

	✔ final variables behave like literals during compilation.

	✔ == compares object references.

	✔ equals() compares String contents.

------------------------------------------------------------
Common Mistakes

	❌ Assuming every concatenation uses the String Pool.

	❌ Forgetting that variables are evaluated at runtime.

	❌ Confusing final variables with normal variables.

------------------------------------------------------------
Top Company Interview Focus

★★★★★ Oracle

★★★★★ Amazon

★★★★★ Microsoft

★★★★★ Google

★★★★★ Adobe

★★★★☆ Atlassian

★★★★☆ IBM

★★★★★ Infosys Specialist Programmer

★★★★★ TCS Digital

------------------------------------------------------------
Follow-up Interview Questions

	1. What is compile-time concatenation?

	2. What is runtime concatenation?

	3. Why does final affect concatenation?

	4. Which object is stored in the String Pool?

	5. Why does runtime concatenation create a new object?

------------------------------------------------------------
Real World Use Cases

	✔ JVM optimization

	✔ Memory optimization

	✔ Java compiler internals

	✔ Performance tuning

	✔ Technical interviews

------------------------------------------------------------
Practice Questions

1.

String s1 = "Java";
String s2 = "Programming";

String s3 = "JavaProgramming";

System.out.println((s1 + s2) == s3);

Predict the output.

------------------------------------------------------------

2.

final String s1 = "Java";
final String s2 = "Programming";

String s3 = "JavaProgramming";

System.out.println((s1 + s2) == s3);

Predict the output.

------------------------------------------------------------

3.

Why does adding the final keyword change
the result?

------------------------------------------------------------
*/
