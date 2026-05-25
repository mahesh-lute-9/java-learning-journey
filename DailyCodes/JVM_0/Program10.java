

//User-defined class vs Pre-defined class


class Demo{

	public static void main(string[] args){
	
		System.out.println("In Demo class");
	}
}


/* Output :
 *
 * 	Program10.java:8: error: cannot find symbol
        	public static void main(string[] args){
                                ^
  	symbol:   class string
  	location: class Demo
	
	1 error
 *
 *
 * Explanation:
 * 	This error occurs because Java is a case-sensitive programming language. In Java, String and strig are treated as completely differently.
 * 	when the compiler encounters string[] args
 *
 * 	it attempts to find a class named string, Since no such class exists in the current program or in the standard libraries, the compiler
 * 	reports the error "cannot find symbol". The termsymbol here refers to an identifier such as a classs name, variable name, or method name that
 * 	the compiler cannot resolve.
 *
 * 	Since no predefined class named string exists in the Java library, and no user-defined class with that name is declared in the program, the
 * 	compiler cannot resolve it. Therefore, it reports the error "cannot find symbol", meaning it cannot find a class definiton for string.
 * */
