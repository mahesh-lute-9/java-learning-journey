

//Famous Interview Question


class Student {

    int age = 20;

}

class Main {

    static void change(Student s) {

        s = new Student();

        s.age = 100;

    }

    public static void main(String[] args) {

        Student s = new Student();

        change(s);

        System.out.println(s.age);

    }

}

/*Output:
	20

Explanation:
	-change() receives a copy of the reference.
	-s = new Student() changes only the copied reference.
	-Original s in main() still points to old object.
	-Therefore output remains 20.

	This proves:
		Java is ALWAYS Pass By Value.

		Not pass by reference.

This is one of the most asked interview questions.
