

class Demo{

	public static void main(String[] args){
	
		system.out.println("Welcome to the Arena");
	}
}

/* Output :
 *	Program13.java:7: error: package system does not exist
                system.out.println("Welcome to the Arena");
                      ^
	1 error
 *
 *
 * Explanation :
 *
 * 	The code will give compile time error.
 *
 * 	Java is case sensitive, and system is not the same as System. Since no
 * 	class named system exists, so the compiler throws a "cannot find/ does not exists" error.
 *
