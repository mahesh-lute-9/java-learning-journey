

/*
------------------------------------------------------------
Program 03 : Creating Wrapper Objects

Language    : Java
Difficulty  : ⭐⭐ Beginner

Concepts Covered

	✔ Wrapper Object Creation
	✔ Deprecated Constructors
	✔ valueOf()
	✔ Autoboxing
	✔ Modern Java Best Practices

Expected Time : 20 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate the three different
	ways of creating Wrapper objects.

	1. Using Constructors (Deprecated)
	2. Using valueOf()
	3. Using Autoboxing

	Print all the Wrapper objects and observe that they
	store the same value.

------------------------------------------------------------
*/

public class CreatingWrapperObjects {

    public static void main(String[] args) {

        // Using Constructor (Deprecated)

        Integer employeeAge1 = new Integer(25);

        // Using valueOf()

        Integer employeeAge2 = Integer.valueOf(25);

        // Using Autoboxing

        Integer employeeAge3 = 25;

        System.out.println("Using Constructor : " + employeeAge1);

        System.out.println("Using valueOf()   : " + employeeAge2);

        System.out.println("Using Autoboxing  : " + employeeAge3);

    }

}

/*
------------------------------------------------------------
Output:

	Using Constructor : 25

	Using valueOf()   : 25

	Using Autoboxing  : 25

------------------------------------------------------------
Explanation:

	Java provides three ways to create Wrapper objects.

	1.

	Using Constructors

	Example

	new Integer(25)

	This approach always creates a new Wrapper object.

	Since Java 9,

	most Wrapper constructors have been deprecated.

------------------------------------------------------------

	2.

	Using valueOf()

	Example

	Integer.valueOf(25)

	This is the recommended approach for explicitly creating
	Wrapper objects.

	Internally,

	the JVM may reuse existing Wrapper objects for better
	performance.
	
	(IntegerCache is covered separately.)

------------------------------------------------------------

	3.

	Using Autoboxing

	Example

	Integer employeeAge = 25;

	The Java compiler automatically converts it into

	Integer employeeAge = Integer.valueOf(25);

	Autoboxing is the most common way to create Wrapper
	objects in modern Java.

------------------------------------------------------------
Important Points

	✔ Wrapper objects can be created in three ways.

	✔ Constructors are deprecated since Java 9.

	✔ valueOf() is the recommended factory method.

	✔ Autoboxing internally uses valueOf().

	✔ Modern Java applications primarily use Autoboxing.

------------------------------------------------------------
Common Mistakes

❌ Continuing to use deprecated constructors.

Example

new Integer(25)

------------------------------------------------------------

❌ Assuming Autoboxing internally calls constructors.

It actually uses

Integer.valueOf()

------------------------------------------------------------

❌ Thinking valueOf() always creates a new object.

Some Wrapper Classes internally reuse objects.

------------------------------------------------------------

❌ Confusing Wrapper object creation with parsing.

valueOf()

creates Wrapper objects.

parseInt()

returns primitive values.

------------------------------------------------------------
Follow-up Interview Questions

1.

How many ways can a Wrapper object be created?

------------------------------------------------------------

2.

Why are Wrapper constructors deprecated?

------------------------------------------------------------

3.

Which method does Autoboxing use internally?

------------------------------------------------------------

4.

Which approach is recommended in modern Java?

------------------------------------------------------------

5.

What is the difference between

valueOf()

and

new Integer()?

------------------------------------------------------------
Real World Use Cases

	✔ Collections Framework

	✔ Spring Boot Applications

	✔ Hibernate

	✔ REST APIs

	✔ Configuration Processing

	✔ Enterprise Java Applications

------------------------------------------------------------
Practice Questions

1.

Create Wrapper objects for

Double

using all three approaches.

------------------------------------------------------------

2.

Create Wrapper objects for

Boolean

using all three approaches.

------------------------------------------------------------

3.

Replace all constructor calls with

valueOf()

and observe the output.

------------------------------------------------------------

4.

Research why Wrapper constructors were deprecated in
Java 9.

------------------------------------------------------------
*/
