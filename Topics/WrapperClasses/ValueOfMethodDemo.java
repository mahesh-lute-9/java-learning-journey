

/*
------------------------------------------------------------
Program 07 : valueOf() Method

Language    : Java
Difficulty  : ⭐⭐ Intermediate

Concepts Covered

	✔ valueOf()
	✔ Primitive to Wrapper Conversion
	✔ String to Wrapper Conversion
	✔ Wrapper Object Creation
	✔ valueOf() vs parseInt()

Expected Time : 25 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate the valueOf()
	method by creating Wrapper objects from both primitive
	values and String values.

	Print all the Wrapper objects and their class names.

------------------------------------------------------------
*/

public class ValueOfMethodDemo {

    public static void main(String[] args) {

        // Creating Wrapper Objects from Primitive Values

        Integer employeeAge = Integer.valueOf(25);

        Double productPrice = Double.valueOf(1999.99);

        Boolean isPaymentSuccessful = Boolean.valueOf(true);

        Character grade = Character.valueOf('A');

        // Creating Wrapper Objects from String Values

        Integer employeeId = Integer.valueOf("101");

        Double totalSalary = Double.valueOf("85000.50");

        Long mobileNumber = Long.valueOf("9876543210");

        System.out.println("Employee Age      : " + employeeAge);

        System.out.println("Product Price     : " + productPrice);

        System.out.println("Payment Status    : " + isPaymentSuccessful);

        System.out.println("Grade             : " + grade);

        System.out.println("Employee ID       : " + employeeId);

        System.out.println("Total Salary      : " + totalSalary);

        System.out.println("Mobile Number     : " + mobileNumber);

        System.out.println();

        System.out.println("Class Name of employeeAge : "
                + employeeAge.getClass().getSimpleName());

        System.out.println("Class Name of totalSalary : "
                + totalSalary.getClass().getSimpleName());

    }

}

/*
------------------------------------------------------------
Output

	Employee Age      : 25

	Product Price     : 1999.99

	Payment Status    : true

	Grade             : A

	Employee ID       : 101

	Total Salary      : 85000.5

	Mobile Number     : 9876543210

	Class Name of employeeAge : Integer

	Class Name of totalSalary : Double

------------------------------------------------------------
Explanation

	The valueOf() method converts

	• Primitive values

	or

	• Valid String values

	into their corresponding Wrapper Class objects.

	Unlike

	parseInt()

	or

	parseDouble(),

	valueOf()

	returns Wrapper objects instead of primitive values.

	Autoboxing internally uses valueOf()

	to create Wrapper objects.

------------------------------------------------------------
Important Points

	✔ valueOf() returns Wrapper objects.

	✔ It accepts both primitive values and valid Strings.

	✔ valueOf() is the recommended way to explicitly create
	Wrapper objects.

	✔ Autoboxing internally uses valueOf().

	✔ Modern Java prefers valueOf() over deprecated
	constructors.

------------------------------------------------------------
Common Mistakes

❌ Assuming

valueOf()

returns primitive values.

It returns Wrapper objects.

------------------------------------------------------------

❌ Confusing

valueOf()

with

parseInt().

valueOf()

↓

Wrapper Object

parseInt()

↓

Primitive Value

------------------------------------------------------------

❌ Using deprecated constructors instead of

valueOf().

------------------------------------------------------------

❌ Passing invalid numeric Strings.

Example

Integer.valueOf("Twenty Five");

throws

NumberFormatException.

------------------------------------------------------------
Follow-up Interview Questions

1.

What does

valueOf()

return?

------------------------------------------------------------

2.

What is the difference between

valueOf()

and

parseInt()?

------------------------------------------------------------

3.

Why is

valueOf()

preferred over constructors?

------------------------------------------------------------

4.

Does Autoboxing internally use

valueOf()?

------------------------------------------------------------

5.

Can

valueOf()

accept String values?

------------------------------------------------------------
Real World Use Cases

	✔ Collections Framework

	✔ Spring Boot

	✔ Hibernate

	✔ REST APIs

	✔ Configuration Files

	✔ Database Applications

------------------------------------------------------------
Practice Questions

1.

Create Wrapper objects for all eight Wrapper Classes
using

valueOf().

------------------------------------------------------------

2.

Create Wrapper objects using String values only.

------------------------------------------------------------

3.

Compare the return type of

Integer.valueOf()

and

Integer.parseInt().

------------------------------------------------------------

4.

Pass an invalid String to

valueOf()

and observe the exception.

------------------------------------------------------------

Quick Revision

	Primitive

	↓

	valueOf()

	↓

	Wrapper Object

-------------------------

	String

	↓

	valueOf()

	↓

	Wrapper Object

-------------------------

	String

	↓

	parseXxx()

	↓

	Primitive

-------------------------

	Autoboxing

	↓

	Compiler

	↓

	valueOf()

------------------------------------------------------------
*/
