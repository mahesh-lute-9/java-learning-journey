

//Most Confusing Question


class Student {

}

class Main {

    public static void main(String[] args) {

        final Student s = new Student();

        s = new Student();

    }

}

/*Output:
 *
	Compilation Error

	Error:

		cannot assign a value to final variable s

		
Explanation:
	-s is final.
	-Final references cannot point to another object.
	-Reassignment is illegal.
