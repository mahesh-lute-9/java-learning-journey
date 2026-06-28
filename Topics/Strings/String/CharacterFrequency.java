/*
Count Frequency of Each Character
Problem Statement

Write a Java program to count the frequency of each character in a given String.
*/
/*
------------------------------------------------------------
Program 06 : Count Frequency of Each Character
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ String
✔ HashMap
✔ charAt()
✔ for Loop
✔ Frequency Counting

Expected Time : 15 Minutes
------------------------------------------------------------
*/

import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {

    public static void main(String[] args) {

        String input = "programming";

        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);

            if (characterFrequency.containsKey(currentCharacter)) {

                characterFrequency.put(
                        currentCharacter,
                        characterFrequency.get(currentCharacter) + 1
                );

            } else {

                characterFrequency.put(currentCharacter, 1);

            }

        }

        System.out.println("Input String : " + input);
        System.out.println("\nCharacter Frequencies");

        for (Map.Entry<Character, Integer> entry : characterFrequency.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());

        }

    }
}

/*Output:
	Input String : programming

	Character Frequencies

	p : 1
	r : 2
	o : 1
	g : 2
	a : 1
	m : 2
	i : 1
	n : 1

	Note: Since HashMap does not maintain insertion order, the output order may vary.


Explanation:

	First, we create a String named input.

	String input = "programming";

	Next, we create a HashMap.

	Map<Character, Integer> characterFrequency = new HashMap<>();

	The key stores the character, and the value stores the number of times that character appears.

Example:

	Character     Frequency

	p      ----->     1
	r      ----->     2
	g      ----->     2

	Using a for loop, we visit every character in the String.

	char currentCharacter = input.charAt(index);

	If the character already exists in the HashMap, we increase its count by 1.

	characterFrequency.put(
    		currentCharacter,
   	 	characterFrequency.get(currentCharacter) + 1
	);

	Otherwise, we insert the character into the HashMap with a frequency of 1.

	characterFrequency.put(currentCharacter, 1);

	Finally, we use a for-each loop to print every character and its frequency.

	for (Map.Entry<Character, Integer> entry : characterFrequency.entrySet())

	Each entry contains:

		entry.getKey() → Character
		entry.getValue() → Frequency

		
Use Cases:
	-Text analytics.
	-Word and character frequency analysis.
	-Data compression algorithms.
	-Search engine indexing.
	-Coding interview questions.
	-Building histograms.
	-Frequency-based string problems.



📌 What's New in This Program?

This is the first program where you've used:

✅ HashMap
✅ Map
✅ Map.Entry
✅ containsKey()
✅ put()
✅ get()
✅ Frequency counting

These concepts are used extensively in Java interview questions.

*/
