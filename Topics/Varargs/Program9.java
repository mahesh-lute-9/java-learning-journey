
//Real World Exaple - printf()


/*	You already use varargs without realizing it.

	System.out.printf(
        	"%s scored %d marks",
        	"Mahesh",
        	95
	);

	Internally:

	public PrintStream printf(
        	String format,
        	Object... args
	)

	Here:

	Object... args

	can accept:

		printf("%d",10);

		printf("%s","Mahesh");

		printf("%s %d","Mahesh",95);

		printf("%s %d %.2f",
       			"Mahesh",
       			95,
       			89.5
		);
 *
 * */
