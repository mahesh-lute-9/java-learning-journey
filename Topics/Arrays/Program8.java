
import java.util.*;

class Demo{

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter array Size: ");
		int size = sc.nextInt();

		int[] arr = new int[size];

		System.out.println("Enter array elements: ");
		for(int i = 0; i < size; i++){
		
			arr[i] = sc.nextInt();
		}

		System.out.println("The array elemets are: ");
		for(int i = 0; i < size; i++){
			
			System.out.println(arr[i]);
		}	
	}
}


/* Output:
 * 	Enter array Size:
	4
	Enter array elements:
	10
	20
	30
	40
	The array elemets are:
	10
	20
	30
	40
 *
 * */
