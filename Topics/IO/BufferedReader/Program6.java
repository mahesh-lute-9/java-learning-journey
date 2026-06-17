

/* read() method (InputStreamReader)
 *
 * The read() method of the InputStreamReader class is used to read a single character from the input stream. However, it does not return
 * a character directly. Instead, it returns an integer value representing the ASCII/Unicode of that character. Another important point is that
 * the read() method can throw an IOException, so it must be either handled using try-catch block or declared using throws IOException.
 *
 * Also, read() reads only one character at a time, now a full word or sentense. If we want to read  multiple characters or a full line, we need
 * to call it multiple times of use classes like BufferedReader.
 * */

import java.io.*;

class Demo{
	
	public static void main(String[] args){
		
		InputStreamReader isr = new InputStreamReader(System.in);

		System.out.println("Enter your name: ");

		char data = isr.read();
	}
}



/* OutPut:
 * 	Program6.java:23: error: incompatible types: possible lossy conversion from int to char
                char data = isr.read();
                                    ^
1 error
 *
 *
 * Explanation:
 * 	In this program, the error occurs because the method read() of the InputStreamReader class returns a nt value, not a char
 * 	The read() method returns the ASCII(Unicode) value of the character, which is an integer.
 *
 * */
