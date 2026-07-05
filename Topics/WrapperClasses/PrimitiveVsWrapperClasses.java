

/*
------------------------------------------------------------
Program 02 : Primitive vs Wrapper Classes

Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered

	✔ Primitive Data Types
	✔ Wrapper Classes
	✔ Object Creation
	✔ getClass()

Expected Time : 15 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate the difference
	between primitive data types and Wrapper Classes.

	Print the primitive value, Wrapper object, and the
	runtime class of the Wrapper object.

------------------------------------------------------------
*/

public class PrimitiveVsWrapperClasses {

    public static void main(String[] args) {

        int employeeAge = 25;

        Integer employeeAgeObject = 25;

        System.out.println("Primitive Value : " + employeeAge);

        System.out.println("Wrapper Object  : " + employeeAgeObject);

        System.out.println("Wrapper Class   : "
                + employeeAgeObject.getClass().getName());

    }

}

/*
------------------------------------------------------------
Output

	Primitive Value : 25

	Wrapper Object  : 25

	Wrapper Class   : java.lang.Integer

------------------------------------------------------------
Explanation

	A primitive data type stores the actual value directly.

	Example

	employeeAge

	↓

	25

	A Wrapper Class stores the value inside an object.

	The

	getClass()

	method confirms that

	employeeAgeObject

	is an object of type

	java.lang.Integer.

	Primitive data types cannot call methods because they
	are not objects.

	Wrapper Classes can call methods because they are
	objects.

------------------------------------------------------------
Important Points

	✔ Primitive data types are not objects.

	✔ Wrapper Classes are objects.

	✔ Wrapper Classes belong to the java.lang package.

	✔ Primitive variables cannot call methods.

	✔ Wrapper objects inherit methods from Object.

------------------------------------------------------------
Common Mistakes

❌ Trying to call methods on primitive variables.

Example

employeeAge.getClass();

This causes a compilation error because

employeeAge

is not an object.

------------------------------------------------------------

❌ Assuming both variables occupy memory in the same way.

Primitive variables store values directly.

Wrapper variables store object references.

------------------------------------------------------------

❌ Thinking Wrapper Classes improve performance.

Primitive data types are generally faster and use less
memory.

------------------------------------------------------------
Follow-up Interview Questions

1.

What is the difference between a primitive data type
and a Wrapper Class?

------------------------------------------------------------

2.

Can primitive variables call methods?

------------------------------------------------------------

3.

Why does

getClass()

work only for Wrapper objects?

------------------------------------------------------------

4.

Where are Wrapper Classes stored?

------------------------------------------------------------

5.

Which package contains all Wrapper Classes?

------------------------------------------------------------
Real World Use Cases

	✔ Collections Framework

	✔ Generic Classes

	✔ Streams API

	✔ Spring Boot

	✔ Hibernate

	✔ REST APIs

------------------------------------------------------------
Practice Questions

1.

Create Wrapper objects for

double

and

boolean

and print their class names.

------------------------------------------------------------

2.

Try calling

getClass()

on a primitive variable.

Observe the compiler error.

------------------------------------------------------------

3.

Print the simple class name using

getSimpleName()

instead of

getName().

------------------------------------------------------------

4.

Print the fully qualified class name and compare it
with the simple class name.

------------------------------------------------------------
*/
