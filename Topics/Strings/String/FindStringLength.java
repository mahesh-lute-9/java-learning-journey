
/*
------------------------------------------------------------
Program 01 : Find Length of a String
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐ Beginner

Concepts Covered
✔ String
✔ length() method
✔ Variables
✔ User Output

Expected Time : 5 Minutes
------------------------------------------------------------
*/

public class FindStringLength {

    public static void main(String[] args) {

        String input = "Java Programming";

        int length = input.length();

        System.out.println("String : " + input);
        System.out.println("Length : " + length);

    }
}



/* Output:
	String : Java Programming
	Length : 16


Explanation:

	A String is a sequence of characters.

	Java provides the length() method to count the total number of characters present in a String.

	String input = "Java Programming";

	Here,

	J
	a
	v
	a
	(space)
	P
	r
	o
	g
	r
	a
	m
	m
	i
	n
	g

	Every character is counted.

	Even the space is considered a character.

	Therefore,

		Length = 16
		
	How does length() work?
		
		int length = input.length();
	
	The length() method belongs to the String class.

	It returns the number of characters present in the String.

	Return type:

		int

	Syntax:

		stringName.length();

	Example:

		String city = "Pune";

		System.out.println(city.length());

	Output:

		4
		
	Dry Run:

	Initial

		input = "Java Programming"

		Step 1

		length()

		↓

		16

		Step 2

		Print String

		↓

		Java Programming

		Step 3

		Print Length

		↓

		16

		Final Output

		String : Java Programming

		Length : 16

	Time Complexity:
		O(1)
	Why?

		The String class stores its length internally.

		It does not count the characters every time you call length().

		So,

		length()

		↓

		Returns stored value

		↓

		Constant Time
		Space Complexity
		O(1)

		No extra memory is created.


	Interview Notes ⭐
	Q1 Why is length() O(1)?

		Because Java stores the length of the String as an internal field.

		It simply returns that value.

	Q2 Difference between length() and length?

		For String

		String s = "Java";

		s.length()

		For Array

		int[] numbers = {1,2,3};

		numbers.length

		Notice

		String

		↓

		Method

		length()
		Array

		↓

		Property

		length

		One of the most common Java interview questions.

	Q3 Does length() count spaces?

	Yes.

	Example

		String s = "Java Programming";

	Output

		16

		because space is also a character.

	Q4 Can length() return 0?

	Yes.

		String s = "";

		System.out.println(s.length());

	Output

		0

	Q5 What happens if String is null?
		
		String s = null;

		System.out.println(s.length());

	Output

		NullPointerException

		Because there is no object to call the method on.

	Common Mistakes
		❌ Mistake 1
		String s = null;

		System.out.println(s.length());

	Throws

		NullPointerException
		❌ Mistake 2

		Confusing

		length

		with

		length()

		Remember

		Array

		↓

		length
		String

		↓

		length()

	Best Practice

		If the String may be null, check first.

		if (input != null) {
   			System.out.println(input.length());
		}
	

Alternative Example (User Input):

	import java.util.Scanner;

	public class FindStringLength {

    		public static void main(String[] args) {

        	Scanner scanner = new Scanner(System.in);

        	System.out.print("Enter a String : ");

        	String input = scanner.nextLine();

        	System.out.println("Length = " + input.length());

        	scanner.close();

    		}
	}

	
Example Output:

	Enter a String :
	Java

	Length = 4

	Real World Uses::

	The length() method is used in many practical scenarios, such as:

	-Password length validation
	-Username validation
	-Form input validation
	-Character limit checks (e.g., Twitter posts)
	-Text editors
	-Search functionality

	
Practice Questions::

Try solving these on your own:

	Easy ⭐
	1.Print the length of your name.
	2.Print the length of your city name.
	3.Check whether the length is even or odd.

	Medium ⭐⭐
	1.Print the first and last character using the length.
	2.Print the middle character of a String.

	Challenge ⭐⭐⭐

	Without using the length() method, count the total number of characters in a String manually.

	
	Key Takeaways::
	
	-String.length() returns the number of characters in a String.
	-Spaces are counted as characters.
	-The return type is int.
	-Time Complexity is O(1) because Java stores the length internally.
	-Arrays use length, whereas Strings use length().
	-Calling length() on a null reference throws a NullPointerException.
 *
 * */
