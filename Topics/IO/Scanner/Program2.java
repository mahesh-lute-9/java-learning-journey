
//Read a String(Single word)

import java.util.*;

class Demo{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter name: ");

		String name = sc.next();

		System.out.println(name);

		sc.close();
	}
}

/* Output:
 * 	Enter name: Mahesh
	Mahesh
 *
 * 	next() stops at whitespace
 * */
