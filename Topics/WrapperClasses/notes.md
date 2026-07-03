# Wrapper Classes

Wrapper Classes are special classes in Java that encapsulate primitive data types into objects. They bridge the gap between primitive values and Java's object-oriented features, allowing primitives to work with APIs that require objects, such as Collections, Generics, and Streams.

Wrapper Classes are immutable, belong to the `java.lang` package, and provide numerous utility methods for conversion, comparison, parsing, and validation.

---

# Learning Objectives

After completing this module, you will be able to:

* Explain what Wrapper Classes are.
* Understand why Wrapper Classes were introduced.
* Differentiate between primitive data types and Wrapper Classes.
* Create Wrapper objects using modern approaches.
* Understand Autoboxing and Unboxing.
* Convert Strings into primitive values and Wrapper objects.
* Use Wrapper Class utility methods effectively.
* Work with Wrapper Classes in Collections and Generics.
* Understand performance considerations.
* Solve common Wrapper Class interview questions.

---

# Table of Contents

1. What are Wrapper Classes?
2. Why Wrapper Classes Exist?
3. Primitive vs Wrapper Classes
4. The Eight Wrapper Classes
5. Creating Wrapper Objects
6. Autoboxing
7. Unboxing
8. Parsing Methods
9. valueOf() Method
10. Utility Methods
11. Best Practices
12. Common Mistakes
13. Interview Corner
14. Quick Revision
15. Summary

---

# 1. What are Wrapper Classes?

## Definition

A Wrapper Class is a Java class that wraps a primitive data type inside an object.

Example

```java
int employeeAge = 25;          // Primitive

Integer employeeAge = 25;      // Wrapper Object
```

---

## Characteristics

* Every primitive type has one corresponding Wrapper Class.
* Wrapper Classes belong to the `java.lang` package.
* Wrapper objects are immutable.
* They provide useful utility methods.
* They enable primitives to be used where objects are required.

---

## Primitive to Wrapper Mapping

| Primitive | Wrapper Class |
| --------- | ------------- |
| byte      | Byte          |
| short     | Short         |
| int       | Integer       |
| long      | Long          |
| float     | Float         |
| double    | Double        |
| char      | Character     |
| boolean   | Boolean       |

> **Note:** The two naming exceptions are `Integer` (not `Int`) and `Character` (not `Char`).

---

# 2. Why Wrapper Classes Exist?

Primitive data types are lightweight and efficient, but they are **not objects**.

Many Java APIs are designed to work only with objects.

Examples include:

* Collections Framework
* Generics
* Streams API
* Optional
* Reflection
* Serialization

Without Wrapper Classes, primitive values cannot be used with these APIs.

Example

❌ Invalid

```java
ArrayList<int> employeeIds = new ArrayList<>();
```

✅ Correct

```java
ArrayList<Integer> employeeIds = new ArrayList<>();
```

---

## Why didn't Java make primitives into objects?

If every primitive value were an object,

* More memory would be required.
* Object creation would slow down simple calculations.
* Basic arithmetic operations would become less efficient.

Java keeps primitive types for performance and provides Wrapper Classes whenever object behavior is needed.

---

## Common Use Cases

* Collections
* Generics
* Streams API
* Database Applications
* JSON Processing
* Configuration Files
* Frameworks such as Spring and Hibernate

---

# 3. Primitive Data Types vs Wrapper Classes

## Comparison

| Feature                           | Primitive       | Wrapper Class   |
| --------------------------------- | --------------- | --------------- |
| Type                              | Built-in        | Class           |
| Stores                            | Value           | Object          |
| Package                           | —               | `java.lang`     |
| Memory Usage                      | Less            | More            |
| Performance                       | Faster          | Slightly Slower |
| Supports `null`                   | ❌ No            | ✅ Yes           |
| Utility Methods                   | ❌ No            | ✅ Yes           |
| Collections                       | ❌ Not Supported | ✅ Supported     |
| Generics                          | ❌ Not Supported | ✅ Supported     |
| Default Value (Instance Variable) | Depends on Type | `null`          |

