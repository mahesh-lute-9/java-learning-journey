/*
Group Anagrams
Problem Statement

Write a Java program to group all anagrams from a given array of Strings.

Strings that contain the same characters with the same frequency belong to the same group.

Example
Input :

["eat", "tea", "tan", "ate", "nat", "bat"]

Output :

	[eat, tea, ate]
	[tan, nat]
	[bat]

/*
------------------------------------------------------------
Program 19 : Group Anagrams
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐⭐ Hard

Concepts Covered
✔ String
✔ Arrays
✔ HashMap
✔ ArrayList
✔ Sorting

Expected Time : 30 Minutes
------------------------------------------------------------
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public static void main(String[] args) {

        String[] words = {
                "eat",
                "tea",
                "tan",
                "ate",
                "nat",
                "bat"
        };

        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : words) {

            char[] characters = word.toCharArray();

            Arrays.sort(characters);

            String sortedWord = new String(characters);

            anagramGroups
                    .computeIfAbsent(sortedWord, key -> new ArrayList<>())
                    .add(word);

        }

        System.out.println("Grouped Anagrams:");

        for (List<String> group : anagramGroups.values()) {

            System.out.println(group);

        }

    }

}

/*Output:
	Grouped Anagrams:

	[eat, tea, ate]
	[tan, nat]
	[bat]

	Note: Since HashMap does not preserve insertion order, the order of the groups may vary.

Explanation:

	First, we create an array of Strings.

	String[] words = {
    		"eat",
    		"tea",
    		"tan",
    		"ate",
   		"nat",
    		"bat"
	};

	Next, we create a HashMap.

	Map<String, List<String>> anagramGroups = new HashMap<>();

	In this map:

	Key → Sorted version of a word.
	Value → List of words having the same sorted characters.

	For every word, we convert it into a character array.

	char[] characters = word.toCharArray();

	Then we sort the characters.

	Arrays.sort(characters);

	For example,

	eat

	↓

	aet
	tea

	↓

	aet
	ate

	↓

	aet

	All three produce the same sorted String.

	We convert the sorted characters back into a String.

	String sortedWord = new String(characters);

	Now we insert the original word into the corresponding group.

	anagramGroups
        	.computeIfAbsent(sortedWord, key -> new ArrayList<>())
        	.add(word);

	If the key already exists, the word is added to the existing list.

	Otherwise, a new list is created automatically.

	Finally, we print every anagram group stored in the HashMap.



Use Cases:
	Dictionary applications.
	Spell checking.
	Word games.
	Search optimization.
	Coding interview questions.
	Competitive programming.

*/
