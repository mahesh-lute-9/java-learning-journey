
//Logging Utility(SDE Level Example

//This resembles how logging frameworks works


class Logger{

	static void log(String level, Object... messages){
	
		System.out.print("[" + level + "] ");

		for(Object obj : messages){
		
			System.out.print(obj + " ");
		}

		System.out.println();
	}

	public static void main(String[] args){
	
		log(
			"INFO",
			"User Logged In",
			"Mahesh",
			21
		);

		log(
			"ERROR",
			"Database Failed",
			500		
		);
	}
}


/* Output:
 * 	[INFO] User Logged In Mahesh 21
	[ERROR] Database Failed 500
 *
 *
 * 	This pattern is heavily used in:

		-Logging frameworks
		-Testing frameworks
		-Spring Framework APIs
		-Utility libraries
 * 
 *
 *	Interview Quick Questions
		Question				Answer
	Can varargs accept zero arguments?		Yes
	Can we pass an array to varargs?		Yes
	Can we have two varargs?			No
	Must varargs be last parameter?			Yes
	Internally varargs is converted to?		Array
	Which has lower priority in overloading?	Varargs
	Can varargs store objects?			Yes
	Can varargs use generics?			Yes (T... args)
 *
 * */
