

/*
------------------------------------------------------------
Program 08 : Wrapper Class Utility Methods

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered

	✔ compareTo()
	✔ equals()
	✔ intValue()
	✔ doubleValue()
	✔ toBinaryString()
	✔ MAX_VALUE
	✔ MIN_VALUE
	✔ SIZE
	✔ BYTES

Expected Time : 30 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate some of the most
	commonly used utility methods provided by Wrapper
	Classes.

	Display comparison results, primitive conversions,
	number conversions, and Wrapper class constants.

------------------------------------------------------------
*/

public class WrapperUtilityMethodsDemo {

    public static void main(String[] args) {

        Integer employeeAge = 25;
        Integer managerAge = 30;

        Double totalSalary = 85000.75;

        System.out.println("----- compareTo() -----");

        System.out.println(employeeAge.compareTo(managerAge));

        System.out.println(managerAge.compareTo(employeeAge));

        System.out.println(employeeAge.compareTo(25));

        System.out.println();

        System.out.println("----- equals() -----");

        System.out.println(employeeAge.equals(managerAge));

        System.out.println(employeeAge.equals(25));

        System.out.println();

        System.out.println("----- Primitive Value Methods -----");

        System.out.println(employeeAge.intValue());

        System.out.println(totalSalary.doubleValue());

        System.out.println(totalSalary.intValue());

        System.out.println();

        System.out.println("----- Number Conversion -----");

        System.out.println(Integer.toBinaryString(employeeAge));

        System.out.println(Integer.toOctalString(employeeAge));

        System.out.println(Integer.toHexString(employeeAge));

        System.out.println();

        System.out.println("----- Wrapper Constants -----");

        System.out.println("MAX_VALUE : " + Integer.MAX_VALUE);

        System.out.println("MIN_VALUE : " + Integer.MIN_VALUE);

        System.out.println("SIZE      : " + Integer.SIZE);

        System.out.println("BYTES     : " + Integer.BYTES);

    }

}

/*
------------------------------------------------------------
Output

	----- compareTo() -----

	-1

	1

	0

	----- equals() -----

	false

	true

	----- Primitive Value Methods -----

	25

	85000.75

	85000

	----- Number Conversion -----

	11001

	31

	19

	----- Wrapper Constants -----

	MAX_VALUE : 2147483647

	MIN_VALUE : -2147483648

	SIZE      : 32

	BYTES     : 4

------------------------------------------------------------
Explanation:

	Wrapper Classes provide many built-in utility methods
	that simplify common programming tasks.

	These methods can be grouped into four categories.

	1.

	Comparison Methods

	compareTo()

	equals()

------------------------------------------------------------

	2.

	Primitive Conversion Methods

	intValue()

	doubleValue()

	longValue()

	floatValue()

------------------------------------------------------------

	3.

	Number Conversion Methods

	toBinaryString()

	toOctalString()

	toHexString()

------------------------------------------------------------

	4.

	Constants

	MAX_VALUE

	MIN_VALUE

	SIZE

	BYTES

	Using these methods is preferred over writing custom
	logic because they are well-tested, optimized, and easy
	to understand.

------------------------------------------------------------
Important Points

	✔ compareTo() compares Wrapper object values.

	✔ equals() compares object values, not references.

	✔ intValue() converts a Wrapper object into a primitive.

	✔ toBinaryString() converts a decimal number into its
	binary representation.

	✔ Integer.SIZE returns the number of bits.

	✔ Integer.BYTES returns the number of bytes.

	✔ Wrapper constants improve code readability.

------------------------------------------------------------
Common Mistakes

❌ Using

==

instead of

equals()

for Wrapper value comparison.

------------------------------------------------------------

❌ Hardcoding values like

2147483647

instead of using

Integer.MAX_VALUE.

------------------------------------------------------------

❌ Writing custom binary conversion logic instead of
using

Integer.toBinaryString().

------------------------------------------------------------

❌ Assuming

compareTo()

returns only

true

or

false.

It returns

Negative Value

↓

Current object is smaller

Zero

↓

Both values are equal

Positive Value

↓

Current object is greater

------------------------------------------------------------
Follow-up Interview Questions

1.

What is the difference between

compareTo()

and

equals()?

------------------------------------------------------------

2.

What does

compareTo()

return?

------------------------------------------------------------

3.

What is the purpose of

intValue()?

------------------------------------------------------------

4.

What is the difference between

SIZE

and

BYTES?

------------------------------------------------------------

5.

Why should

Integer.MAX_VALUE

be preferred over hardcoded values?

------------------------------------------------------------
Real World Use Cases

	✔ Sorting Collections

	✔ Input Validation

	✔ Binary Conversion

	✔ Bit Manipulation

	✔ Competitive Programming

	✔ Data Validation

	✔ System Programming

------------------------------------------------------------
Practice Questions

1.

Print the binary representation of

100.

------------------------------------------------------------

2.

Compare two

Double

objects using

compareTo().

------------------------------------------------------------

3.

Convert a

Double

object into

int,

long,

and

float

using Wrapper methods.

------------------------------------------------------------

4.

Print

MAX_VALUE

and

MIN_VALUE

for

Long,

Double,

and

Float.

------------------------------------------------------------

5.

Research additional utility methods provided by

Integer,

such as

max(),

min(),

sum(),

and

compare().

------------------------------------------------------------

Quick Revision

	compareTo()

	↓

	Compare Values

-------------------------

	equals()

	↓

	Check Equality

-------------------------

	intValue()

	↓

	Wrapper → Primitive

-------------------------

	toBinaryString()

	↓

	Decimal → Binary

-------------------------

	MAX_VALUE

	↓

	Largest Supported Value

-------------------------

	SIZE

	↓

	Bits

-------------------------

	BYTES

	↓

	Bytes

------------------------------------------------------------
*/
