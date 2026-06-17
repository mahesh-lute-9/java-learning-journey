
import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
 *
 * Explanation:
 * 	In this program, we are directly creating a BufferedReader object by passing an InputStreamReader inside it. This is more
 * 	optimized and commanly used way of writing the same code, instead of creating two separate objects.
 *
 * 	Here we're doing two things in single line:
 * 		
 * 		1. InputStreamReader converts byte input(keyboard) into charaacter stream.
 *
 * 		2. BufferedReader wraps it to provide efficient reading and readLine() method.
 *
 * */
