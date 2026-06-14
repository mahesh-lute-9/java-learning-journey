
class Demo{

	public static void main(String[] args){

		char ch = 'F';

		switch(ch){
		
			case 'A':
				System.out.println("A");
				break;	

			case 'B':
				System.out.println("B");
				break;	

			case 'C':
				System.out.println("C");
				break;

			case 'D':
				System.out.println("D");
				break;

			case 'E':
				System.out.println("E");
				break;

			default:
				System.out.println("Wrong input");
		}
	}
}

/* Output:
 * 	Wrong input
 *
 * Explanation:
 * 	The default case handles the wrong input which is out of the match.
 *
 * */
