
//The for-each loop (also called the enhanced for loop) is used to traverse elements of an array or a collection without using an index.


/*Syntax:
	for (dataType variable : collection) {
    	// code
	}
	
	variable → stores the current element.
	collection → array or collection being traversed
*/

//Q. Print all elements of an array

class Demo{

	public static void main(String[] args) {
 
	 	int[] numbers = {10, 20, 30, 40};

        	for (int num : numbers) {
            	
			System.out.println(num);
        	}
    	}
}

/* Output:
 * 	10
	20
	30
	40
 *
 * */
