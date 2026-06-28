
/*
Check Whether One String is a Rotation of Another
Problem Statement

Write a Java program to check whether one String is a rotation of another String.

Two Strings are said to be rotations of each other if one String can be obtained by rotating the other String.

Example
Input 1 : ABCD
Input 2 : CDAB

Output : Strings are Rotations.

Code:

------------------------------------------------------------
Program 13 : Check Whether One String is a Rotation of Another
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String
✔ concat()
✔ contains()
✔ if-else

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class StringRotation {

    public static void main(String[] args) {

        String firstString = "ABCD";
        String secondString = "CDAB";

        if (firstString.length() != secondString.length()) {

            System.out.println("Strings are not Rotations.");
            return;

        }

        String combinedString = firstString + firstString;

        if (combinedString.contains(secondString)) {

            System.out.println("Strings are Rotations.");

        } else {

            System.out.println("Strings are not Rotations.");

        }

    }
}

/*Output:
	Strings are Rotations.

	
Explanation:

	First, we create two Strings.

	String firstString = "ABCD";
	String secondString = "CDAB";

	Before checking for rotation, we compare their lengths.

	if (firstString.length() != secondString.length())

	If the lengths are different, they cannot be rotations of each other.

	Next, we concatenate the first String with itself.

	String combinedString = firstString + firstString;

	The value becomes:

	ABCDABCD

	Now we check whether the second String exists inside the combined String.

	combinedString.contains(secondString)

	Since "CDAB" is present inside "ABCDABCD", the Strings are rotations of each other.

	If the second String is not found, then they are not rotations.


	
Use Cases:
	Circular string matching.
	String pattern detection.
	Rotation-based algorithms.
	Data validation.
	Coding interview questions.

*/
