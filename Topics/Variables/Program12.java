
//Tricky One


class Test {

    static int x = 10;

    Test() {

        x++;

    }

    public static void main(String[] args) {

        Test t1 = new Test();

        Test t2 = new Test();

        Test t3 = new Test();

        System.out.println(x);

    }

}


/*Output:
 * 	13

Explanation:

	-Initial x=10.
	-Constructor called 3 times.
	-Every object increments the same static variable.
	-Final value = 13.
