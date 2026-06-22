# 🚀 Command Line Arguments in Java

## Introduction

Command Line Arguments (CLA) are values passed to a Java program when it is executed from the terminal or command prompt.

These arguments are stored in the `String[] args` parameter of the `main()` method.

Example:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("Number of Arguments = " + args.length);

    }

}
```

---

## Syntax of main() Method

```java
public static void main(String[] args)
```

### Meaning of Each Keyword

| Keyword       | Meaning                         |
| ------------- | ------------------------------- |
| public        | JVM can access it from anywhere |
| static        | Object creation is not required |
| void          | Does not return anything        |
| main          | Entry point of Java program     |
| String[] args | Stores command line arguments   |

You can also write:

```java
public static void main(String args[])
```

Both are completely valid.

---

## How Command Line Arguments Work

Suppose:

```java
public class Test {

    public static void main(String[] args) {

        System.out.println(args[0]);
        System.out.println(args[1]);

    }

}
```

Compile:

```bash
javac Test.java
```

Run:

```bash
java Test Mahesh Lute
```

Output:

```text
Mahesh
Lute
```

Internally:

```java
args[0] = "Mahesh";
args[1] = "Lute";
```

---

## Example 1 : Print All Arguments

```java
public class Main {

    public static void main(String[] args) {

        for(String s : args){

            System.out.println(s);

        }

    }

}
```

Run:

```bash
java Main Java SpringBoot DSA
```

Output:

```text
Java
SpringBoot
DSA
```

---

## Example 2 : Add Two Numbers

Remember:

**All command line arguments are Strings.**

You must convert them.

```java
public class Main {

    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);

        int b = Integer.parseInt(args[1]);

        System.out.println(a+b);

    }

}
```

Run:

```bash
java Main 10 20
```

Output:

```text
30
```

---

## Example 3 : Double Values

```java
double price = Double.parseDouble(args[0]);
```

Run:

```bash
java Main 99.99
```

Output:

```text
99.99
```

---

## Example 4 : Boolean Argument

```java
boolean isAdmin = Boolean.parseBoolean(args[0]);

System.out.println(isAdmin);
```

Run:

```bash
java Main true
```

Output:

```text
true
```

---

## What Happens if No Arguments Are Passed?

```java
public class Main {

    public static void main(String[] args){

        System.out.println(args[0]);

    }

}
```

Run:

```bash
java Main
```

Output:

```text
Exception in thread "main"
java.lang.ArrayIndexOutOfBoundsException
```

Because:

```java
args.length == 0
```

There is no element at index `0`.

---

## Safe Way

```java
public class Main {

    public static void main(String[] args){

        if(args.length > 0)

            System.out.println(args[0]);

        else

            System.out.println("No Arguments Passed");

    }

}
```

---

## Important Property

Command Line Arguments are **ALWAYS Strings**.

Example:

```bash
java Main 10 true 99.5
```

Internally:

```java
args[0] = "10";
args[1] = "true";
args[2] = "99.5";
```

Datatype:

```java
String
```

Always.

---

## Memory Representation

If:

```bash
java Main A B C
```

JVM creates:

```text
args
 |
 v

+-----+-----+-----+
| "A" | "B" | "C" |
+-----+-----+-----+

args[0]
args[1]
args[2]
```

`args` is simply a reference to a `String[]`.

---

## Scanner vs Command Line Arguments

| Command Line Arguments      | Scanner                |
| --------------------------- | ---------------------- |
| Input before program starts | Input during execution |
| Stored in args[]            | Reads from keyboard    |
| Faster                      | Slightly slower        |
| No interaction              | Interactive            |
| Good for scripts            | Good for applications  |

Example:

```bash
java Main Mahesh
```

vs

```java
Scanner sc = new Scanner(System.in);

String name = sc.next();
```

---

## Varargs vs Command Line Arguments

Students often confuse:

```java
void fun(int... a)
```

with

```java
main(String[] args)
```

| Varargs             | Command Line Arguments |
| ------------------- | ---------------------- |
| Used inside methods | Passed from terminal   |
| Any datatype        | Only String            |
| Compiler feature    | JVM feature            |

---

## JVM Internals

When:

```bash
java Main Hello World
```

### Step 1

JVM loads:

```text
Main.class
```

### Step 2

Creates:

```java
String[] args;
```

Stores:

```java
args[0] = "Hello";
args[1] = "World";
```

### Step 3

Invokes:

```java
Main.main(args);
```

Equivalent to:

```java
String[] arr = {"Hello","World"};

Main.main(arr);
```

---

## Can main() be overloaded?

Yes.

```java
class Main {

    public static void main(String[] args){

        System.out.println("Original Main");

    }

    public static void main(int x){

        System.out.println(x);

    }

}
```

JVM always calls:

```java
public static void main(String[] args)
```

---

## Can args be renamed?

Yes.

```java
public static void main(String[] x)
```

or

```java
public static void main(String[] mahesh)
```

Both are valid.

---

## Can args be final?

Yes.

```java
public static final void main(String[] args)
```

Valid.

---

## Can main() be written as varargs?

Yes.

```java
public static void main(String... args)
```

Internally:

```java
String[]
```

---

# --------------------------------------------------

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. Why is main() static?

**Answer:**

JVM invokes:

```java
Main.main(args);
```

without creating:

```java
new Main();
```

Hence `main()` must be static.

---

### Q2. Can main() be overloaded?

**Answer:**

Yes.

```java
public static void main(String[] args){

}

public static void main(int x){

}
```

JVM always starts with:

```java
public static void main(String[] args)
```

---

### Q3. Can main() be overridden?

**Answer:**

No.

Because:

```java
main() is static.
```

Static methods are hidden, not overridden.

---

### Q4. Why are Command Line Arguments always Strings?

**Answer:**

When:

```bash
java Main 100 true 3.14
```

Internally:

```java
"100"
"true"
"3.14"
```

All are stored as:

```java
String
```

---

### Q5. How do you convert Command Line Arguments into integers?

**Answer**

```java
int a = Integer.parseInt(args[0]);

int b = Integer.parseInt(args[1]);
```

---

### Q6. What exception occurs if the argument is not a number?

**Answer**

```java
Integer.parseInt("abc");
```

throws:

```text
NumberFormatException
```

---

### Q7. What happens if no arguments are passed?

**Answer**

```java
args[0]
```

throws:

```text
ArrayIndexOutOfBoundsException
```

because:

```java
args.length == 0
```

---

### Q8. Which is better?

```java
String args[]
```

or

```java
String[] args
```

**Answer**

Both are valid.

Industry standard:

```java
String[] args
```

---

### Q9. Can main() be private?

**Answer**

No.

JVM specifically looks for:

```java
public static void main(String[] args)
```

Otherwise:

```text
Error: Main method not found
```

---

### Q10. Can main() throw Exception?

**Answer**

Yes.

```java
public static void main(String[] args) throws Exception {

}
```

Perfectly valid.

---

### Q11. Can main() be synchronized?

**Answer**

Yes.

```java
public static synchronized void main(String[] args)
```

Valid.

---

### Q12. Is this valid?

```java
public static void main(String... args)
```

**Answer**

Yes.

Because:

```java
String... args
```

is internally converted to:

```java
String[]
```

---

### Q13. What is the difference between:

```java
args.length
```

and

```java
args[0].length()
```

**Answer**

```java
args.length
```

returns:

```text
Number of command line arguments
```

while

```java
args[0].length()
```

returns:

```text
Length of first String argument
```

Example:

```bash
java Main Mahesh
```

```java
args.length      -> 1

args[0].length() -> 6
```
