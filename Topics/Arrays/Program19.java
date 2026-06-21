

class Demo{

	public static void main(String[] args){
	
		int arr[][] = {{10,20,30},{40},{70,80,90}};

		System.out.println(arr.length);	//3

		System.out.println(arr[0].length);	//3

		System.out.println(arr[1].length);	//1
	}
}

/* Output:
 * 	The given program demonstrates the concept of Jagged Array, where each row can have diff. number of elements. In Java, a two-dimensional
 * 	array is actually an array of arrays. This means each row is treated as a separate array object, and therefore each row can have it's own
 * 	independent length. The main array arr stores references to these row arrays. Because of this design,it is possible for different rows to have
 * 	diff. sizes, which is exactly what is shown in this example.
 *
 * 	The statement System.out.println(arr.length); prints the total number of rows in the array. Since there are three row arrays, the output is 3.
 * 	The expression arr[0].length accesses the second row and returns it's size, which is 1, since it contains only a single element.
 *
 * */
