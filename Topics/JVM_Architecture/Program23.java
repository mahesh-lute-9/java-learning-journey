

//JVM under the hood: Lect - 2




class Demo{

	public static void main(String[] args){
	
		int x = 10;
		int y = 20;

		System.out.println("In main method");
		System.out.println(x+y);
	}
}


/* Output :
 * 	In main method
	30
 *
 *
 * for this program we're having the java version is 11 and javac version is 1.8. So it is okay Java gives backward 
 * compatibility for the bytecode, JVM (java) can read it's before version's bycode. But reverse of it is not allowed.
 * Then it gives version error.
 * */
