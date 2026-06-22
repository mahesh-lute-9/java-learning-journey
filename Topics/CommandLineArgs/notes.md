Command Line Arguments in Java: In detailed with Interview Questions

In Java, Command Line Arguments (CLA) are values passed to a Java program when it is executed from the terminal/command prompt.

They are stored in the String[] args parameter of the main() method.

public class Main {
    public static void main(String[] args) {
        System.out.println("Number of arguments = " + args.length);
    }
}
1. Syntax of main() Method
public static void main(String[] args)
Meaning of each keyword
Keyword	Meaning
public	JVM can access it from anywhere
static	Object creation not required
void	Doesn't return anything
main	Entry point of Java program
String[] args	Stores command line arguments

You can also write:

public static void main(String args[])

Both are equivalent.

How Command Line Arguments Work

Suppose:

public class Test {
    public static void main(String[] args) {

        System.out.println(args[0]);
        System.out.println(args[1]);
    }
}

Compile:

javac Test.java

Run:

java Test Mahesh Lute

Output:

Mahesh
Lute

Internally:

args[0] = "Mahesh"
args[1] = "Lute"
Example 1 : Print All Arguments
public class Main {

    public static void main(String[] args) {

        for(String s : args){
            System.out.println(s);
        }

    }

}

Run:

java Main Java SpringBoot DSA

Output:

Java
SpringBoot
DSA
Example 2 : Add Two Numbers

Remember:

All command line arguments are Strings.

You must convert them.

public class Main {

    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        System.out.println(a+b);

    }

}

Run:

java Main 10 20

Output:

30
Example 3 : Double Values
double price = Double.parseDouble(args[0]);

Run:

java Main 99.99

Output:

99.99
Example 4 : Boolean Argument
boolean isAdmin = Boolean.parseBoolean(args[0]);

System.out.println(isAdmin);

Run:

java Main true

Output:

true
What Happens if No Arguments Are Passed?
public class Main {

    public static void main(String[] args){

        System.out.println(args[0]);

    }

}

Run:

java Main

Output:

Exception in thread "main"
java.lang.ArrayIndexOutOfBoundsException

Because:

args.length = 0

There is no args[0].

Safe Way
public class Main {

    public static void main(String[] args){

        if(args.length > 0)
            System.out.println(args[0]);
        else
            System.out.println("No arguments passed");

    }

}
Important Property : Arguments Are Always Strings

Even:

java Main 10 true 99.5

Internally:

args[0] = "10"
args[1] = "true"
args[2] = "99.5"

Datatype:

String

Always.

Memory Representation

If:

java Main A B C

JVM creates:

args
  |
  V

+-----+-----+-----+
| "A" | "B" | "C" |
+-----+-----+-----+

args[0]
args[1]
args[2]

args is simply a reference to a String[].

Difference Between Scanner and Command Line Arguments
Command Line Arguments	Scanner
Input before program starts	Input during execution
Stored in args[]	Reads from keyboard
Faster	Slightly slower
No interaction	Interactive
Good for scripts	Good for applications

Example:

java Main Mahesh

vs

Scanner sc = new Scanner(System.in);
String name = sc.next();
Variable Arguments (varargs) vs Command Line Arguments

Students often confuse:

void fun(int... a)

with

main(String[] args)

Difference:

Varargs	Command Line
Inside methods	Passed from terminal
Any datatype	Only String
Compiler feature	JVM feature
JVM Internals (SDE Interview)

When you execute:

java Main hello world

JVM performs:

Step 1

Creates:

String[] args
Step 2

Stores:

args[0] = "hello"
args[1] = "world"
Step 3

Invokes:

Main.main(args);

Equivalent to:

String[] arr = {"hello","world"};

Main.main(arr);
Can main() be overloaded?

Yes.

class Main {

    public static void main(String[] args){

        System.out.println("Original Main");
    }

    public static void main(int a){

        System.out.println(a);
    }

}

JVM always calls:

main(String[] args)

only.

Can args be renamed?

Yes.

public static void main(String[] x)

or

public static void main(String[] mahesh)

Works perfectly.

Can args be final?

Yes.

public static void main(final String[] args)

Valid.

Can main be written as:
static public void main(String... args)

Yes.

Because:

String... args

is compiled as:

String[] args
SDE Interview Questions
1. Are command line arguments Strings or Objects?

Answer:

Always String.

Even:

java Main 100 true 99.5

becomes:

"100"
"true"
"99.5"
2. Why is main() static?

Answer:

Because JVM calls:

Main.main(args);

without creating:

new Main();
3. Can main() be private?
private static void main(String[] args)

No

JVM cannot access it.

Must be:

public static void main(String[] args)
4. What happens if:
public void main(String[] args)

(no static)

Answer:

Compilation succeeds.

But:

Error: Main method not found

because JVM expects:

public static void main(String[] args)
5. Which is correct?
String args[]

or

String[] args

Both are correct.

But industry standard:

String[] args
6. Is this valid?
public static void main(String... args)

Yes.

Because varargs is internally:

String[]
7. Can command line arguments contain spaces?

Run:

java Main "Mahesh Lute"

Then:

args[0] = "Mahesh Lute"

Without quotes:

java Main Mahesh Lute

You get:

args[0]="Mahesh"
args[1]="Lute"
8. What exception occurs if no argument is passed?
args[0]

throws:

ArrayIndexOutOfBoundsException



