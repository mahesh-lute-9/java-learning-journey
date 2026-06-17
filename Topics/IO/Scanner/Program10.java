
//Take address from user and print it.


import java.util.*;

class Address{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Society Name: ");
		String socName = sc.nextLine();
		
		System.out.println("Enter wing Name: ");
		char wing = sc.next().charAt(0);

		System.out.println("Enter flat No.: ");
		int flatNo = sc.nextInt();

		System.out.println("Address: ");

		System.out.println("Society Name: " + socName);

		System.out.println("Wing: " + wing);

		System.out.println("flat No.: " + flatNo);
	}
}


/* Output:
 * 	Enter Society Name:
	Sun Universe
	Enter wing Name:
	A
	Enter flat No.:
	201
	Address:
	Society Name: Sun Universe
	Wing: A
	flat No.: 201
 *
 *
 * Explanation:
 * 	When the program starts, a Scanner object is created to read input from the keyboard. First,the program asks for the society name. The
 * 	method sc.nextLine() is used, which reads the entire line including spaces, so when the user enters "Sun Universe", the full name is stored
 * 	in the variable socName.
 *
 * 	Next, the program asks for the wing. Since there is no direct method in Scanner to read a single character, sc.next() is used to read a word, 
 * 	and then .charAt(0) is applied to extract the first character. So when the user enters "A", the character 'A' is stored in the variable wing.
 *
 * 	Then, the program asks for the flat number. The method sc.nextInt() reads an integer value, so when the user enters 201,it stored
 * 	in the variable flatNo.
 *
 *
 * 	Thus, the program shows how to handle multiple data types using different Scanner methods: 
 * 		nextLine() for full Strings
 * 		next().charAt(0) for characters
 * 		nextInt() for integers
 * 		nextFloat() for float
 *
 * */