---

## Rule of Thumb

Use **primitive data types** when:

* Performing calculations
* Working inside loops
* Optimizing performance
* Memory usage is important

Use **Wrapper Classes** when:

* Working with Collections
* Using Generics
* Processing Streams
* Nullable values are required
* Interacting with frameworks and libraries

---

## Quick Example

Primitive

```java
int employeeSalary = 50000;
```

Wrapper

```java
Integer employeeSalary = 50000;
```

Choose the type based on your use case—not simply because one is newer than the other.

---

# 4. The Eight Wrapper Classes

Java provides one Wrapper Class for each primitive data type.

| Primitive | Wrapper   | Size          | Range                   |
| --------- | --------- | ------------- | ----------------------- |
| byte      | Byte      | 8 bits        | -128 to 127             |
| short     | Short     | 16 bits       | -32,768 to 32,767       |
| int       | Integer   | 32 bits       | -2³¹ to (2³¹ - 1)       |
| long      | Long      | 64 bits       | -2⁶³ to (2⁶³ - 1)       |
| float     | Float     | 32 bits       | IEEE 754 Floating Point |
| double    | Double    | 64 bits       | IEEE 754 Floating Point |
| char      | Character | 16 bits       | Unicode (0–65,535)      |
| boolean   | Boolean   | JVM Dependent | `true` / `false`        |

---

## Frequently Used Wrapper Classes

| Wrapper Class | Common Use Cases                          |
| ------------- | ----------------------------------------- |
| Integer       | Collections, IDs, Counters, User Input    |
| Long          | Timestamps, File Sizes, Large IDs         |
| Double        | Scientific Calculations, Analytics        |
| Boolean       | Authentication, Validation, Feature Flags |
| Character     | Text Processing, Parsing                  |

> **Note:** For financial calculations, prefer `BigDecimal` over `Float` or `Double` to avoid precision issues.

---

# 5. Creating Wrapper Objects

There are three ways to create Wrapper objects.

| Method      | Example                     | Status                 |
| ----------- | --------------------------- | ---------------------- |
| Constructor | `new Integer(25)`           | ❌ Deprecated (Java 9+) |
| `valueOf()` | `Integer.valueOf(25)`       | ✅ Recommended          |
| Autoboxing  | `Integer employeeAge = 25;` | ✅ Most Common          |

---

## Using Constructors (Deprecated)

```java
Integer employeeAge = new Integer(25);
```

Wrapper class constructors were deprecated in Java 9 because better alternatives are available.

---

## Using valueOf()

```java
Integer employeeAge = Integer.valueOf(25);
```

This is the recommended factory method for explicitly creating Wrapper objects.

---

## Using Autoboxing

```java
Integer employeeAge = 25;
```

The compiler automatically converts the primitive value into a Wrapper object.

Internally,

```java
Integer employeeAge = Integer.valueOf(25);
```

---

## Quick Comparison

| Feature             | Constructor | valueOf()  | Autoboxing      |
| ------------------- | ----------- | ---------- | --------------- |
| Readability         | Low         | Good       | Excellent       |
| Recommended         | ❌           | ✅          | ✅               |
| Used in Modern Java | Rarely      | Frequently | Very Frequently |

---

# 6. Autoboxing

## Definition

Autoboxing is the automatic conversion of a primitive value into its corresponding Wrapper object.

Example

```java
Integer employeeAge = 25;
```

---

## Internal Working

The compiler converts

```java
Integer employeeAge = 25;
```

into

```java
Integer employeeAge = Integer.valueOf(25);
```

Autoboxing **does not** use deprecated constructors.

---

## Common Examples

```java
Integer employeeAge = 25;

Double productPrice = 999.99;

Boolean isPaymentSuccessful = true;

Character grade = 'A';
```

---

## Where is Autoboxing Used?

* Collections
* Generics
* Streams
* Method Arguments
* Method Return Values
* Modern Java Frameworks

---

## Things to Remember

