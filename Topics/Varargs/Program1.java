
//Sum of Numbers


class Demo{

	static int sum(int... nums){
	
		int total = 0;

		for(int n : nums){
		
			total += n;
		}

		return total;
	}

	public static void main(String[] args) {

     		System.out.println(sum());

        	System.out.println(sum(10));

        	System.out.println(sum(10,20));

        	System.out.println(sum(10,20,30,40));
    	}
}


/* Output:
 * 	0
	10
	30
	100
 * */
