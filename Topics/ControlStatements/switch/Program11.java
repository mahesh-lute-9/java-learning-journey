
class Demo{

	public static void main(String[] args){
	
		String cmpName = "Microsoft";

		switch(cmpName){
			
			case "Apple":
				System.out.println("Steve Jobs");
				break;
			
			case "Microsoft":
				System.out.println("Bill Gates");
				break;

			case "Tesla":
				System.out.println("Elon Musk");
				break;

			case "Amazon":
				System.out.println("Jeff Bezos");
				break;

			default:
				System.out.println("Trump Tatya");
		}
	}
}

/* Output:
 * 	Bill Gates

 *
 * Explanation:
 * 	Strings are allowed in the switch case.
 * */
