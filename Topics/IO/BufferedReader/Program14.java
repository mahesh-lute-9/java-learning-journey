
import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter soc name: ");
		String socName = br.readLine();

		System.out.println("Enter wing: ");
		char wing = (char)br.read();

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
Exception in thread "main" java.lang.NumberFormatException: For input string: ""
        at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
        at java.lang.Integer.parseInt(Integer.java:592)
        at java.lang.Integer.parseInt(Integer.java:615)
        at Demo.main(Program14.java:17)
 *
 *
 *
 * Explanation:
 * 	In this program, the error occurs because we are mising read() and readLine(), hich handle input differently. The read() method which
 * 	read a single character from the input stream, but it does not consume the newline(\n) character when the user presses Enter
 *
 * 	So when the user enter: A
 *
 * 	Internally it becomes 'A' + '\n'
 *
 * 	Now what happens step-by -step:
 *
 * 	br.read() reads only 'A'
 *
 * 	the newline(\n) is still left in the buffer
 *
 * 	Next, when br.readLine() is called for flatNo, it immediately reads this leftover newline, So it gets an empty String('''')
 *
 * 	Integer.parseInt('''') --> cause NumberFormatException
 *
 * 	Roor cause: read() --> reads only oone character. Does not clear enter(\n) from buffer 
 *
 * 	readLine() --> reads until newline. So it reads leftover \n --> gives empty String
 * */
