

/*
------------------------------------------------------------
Program 05 : Unboxing

Language    : Java
Difficulty  : ⭐⭐ Beginner

Concepts Covered

	✔ Unboxing
	✔ Wrapper to Primitive Conversion
	✔ intValue()
	✔ Compiler Conversion
	✔ NullPointerException

Expected Time : 20 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate Unboxing by
	converting Wrapper objects into their corresponding
	primitive data types.

	Print both the Wrapper objects and primitive values to
	verify the automatic conversion.

------------------------------------------------------------
*/

public class UnboxingDemo {

    public static void main(String[] args) {

        Integer employeeAgeObject = 25;
        Double productPriceObject = 1999.99;
        Character gradeObject = 'A';
        Boolean paymentStatusObject = true;
        Long mobileNumberObject = 9876543210L;

        int employeeAge = employeeAgeObject;
        double productPrice = productPriceObject;
        char grade = gradeObject;
        boolean isPaymentSuccessful = paymentStatusObject;
        long mobileNumber = mobileNumberObject;

        System.out.println("Employee Age      : " + employeeAge);
        System.out.println("Product Price     : " + productPrice);
        System.out.println("Grade             : " + grade);
        System.out.println("Payment Status    : " + isPaymentSuccessful);
        System.out.println("Mobile Number     : " + mobileNumber);

    }

}

/*
------------------------------------------------------------
Output

	Employee Age      : 25
	Product Price     : 1999.99
	Grade             : A
	Payment Status    : true
	Mobile Number     : 9876543210

------------------------------------------------------------
Explanation:

	Unboxing is the automatic conversion of a Wrapper
	Class object into its corresponding primitive data
	type.

	When the compiler encounters

	int employeeAge = employeeAgeObject;

	it internally converts it into

	int employeeAge = employeeAgeObject.intValue();

	Similarly,

	double productPrice = productPriceObject;

	becomes

	double productPrice = productPriceObject.doubleValue();

	The conversion is performed automatically by the Java compiler.

------------------------------------------------------------
Important Points

	✔ Unboxing was introduced in Java 5.

	✔ Unboxing converts Wrapper objects into primitive
	data types.

	✔ The compiler automatically performs Unboxing.

	✔ Unboxing internally uses methods like

	intValue()

	doubleValue()

	charValue()

	booleanValue()

	✔ Wrapper objects can store null values, but primitive
	variables cannot.

------------------------------------------------------------
Common Mistakes

❌ Assuming Unboxing never throws an exception.

------------------------------------------------------------

❌ Forgetting that Wrapper objects can be null.

------------------------------------------------------------

❌ Performing repeated Boxing and Unboxing inside
performance-critical loops.

------------------------------------------------------------

❌ Thinking Unboxing modifies the Wrapper object.

The Wrapper object remains unchanged.

------------------------------------------------------------
Follow-up Interview Questions

1.

What is Unboxing?

------------------------------------------------------------

2.

Who performs Unboxing?

------------------------------------------------------------

3.

Which methods are internally used during Unboxing?

------------------------------------------------------------

4.

Can Unboxing throw an exception?

------------------------------------------------------------

5.

When was Unboxing introduced in Java?

------------------------------------------------------------
Real World Use Cases

	✔ Reading values from Collections

	✔ Database Applications

	✔ Streams API

	✔ Method Return Values

	✔ REST API Response Processing

	✔ Enterprise Java Applications

------------------------------------------------------------
Practice Questions

1.

Perform Unboxing for all eight Wrapper Classes.

------------------------------------------------------------

2.

Explicitly use

intValue()

doubleValue()

instead of automatic Unboxing.

------------------------------------------------------------

3.

Create a Wrapper object containing

null

and observe what happens during Unboxing.

------------------------------------------------------------

4.

Compare manual Unboxing with automatic Unboxing.

------------------------------------------------------------

------------------------------------------------------------
Interview Corner

One of the most frequently asked interview questions.

* What will be the output?

	Integer employeeAgeObject = null;

	int employeeAge = employeeAgeObject;

Answer

	The program compiles successfully but throws

	NullPointerException at runtime.

	Reason:

	The compiler converts

	int employeeAge = employeeAgeObject;

	into

	employeeAgeObject.intValue();

	Since

	employeeAgeObject

	is

	null, calling intValue() throws

	NullPointerException.

------------------------------------------------------------
*/
