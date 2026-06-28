/*
Create and Print a StringBuilder
Problem Statement

Write a Java program to create a StringBuilder object and print its contents.

Code
/*
------------------------------------------------------------
Program 01 : Create and Print a StringBuilder
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ StringBuilder
✔ Constructors
✔ toString()

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class CreateStringBuilder {

    public static void main(String[] args) {

        StringBuilder message = new StringBuilder("Hello Java");

        System.out.println("StringBuilder Content : " + message);

    }

}

/*Output:
	StringBuilder Content : Hello Java

	
Explanation:

	First, we create a StringBuilder object.

	StringBuilder message = new StringBuilder("Hello Java");

	Unlike String, a StringBuilder is mutable, which means its content can be modified without creating a new object.

	When we print the StringBuilder object,

	System.out.println(message);

	Java automatically calls its toString() method and displays the stored text.


Use Cases:
	Building dynamic Strings.
	Generating reports.
	Creating JSON or XML.
	Building SQL queries.
	Efficient String manipulation.
*/
