
//Static variables shared by Objects


class Student{

	static String college = "ABC";
}


class Main{

	public static void main(String[] args){
	
		Student s1 = new Student();

		Student s2 = new Student();

        	s1.college = "XYZ";	//changing the value of college by using object

        	System.out.println(s2.college); 	//reflected change in diff. object of same class
	}
}



/* Output:
 *	XYZ
 *
 * Explanation:
 *	-college is static, so only one copy exists for the class.
	-Changing through s1 changes the same static variable.
	-s2 also sees the updated value.
	-Static members are shared by all objects.

	Memory View:

	Method Area

		college -> "XYZ"

		s1 ---->
		s2 ----> Same static variable

	Interview Point:

	Prefer:

		Student.college

	instead of:

		s1.college
 *
 * */
