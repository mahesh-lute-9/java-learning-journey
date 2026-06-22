

//final Reference Variable


class Student {

    int age;

}

class Main {

    public static void main(String[] args) {

        final Student s = new Student();

        s.age = 100;

        System.out.println(s.age);

    }

}

/*Output:
	100

	
Explanation:
	-final protects the reference, not the object.
	-Object data can still be modified.
	-Therefore changing age is allowed.
