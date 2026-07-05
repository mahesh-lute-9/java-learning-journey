

/*
------------------------------------------------------------
Program 11 : Wrapper Classes with Generics

Language    : Java
Difficulty  : ⭐⭐⭐ Advanced

Concepts Covered

	✔ Generics
	✔ Wrapper Classes
	✔ Generic Methods
	✔ Generic Collections
	✔ Compile-Time Type Safety

Expected Time : 30 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate how Wrapper Classes
	work with Generics.

	Store employee IDs in a generic ArrayList and create a
	generic method that prints elements of different Wrapper
	Class collections.

------------------------------------------------------------
*/

import java.util.ArrayList;
import java.util.List;

public class WrapperClassesWithGenerics {

    public static void printElements(List<?> elements) {

        for (Object element : elements) {
            System.out.println(element);
        }

    }

    public static void main(String[] args) {

        List<Integer> employeeIds = new ArrayList<>();

        employeeIds.add(101);
        employeeIds.add(102);
        employeeIds.add(103);

        List<Double> employeeSalaries = new ArrayList<>();

        employeeSalaries.add(55000.50);
        employeeSalaries.add(72000.75);
        employeeSalaries.add(89000.00);

        System.out.println("Employee IDs");

        printElements(employeeIds);

        System.out.println();

        System.out.println("Employee Salaries");

        printElements(employeeSalaries);

    }

}

/*
------------------------------------------------------------
Output

	Employee IDs

	101
	102
	103

	Employee Salaries

	55000.5
	72000.75
	89000.0

------------------------------------------------------------
Explanation:

	Generics allow classes, interfaces and methods to work
	with different data types while maintaining type safety.

	Collections Framework uses Generics extensively.

Example:

	List<Integer>

	stores only Integer objects.

	Similarly,

	List<Double>

	stores only Double objects.

	The compiler ensures that only the specified type can be
	added to the collection.

------------------------------------------------------------

* Why Wrapper Classes?

-->	Generics work only with reference types (objects).

	Primitive data types are not objects.

	Therefore,

	this is invalid

	List<int>

	but

	List<Integer>

	is valid.

------------------------------------------------------------

Generic Method

The method printElements(List<?> elements)

accepts a List of any type.

The wildcard

?

means

"unknown type".

Therefore, the same method works for

	List<Integer>

	List<Double>

	List<String>

	List<Character>

	and many other reference types.

------------------------------------------------------------
Important Points

	✔ Generics provide compile-time type safety.

	✔ Generics work only with reference types.

	✔ Wrapper Classes make primitive values compatible with
	Generics.

	✔ Collections and Generics are closely related.

	✔ Wildcards improve code reusability.

------------------------------------------------------------
Common Mistakes

❌ Trying to create

List<int>

This causes a compilation error.

------------------------------------------------------------

❌ Assuming Generics support primitive data types.

Only reference types are allowed.

------------------------------------------------------------

❌ Using raw types.

Example

List employeeIds = new ArrayList();

Always prefer

List<Integer>

------------------------------------------------------------

❌ Casting objects unnecessarily.

Generics eliminate many explicit type casts.

------------------------------------------------------------
Follow-up Interview Questions

1.

Why don't Generics support primitive data types?

------------------------------------------------------------

2.

Why is

List<Integer>

valid but

List<int>

invalid?

------------------------------------------------------------

3.

What is compile-time type safety?

------------------------------------------------------------

4.

What is a wildcard (?) in Generics?

------------------------------------------------------------

5.

How do Wrapper Classes make Generics possible?

------------------------------------------------------------
Real World Use Cases

	✔ Collections Framework

	✔ Spring Boot

	✔ Hibernate

	✔ REST APIs

	✔ Generic Utility Libraries

	✔ Enterprise Java Applications

	✔ Data Processing Pipelines

------------------------------------------------------------
Practice Questions

1.

Create a

List<Long>

to store mobile numbers.

------------------------------------------------------------

2.

Write a generic method that prints any type of List.

------------------------------------------------------------

3.

Find the largest value from a

List<Integer>.

------------------------------------------------------------

4.

Create a

List<Character>

and print all elements.

------------------------------------------------------------

5.

Research bounded wildcards

<? extends Number>

and

<? super Integer>.

------------------------------------------------------------

Quick Revision

	Generics

	↓

	Reference Types Only

-------------------------

	Primitive

	↓

	❌ Not Allowed

-------------------------

	Wrapper Classes

	↓

	✅ Allowed

-------------------------

	List<Integer>

	↓

	Type Safe

-------------------------

	Wildcard (?)

	↓

	Unknown Type

-------------------------

	Benefits

	↓

	Type Safety

	↓

	Code Reusability

	↓

No Explicit Casting

------------------------------------------------------------
*/
