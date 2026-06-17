

import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
		
		InputStreamReader isr = new InputStreamReader(System.in);

		System.out.println("Enter your name: ");

		int data = isr.read();

		System.out.println(data);
	}
}



/* Output:
 * 	Enter your name:
 * 	Ashish
 * 	65
 *
 *
 * Explanation:
 * 	In this program, when we use the read() method of InputStreamReader class,it reads only one character at a time from the input stream
 * 	and returns it's ASCII(Unicode) integer value So, Here A --> 65
 * */
