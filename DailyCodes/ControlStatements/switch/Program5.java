
class Demo{

	public static void main(String[] args){

		char ch = 'F';

		switch(ch){
		

			default:
				System.out.println("Wrong input");

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
		}
	}
}

/* Output:
 * 	Wrong input
	A
 * 
 * Explanation:
 * 	The default case is written at the start of the switch case without break hence the output comes with next statement
 * 	then it breaks.
 *
 * */
