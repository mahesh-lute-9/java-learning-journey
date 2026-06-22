
//sum of args[]

class Demo{

	public static void main(String[] args){
	
		int sum = 0;

		for(int i = 0; i < args.length; i++){
			
			sum = sum + args[i];
		}
	}
}

/* Output:
 * 	An compile time error as:
 * 		
 * 		Program4.java:12: error: incompatible types: String cannot be converted to int
                        sum = sum + args[i];
                                  ^
	1 error
 *
 * Explanation:
 * 	here the error comes because the command line arguments are of type String and they can't be converted into type sum i.e: int.
 */
