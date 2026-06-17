
//import error code


class Demo{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your name: ");
		String name = sc.next();

		System.out.println("Name: " + name);
	}
}


/*
 * Output:
 * 	Program6.java:9: error: cannot find symbol
                Scanner sc = new Scanner(System.in);
                ^
  symbol:   class Scanner
  location: class Demo
Program6.java:9: error: cannot find symbol
                Scanner sc = new Scanner(System.in);
                                 ^
  symbol:   class Scanner
  location: class Demo
2 errors
**

	It can't find Scanner class because the Scanner class is present inside the util package and it does
       	not imported yet. So it can't find it and throws errors both the sides The class and it's object also.
 * */
