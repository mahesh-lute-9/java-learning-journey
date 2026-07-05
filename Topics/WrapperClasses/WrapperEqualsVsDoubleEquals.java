

/*
------------------------------------------------------------
Program 12 : == vs equals() in Wrapper Classes

Language    : Java
Difficulty  : ⭐⭐⭐ Advanced

Concepts Covered

	✔ == Operator
	✔ equals() Method
	✔ Reference Comparison
	✔ Value Comparison
	✔ IntegerCache Preview

Expected Time : 30 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to compare Wrapper objects using
	both the == operator and the equals() method.

	Observe the difference between reference comparison and
	value comparison.

------------------------------------------------------------
*/

public class WrapperEqualsVsDoubleEquals {

    public static void main(String[] args) {

        Integer firstEmployeeAge = 100;

        Integer secondEmployeeAge = 100;

        Integer thirdEmployeeAge = 200;

        Integer fourthEmployeeAge = 200;

        System.out.println("----- Using == -----");

        System.out.println(firstEmployeeAge == secondEmployeeAge);

        System.out.println(thirdEmployeeAge == fourthEmployeeAge);

        System.out.println();

        System.out.println("----- Using equals() -----");

        System.out.println(firstEmployeeAge.equals(secondEmployeeAge));

        System.out.println(thirdEmployeeAge.equals(fourthEmployeeAge));

    }

}

/*
------------------------------------------------------------
Output

	----- Using == -----

	true

	false

	----- Using equals() -----

	true

	true

------------------------------------------------------------
Explanation:

	There are two ways to compare Wrapper objects.

	1.

	==

	Compares object references.

	It checks whether both variables point to the same
	object in memory.

------------------------------------------------------------

	2.

	equals()

	Compares object values.

	It checks whether both Wrapper objects contain the same
	value.

------------------------------------------------------------

Notice:

	100 == 100

	returns

	true

	whereas

	200 == 200

	returns

	false.

	This happens because Integer values from

	-128

	to

	127

	are cached by the JVM.

	Values outside this range usually create different
	objects.

	This behavior is explained in detail in the separate IntegerCache

	module.

------------------------------------------------------------
Important Points:

	✔ == compares references.

	✔ equals() compares values.

	✔ Always use equals() for Wrapper value comparison.

	✔ Small Integer values are cached by the JVM.

	✔ IntegerCache affects == results.

------------------------------------------------------------
Common Mistakes

❌ Using

==

to compare Wrapper values.

------------------------------------------------------------

❌ Assuming

==

and

equals()

always behave the same.

------------------------------------------------------------

❌ Not knowing about IntegerCache during interviews.

------------------------------------------------------------

❌ Assuming

equals()

compares object references.

------------------------------------------------------------
Follow-up Interview Questions

1.

What is the difference between

==

and

equals()?

------------------------------------------------------------

2.

Why does

100 == 100

return

true

but

200 == 200

returns

false?

------------------------------------------------------------

3.

What is IntegerCache?

------------------------------------------------------------

4.

Which comparison method should be preferred?

------------------------------------------------------------

5.

Does

equals()

compare object references?

------------------------------------------------------------
Real World Use Cases

	✔ Comparing Database IDs

	✔ Authentication

	✔ Business Logic

	✔ Validation

	✔ Collections

	✔ Enterprise Java Applications

------------------------------------------------------------
Practice Questions

1.

Compare

Long

objects using

==

and

equals().

------------------------------------------------------------

2.

Repeat the program using

Double.

Observe the output.

------------------------------------------------------------

3.

Research why IntegerCache exists.

------------------------------------------------------------

4.

Predict the output before executing the program.

------------------------------------------------------------

Quick Revision

	==

	↓

	Reference Comparison

-------------------------

	equals()

	↓

	Value Comparison

-------------------------

	100

	↓

	Cached

-------------------------

	200

	↓

	Usually Not Cached

-------------------------

Use:

	equals()

	↓

	Recommended

------------------------------------------------------------
*/
