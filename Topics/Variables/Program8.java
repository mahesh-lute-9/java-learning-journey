//Static Variable Shared


class Student {

    static int count = 0;

    Student() {

        count++;

    }

}

class Main {

    public static void main(String[] args) {

        new Student();

        new Student();

        new Student();

        System.out.println(Student.count);

    }

}

/*Output:
 *	3

Explanation:

	-count is static.
	-Only one copy exists in memory.
	-Every constructor call increments the same variable.
	-After 3 objects → count = 3.
