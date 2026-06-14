

class Demo{

	public static void main(String[] args){

		int x = 3;

		switch(x){
		
			case 1:
				System.out.println("One");
				break;	

			case 2:
				System.out.println("Two");
				break;	

			case 3:
				System.out.println("Three");
				break;

			case 4:
				System.out.println("Four");
				break;

			case 5:
				System.out.println("Five");
				break;

			default:
				System.out.println("Invalid choice/num");
		}
	}
}

/* Output:
 * 	Three	
 *
 * Explanation:
 * 	As the break statement is used, the output is printed inly for the match
 *
 * */
