/*
String Compression (Run-Length Encoding)
Problem Statement

Write a Java program to compress a given String by replacing consecutive repeated characters with the character followed by its count.

If a character appears only once, it should be printed without the count.

Example
Input  : aaabbccccdd

Output : a3b2c4d2

------------------------------------------------------------
Program 16 : String Compression (Run-Length Encoding)
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ String
✔ StringBuilder
✔ Character Comparison
✔ Loop

Expected Time : 20 Minutes
------------------------------------------------------------
*/

public class StringCompression {

    public static void main(String[] args) {

        String input = "aaabbccccdd";

        StringBuilder compressedString = new StringBuilder();

        int count = 1;

        for (int index = 0; index < input.length(); index++) {

            if (index < input.length() - 1 &&
                    input.charAt(index) == input.charAt(index + 1)) {

                count++;

            } else {

                compressedString.append(input.charAt(index));

                if (count > 1) {
                    compressedString.append(count);
                }

                count = 1;

            }

        }

        System.out.println("Original String   : " + input);
        System.out.println("Compressed String : " + compressedString);

    }
}

/*Output:
	Original String   : aaabbccccdd
	Compressed String : a3b2c4d2

	
Explanation:

	First, we create the input String.

	String input = "aaabbccccdd";

	We use a StringBuilder to build the compressed String efficiently.

	StringBuilder compressedString = new StringBuilder();

	A variable named count keeps track of how many times the current character appears consecutively.

	int count = 1;

	Using a for loop, we compare each character with the next character.

	input.charAt(index) == input.charAt(index + 1)

	If both characters are the same, we increase the count.

	count++;

	When a different character is found, we append the current character to the result.

	compressedString.append(input.charAt(index));

	If the character appeared more than once, we also append its count.

	compressedString.append(count);

	Finally, we reset the counter to 1 for the next character sequence.

	After processing all characters, the compressed String is displayed.


	
Use Cases:
	-Data compression.
	-Log file optimization.
	-Text processing.
	-File storage optimization.
	-Coding interview questions.
	-Run-Length Encoding (RLE) algorithms.

/*/
