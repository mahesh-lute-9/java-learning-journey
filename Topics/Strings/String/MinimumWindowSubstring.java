/*
Minimum Window Substring
Problem Statement

Write a Java program to find the smallest substring in a given String that contains all the characters of another String.

Example
Input String : ADOBECODEBANC
Pattern      : ABC

Output :
BANC

The substring "BANC" is the smallest substring that contains all the characters A, B, and C.

/*
------------------------------------------------------------
Program 20 : Minimum Window Substring
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Hard

Concepts Covered
✔ String
✔ HashMap
✔ Sliding Window
✔ Two Pointers
✔ Character Frequency

Expected Time : 35 Minutes
------------------------------------------------------------
*/

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {

        String input = "ADOBECODEBANC";
        String pattern = "ABC";

        if (input.length() < pattern.length()) {
            System.out.println("No valid window found.");
            return;
        }

        Map<Character, Integer> patternFrequency = new HashMap<>();
        Map<Character, Integer> windowFrequency = new HashMap<>();

        for (char character : pattern.toCharArray()) {
            patternFrequency.put(
                    character,
                    patternFrequency.getOrDefault(character, 0) + 1
            );
        }

        int left = 0;
        int matchedCharacters = 0;

        int minimumWindowLength = Integer.MAX_VALUE;
        int windowStart = 0;

        for (int right = 0; right < input.length(); right++) {

            char currentCharacter = input.charAt(right);

            windowFrequency.put(
                    currentCharacter,
                    windowFrequency.getOrDefault(currentCharacter, 0) + 1
            );

            if (patternFrequency.containsKey(currentCharacter) &&
                windowFrequency.get(currentCharacter)
                        <= patternFrequency.get(currentCharacter)) {

                matchedCharacters++;

            }

            while (matchedCharacters == pattern.length()) {

                if (right - left + 1 < minimumWindowLength) {

                    minimumWindowLength = right - left + 1;
                    windowStart = left;

                }

                char leftCharacter = input.charAt(left);

                windowFrequency.put(
                        leftCharacter,
                        windowFrequency.get(leftCharacter) - 1
                );

                if (patternFrequency.containsKey(leftCharacter) &&
                    windowFrequency.get(leftCharacter)
                            < patternFrequency.get(leftCharacter)) {

                    matchedCharacters--;

                }

                left++;

            }

        }

        if (minimumWindowLength == Integer.MAX_VALUE) {

            System.out.println("No valid window found.");

        } else {

            String result =
                    input.substring(
                            windowStart,
                            windowStart + minimumWindowLength
                    );

            System.out.println("Input String : " + input);
            System.out.println("Pattern      : " + pattern);
            System.out.println("Result       : " + result);

        }

    }

}

/*
Output
	Input String : ADOBECODEBANC
	Pattern      : ABC
	Result       : BANC
	
Explanation:

	First, we create two Strings.

	String input = "ADOBECODEBANC";
	String pattern = "ABC";

	Next, we create two HashMap objects.

	Map<Character, Integer> patternFrequency = new HashMap<>();
	Map<Character, Integer> windowFrequency = new HashMap<>();
	patternFrequency stores the required frequency of each character in the pattern.
	windowFrequency stores the frequency of characters currently inside the sliding window.

	Using a for loop, we store the frequency of every character in the pattern.

	for (char character : pattern.toCharArray())

	We then use the Sliding Window technique.

	Two pointers are maintained:

	int left = 0;
	int right = 0;
	right expands the window by moving forward.
	left shrinks the window whenever all required characters are found.

	Every time a character is added to the window, we update its frequency.

	windowFrequency.put(
        	currentCharacter,
        	windowFrequency.getOrDefault(currentCharacter, 0) + 1
	);

	Whenever all required characters are matched, we check whether the current window is smaller than the previous best window.

	if (right - left + 1 < minimumWindowLength)

	If it is smaller, we store its starting position and length.

	Next, we move the left pointer forward to reduce the window size while still trying to keep all required characters inside it.

	This process continues until the entire String has been processed.

	Finally, the smallest valid substring is extracted using:

		input.substring(
        		windowStart,
        		windowStart + minimumWindowLength
		);

		
Use Cases:
	Search engines.
	DNA sequence analysis.
	Text search algorithms.
	Pattern matching.
	Log analysis.
	Coding interview questions.
	Competitive programming.

*/
