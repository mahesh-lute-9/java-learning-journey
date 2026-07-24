
# Exception Hierarchy

## Overview

Every exception or error in Java is represented as an object.

All exceptions and errors inherit from a common root class called **`Throwable`**.

Understanding the Exception Hierarchy is important because it helps you:

- Choose the correct exception to handle.
- Write proper `catch` blocks.
- Differentiate between recoverable and unrecoverable problems.
- Understand how exception propagation works.

---

# Java Exception Hierarchy

```text
                           Object
                              │
                              ▼
                         Throwable
                         /        \
                        /          \
                   Error        Exception
                                   │
                ┌──────────────────┼──────────────────┐
                │                  │                  │
        RuntimeException      IOException      SQLException
                │
        ┌───────┼────────┬──────────────┐
        │       │        │              │
ArithmeticException
NullPointerException
ArrayIndexOutOfBoundsException
ClassCastException
NumberFormatException
...
```

---

# Throwable

`Throwable` is the root class of Java's exception-handling mechanism.

Every object that can be thrown using the `throw` statement is a subclass of `Throwable`.

It has two direct subclasses:

- `Error`
- `Exception`

---

# Error

An **Error** represents serious problems that usually occur due to the JVM or system environment.

Applications generally should **not** try to recover from Errors.

Examples include:

- OutOfMemoryError
- StackOverflowError
- VirtualMachineError
- AssertionError

Example:

```java
public class Main {
    public static void main(String[] args) {

        throw new StackOverflowError();

    }
}
```

Although possible, manually throwing an `Error` is rarely useful in real-world applications.

---

## Common Errors

| Error | Description |
|-------|-------------|
| OutOfMemoryError | JVM cannot allocate more memory. |
| StackOverflowError | Infinite recursion exhausts the call stack. |
| AssertionError | Assertion statement fails. |
| VirtualMachineError | Serious JVM failure. |

---

# Exception

An **Exception** represents problems that an application can detect and handle.

These are the exceptions developers normally work with.

Examples include:

- ArithmeticException
- IOException
- SQLException
- FileNotFoundException
- NullPointerException

---

# Types of Exceptions

Java divides exceptions into two major categories.

```text
Exception
     │
     ├──────────────┐
     │              │
Checked      Unchecked
Exceptions    Exceptions
```

These will be discussed in detail in later chapters.

---

# Checked Exceptions

Checked Exceptions are checked by the compiler.

The compiler forces the programmer to either:

- Handle the exception using `try-catch`, or
- Declare it using `throws`.

Example:

```java
FileReader reader = new FileReader("data.txt");
```

If the file does not exist, the compiler requires proper handling.

Common Checked Exceptions:

- IOException
- FileNotFoundException
- SQLException
- InterruptedException
- ClassNotFoundException

---

# Unchecked Exceptions

Unchecked Exceptions occur during program execution.

The compiler does not force you to handle them.

Most of these exceptions occur because of programming mistakes.

Examples:

- ArithmeticException
- NullPointerException
- ArrayIndexOutOfBoundsException
- NumberFormatException
- IllegalArgumentException

Example:

```java
int result = 10 / 0;
```

Output

```text
Exception in thread "main"
java.lang.ArithmeticException: / by zero
```

---

# RuntimeException

`RuntimeException` is the parent class of all unchecked exceptions.

Many commonly encountered exceptions inherit from it.

Examples:

```text
RuntimeException
       │
       ├── ArithmeticException
       ├── NullPointerException
       ├── NumberFormatException
       ├── ClassCastException
       ├── IllegalArgumentException
       ├── IndexOutOfBoundsException
       └── ArrayIndexOutOfBoundsException
```

---

# Why This Hierarchy Matters

Suppose you write:

```java
catch (Exception e)
```

This catches almost every application exception.

However,

```java
catch (ArithmeticException e)
```

only catches arithmetic-related exceptions.

Choosing the appropriate exception makes programs easier to debug and maintain.

---

# Exception Hierarchy Flow

```text
Problem Occurs
      │
      ▼
JVM Creates Exception Object
      │
      ▼
Exception Object Belongs To?
      │
      ├── Error
      │      │
      │      └── Usually Not Handled
      │
      └── Exception
              │
              ├── Checked Exception
              │
              └── RuntimeException
```

---

# Real-World Examples

| Situation | Exception |
|-----------|-----------|
| Divide by zero | ArithmeticException |
| Calling a method on `null` | NullPointerException |
| Invalid array index | ArrayIndexOutOfBoundsException |
| Invalid string to integer conversion | NumberFormatException |
| File not found | FileNotFoundException |
| Database connection issue | SQLException |

---

# Best Practices

- Catch the most specific exception possible.
- Avoid catching `Exception` unless necessary.
- Never catch `Throwable`.
- Do not use exceptions for normal program flow.
- Understand whether an exception is checked or unchecked before handling it.

---

# Common Mistakes

❌ Assuming every problem is an Exception.

❌ Catching `Throwable`.

❌ Catching overly generic exceptions without reason.

❌ Ignoring compiler warnings for checked exceptions.

---

# Summary

- `Throwable` is the root of Java's exception hierarchy.
- `Throwable` has two subclasses: `Error` and `Exception`.
- `Error` represents serious JVM or system-level failures.
- `Exception` represents recoverable problems that applications can handle.
- Exceptions are divided into **Checked** and **Unchecked** exceptions.
- `RuntimeException` is the parent class of most unchecked exceptions.
- Understanding the hierarchy helps in writing better exception handling code.

---

## Next Chapter

➡️ **03_Try_and_Catch.md**
