````markdown
# throws Keyword

## Overview

The `throws` keyword is used to declare that a method may throw one or more exceptions.

Unlike the `throw` keyword, which actually throws an exception, the `throws` keyword only informs the caller that a particular exception might occur during method execution.

It is mainly used with **checked exceptions**.

---

# Why do we need throws?

Some operations are risky and may fail due to external reasons.

Examples include:

- Reading a file
- Writing to a file
- Database operations
- Network communication
- Thread operations

Instead of handling these exceptions immediately, a method can pass the responsibility to the calling method using `throws`.

---

# Syntax

```java
returnType methodName() throws ExceptionType {

    // Code that may throw an exception

}
```

Example

```java
void readFile() throws IOException {

}
```

---

# How throws Works

```text
Method Starts
      │
      ▼
Risky Code Executes
      │
      ▼
Exception Occurs?
      │
 ┌────┴────┐
 │         │
No        Yes
 │         │
 ▼         ▼
Return   Method Declares Exception
Normally        │
                ▼
      Calling Method Must
      Handle or Declare It
```

---

# Example 1 — Method Declares Exception

```java
import java.io.IOException;

public class Main {

    static void test() throws IOException {

        throw new IOException("File not found.");

    }

    public static void main(String[] args) {

        try {

            test();

        }

        catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

}
```

Output

```text
File not found.
```

### Explanation

- The `test()` method declares that it may throw an `IOException`.
- The `main()` method calls `test()`.
- Since `main()` calls a method that throws a checked exception, it must either handle or declare the exception.
- Here, it handles the exception using `try-catch`.

---

# Example 2 — Multiple Exceptions

```java
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    static void process() throws IOException, SQLException {

    }

}
```

A method can declare multiple exceptions using commas.

---

# Example 3 — Declaring Instead of Handling

```java
import java.io.IOException;

public class Main {

    static void methodA() throws IOException {

        throw new IOException("Error");

    }

    public static void main(String[] args) throws IOException {

        methodA();

    }

}
```

Output

```text
Exception in thread "main"
java.io.IOException: Error
```

### Explanation

- `methodA()` declares the exception.
- `main()` also declares the exception.
- No method handles it.
- Eventually, the JVM handles it and terminates the program.

---

# throws with Checked Exceptions

Checked exceptions must be either:

- Handled using `try-catch`
- Declared using `throws`

Example

```java
FileReader reader = new FileReader("data.txt");
```

The compiler requires proper handling because `FileReader` may throw a `FileNotFoundException`.

---

# throws with Unchecked Exceptions

Using `throws` for unchecked exceptions is optional.

Example

```java
void divide() throws ArithmeticException {

    int result = 10 / 0;

}
```

Although valid, it is generally unnecessary because unchecked exceptions are not checked by the compiler.

---

# throw vs throws

| throw | throws |
|--------|---------|
| Used to explicitly throw an exception. | Used to declare that a method may throw exceptions. |
| Used inside a method. | Used in the method declaration. |
| Throws one exception object at a time. | Can declare multiple exception types. |
| Transfers control to the JVM immediately. | Only informs the compiler and caller. |

Example

```java
throw new IOException("File missing.");
```

```java
void readFile() throws IOException {

}
```

---

# Exception Propagation

If a method does not handle an exception, it is propagated to the calling method.

```text
methodC()
    ▲
    │
methodB()
    ▲
    │
methodA()
```

If `methodA()` throws an exception and does not handle it,

the exception propagates to `methodB()`.

If `methodB()` also does not handle it,

it propagates to `methodC()`.

Eventually, if no method handles the exception, the JVM terminates the program.

---

# Best Practices

- Use `throws` primarily for checked exceptions.
- Handle exceptions at the appropriate level.
- Declare only the exceptions that a method can actually throw.
- Avoid declaring unnecessary exceptions.

---

# Common Mistakes

❌ Confusing `throw` with `throws`.

❌ Declaring `throws Exception` for every method without a valid reason.

❌ Ignoring checked exceptions.

❌ Declaring exceptions that never occur.

---

# Interview Questions

### 1. What is the purpose of the `throws` keyword?

It declares that a method may throw one or more exceptions.

---

### 2. Does `throws` actually throw an exception?

No.

It only declares the possibility of an exception.

---

### 3. Can a method declare multiple exceptions?

Yes.

```java
void process() throws IOException, SQLException {

}
```

---

### 4. Is `throws` mandatory for unchecked exceptions?

No.

It is optional because unchecked exceptions are not checked by the compiler.

---

### 5. What happens if no method handles a declared exception?

The exception propagates up the call stack.

If it remains unhandled, the JVM terminates the program.

---

# Key Takeaways

- `throws` declares exceptions that a method may throw.
- It is mainly used with checked exceptions.
- It transfers the responsibility of handling exceptions to the calling method.
- It does not actually throw an exception.
- If no method handles the propagated exception, the JVM terminates the program.

---

## Related Programs

- `15_BasicThrows.java`
- `16_ThrowsWithIOException.java`
- `17_MultipleThrows.java`
- `18_ExceptionPropagation.java`
- `19_ThrowsVsThrow.java`

---

## Next Chapter

➡️ **07_CheckedVsUnchecked.md**
````
