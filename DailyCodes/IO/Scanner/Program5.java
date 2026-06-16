
//Read Array Elements

import java.util.*;

class Demo{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] arr = new int[n];

		for(int i=0;i<n;i++){

   	 		arr[i]=sc.nextInt();
		}

		for(int x : arr){

    			System.out.print(x+" ");

		}

		sc.close();
	}
}
