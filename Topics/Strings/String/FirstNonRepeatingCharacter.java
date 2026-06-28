
/*
Find the First Non-Repeating Character in a String
Problem Statement

Write a Java program to find the first non-repeating character in a given String.

Example
Input  : programming
Output : p
Code
------------------------------------------------------------
Program 10 : Find the First Non-Repeating Character
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ String
✔ HashMap
✔ Character Frequency
✔ for Loop

Expected Time : 15 Minutes
------------------------------------------------------------
*/

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {

        String input = "programming";

        Map<Character, Integer> characterFrequency = new HashMap<>();

        // Count the frequency of each character
        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);

            characterFrequency.put(
                    currentCharacter,
                    characterFrequency.getOrDefault(currentCharacter, 0) + 1
            );

        }

        // Find the first non-repeating character
        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);

            if (characterFrequency.get(currentCharacter) == 1) {

                System.out.println("Input String                 : " + input);
                System.out.println("First Non-Repeating Character: " + currentCharacter);

                return;

            }

        }

        System.out.println("No non-repeating character found.");

    }
}

/*Output;
	Input String                 : programming
	First Non-Repeating Character: p

	
Explanation:

	First, we create a String named input.

	String input = "programming";

	Next, we create a HashMap to store the frequency of each character.

	Map<Character, Integer> characterFrequency = new HashMap<>();

	Using the first for loop, we count how many times each character appears.

	characterFrequency.put(
    		currentCharacter,
    		characterFrequency.getOrDefault(currentCharacter, 0) + 1
	);

	The getOrDefault() method works as follows:

	If the character already exists in the HashMap, its frequency is increased by 1.
	If it does not exist, it starts with a frequency of 0, and then 1 is added.

	After counting the frequencies, we traverse the String again.

	for (int index = 0; index < input.length(); index++)

	For each character, we check its frequency.

	if (characterFrequency.get(currentCharacter) == 1)

	The first character whose frequency is exactly 1 is the first non-repeating character.

	Once it is found, we print it and exit the program using:

	return;

	If every character repeats, the program prints:

	No non-repeating character found.

	
Use Cases:
	-Text analytics.
	-Search engines.
	-Data validation.
	-Log file analysis.
	-Coding interview questions.
	-Character frequency analysis.

*/
