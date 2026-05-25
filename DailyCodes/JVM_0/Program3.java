

class Demo{

	{
	
		System.out.println("Hello World!");
	}
}



/* Output :
 * 	Error: Main method not found in class Demo, please define the main method as:
   		public static void main(String[] args)
	or a JavaFX application class must extend javafx.application.Application
 *
 *
 * Explanation :
 * 	On compiler there is no error, but at run-time it throws an error.
 *
 * 	Because the print statement is placed inside an instance initiazer block. In java, a class
 * 	body can contain initilizer blocks, and these blocks are valid members of a class. An instance
 * 	initializer block is enclosed within {} and is executed every time an object of the class is 
 * 	created. Since the syntax is correct and the structure follows Java program rules, the compiler
 * 	accepts the program without an error.
 * */
