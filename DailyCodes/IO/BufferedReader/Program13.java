

import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter your name: ");
		String name = br.readLine();

		System.out.println("Enter your age: ");
		int age = Integer.parseInt(br.readLine());

		System.out.println("Enter your salary: ");
		double sal = Double.parseDouble(br.readLine());

		System.out.println("Details: ");

		System.out.println("Name: " + name);
	
		System.out.println("Age: " + age);
	
		System.out.println("Salary: " + sal);
	}
}


/* Here we've to always remember that all input comes as String and need to be parsed for numeric types.
 *
 * */
