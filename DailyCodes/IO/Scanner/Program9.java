
//concatination and addition


import java.util.*;

class Demo{
	
	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter num1: ");
		int num1 = sc.nextInt();

		System.out.println("Enter num2: ");
		int num2 = sc.nextInt();

		System.out.println("Addition" + num1 + num2);
	}
}


/* Output:
 * 	Enter num1:
	12
	Enter num2:
	13
	Addition1213
 *
 *
 * 	
 * 	In this program, the variables num1 and num2 are primitive data types(integers), while "Addition" is
 * 	a String. Which is class in Java, when the + operator is used with a string. It performs concatibnation
 * 	instead of arithmetic addition.
 * */
