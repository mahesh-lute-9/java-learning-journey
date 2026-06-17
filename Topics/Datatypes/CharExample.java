

class CharExample{

	public static void main(String[] args){
	
		//storing the wing of the building

		char wing = 'A';

		System.out.println("Building wing: " + wing);
	}
}


/* Output:
 * 	Building wing: A
 *
 * -------------------------------------------------------------
 *
 *  In java char is internally int values because java support unicode
 *  language format.
 *
 *  Memory: The char is of 2 bytes,which means 16 bits because java uses
 *  	    unicode encoding.
 *
 *  Range: 0 to 65,535(Unicode characters)
 *
 *  Used for: Storing a single character
 *
 * */
