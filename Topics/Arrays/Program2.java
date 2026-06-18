
//Array Dimension missing

class Demo{

	public static void main(String[] args){

		int arr1[] = new int[];	
	}
}


/* Output:
 * 	Program2.java:6: error: array dimension missing
                int arr1[] = new int[];
                                      ^
1 error


 *
 * Explanation:
 * 	In Java, while creating an array, using the new keyword,you must provide either the array size(e.g: new int[5]) or initialize it with
 * 	values(e.g: new int[]{1,2,3}). Since neither is given here, the compiler throws an error stating that the array dimension missing. Hence
 * 	this code results in a compilation erro.
 */
