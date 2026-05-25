


class Demo{
	
	public static void main(){
	
		System.out.println("In class Demo");
	}
}



/* Output :
 * 	In JDK 25 - No error
 *
 * 	In JDK 8 - Error
 *
 *
 *	Error: Main method not found in class Demo, please define the main method as:
   		public static void main(String[] args)
	or a JavaFX application class must extend javafx.application.Application
 *
 *
 * Explanation :
 * 	Chanage in Modern JAVA(JDK 25)
 *
 * 	Recent Java versions have introduced greater flexibility in defining the main method. The String[] args parameter
 * 	is no longer strictly mandatory for launching a program. If a parameterless public static void main() method is
 * 	present, the JVM can treat it as the entry point.
 *
 * 	This change simplifies small programs and beginner-level examples, reducing unnecessary boilerplate code while maintaining
 * 	backward compatibility.
 *
 * 	Earlier Java Versions required an exact main(String[] args) signature for program execution. Modern Java versions relax this constraint
 * 	and allow a parameterless main() method to act as the entry point.
 * */
