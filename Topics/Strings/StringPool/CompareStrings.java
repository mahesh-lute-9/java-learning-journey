/*
# Program 02 : `==` vs `equals()`

## Problem Statement

Write a Java program to demonstrate the difference between the `==` operator and the `equals()` method while comparing Strings.

/*
------------------------------------------------------------
Program 02 : == vs equals()

Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ String Pool
✔ == Operator
✔ equals() Method
✔ Heap Memory

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class CompareStrings {

    public static void main(String[] args) {

        String first = "Java";

        String second = "Java";

        String third = new String("Java");

        System.out.println("first == second : " + (first == second));

        System.out.println("first == third  : " + (first == third));

        System.out.println("first.equals(third) : " + first.equals(third));

    }

}

/*
------------------------------------------------------------
Output

	first == second : true
	first == third  : false
	first.equals(third) : true

------------------------------------------------------------
Explanation:

	Two String literals having the same value point to
	the same object inside the String Pool.

Therefore,

	first == second

	returns true because both variables refer to the
	same memory location.

	The new keyword always creates a new object in Heap Memory.

Therefore,

	first == third

	returns false because both variables refer to different objects.

	The equals() method compares the contents of the Strings instead of their references.

	Since both Strings contain "Java",

	equals() returns true.

------------------------------------------------------------
Interview Notes:

	This is one of the most frequently asked Java
	interview questions.

	Remember this rule forever:

	==

	Compares memory addresses (references).

	.equals()

	Compares actual contents.

	If the interviewer asks, "When should you use == ?"

Answer:

	Use == only when you intentionally want to check
	whether two references point to the same object.

	For comparing String values, always use equals().

------------------------------------------------------------
Important Points

	✔ String literals are stored in the String Pool.

	✔ new String() creates a new object in Heap Memory.

	✔ == compares references.

	✔ equals() compares contents.

	✔ Two different objects can contain the same value.

------------------------------------------------------------
Common Mistakes

	❌ Comparing Strings using ==

Example:

	String name = "Mahesh";

	if(name == "Mahesh")

	Although this may sometimes work because of the String Pool, it should never be relied upon.

	Always write

	name.equals("Mahesh")

	or

	"Mahesh".equals(name)

	to avoid NullPointerException.

------------------------------------------------------------
Top Company Interview Focus

★★★★★ Google

★★★★★ Amazon

★★★★★ Microsoft

★★★★★ Oracle

★★★★★ Adobe

★★★★★ Atlassian

★★★★★ Walmart Global Tech

★★★★★ JP Morgan Chase

★★★★★ Goldman Sachs

★★★★★ Infosys Specialist Programmer

★★★★★ TCS Digital

This question is one of the highest-frequency
Java interview questions.

------------------------------------------------------------
Follow-up Interview Questions

	1. What is String Pool?

	2. How many objects are created?

	String s = new String("Java");

	3. Difference between == and equals()?

	4. What is intern()?

	5. Why is String immutable?

	6. Can equals() be overridden?

------------------------------------------------------------
Real World Use Cases

✔ Login Systems
	
✔ Password Validation

✔ Spring Boot Applications

✔ REST APIs

✔ Database Value Comparison

✔ Search Functionality

------------------------------------------------------------
Practice Questions

1.

	String s1 = "Java";
	String s2 = "Java";

Predict the output of s1 == s2

----------------------------

2.

	String s1 = new String("Java");
	String s2 = new String("Java");

Predict the output of s1 == s2

----------------------------

3.

	String s1 = "Java";
	String s2 = new String("Java");

Predict the output of s1.equals(s2)

----------------------------

4.

	Explain why

	new String("Java")

usually creates two objects.

------------------------------------------------------------
*/
