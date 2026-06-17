

import java.io.*;

class Demo{

	public static void main(String[] args){
	
		InputStreamReader isr = new InputStreamReader(System.in);

		BufferedReader br = new BufferedReader(isr);

		System.out.println("Enter your name: ");
		String name = br.readLine();

		System.out.println(name);	
	}
}


/* Output:
 * 	Program10.java:14: error: unreported exception IOException; must be caught or declared to be thrown
                String name = br.readLine();
                                         ^
1 error


 *
 * Explanation:
 * 	Here readLine() method of BufferedReader class throws a checked IOException. So it must be caught or declared to be thrown.
 *
 * 	The reason is that while input, issues like streams interruption or input failure can occur, so Java forces us to handle
 * 	such situations explicitly.
 * */
