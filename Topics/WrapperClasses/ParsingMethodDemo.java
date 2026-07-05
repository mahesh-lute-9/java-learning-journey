

/*
------------------------------------------------------------
Program 06 : Parsing Methods

Language    : Java
Difficulty  : ⭐⭐ Intermediate

Concepts Covered

	✔ Parsing
	✔ parseInt()
	✔ parseDouble()
	✔ parseLong()
	✔ parseBoolean()
	✔ NumberFormatException

Expected Time : 25 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate various Wrapper
	Class parsing methods by converting String values into
	their corresponding primitive data types.

	Print all the converted values.

------------------------------------------------------------
*/

public class ParsingMethodsDemo {

    public static void main(String[] args) {

        String employeeAge = "25";
        String productPrice = "1999.99";
        String mobileNumber = "9876543210";
        String isPaymentSuccessful = "true";
        String productDiscount = "15.5";

        int age = Integer.parseInt(employeeAge);

        double price = Double.parseDouble(productPrice);

        long number = Long.parseLong(mobileNumber);

        boolean paymentStatus =
                Boolean.parseBoolean(isPaymentSuccessful);

        float discount =
                Float.parseFloat(productDiscount);

        System.out.println("Employee Age      : " + age);

        System.out.println("Product Price     : " + price);

        System.out.println("Mobile Number     : " + number);

        System.out.println("Payment Status    : " + paymentStatus);

        System.out.println("Product Discount  : " + discount);

    }

}

/*
------------------------------------------------------------
Output

	Employee Age      : 25

	Product Price     : 1999.99

	Mobile Number     : 9876543210

	Payment Status    : true

	Product Discount  : 15.5

------------------------------------------------------------
Explanation:

	Parsing is the process of converting a String into its corresponding primitive data type.

	Each Wrapper Class provides parsing methods.

	Example

	Integer.parseInt()

	converts

	String

	↓

	int

	Similarly, Double.parseDouble()

	converts

	String

	↓

	double

	Unlike

	valueOf(),

	parsing methods always return primitive values.

------------------------------------------------------------
Important Points:

	✔ Parsing methods convert String values into primitive
	data types.

	✔ parseInt() returns int.

	✔ parseDouble() returns double.

	✔ parseLong() returns long.

	✔ parseBoolean() returns boolean.

	✔ Character does not provide

	parseChar()

	Use

	charAt()

	instead.

------------------------------------------------------------
Common Mistakes

❌ Assuming

parseInt()

returns an

Integer

object.

It returns a primitive

int.

------------------------------------------------------------

❌ Using

Character.parseChar()

This method does not exist.

------------------------------------------------------------

❌ Ignoring invalid user input.

Invalid numeric Strings throw

NumberFormatException.

------------------------------------------------------------

❌ Confusing

parseInt()

with

valueOf().

parseInt()

↓

Primitive

valueOf()

↓

Wrapper Object

------------------------------------------------------------
Follow-up Interview Questions

1.

What is parsing?

------------------------------------------------------------

2.

What is the return type of

Integer.parseInt()?

------------------------------------------------------------

3.

What happens when parsing an invalid number?

------------------------------------------------------------

4.

What is the difference between

parseInt()

and

valueOf()?

------------------------------------------------------------

5.

Why doesn't Character provide

parseChar()?

------------------------------------------------------------
Real World Use Cases

	✔ Reading User Input

	✔ Configuration Files

	✔ CSV Processing

	✔ JSON Parsing

	✔ REST APIs

	✔ Database Applications

------------------------------------------------------------
Practice Questions

1.

Parse

Short

and

Byte

values from String.

------------------------------------------------------------

2.

Read a decimal number from a String and convert it
into

double.

------------------------------------------------------------

3.

Try parsing

"Twenty Five"

using

parseInt()

and observe the exception.

------------------------------------------------------------

4.

Convert the following Strings into primitive values.

"100"

"99.95"

"false"

"9876543210"

------------------------------------------------------------

Quick Revision

	String

	↓

	parseXxx()

	↓

	Primitive

-------------------------

	parseInt()

	↓

	int

-------------------------

	parseDouble()

	↓

	double

-------------------------

	parseLong()

	↓

	long

-------------------------

	parseBoolean()

	↓

	boolean

-------------------------

	Invalid Input

	↓

	NumberFormatException

------------------------------------------------------------
*/
