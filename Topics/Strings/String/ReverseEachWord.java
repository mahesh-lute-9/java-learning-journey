
/*
Reverse Each Word in a Sentence
Problem Statement

Write a Java program to reverse each word of a given sentence while keeping the word order unchanged.

Example
Input  : Java Programming Language
Output : avaJ gnimmargorP egaugnaL
Code

------------------------------------------------------------
Program 09 : Reverse Each Word in a Sentence
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Intermediate

Concepts Covered
✔ String
✔ split()
✔ StringBuilder
✔ for-each Loop

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class ReverseEachWord {

    public static void main(String[] args) {

        String sentence = "Java Programming Language";

        String[] words = sentence.split(" ");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            String reversedWord = new StringBuilder(word).reverse().toString();

            result.append(reversedWord).append(" ");

        }

        System.out.println("Original Sentence : " + sentence);
        System.out.println("Result Sentence   : " + result.toString().trim());

    }
}

/*Output:
	Original Sentence : Java Programming Language
	Result Sentence   : avaJ gnimmargorP egaugnaL

	
Explanation:

	First, we create a sentence.

	String sentence = "Java Programming Language";

	Next, we split the sentence into individual words.

	String[] words = sentence.split(" ");

	The split(" ") method divides the sentence wherever it finds a space.

	The array becomes:

	Java
	Programming
	Language

	We create a StringBuilder named result to store the final sentence.

	StringBuilder result = new StringBuilder();

	Using a for-each loop, we process one word at a time.

	for (String word : words)

	Each word is reversed using the built-in reverse() method of StringBuilder.

	String reversedWord = new StringBuilder(word).reverse().toString();

	The reversed word is then appended to the result along with a space.

	result.append(reversedWord).append(" ");

	Finally, trim() removes the extra space added after the last word before printing the result.



	Use Cases:
		-Text formatting applications.
		-Word processing software.
		-Coding interview questions.
		-String manipulation practice.
		-Natural Language Processing (NLP) preprocessing.
*/
