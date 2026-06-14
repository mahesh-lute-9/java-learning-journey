
class Demo{

	public static void main(String[] args){
	
		double x = 25.5;

		switch((int)x){
			
			case 5.5:
				System.out.println("5.5");
				break;
			
			case 15.5:
				System.out.println("15.5");
				break;

			case 25.5:
				System.out.println("25.5");
				break;

			case 35.5:
				System.out.println("35.5");
				break;

			default:
				System.out.println("Not match found");
		}
	}
}

/* Output:
 * 	Program9.java:10: error: incompatible types: possible lossy conversion from double to int
                        case 5.5:
                             ^
Program9.java:14: error: incompatible types: possible lossy conversion from double to int
                        case 15.5:
                             ^
Program9.java:18: error: incompatible types: possible lossy conversion from double to int
                        case 25.5:
                             ^
Program9.java:22: error: incompatible types: possible lossy conversion from double to int
                        case 35.5:
                             ^
4 errors

 *
 * Explanation:
 * 	Here in cases we're used Double/float values as cases therefore it gives as error for this.
 * */
