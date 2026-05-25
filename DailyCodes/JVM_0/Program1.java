

System.out.println("Hello World!");




/* Output :
 *
 * 	Program1.java:3: error: class, interface, or enum expected
		System.out.println("Hello World!");
			^
	1 error
 *
 *
 *
 * Explanation :
 * 	
 *	This code is ran using JDK 8
 *
 *	When you write : System.out.println("Hello World!");
 *	and try to compile it directly inside a .java file, the compiler throws:
 *
 *	Program1.java:3: error: class, interface, or enum expected
		System.out.println("Hello World!");
			^
	1 error

	Now let's decode what is actually happening
	Java is a strictly structured language.Every executable statement must exist inside a method, and
	every method must exist inside a class. The compiler does not allow free-floating statements in a source
	file. Unike scripting languages, Java enforces a rigid program structure.

	The statement System.out.println("Hello World!"); is a valid statement, but it is not inside any class or method.
 * */
