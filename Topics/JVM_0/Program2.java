

class Demo{

	System.put.println("Hello World!");
}



/* Output :
 * 	Program2.java:5: error: <identifier> expected
        	System.put.println("Hello World!");
                          	^
	Program2.java:5: error: illegal start of type
        	System.put.println("Hello World!");
                           	^
	2 errors
 *
 *
 * Explanation :
 *
 * 	The Java compiler generatesthe errors "expected" and "illegal start type". This error
 * 	occurs because Java does not allow executable statements directly inside the body of a class.
 * 	In Java, the body of a class can contain only member declaratios such as instance variables,methods,
 *	constructors, initializer blocks, or nested classes and interfaces. A statement like System.out.println();
 *	is an executable statement, and executable statements are permitted only inside methods, constructors, or 
 *	initializer blocks.
 *
 *	This is a compile-time structural error, not a runtime error. The program fails during the syntax analysis phase
 *	because it violates Java's rule that all executables statements must be enclosed within a method, constructor, or
 *	initializer block(static block).
 *
 ** * */
