
class Demo{

	public static void main(String[] args){
	
		String cmpName = "Microsoft";

		switch(cmpName){
			
			case "Apple" ->
				System.out.println("Steve Jobs");
			
			case "Microsoft" ->
				System.out.println("Bill Gates");

			case "Tesla" ->
				System.out.println("Elon Musk");

			case "Amazon" ->
				System.out.println("Jeff Bezos");

			default ->
				System.out.println("Trump Tatya");
		}
	}
}

/* Output:
 * 	When Compiled in JDK 8 --> ERROR
 *
 * 	When Compiled in JDK 17 --> Bill Gates

 *
 * Explanation:
 * 	This feature is added in Java JDK 12. With this updatein JDK 12, we can write the switch case without break.
 * */
