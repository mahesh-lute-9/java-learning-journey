````markdown
# Checked vs Unchecked Exceptions

## Overview

Java classifies exceptions into two categories:

1. **Checked Exceptions**
2. **Unchecked Exceptions**

The primary difference is whether the compiler forces the programmer to handle the exception.

Understanding this distinction is essential because it determines how exceptions should be handled in Java applications.

---

# Exception Classification

```text
Throwable
    │
    ├── Error
    │
    └── Exception
            │
            ├── Checked Exceptions
            │
            └── RuntimeException
                     │
                     └── Unchecked Exceptions
```

---

# Checked Exceptions

A **Checked Exception** is an exception that is checked by the compiler during compilation.

The compiler ensures that these exceptions are either:

- Handled using `try-catch`, or
- Declared using the `throws` keyword.

If neither is done, the program will not compile.

---

## Why are they called Checked Exceptions?

Because the **compiler checks** whether the programmer has handled them properly.

If not, compilation fails.

---

## Example

```java
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        FileReader reader = new FileReader("data.txt");

    }

}
```

Compilation Error

```text
Unhandled exception type FileNotFoundException
```

The compiler forces the programmer to handle the exception.

---

## Handling the Exception

```java
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        try {

            FileReader reader = new FileReader("data.txt");

        }

        catch (Exception e) {

            System.out.println("File not found.");

        }

    }

}
```

Now the program compiles successfully.

---

## Common Checked Exceptions

| Exception | Description |
|-----------|-------------|
| IOException | Input/output failure |
| FileNotFoundException | File does not exist |
| SQLException | Database operation failure |
| InterruptedException | Thread interruption |
| ClassNotFoundException | Requested class not found |

---

# Unchecked Exceptions

Unchecked Exceptions are **not checked by the compiler**.

They occur during program execution.

The compiler does not require the programmer to handle them.

Most unchecked exceptions occur because of programming mistakes.

---

## Example

```java
public class Main {

    public static void main(String[] args) {

        int result = 10 / 0;

    }

}
```

Output

```text
Exception in thread "main"
java.lang.ArithmeticException: / by zero
```

The program compiles successfully.

The exception occurs only at runtime.

---

## Another Example

```java
public class Main {

    public static void main(String[] args) {

        String str = null;

        System.out.println(str.length());

    }

}
```

Output

```text
Exception in thread "main"
java.lang.NullPointerException
```

Again,

the compiler does not report any error.

---

## Common Unchecked Exceptions

| Exception | Description |
|-----------|-------------|
| ArithmeticException | Divide by zero |
| NullPointerException | Null object access |
| ArrayIndexOutOfBoundsException | Invalid array index |
| NumberFormatException | Invalid number conversion |
| IllegalArgumentException | Invalid method argument |
| ClassCastException | Invalid type casting |

---

# Checked vs Unchecked

| Feature | Checked Exception | Unchecked Exception |
|---------|-------------------|---------------------|
| Checked by Compiler | ✅ Yes | ❌ No |
| Handling Required | ✅ Yes | ❌ No |
| Occurs | Compile-time checking | Runtime |
| Parent Class | Exception | RuntimeException |
| Usually Caused By | External resources | Programming mistakes |

---

# Real-World Examples

## Checked Exception

Reading a file.

```java
FileReader reader = new FileReader("student.txt");
```

The file may not exist.

The compiler requires proper handling.

---

## Unchecked Exception

```java
int marks[] = {80, 90};

System.out.println(marks[5]);
```

Output

```text
Exception in thread "main"
java.lang.ArrayIndexOutOfBoundsException
```

This is a programming error.

The compiler cannot determine the mistake beforehand.

---

# Why Doesn't Java Check Unchecked Exceptions?

Programming mistakes like:

- Dividing by zero
- Accessing invalid array indexes
- Using null references

depend on runtime data.

The compiler cannot predict these situations.

Therefore, Java allows them to occur during execution.

---

# When Should We Handle Them?

### Checked Exceptions

Always handle them or declare them using `throws`.

---

### Unchecked Exceptions

Fix the programming mistake whenever possible.

Use exception handling only when recovery is meaningful.

---

# Best Practices

- Always handle checked exceptions appropriately.
- Avoid ignoring checked exceptions.
- Prevent unchecked exceptions by writing correct logic.
- Catch specific exception types instead of generic `Exception`.
- Validate user input before processing.

---

# Common Mistakes

❌ Treating checked and unchecked exceptions the same way.

❌ Catching every exception without understanding the cause.

❌ Ignoring checked exceptions using empty catch blocks.

```java
catch(Exception e){

}
```

❌ Using exceptions as normal control flow.

---

# Interview Questions

### 1. What is the difference between checked and unchecked exceptions?

Checked exceptions are verified by the compiler.

Unchecked exceptions occur during runtime and are not checked by the compiler.

---

### 2. Which class is the parent of unchecked exceptions?

`RuntimeException`

---

### 3. Which class is the parent of checked exceptions?

`Exception` (excluding `RuntimeException` and its subclasses).

---

### 4. Can checked exceptions occur at runtime?

Yes.

They occur at runtime, but the compiler checks whether they are properly handled before the program is compiled.

---

### 5. Which type of exception is generally caused by programming mistakes?

Unchecked exceptions.

---

# Summary

- Java divides exceptions into **Checked** and **Unchecked** exceptions.
- Checked exceptions are verified by the compiler.
- Unchecked exceptions are subclasses of `RuntimeException`.
- Checked exceptions usually involve external resources.
- Unchecked exceptions usually result from programming errors.
- Understanding this distinction helps you write reliable and maintainable Java applications.

---

## Related Programs

- `20_CheckedExceptionExample.java`
- `21_UncheckedExceptionExample.java`
- `22_FileHandlingCheckedException.java`
- `23_ArrayIndexException.java`
- `24_CheckedVsUncheckedComparison.java`

---

## Next Chapter

➡️ **08_CustomExceptions.md**
````
