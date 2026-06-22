
//Uninitialized Local Variable

class Test {

    public static void main(String[] args) {

        int x;

        System.out.println(x);

    }

}

/*
 * Output:
	Compilation Error

	Error:

		variable x might not have been initialized

Explanation:
	-x is a local variable.
	-Local variables do NOT get default values.
	-JVM requires explicit initialization.
	-Since x is never assigned, compilation fails.
