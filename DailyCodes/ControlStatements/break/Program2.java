

class Demo{
	
	public static void main(String[] args){
		
		int[] arr = {10,20,30,40,50};

		for(int i = 0; i < arr.length; i++){
			
			if(arr[i] == 30){
				
				System.out.println("Found at index: " + i);
				break;
			}
		}
	}
}


/* Output:
 * 	Found at index: 2
 *
 *
 * Here no need to continue searching after finding the element.
 *
 * */
