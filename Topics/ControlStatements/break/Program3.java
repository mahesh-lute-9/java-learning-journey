
//User login attempts

class Demo{

	public static void main(String[] args){
		
		int correctPin = 1234;

		int[] attempts = {1111,2222,1234,9999};

		for(int pin:attempts){
			
			if(pin == correctPin){
				
				System.out.println("Login Successful");
				break;
			}

			System.out.println("Wrong PIN");
		}
	}	
}

/* Output:
 * 	Wrong PIN
	Wrong PIN
	Login Successful
 *
 *
 * */


