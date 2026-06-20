

class Demo{
	
	public static void main(String[] args){
	
		int x = 225;

		Integer y = 225;

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
 	Equals
	1252169911
	2101973421
 * *
 *
 *	ExplanatioN:
 *		In this code, we have  x as primitive and y as Integer. While comparing these two variables Integer type y downgrades itself
 *		to the primitive int. Here is the proof
 *
 *		the method intValue() returns an int, which can seen in the source code of integer class
 * /
