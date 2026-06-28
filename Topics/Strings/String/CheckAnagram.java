/*
Check Whether Two Strings are Anagrams
Problem Statement

Write a Java program to check whether two given Strings are anagrams.

Two Strings are called anagrams if they contain the same characters with the same frequency, but the characters may be arranged in a different order.

Example
Input 1 : listen
Input 2 : silent

Output : Strings are Anagrams.
Code:
------------------------------------------------------------
Program 11 : Check Whether Two Strings are Anagrams
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String
✔ Arrays
✔ sort()
✔ equals()

Expected Time : 15 Minutes
------------------------------------------------------------
*/

import java.util.Arrays;

public class CheckAnagram {

    public static void main(String[] args) {

        String firstString = "listen";
        String secondString = "silent";

        firstString = firstString.toLowerCase();
        secondString = secondString.toLowerCase();

        if (firstString.length() != secondString.length()) {

            System.out.println("Strings are not Anagrams.");
            return;

        }

        char[] firstCharacters = firstString.toCharArray();
        char[] secondCharacters = secondString.toCharArray();

        Arrays.sort(firstCharacters);
        Arrays.sort(secondCharacters);

        if (Arrays.equals(firstCharacters, secondCharacters)) {

            System.out.println("Strings are Anagrams.");

        } else {

            System.out.println("Strings are not Anagrams.");

        }

    }
}

/*Output:
	Strings are Anagrams.

	
Explanation;

	First, we create two Strings.

	String firstString = "listen";
	String secondString = "silent";

	To make the comparison case-insensitive, we convert both Strings to lowercase.

	firstString = firstString.toLowerCase();
	secondString = secondString.toLowerCase();

	Before comparing the characters, we check whether both Strings have the same length.

	if (firstString.length() != secondString.length())

	If the lengths are different, the Strings cannot be anagrams.

	Next, we convert both Strings into character arrays.

	char[] firstCharacters = firstString.toCharArray();
	char[] secondCharacters = secondString.toCharArray();

	After that, we sort both arrays.

	Arrays.sort(firstCharacters);
	Arrays.sort(secondCharacters);

	Sorting arranges the characters in alphabetical order.

	For example:

	listen

	↓

	eilnst
	silent

	↓

	eilnst

	Finally, we compare both sorted arrays.

	Arrays.equals(firstCharacters, secondCharacters)

	If both arrays are equal, the Strings are anagrams.

	Otherwise, they are not 
	
Use Cases:
	Spell checking applications.
	Word puzzle games.
	Dictionary applications.
	Text analysis.
	Coding interview questions.
	Competitive programming.

/*/
