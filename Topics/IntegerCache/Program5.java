

class Demo{

	public static void main(String[] args){
	
		int x = 2147483647;

		int y = (int)2147483648;

		System.out.println(x);

		System.out.println(y);
	}
}

/* Output:
 *	Program5.java:9: error: integer number too large: 2147483648
                int y = (int)2147483648;
                             ^
1 error


 *
 * 
 **/
