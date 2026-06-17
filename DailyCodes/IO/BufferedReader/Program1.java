
//BufferedReader reads text and is generally faster than scanner.


//1. Read a String


import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = br.readLine();

		System.out.println(name);
	}
}
