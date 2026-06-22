
//Command Line arguments are the data provided through the command line, let us see how it works!


class Demo{

	public static void main(String[] args){
	
		int arr[] = {10,20,30};

		System.out.println(arr.length);

		System.out.println(args.length);
	}
}


/*Output:
 *	3
	0
 *
 * Explanation:
 * 	Here we're not giving any instructions while runtime through commandline just printed the length of args as ergs.length so it returns it as 0
 * */
