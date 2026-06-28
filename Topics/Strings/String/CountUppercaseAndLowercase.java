
/*Count Uppercase and Lowercase Characters
Problem Statement

Write a Java program to count the number of uppercase and lowercase characters in a given String.
*/
/*
------------------------------------------------------------
Program 05 : Count Uppercase and Lowercase Characters
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ String
✔ charAt()
✔ Character Class Methods
✔ for Loop
✔ if-else

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class CountUppercaseAndLowercase {

    public static void main(String[] args) {

        String input = "Java Programming Is Awesome";

        int uppercaseCount = 0;
        int lowercaseCount = 0;

        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);

            if (Character.isUpperCase(currentCharacter)) {

                uppercaseCount++;

            } else if (Character.isLowerCase(currentCharacter)) {

                lowercaseCount++;

            }

        }

        System.out.println("Input String      : " + input);
        System.out.println("Uppercase Letters : " + uppercaseCount);
        System.out.println("Lowercase Letters : " + lowercaseCount);

    }
}

/*Output:
	Input String      : Java Programming Is Awesome
	Uppercase Letters : 4
	Lowercase Letters : 20

Explanation:

	First, we create a String named input.

	String input = "Java Programming Is Awesome";

	We initialize two variables:

	uppercaseCount → Stores the number of uppercase letters.
	lowercaseCount → Stores the number of lowercase letters.

	Using a for loop, we visit every character in the String.

	char currentCharacter = input.charAt(index);

	The Character.isUpperCase() method checks whether the current character is an uppercase letter.

	Character.isUpperCase(currentCharacter)

	If it returns true, the uppercase counter is increased.

	Similarly, the Character.isLowerCase() method checks whether the current character is a lowercase letter.

	Character.isLowerCase(currentCharacter)

	If it returns true, the lowercase counter is increased.

	Spaces are ignored because they are neither uppercase nor lowercase letters.

	Finally, both counts are displayed.


	Use Cases:
		-Password validation.
		-Text formatting tools.
		-Data cleaning.
		-Document analysis.
		-Coding interview questions.

*/
