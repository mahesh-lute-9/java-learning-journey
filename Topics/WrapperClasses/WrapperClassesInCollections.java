

/*
------------------------------------------------------------
Program 10 : Wrapper Classes in Collections

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered

	✔ Wrapper Classes in Collections
	✔ ArrayList<Integer>
	✔ Autoboxing
	✔ Unboxing
	✔ Why Collections Cannot Store Primitives

Expected Time : 30 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate how Wrapper Classes
	are used with the Java Collections Framework.

	Store employee ages in an ArrayList, perform common
	operations, and calculate the average age.

------------------------------------------------------------
*/

import java.util.ArrayList;

public class WrapperClassesInCollections {

    public static void main(String[] args) {

        ArrayList<Integer> employeeAges = new ArrayList<>();

        // Autoboxing

        employeeAges.add(22);
        employeeAges.add(25);
        employeeAges.add(28);
        employeeAges.add(30);
        employeeAges.add(35);

        System.out.println("Employee Ages : " + employeeAges);

        System.out.println();

        System.out.println("First Employee Age : "
                + employeeAges.get(0));

        System.out.println("Total Employees : "
                + employeeAges.size());

        int totalAge = 0;

        // Automatic Unboxing

        for (Integer employeeAge : employeeAges) {

            totalAge += employeeAge;

        }

        double averageAge =
                (double) totalAge / employeeAges.size();

        System.out.println("Total Age : " + totalAge);

        System.out.println("Average Age : " + averageAge);

    }

}

/*
------------------------------------------------------------
Output

	Employee Ages : [22, 25, 28, 30, 35]

	First Employee Age : 22

	Total Employees : 5

	Total Age : 140

	Average Age : 28.0

------------------------------------------------------------
Explanation:

	The Java Collections Framework stores only objects.

	Primitive data types such as

	int

	double

	char

	cannot be stored directly inside Collections.

	Therefore,

	Wrapper Classes are used.

	Example

	ArrayList<Integer>

	stores

	Integer

	objects instead of

	int

	values.

------------------------------------------------------------

	When adding elements

	employeeAges.add(22); the compiler performs

	Autoboxing

	↓

	Integer.valueOf(22)

------------------------------------------------------------

	When reading elements

	totalAge += employeeAge;

	the compiler performs

	Unboxing

	↓

	employeeAge.intValue()

------------------------------------------------------------

	Both conversions happen automatically.

------------------------------------------------------------
Important Points

	✔ Collections store objects, not primitive values.

	✔ Wrapper Classes are mandatory when using Collections.

	✔ Autoboxing occurs while adding primitive values.

	✔ Unboxing occurs while retrieving Wrapper objects.

	✔ Collections work seamlessly because of Autoboxing and
	Unboxing.

------------------------------------------------------------
Common Mistakes

❌ Trying to create

ArrayList<int>

This is invalid because Generics support only reference
types.

------------------------------------------------------------

❌ Assuming Collections store primitive values.

Collections always store objects.

------------------------------------------------------------

❌ Performing unnecessary manual Boxing.

Instead of

employeeAges.add(Integer.valueOf(25));

simply write

employeeAges.add(25);

------------------------------------------------------------

❌ Ignoring possible

NullPointerException

when unboxing nullable Wrapper objects.

------------------------------------------------------------
Follow-up Interview Questions

1.

Why can't Collections store primitive data types?

------------------------------------------------------------

2.

Why is

ArrayList<int>

invalid?

------------------------------------------------------------

3.

What happens internally when calling

employeeAges.add(25)?

------------------------------------------------------------

4.

Where does Unboxing occur in this program?

------------------------------------------------------------

5.

Why are Wrapper Classes required for Generics?

------------------------------------------------------------
Real World Use Cases

	✔ Employee Management Systems

	✔ Student Management Systems

	✔ Banking Applications

	✔ Inventory Management

	✔ REST APIs

	✔ Spring Boot Applications

	✔ Hibernate

	✔ Data Analytics

------------------------------------------------------------
Practice Questions

1.

Create an

ArrayList<Double>

to store product prices.

------------------------------------------------------------

2.

Find the maximum value stored in an

ArrayList<Integer>.

------------------------------------------------------------

3.

Calculate the average salary using

ArrayList<Double>.

------------------------------------------------------------

4.

Remove duplicate values using

HashSet<Integer>.

------------------------------------------------------------

5.

Sort an

ArrayList<Integer>

in ascending and descending order.

------------------------------------------------------------

Quick Revision

	Collections

	↓

	Store Objects

-------------------------

	Primitive

	↓

	❌ Not Allowed

-------------------------

	Wrapper Class

	↓

	✅ Allowed

-------------------------

	add(25)

	↓

	Autoboxing

	↓

	Integer.valueOf(25)

-------------------------

	get()

	↓

	Unboxing

	↓

	intValue()

------------------------------------------------------------
*/
