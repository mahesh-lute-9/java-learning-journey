
/* Reverse a String
	
 	
  Problem Statement

* Write a Java program to reverse a given String.
*/


public class ReverseString {

    public static void main(String[] args) {

        String input = "Java";
        String reversedString = "";

        for (int index = input.length() - 1; index >= 0; index--) {
            reversedString += input.charAt(index);
        }

        System.out.println("Original String : " + input);
        System.out.println("Reversed String : " + reversedString);
    }
}


/*Output:
 *	Original String : Java
	Reversed String : avaJ

	
Explanation:

	First, we create a String named input.

	String input = "Java";

	We also create an empty String to store the reversed result.

	String reversedString = "";

	The for loop starts from the last character of the String.

	for (int index = input.length() - 1; index >= 0; index--)

	Inside the loop, each character is added one by one to reversedString.

	reversedString += input.charAt(index);

	For "Java" the characters are picked in this order:

	a
	v
	a
	J

	Finally, the reversed String is printed.

	
	
Use Cases:
	-Reverse user input.
	-Check whether a String is a palindrome.
	-Text processing applications.
	-Interview coding questions.
	-Data formatting.

*/


/*
 *
 * ------------------------------------------------------------------------------------------------
 *
 *	"Reverse a String" is one of the most common interview questions, and interviewers often ask you to solve it in multiple ways.

	Here are the approaches I recommend including in your repository.


	Approach 1: Using a for Loop (Beginner) ⭐
	Code:
	
	public class ReverseString {

    		public static void main(String[] args) {

        		String input = "Java";
        		String reversedString = "";

        		for (int index = input.length() - 1; index >= 0; index--) {
            			reversedString += input.charAt(index);
        		}

        	System.out.println(reversedString);
    		}
	}
	
	
	Output:
		avaJ

		
	Explanation:
		-Start from the last character.
		-Move towards the first character.
		-Append each character into a new String.

		
	Use Cases:
		-Best for beginners.
		-Easy to understand.
		-Good for learning loops.

---------------------------------------------------------------------------------------------------------------

	Approach 2: Using Character Array ⭐⭐
	Code:
	public class ReverseString {

    		public static void main(String[] args) {

        		String input = "Java";

        		char[] characters = input.toCharArray();

        		for (int index = characters.length - 1; index >= 0; index--) {
            		System.out.print(characters[index]);
        		}
    		}
	}

	Output:
		avaJ

		
	Explanation:
		-Convert the String into a character array.
		-Traverse the array in reverse order.
		-Print each character.

	Use Cases:
		-Useful when working with character arrays.
		-Common in string manipulation problems.

----------------------------------------------------------------------------------------------------------------	
	
	Approach 3: Using StringBuilder ⭐⭐⭐
	Code:
	public class ReverseString {

   		 public static void main(String[] args) {

     	  		String input = "Java";

        		String reversedString = new StringBuilder(input).reverse().toString();

        		System.out.println(reversedString);
    		}
	}

	Output:
		avaJ

	Explanation:
		-StringBuilder has a built-in reverse() method.
		-It reverses the characters efficiently.
		-toString() converts the result back to a String.

	Use Cases:
		-Most preferred approach.
		-Cleaner code.
		-Frequently used in real-world Java applications.

-------------------------------------------------------------------------------------------------------------------

	Approach 4: Using StringBuffer ⭐⭐⭐
	Code:
	public class ReverseString {

    		public static void main(String[] args) {

        		String input = "Java";

        		String reversedString = new StringBuffer(input).reverse().toString();

        		System.out.println(reversedString);
    		}
	}

	Output:
		avaJ
	
	Explanation:
		-Works exactly like StringBuilder.
		-StringBuffer is synchronized, making it thread-safe.
		-Slightly slower because of synchronization.

	Use Cases:
		-Multithreaded applications.
		-Legacy Java projects.

------------------------------------------------------------------------------------------------------------------

	Approach 5: Using Recursion ⭐⭐⭐⭐
	Code:
	public class ReverseString {

 	  	public static String reverse(String input) {

        	if (input.isEmpty()) {
            		return input;
        	}

        	return reverse(input.substring(1)) + input.charAt(0);
    	}

    		public static void main(String[] args) {

        	String input = "Java";

        	System.out.println(reverse(input));
    		}
	}


	Output:
		avaJ

		
	Explanation:
		-The function calls itself.
		-It removes the first character using substring(1).
		-The removed character is added after the recursive call returns.
		-This continues until the String becomes empty.

		
	Use Cases:
		-Learning recursion.
		-Recursive string processing.
		-Interview questions on recursion.

---------------------------------------------------------------------------------------------------------------

	Approach 6: Using Two Pointers (SDE Preferred) ⭐⭐⭐⭐
	Code:
	public class ReverseString {

    		public static void main(String[] args) {

        		String input = "Java";

       	 		char[] characters = input.toCharArray();

        		int left = 0;
        		int right = characters.length - 1;

        		while (left < right) {

            		char temp = characters[left];
            		characters[left] = characters[right];
            		characters[right] = temp;

            		left++;
            		right--;
        		}

        	System.out.println(new String(characters));
    		}
	}

	Output:
		avaJ
	
	Explanation:
		-Convert the String into a character array.

		Use two pointers:
		-left starts from the beginning.
		-right starts from the end.
		-Swap the characters.
		-Move both pointers toward the center.
		-Convert the character array back into a String.

		
	Use Cases:
		-In-place reversal.
		-Frequently asked in coding interviews.
		-Builds the foundation for array and string algorithms.

		
	Comparison Table:
	
		Approach		Easy		Efficient		Interview		Recommended
		for Loop		✅		⭐⭐			⭐⭐⭐			✅ Beginner
		Character Array		✅		⭐⭐⭐			⭐⭐⭐⭐		✅
		StringBuilder		✅		⭐⭐⭐⭐⭐		⭐⭐⭐⭐⭐		⭐ Best Overall
		StringBuffer		✅		⭐⭐⭐⭐		⭐⭐⭐			For Thread Safety
		Recursion		❌		⭐⭐			⭐⭐⭐⭐		For Recursion Practice
		Two Pointers		⭐⭐⭐		⭐⭐⭐⭐⭐		⭐⭐⭐⭐⭐		⭐ SDE Favorite
 *
 *
 *
 *
 *  */
