

class Demo{
	
	public static void main(String[] args){
	
		int x = 10;	//stack frame

		Integer y = 20;		//Heap-IntegerCache

		int arr1[] = {10,20,30};

		//After compile: int arr1[] = new int[]{10,20,30};

		Integer arr2[] = {10,20,30,40}; 	//Heap-IntegerCache	

	}
}

/*
 *
 *  No output
 *
 * Here we can say primitive like int stores values directly in the stack or inside arrays, while wrapper classes like Integer use objects that
 * commonly used values are reused, which is why multiple variables can point to the same object when the vakue within the cache range is there
 *
 */
