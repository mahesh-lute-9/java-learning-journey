
//Read a Full Line


import java.util.*;

class Demo{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter address: ");

		String address = sc.nextLine();

		System.out.println(address);

		sc.close();
	}
}

/* Output:
 * 	Enter address: At, Ambegaon Bk., Pune
	At, Ambegaon Bk., Pune
 *
 * */
