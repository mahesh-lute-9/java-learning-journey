
//Reference Variables


class Student {

    int age = 21;

}

class Main {

    public static void main(String[] args) {

        Student s1 = new Student();

        Student s2 = s1;

        s2.age = 50;

        System.out.println(s1.age);

    }

}

/*Output:
 *	50

Explanation:
	-s2 = s1 does NOT create a new object.
	-Both references point to the same object.
	-Changing through s2 changes the same object.
	-Hence s1.age also becomes 50.