* Introduced in **Java 5**.
* Performed automatically by the compiler.
* Internally uses `valueOf()`.
* Improves code readability.

---

# 7. Unboxing

## Definition

Unboxing is the automatic conversion of a Wrapper object into its corresponding primitive value.

Example

```java
Integer employeeAge = 25;

int age = employeeAge;
```

---

## Internal Working

The compiler converts

```java
int age = employeeAge;
```

into

```java
int age = employeeAge.intValue();
```

---

## Unboxing Methods

| Wrapper   | Method           |
| --------- | ---------------- |
| Integer   | `intValue()`     |
| Long      | `longValue()`    |
| Double    | `doubleValue()`  |
| Float     | `floatValue()`   |
| Short     | `shortValue()`   |
| Byte      | `byteValue()`    |
| Character | `charValue()`    |
| Boolean   | `booleanValue()` |

---

## NullPointerException

One of the most common mistakes while working with Wrapper Classes.

```java
Integer employeeAge = null;

int age = employeeAge;
```

The compiler generates

```java
employeeAge.intValue();
```

Since `employeeAge` is `null`, the program throws a `NullPointerException`.

Always check for `null` before unboxing values obtained from databases, APIs, or external sources.

---

## Things to Remember

* Introduced in **Java 5**.
* Performed automatically by the compiler.
* Internally uses methods like `intValue()`.
* Unboxing a `null` Wrapper object throws `NullPointerException`.

---
# 8. Parsing Methods

Parsing is the process of converting a **String** into its corresponding **primitive data type**.

It is commonly used when reading data from:

* User Input
* Configuration Files
* CSV Files
* JSON/XML
* Databases
* REST APIs

---

## Common Parsing Methods

| Wrapper Class | Method           | Returns   |
| ------------- | ---------------- | --------- |
| Integer       | `parseInt()`     | `int`     |
| Long          | `parseLong()`    | `long`    |
| Double        | `parseDouble()`  | `double`  |
| Float         | `parseFloat()`   | `float`   |
| Short         | `parseShort()`   | `short`   |
| Byte          | `parseByte()`    | `byte`    |
| Boolean       | `parseBoolean()` | `boolean` |
| Character     | Not Available    | —         |

---

## Examples

```java
int employeeAge = Integer.parseInt("25");

double productPrice = Double.parseDouble("999.99");

long mobileNumber = Long.parseLong("9876543210");

boolean isPaymentSuccessful =
        Boolean.parseBoolean("true");

char grade = "A".charAt(0);
```

---

## Important Points

* Parsing methods return **primitive values**.
* `Character` does not provide a `parseChar()` method.
* Invalid numeric input throws `NumberFormatException`.
* `Boolean.parseBoolean()` is case-insensitive.

---

# 9. valueOf() Method

The `valueOf()` method converts a primitive value or a valid String into its corresponding **Wrapper object**.

Unlike parsing methods,

`valueOf()` returns an **object**, not a primitive value.

---

## Syntax

```java
WrapperClass.valueOf(value);
```

---

## Examples

```java
Integer employeeAge = Integer.valueOf(25);

Integer employeeId = Integer.valueOf("101");

Double productPrice = Double.valueOf("999.99");

Boolean isActive = Boolean.valueOf("true");
```

---

## valueOf() vs Parsing

| Feature            | `valueOf()`    | `parseXxx()`    |
| ------------------ | -------------- | --------------- |
| Returns            | Wrapper Object | Primitive Value |
| Accepts Primitive  | ✅              | ❌               |
| Accepts String     | ✅              | ✅               |
| Used in Autoboxing | ✅              | ❌               |

---

## Internal Working

Autoboxing

```java
Integer employeeAge = 25;
```

Compiler converts it into

```java
Integer employeeAge =
        Integer.valueOf(25);
```

This is why `valueOf()` plays an important role in modern Java.

---

## Important Points

* Preferred over deprecated constructors.
* Used internally by Autoboxing.
* Invalid numeric Strings throw `NumberFormatException`.
* Some Wrapper Classes internally reuse objects for better performance (covered in the IntegerCache module).

