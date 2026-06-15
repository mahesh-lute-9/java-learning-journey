

class Demo{

	public static void main(String[] args){
	
		int x = 5;

		do{
			System.out.println("In do-while loop");
		
		}while(x < 5);
	}
}


/* Output:
 * 	In do-while loop
 *
 *
 * A do-while loop executes the code block at least once, and then checks the condition.
 *
 * Flow:
 * 	1. Execute the code inside do
 * 	2. Check the condition in while.
 * 	3. if true, execute again.
 * 	4. if false, stop
 *
 * In above code the condition get's failed(false) but still it is do-while loop so at least once the statement will print.
 */
