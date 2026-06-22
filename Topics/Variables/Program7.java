
//hadowing with Modification


class Test {

    int x = 10;

    void display() {

        int x = 50;

        x++;

        this.x++;

        System.out.println(x);

        System.out.println(this.x);

    }

}

class Main {

    public static void main(String[] args) {

        Test t = new Test();

        t.display();

    }

}

/*Output:
 *	51
	11

	
Explanation:

	-Local x becomes 51.
	-Instance x becomes 11.
	-Both variables are different and stored separately.
	-this.x always refers to the object's variable.
