
// Skip one number
//When Java encounters continue, it skips the remaining statements of the current iteration and moves to the next iteration.

class Demo{

	public static void main(String[] args){
		
		for(int i = 1; i <= 5; i++){
			
			if(i == 3)
			continue;

			System.out.print(i + " ");
		}

		System.out.println();
	}	
} 


/* Output:
 * 	1 2 4 5
 *
 * */
