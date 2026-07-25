# Interview Questions

> [!IMPORTANT]
> Exception Handling is one of the most frequently asked Java interview topics. Interviewers often focus on concepts, execution flow, and practical scenarios rather than syntax alone.

---

# 📖 Basic Questions

## 1. What is an Exception?

An **Exception** is an event that occurs during program execution and disrupts the normal flow of the program.

It is represented as an object in Java.

---

## 2. What is Exception Handling?

Exception Handling is the mechanism of detecting, handling, and recovering from runtime exceptions so that the program can continue execution gracefully.

---

## 3. What is the difference between an Error and an Exception?

| Error                                              | Exception                                      |
| -------------------------------------------------- | ---------------------------------------------- |
| Serious JVM/System problem                         | Recoverable application problem                |
| Generally not handled                              | Can be handled                                 |
| Examples: `OutOfMemoryError`, `StackOverflowError` | Examples: `IOException`, `ArithmeticException` |

---

## 4. What is the root class of all exceptions?

`Throwable`

Hierarchy

```text
Object
   │
Throwable
 ├── Error
 └── Exception
```

---

## 5. What is the difference between Checked and Unchecked Exceptions?

| Checked Exception           | Unchecked Exception        |
| --------------------------- | -------------------------- |
| Checked by compiler         | Not checked by compiler    |
| Must be handled or declared | Handling is optional       |
| Extends `Exception`         | Extends `RuntimeException` |

---

# 📖 try-catch Questions

## 6. What is the purpose of the `try` block?

The `try` block contains code that may throw an exception.

---

## 7. What is the purpose of the `catch` block?

The `catch` block handles an exception if a matching exception occurs.

---

## 8. Can a `try` block exist without a `catch` block?

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

## 9. Can a `catch` block exist without a `try` block?

❌ No.

Every `catch` block must immediately follow a `try` block.

---

## 10. Can multiple catch blocks be used?

✅ Yes.

```java
try {

}
catch (IOException e) {

}
catch (SQLException e) {

}
```

---

# 📖 finally Questions

## 11. What is the purpose of the `finally` block?

The `finally` block is mainly used for resource cleanup.

It executes whether an exception occurs or not.

---

## 12. Does `finally` always execute?

Almost always.

Exceptions include:

* `System.exit()`
* JVM crash
* Power failure
* Forceful JVM termination

---

## 13. Will `finally` execute after a `return` statement?

✅ Yes.

The `finally` block executes before the method actually returns.

---

# 📖 throw & throws Questions

## 14. What is the difference between `throw` and `throws`?

| `throw`                        | `throws`                                    |
| ------------------------------ | ------------------------------------------- |
| Explicitly throws an exception | Declares that a method may throw exceptions |
| Used inside a method           | Used in the method declaration              |
| Throws one exception object    | Can declare multiple exception types        |

---

## 15. Can we throw checked exceptions?

Yes.

They must be handled or declared using `throws`.

---

## 16. Can we throw custom exceptions?

Yes.

Custom exceptions are created by extending `Exception` or `RuntimeException`.

---

# 📖 Custom Exception Questions

## 17. What is a Custom Exception?

A user-defined exception created to represent business-specific errors.

Example:

* `InvalidAgeException`
* `InsufficientBalanceException`

---

## 18. Why do we create Custom Exceptions?

Because built-in exceptions cannot represent every business rule.

They improve readability and maintainability.

---

## 19. How do you create a Custom Exception?

```java
class InvalidAgeException
        extends Exception {

    public InvalidAgeException(String message) {

        super(message);

    }

}
```

---

# 📖 Try-with-Resources Questions

## 20. What is Try-with-Resources?

A Java 7 feature that automatically closes resources after use.

---

## 21. Which interface is required?

`AutoCloseable`

---

## 22. Which resources can be used?

Examples:

* FileReader
* BufferedReader
* Scanner
* Connection
* Statement
* ResultSet
* Socket

Any class implementing `AutoCloseable`.

---

## 23. In which order are resources closed?

Reverse order (LIFO).

---

# 📖 Conceptual Questions

## 24. What happens when an exception occurs?

1. Exception occurs.
2. JVM creates an exception object.
3. JVM searches for a matching `catch` block.
4. If found, it executes the `catch` block.
5. Otherwise, the program terminates.

---

## 25. What is Exception Propagation?

When a method does not handle an exception, it passes the exception to its caller.

This process continues up the call stack until the exception is handled or reaches the JVM.

---

## 26. What is Stack Trace?

A stack trace is the sequence of method calls leading to an exception.

It helps identify where the exception occurred.

---

## 27. Why shouldn't we catch `Throwable`?

Because it also catches serious JVM `Error`s that applications generally should not handle.

---

## 28. Why are empty catch blocks discouraged?

Because they silently ignore exceptions and make debugging difficult.

---

## 29. Why should we catch specific exceptions?

Specific exceptions:

* Improve readability
* Simplify debugging
* Prevent unrelated exceptions from being accidentally handled

---

## 30. Why is Try-with-Resources preferred?

Because it:

* Automatically closes resources
* Prevents resource leaks
* Reduces boilerplate code
* Produces cleaner code

---

# 🎯 Frequently Asked Coding Questions

* Handle division by zero using `try-catch`.
* Read a file using Try-with-Resources.
* Create a Custom Exception.
* Demonstrate Exception Propagation.
* Show the difference between `throw` and `throws`.
* Demonstrate Checked vs Unchecked Exceptions.
* Show the execution order of `try`, `catch`, and `finally`.
* Demonstrate multiple catch blocks.
* Validate user input using `throw`.
* Create an `AutoCloseable` class.

---

# 💼 Interview Tips

> [!TIP]
> During interviews, focus on **why** Exception Handling exists—not just the syntax.

Interviewers often expect you to explain:

* Why Checked Exceptions exist.
* Why `RuntimeException` is unchecked.
* Why Try-with-Resources is preferred.
* Why Custom Exceptions improve code quality.
* The difference between `throw` and `throws`.
* The execution flow when an exception occurs.

---

# 📚 Quick Revision

| Topic                | Key Point                      |
| -------------------- | ------------------------------ |
| Root Class           | `Throwable`                    |
| Recoverable Problems | `Exception`                    |
| Serious JVM Problems | `Error`                        |
| Checked Exception    | Compiler enforces handling     |
| Unchecked Exception  | Extends `RuntimeException`     |
| `throw`              | Explicitly throws an exception |
| `throws`             | Declares possible exceptions   |
| `finally`            | Cleanup block                  |
| Try-with-Resources   | Automatic resource management  |
| Custom Exception     | User-defined exception         |

---

# 🎯 Final Takeaways

After completing this chapter, you should be able to:

* Explain the Java Exception Hierarchy.
* Differentiate between Checked and Unchecked Exceptions.
* Use `try`, `catch`, `finally`, `throw`, and `throws` correctly.
* Create and use Custom Exceptions.
* Implement Try-with-Resources.
* Apply Exception Handling best practices.
* Answer common Java interview questions confidently.

---

## 📂 Recommended Practice Programs

* `45_ExceptionHierarchyDemo.java`
* `46_ThrowVsThrows.java`
* `47_CustomExceptionDemo.java`
* `48_TryWithResourcesDemo.java`
* `49_ExceptionPropagationDemo.java`
* `50_ExceptionHandlingRevision.java`

---

> [!SUCCESS]
> 🎉 Congratulations! You have completed the **Exception Handling** module. You are now ready to move on to the **Collections Framework**, where you'll learn how Java efficiently stores and manages data using collections like `ArrayList`, `LinkedList`, `HashSet`, `HashMap`, and more.
