

import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		InputStreamReader isr = new InputStreamReader(System.in);

		BufferedReader br = new BufferedReader(isr);

		System.out.println("Enter your name: ");
		String name = br.readLine();

		System.out.println("Name: " + name);	
	}
}


/* Output:
 * 	Enter your name:
 * 	Mahesh
 * 	Name: Mahesh
 *
 * Explanation: 
 * 	In this program, we are using BufferedReader along with InputStreamReader to take input from the user. The InputStreamReader converts
 * 	byte input(from keyboard) into character stream and BufferedReader is used on top of it to efficiently read data, especially full lines.
 *
 * 	When we call the readLine() method, it reads the entire line of input untill the user presses Enter. Unlike the read() method(which reads
 * 	only single character), readLine() directly returns a String containing the complete input.
 *
 * 	This shows that BufferedReader with ReadLine() is useful when we want to read complete sentenses or full input, making it more suitable
 * 	than read() for string input.
 * 	
 * */
