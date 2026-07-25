# Common Mistakes

> [!WARNING]
> Many runtime problems in Java are caused not by the language itself, but by incorrect exception-handling practices. Understanding these mistakes will help you write cleaner, safer, and more maintainable applications.

---

# 📖 Overview

Exception handling is intended to improve the reliability of software.

However, poor exception handling can:

* Hide real problems
* Make debugging difficult
* Cause resource leaks
* Produce confusing error messages
* Reduce application maintainability

This chapter covers the most common mistakes developers make and how to avoid them.

---

# ❌ 1. Catching Generic `Exception`

One of the most common mistakes is catching every exception using `Exception`.

### Bad Practice

```java
try {

    int result = 10 / 0;

}
catch (Exception e) {

    System.out.println("Error");

}
```

### Better Practice

```java
try {

    int result = 10 / 0;

}
catch (ArithmeticException e) {

    System.out.println("Cannot divide by zero.");

}
```

> [!TIP]
> Catch the most specific exception possible.

---

# ❌ 2. Leaving Catch Blocks Empty

Ignoring exceptions hides valuable debugging information.

### Bad Practice

```java
try {

    // Code

}
catch (Exception e) {

}
```

The program silently ignores the exception.

### Better Practice

```java
catch (Exception e) {

    System.out.println(e.getMessage());

}
```

---

# ❌ 3. Using Exceptions for Normal Program Flow

Exceptions should represent **exceptional situations**, not regular logic.

### Bad Practice

```java
try {

    int number = Integer.parseInt(input);

}
catch (NumberFormatException e) {

    number = 0;

}
```

### Better Practice

Validate the input before processing whenever possible.

---

# ❌ 4. Throwing Generic Exceptions

Throwing a generic exception makes debugging difficult.

### Bad Practice

```java
throw new Exception("Error");
```

### Better Practice

```java
throw new IllegalArgumentException(
        "Age cannot be negative.");
```

---

# ❌ 5. Ignoring Exception Messages

Throwing exceptions without meaningful messages makes troubleshooting harder.

### Bad Practice

```java
throw new IllegalArgumentException();
```

### Better Practice

```java
throw new IllegalArgumentException(
        "Quantity cannot be less than zero.");
```

---

# ❌ 6. Writing Large try Blocks

Large `try` blocks make it difficult to determine which statement caused the exception.

### Bad Practice

```java
try {

    // Hundreds of lines of code

}
catch (Exception e) {

}
```

### Better Practice

```java
try {

    readFile();

}
catch (IOException e) {

}
```

> [!NOTE]
> Keep the `try` block focused on only the statements that may throw an exception.

---

# ❌ 7. Forgetting to Close Resources

Failing to close files, database connections, or streams can lead to resource leaks.

### Bad Practice

```java
FileReader reader = new FileReader("data.txt");

// Read file

// Forgot to close reader
```

### Better Practice

```java
try (FileReader reader =
        new FileReader("data.txt")) {

    // Read file

}
```

---

# ❌ 8. Catching `Throwable`

`Throwable` includes both `Exception` and `Error`.

### Bad Practice

```java
catch (Throwable t) {

}
```

This may unintentionally catch serious JVM errors.

### Better Practice

Catch only the expected exception type.

---

# ❌ 9. Swallowing Exceptions

Sometimes developers catch an exception and do nothing.

```java
catch (IOException e) {

}
```

This is called **swallowing an exception**.

It makes debugging extremely difficult because the actual problem is hidden.

---

# ❌ 10. Creating Unnecessary Custom Exceptions

Not every validation requires a custom exception.

### Bad Practice

Creating dozens of custom exceptions for simple validations.

### Better Practice

Use built-in exceptions whenever they clearly describe the problem.

Create custom exceptions only for meaningful business rules.

---

# 📋 Common Mistakes Summary

| ❌ Mistake                        | ✅ Recommended Practice         |
| -------------------------------- | ------------------------------ |
| Catching `Exception` everywhere  | Catch specific exceptions      |
| Empty catch blocks               | Handle or log the exception    |
| Throwing generic exceptions      | Throw specific exceptions      |
| Large `try` blocks               | Keep them small                |
| Forgetting resource cleanup      | Use Try-with-Resources         |
| Catching `Throwable`             | Catch expected exceptions only |
| Ignoring exception messages      | Provide meaningful messages    |
| Swallowing exceptions            | Log or handle them properly    |
| Using exceptions for normal flow | Use conditional statements     |
| Unnecessary custom exceptions    | Create only when needed        |

---

# 💼 Real-World Impact

Poor exception handling can result in:

* Application crashes
* Memory leaks
* Database connection leaks
* Hidden production bugs
* Difficult debugging
* Poor user experience

Good exception handling improves software reliability and maintainability.

---

# 💡 Interview Questions

### 1. Why are empty catch blocks discouraged?

Because they silently ignore exceptions and make debugging difficult.

---

### 2. Why should `Throwable` not be caught?

Because it also includes serious JVM `Error`s that applications generally should not handle.

---

### 3. Why should `try` blocks be kept small?

Small `try` blocks make it easier to identify the exact statement that caused an exception.

---

### 4. What is exception swallowing?

It is catching an exception and doing nothing with it, effectively hiding the error.

---

### 5. When should custom exceptions be created?

Only when built-in exceptions cannot clearly represent a business-specific problem.

---

# 📚 Summary

* Avoid catching generic exceptions unnecessarily.
* Never leave catch blocks empty.
* Do not use exceptions for normal program flow.
* Keep `try` blocks small.
* Always provide meaningful exception messages.
* Prefer Try-with-Resources for resource management.
* Catch only expected exception types.
* Follow consistent exception-handling practices to write reliable Java applications.

---

## 📂 Related Programs

* `40_GenericCatchExample.java`
* `41_EmptyCatchBlock.java`
* `42_SwallowingExceptions.java`
* `43_ResourceLeakExample.java`
* `44_ExceptionHandlingMistakes.java`

---

## ➡️ Next Chapter

**12_InterviewQuestions.md**