--------------------------------------------------------------------------------------------------------------------------------------------------------

SDE Interview Questions on Command Line Arguments in Java (With Answers)
Q1. Why is main() declared as static?

Answer:

main() is the entry point of a Java application. The JVM needs to invoke it before creating any object of the class.

Because it is static, JVM can call:

Main.main(args);

instead of:

Main obj = new Main();
obj.main(args);

If main() were non-static:

public void main(String[] args)

The program compiles, but running it gives:

Error: Main method not found in class Main
Q2. Can we overload main()?

Answer:

✅ Yes.

class Main {

    public static void main(String[] args) {
        System.out.println("Original Main");
    }

    public static void main(int x) {
        System.out.println(x);
    }
}

Output:

Original Main

JVM always starts with:

public static void main(String[] args)

The overloaded versions are called only manually.

Q3. Can we override main()?

Answer:

Technically No, because:

main() is static.

And static methods belong to the class, not objects.

Example:

class Parent {

    public static void main(String[] args){
        System.out.println("Parent");
    }

}

class Child extends Parent {

    public static void main(String[] args){
        System.out.println("Child");
    }

}

This is method hiding, not overriding.

Q4. Why are Command Line Arguments always Strings?

Suppose:

java Main 100 true 3.14

Internally:

args[0] = "100"
args[1] = "true"
args[2] = "3.14"

Datatype:

String

always.

Q5. How do you convert Command Line Arguments into integers?

Answer

public class Main {

    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        System.out.println(a+b);

    }

}

Run:

java Main 10 20

Output:

30
Q6. What exception occurs if the argument is not a number?

Example:

int n = Integer.parseInt(args[0]);

Run:

java Main abc

Output:

NumberFormatException

Reason:

"abc" cannot be converted to int.
Q7. What happens if no arguments are passed?
public class Main {

    public static void main(String[] args) {

        System.out.println(args[0]);

    }

}

Run:

java Main

Output:

Exception in thread "main"
java.lang.ArrayIndexOutOfBoundsException

Because:

args.length == 0

There is no element at index 0.

Q8. How do you avoid ArrayIndexOutOfBoundsException?
public class Main {

    public static void main(String[] args) {

        if(args.length > 0)
            System.out.println(args[0]);

        else
            System.out.println("No Arguments");

    }

}

Output:

No Arguments
Q9. Which is better?
String args[]

or

String[] args

Answer

Both are valid.

But industry standard:

String[] args

because it clearly indicates:

args is an array of Strings
Q10. Is this valid?
public static void main(String... args)

Answer

✅ Yes.

Because:

String... args

is internally converted to:

String[] args
Q11. Can the parameter name be changed?

Instead of:

public static void main(String[] args)

Can we write:

public static void main(String[] mahesh)

or

public static void main(String[] x)

Answer

✅ Yes.

Example:

public class Main {

    public static void main(String[] mahesh){

        System.out.println(mahesh.length);

    }

}

Works perfectly.

Q12. Can main be final?

Answer

Yes.

public static final void main(String[] args)

Valid.

Q13. Can main be synchronized?

Answer

Yes.

public static synchronized void main(String[] args)

Valid.

Q14. Can main throw exceptions?

Answer

Yes.

public class Main {

    public static void main(String[] args) throws Exception {

        throw new Exception("Hello");

    }

}

Output:

Exception in thread "main"
java.lang.Exception: Hello
Q15. Can main() be private?
private static void main(String[] args)

Answer

❌ No.

JVM looks specifically for:

public static void main(String[] args)

Otherwise:

Error: Main method not found
Q16. Can main return int?
public static int main(String[] args)

Answer

❌ No.

The return type must be:

void

Otherwise:

Error: Main method must return void
Q17. What is the difference between:
args.length

and

args[0].length()

Example:

java Main Mahesh

Then:

args.length

returns:

1

because there is only one argument.

But:

args[0].length()

returns:

6

because:

"Mahesh" has 6 characters.
Q18. What happens internally when JVM runs:
java Main Hello World

JVM performs:

Step 1

Loads class:

Main.class
Step 2

Creates:

String[] args

containing:

args[0] = "Hello";
args[1] = "World";
Step 3

Invokes:

Main.main(args);

Equivalent to:

String[] arr = {"Hello","World"};

Main.main(arr);
Q19. Can command line arguments contain spaces?

Example:

java Main "Mahesh Lute"

Then:

args[0]

contains:

Mahesh Lute

with the space included.

Without quotes:

java Main Mahesh Lute

Then:

args[0] = "Mahesh"
args[1] = "Lute"
Q20. Difference between JVM arguments and Command Line Arguments?

Example:

java -Xmx512m -Dname=Mahesh Main Java DSA
Part	Meaning
-Xmx512m	JVM argument (Heap Size)
-Dname=Mahesh	System Property
Java	Command Line Argument
DSA	Command Line Argument

Inside:

args[0] = "Java";
args[1] = "DSA";

But:

System.getProperty("name")

returns:

Mahesh
Frequently Asked by SDE Interviewers
Why is main() static?
Can main() be overloaded? Explain with code.
Can static methods be overridden?
Why are command line arguments always Strings?
Difference between String[] args and String... args.
What exceptions are common while using command line arguments?
What does JVM do internally before calling main()?
Difference between JVM arguments and application arguments.
Can main() be final, synchronized, or throw exceptions?
What happens if the JVM cannot find public static void main(String[] args)?
