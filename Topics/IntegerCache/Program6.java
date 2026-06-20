

class Demo{

	public static void main(String[] args){
	
		byte x = 127;

		byte y = 128;

		System.out.println(x);

		System.out.println(y);
	}
}


/* Output:
 * 	Program6.java:9: error: incompatible types: possible lossy conversion from int to byte
                byte y = 128;
                         ^
1 error


 *
 * We can see in the above two codes the error format is diff
 * */
