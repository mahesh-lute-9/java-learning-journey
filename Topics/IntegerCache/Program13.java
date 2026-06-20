

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
 * 	You can observe  the value up to which the flow is normal
 *
 * 	Once the value is changed from 127 to 128 the answer --> Not equals. And this is because of IntegerCache
 *
 * 	Let's type the same code using primitive datatype
 *
 * */
