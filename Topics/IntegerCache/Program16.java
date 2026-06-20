

class Demo{
	
	public static void main(String[] args){
	
		Integer x = 128;

		Integer y = 128;

		if(x == y){
		
			System.out.println("Equals");
		}else{
			System.out.println("Not equals");
		}
	}
}


/* Output:
 * 	Not Equals
 *
 * 	Upto the value 127, the Integer cache handles the references but above 127 there will be the creation of new references for the value
 * */
