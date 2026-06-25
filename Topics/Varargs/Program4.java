
//Passing Array to Varargs


class Demo{

	static void print(int... nums){
	
		for(int n : nums){
		
			System.out.println(n);
		}
	}

	public static void main(String[] args){
	
		int arr[] = {10,20,30,40};

		print(arr);
	}
}


/* Output:
 * 	10
	20
	30
	40	
 *
 * */
