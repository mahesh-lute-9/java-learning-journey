
//Varargs with Wrapper Class

class Demo{

	static void show(Integer... nums){
	
		for(Integer n : nums){
		
			System.out.println(n);
		}
	}

	public static void main(String[] args){
	
		show(10,20,30);
	}
}


/*
 * Output:
 
 * 	10
	20
	30
*
* 
*	Behind the scenes
		show(
 			Integer.valueOf(10),
 			Integer.valueOf(20),
 			Integer.valueOf(30)
		);

	This is because of Autoboxing.
*
* */
