
//Variable Shadowing


class Student {

    int age = 21;

    void show() {

        int age = 30;

        System.out.println(age);

        System.out.println(this.age);

    }

}

class Main {

    public static void main(String[] args) {

        Student s = new Student();

        s.show();

    }

}

/*Output:
 	30
	21

	
Explanation:

	-Inside show(), the local variable age=30 shadows the instance variable.
	-age refers to the local variable.
	-this.age refers to the instance variable of the current object.
	-Therefore, first 30 then 21 is printed.

Interview Point:
	age       // local variable
	this.age  // instance variable
