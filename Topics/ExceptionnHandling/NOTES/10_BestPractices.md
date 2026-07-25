# Best Practices

> [!IMPORTANT]
> Exception handling is not just about preventing program crashes—it's about writing clean, maintainable, and reliable software. Following best practices helps build applications that are easier to debug, maintain, and scale.

---

# 📖 Overview

Poor exception handling can make applications difficult to understand and debug.

Common problems include:

* Hiding actual errors
* Catching unnecessary exceptions
* Swallowing exceptions silently
* Resource leaks
* Difficult maintenance

By following best practices, your code becomes cleaner and more predictable.

---

# ✅ 1. Catch Specific Exceptions

Always catch the most specific exception possible.

### ❌ Bad

```java
try {

    int result = 10 / 0;

}
catch (Exception e) {

    System.out.println("Something went wrong.");

}
```

### ✅ Good

```java
try {

    int result = 10 / 0;

}
catch (ArithmeticException e) {

    System.out.println("Cannot divide by zero.");

}
```

### Why?

Specific exceptions make debugging much easier.

---

# ✅ 2. Never Leave a Catch Block Empty

Ignoring an exception makes debugging extremely difficult.

### ❌ Bad

```java
try {

    // Code

}
catch (Exception e) {

}
```

### ✅ Good

```java
catch (Exception e) {

    System.out.println(e.getMessage());

}
```

---

# ✅ 3. Provide Meaningful Exception Messages

Always provide a clear and descriptive message.

### ❌ Bad

```java
throw new IllegalArgumentException();
```

### ✅ Good

```java
throw new IllegalArgumentException(
        "Age cannot be negative.");
```

Meaningful messages make debugging much easier.

---

# ✅ 4. Do Not Use Exceptions for Normal Program Flow

Exceptions should represent exceptional situations.

### ❌ Bad

```java
try {

    int number = Integer.parseInt(input);

}
catch (Exception e) {

    number = 0;

}
```

Instead, validate the input whenever possible.

---

# ✅ 5. Keep try Blocks Small

A small `try` block makes it easier to identify where an exception occurred.

### ❌ Bad

```java
try {

    // 100 lines of code

}
catch (Exception e) {

}
```

### ✅ Good

```java
try {

    readFile();

}
catch (IOException e) {

}
```

---

# ✅ 6. Use finally or Try-with-Resources for Cleanup

Always release external resources.

### Preferred (Java 7+)

```java
try (FileReader reader =
        new FileReader("data.txt")) {

    // Read file

}
```

Avoid manually closing resources whenever possible.

---

# ✅ 7. Throw Appropriate Exceptions

Choose the exception that best describes the problem.

### ❌ Bad

```java
throw new Exception();
```

### ✅ Good

```java
throw new IllegalArgumentException(
        "Quantity cannot be negative.");
```

---

# ✅ 8. Create Custom Exceptions for Business Rules

Use custom exceptions when built-in exceptions do not clearly describe the problem.

Example:

* `InvalidAgeException`
* `InsufficientBalanceException`
* `InvalidOrderException`

This improves readability and maintainability.

---

# ✅ 9. Do Not Catch Throwable

Avoid catching `Throwable`.

```java
catch (Throwable t) {

}
```

`Throwable` includes both **Errors** and **Exceptions**.

Serious JVM errors should generally not be handled by applications.

---

# ✅ 10. Document Exceptions

If a method can throw important checked exceptions, declare them using `throws` and document their purpose.

Example

```java
public void readData()
        throws IOException {

}
```

This makes the API easier to understand.

---

# 🚫 Common Anti-Patterns

| ❌ Bad Practice                      | ✅ Better Approach              |
| ----------------------------------- | ------------------------------ |
| Catching `Exception` everywhere     | Catch specific exceptions      |
| Empty catch blocks                  | Log or handle the exception    |
| Throwing generic `Exception`        | Throw a specific exception     |
| Ignoring resource cleanup           | Use Try-with-Resources         |
| Using exceptions as loop conditions | Use proper conditional logic   |
| Catching `Throwable`                | Catch only expected exceptions |

---

# 💼 Real-World Recommendations

Modern Java frameworks like:

* Spring Boot
* Hibernate
* Jakarta EE
* JDBC

follow these principles extensively.

Writing code in this style makes it easier to work on enterprise applications.

---

# 💡 Interview Questions

### 1. Why should we catch specific exceptions?

Because they make debugging easier and prevent unrelated exceptions from being accidentally handled.

---

### 2. Why are empty catch blocks discouraged?

They silently hide errors, making applications difficult to debug.

---

### 3. Why should try blocks be kept small?

Small try blocks make it easier to locate the exact statement that caused an exception.

---

### 4. Why is Try-with-Resources preferred?

It automatically closes resources and reduces boilerplate code.

---

### 5. Why shouldn't we catch `Throwable`?

Because it also catches serious JVM `Error`s, which applications generally should not handle.

---

# 📚 Summary

* Catch specific exceptions whenever possible.
* Never ignore exceptions.
* Keep `try` blocks small.
* Provide meaningful exception messages.
* Prefer Try-with-Resources for resource cleanup.
* Use custom exceptions for business-specific problems.
* Avoid catching `Throwable`.
* Follow consistent exception-handling practices to build reliable and maintainable Java applications.

---

## 📂 Related Programs

* `35_SpecificVsGenericCatch.java`
* `36_EmptyCatchExample.java`
* `37_CustomExceptionExample.java`
* `38_TryWithResourcesExample.java`
* `39_ExceptionHandlingBestPractices.java`

---

## ➡️ Next Chapter

**11_CommonMistakes.md**
