
//final Primitive Variable


class Main {

    public static void main(String[] args) {

        final int x = 10;

        x = 20;

        System.out.println(x);

    }

}

/*Output:
	Compilation Error

	Error:

	cannot assign a value to final variable x


Explanation:
	-final means value cannot be reassigned.
	-x receives value once.
	-Any further assignment causes compilation error.
