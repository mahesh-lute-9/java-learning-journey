

# try and catch

## Overview

Exceptions are handled in Java using the `try` and `catch` blocks.

- The **try** block contains code that may throw an exception.
- The **catch** block handles the exception if one occurs.

If no exception occurs, the catch block is skipped.

If an exception occurs and a matching catch block is found, the control is transferred to that catch block.

---

# Syntax

```java
try {
    // Code that may throw an exception
} catch (ExceptionType exceptionObject) {
    // Code to handle the exception
}
```

---

# How try-catch Works

```text
Start
  │
  ▼
Enter try Block
  │
  ▼
Execute Statements
  │
  ▼
Exception Occurs?
  │
 ┌┴───────────────┐
 │                │
No               Yes
 │                │
 ▼                ▼
Skip catch   JVM Creates Exception Object
 │                │
 ▼                ▼
Continue     Matching catch Found?
 Program          │
              ┌───┴────┐
              │        │
             Yes      No
              │        │
              ▼        ▼
     Execute catch   Program Terminates
              │
              ▼
        Continue Program
```

---

# Understanding the try Block

The `try` block contains statements that might generate an exception during execution.

Rules:

- Every `try` block must be followed by at least one `catch` block or a `finally` block.
- A `try` block cannot exist alone.
- Multiple statements can be placed inside a `try` block.
- An exception may occur on any statement inside the block.

Example

```java
try {
    int a = 10;
    int b = 0;
    int result = a / b;

    System.out.println(result);
}
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero.");
}
```

---

# Understanding the catch Block

The `catch` block executes only when an exception matching its type occurs.

Syntax

```java
catch (ExceptionType e) {

}
```

Where

- `ExceptionType` specifies the type of exception to handle.
- `e` is the exception object created by the JVM.

---

# Example 1 — Basic try-catch

```java
public class Main {

    public static void main(String[] args) {

        try {
            int result = 10 / 0;
            System.out.println(result);
        }

        catch (ArithmeticException e) {
            System.out.println("Division by zero is not allowed.");
        }

        System.out.println("Program Finished.");
    }
}
```

Output

```text
Division by zero is not allowed.
Program Finished.
```

### Explanation

1. JVM enters the `try` block.
2. Division by zero occurs.
3. JVM creates an `ArithmeticException` object.
4. JVM searches for a matching `catch` block.
5. The matching catch block executes.
6. Program continues after the catch block.

---

# Example 2 — No Exception Occurs

```java
public class Main {

    public static void main(String[] args) {

        try {

            int result = 20 / 5;

            System.out.println(result);

        }

        catch (ArithmeticException e) {

            System.out.println("Exception Handled");

        }

        System.out.println("Program Finished.");

    }

}
```

Output

```text
4
Program Finished.
```

### Explanation

Since no exception occurred,

- the catch block is skipped,
- execution continues normally.

---

# Example 3 — Exception Not Matching

```java
public class Main {

    public static void main(String[] args) {

        try {

            String str = null;

            System.out.println(str.length());

        }

        catch (ArithmeticException e) {

            System.out.println("Handled");

        }

    }

}
```

Output

```text
Exception in thread "main"
java.lang.NullPointerException
```

### Why?

The exception generated is

```text
NullPointerException
```

But the catch block handles

```text
ArithmeticException
```

No matching catch block is found.

Therefore,

the JVM terminates the program.

---

# Exception Object

Whenever an exception occurs,

the JVM automatically creates an object of the corresponding exception class.

Example

```java
ArithmeticException e
```

Here,

`e` is an object containing information such as

- Exception name
- Error message
- Stack trace
- Cause (if any)

You can access this information using methods like:

```java
e.getMessage();

e.printStackTrace();

e.toString();
```

These methods will be discussed later.

---

# Control Flow

```text
try {

Statement 1

Statement 2

Statement 3 ← Exception Occurs

Statement 4

}

catch (...)

{

Handle Exception

}

Statement 5
```

Execution Order

- Statement 1 ✅
- Statement 2 ✅
- Statement 3 ❌ Exception Occurs
- Statement 4 ❌ Skipped
- catch Block ✅
- Statement 5 ✅

Once an exception occurs,

the remaining statements inside the try block are skipped.

---

# Common Runtime Exceptions Handled Using try-catch

| Exception | Cause |
|-----------|-------|
| ArithmeticException | Divide by zero |
| NullPointerException | Accessing a null reference |
| ArrayIndexOutOfBoundsException | Invalid array index |
| NumberFormatException | Invalid number conversion |
| ClassCastException | Invalid object casting |

---

# Best Practices

- Keep the `try` block as small as possible.
- Catch the most specific exception.
- Write meaningful handling logic.
- Avoid empty catch blocks.
- Do not use exceptions for normal program flow.

---

# Common Mistakes

❌ Writing large amounts of code inside a single try block.

❌ Catching `Exception` when a specific exception is sufficient.

❌ Ignoring the exception object.

❌ Leaving the catch block empty.

```java
catch(Exception e){

}
```

This makes debugging difficult.

---

# Key Takeaways

- The `try` block contains code that may generate an exception.
- The `catch` block handles matching exceptions.
- The JVM creates the exception object automatically.
- If no matching catch block exists, the program terminates.
- After handling an exception, execution continues after the catch block.

---

## Related Programs

- `01_BasicTryCatch.java`
- `02_NoException.java`
- `03_ExceptionNotMatching.java`
- `04_TryBlockExecutionFlow.java`

---

## Next Chapter

➡️ **04_Finally_Block.md**
