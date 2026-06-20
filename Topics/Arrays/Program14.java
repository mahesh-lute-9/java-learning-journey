

class Demo{

	public static void main(String[] args){
	
		int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};

		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				
				System.out.print(arr[i][j] + " ");
			}	
			
			System.out.println();
		}
	}
}


/* Output:
 * 	1 2 3
	4 5 6
	7 8 9
 *
 *
 * Both the statements of creating array are valid and produce the same result. The difference is that when you provide values directly using{},Java
 * automatically understands the size and structure of the array, so writing new int[][] becomes optional.
 * */
