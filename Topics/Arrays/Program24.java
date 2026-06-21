

class Demo{

	public static void main(String[] args){
	
		int arr[][] = new int[][3];

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
	Program24.java:7: error: ']' expected
                int arr[][] = new int[][3];
                                        ^
Program24.java:7: error: ';' expected
                int arr[][] = new int[][3];
                                         ^
2 errors

	
Explanation:
	The given Java program results in a compilation error because of an incorrect way of declaring a to-dimensional array. The statement
	int arr[][] = new int[][3]; is not valid syntax in java. When creating an array using the new keyword, Java requires that the size of
	the first dimension(number of rows) must always be specified. Only the subsequent dimensions can be left unspecified.


	In this case, the program attempts to leave the first dimension empty while specifying the second dimension as 3. This is not allowed
       	because Java needs to know how many row references to create in the outer array. Since the first dimension is missing, the compiler
	throws error such as "'}' expected" and "';' expected".
 * */
