

class Demo{

	public static void main(String[] args){

		boolean x = false;

		boolean y = true;

		System.out.println("Start Code");

		if(x = y){
		
			System.out.println("In if");
		}else{
			
			System.out.println("In else");
		}

		System.out.println("End Code");
	}
	
}


/* Output:
 * 	Start Code
	In if
	End Code
 *
 *
 * Explanation:
 * 	Condition x = y means the assignment operation is being performed and the value of y is true which means the ultimate
 * 	value of the condition is true, hence if block is executed.
 * */
