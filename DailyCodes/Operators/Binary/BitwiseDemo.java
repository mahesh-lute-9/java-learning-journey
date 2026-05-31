


class BitwiseDemo{
	
	public static void main(String[] args){
	
		int x = 7;
		
		int y = 8;

	
		System.out.println("x & y = " + (x & y));

		System.out.println("x | y = " + (x | y));

		System.out.println("x ^ y = " + (x ^ y));

		System.out.println("~x = " + (~x));

		System.out.println("x << 2 = " + (x << 2));

		System.out.println("x >> 2 = " + (x >> 2));
	}
}


/* Output: 
	x & y = 0 
	x | y = 15 
	x ^ y = 15 
	~x = -8 
	x << 2 = 28 
	x >> 2 = 1 
	
	
	Explanation :
		In this program, bitwise operators are demonstrated using x = 7 and y = 8. The operators &, |, and ^ perform
		AND, OR, and XOR operations on the binary values of the numbers. The ~ operator gives the bitwise
		complement, while << and >> perform left shift and right shift operations, shifting the bits of x to produce the
		respective results.
 *
 * */
