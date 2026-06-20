

class Demo{

	public static void main(String[] args){
	
		int x = 128;

		int y = 128;

		if(x == y){
		
			System.out.println("Equals");
		}else{
			System.out.println("Not Equals");
		}
	}
}


/* Output:
 * 	Equals
 *
 * 	The IntegerCache is the inner class in the Integer wrapper class
 *
 * 	The range of the cache is -129 ro 127
 *
 * 	Here's the proof of it from Java doc	
 *
 * */