---

# 10. Wrapper Class Utility Methods

Wrapper Classes provide many useful utility methods for conversion, comparison, and validation.

---

## Comparison Methods

```java
Integer employeeAge = 25;

Integer managerAge = 30;

employeeAge.compareTo(managerAge);

employeeAge.equals(managerAge);
```

---

## Primitive Value Methods

```java
Integer employeeAge = 25;

employeeAge.intValue();

employeeAge.doubleValue();

employeeAge.longValue();

employeeAge.floatValue();
```

These methods are internally used during Unboxing.

---

## Number Conversion Methods

```java
Integer.toBinaryString(25);

Integer.toOctalString(25);

Integer.toHexString(25);
```

Useful for:

* Bit Manipulation
* Networking
* Low-Level Programming

---

## Useful Constants

```java
Integer.MAX_VALUE

Integer.MIN_VALUE

Integer.SIZE

Integer.BYTES
```

| Constant    | Description              |
| ----------- | ------------------------ |
| `MAX_VALUE` | Largest supported value  |
| `MIN_VALUE` | Smallest supported value |
| `SIZE`      | Number of bits           |
| `BYTES`     | Number of bytes          |

---

## Character Utility Methods

```java
Character.isDigit('5');

Character.isLetter('A');

Character.isUpperCase('M');

Character.isLowerCase('z');

Character.isWhitespace(' ');
```

Commonly used for:

* Input Validation
* Password Validation
* Text Processing
* Parsing

---

## Frequently Used Methods

| Category             | Methods                       |
| -------------------- | ----------------------------- |
| Conversion           | `valueOf()`                   |
| Parsing              | `parseXxx()`                  |
| Comparison           | `compareTo()`, `equals()`     |
| Primitive Conversion | `intValue()`, `doubleValue()` |
| Constants            | `MAX_VALUE`, `MIN_VALUE`      |
| Character Utilities  | `isDigit()`, `isLetter()`     |

---

## Things to Remember

* Use built-in utility methods instead of writing custom logic.
* Use constants instead of hardcoded values.
* Prefer `equals()` over `==` for Wrapper value comparison.
* Wrapper Classes provide many helper methods that primitive types do not.

---

# 11. Advanced Concepts

## 11.1 `==` vs `equals()`

This is one of the most frequently misunderstood topics.

```java
Integer employeeAge = 200;
Integer managerAge = 200;

System.out.println(employeeAge == managerAge);      // false
System.out.println(employeeAge.equals(managerAge)); // true
```

### Difference

| `==`                                                            | `equals()`                                            |
| --------------------------------------------------------------- | ----------------------------------------------------- |
| Compares object references                                      | Compares object values                                |
| Returns `true` only if both references point to the same object | Returns `true` if both objects contain the same value |

> **Rule:** Always use `equals()` when comparing Wrapper Class values.

**Note:** Small integer values may behave differently because of Integer caching. This is covered separately in the **IntegerCache** module.

---

## 11.2 Wrapper Class Immutability

All Wrapper Classes are immutable.

```java
Integer employeeAge = 25;

employeeAge = 30;
```

The existing object is **not modified**.

Instead, a new Wrapper object is created.

### Benefits

* Thread-safe
* Safe to use as HashMap keys
* Predictable behavior
* Easier JVM optimizations

---

## 11.3 Wrapper Classes as HashMap Keys

```java
HashMap<Integer, String> employees = new HashMap<>();

employees.put(101, "Rahul");
employees.put(102, "Priya");
```

Wrapper Classes work well as keys because they

* are immutable
* implement `equals()`
* implement `hashCode()`

---

## 11.4 Method Overloading

```java
void display(int employeeAge) { }

void display(Integer employeeAge) { }
```

```java
display(25);
```

The `int` version is selected because Java prefers an exact primitive match over Autoboxing.

---

## 11.5 Ternary Operator + Autoboxing

