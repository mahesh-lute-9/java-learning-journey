
//Q.Find the sum of array elements

class Demo {
    	
	public static void main(String[] args) {
       		
		int[] numbers = {5, 10, 15, 20};

        	int sum = 0;

        	for (int num : numbers) {
            		
			sum += num;
        	}

        	System.out.println("Sum = " + sum);
    	}
}



/* Output:
 * 	Sum = 50
 *
 * 	Useful when you need to process eery element but don't need indexes.
 *
 * */
