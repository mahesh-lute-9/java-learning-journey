

class LogicalDemo{

	public static void main(String[] args){
		
		int x = 7;
		int y = 8;

		System.out.println(x&&y);
	}
}


/* Output:
 * 	RelationalDemo1.java:11: error: incompatible types: boolean cannot be converted to int
                int result = x<y;
                              ^
1 error
 *
 * Explanation:
 * 	The logical operators only works with boolean values(true or false). Here x and y are int variable, so
 * 	Java gives an error because && cannot be applied for integers.
 *
 * */
