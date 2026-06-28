/*
Find the Longest Common Prefix
Problem Statement

Write a Java program to find the longest common prefix among an array of Strings.

The longest common prefix is the longest starting sequence of characters shared by all Strings.

Example
Input :
["flower", "flow", "flight"]

Output :
	fl


------------------------------------------------------------
Program 15 : Find the Longest Common Prefix
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String
✔ Array
✔ startsWith()
✔ substring()
✔ while Loop

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class LongestCommonPrefix {

    public static void main(String[] args) {

        String[] words = {
                "flower",
                "flow",
                "flight"
        };

        String prefix = words[0];

        for (int index = 1; index < words.length; index++) {

            while (!words[index].startsWith(prefix)) {

                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) {
                    break;
                }

            }

        }

        System.out.println("Longest Common Prefix : " + prefix);

    }
}
/*Output:
	Longest Common Prefix : fl

	
Explanation:

	First, we create an array of Strings.

	String[] words = {
    		"flower",
    		"flow",
    		"flight"
	};

	We assume the first String is the common prefix.

	String prefix = words[0];

	Initially,

	flower

	Now we compare this prefix with every other String.

	for (int index = 1; index < words.length; index++)

	The startsWith() method checks whether the current word begins with the current prefix.

	words[index].startsWith(prefix)

	If the current word does not start with the prefix, we shorten the prefix by removing its last character.

	prefix = prefix.substring(0, prefix.length() - 1);

	This process continues until the current word starts with the prefix.

	For the given input:

	flower

	↓

	flow

	↓

	flight

	The common prefix becomes:

	flower

	↓

	flowe

	↓

	flow

	↓

	flo

	↓

	fl

	Since all three words start with "fl", it is the longest common prefix.

	Finally, the prefix is printed.


Use Cases:
	Search engine autocomplete.
	Dictionary applications.
	File path comparison.
	Text processing.
	Coding interview questions.
	Competitive programming.

*/
