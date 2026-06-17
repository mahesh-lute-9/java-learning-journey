

class Demo{
	
	public static main(String[] args){
	
		System.out.println("Hello World!");
	}
}


/* Output :
 * 	Program2.java:5: error: invalid method declaration; return type required
        	public static main(String[] args){
                      ^
	1 error
 *
 *
 * Explanation :
 * 	The error occurs because the main method is missing it's return type. In Java
 * 	every method must declare a return ty[e, and main must be written as public static void main(..).
 *
 * 	Since  void is missing, the compiler doesn't recognise it as a valid method declaration and throws
 * 	the "return type required" error.
 *
