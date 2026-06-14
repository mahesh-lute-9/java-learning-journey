

class Demo{

	public static void main(String[] args){
	
		double x = 25.5;

		switch(x){
			
			case 5.5:
				System.out.println("5.5");
				break;
			
			case 15.5:
				System.out.println("15.5");
				break;

			case 25.5:
				System.out.println("25.5");
				break;

			case 35.5:
				System.out.println("35.5");
				break;

			default:
				System.out.println("Not match found");
		}
	}
}

/* Output:
 * 	Program8.java:9: error: selector type double is not allowed
                switch(x){
                      ^
1 errors

 *
 * Explanation:
 * 	In switch, we can only use int, char and Strings only. Here we've used double therefore it has given an error.
 * */
