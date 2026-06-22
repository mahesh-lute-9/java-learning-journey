
//Tricky One


class Test {

    int x = 10;

}

class Main {

    public static void main(String[] args) {

        Test t1 = new Test();

        Test t2 = t1;

        t1 = null;

        System.out.println(t2.x);

    }

}

/*Output:
 	10

Explanation:

	-t2 still points to the object.
	-Setting t1 = null only removes one reference.
	-Object still exists because t2 references it.
	-Therefore t2.x prints 10.
