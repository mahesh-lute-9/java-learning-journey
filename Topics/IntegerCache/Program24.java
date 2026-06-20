

class Demo{

	public static void main(String[] args){
	
		Integer x = 10;	

		Integer y = 10;	

		System.out.println(x == y);

	}
}


/* Output:
 * 	true
 *
 * 	In this program, both variables x and y are declared as Integer objects and assigned the value. Although it looks like simple assignment
 * 	Java automatically applies autoboxing, converting the code into:
 *
 * 	Integer x = Integer.valueOf(10);
 * 	Integer y = Integer.valueOf(10);
 *
 * 	instead of creating new objects every timw, the method Integer.valueOf() uses an internal optimization called th Integer Cache. This
 * 	cache stores pre-created integer objects for values in the range -128 to 127. Since the value 10 falls within this range, Java does not
 * 	create new object, instead it returns a reference to the same cached object for the both x and y.
 *
 * 	As a result, both variables x and y point to the exact same memory location in the heap. Therefore it returns true
 *
 * */
