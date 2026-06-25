

//Varargs with Normal Parameter

//The varargs parameter must come last.


class Demo{

	static void showStudent(String name, int... marks){
	
		System.out.println("Student : " + name);

		for(int mark : marks){
		
			System.out.println(mark + " ");
		}
	}

	public static void main(String[] args){
	
		showStudent("Mahesh", 85,90,95);
	}
}


/* Output:
 * 	Student : Mahesh
	85
	90
	95
 *
 * */
