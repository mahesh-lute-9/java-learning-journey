
 /*
  * Remove Whitespaces from a String
	
  Problem Statement:

Write a Java program to remove all whitespaces from a given String
/*
/*
------------------------------------------------------------
Program 08 : Remove Whitespaces from a String
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐ Beginner to Intermediate

Concepts Covered
✔ String
✔ replaceAll()
✔ Regular Expression (Regex)

Expected Time : 10 Minutes
------------------------------------------------------------
*/

public class RemoveWhiteSpaces {

    public static void main(String[] args) {

        String input = "Java Programming Language";

        String result = input.replaceAll("\\s", "");

        System.out.println("Original String : " + input);
        System.out.println("Result String   : " + result);

    }
}

/*Output:
	Original String : Java Programming Language
	Result String   : JavaProgrammingLanguage


Explanation:

	First, we create a String named `input`.

	String input = "Java Programming Language";


	To remove all whitespaces, we use the `replaceAll()` method.

	String result = input.replaceAll("\\s", "");

	Here,

*		`replaceAll()` searches the String using a **Regular Expression (Regex)**.
* 		`\\s` represents any whitespace character.

	It removes:

*		 Space (` `)
* 		 Tab (`\t`)
* 		 New Line (`\n`)
*  		 Carriage Return (`\r`)
*		 Form Feed (`\f`)

	The second parameter is an empty String.

		""

	which means every whitespace is replaced with nothing.

	The resulting String is then stored in `result` and printed.


Use Cases:

* Cleaning user input.
* Removing unnecessary spaces before storing data.
* Form validation.
* Text preprocessing.
* Data cleaning in CSV or text files.
* Coding interview questions.


/*
 *
 * 💡 Alternative Approach (Without replaceAll())

	Interviewers sometimes ask you not to use built-in methods.

	String input = "Java Programming Language";

	StringBuilder result = new StringBuilder();

	for (int index = 0; index < input.length(); index++) {

    		char currentCharacter = input.charAt(index);

    		if (currentCharacter != ' ') {
        		result.append(currentCharacter);
    		}

	}

	System.out.println(result);

	
	This approach removes only space characters (' '), whereas the regex version removes all kinds of whitespace.

	📚 Concepts Learned So Far
	Program		New Concept
	01		length()
	02		Reverse String
	03		Palindrome
	04		Character Classification
	05		Uppercase & Lowercase
	06		HashMap
	07		HashSet + StringBuilder
	08		replaceAll() + Regex

*/
