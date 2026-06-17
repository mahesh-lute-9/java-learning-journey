
//4.Read an Array


import java.io.*;

class Demo{

	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] s = br.readLine().split(" ");

		int[] arr = new int[n];

		for(int i = 0; i < n; i++){
		
			arr[i] = Integer.parseInt(s[i]);
		}

		for(int x : arr)

			System.out.println(x + " ");
	}
}
