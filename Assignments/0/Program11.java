

class String{

	public static void main(String[] args){
	
		System.out.println("String printed..");
	}
}


/* Output :
 * 	Error: Main method not found in class String, please define the main method as:
   		public static void main(String[] args)
	or a JavaFX application class must extend javafx.application.Application
 *
 *
 * Explanation :
 *
 * 	(Here there will be Runtime error)
 *
 * 	It compiles, because String[] now refers to our own String class, so syntactically everything
 * 	looks fine to the compiler. 
 *
 * 	But at runtim, the JVM looks specialllt for main(java.lang.String[] args). Since our parameter
 * 	type is our package.String[]., the required signature is not found, so it throws a runtime error
 * 	saying the main method is missing.
