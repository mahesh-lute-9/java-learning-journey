

class Demo{

	static void public main(String[] args){
		
		System.out.println("In Demo class");
	}
}



/* Output :
 * 	Program18.java:5: error: <identifier> expected
        static void public main(String[] args){
                   ^
Program18.java:5: error: '(' expected
        static void public main(String[] args){
                    ^
Program18.java:5: error: invalid method declaration; return type required
        static void public main(String[] args){
                           ^
3 errors

 *
 *
 * Explanation:
 *
 * 	The error occur because the method declaration does not follow the correct syntax rule or java.
 *
 * 	In Java, a method declaration must follow this general structure:
 * 	access modifiers -> non-access modifiers -> return type -> method name -> parameters -> method body.
 *
 * 	In above program, static void public main(...)
 *
 * 	is given; Here void(which is the return type) appears before public(which is access modifier). This violates Java's method declaration grammer.
 * 	Once the compiler encounters void, it expects the next token to be the method name. Instead, it finds public, which causes confusion in parsing.
 *
 * 	Although Java allows flexibility in the order of modifiers such as public and static, the return type msut always appear immediatey before method
 * 	name. Modifiers cannot be placed after the return type.
 *
 * */
