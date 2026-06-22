
//Passing Object Reference



class Student {

    int age = 20;

}

class Main {

    static void change(Student s) {

        s.age = 100;

    }

    public static void main(String[] args) {

        Student s = new Student();

        change(s);

        System.out.println(s.age);

    }

}

/*Output:
	100

Explanation:
	-Java passes a copy of the reference.
	-Both references point to the same object.
	-Changing object's state affects the original object.
	-Therefore age becomes 100.
