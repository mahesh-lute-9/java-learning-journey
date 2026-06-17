
import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter soc name: ");
		String socName = br.readLine();

		System.out.println("Enter wing: ");
		char wing = (char)br.read();
		br.skip(1);
			
		System.out.println("Enter flatNo: ");
		int flatNo = Integer.parseInt(br.readLine());

		System.out.println("Details: ");

		System.out.println("Soc Name: " + socName);

		System.out.println("wing: " + wing);

		System.out.println("flatNo: " + flatNo);
	}
}



/* Output:
 * 	Enter soc name:
	Sun Universe
	Enter wing:
	A
	Enter flatNo:
	201
	Details:
	Soc Name: Sun Universe
	wing: A
	flatNo: 201


	Here we're using br.skip(1); this statement skips one character from the buffer, which is lstover newline(\n). So now the buffer
       	is clean before taking the next input.

	After that: br.radLine() correctly waits for user input. The flatNo is properly read and parsed.
 * */
