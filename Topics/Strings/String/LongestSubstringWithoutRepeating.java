/*
Longest Substring Without Repeating Characters
Problem Statement

Write a Java program to find the length of the longest substring without repeating characters.

Example
Input  : abcabcbb

Output : 3

Explanation: The longest substring without repeating characters is "abc".

Code
/*
------------------------------------------------------------
Program 18 : Longest Substring Without Repeating Characters
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Hard

Concepts Covered
✔ String
✔ HashSet
✔ Sliding Window
✔ Two Pointers

Expected Time : 30 Minutes
------------------------------------------------------------
*/

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    public static void main(String[] args) {

        String input = "abcabcbb";

        Set<Character> uniqueCharacters = new HashSet<>();

        int left = 0;
        int maximumLength = 0;

        for (int right = 0; right < input.length(); right++) {

            while (uniqueCharacters.contains(input.charAt(right))) {

                uniqueCharacters.remove(input.charAt(left));
                left++;

            }

            uniqueCharacters.add(input.charAt(right));

            maximumLength = Math.max(maximumLength, right - left + 1);

        }

        System.out.println("Input String             : " + input);
        System.out.println("Longest Substring Length : " + maximumLength);

    }

}


/*Output:
	Input String             : abcabcbb
	Longest Substring Length : 3

	
Explanation:

	First, we create the input String.

	String input = "abcabcbb";

	We use a HashSet to store the unique characters currently present in the substring.

	Set<Character> uniqueCharacters = new HashSet<>();

	We use two pointers:

	left → Starting index of the current substring.
	right → Ending index of the current substring.

	Initially,

	left = 0
	right = 0

	As right moves forward, we check whether the current character already exists in the HashSet.

	uniqueCharacters.contains(input.charAt(right))

	If the character already exists, it means a duplicate has been found.

	We keep removing characters from the left side of the window until the duplicate is removed.

	uniqueCharacters.remove(input.charAt(left));
	left++;

	After removing duplicates, we add the current character to the HashSet.

	uniqueCharacters.add(input.charAt(right));

	The current substring length is calculated as:

	right - left + 1

	We compare it with the previous maximum length.

	maximumLength = Math.max(maximumLength, right - left + 1);

	Finally, the maximum length of a substring without repeating characters is printed.



Use Cases:
	Text editors.
	Search engines.
	Data validation.
	Network packet analysis.
	Coding interview questions.
	Competitive programming.

*/
