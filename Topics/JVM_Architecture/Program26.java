

class Demo{

	public static void main(String[] args){
	
		int x = 10;
		int y = 20;

		//Object creation

		Demo obj = new Demo();

		System.out.println("In main method");
		
		System.out.println(x+y);
	}
}


/* Output:
 * 	In main method
	30
 *
 *
 * 
 * 	Q. Why is the main method static?
 *	--> In Java, to execute any method, it must be accessed either throuugh an object of th class or it must be declared as a static
 *	    method. Normally,when a method is not static we nned to create an object of the class and then call to that method using the object.
 *	    However, when a method is declared as static, it belongs to the class itself rather than to an object, so it can be accessed directly
 *	    using the clas name without creating an object.
 *
 *	    The main() method in Java is declared as static because when the program starts, no objects of the class exist yet. The Java Virtual Machine
 *	    begins execution by loading the class and directly calling the main() method. If main() were not static the JVM would first need to 
 *	    create an object of the class in order to call it. But since the JVM would not know how to create that object before calling the first method.
 *
 * 	
 * */
