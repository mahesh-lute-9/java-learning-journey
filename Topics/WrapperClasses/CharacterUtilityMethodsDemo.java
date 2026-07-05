

/*
------------------------------------------------------------
Program 09 : Character Utility Methods

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered

	✔ isDigit()
	✔ isLetter()
	✔ isAlphabetic()
	✔ isUpperCase()
	✔ isLowerCase()
	✔ isWhitespace()
	✔ toUpperCase()
	✔ toLowerCase()

Expected Time : 30 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to demonstrate the most commonly
	used utility methods provided by the Character Wrapper
	Class.

	Check whether a character is a digit, letter,
	alphabetic character, uppercase letter, lowercase
	letter, or whitespace. Also demonstrate character case
	conversion.

------------------------------------------------------------
*/

public class CharacterUtilityMethodsDemo {

    public static void main(String[] args) {

        char character1 = 'A';
        char character2 = 'm';
        char character3 = '7';
        char character4 = ' ';
        char character5 = '@';

        System.out.println("----- isLetter() -----");

        System.out.println(Character.isLetter(character1));
        System.out.println(Character.isLetter(character3));

        System.out.println();

        System.out.println("----- isDigit() -----");

        System.out.println(Character.isDigit(character3));
        System.out.println(Character.isDigit(character1));

        System.out.println();

        System.out.println("----- isAlphabetic() -----");

        System.out.println(Character.isAlphabetic(character1));
        System.out.println(Character.isAlphabetic(character5));

        System.out.println();

        System.out.println("----- isUpperCase() -----");

        System.out.println(Character.isUpperCase(character1));
        System.out.println(Character.isUpperCase(character2));

        System.out.println();

        System.out.println("----- isLowerCase() -----");

        System.out.println(Character.isLowerCase(character2));
        System.out.println(Character.isLowerCase(character1));

        System.out.println();

        System.out.println("----- isWhitespace() -----");

        System.out.println(Character.isWhitespace(character4));
        System.out.println(Character.isWhitespace(character1));

        System.out.println();

        System.out.println("----- Case Conversion -----");

        System.out.println(Character.toUpperCase(character2));

        System.out.println(Character.toLowerCase(character1));

    }

}

/*
------------------------------------------------------------
Output

	----- isLetter() -----

	true
	false

	----- isDigit() -----

	true
	false

	----- isAlphabetic() -----

	true
	false

	----- isUpperCase() -----

	true
	false

	----- isLowerCase() -----

	true
	false

	----- isWhitespace() -----

	true
	false

	----- Case Conversion -----

	M

	a

------------------------------------------------------------
Explanation:

	The Character Wrapper Class provides several utility
	methods for validating and manipulating characters.

	These methods eliminate the need to manually compare
	characters using ASCII or Unicode values.

	Character utility methods are widely used in text
	processing, input validation, parsers, compilers,
	password validation, and editors.

------------------------------------------------------------
Important Points:

	✔ Character.isLetter() checks whether a character is a letter.

	✔ Character.isDigit() checks whether a character is a
	numeric digit.

	✔ Character.isAlphabetic() checks whether a character
	belongs to an alphabetic writing system.

	✔ Character.isUpperCase() checks for uppercase letters.

	✔ Character.isLowerCase() checks for lowercase letters.

	✔ Character.isWhitespace() checks for whitespace
	characters.

	✔ Character.toUpperCase() converts a character to
	uppercase.

	✔ Character.toLowerCase() converts a character to
	lowercase.

------------------------------------------------------------
Common Mistakes

❌ Comparing ASCII values manually.

Instead of

character >= 'A' && character <= 'Z'

prefer

Character.isUpperCase(character)

------------------------------------------------------------

❌ Assuming

isLetter()

returns true for digits.

Digits are not letters.

------------------------------------------------------------

❌ Using

isLetter()

when

isAlphabetic()

is more appropriate for Unicode-aware applications.

------------------------------------------------------------

❌ Forgetting that whitespace includes spaces, tabs,
newlines, and other Unicode whitespace characters.

------------------------------------------------------------
Follow-up Interview Questions

1.

What is the difference between

isLetter()

and

isAlphabetic()?

------------------------------------------------------------

2.

Which Character method checks whether a character is a
digit?

------------------------------------------------------------

3.

How do you convert a lowercase character into uppercase?

------------------------------------------------------------

4.

Which method is commonly used while validating user
input?

------------------------------------------------------------

5.

Why should Character utility methods be preferred over
manual ASCII comparisons?

------------------------------------------------------------
Real World Use Cases

	✔ Username Validation

	✔ Password Validation

	✔ Lexical Analysis

	✔ Compiler Design

	✔ Text Editors

	✔ Search Engines

	✔ Form Validation

	✔ Data Sanitization

------------------------------------------------------------
Practice Questions

1.

Count the number of uppercase letters in a String.

------------------------------------------------------------

2.

Count the number of lowercase letters in a String.

------------------------------------------------------------

3.

Count the number of digits in a String.

------------------------------------------------------------

4.

Remove all whitespace characters from a String using
Character.isWhitespace().

------------------------------------------------------------

5.

Convert every lowercase character in a String to
uppercase without using String.toUpperCase().

------------------------------------------------------------

Quick Revision

	isLetter()

	↓

	Checks Letter

-------------------------

	isDigit()

	↓

	Checks Digit

-------------------------

	isAlphabetic()

	↓

	Checks Alphabetic Character

-------------------------

	isUpperCase()

	↓

	Checks Uppercase

-------------------------

	isLowerCase()

	↓	

	Checks Lowercase

-------------------------

	isWhitespace()

	↓

	Checks Whitespace

-------------------------

	toUpperCase()

	↓

	Lowercase → Uppercase

-------------------------

	toLowerCase()

	↓

	Uppercase → Lowercase

------------------------------------------------------------
*/
