/*Program 03 : Demonstrate the `intern()` Method

## Problem Statement

Write a Java program to demonstrate how the `intern()` method works and how it returns a reference from the String Pool.

/*
------------------------------------------------------------
Program 03 : Demonstrate the intern() Method

Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ String Pool
✔ intern()
✔ Heap Memory
✔ == Operator

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class InternMethodDemo {

    public static void main(String[] args) {

        String first = new String("Java");

        String second = first.intern();

        String third = "Java";

        System.out.println("first == second : " + (first == second));

        System.out.println("second == third : " + (second == third));

        System.out.println("first.equals(third) : " + first.equals(third));

    }

}

/*
------------------------------------------------------------
Output

	first == second : false
	second == third : true
	first.equals(third) : true

------------------------------------------------------------
Explanation

	First, a new String object is created using the new keyword.

	String first = new String("Java");

	This object is stored in Heap Memory.

Next,

	String second = first.intern();

	The intern() method checks whether an identical String already exists in the String Pool.

	If it exists, it returns the reference of the pooled String.

	If it does not exist, Java adds the String to the String Pool and returns its reference.

Finally,

	String third = "Java";

	Since "Java" already exists in the String Pool,
	third refers to the pooled object.

Therefore,

	first == second

	returns false because first points to the Heap object while second points to the String Pool object.

	second == third

	returns true because both variables point to the same object in the String Pool.

	first.equals(third)

	returns true because equals() compares the contents of the Strings.

------------------------------------------------------------
Interview Notes

	The intern() method is used to obtain the String Pool reference of a String.

Method Signature

	public native String intern();

Remember:

	new String("Java")

	creates a Heap object.

	intern()

returns the String Pool reference.

------------------------------------------------------------
Important Points

	✔ intern() returns a reference from the String Pool.

	✔ It does not create another Heap object.

	✔ intern() is useful when many duplicate Strings exist and memory optimization is required.

	✔ == compares references.

	✔ equals() compares values.

------------------------------------------------------------
Common Mistakes

	❌ Thinking intern() creates another object.

	❌ Assuming intern() returns the Heap object.

	❌ Using == before understanding String Pool.

------------------------------------------------------------
Top Company Interview Focus

★★★★★ Oracle

★★★★★ Amazon

★★★★★ Microsoft

★★★★★ Google

★★★★★ Adobe

★★★★☆ Atlassian

★★★★☆ Walmart Global Tech

This is a very common JVM interview topic.

------------------------------------------------------------
Follow-up Interview Questions

	1. What does intern() return?

	2. Why is intern() used?

	3. Does intern() create a new object?

	4. Where is the returned object stored?

	5. Difference between Heap Memory and String Pool?

------------------------------------------------------------
Real World Use Cases

	✔ Memory optimization

	✔ JVM internals

	✔ High-performance applications

	✔ Applications processing millions of duplicate Strings

------------------------------------------------------------
Practice Questions

1.

String s1 = new String("Java");
String s2 = s1.intern();

Predict the output of

s1 == s2

----------------------------

2.

String s1 = "Java";
String s2 = new String("Java").intern();

Predict the output of

s1 == s2

----------------------------

3.

Explain why intern() can reduce memory usage.

------------------------------------------------------------
*/
