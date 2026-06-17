
//2. Read an Integer


//Since readLine() returns String

import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int age = Integer.parseInt(br.readLine());

		System.out.println(age);
	}
}
