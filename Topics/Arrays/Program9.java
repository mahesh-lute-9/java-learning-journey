//Entering and accessing array elements by using BufferedReader(user)


import java.io.*;

class Demo{
	
	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter array size: ");
		int size = Integer.parseInt(br.readLine());

		int arr[] = new int[size];

		System.out.println("Enter array elements: ");
		for(int i = 0; i < size; i++){
			
			arr[i] = Integer.parseInt(br.readLine());
		}

		System.out.println("Array Elements are: ");
		for(int i = 0; i < size; i++){
			
			System.out.println(arr[i]);
		}

	}
}


/* Output:
 * 	Enter array size:
	4
	Enter array elements:
	10
	20
	30
	40
	Array Elements are:
	10
	20
	30
	40
 *
 *
 * */
