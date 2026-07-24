
# finally Block

## Overview

The `finally` block is used to execute code that must run regardless of whether an exception occurs or not.

It is commonly used for resource cleanup, such as:

- Closing files
- Closing database connections
- Releasing network sockets
- Releasing locks
- Cleaning up resources

The `finally` block executes after the `try` and `catch` blocks, making it the ideal place for cleanup operations.

---

# Why do we need finally?

Consider a program that opens a file.

```java
FileReader reader = new FileReader("data.txt");
```

If an exception occurs before the file is closed, the file remains open, leading to resource leaks.

Using `finally` ensures that cleanup code executes even if an exception occurs.

---

# Syntax

```java
try {
    // Risky code
}
catch (ExceptionType e) {
    // Handle exception
}
finally {
    // Cleanup code
}
```

---

# Execution Flow

```text
                 Start
                   │
                   ▼
            Execute try Block
                   │
         ┌─────────┴─────────┐
         │                   │
         ▼                   ▼
 No Exception          Exception Occurs
         │                   │
         ▼                   ▼
 Skip catch         Execute Matching catch
         │                   │
         └─────────┬─────────┘
                   ▼
           Execute finally
                   │
                   ▼
          Continue Program
```

---

# Example 1 — No Exception

```java
public class Main {

    public static void main(String[] args) {

        try {

            System.out.println("Inside try");

        }

        catch (ArithmeticException e) {

            System.out.println("Inside catch");

        }

        finally {

            System.out.println("Inside finally");

        }

        System.out.println("Program Finished");

    }

}
```

Output

```text
Inside try
Inside finally
Program Finished
```

### Explanation

- No exception occurs.
- The catch block is skipped.
- The finally block still executes.

---

# Example 2 — Exception Occurs

```java
public class Main {

    public static void main(String[] args) {

        try {

            int result = 10 / 0;

        }

        catch (ArithmeticException e) {

            System.out.println("Exception Handled");

        }

        finally {

            System.out.println("Cleanup Completed");

        }

        System.out.println("Program Finished");

    }

}
```

Output

```text
Exception Handled
Cleanup Completed
Program Finished
```

### Explanation

1. Exception occurs inside the try block.
2. JVM transfers control to the matching catch block.
3. The catch block executes.
4. The finally block executes.
5. Program continues normally.

---

# Example 3 — Return Statement

```java
public class Main {

    public static void main(String[] args) {

        System.out.println(test());

    }

    static int test() {

        try {

            return 10;

        }

        finally {

            System.out.println("Finally Executed");

        }

    }

}
```

Output

```text
Finally Executed
10
```

### Explanation

Even though the `try` block contains a `return` statement, the `finally` block executes before the method actually returns.

---

# Example 4 — Exception Not Handled

```java
public class Main {

    public static void main(String[] args) {

        try {

            String str = null;

            System.out.println(str.length());

        }

        finally {

            System.out.println("Finally Executed");

        }

    }

}
```

Output

```text
Finally Executed

Exception in thread "main"
java.lang.NullPointerException
```

### Explanation

- No catch block is present.
- The exception remains unhandled.
- The finally block executes.
- The JVM then terminates the program.

---

# When finally Does NOT Execute

Although `finally` almost always executes, there are a few exceptional situations where it may not.

Examples include:

- JVM is terminated using `System.exit()`
- JVM crashes
- Power failure
- Operating system crash
- Forcefully terminating the process

Example

```java
try {

    System.out.println("Inside try");

    System.exit(0);

}

finally {

    System.out.println("Inside finally");

}
```

Output

```text
Inside try
```

The JVM shuts down immediately, so the `finally` block is never executed.

---

# Common Uses of finally

The `finally` block is commonly used for cleaning up resources.

Example

```java
FileReader reader = null;

try {

    reader = new FileReader("data.txt");

    // Read file

}

catch (IOException e) {

    System.out.println("Unable to read file.");

}

finally {

    if (reader != null) {

        reader.close();

    }

}
```

> **Note:** In modern Java, `try-with-resources` is preferred over manually closing resources. It is covered in a later chapter.

---

# Best Practices

- Use `finally` only for cleanup code.
- Keep the `finally` block short and simple.
- Avoid writing business logic inside `finally`.
- Prefer `try-with-resources` when working with `AutoCloseable` resources.

---

# Common Mistakes

❌ Writing important business logic inside `finally`.

❌ Throwing another exception from the `finally` block without understanding its impact.

❌ Assuming `finally` always executes, even after `System.exit()`.

❌ Performing lengthy operations inside `finally`.

---

# Interview Questions

### 1. Does the `finally` block always execute?

Almost always, except when the JVM terminates abruptly (e.g., `System.exit()`, JVM crash, power failure).

---

### 2. Can a `try` block exist without a `catch` block?

Yes.

A `try` block can be followed directly by a `finally` block.

```java
try {

    // Code

}
finally {

    // Cleanup

}
```

---

### 3. Will `finally` execute if there is a `return` statement?

Yes.

The `finally` block executes before the method returns.

---

### 4. Why is `finally` mainly used?

To release resources and perform cleanup operations regardless of whether an exception occurs.

---

# Key Takeaways

- The `finally` block executes after `try` and `catch`.
- It is primarily used for cleanup operations.
- It executes whether an exception occurs or not.
- It also executes before a method returns.
- It does **not** execute if the JVM terminates abruptly (e.g., `System.exit()`).

---

## Related Programs

- `05_FinallyWithoutException.java`
- `06_FinallyWithException.java`
- `07_FinallyWithReturn.java`
- `08_FinallyWithoutCatch.java`
- `09_SystemExitFinally.java`

---

## Next Chapter

➡️ **05_Throw_Keyword.md**
