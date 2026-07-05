

/*
------------------------------------------------------------
Program 15 : Wrapper Classes Interview Challenge

Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered

	✔ Autoboxing
	✔ Unboxing
	✔ valueOf()
	✔ parseInt()
	✔ equals()
	✔ ==
	✔ Collections
	✔ Null Handling
	✔ Output Prediction

Expected Time : 45 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program that demonstrates multiple Wrapper
	Class concepts in a single program.

	Before executing the program,

	predict the output of every section.

	Then execute the program and verify your answers.

------------------------------------------------------------
*/

import java.util.ArrayList;

public class WrapperClassesInterviewChallenge {

    public static void main(String[] args) {

        System.out.println("========== Challenge 1 ==========");

        Integer firstEmployeeAge = 100;
        Integer secondEmployeeAge = 100;

        System.out.println(firstEmployeeAge == secondEmployeeAge);

        System.out.println(firstEmployeeAge.equals(secondEmployeeAge));

        System.out.println();

        System.out.println("========== Challenge 2 ==========");

        Integer thirdEmployeeAge = 200;
        Integer fourthEmployeeAge = 200;

        System.out.println(thirdEmployeeAge == fourthEmployeeAge);

        System.out.println(thirdEmployeeAge.equals(fourthEmployeeAge));

        System.out.println();

        System.out.println("========== Challenge 3 ==========");

        int employeeId =
                Integer.parseInt("101");

        Integer employeeIdObject =
                Integer.valueOf("101");

        System.out.println(employeeId);

        System.out.println(employeeIdObject);

        System.out.println();

        System.out.println("========== Challenge 4 ==========");

        ArrayList<Integer> employeeAges =
                new ArrayList<>();

        employeeAges.add(22);
        employeeAges.add(25);
        employeeAges.add(28);

        int totalAge = 0;

        for (Integer employeeAge : employeeAges) {

            totalAge += employeeAge;

        }

        System.out.println(totalAge);

        System.out.println();

        System.out.println("========== Challenge 5 ==========");

        Integer managerAge = null;

        try {

            int age = managerAge;

            System.out.println(age);

        } catch (NullPointerException exception) {

            System.out.println("NullPointerException Caught");

        }

    }

}

/*
------------------------------------------------------------
Output

	========== Challenge 1 ==========

	true

	true

	========== Challenge 2 ==========

	false

	true

	========== Challenge 3 ==========

	101

	101

	========== Challenge 4 ==========

	75

	========== Challenge 5 ==========

	NullPointerException Caught

------------------------------------------------------------
Explanation:

Challenge 1

	Small Integer values are cached by the JVM.

	Therefore,

	==

	returns

	true.

	equals()

	also returns

	true

	because both objects contain the same value.

------------------------------------------------------------

Challenge 2

	Integer value

	200

	is generally outside the default Integer cache.

	Hence,

	==

	returns

	false

	because two different Wrapper objects are compared.

	equals()

	returns

	true

	because both objects contain the same value.

------------------------------------------------------------

Challenge 3

	parseInt()

	↓

	Returns primitive

	int

-------------------------

	valueOf()

	↓

	Returns Wrapper

	Integer

------------------------------------------------------------

Challenge 4

	Adding

	22

	25

	28

	into

	ArrayList<Integer>

	uses

	Autoboxing.

	Reading values inside the enhanced for loop uses

	Unboxing.

------------------------------------------------------------

Challenge 5

	Wrapper Classes can store

	null.

	During

	Unboxing

	the compiler internally calls

	intValue().

	Since the object reference is

	null,

	Java throws

	NullPointerException.

------------------------------------------------------------
Important Points

	✔ Wrapper Classes support Autoboxing.

	✔ Wrapper Classes support Unboxing.

	✔ parseInt() returns primitive values.

	✔ valueOf() returns Wrapper objects.

	✔ Collections require Wrapper Classes.

	✔ equals() compares values.

	✔ == compares references.

	✔ Wrapper objects may contain null.

------------------------------------------------------------
Common Mistakes

❌ Comparing Wrapper objects using

==.

------------------------------------------------------------

❌ Assuming

parseInt()

returns an

Integer.

------------------------------------------------------------

❌ Ignoring

NullPointerException

during Unboxing.

------------------------------------------------------------

❌ Forgetting that Collections cannot store primitive
types.

------------------------------------------------------------

❌ Confusing

Autoboxing

with

valueOf().

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

true,

but

200 == 200

return

false?

------------------------------------------------------------

3.

What is the difference between

parseInt()

and

valueOf()?

------------------------------------------------------------

4.

Can Unboxing throw

NullPointerException?

------------------------------------------------------------

5.

Why are Wrapper Classes required in Collections?

------------------------------------------------------------

6.

What is Autoboxing?

------------------------------------------------------------

7.

What is Unboxing?

------------------------------------------------------------

8.

Which method is internally used during Autoboxing?

------------------------------------------------------------

9.

Which method is internally used during Unboxing?

------------------------------------------------------------

10.

When should primitive types be preferred over Wrapper
Classes?

------------------------------------------------------------
Real World Use Cases

	✔ Collections Framework

	✔ Spring Boot

	✔ Hibernate

	✔ REST APIs

	✔ Database Applications

	✔ Enterprise Java Applications

------------------------------------------------------------
Practice Questions

1.

Predict the output before executing the program.

------------------------------------------------------------

2.

Replace

Integer

with

Long

and compare the behavior.

------------------------------------------------------------

3.

Replace

Integer

with

Double

and observe the result of

==

and

equals().

------------------------------------------------------------

4.

Create a similar challenge using

Character

and

Boolean.

------------------------------------------------------------

5.

Modify the program to safely handle nullable Wrapper
objects without using

try-catch.

------------------------------------------------------------

Quick Revision

	Primitive

	↓

	Autoboxing

	↓

	Wrapper

-------------------------

	Wrapper

	↓

	Unboxing

	↓

	Primitive

-------------------------

	String

	↓

	parseXxx()

	↓

	Primitive

-------------------------

	String / Primitive

	↓

	valueOf()

	↓

	Wrapper

-------------------------

	==

	↓

	Reference Comparison

-------------------------

	equals()

	↓

	Value Comparison

-------------------------

	Collections

	↓

	Wrapper Classes

-------------------------

	Wrapper

	↓

	null

	↓

	Unboxing

	↓

	NullPointerException

------------------------------------------------------------
*/
