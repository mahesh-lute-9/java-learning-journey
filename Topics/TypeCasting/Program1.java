

class Demo{

	public static void main(String[] args){
	
		byte x = (byte)255;

		System.out.println(x);  
	}
}


/* Output:
 * 	-1
 *
 *
 *
 * Explanation:
 * 	A byte in Java can only store values from -128 to 127. 255 is too bit for a byte, so java wraps it around.
 * 	In binary 255 is 1111 1111, which Java treats as -1.
 *
 * */
