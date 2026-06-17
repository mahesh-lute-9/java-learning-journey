

// Tricky: Modifying elements


class Demo{

	public static void main(String[] args) {
     
	     	int[] arr = {10, 20, 30};

       
	       	for (int x : arr) {
            
			x = x + 10;
        
		}

        
		for (int x : arr) {
            
			System.out.print(x + " ");
        
		}

		System.out.println();
	}
}


/* Output:
 * 	10
 * 	20
 * 	30
 *
 * Many beginners think this will change the array:
 *
 * Why?

	x is just a copy of each value.

	arr[0] → 10 → x
	arr[1] → 20 → x
	arr[2] → 30 → x

	Changing x does not change the actual array element.
 *
 *
 *
 *
 * ------------------------------------------------------------
 *
 *  When to use which loop?
		
 	Situation		-	Best Loop
	Just read all elements		for-each
	Need index			for
	Traverse backward		for
	Unknown number of iterations	while
	Run at least once		do-while
 * */
