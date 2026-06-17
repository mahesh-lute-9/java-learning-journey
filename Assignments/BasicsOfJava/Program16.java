
class Demo{

	public static void main(String[] args){
		
		System.out.println("Hello World1");
	}
	
	public static void main(String[] args){
		
		System.out.println("Hello World2");
	}
}


/* Output :
 *  	Program16.java:9: error: method main(String[]) is already defined in class Demo
       	 	public static void main(String[] args){
                           ^
	1 error
 *
 *
 * Explanation :
 * 	This code will give compile time error
 *
 * 	You cannot declare two methods with the exact two same signature in the same class. Both
 * 	main methods have identical parameter types (String[]), so the compiler says the method is
 * 	already defined in the class Demo.
