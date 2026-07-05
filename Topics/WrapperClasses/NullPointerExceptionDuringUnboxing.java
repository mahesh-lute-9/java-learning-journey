

/*
------------------------------------------------------------
Program 13 : NullPointerException During Unboxing

Language    : Java
Difficulty  : ⭐⭐⭐ Advanced

Concepts Covered

	✔ Unboxing
	✔ NullPointerException
	✔ Wrapper Classes
	✔ Primitive Data Types
	✔ Null Safety

Expected Time : 20 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate how automatic
	Unboxing can throw a NullPointerException when the
	Wrapper object contains a null value.

	Also demonstrate the correct way to avoid this problem.

------------------------------------------------------------
*/

public class NullPointerExceptionDuringUnboxing {

    public static void main(String[] args) {

        Integer employeeAge = null;

        try {

            int age = employeeAge;

            System.out.println(age);

        } catch (NullPointerException exception) {

            System.out.println("Exception : " + exception);

        }

        System.out.println();

        Integer managerAge = 30;

        int validAge = managerAge;

        System.out.println("Manager Age : " + validAge);

    }

}

/*
------------------------------------------------------------
Output

	Exception : java.lang.NullPointerException

	Manager Age : 30

------------------------------------------------------------
Explanation

	Wrapper Classes can store

	null

	because they are objects.

	Primitive data types cannot store

	null.

	When the compiler encounters

	int age = employeeAge;

	it automatically converts it into

	int age = employeeAge.intValue();

	Since

	employeeAge

	contains

	null,

	there is no object on which

	intValue()

	can be invoked.

	As a result,

	Java throws

	NullPointerException.

------------------------------------------------------------
Internal Working:

	Source Code

	↓

	int age = employeeAge;

	Compiler Converts It Into

	↓

	int age = employeeAge.intValue();

	If

	employeeAge

	↓

	null

	then

	null.intValue()

	↓

	NullPointerException

------------------------------------------------------------
Important Points

	✔ Wrapper objects can store null values.

	✔ Primitive variables cannot store null.

	✔ Unboxing internally invokes methods such as

	intValue()

	doubleValue()

	longValue()

	etc.

	✔ Automatic Unboxing may throw
	NullPointerException.

	✔ Always validate nullable Wrapper objects before
	Unboxing.

------------------------------------------------------------
Common Mistakes

❌ Assuming automatic Unboxing is always safe.

------------------------------------------------------------

❌ Forgetting that database values may be null.

------------------------------------------------------------

❌ Reading nullable API responses directly into
primitive variables.

------------------------------------------------------------

❌ Ignoring null checks before performing arithmetic
operations.

------------------------------------------------------------
Best Practices

✔ Check for null before Unboxing.

Example

if (employeeAge != null) {

    int age = employeeAge;

}

------------------------------------------------------------

✔ Use primitive types when null values are not required.

------------------------------------------------------------

✔ Use Wrapper Classes only when object behavior or null
support is needed.

------------------------------------------------------------
Follow-up Interview Questions

1.

Can Unboxing throw NullPointerException?

------------------------------------------------------------

2.

Why can Wrapper Classes store null values?

------------------------------------------------------------

3.

What does the compiler generate during Unboxing?

------------------------------------------------------------

4.

How can this exception be prevented?

------------------------------------------------------------

5.

When should Wrapper Classes be preferred over primitive
data types?

------------------------------------------------------------
Real World Use Cases

	✔ Reading Database Records

	✔ REST API Responses

	✔ Spring Boot Applications

	✔ Hibernate Entities

	✔ JSON Deserialization

	✔ Configuration Management

------------------------------------------------------------
Practice Questions

1.

Replace

Integer

with

Double

and observe the behavior.

------------------------------------------------------------

2.

Write a method that safely converts an

Integer

to

int

without throwing an exception.

------------------------------------------------------------

3.

Read a nullable Wrapper object from a method and
provide a default value if it is null.

------------------------------------------------------------

4.

Research

Objects.requireNonNullElse()

introduced in Java 9 and rewrite the program using it.

------------------------------------------------------------

Quick Revision

	Wrapper

	↓

	Can Store null

-------------------------

	Primitive

	↓

	Cannot Store null

-------------------------

	Unboxing

	↓

	intValue()

-------------------------

	null

	↓

	intValue()

	↓

	NullPointerException

-------------------------

	Always

	↓

	Check or null

	Before Unboxing
`
------------------------------------------------------------
*/
