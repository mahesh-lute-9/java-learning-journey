
import java.util.*;

class Demo{

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter array Size: ");
		int size = sc.nextInt();

		int[] arr = new int[size];

		for(int i = 0; i < 5; i++){
			
			System.out.println(arr[i]);
		}	
	}
}


/* Output:
 * 	Enter array Size:
3
0
0
0
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 3
        at Demo.main(Program6.java:17)
 *
 *
 * Explanation:
 * 	Inside the method, the program takes the array size as input from the useer and creates an array of that size. However, the for loop
 * 	run from index 0 to 4 (fixed 5 interations), regardless of the entered size. Whenthe user enters size 3, the array has valid indices
 * 	only from 0 to 2, so accessing index 3 causes an ArrayIndexOutOfBoundException. The first three elements prints as 0 due to default 
 * 	initialization.This error occurs because the loop condition does not match the actual array size.
 * */
