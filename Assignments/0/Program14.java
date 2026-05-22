

class Demo{

	public static void main(string[] args){
	
		System.out.println("Hello World!");
	}
}


/* Output :
 *
 * 	Program14.java:5: error: cannot find symbol
        	public static void main(string[] args){
                                ^
  	symbol:   class string
  	location: class Demo
	1 error
 *
 *
 * Explanation :
 * 	The code will give a compile time error.
 *
 * 	Java is a case-sensitive, and string is not a same as String. Since no
 * 	type named string exists.The compiler throws a "cannot find symbol" error.
 * */
