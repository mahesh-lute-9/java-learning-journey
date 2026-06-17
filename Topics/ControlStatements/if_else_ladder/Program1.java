

class Demo{

	public static void main(String[] args){

		int x = 8;

		int y = 9;

		int z = 17;

		if(x > y && x > z ){

			System.out.println(x + " is greater");
		
		}else if(y > x && y > z){
		
			System.out.println(y + " is greater");
		}else{
			
			System.out.println(z + " is greater");
		}
	}
}


/* Output:
 * 	17 is greater
 *
 * */
