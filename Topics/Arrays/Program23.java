

class Demo{

	public static void main(String[] args){
	
		int arr[][] = new int[3][];

		arr[0] = new int[3];

		arr[1] = new int[1];

		arr[2] = new int[2];

		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				
				System.out.print(arr[i][j] + " ");
			}

			System.out.println();
		}
	}
}


/* OUTPUT:
 * 	0 0 0
	0
	0 0
 *
 * The given program demonstrates the creation of a jagged array where the sizes of the rows are defined first, but the elements are not
 * explicitly initialized. The array is declared as int arr[][] = new int[30][];, which creates an outer array capable of holding three rows.
 * At this point, the inner arrays are not yet assigned.
 *
 * In Java, when an array of integer is created using new, all elements are automatically initialized to their default values, which is 0.
 * */
