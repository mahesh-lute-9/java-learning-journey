

class Class{

	public static void main(String Mahesh){
		
		System.out.println("Hello from Mahesh..");
	}
}


/* Output :
 * 	Code will compile but not run fully. Error at runtime
 *
 * 	Error: Main method not found in class Class, please define the main method as:
   		public static void main(String[] args)
	or a JavaFX application class must extend javafx.application.Application
 *
 *
 * Explanation :
 *
 * 	This code will not run as Java Application.
 *
 * 	The main method must take a String[] (array of Strings), but here it takes a single String.
 * 	Since the JVM cannot find the required public static void main(String[] args) method, it
 * 	will throw a runtime error saying the main method is not found. The original main method has
 * 	required parameter as String[] so it can't find it.
