
//Static vs Instance Access



class Test {

    static int x = 10;

    int y = 20;

    public static void main(String[] args) {

        System.out.println(x);

        System.out.println(y);

    }

}

/*Output:
	Compilation Error

	Error:

		non-static variable y cannot be referenced from a static context

Explanation:

	-main() is static.
	-Static methods belong to the class.
	-Instance variables belong to objects.
	-Without creating an object, y cannot be accessed.
