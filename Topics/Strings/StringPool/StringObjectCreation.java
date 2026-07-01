

/*Program 05 : How Many Objects are Created?

## Problem Statement

Write a Java program to understand how many String objects are created in different scenarios involving String literals and the `new` keyword.

/*
------------------------------------------------------------
Program 05 : How Many Objects are Created?

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String Pool
✔ Heap Memory
✔ String Literals
✔ new Keyword
✔ Object Creation

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class StringObjectCreation {

    public static void main(String[] args) {

        String first = "Java";

        String second = "Java";

        String third = new String("Java");

        String fourth = new String("Programming");

        System.out.println("first == second : " + (first == second));

        System.out.println("first == third  : " + (first == third));

        System.out.println("third == fourth : " + (third == fourth));

    }

}

/*
------------------------------------------------------------
Output

	first == second : true
	first == third  : false
	third == fourth : false

------------------------------------------------------------
Explanation:

	Let's understand object creation step by step.

------------------------------------------------------------
Statement 1

	String first = "Java";

	Since "Java" is not present in the String Pool,Java creates

	✔ One String object in the String Pool.

------------------------------------------------------------
Statement 2

	String second = "Java";

	Java checks the String Pool.

	The String "Java" already exists.

Therefore,

	No new object is created.

	Both first and second refer to the same object.

------------------------------------------------------------
Statement 3

	String third = new String("Java");

	The String literal "Java" already exists in the String Pool.

	The new keyword creates a new object in Heap Memory.

Therefore,

	✔ No new object is created in the String Pool.

	✔ One new object is created in Heap Memory.

------------------------------------------------------------
Statement 4

	String fourth = new String("Programming");

	The literal "Programming" does not exist.

	Java first creates

	✔ One object in the String Pool.

Then,

	The new keyword creates

	✔ One object in Heap Memory.

Therefore,

	Two new objects are created.

------------------------------------------------------------
Total Objects Created

	"Java"

Pool Object      : 1

Heap Object      : 1

-------------------------------

	"Programming"

Pool Object      : 1

Heap Object      : 1

-------------------------------

Total Objects = 4

------------------------------------------------------------
Interview Notes

This is one of the highest-frequency Java interview questions.

	The interviewer usually asks,How many objects are created?

	Never answer immediately.

Always think about

	1. Is the literal already present in the String Pool?

	2. Is the new keyword used?


	These two questions are enough to solve almost every object creation problem.

------------------------------------------------------------
Important Points

	✔ String literals are stored in the String Pool.

	✔ Duplicate literals never create new objects.

	✔ new always creates a Heap object.

	✔ String Pool and Heap are different memory areas.

	✔ Pool objects can be shared.

------------------------------------------------------------
Common Mistakes

❌ Thinking

	new String("Java") always creates two objects.

It creates

	Two objects only if "Java" is not already present in the String Pool.

Otherwise,

	Only one Heap object is created.

------------------------------------------------------------
Top Company Interview Focus

★★★★★ Oracle

★★★★★ Amazon

★★★★★ Microsoft

★★★★★ Google

★★★★★ Adobe

★★★★★ IBM

★★★★★ Infosys Specialist Programmer

★★★★★ TCS Digital

★★★★★ Capgemini

★★★★★ Cognizant

------------------------------------------------------------
Follow-up Interview Questions

1. How many objects are created?

String s = "Java";

------------------------------------------------------------

2. How many objects are created?

String s = new String("Java");

------------------------------------------------------------

3. How many objects are created?

String s1 = "Java";
String s2 = "Java";

------------------------------------------------------------

4. How many objects are created?

String s1 = new String("Java");
String s2 = new String("Java");

------------------------------------------------------------

5. When does new String() create two objects?

------------------------------------------------------------

6. Can String Pool contain duplicate objects?

------------------------------------------------------------
Real World Use Cases

	✔ JVM Internals

	✔ Memory Optimization

	✔ Java Performance Tuning

	✔ Technical Interviews

	✔ Oracle Java Certification

------------------------------------------------------------
Practice Questions

1.

String s1 = "Java";
String s2 = "Java";
String s3 = "Java";

How many objects are created?

------------------------------------------------------------

2.

String s1 = new String("Java");

How many objects are created if "Java"
already exists in the String Pool?
/*
------------------------------------------------------------
3.

String s1 = new String("Python");

How many objects are created if "Python"
does not exist in the String Pool?

------------------------------------------------------------

4.

Why does Java use the String Pool?

------------------------------------------------------------

5.

Can two Heap objects point to the same
String Pool object?

------------------------------------------------------------
*/
