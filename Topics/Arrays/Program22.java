

class Demo{

	public static void main(String[] args){
	
		int arr[][] = new int[3][];

		arr[0] = new int[]{10,20,30};

		arr[1] = new int[]{40,50,60};

		arr[2] = new int[]{70,80,90};

		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				
				System.out.print(arr[i][j] + " ");
			}

			System.out.println();
		}
	}
}


/* OUTPUT:
 * 	10 20 30
	40 50 60
	70 80 90
 *
 * The given progrma shows how a two-dimensional array can be created using dynamic row initialization and the traversed using nested loops
 * */
