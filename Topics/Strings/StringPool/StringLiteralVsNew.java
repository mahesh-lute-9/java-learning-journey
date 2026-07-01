
/*
String Literal vs new String()
Problem Statement

Write a Java program to demonstrate the difference between creating a String using a String Literal and using the new keyword.

------------------------------------------------------------
Program 01 : String Literal vs new String()
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ String Pool
✔ Heap Memory
✔ String Literal
✔ new Keyword

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class StringLiteralVsNew {

    public static void main(String[] args) {

        String first = "Java";

        String second = "Java";

        String third = new String("Java");

        System.out.println("first == second : " + (first == second));

        System.out.println("first == third  : " + (first == third));

        System.out.println("first.equals(third) : " + first.equals(third));

    }

}

/*Output:
	first == second : true

	first == third  : false

	first.equals(third) : true

Explanation:

	First, we create two Strings using String Literals.

	String first = "Java";

	String second = "Java";

	When Java sees a String Literal, it first checks the String Pool.

	If the same String already exists, Java does not create another object.

	Both variables point to the same object.

		String Pool

		+----------+
		| "Java"   |
		+----------+
     		     ▲
     		     │
 		 ┌───┴────┐
		 │        │
		first   second

	Therefore,

		first == second

	returns

		true

	because both variables reference the same object.

	Next,

		String third = new String("Java");

	The new keyword always creates a new object in Heap Memory.

	Even if "Java" already exists in the String Pool, a separate object is created in the Heap.

		String Pool              Heap Memory

		+----------+          +----------+
		| "Java"   |          | "Java"   |
		+----------+          +----------+
    		     ▲                     ▲
     		     │                     │
		 ┌───┴────┐               third
		 │        │
		first   second

	Therefore,

		first == third

	returns

		false

	because the references are different.

	Finally,

		first.equals(third)

	returns

		true

	because the equals() method compares the contents of the Strings, not their memory addresses.

	Both Strings contain:

	Java

		
		so the result is true.


Use Cases:
	-Java interviews.
	-Understanding JVM memory.
	-Learning String Pool behavior.
	-Writing memory-efficient Java programs.
	-Avoiding confusion between == and equals().


-----------------------------------------------------------------------------------------------------------

💡 Interview Question

Which statement creates only one object?
	
	String s = "Java";

	✅ Creates one object (if "Java" is not already present in the String Pool).


Which statement creates two objects?
	
	String s = new String("Java");

	Usually creates:

	One object in the String Pool (if not already present).
	One object in the Heap Memory.

	So, up to two objects may be involved.


📌 Key Learning:
	
	String Creation		-	Memory Location
	"Java"				String Pool
	new String("Java")		Heap Memory (and may also use the String Pool literal)

*/
