
//Zero Arguments
//Varargs can accept nothing


class Demo{

	static void test(int... nums){
	
		System.out.println(nums.length);
	}

	public static void main(String[] args){
	
		test();
	}
}

/*Output:
 * 	0
 *
 * */
