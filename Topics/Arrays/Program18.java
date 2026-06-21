//Jagged Array- A type of an Array

/* A Jagged Array in Java is a special type of multidimensional array in which each row can have a different number of elements. Unlike a regular
 * two-dimensional array, where all rows must have the same number of columns. A Jagged Array allows uneven or variable row size. This makes it more
 * flexible when dealing with data that is not uniform in structure.
 *
 * Jagged arrays are particularly useful in situations where data is irregular or does not naturally fit into a fixed rectangular structure.
 *
 */

class Demo{

	public static void main(String[] args){
	
		int arr[][] = {{10,20,30},{40,50,60},{70,80,90}};

		System.out.println(arr[1][1]);	//50

		System.out.println(arr[2][0]);	//70

		System.out.println(arr[0][0]);	//10
	}
}


/* Output:
 * 	50
	70
	10
 *
 * */
