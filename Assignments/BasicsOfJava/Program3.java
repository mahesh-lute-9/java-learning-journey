

class Demo{

	public void main(String[] args){
	
		System.out.println("Program Executed..");
	}
}


/* Output : Here the error will come at runtime and not at compile time.
 *	
 *	Error: Main method is not static in class Demo, please define the main method as:
   		public static void main(String[] args)
 *	
 *
 * Explantion :
 * 	
 * 	The code compiles successfully because the method syntax is valid.
 *
 * 	But at runtime, the JVM looks specially for public static void main(String[] args).
 * 	Since main is not static, the JVM cannot call it without creating an object, so it
 * 	throws the error at runtime.
