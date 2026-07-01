
/*# Program 07 : String Pool vs Heap Memory

## Problem Statement

Write a Java program to understand the difference between the **String Pool** and **Heap Memory** by creating Strings using literals and the `new` keyword.

/*
------------------------------------------------------------
Program 07 : String Pool vs Heap Memory

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String Pool
✔ Heap Memory
✔ String Literals
✔ new Keyword
✔ Memory Allocation

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class StringPoolVsHeap {

    public static void main(String[] args) {

        String first = "Java";

        String second = "Java";

        String third = new String("Java");

        String fourth = new String("Programming");

        System.out.println("first == second : " + (first == second));

        System.out.println("first == third  : " + (first == third));

        System.out.println("third.equals(first) : "
                + third.equals(first));

        System.out.println("fourth : " + fourth);

    }

}

/*
------------------------------------------------------------
Output

	first == second : true

	first == third  : false

	third.equals(first) : true

	fourth : Programming

------------------------------------------------------------
Explanation

	Let's understand how memory is allocated for each statement.

------------------------------------------------------------
Statement 1

	String first = "Java";

	The JVM checks the String Pool.

	Since "Java" is not present,

	it creates one object inside the String Pool.

------------------------------------------------------------

Statement 2

	String second = "Java";

	The JVM again checks the String Pool.

	The object already exists.

Therefore,

	second points to the existing object.

	No new object is created.

------------------------------------------------------------

Statement 3

	String third = new String("Java");

	The literal "Java" already exists in the String Pool.

	The new keyword always creates another object inside Heap Memory.

Memory Representation:

                String Pool

            +---------------+
            |    "Java"     |
            +---------------+
               ▲         ▲
               │         │
            first     second
                          ▲
                          │
                     Literal Used
                          │

------------------------------------------------------------

                 Heap Memory

            +---------------+
            |    "Java"     |
            +---------------+
                   ▲
                   │
                 third

------------------------------------------------------------

Statement 4:

	String fourth = new String("Programming");

	The JVM first checks the String Pool.

	Since "Programming" is not present,

	it creates one object inside the String Pool.

Then,

	the new keyword creates another object inside Heap Memory.

Memory Representation:

            String Pool

      +---------------+
      |    "Java"     |
      +---------------+

      +--------------------+
      |   "Programming"    |
      +--------------------+

------------------------------------------------------------

           Heap Memory

      +---------------+
      |    "Java"     |
      +---------------+
             ▲
             │
           third

      +--------------------+
      |   "Programming"    |
      +--------------------+
              ▲
              │
            fourth

------------------------------------------------------------

Comparison:

	first == second

Both point to the same String Pool object.

Result

	true

------------------------------------------------------------

	first == third

Different objects.

One is inside the String Pool.

The other is inside Heap Memory.

Result:

	false

------------------------------------------------------------

	third.equals(first)

Both contain the same value.

Result:

	true

------------------------------------------------------------
Interview Notes

Remember these rules.

Rule 1

	String Literal

		↓

	Stored in the String Pool.

------------------------------------------------------------

Rule 2

	new String()

		↓

	Always creates a Heap object.

------------------------------------------------------------

Rule 3

	The String Pool avoids duplicate String objects.

------------------------------------------------------------

Rule 4

	Heap Memory can contain duplicate String objects.

------------------------------------------------------------

Rule 5

	The String Pool improves memory efficiency by sharing immutable String objects.

------------------------------------------------------------
Important Points

	✔ String Pool stores unique String literals.

	✔ Heap Memory can contain duplicate objects.

	✔ String literals are reused.

	✔ new String() bypasses object reuse in the Heap.

	✔ == compares references.

	✔ equals() compares contents.

------------------------------------------------------------
Common Mistakes

❌ Thinking the String Pool and Heap Memory are
the same.

❌ Assuming new String() stores objects only in
Heap Memory.

❌ Forgetting that String literals are checked
in the String Pool first.

------------------------------------------------------------
Top Company Interview Focus

★★★★★ Oracle

★★★★★ Google

★★★★★ Amazon

★★★★★ Microsoft

★★★★★ Adobe

★★★★★ IBM

★★★★★ JP Morgan Chase

★★★★★ Goldman Sachs

★★★★★ Infosys Specialist Programmer

★★★★★ TCS Digital

One of the most frequently asked JVM memory
questions.

------------------------------------------------------------
Follow-up Interview Questions

1. What is the difference between String Pool
and Heap Memory?

2. Why are String literals stored separately?

3. Can Heap Memory contain duplicate Strings?

4. Why is String immutable?

5. Is the String Pool inside Heap Memory in
modern JVMs?

6. Why is String Pool faster than creating new
objects repeatedly?

------------------------------------------------------------
Real World Use Cases

	✔ JVM Memory Optimization

	✔ Performance Tuning

	✔ High-Performance Applications

	✔ Spring Boot Applications

	✔ Enterprise Java Development

	✔ Technical Interviews

------------------------------------------------------------
Practice Questions

1.

Draw the memory diagram.

String s1 = "Java";
String s2 = new String("Java");

------------------------------------------------------------

2.

How many objects are created?
/*
String s1 = new String("Python");

------------------------------------------------------------

3.

Can two Heap objects point to the same String
Pool object?

------------------------------------------------------------

4.

Why is String Pool memory-efficient?

------------------------------------------------------------

5.

Explain String Pool vs Heap Memory to an
interviewer without writing code.

------------------------------------------------------------
*/
