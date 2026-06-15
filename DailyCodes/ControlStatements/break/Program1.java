

class Demo{

	public static void main(String[] args){
	
		for(int i = 1; i <= 10; i++){
		
			if(i == 5){
			break;
			}

			System.out.println(i + " ");
		}
	}
}


/* Output:
 * 	1
 * 	2
 * 	3
 * 	4
 *
 *
 * When java encounters break, it stops the entire loop immediately. Here when i becomes 5, the loop terminates.
 *
 * */
