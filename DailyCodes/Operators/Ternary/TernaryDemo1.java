

class TernaryDemo1{

	public static void main(String[] args){
	
		int num = 10;

		String result = (num %2 == 0) ? "Even number" : "Odd number";

		System.out.println(result);
	}
}


/* Output: 
 * 	Even number
 *
 *
 *
 * 	The program uses the ternary operator to check whether the number is even or odd. Since 10 % 2 == 0 is true,
 *	the result is "Even number"
 *
 * */
