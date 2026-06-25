
//Overloading with Varargs


class Demo{

	static void show(int a){
	
		System.out.println("Exact Match");
	}

	static void show(int... a){
	
		System.out.println("Varargs");
	}

	public static void main(String[] args){
	
		show(10);
	}
}


/* Output:
 * 	Exact Match
 *
 * */
