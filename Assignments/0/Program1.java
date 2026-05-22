
Class Demo{
	
	public static void main(String[] args){
	
		System.out.println("Hello World!");
	}
}


/* Output :
 *
 * 	Program1.java:2: error: class, interface, or enum expected
		Class Demo{
			^
	Program1.java:4: error: class, interface, or enum expected
        	public static void main(String[] args){
                      	^
	Program1.java:7: error: class, interface, or enum expected
        	}
        	^
	3 errors
 * 
 *
 * Explanation :
 * 	Java is a case-sensitive. class is a keyword. Class is not, So Class Demo{...} will not
 * 	compile- the compiler will throw an error like "class, interface, or enum expected".
