/*
Find Duplicate Characters in a String
Problem Statement

Write a Java program to find all duplicate characters present in a given String along with their occurrence count.

Example
Input  : programming

Output :
r : 2
g : 2
m : 2

Code:

------------------------------------------------------------
Program 12 : Find Duplicate Characters in a String
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

public class FindDuplicateCharacters {

    public static void main(String[] args) {

        String input = "programming";

        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);

            characterFrequency.put(
                    currentCharacter,
                    characterFrequency.getOrDefault(currentCharacter, 0) + 1
            );

        }

        System.out.println("Input String : " + input);
        System.out.println("\nDuplicate Characters");

        for (Map.Entry<Character, Integer> entry : characterFrequency.entrySet()) {

            if (entry.getValue() > 1) {

                System.out.println(entry.getKey() + " : " + entry.getValue());

            }

        }

    }
}

/*Output:
	Input String : programming

	Duplicate Characters

	r : 2
	g : 2
	m : 2

	Note: Since HashMap does not maintain insertion order, the output order may vary.


	
Explanation:

	First, we create a String named input.

	String input = "programming";

	Next, we create a HashMap to store the frequency of each character.

	Map<Character, Integer> characterFrequency = new HashMap<>();

	Using a for loop, we visit each character in the String.

	char currentCharacter = input.charAt(index);

	For every character, we update its frequency.

	characterFrequency.put(
    		currentCharacter,
    		characterFrequency.getOrDefault(currentCharacter, 0) + 1
	);

	After counting the frequencies, we iterate through the HashMap.

	for (Map.Entry<Character, Integer> entry : characterFrequency.entrySet())

	Each entry contains:

	entry.getKey() → Character
	entry.getValue() → Frequency

	If the frequency is greater than 1, the character is a duplicate.

	if (entry.getValue() > 1)

	Finally, we print the duplicate character along with its occurrence count.


Use Cases:
	Data analysis.
	Text processing.
	Duplicate detection.
	Search optimization.
	Coding interview questions.
	Character frequency analysis.

/**/
