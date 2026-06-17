
//All the methods of Scanner class

import java.util.*;

class Employee{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Employee name: ");
		String name = sc.next();

		System.out.println("Enter Employee age: ");
		int age = sc.nextInt();

		System.out.println("Enter employee salary: ");
		float salary = sc.nextFloat();

		System.out.println("Employee Details: ");
		System.out.println("Emp name: " + name);
		System.out.println("Emp age: " + age);
		System.out.println("Emp salary: " + salary);

		sc.close();
	}
}



/* Output:
 * 	Enter Employee name:
	Mahesh
	Enter Employee age:
	21
	Enter employee salary:
	80000
	Employee Details:
	Emp name: Mahesh
	Emp age: 21
	Emp salary: 80000.0
 *
 *
 * */
