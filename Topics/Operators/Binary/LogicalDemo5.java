

class LogicalDemo5{

	public static void main(String[] args){
	
		int x = 7;

		int y = 8;

		boolean result = (++x < --y) || (x++ < --y);

		System.out.println(result);

		System.out.println(x);

		System.out.println(y);
	}
}


/* Output:
 * 	false
	9
	6
 *
 */
