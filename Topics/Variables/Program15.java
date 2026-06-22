

//Java is Pass By Value


class Test {

    static void change(int x) {

        x = 100;

    }

    public static void main(String[] args) {

        int x = 10;

        change(x);

        System.out.println(x);

    }

}

/*Output:
 *	10

Explanation:

	-x is a primitive variable.
	-Java passes a copy of x to the method.
	-Inside change(), only the copied variable changes.
	-Original x in main() remains 10.
