
/*Program 14 : Count Occurrences of a Substring in a String

Problem Statement

Write a Java program to count how many times a substring appears in a given String.

Example

Input String    : Java Java Programming Java
Substring       : Java

Output          : 3

 Code:
------------------------------------------------------------
Program 14 : Count Occurrences of a Substring in a String
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String
✔ indexOf()
✔ substring()
✔ while Loop

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class CountSubstringOccurrences {

    public static void main(String[] args) {

        String input = "Java Java Programming Java";
        String substring = "Java";

        int occurrenceCount = 0;
        int currentIndex = 0;

        while ((currentIndex = input.indexOf(substring, currentIndex)) != -1) {

            occurrenceCount++;
            currentIndex += substring.length();

        }

        System.out.println("Input String : " + input);
        System.out.println("Substring    : " + substring);
        System.out.println("Occurrences  : " + occurrenceCount);

    }
}

/*Output;


	Input String : Java Java Programming Java
	Substring    : Java
	Occurrences  : 3

Explanation:

	First, we create the main String and the substring to search.


	String input = "Java Java Programming Java";
	String substring = "Java";

	We initialize two variables.

	int occurrenceCount = 0;
	int currentIndex = 0;

* `occurrenceCount` stores the total number of matches.
* `currentIndex` stores the position from where the next search begins.

The `indexOf()` method searches for the substring starting from the given index.

	input.indexOf(substring, currentIndex)
	

	If the substring is found, `indexOf()` returns its position.

	If it is not found, it returns `-1`.

	Each time the substring is found:


	occurrenceCount++;

	The search continues after the current occurrence.

	currentIndex += substring.length();

	This prevents counting the same occurrence again.

	Finally, the total number of occurrences is printed.

Use Cases;

* Word frequency analysis.
* Search functionality.
* Log file processing.
* Text editors.
* Coding interview questions.
* Document analysis.
*/
