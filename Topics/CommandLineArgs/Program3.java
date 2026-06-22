

//ArrayIndexOutOfBoundException in Command Line arguments


class Demo{

	public static void main(String[] args){
	
		System.out.println(args[0]);

		System.out.println(args[1]);

		System.out.println(args[2]);
	}
}

/* Output:
 * 	3
	2


 *
 * 	When there is no argument given but we are trying to print the data hence there is an exception.
 * */
