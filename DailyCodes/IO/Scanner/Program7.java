
//Another way of writing Scanner and creating it's object


class Demo{

	public static void main(String[] args){
	
		java.util.Scanner sc = new java.util.Scanner(System.in);

		System.out.println("Enter your name: ");

		String name = sc.next();

		System.out.println("Name: " + name);
	}
}

/* We're writing proper path of class, so the compiler does not need an import statement. Because you are 
 * giving the complete path.
 *
 * */
