

class Demo{

	public static void main(String[] args){
	
		println("In Demo class");
	}
}



/* Output :
 * 	Program19.java:7: error: cannot find symbol
                println("In Demo class");
                ^
  symbol:   method println(String)
  location: class Demo
1 error
 *
 *
 * Explanation :
 * 	The error occurs because the method print() is being called without specifying the class or object that deines it.
 * 	In Java, println() is not a standalone method/function.
 * 	When the compiler encounters: println("In Demo class");
 *
 * 	it searches for a method named println() inside the current class Demo. Since no such method is defind in Demo, the compiler reports
 * 	"cannot find symbol"
 * */
