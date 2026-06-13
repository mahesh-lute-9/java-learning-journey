

class Demo{

	public static void main(String[] args){

		int x = 10;

		int y = 9;

		System.out.println("Statement1");

		if(--x <= ++y){
			
			System.out.println("Statement2");
		}

		System.out.println("Statement3");
	}
}


/* Output:
 * 	Statement1
	Statement2
	Statement3
 *
 * Explanation:
 * 	Here the value of x becomes 9 and value of y becomes 10 then they checks as 9 <= 10(true), so condition is true 
 * 	that's why it goes into statement
 *
 * */
