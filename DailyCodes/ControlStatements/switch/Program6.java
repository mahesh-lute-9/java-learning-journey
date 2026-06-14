
class Demo{

	public static void main(String[] args){

		char ch = 'F';

		switch(ch){
		
			default:
				System.out.println("Wrong input");
				break;

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
 * 
 * Explanation:
 * 	The default case is written with the break statement, hence the output is only matched to the default case..
 *
 * */
