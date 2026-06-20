// Accessing Array elements.

class Demo{

	public static void main(String[] args){
	
		int arr1[] = new int[5];

		arr1[0] = 10;
		arr1[1] = 20;
		arr1[2] = 30;	

		System.out.println(arr1[0]);

		System.out.println(arr1[1]);

		System.out.println(arr1[2]);

		System.out.println(arr1[3]);

		System.out.println(arr1[4]);
	}
}

/* Output:
 * 	10
 * 	20
 * 	30
 * 	0
 * 	0
 *
 * Explanation:
 * 	Inside the main method, an integer array arr1 is created with a size of 5 using new int[5]. Values are assigned only to the first
 * 	three indices(0,1, and 2), while the remaining indices are left uninitialized. In java, unassigned elements of an integer array
 * 	automatically take adefault value 0. When all elements are printed, the first show assigned values and the last two displays as 0's.
 * 	This demonstartes that partial initialization and default values in arrays.
 * */
