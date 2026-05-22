

class System{

	public static void main(String[] args){
	
		System.out.println("System faad denge..");
	}
}


/* Output :
 * 	Program10.java:7: error: cannot find symbol
                	System.out.println("System faad denge..");
                      		^
  		symbol:   variable out
  		location: class System
	1 error
 *
 *
 * Explanation :
 *
 * 	The code gives a compile time error.
 *
 * 	Here we've created our own class named System, which hides java's built-in System class.
 * 	So System.out.println(); now refers to our own class, and since our class has no out variable
 * 	the compiler throws an error like "cannot find symbol: variable out".
 */
