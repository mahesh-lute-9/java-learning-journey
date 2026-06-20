
//20-Dimensional Array

class Demo{

	public static void main(String[] args){
	
		int arr1[];

		int arr2[][];
	}
}



/* No error
 *
 * When you write int arr1[]; you are telling Java that arr1 is a variable the can hold a reference to a one-dimensional integer array. Similiarly
 * int arr2[][]; declares a variable that can hold a reference to a two-dimensional integer array. At thia stage, no actual array is created in memory.
 * These variables simply exist in the stack frame of the main method, but they do not yet point to any array object in the heap.
 *
 * Because Java allows variables to be declared without immediately assigning the a value, this code compiles successfully and produces no error. The
 * compiler only checks whether the syntax is correct, and in this case, both declarations are valid so
 *
 * */
