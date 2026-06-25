

//Print Multiple Names


class Demo{

	static void printNames(String... names){
	
		for(String name : names){
		
			System.out.println(name);
		}
	}


	public static void main(String[] args){
	
		printNames("Mahesh");

		printNames("Mahesh","Rahul","Amit");
	}
}


/* Output:
 * 	Mahesh
	Mahesh
	Rahul
	Amit
 *
 * */
