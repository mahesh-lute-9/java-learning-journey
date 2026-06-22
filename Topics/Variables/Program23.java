

//SDE Level


class Student {

    int age;

}

class Main {

    public static void main(String[] args) {

        final Student s = new Student();

        s.age = 10;

        s.age = 50;

        System.out.println(s.age);

    }

}

/*Output:
	50


Explanation:
	-Object is mutable.
	-final doesn't make object immutable.
	-Only the reference cannot change.
	-Therefore modifying fields is allowed.

---------------------------------------------------------------------------------

Interview Question:

	final StringBuilder sb = new StringBuilder("Java");

	sb.append(" Programming");

	System.out.println(sb);

Output:
	Java Programming
Why?

Because:

	final = reference cannot change

NOT

	final = object cannot change


* Most Important Rule to Remember

final primitive - Value cannot change

final reference - Reference cannot change

Object may change
