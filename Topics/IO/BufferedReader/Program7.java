

import java.io.*;

class Demo{

	public static void main(String[] args){
		
		InputStreamReader isr = new InputStreamReader(System.in);

		System.out.println("Enter your name: ");

		isr.read();
	}
}



/* Output:
 * 	Program7.java:13: error: unreported exception IOException; must be caught or declared to be thrown
                isr.read();
                        ^
1 error

	
 * Explanation:
 * 	In this program, the error occurs because the read() method od the InputStreamReader class can throw a checked exception called IOException.
 * 	In java checked exceptions must be either handled using try-catch or declared using throws. Since we are directly calling isr.read() without
 * 	handling or declaring the exception, the compiler gives the error: "unreported exception IOException; must be caught or declared to be thrown"
 *
 *
 * 	The reason behind this is that input/output operations are not always safe - there can be issues like input failure or stream problems - so Java
 * 	forces the programmer to handle such cases explicitly.
 * */
