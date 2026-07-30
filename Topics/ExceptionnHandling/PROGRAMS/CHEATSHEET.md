# ☕ Java Exception Handling — Cheatsheet

> [!TIP]
> Use this cheatsheet for **quick revision**.
> For detailed explanations, examples, internals, and interview preparation, refer to the files inside the [`NOTES/`](./NOTES/) directory.

---

## 📑 Table of Contents

1. [Exception Hierarchy](#1-exception-hierarchy)
2. [Basic try-catch](#2-basic-try-catch)
3. [Multiple catch Blocks](#3-multiple-catch-blocks)
4. [finally](#4-finally)
5. [throw](#5-throw)
6. [throws](#6-throws)
7. [throw vs throws](#7-throw-vs-throws)
8. [Checked Exceptions](#8-checked-exceptions)
9. [Unchecked Exceptions](#9-unchecked-exceptions)
10. [Checked vs Unchecked](#10-checked-vs-unchecked)
11. [Custom Checked Exception](#11-custom-checked-exception)
12. [Custom Unchecked Exception](#12-custom-unchecked-exception)
13. [Exception Propagation](#13-exception-propagation)
14. [Try-with-Resources](#14-try-with-resources)
15. [Multiple Resources](#15-multiple-resources)
16. [Common Exception Methods](#16-common-exception-methods)
17. [Exception Matching](#17-exception-matching)
18. [Custom Exception Decision](#18-custom-exception-decision)
19. [Common Mistakes](#19-common-mistakes)
20. [Best Practices](#20-best-practices)
21. [Quick Syntax Revision](#21-quick-syntax-revision)
22. [One-Line Revision](#22-one-line-revision)
23. [30-Second Revision](#-30-second-revision)

---

## 1. Exception Hierarchy

```text
Object
  │
  └── Throwable
        │
        ├── Error
        │     ├── StackOverflowError
        │     └── OutOfMemoryError
        │
        └── Exception
              │
              ├── IOException
              │     └── FileNotFoundException
              │
              ├── SQLException
              │
              └── RuntimeException
                    ├── ArithmeticException
                    ├── NullPointerException
                    ├── IllegalArgumentException
                    │     └── NumberFormatException
                    └── IndexOutOfBoundsException
                          ├── ArrayIndexOutOfBoundsException
                          └── StringIndexOutOfBoundsException
```

### 🧠 Remember

```text
Throwable
├── Error       → Serious system/JVM problems
└── Exception   → Conditions applications may handle
```

> [!NOTE]
> Both `Error` and `Exception` inherit from `Throwable`.

---

## 2. Basic try-catch

### Syntax

```java
try {
    // Risky code
} catch (ExceptionType e) {
    // Handle exception
}
```

### Example

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println(e.getMessage());
}
```

### 🔄 Execution Flow

```text
Exception Occurs
       ↓
Remaining try statements skipped
       ↓
Matching catch searched
       ↓
catch executes
       ↓
Program continues
```

> [!NOTE]
> If no exception occurs inside `try`, the `catch` block is skipped.

---

## 3. Multiple catch Blocks

```java
try {
    // Risky code
} catch (ArithmeticException e) {
    // Handle arithmetic problem
} catch (NullPointerException e) {
    // Handle null problem
} catch (Exception e) {
    // General handler
}
```

> [!IMPORTANT]
> Always place **specific exceptions before general exceptions**.

### ✅ Correct

```java
catch (ArithmeticException e) {
    // Specific exception
}

catch (Exception e) {
    // General exception
}
```

### ❌ Incorrect

```java
catch (Exception e) {
    // General exception
}

catch (ArithmeticException e) {
    // Unreachable
}
```

The second `catch` is unreachable because `Exception` already handles `ArithmeticException`.

---

## 4. finally

The `finally` block is normally used for cleanup logic.

```java
try {
    // Risky code
} catch (Exception e) {
    // Handle exception
} finally {
    // Cleanup
}
```

### Execution

```text
try
 ↓
catch (if required)
 ↓
finally
 ↓
continue
```

`finally` normally executes whether an exception occurs or not.

### Typical Uses

* Cleanup operations
* Releasing resources
* Closing connections

> [!WARNING]
> Avoid `return` statements inside `finally`.
>
> They can override another return value or suppress a pending exception.

---

## 5. throw

`throw` is used to **explicitly throw an exception object**.

### Syntax

```java
throw new IllegalArgumentException(
    "Age cannot be negative."
);
```

### Example

```java
if (age < 0) {
    throw new IllegalArgumentException(
        "Age cannot be negative."
    );
}
```

### 🧠 Remember

```text
throw = actually throw an exception
```

---

## 6. throws

`throws` is used in a **method declaration** to specify exceptions that may be propagated to the caller.

```java
void readFile() throws IOException {
    // Code
}
```

### Multiple Exceptions

```java
void process() throws IOException, SQLException {
    // Code
}
```

### 🧠 Remember

```text
throws = declare possible exception types
```

---

## 7. throw vs throws

| `throw`                        | `throws`                     |
| ------------------------------ | ---------------------------- |
| Throws an exception            | Declares possible exceptions |
| Used inside a method/block     | Used in method signature     |
| Works with an exception object | Works with exception types   |
| One object per statement       | Can declare multiple types   |

### Example

```java
void process() throws IOException {
    throw new IOException("Unable to process.");
}
```

> [!TIP]
> Think of it this way:
>
> **`throw` → action**
> **`throws` → declaration**

---

## 8. Checked Exceptions

Checked exceptions are **enforced by the compiler**.

They must be:

```text
Handled
   OR
Declared
```

### Option 1 — Handle

```java
try {
    // Code that may throw checked exception
} catch (IOException e) {
    // Handle exception
}
```

### Option 2 — Declare

```java
void readData() throws IOException {
    // Code
}
```

### Common Checked Exceptions

```text
IOException
FileNotFoundException
SQLException
ClassNotFoundException
InterruptedException
```

> [!IMPORTANT]
> If a checked exception is neither handled nor declared, the program will fail to compile.

---

## 9. Unchecked Exceptions

Unchecked exceptions are subclasses of:

```text
RuntimeException
```

The compiler does **not** require them to be handled or declared.

### Common Examples

| Exception                        | Typical Cause              |
| -------------------------------- | -------------------------- |
| `ArithmeticException`            | Integer division by zero   |
| `NullPointerException`           | Dereferencing `null`       |
| `ArrayIndexOutOfBoundsException` | Invalid array index        |
| `NumberFormatException`          | Invalid numeric conversion |
| `IllegalArgumentException`       | Invalid method argument    |
| `ClassCastException`             | Invalid type cast          |

---

## 10. Checked vs Unchecked

| Checked                                | Unchecked                      |
| -------------------------------------- | ------------------------------ |
| Compiler enforces handling/declaration | Compiler does not enforce it   |
| Usually extends `Exception` directly   | Extends `RuntimeException`     |
| Must handle or declare                 | Handling is optional           |
| Example: `IOException`                 | Example: `ArithmeticException` |

> [!NOTE]
> Both checked and unchecked exceptions occur at **runtime**.
>
> **Checked** refers to **compiler enforcement**, not when the exception occurs.

---

## 11. Custom Checked Exception

Create a checked exception by extending `Exception`.

```java
class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }
}
```

### Usage

```java
void validateAge(int age) throws InvalidAgeException {

    if (age < 18) {
        throw new InvalidAgeException(
            "Age must be 18 or above."
        );
    }
}
```

### 🧠 Remember

```text
Custom Checked Exception
        ↓
extends Exception
        ↓
Handle OR Declare
```

---

## 12. Custom Unchecked Exception

Create an unchecked exception by extending `RuntimeException`.

```java
class InvalidAgeException extends RuntimeException {

    public InvalidAgeException(String message) {
        super(message);
    }
}
```

### Usage

```java
if (age < 18) {
    throw new InvalidAgeException(
        "Age must be 18 or above."
    );
}
```

No `throws` declaration is required by the compiler.

### 🧠 Remember

```text
Custom Unchecked Exception
        ↓
extends RuntimeException
        ↓
Compiler does not force handling
```

---

## 13. Exception Propagation

Consider the following call chain:

```text
methodOne()
    ↓
methodTwo()
    ↓
methodThree()
    ↓
Exception
```

If `methodThree()` does not handle the exception:

```text
methodThree()
     ↑
methodTwo()
     ↑
methodOne()
     ↑
main()
     ↑
JVM
```

The exception moves **up the call stack** until a compatible handler is found.

> [!WARNING]
> If nobody handles the exception, the JVM's default exception handling terminates the thread and prints exception information.

---

## 14. Try-with-Resources

Introduced in **Java 7**.

```java
try (
    BufferedReader reader =
        new BufferedReader(
            new FileReader("data.txt")
        )
) {

    // Use resource

} catch (IOException e) {

    System.out.println(e.getMessage());
}
```

### Requirement

The resource must implement:

```java
AutoCloseable
```

or an interface derived from it, such as:

```java
Closeable
```

### Benefits

* ✅ Automatic `close()`
* ✅ Less boilerplate
* ✅ Safer cleanup
* ✅ Reduced resource leaks

---

## 15. Multiple Resources

Multiple resources can be declared inside the try-with-resources statement.

```java
try (
    FileReader reader = new FileReader("input.txt");
    BufferedReader buffer = new BufferedReader(reader)
) {

    // Use resources
}
```

### Closing Order

Resources are closed in **reverse declaration order**.

```text
Declared:

FileReader
    ↓
BufferedReader


Closed:

BufferedReader
    ↓
FileReader
```

---

## 16. Common Exception Methods

Assume:

```java
catch (Exception e) {
    // ...
}
```

### `getMessage()`

```java
e.getMessage();
```

Returns the **detail message** of the exception.

---

### `toString()`

```java
e.toString();
```

Returns the exception class name plus its detail message when available.

---

### `printStackTrace()`

```java
e.printStackTrace();
```

Prints the stack trace, which helps identify:

* Where the exception originated
* Which methods were called
* How execution reached the failure

---

## 17. Exception Matching

Suppose Java throws:

```text
ArithmeticException
```

Its hierarchy is:

```text
ArithmeticException
        ↑
RuntimeException
        ↑
Exception
        ↑
Throwable
```

Therefore, compatible handlers include:

```java
catch (ArithmeticException e)
```

```java
catch (RuntimeException e)
```

```java
catch (Exception e)
```

> [!IMPORTANT]
> Java executes only the **first compatible `catch` block**.

---

## 18. Custom Exception Decision

```text
Need application-specific exception?
              │
              ▼
             Yes
              │
              ▼
Should callers be forced by the compiler
      to handle or declare it?
        │               │
       Yes              No
        │               │
        ▼               ▼
   Exception       RuntimeException
        │               │
        ▼               ▼
     Checked          Unchecked
```

### Quick Rule

| Requirement                    | Extend             |
| ------------------------------ | ------------------ |
| Caller must handle/declare     | `Exception`        |
| Caller is not forced to handle | `RuntimeException` |

---

## 19. Common Mistakes

### ❌ Empty catch

```java
catch (Exception e) {

}
```

Avoid silently swallowing exceptions.

---

### ❌ Catching Everything

```java
catch (Exception e) {
    // ...
}
```

Prefer a more specific type when you can handle the failure meaningfully.

---

### ❌ Generic throw

```java
throw new Exception("Error");
```

Prefer an exception that describes the actual failure.

For example:

```java
throw new IllegalArgumentException(
    "Age cannot be negative."
);
```

---

### ❌ Large try Block

```java
try {

    // Huge amount of unrelated code

}
```

Keep `try` focused on operations whose failures you actually intend to handle.

---

### ❌ Manual Resource Handling When Unnecessary

Instead of manually closing supported resources, prefer:

```java
try (Resource resource = ...) {

}
```

when the resource implements `AutoCloseable`.

---

## 20. Best Practices

* ✅ Catch specific exceptions.
* ✅ Provide meaningful exception messages.
* ✅ Never silently ignore exceptions.
* ✅ Keep `try` blocks focused.
* ✅ Use try-with-resources for `AutoCloseable` resources.
* ✅ Use custom exceptions for meaningful domain-specific failures.
* ✅ Validate before modifying important object state.
* ✅ Handle exceptions where meaningful recovery or reporting is possible.
* ❌ Avoid catching `Throwable` in normal application code.
* ❌ Avoid returning from `finally`.
* ❌ Avoid generic exceptions when a specific type better describes the problem.

---

## 21. Quick Syntax Revision

### try-catch

```java
try {

} catch (Exception e) {

}
```

### try-catch-finally

```java
try {

} catch (Exception e) {

} finally {

}
```

### throw

```java
throw new IllegalArgumentException("Invalid value");
```

### throws

```java
void method() throws IOException {

}
```

### Custom Checked Exception

```java
class MyException extends Exception {

}
```

### Custom Unchecked Exception

```java
class MyException extends RuntimeException {

}
```

### Try-with-Resources

```java
try (Resource resource = ...) {

}
```

---

## 22. One-Line Revision

| Concept             | Remember                                 |
| ------------------- | ---------------------------------------- |
| `Throwable`         | Root of Java's throwable hierarchy       |
| `Error`             | Serious JVM/system-level problem         |
| `Exception`         | Conditions applications may handle       |
| `RuntimeException`  | Base class for many unchecked exceptions |
| `try`               | Contains risky code                      |
| `catch`             | Handles compatible exceptions            |
| `finally`           | Cleanup/finalization logic               |
| `throw`             | Explicitly throws an exception object    |
| `throws`            | Declares possible exception types        |
| Checked             | Must handle or declare                   |
| Unchecked           | Compiler does not require handling       |
| Custom Exception    | Application/domain-specific exception    |
| Propagation         | Exception moves up the call stack        |
| `AutoCloseable`     | Enables try-with-resources               |
| `getMessage()`      | Gets detail message                      |
| `printStackTrace()` | Prints the stack trace                   |

---

# ⚡ 30-Second Revision

```text
Throwable
├── Error
└── Exception
     └── RuntimeException
```

```text
try      → risky code
catch    → handle exception
finally  → cleanup
throw    → throw exception
throws   → declare exception
```

### Checked

```text
Exception
   ↓
Checked
   ↓
Handle OR Declare
```

### Unchecked

```text
RuntimeException
   ↓
Unchecked
   ↓
Compiler does not force handling
```

### Custom Exceptions

```text
Custom Checked   → extends Exception

Custom Unchecked → extends RuntimeException
```

### Resource Management

```text
Try-with-Resources
        ↓
AutoCloseable
        ↓
Automatic close()
```

---

> [!IMPORTANT]
>
> ### 🎯 Core Mental Model
>
> Don't memorize Exception Handling as isolated syntax. Understand the flow:
>
> **Exception occurs → normal flow breaks → handler is searched → exception is handled or propagated → cleanup runs where applicable → execution either continues or terminates.**

---

## 🔗 Related Notes

For deeper study, refer to the [`NOTES/`](./NOTES/) directory.

Topics to explore in detail:

* Exception hierarchy
* Checked vs unchecked exceptions
* `try`, `catch`, and `finally`
* `throw` vs `throws`
* Exception propagation
* Custom exceptions
* Try-with-resources
* `AutoCloseable`
* Exception handling best practices
* JVM exception handling internals
* Interview questions

---

<p align="center">
  <b>☕ Java Exception Handling — Quick Revision Cheatsheet</b>
</p>

<p align="center">
  Understand the flow. Don't just memorize the syntax.
</p>
