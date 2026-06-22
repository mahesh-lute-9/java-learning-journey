
//Instance Variable with Default value. Hw to access default values?


class Student{
	
	int age;

	void show(){
	
		System.out.println(age);	//0
	}
}

class Main{

	public static void main(String[] args) {

        Student s = new Student();	//object creation

        s.show();	//method calling

    }
}



/* Output:
	0


Explanation:
	-age is an instance variable.
	-JVM automatically initializes instance variables.
	-Default value of int is 0.
	-Since we never assigned any value, 0 is printed.
	
Interview Point:

	Default values:

	int -> 0
	double -> 0.0
	boolean -> false
	char -> '\u0000'
	Object -> null
 *
 * */
