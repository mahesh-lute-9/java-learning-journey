

class Demo{
	
	public static void main(String[] args){
		
		for(int i = 1; i <= 50; i++){
		
			if(i % 4 == 0 && i % 5 == 0){
		
				System.out.println(i);  //numbers that are divisible by both 4 and 5 within the range of 1 to 50.
			}
		}
	}
}


/* Output:
 * 	20
	40
 *
 *
 * Here we are strictly checking the numbers that are divisible by 4 and 5 and printing them.
 *
 * */
