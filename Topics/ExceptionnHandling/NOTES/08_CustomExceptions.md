# Custom Exceptions

## Overview

Java provides many built-in exception classes such as:

- `ArithmeticException`
- `NullPointerException`
- `IOException`
- `SQLException`

However, these exceptions cannot represent every business rule or application-specific error.

For such situations, Java allows developers to create their own exception classes, known as **Custom Exceptions**.

---

# Why do we need Custom Exceptions?

Consider the following situations:

- Student age is less than 18.
- Bank account balance becomes negative.
- Employee salary is below the minimum limit.
- User enters an invalid Aadhaar number.
- Product quantity is negative.

These are not JVM errors.

They are **business rules** defined by the application.

Custom Exceptions allow us to represent these situations clearly.

---

# Real-World Example

Imagine an online banking system.

If a customer tries to withdraw more money than the available balance, Java has no built-in exception called:

```text
InsufficientBalanceException
```

The developer creates one.

This makes the code easier to understand and maintain.

---

# How to Create a Custom Exception

A custom exception is simply a class that extends either:

- `Exception` (Checked Exception)
- `RuntimeException` (Unchecked Exception)

---

# Creating a Checked Custom Exception

```java
public class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {

        super(message);

    }

}
```

Here,

- `InvalidAgeException` is our custom exception.
- It extends `Exception`, making it a **checked exception**.
- The constructor passes the message to the parent class.

---

# Throwing the Custom Exception

```java
public class Main {

    static void validateAge(int age)
            throws InvalidAgeException {

        if (age < 18) {

            throw new InvalidAgeException(
                    "Age must be at least 18.");

        }

        System.out.println("Eligible");

    }

    public static void main(String[] args) {

        try {

            validateAge(15);

        }

        catch (InvalidAgeException e) {

            System.out.println(e.getMessage());

        }

    }

}
```

Output

```text
Age must be at least 18.
```

---

# Creating an Unchecked Custom Exception

A custom exception can also extend `RuntimeException`.

```java
public class InvalidSalaryException
        extends RuntimeException {

    public InvalidSalaryException(String message) {

        super(message);

    }

}
```

Since it extends `RuntimeException`,

the compiler does not force it to be handled.

---

# Example

```java
public class Main {

    static void validateSalary(double salary) {

        if (salary < 0) {

            throw new InvalidSalaryException(
                    "Salary cannot be negative.");

        }

        System.out.println("Valid Salary");

    }

    public static void main(String[] args) {

        validateSalary(-5000);

    }

}
```

Output

```text
Exception in thread "main"
InvalidSalaryException:
Salary cannot be negative.
```

---

# Checked vs Unchecked Custom Exceptions

| Checked Custom Exception | Unchecked Custom Exception |
|---------------------------|----------------------------|
| Extends `Exception` | Extends `RuntimeException` |
| Must be handled or declared | Handling is optional |
| Used for recoverable situations | Used for programming or validation errors |

---

# Constructor with super()

The `super()` method calls the constructor of the parent exception class.

Example

```java
public InvalidAgeException(String message) {

    super(message);

}
```

This stores the exception message inside the parent class.

Later,

```java
e.getMessage();
```

returns that message.

---

# Exception Flow

```text
Business Rule Violated
          │
          ▼
Create Custom Exception Object
          │
          ▼
throw Exception
          │
          ▼
Search Matching catch Block
          │
    ┌─────┴─────┐
    │           │
   Found      Not Found
    │           │
    ▼           ▼
Handle      Program Terminates
```

---

# Real-World Applications

Custom Exceptions are widely used in:

- Banking Applications
- E-commerce Systems
- Hospital Management Systems
- Student Management Systems
- Spring Boot Applications
- REST APIs
- Payment Gateways
- Authentication Systems

Examples include:

- InvalidUserException
- ProductNotFoundException
- PaymentFailedException
- InsufficientBalanceException
- UnauthorizedAccessException
- InvalidOrderException

---

# Best Practices

- Give meaningful exception names.
- End custom exception names with `Exception`.
- Provide descriptive error messages.
- Extend `Exception` for checked exceptions.
- Extend `RuntimeException` for unchecked exceptions.
- Use custom exceptions only for application-specific rules.

---

# Common Mistakes

❌ Creating unnecessary custom exceptions.

❌ Naming exceptions without the `Exception` suffix.

```java
class InvalidAge {

}
```

Better

```java
class InvalidAgeException {

}
```

---

❌ Using built-in exceptions when a custom exception makes the code clearer.

---

❌ Throwing custom exceptions without meaningful messages.

---

# Interview Questions

### 1. What is a Custom Exception?

A user-defined exception created by extending `Exception` or `RuntimeException`.

---

### 2. Why do we create Custom Exceptions?

To represent application-specific or business-rule violations that are not covered by Java's built-in exceptions.

---

### 3. Can a Custom Exception be checked?

Yes.

If it extends `Exception`.

---

### 4. Can a Custom Exception be unchecked?

Yes.

If it extends `RuntimeException`.

---

### 5. Why is `super(message)` used?

It passes the exception message to the parent exception class so it can be retrieved later using `getMessage()`.

---

# Summary

- Custom Exceptions are user-defined exception classes.
- They represent business-specific errors.
- A checked custom exception extends `Exception`.
- An unchecked custom exception extends `RuntimeException`.
- Custom Exceptions improve code readability, maintainability, and error reporting.

---

## Related Programs

- `25_CreateCheckedCustomException.java`
- `26_CreateUncheckedCustomException.java`
- `27_InvalidAgeValidation.java`
- `28_InvalidSalaryValidation.java`
- `29_InsufficientBalanceException.java`

---

## Next Chapter

➡️ **09_TryWithResources.md**
