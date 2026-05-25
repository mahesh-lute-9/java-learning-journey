
// In java there is by default inheritance betn. any class to Object class.


class Demo extends Object{

	public static void main(String[] args){
		
		System.out.println("In Demo class");
	}
}


/* Output :
 * 	In Demo class
 *
 *
 * Explanation :
 * 	This program runs successfully and prints the output as expected. At first glance you may notice that the class Demo
 * 	explicitly extends Object. However, even if we do not write extends Object, the program would still behave in exactly
 * 	the same way.
 *
 * 	In Java, every class automatically extends the Object class. The Objects class is the root of tge entire class hierarchy in
 * 	Java and is defined in the java.lang package. If a class does not explicitly extend any other class, the Java compiler automatically
 * 	adds extends Object behind the scenes.
 *
 * 	So when we write:
 * 		 class Demo{
 *
 * 		}
 *
 * 	the compiler internally treats it as:
 *
 * 		class Demo extends Object{
 * 		
 * 		}
 * */
