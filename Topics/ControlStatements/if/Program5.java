

class Demo{

	public static void main(String[] args){

		int x = 10;

		int y = 9;

		System.out.println("Statement1");

		if(x = y){

			System.out.println("Statement2");	
		}

		System.out.println("Statement3");
	}
}


/* Output:
 * 	Program5.java:13: error: incompatible types: int cannot be converted to boolean
                if(x = y){
                     ^
1 error

 *
 * Explanation:
 * 	in if, the condition should operate boolean values onlu x = y is an assignment operation in the if condition. Hence
 * 	there is an error.
 *
 * */
