//Skip Multiple of 3

class Demo{

	public static void main(String[] args){
	
		for(int i =1; i <= 15; i++){
		
			if(i % 3 == 0)
				continue;

			System.out.print(i + " ");
		}

		System.out.println();
	}
}


/* Output:
 * 	1 2 4 5 7 8 10 11 13 14
 *
 * 	Here we are skipping the multiples of 3 in range of 1 to 15
 *
 * */
