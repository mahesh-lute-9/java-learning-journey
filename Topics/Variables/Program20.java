
//Blank final without Initialization


public class Main {

    public static void main(String[] args) {

        final int x;

        System.out.println(x);

    }

}

/*Output:
	Compilation Error

	Error:

	variable x might not have been initialized

	
Explanation:
	-final variables must be assigned before use.
	-Here no value is assigned.
	-Compiler prevents usage of uninitialized variable.
