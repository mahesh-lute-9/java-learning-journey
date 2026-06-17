

class Demo{

	public static void main(String[] args){
		
		short x = (short)65535;
	
		System.out.println(x);
	}
}


/* Output:
 *	-1
 *
 *
 * Explanation:
 * 	short is of 2 byte signed integer, meaning it can store values from -32768 to 32767. 65535 is too large for a short, so
 * 	it wraps around(since short is 2 byte).
 *
 * 	65535 in binary is 1111 1111 1111 1111(16 bits) and represented as -1.
 *
 * */
