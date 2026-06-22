
//Scope Based Questions


class Test {

    public static void main(String[] args) {

        int x = 10;

        {

            int y = 20;

            System.out.println(x);

            System.out.println(y);

        }

        System.out.println(y);

    }

}


/* Output:
 *
 * 	Compilation Error

	Error:

		cannot find symbol : y
Explanation:
	-y is declared inside the inner block {}.
	-Scope of y ends when that block ends.
	-Outside the block, y no longer exists.
	-Therefore compilation fails.

Interview Point:

		{
 		  int y = 20;
		}

	After }, y is destroyed.
 * */
