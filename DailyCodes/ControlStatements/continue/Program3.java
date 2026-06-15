// Skip Negative Numbers

class Demo{

	public static void main(String[] args){
		
		int[] nums = {10,-5,20,-8,30};

		for(int num : nums){
		
			if(num < 0)
				continue;

			System.out.print(num + " ");
		}

		System.out.println();
	}
}


/* Output:
 * 	10 20 30
 *
 * Here in array there are negative numbers, to skip those ones we've used for each loop and condition as (num<0). If it is true then the
 * value gets skipped and iteration moves to next.
 *
 **/
