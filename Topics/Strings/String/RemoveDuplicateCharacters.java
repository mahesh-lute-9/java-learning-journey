/*
Remove Duplicate Characters from a String
Problem Statement

Write a Java program to remove duplicate characters from a given String while preserving the order of their first occurrence.
*/
/*
------------------------------------------------------------
Program 07 : Remove Duplicate Characters from a String
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ String
✔ HashSet
✔ StringBuilder
✔ for Loop
✔ Duplicate Removal

Expected Time : 15 Minutes
------------------------------------------------------------
*/

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateCharacters {

    public static void main(String[] args) {

        String input = "programming";

        Set<Character> uniqueCharacters = new HashSet<>();

        StringBuilder result = new StringBuilder();

        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);

            if (!uniqueCharacters.contains(currentCharacter)) {

                uniqueCharacters.add(currentCharacter);
                result.append(currentCharacter);

            }

        }

        System.out.println("Original String : " + input);
        System.out.println("Result String   : " + result);

    }
}

/*Output:
	Original String : programming
	Result String   : progamin

	
Explanation:

	First, we create a String named input.

	String input = "programming";

	Next, we create a HashSet.

	Set<Character> uniqueCharacters = new HashSet<>();

	The HashSet stores only unique characters.

	If we try to add the same character again, it will simply ignore it.

	We also create a StringBuilder.

	StringBuilder result = new StringBuilder();

	It is used to efficiently build the final String without creating multiple String objects.

	Using a for loop, we visit each character one by one.

	char currentCharacter = input.charAt(index);

	Before adding the character, we check whether it already exists in the HashSet.

	if (!uniqueCharacters.contains(currentCharacter))

	If the character is not present,

	Add it to the HashSet
	Append it to the StringBuilder
	uniqueCharacters.add(currentCharacter);
	result.append(currentCharacter);

	If the character is already present, we simply skip it.

	Finally, we print the String stored in the StringBuilder.


Use Cases:
	-Removing duplicate characters from user input.
	-Data cleaning and preprocessing.
	-Preparing unique identifiers.
	-Text processing applications.
	-Coding interview questions.



	📚 Concepts Learned So Far
		Program		New Concept
		Program 01	length()
		Program 02	Reverse String
		Program 03	equals()
		Program 04	Character class methods
		Program 05	isUpperCase() & isLowerCase()
		Program 06	HashMap
		Program 07	HashSet + StringBuilder


*/
