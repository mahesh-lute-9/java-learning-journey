
/*Program 08 : String Immutability and the String Pool

## Problem Statement

Write a Java program to understand why `String` objects are immutable and how immutability helps the String Pool work efficiently.

/*
------------------------------------------------------------
Program 08 : String Immutability and the String Pool

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String Immutability
✔ String Pool
✔ Heap Memory
✔ Reference Variables

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class StringImmutabilityDemo {

    public static void main(String[] args) {

        String first = "Java";

        String second = first;

        first = first.concat(" Programming");

        System.out.println("first  : " + first);

        System.out.println("second : " + second);

    }

}

/*
------------------------------------------------------------
Output

first  : Java Programming

second : Java

------------------------------------------------------------
Explanation

	Initially,first points to the String

	"Java"

	The statement

	String second = first;

	does not create a new object.Both variables point to the same String.

Memory Representation:

             String Pool

        +---------------+
        |    "Java"     |
        +---------------+
           ▲         ▲
           │         │
        first     second

------------------------------------------------------------

Now,

	first = first.concat(" Programming");

The concat() method never modifies the existing
String object.

Instead,

	it creates a completely new String object.

Memory Representation:

             String Pool

        +---------------+
        |    "Java"     |
        +---------------+
               ▲
               │
            second

------------------------------------------------------------

             Heap Memory

     +-------------------------+
     |  "Java Programming"     |
     +-------------------------+
                ▲
                │
              first

The original String remains unchanged.

This behavior is called

String Immutability.

------------------------------------------------------------
Interview Notes

One of the most common Java interview questions is

	Why is String immutable?

----> 	Because immutable objects can be safely shared between multiple references without worrying
	about accidental modifications.

	This is the primary reason why the String Pool works efficiently.

------------------------------------------------------------
Important Points

	✔ String objects are immutable.

	✔ Methods like concat(), replace(), and substring() never modify the original String.

	✔ They always return a new String object.

	✔ Multiple variables can safely share one String object.

	✔ Immutability improves memory efficiency.

------------------------------------------------------------
Common Mistakes

❌ Thinking concat() changes the existing String.

❌ Assuming String methods modify the original
object.

❌ Forgetting to assign the returned String.

Example:

	String name = "Java";

	name.concat(" Programming");

The result is ignored because it was not assigned.

------------------------------------------------------------
Follow-up Interview Questions

	1. Why is String immutable?

	2. Which String methods create new objects?

	3. Can String objects ever be modified?

	4. Why is immutability useful?

	5. What problems would occur if Strings were mutable?

------------------------------------------------------------
Real World Use Cases

	✔ URL Handling

	✔ Database Queries

	✔ File Paths

	✔ Security Tokens

	✔ Password Storage

	✔ Configuration Values

------------------------------------------------------------
Practice Questions

1.

Predict the output.

String s = "Java";

s.concat(" Programming");

System.out.println(s);

------------------------------------------------------------

2.

How many objects are created?

String s = "Java";

s = s.concat(" Programming");

------------------------------------------------------------

3.

Why is String immutable but StringBuilder is
mutable?

------------------------------------------------------------

4.

Name three advantages of String immutability.

------------------------------------------------------------
*/		
