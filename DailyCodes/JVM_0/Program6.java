

class Demo{

	public static void main(String[] args){
	
		System.out.println("Welcome to Java");
	}
}



/* Output :
 * 	Welcome to Java
 *
 *
 *
 * Explanation :
 * 	Let's walk through the flow in simple terms, step by step.
 *
 * 	First, you write the Java program in al file like Program1.java. This is called High-level Language(HLL)
 * 	code because humans can read and understand it. It contains your classDemo and the main() method.
 *
 * 	Now comes compilation.
 *
 * 	When you run: javac Program1.java
 *
 * 	the javac compiler translates your high-level Java code into bytecode. This bytecode is stored in a new file
 * 	i.e: Demo.class     (Your class_name.class)
 *
 *
 * 	Bytecode is not machine code. It is an intermidiate form of code that is understood by the Java Virtual Machine(JVM)
 * 	This is why Java id platform-independant. The same .class file can run on any system that has a JVM intalled.
 *
 * 	Next comes execution. When you run: java.Demo
 *
 * 	the Operating System starts the Java runtime Environment. Inside this environment, the class loader loads the Demo.classfile into memory.
 *
 * 	The flow now is: Operating System--> Class Loader --> JVM
 *
 * 	The Class Loader loads the bytecode into the JVM. The JVM then verifies the bytecode for security and correctness. After verification, the
 * 	JVM executes the bytecode.
 *
 * 	The JVM looks for: puublic static void main(String[] args)
 *
 * 	This is the starting point of the program. Execution begins from inside this method and ends here.
 *
 * 	When the JVM executes:System.out.println("Welcome to Java");
 * 	The text is printed on the screen as the final output.
 *
 * 	So the complete flow is: 
 * 			Write Java source code(HLL) --> Program1.java
 * 			Compile using javac --> generates Demo.class(bytecode)
 * 			Run using (giving bytecode to JVM for execution) --> java Demo
 *			OS starts JVM
 *			Class loader loads Demo.class
 *			Output appears on the screen
 *
 *	What is JVM?
 *	* The JVM(Java Virtual Machine) is the core component of Java. It is responsible for executing the bytecode
 *	  generated after compilation. The JVM does not understand Java source code directly. It understands bytecode(.class file)
 *	  When you run a java program, the JVM loads the bytecode, verifies it for safety, and converts it into
 *	  machine-level instructions that your operating system understands. This is what makes Java platform independant. Different
 *	  systems have different JVM implementations, but the all understand the same bytecode format.
 *
 *	
 *	What is JRE?
 *	* The JRE(Java Runtime Environment) provideseverything required to run a Java program. It includes the JVM and a a collection
 *	  of predefined class libraries such as String,System,Math,Collections,and many more. These predefined classes are necessary because
 *	  almost every Java Program depends on them. The JRE does not contain development tools like the compiler. It is meant only for
 *	  running Java Applications,not creating them.
 *
 *	
 *	What is JDK?
 *	* The JDK(Java Development Kit) is the complete package used for developing Java applications. It includes the JRE and additional
 *	  development tools such as the compiler(javac), debugger, documentation generator, and other utilities. When you wrie Java code and compile
 *	  it using javac, you are using the JDK. So, JDK is required for development, while JRE is sufficient only for execution.
 *
 *	  In simple relationship form:
 *	  	JDK = JRE + Development Tools
 *	  	JRE = JVM + Predefined Class Libraries
 *
 *
 *	 What is difference between JDK, JRE and JVM?
 *	 * JDK, JRE amd JVM are core components of the Java platform, each with different role.
 *
 *	   JVM(Java Virtual Machine) is the engine that executes Java bytecode and make Java platform independant. It
 *	   Converts compiled bytecode into machine-specific instructions and manages memory and security.
 *
 *	   JRE(Java Runtime Environment) provides the librabries and JVM required to run Java applications. It does not
 *	   inlcude development tools, so you cannot compile Java Programs using only JRE.
 *
 * 	   JDK(java Development Kit) is a compplete package for developers to create Java applications.
 *	   It inlcudes JRE, JVM and development tools like the Java compiler(javac) and debugger.
 *
 *	   In simple terms, JVM runs the program, JRE provides the runtime environment and JDK provides tools to develop and run Java programs
 *	   Therefore, JDK ) JRE ) JVM (JDK contains JRE, and JRE contains JVM)
 *
 *	   	
 *
 *
 * */
