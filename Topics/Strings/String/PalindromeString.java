

/* Check Whether a String is Palindrome

   Problem Statement:

	Write a Java program to check whether a given String is a palindrome or not.

	* A palindrome is a String that reads the same forward and backward.

	Examples:
		madam  → Palindrome
		racecar → Palindrome
		java → Not a Palindrome
		level → Palindrome

*/

public class PalindromeString {

    public static void main(String[] args) {

        String input = "madam";
        String reversedString = "";

        for (int index = input.length() - 1; index >= 0; index--) {
            reversedString += input.charAt(index);
        }

        if (input.equals(reversedString)) {
            System.out.println(input + " is a Palindrome.");
        } else {
            System.out.println(input + " is not a Palindrome.");
        }

    }
}

/*Output:
 * 	madam is a Palindrome.
 *
 *
 * Explanation:
	First, we create a String named input.

	String input = "madam";

	We also create an empty String to store the reversed version.

	String reversedString = "";

	Using a for loop, we traverse the String from the last character to the first.

		for (int index = input.length() - 1; index >= 0; index--) {
    			reversedString += input.charAt(index);
		}

	After the loop, the reversed String becomes:

	madam

	Finally, we compare the original String with the reversed String using the equals() method.

	if (input.equals(reversedString))

	If both Strings are equal, the String is a palindrome. Otherwise, it is not.


	Note: We use equals() because it compares the contents of the Strings. Using == would compare object references instead of the actual text.

	Other Approaches:
		1.Using StringBuilder.reverse()
		2.Using Two Pointers (Most Efficient)
		3.Using Recursion
		4.Using a Character Array

		
	Use Cases:
		-Checking palindrome words.
		-Validating symmetric text patterns.
		-Coding interview questions.
		-Text processing applications.
		-Basic algorithm practice.

 * */
