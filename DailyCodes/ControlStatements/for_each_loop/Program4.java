

// Using break and continue


class Demo{

	public static void main(String[] args) {
     		
		int[] nums = {1, 2, 3, 4, 5};

        
		for (int n : nums) {
            		if (n % 2 == 0)
               		       	continue;

            		System.out.println(n);
        	}
    	}
}


/* Output:
 * 	1
	3
	5
 *
 * 	Here we've used continue keyword means we're skipped the elements which are divisible by 2
 *
 * */
