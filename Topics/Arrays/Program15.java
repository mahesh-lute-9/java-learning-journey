

class Demo{

	public static void main(String[] args){
	
		int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};

		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				
				System.out.print(arr[i][j] + " ");
			}	
			
			System.out.println();
		}
	}
}


/* Output:
 * 	Program15.java:7: error: ';' expected
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                           ^
Program15.java:7: error: not a statement
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                             ^
Program15.java:7: error: ';' expected
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                              ^
Program15.java:7: error: illegal start of expression
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                                   ^
Program15.java:7: error: not a statement
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                                     ^
Program15.java:7: error: ';' expected
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                                      ^
Program15.java:7: error: illegal start of expression
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                                           ^
Program15.java:7: error: not a statement
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                                             ^
Program15.java:7: error: ';' expected
                int arr[][] = new int[3][3]{{1,2,3},{4,5,6},{7,8,9}};
                                                              ^
9 errors
 *
 *
 *  Here you tries to use both size-based initialization(new int[3][3])) and value based initialization({{1,2,3},...}) at the same time. In java,
 *  this is not allowed. When you specify the size of an array using int[3][3], Java expecta you to fill the values later, not immediately using
 *  curly braces. On the other hand, when you provide the values using {}. Java automatically determines the size, so writng the size again becomes
 *  unneccessary and incorrect, that's why it throws an error
 * */
