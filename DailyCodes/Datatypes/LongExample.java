

class LongExample{

	public static void main(String[] args){
	
		//storing a very large transaction ID

		long transactionId = 9876543210100l;

		System.out.println("Transaction ID: " + transactionId);
	}
}


/* Output:
 * 	Transaction ID: 9876543210100
 *
 * ---------------------------------------------------------------------
 *
 *  * On long values we have to give l or L aplhabet at the end of value.
 *
 *	
 *	Memory: The long takes 8 bytes, which means about 64 bits.
 *
 *	Range: -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
 *
 *	Used for: Storing very large integer values.
 *		
 **/
