

//SDE interview QUestion


class Student {

    int age = 20;

}

class Main {

    public static void main(String[] args) {

        Student s1 = new Student();

        Student s2 = s1;

        s1 = new Student();

        s2.age = 50;

        System.out.println(s1.age);

        System.out.println(s2.age);

    }

}


/* OUTPUT:
	20
   	50

Explanation:
	-Initially both point to the same object.
	-Then s1 points to a new object.
	-s2 still points to the old object.
	-Changing s2.age affects only the old object.
