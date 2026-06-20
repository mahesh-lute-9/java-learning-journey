

class Demo{
	
	public static void main(String[] args){
	
		Integer x = 128;

		Integer y = 128;

		if(x == y){
		
			System.out.println("Equals");
		}else{
			System.out.println("Not equals");
		}

		System.out.println(System.identityHashCode(x));

		System.out.println(System.identityHashCode(y));
	}
}


/* Output:
 *	Not equals
	1252169911
	2101973421
 *
 * */
