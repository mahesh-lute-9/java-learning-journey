

class Demo{

	public static void main(String[] args){
	
		int x = 2147483647;

		int y = (int)2147483648L;

		System.out.println(x);

		System.out.println(y);
	}
}

/* Output:
 *	2147483647
	-2147483648
 *
 * 
 * Explanation:
 * 	Here the long data is typecasted into the wrap i.e: -2147483648. Now you may have clear idea about int datatype, let's see what happens 
 * 	with the integer class when we write the codes 
 **/
