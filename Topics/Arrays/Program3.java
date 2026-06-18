// Accessing null Array. auto initialization in array int - 0

class Demo{

	public static void main(String[] args){
	
		int arr1[] = new int[5];

		System.out.println(arr1[0]);

		System.out.println(arr1[1]);

		System.out.println(arr1[2]);

		System.out.println(arr1[3]);

		System.out.println(arr1[4]);
	}
}

/* Output:
 * 	0
	0
	0
	0
	0
 *
 *
 * Explanation:
 * 	Inside the main method, an integer array arr1 is created with a fixed size of 5 using new int[5]. Since no values are explicitly
 * 	assigned. Java automatically initializes all the elements to the default values for integers, which is 0. Each element from index
 * 	0 to 4 is then printed using System.out.println(). As a output displays five zeros, showing how default initialization works in arrays.
 *
 * */
