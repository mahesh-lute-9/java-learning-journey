
//Scanner is easy to use because it can directly parse different data types.
//Read an Integer

import java.util.Scanner;

class Demo {
    
	public static void main(String[] args) {

        	Scanner sc = new Scanner(System.in);

     	   	System.out.print("Enter age: ");

        	int age = sc.nextInt();

        	System.out.println("Age = " + age);

        	sc.close();		//It's a safe option that you should close stream
    	}
}


/* Output:
 * 	Enter age: 21
	Age = 21
 *
 *
 * */

