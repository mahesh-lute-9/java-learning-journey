

class Demo{

	public static void main(String[] args){

		int x = 5;

		switch(x){
		
			case 1:
				System.out.println("One");
				
			case 2:
				System.out.println("Two");

			case 3:
				System.out.println("Three");

			case 4:
				System.out.println("Four");

			case 5:
				System.out.println("Five");

			default:
				System.out.println("Invalid choice/num");
		}
	}
}

/* Output:
 * 	Five
 * 	Invalid choice/num
 *
 * Explanation:
 * 	Once the match is found then the output is printed, when the break statement is written all lines
 * 	will be printed after the match.
 *
 * */
