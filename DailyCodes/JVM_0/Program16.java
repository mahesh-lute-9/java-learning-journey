

class Demo{

	static void main(String[] args){

		System.out.println("In Demo class");
	}
}


/* Output :
 *      On compile time no error, but at run time it throws wrroe like this,
 *
 * 	Error: Main method not found in class Demo, please define the main method as:
   public static void main(String[] args)
or a JavaFX application class must extend javafx.application.Application
 *
 *
 * Explanation :
 *
 * 	The keyword public is mandatory because the JVM must be able to access this method from outside the class. If the method is not declared
 * 	as public, it has package-level(default) access. In such a case, the JVM cannot treat it as the starting point of the program.
 *
 * 	Important Distinction
 *
 * 	Compilation Phase: The compiler only checks syntax and structure. Since the method declaration is syntactically valid, the program compiles
 * 	without error.
 *
 * 	Execution Phase: The JVM checks for the exact required signature. Since the method is not declared publid, it does not qualify as the entry
 * 	point, and execution fails at runtime.
 * */
