
class Demo{

	public static void main(String[] args){

		int x = 65;

		switch(x){
		
			case 65:
				System.out.println("65");
				break;	

			case 'A':
				System.out.println("A");
				break;	

			case 66:
				System.out.println("66");
				break;

			case 'B':
				System.out.println("B");
				break;

			default:
				System.out.println("No match found..");
		}
	}
}

/* Output:
 * 	Program7.java:14: error: duplicate case label
                        case 'A':
                        ^
Program7.java:22: error: duplicate case label
                        case 'B':
                        ^
2 errors
 *
 * Explanation:
 * 	The duplicate case is not allowed in switch, 65 and 66 are ASCII values of A and B respectively.
 *
 * */
