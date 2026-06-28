
/*
Longest Palindromic Substring
Problem Statement

Write a Java program to find the longest palindromic substring in a given String.

A palindrome is a String that reads the same forward and backward.

Example
Input  : babad

Output : bab

Note: "aba" is also a valid answer for the same input.

Code
/*
------------------------------------------------------------
Program 17 : Longest Palindromic Substring
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Hard

Concepts Covered
✔ String
✔ Two Pointers
✔ Expand Around Center
✔ substring()

Expected Time : 30 Minutes
------------------------------------------------------------
*/

public class LongestPalindromicSubstring {

    public static void main(String[] args) {

        String input = "babad";

        if (input == null || input.length() < 2) {
            System.out.println("Longest Palindrome : " + input);
            return;
        }

        int start = 0;
        int end = 0;

        for (int index = 0; index < input.length(); index++) {

            int oddLength = expandAroundCenter(input, index, index);

            int evenLength = expandAroundCenter(input, index, index + 1);

            int maximumLength = Math.max(oddLength, evenLength);

            if (maximumLength > end - start) {

                start = index - (maximumLength - 1) / 2;
                end = index + maximumLength / 2;

            }

        }

        System.out.println("Input String       : " + input);
        System.out.println("Longest Palindrome : "
                + input.substring(start, end + 1));

    }

    private static int expandAroundCenter(String input,
                                          int left,
                                          int right) {

        while (left >= 0 &&
                right < input.length() &&
                input.charAt(left) == input.charAt(right)) {

            left--;
            right++;

        }

        return right - left - 1;

    }

}

/*Output;
	Input String       : babad
	Longest Palindrome : bab

Explanation:

	First, we create the input String.

	String input = "babad";

	A palindrome can have two types of centers:

	Odd-length palindrome (Example: racecar)
	Even-length palindrome (Example: abba)

	So, for every character in the String, we check both possibilities.

	int oddLength = expandAroundCenter(input, index, index);

	int evenLength = expandAroundCenter(input, index, index + 1);

	The expandAroundCenter() method starts from the center and moves in both directions.

	while (left >= 0 &&
       		right < input.length() &&
       		input.charAt(left) == input.charAt(right))

	As long as both characters are equal, the palindrome keeps expanding.

	The method returns the length of the palindrome.

	return right - left - 1;

	If the newly found palindrome is longer than the previous one, we update the starting and ending positions.

	start = index - (maximumLength - 1) / 2;
	end = index + maximumLength / 2;
	
	Finally, we extract the longest palindrome using the substring() method.

	input.substring(start, end + 1)

	
	
Use Cases:
	DNA sequence analysis.
	Bioinformatics.
	Text analysis.
	Pattern matching.
	Coding interview questions.
	Competitive programming.

*/
