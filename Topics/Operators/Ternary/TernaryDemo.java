

class TernaryDemo{

	public static void main(String[] args){
	
		int num = 10;

		String result;

		if(num % 2 == 0){
		
			result = "Even number";
		}else{
			
			result = "Odd number";
		}

		System.out.println(result);
	}
}


/* Output:
 *  	Even number
 *
 *  The Ternary Operator is a short way to write an is-else statement. It checks a condition and gives one of two result,
 *
 *  Syntax:
 *  	condition ? value_if_true : value_if_false
 *
 *  	if the condition is true, the first value is selected. If the condition is false, the second value is selected. 	
 *
 * */
