

/*
------------------------------------------------------------
Program 01 : Introduction to Wrapper Classes

Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered

	✔ Primitive Data Types
	✔ Wrapper Classes
	✔ Printing Wrapper Objects

Expected Time : 10 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate the basic usage of
	Wrapper Classes by creating Wrapper objects for different
	primitive data types and printing their values.

------------------------------------------------------------
*/

public class IntroductionToWrapperClasses {

    public static void main(String[] args) {

        Integer employeeAge = 25;

        Double productPrice = 1999.99;

        Character grade = 'A';

        Boolean isPaymentSuccessful = true;

        Long mobileNumber = 9876543210L;

        System.out.println("Employee Age : " + employeeAge);

        System.out.println("Product Price : " + productPrice);

        System.out.println("Grade : " + grade);

        System.out.println("Payment Status : " + isPaymentSuccessful);

        System.out.println("Mobile Number : " + mobileNumber);

    }

}

/*
------------------------------------------------------------
Output

	Employee Age : 25
	Product Price : 1999.99
	Grade : A
	Payment Status : true
	Mobile Number : 9876543210

------------------------------------------------------------
Explanation;
		
	Wrapper Classes allow primitive values to be represented
	as objects.

	In this program,

	25

	is automatically converted into

	Integer.valueOf(25)

	using Autoboxing.

	The same happens for all the other Wrapper Classes.

------------------------------------------------------------
Important Points

	✔ Wrapper Classes belong to java.lang package.

	✔ Wrapper Classes are immutable.

	✔ Every primitive has one corresponding Wrapper Class.

	✔ Wrapper Classes can be printed directly.

	✔ Autoboxing automatically converts primitive values into
	Wrapper objects.

------------------------------------------------------------
Common Mistakes

❌ Thinking Wrapper Classes store primitive values directly.

❌ Assuming Wrapper Classes are mutable.

❌ Confusing Wrapper Classes with primitive data types.

------------------------------------------------------------
Follow-up Interview Questions

	1. What is a Wrapper Class?

	2. Why were Wrapper Classes introduced?

	3. How many Wrapper Classes exist in Java?

	4. Which package contains all Wrapper Classes?

	5. What is Autoboxing?

------------------------------------------------------------
Real World Use Cases

	✔ Collections Framework

	✔ Database Applications

	✔ REST APIs

	✔ JSON Processing

	✔ Spring Framework

	✔ Hibernate

------------------------------------------------------------
Practice Questions

1.

Create Wrapper objects for all eight primitive data types.

------------------------------------------------------------

2.

Print the class name of each Wrapper object.

------------------------------------------------------------

3.

Create Wrapper objects using valueOf() instead of
Autoboxing.

------------------------------------------------------------

4.

Try creating Wrapper objects using deprecated constructors.
Observe the compiler warning.

------------------------------------------------------------
*/
