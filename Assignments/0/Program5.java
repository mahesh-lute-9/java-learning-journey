

class Demo{

	public void static main(String[] args){
	
		System.out.println("Hello from Java!");
	}
}


/* Output :
 *
 * 	Program5.java:5: error: <identifier> expected
        	public void static main(String[] args){
                   	^
	Program5.java:5: error: '(' expected
        	public void static main(String[] args){
                    	^
	Program5.java:5: error: invalid method declaration; return type required
        	public void static main(String[] args){
                     	      ^
	3 errors
 *
 * Explanation :
 *
 * 	This code will not compile because public void static main is an invalid modifier order.
 * 	In Java, static must come before the return type, so it should be public static void main.
 *
 * 	Here, the compiler get's confused because void is treated as the return type before static,
 * 	which breaks the method declaration syntax.
 *
