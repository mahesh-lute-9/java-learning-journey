

class new{
	
	public static void main(String[] args){
		
		System.out.println("Main Printed..");
	}
}


/* Output :
 *	Program12.java:3: error: <identifier> expected
class new{
     ^
Program12.java:5: error: illegal start of expression
        public static void main(String[] args){
        ^
Program12.java:5: error: illegal start of expression
        public static void main(String[] args){
               ^
Program12.java:5: error: ';' expected
        public static void main(String[] args){
                     ^
Program12.java:5: error: '.class' expected
        public static void main(String[] args){
                                         ^
Program12.java:5: error: ';' expected
        public static void main(String[] args){
                                             ^
Program12.java:9: error: reached end of file while parsing
}
 ^
7 errors
 * 	
 *
 *
 * Explanation :
 * 	This code will give a compile time error.
 *
 * 	new is a reserved keyword in Java(used for creating objects), so it cannot be used as a class name.
 * 	The compile immidiately rejects it because keywords cannot be used as indentifiers in Java.
 */
