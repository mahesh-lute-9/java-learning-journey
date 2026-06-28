
/* Count Vowels, Consonants, Digits and Special Characters
Problem Statement;

 Write a Java program to count the number of vowels, consonants, digits, and special characters present in a given String.

Code:
------------------------------------------------------------
Program 04 : Count Vowels, Consonants, Digits and Special Characters
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ String
✔ charAt()
✔ Character Class Methods
✔ if-else
✔ Loop

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class CountCharacters {

    public static void main(String[] args) {

        String input = "Java123@Programming";

        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int specialCharacters = 0;

        for (int index = 0; index < input.length(); index++) {

            char currentCharacter = Character.toLowerCase(input.charAt(index));

            if (currentCharacter >= 'a' && currentCharacter <= 'z') {

                if (currentCharacter == 'a' ||
                    currentCharacter == 'e' ||
                    currentCharacter == 'i' ||
                    currentCharacter == 'o' ||
                    currentCharacter == 'u') {

                    vowels++;

                } else {

                    consonants++;

                }

            } else if (Character.isDigit(currentCharacter)) {

                digits++;

            } else {

                specialCharacters++;

            }
        }

        System.out.println("Input String        : " + input);
        System.out.println("Vowels              : " + vowels);
        System.out.println("Consonants          : " + consonants);
        System.out.println("Digits              : " + digits);
        System.out.println("Special Characters  : " + specialCharacters);

    }
}

/*Output:
	Input String        : Java123@Programming
	Vowels              : 6
	Consonants          : 9
	Digits              : 3
	Special Characters  : 1

Explanation:

	First, we create a String named input.

	String input = "Java123@Programming";

	We also create four variables to count:

	Vowels
	Consonants
	Digits
	Special Characters

	Using a for loop, we visit each character one by one.

	for (int index = 0; index < input.length(); index++)

	To avoid checking both uppercase and lowercase letters separately, we convert every character to lowercase.

	char currentCharacter = Character.toLowerCase(input.charAt(index));

	Now we check:

		If the character is between 'a' and 'z', it is an alphabet.
		If it is one of a, e, i, o, u, it is a vowel.
		Otherwise, it is a consonant.
		If it is a digit, we increase the digit counter.
		Any remaining character is treated as a special character.

	Finally, we print all the counts.


	Use Cases:
		-Password strength validation.
		-Text analysis applications.
		-Data validation.
		-Input sanitization.
		-Coding interview questions
 * */
