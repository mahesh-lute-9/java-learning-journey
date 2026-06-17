

class Demo{

	static{
		
		System.out.println("Hello World!");
	}
}


/* Output :
 *
 * 	Error: Main method not found in class Demo, please define the main method as:
   		public static void main(String[] args)
	or a JavaFX application class must extend javafx.application.Application
 *
 *
 * Explanation :
 * 	
 * 	On compile time there is no error, this error comes at run-time. because a static block is
 * 	valid member of a classs. Java allows static initializer blocks inside a class body. So the
 * 	structure is syntactically correct.
 *	
 *	In Java, all code must be written inside a type definition. A type in java can be a class
 *	an interface, or an enum. Java does not allow standalone statements or functions outside these
 *	structures. This is a fundamental structural rule of the Java Language.
 *
 *
 * */
