
//Command Line arguments are the data provided through the command line, let us see how it works! Here we'll provide the data while run time


class Demo{

	public static void main(String[] args){
	
		int arr[] = {10,20,30};

		System.out.println(arr.length);

		System.out.println(args.length);
	}
}


/*Output:
 *	3
	2
 *
 * Explanation:
 * 	Here we're providing input through command line as java Demo Mahesh Lute so it works like args is an array of String. And internally
 * 	it stores the Strings that we're providing as args[0] = "Mahesh"; and args[1] = "Lute". And there are 2 String in args array so it prints
 * 	2 when the System.out.println(args.length); statement executes.
 * */
