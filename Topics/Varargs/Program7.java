

//Varargs with Objects


class Employee{

	String name;

	Employee(String name){
	
		this.name = name;
	}
}


class Demo{

	static void print(Employee... emp){
	
		for(Employee e : emp){
		
			System.out.println(e.name);
		}
	}

	public static void main(String[] args){
	
		Employee e1 = new Employee("Mahesh");

		Employee e2 = new Employee("Rahul");

		print(e1,e2);
	}
}