```java
Integer employeeAge = null;

int defaultAge = 18;

int age = true ? employeeAge : defaultAge;
```

This throws a

```
NullPointerException
```

because `employeeAge` is automatically unboxed.

Always avoid unboxing nullable Wrapper objects.

---

## 11.6 Boxing & Unboxing Performance

```java
Integer total = 0;

for (int employeeAge = 1; employeeAge <= 100000; employeeAge++) {

    total += employeeAge;

}
```

Every iteration performs

* Unboxing
* Addition
* Autoboxing

This creates unnecessary overhead.

Prefer primitive types for

* Mathematical calculations
* Large loops
* Performance-critical code

---

# 12. Best Practices

* Prefer primitive types for calculations.
* Use Wrapper Classes when objects are required.
* Prefer Autoboxing for readability.
* Use `valueOf()` instead of deprecated constructors.
* Compare Wrapper values using `equals()`.
* Always check for `null` before Unboxing.
* Use Wrapper utility methods instead of custom implementations.
* Use `BigDecimal` for financial calculations.

---

# 13. Common Mistakes

* Using deprecated Wrapper constructors.
* Comparing Wrapper objects using `==`.
* Forgetting that Wrapper objects can be `null`.
* Ignoring `NumberFormatException` while parsing.
* Excessive Boxing and Unboxing inside loops.
* Using `Double` or `Float` for currency calculations.

---

# 14. Interview Corner

### Q1. Why were Wrapper Classes introduced?

Primitive types are not objects. Wrapper Classes allow primitive values to work with object-oriented APIs such as Collections, Generics, and Streams.

---

### Q2. What is Autoboxing?

Automatic conversion of a primitive value into its corresponding Wrapper object.

---

### Q3. What is Unboxing?

Automatic conversion of a Wrapper object into its corresponding primitive value.

---

### Q4. Can Unboxing throw `NullPointerException`?

Yes. Unboxing a `null` Wrapper object throws `NullPointerException`.

---

### Q5. What is the difference between `parseInt()` and `valueOf()`?

| `parseInt()`            | `valueOf()`              |
| ----------------------- | ------------------------ |
| Returns primitive `int` | Returns `Integer` object |

---

### Q6. Why are Wrapper Classes immutable?

Immutability provides thread safety, predictable behavior, and allows Wrapper objects to be safely used as keys in hash-based collections.

---

### Q7. Why shouldn't Wrapper objects be compared using `==`?

Because `==` compares object references, not values.

Use `equals()` instead.

---

### Q8. Why are Wrapper constructors deprecated?

Because `valueOf()` provides a more efficient and optimized way of creating Wrapper objects.

---

### Q9. When should primitive types be preferred?

Use primitive types for calculations, loops, and performance-sensitive code.

---

### Q10. Why are Wrapper Classes required in Collections?

Collections store objects, not primitive values.

---

# 15. Quick Revision

```
Primitive
        ↓
 Autoboxing
        ↓
Wrapper Object
        ↓
  Unboxing
        ↓
Primitive
```

```
String
      ↓

parseXxx()

      ↓

Primitive
```

```
String / Primitive
        ↓

valueOf()

        ↓

Wrapper Object
```

### Quick Facts

* 8 Wrapper Classes
* All belong to `java.lang`
* Immutable and `final`
* Constructors deprecated (Java 9+)
* Autoboxing uses `valueOf()`
* Unboxing uses `xxxValue()`
* Wrapper Classes support `null`
* Collections require Wrapper Classes
* Compare Wrapper values using `equals()`

---

# 16. Summary

* Wrapper Classes convert primitive values into objects.
* They enable primitive values to work with Java's object-oriented APIs.
* Modern Java primarily uses Autoboxing and `valueOf()`.
* Parsing methods convert Strings into primitive values.
* Wrapper Classes provide useful utility methods for conversion, comparison, and validation.
* Use Wrapper Classes when object behavior is required and primitive types when maximum performance is important.
* Integer caching and related JVM optimizations are covered separately in the **IntegerCache** module.
