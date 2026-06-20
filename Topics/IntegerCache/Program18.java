

class Demo{
	
	public static void main(String[] args){
	
		Integer x = 127;

		Integer y = 127;

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
 *	Equals
	1252169911
	1252169911
 *
 * */
