

class Demo{
	
	public static void main(String[] args){
	
		Integer x = 128;

		Integer y = 128;

		if(x.equals(y)){
		
			System.out.println("Equals");
		}else{
			System.out.println("Not equals");
		}
	}
}


/* Output:
 * 	 Equals
 *
 * 	 Now here we're using .equals() method so it compares the value/content of varibles and returns true there fore it gives Equals ans.
 * 	 Ans always remember that (==) compares the references of variables/objects not actual content
 * */
