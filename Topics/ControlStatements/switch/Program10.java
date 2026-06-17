
class Demo{

	public static void main(String[] args){
	
		double x = 25.5;

		switch((int)x){
			
			case (int)5.5:
				System.out.println("5.5");
				break;
			
			case (int)15.5:
				System.out.println("15.5");
				break;

			case (int)25.5:
				System.out.println("25.5");
				break;

			case (int)35.5:
				System.out.println("35.5");
				break;

			default:
				System.out.println("Not match found");
		}
	}
}

/* Output:
 * 	25.5

 *
 * Explanation:
 * 	Here in this code, we are typecasting doube data to int which is allowed in the switch case.
 * */
