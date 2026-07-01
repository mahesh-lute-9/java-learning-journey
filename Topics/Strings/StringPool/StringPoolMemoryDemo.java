
/*
# Program 06 : Understanding String Pool Memory

## Problem Statement

Write a Java program to understand how the JVM stores String literals in the String Pool and how multiple references point to the same object.

/*
------------------------------------------------------------
Program 06 : Understanding String Pool Memory

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String Pool
✔ JVM Memory
✔ Heap Memory
✔ Reference Variables

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class StringPoolMemoryDemo {

    public static void main(String[] args) {

        String first = "Java";

        String second = "Java";

        String third = "Programming";

        System.out.println("first == second : " + (first == second));

        System.out.println("first == third  : " + (first == third));

    }

}

/*
------------------------------------------------------------
Output

	first == second : true

	first == third  : false

------------------------------------------------------------
Explanation

	When the JVM encounters

	String first = "Java";

	it checks whether "Java" already exists in the String Pool.

	Since it does not exist, a new String object is created in the String Pool.

Memory Representation:

                String Pool

            +---------------+
            |    "Java"     |
            +---------------+
                   ▲
                   │
                first

------------------------------------------------------------

Now,

	String second = "Java";

	The JVM again checks the String Pool.

	It finds that "Java" already exists.

	Instead of creating another object, second points to the existing object.

Memory Representation:

                String Pool

            +---------------+
            |    "Java"     |
            +---------------+
               ▲         ▲
               │         │
            first     second

No new object is created.

------------------------------------------------------------

Now,

	String third = "Programming";

	The JVM checks the String Pool.

	Since "Programming" is not available,it creates another object.

Memory Representation:

             String Pool

        +---------------+
        |    "Java"     |
        +---------------+
           ▲         ▲
           │         │
        first     second

        +--------------------+
        |   "Programming"    |
        +--------------------+
                  ▲
                  │
                third

------------------------------------------------------------

Now compare

	first == second

	Both variables point to the same object.

Result

	true

------------------------------------------------------------

Now compare

	first == third

	Both variables point to different objects.

Result

	false

------------------------------------------------------------
Interview Notes

	The String Pool follows the Flyweight Design Pattern.

	Instead of creating duplicate objects,the JVM reuses existing String literals.

This improves:

	✔ Memory Usage

	✔ Performance

	✔ Garbage Collection Efficiency

------------------------------------------------------------
Important Points

	✔ String literals are always stored in the String Pool.

	✔ Duplicate literals never create duplicate objects.

	✔ Multiple variables can point to one pooled object.

	✔ String Pool exists to save memory.

------------------------------------------------------------
Common Mistakes

❌ Thinking every String declaration creates a
new object.

❌ Assuming two identical String literals create
two different objects.

❌ Confusing Heap Memory with the String Pool.

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

This is one of the most important JVM interview
topics.

------------------------------------------------------------
Follow-up Interview Questions

	1. What is the String Pool?

	2. Why was the String Pool introduced?

	3. Can duplicate literals exist in the String Pool?

	4. Is the String Pool part of Heap Memory?

	5. What design pattern is used by the String Pool?

------------------------------------------------------------
Real World Use Cases

	✔ JVM Optimization

	✔ Memory Management

	✔ Enterprise Applications

	✔ High-Performance Systems

	✔ Java Interview Preparation

------------------------------------------------------------
Practice Questions

1.

String s1 = "ABC";
String s2 = "ABC";

How many objects are created?

------------------------------------------------------------

2.

String s1 = "Java";
String s2 = "Programming";
String s3 = "Java";

Draw the String Pool.

------------------------------------------------------------

3.

Why does the JVM reuse String literals?

------------------------------------------------------------

4.

Can two variables point to the same String
object?

------------------------------------------------------------

5.

Explain the Flyweight Design Pattern used by
the String Pool.

------------------------------------------------------------
*/
