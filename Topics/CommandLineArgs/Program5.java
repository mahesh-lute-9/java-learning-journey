

//Sum of args[] - by TypeCasting explicitly


class Demo{

	public static void main(String[] args){
	
		int sum = 0;

		for(int i = 0; i < args.length; i++){
			
			sum = sum + Integer.parseInt(args[i]);
		}

		System.out.println(sum);
	}
}

/* IMPORTANT:
 * 	1. While running if you do not give any argument via commandline then then it prints sum as 0
 * 	2. If you are providing a Strings(words) to it then it throws an error as: java Demo Mahesh Balaji Lute
			Exception in thread "main" java.lang.NumberFormatException: For input string: "Mahesh"
        			at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
        			at java.lang.Integer.parseInt(Integer.java:580)
        			at java.lang.Integer.parseInt(Integer.java:615)
        			at Demo.main(Program5.java:14)
	3. And if you provide intger values like this via command line:  java Demo 1 2 3
		then it gives sum as 6

	So yahh these are the points to remember here
 * */
