
import java.util.*;

class Demo{

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter array Size: ");
		int size = sc.nextInt();

		int[] arr = new int[size];

		System.out.println("The array elemets are: ");
		for(int i = 0; i < 5; i++){
			
			System.out.println(arr[i]);
		}	
	}
}


/* Output:
 * 	Enter array Size:
 * 	3
 * 	The array elements are:
 * 	0
 * 	0
 * 	0
 *
 * */
