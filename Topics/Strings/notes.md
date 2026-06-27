# 📚 Java Strings

> **Difficulty:** Beginner → Advanced (SDE Level)
> **Package:** `java.lang`
> **Introduced In:** JDK 1.0

---

# Table of Contents

* Introduction to String
* Why String is Special?
* Characteristics of String
* Internal Working of String
* Heap Memory vs String Constant Pool
* Ways to Create Strings
* String Immutability
* Why String is Immutable?
* Memory Diagrams
* String Interning
* Common Interview Questions

---

# 1. Introduction to String

A **String** is a sequence of characters used to represent text.

Examples:

```java
String name = "Mahesh";
String city = "Pune";
String language = "Java";
```

Unlike primitive data types (`int`, `char`, `boolean`, etc.), **String is an object** in Java.

The `String` class belongs to the **`java.lang`** package, so it is imported automatically.

Internally, every String stores its characters in an array (or compact byte representation in modern Java implementations), but from a developer's perspective, it behaves as an immutable sequence of characters.

---

# 2. Why is String Special in Java?

Java treats `String` differently from every other class.

Reasons include:

* Supports String literals (`"Hello"`).
* Uses the **String Constant Pool (SCP)** to save memory.
* Immutable by design.
* Frequently used by the JVM.
* Optimized for performance.
* Used in class loading.
* Used in networking.
* Used in database queries.
* Used in file handling.
* Used in logging.
* Used in configuration files.
* Used in JSON/XML processing.

No other Java class has this many JVM-level optimizations.

---

# 3. Characteristics of String

* Immutable
* Final class
* Thread-safe because immutable
* Supports String Pool
* Serializable
* Comparable
* Rich API with many utility methods
* Automatically imported via `java.lang`

Example:

```java
String s = "Java";
```

This single line involves:

* JVM lookup in the String Pool
* Possible object reuse
* Reference assignment
* Immutable object creation (if needed)

---

# 4. Class Declaration

The `String` class is declared as:

```java
public final class String
    implements Serializable, Comparable<String>, CharSequence
```

### Breakdown

| Keyword              | Meaning                                      |
| -------------------- | -------------------------------------------- |
| `public`             | Accessible everywhere                        |
| `final`              | Cannot be inherited                          |
| `Serializable`       | Can be converted into a byte stream          |
| `Comparable<String>` | Allows lexicographical comparison            |
| `CharSequence`       | Represents a readable sequence of characters |

---

# 5. Why is String Final?

Since `String` is immutable and heavily used by the JVM, Java prevents inheritance.

If it were not `final`, a subclass could change its behavior.

Example (hypothetical):

```java
class MyString extends String {
    // Not allowed
}
```

This would compromise:

* Security
* Caching
* Hashing
* Class loading
* String Pool optimizations

Therefore:

```java
public final class String
```

ensures consistent and secure behavior.

---

# 6. Why is String Immutable?

Once a String object is created, **its content cannot be changed**.

Example:

```java
String s = "Java";
s.concat(" Programming");

System.out.println(s);
```

Output:

```text
Java
```

The `concat()` method creates a **new String** instead of modifying the existing one.

Memory representation:

```text
Before:

s
 |
 v
"Java"

After concat():

s
 |
 v
"Java"

New Object

"Java Programming"
```

The original String remains unchanged.

---

# 7. Internal Representation of a String

Conceptually, a String stores characters in order.

Example:

```java
String name = "JAVA";
```

Conceptual layout:

```text
+---+---+---+---+
| J | A | V | A |
+---+---+---+---+
```

Each position represents a character in the sequence.

---

# 8. Memory Areas Used by Strings

Strings primarily involve two memory regions:

1. Stack Memory
2. Heap Memory

Additionally, Java maintains a special area inside the heap called the **String Constant Pool (SCP)**.

---

## Stack Memory

Stores:

* Local variables
* Method frames
* Object references

Example:

```java
String name = "Java";
```

The variable `name` is stored on the stack.

```text
Stack

name
 |
 v
(reference)
```

---

## Heap Memory

Stores:

* Objects
* Arrays
* Instance data
* String objects

Example:

```java
new String("Java");
```

creates a new object in the heap.

---

# 9. String Constant Pool (SCP)

The **String Constant Pool** is a special memory region used to store **unique String literals**.

Purpose:

* Reduce memory usage
* Avoid duplicate objects
* Improve performance

Example:

```java
String s1 = "Java";
String s2 = "Java";
```

Only **one String object** is created.

Memory:

```text
Stack

s1 ----\
         \
          -----> "Java"
         /
s2 -----/
```

Both variables reference the same object.

---

# 10. Heap vs String Constant Pool

Example:

```java
String s1 = "Java";
String s2 = new String("Java");
```

Memory:

```text
Stack

s1 --------> SCP
             |
             v
          "Java"

s2 --------> Heap
             |
             v
          "Java"
```

Although both contain the same characters, they are different objects.

---

# 11. Ways to Create Strings

## Method 1: Using a Literal

```java
String language = "Java";
```

* Uses the String Pool
* Memory efficient
* Recommended approach

---

## Method 2: Using `new`

```java
String language = new String("Java");
```

Creates:

1. Literal in the pool (if absent)
2. A separate object in the heap

This approach consumes more memory.

---

## Method 3: Empty String

```java
String s = "";
```

Creates or reuses the empty string literal from the pool.

---

## Method 4: Constructor from Character Array

```java
char[] letters = {'J', 'a', 'v', 'a'};
String s = new String(letters);
```

Output:

```text
Java
```

---

## Method 5: Using `String.valueOf()`

```java
int number = 100;

String s = String.valueOf(number);

System.out.println(s);
```

Output:

```text
100
```

Useful for converting primitives into Strings.

---

# 12. String Literal vs `new String()`

```java
String a = "Java";
String b = "Java";

String c = new String("Java");
```

Memory:

```text
            String Pool

           +---------+
           | "Java"  |
           +---------+
            ^      ^
            |      |
            |      |
            a      b

Heap

+---------+
| "Java"  |
+---------+
     ^
     |
     c
```

Summary:

| Expression           | Pool                  | Heap |
| -------------------- | --------------------- | ---- |
| `"Java"`             | ✅                     | ❌    |
| `new String("Java")` | ✅ (literal if needed) | ✅    |

---

# 13. Why Does the String Pool Exist?

Without the String Pool:

```java
String a = "Java";
String b = "Java";
String c = "Java";
String d = "Java";
```

Four separate objects would waste memory.

With the String Pool:

```text
a ----\
b -----\
c -------> "Java"
d -----/
```

Only one object is shared.

Benefits:

* Less memory usage
* Faster comparisons with `==` for pooled literals
* Reduced garbage collection
* Better JVM performance

---

# 14. Key Points to Remember

* `String` is a class.
* `String` objects are immutable.
* `String` is `final`.
* String literals are stored in the String Constant Pool.
* `new String()` always creates a new heap object.
* The String Pool stores only unique literals.
* Multiple references can point to the same pooled String.

---

# Interview Quick Recap

✅ String is immutable.
✅ String is final.
✅ String literals use the String Constant Pool.
✅ `new String()` creates a separate heap object.
✅ The String Pool reduces memory usage by reusing identical literals.
✅ Stack stores references; Heap stores objects.

---

---

# 15. String Immutability (Deep Dive)

## What is Immutability?

An **immutable object** is an object whose state **cannot be changed after it is created**.

For a `String`, this means:

* You cannot modify its characters.
* Any operation that appears to modify a String actually creates a **new String object**.

Example:

```java
String s = "Java";

s.concat(" Programming");

System.out.println(s);
```

### Output

```text
Java
```

Many beginners expect:

```text
Java Programming
```

But `concat()` **returns a new String** instead of modifying the original one.

---

## Example 2

```java
String s = "Java";

s = s.concat(" Programming");

System.out.println(s);
```

Output

```text
Java Programming
```

### What happened?

Step 1

```java
String s = "Java";
```

```
Stack                  String Pool

 s ------------------> "Java"
```

---

Step 2

```java
s.concat(" Programming");
```

A new object is created.

```
Stack

 s ------------------> "Java"


Heap

"Java Programming"
```

Since the returned object isn't assigned, it becomes eligible for Garbage Collection.

---

Step 3

```java
s = s.concat(" Programming");
```

Now the reference changes.

```
Stack

 s ----------------------\
                          \
                           \
Heap                        \
"Java Programming" <---------


String Pool

"Java"
```

The original `"Java"` still exists.

---

# 16. Why is String Immutable?

This is one of the most frequently asked interview questions.

## Reason 1 — Security

Strings are used in many security-sensitive areas.

Examples:

* Database URLs
* Usernames
* Passwords
* API Keys
* File Paths
* Socket Connections

Example:

```java
String url = "jdbc:mysql://localhost:3306/company";
```

Imagine if another object could modify it:

```
Before

jdbc:mysql://localhost

↓

After

jdbc:hacker://malicious-server
```

That would be a major security risk.

Immutability prevents such modifications.

---

## Reason 2 — String Constant Pool

Suppose:

```java
String s1 = "Java";
String s2 = "Java";
```

Both references point to the same object.

```
s1 ----\
        \
         ---> "Java"
        /
s2 ----/
```

If `s1` could modify the String:

```java
s1 = "Python";
```

or worse, if the shared object itself changed:

```
s2 would also become

"Python"
```

which would be incorrect.

Immutability makes sharing safe.

---

## Reason 3 — Thread Safety

Imagine 100 threads accessing:

```java
String message = "Welcome";
```

Since no thread can modify it:

* No synchronization required
* No race condition
* No data inconsistency

Immutable objects are naturally thread-safe.

---

## Reason 4 — HashCode Caching

Strings are heavily used as keys in:

```java
HashMap
HashSet
Hashtable
ConcurrentHashMap
```

Example

```java
HashMap<String, Integer> map = new HashMap<>();
```

If a String could change after insertion:

```
Key changes

↓

Hash changes

↓

Bucket changes

↓

Object becomes unreachable
```

Immutability guarantees a stable hash code.

---

## Reason 5 — Performance

The JVM can safely reuse String objects.

```
"Java"

↓

One Object

↓

Thousands of References
```

Without immutability, reuse wouldn't be possible.

---

# 17. How String Methods Work

Consider:

```java
String s = "Java";

String s2 = s.concat(" Programming");
```

Memory

```
Before

s -------> "Java"

After

s -------> "Java"

s2 ------> "Java Programming"
```

Notice:

The original object never changes.

---

Another example

```java
String s = "JAVA";

String lower = s.toLowerCase();
```

Output

```
JAVA
java
```

Memory

```
s ---------> "JAVA"

lower -----> "java"
```

---

# 18. Operations That Create New Strings

All these methods return **new String objects**.

```java
concat()

replace()

replaceAll()

replaceFirst()

substring()

trim()

strip()

toUpperCase()

toLowerCase()

repeat()

intern()

split()

join()

format()

valueOf()
```

None of them modifies the original String.

---

# 19. Compile-Time vs Runtime Concatenation

## Compile-Time Concatenation

```java
String s = "Java" + " Programming";
```

Compiler converts it to:

```java
String s = "Java Programming";
```

Only one object is created in the String Pool.

Memory

```
String Pool

"Java Programming"
```

---

## Runtime Concatenation

```java
String a = "Java";

String s = a + " Programming";
```

Compiler cannot determine the final value during compilation.

Internally it becomes:

```java
new StringBuilder()
        .append(a)
        .append(" Programming")
        .toString();
```

A new String object is created at runtime.

---

## Example

```java
String a = "Hello";
String b = "World";

String c = a + b;
```

Equivalent to:

```java
String c =
new StringBuilder()
.append(a)
.append(b)
.toString();
```

This is why repeated concatenation inside loops is inefficient.

---

# 20. String Interning

Java provides the `intern()` method.

Syntax

```java
string.intern();
```

Purpose:

Returns the reference from the **String Constant Pool**.

---

Example

```java
String s1 = new String("Java");

String s2 = s1.intern();

System.out.println(s1 == s2);
```

Output

```text
false
```

Explanation

```
Heap

s1 ---> "Java"

String Pool

s2 ---> "Java"
```

Different objects.

---

Another example

```java
String a = "Java";

String b = new String("Java");

System.out.println(a == b);
```

Output

```text
false
```

Now:

```java
System.out.println(a == b.intern());
```

Output

```text
true
```

Because `intern()` returns the pooled reference.

---

# 21. When Should You Use intern()?

Generally, you **don't need to call `intern()` manually** in everyday Java development.

Useful only when:

* Large number of duplicate Strings
* Memory optimization is important
* Building compilers
* Parsing huge files
* Search engines
* JVM-level optimizations

For regular application development, let the JVM manage the String Pool.

---

# 22. String Memory Examples

## Example 1

```java
String s1 = "Java";
String s2 = "Java";
```

```
s1 -----\
         \
          ---> "Java"
         /
s2 -----/
```

Only one object.

---

## Example 2

```java
String s1 = new String("Java");
String s2 = new String("Java");
```

```
String Pool

"Java"


Heap

s1 ---> "Java"

s2 ---> "Java"
```

Three objects exist:

* One in the Pool
* Two in the Heap

---

## Example 3

```java
String s = "Java";

s += " Programming";
```

Memory

```
Before

"Java"

↓

After

"Java"

"Java Programming"
```

A new object is created.

---

# 23. Best Practices

✅ Prefer String literals whenever possible.

```java
String name = "Mahesh";
```

---

✅ Use `equals()` for content comparison.

```java
a.equals(b)
```

---

❌ Don't use `==` for comparing String contents.

```java
a == b
```

Only compares references.

---

✅ Use `StringBuilder` for repeated concatenation.

---

✅ Avoid creating unnecessary `new String()` objects.

---

✅ Never assume a String method modifies the original object.

---

# Interview Notes

### Why is String immutable?

* Security
* String Pool
* Thread Safety
* HashCode Caching
* Performance
* Safe Sharing

---

### Which methods modify the original String?

**None.**

Every modification creates a new object.

---

### Why is `StringBuilder` faster?

Because it is **mutable**, so it modifies the same object instead of creating multiple new Strings.

---

### What does `intern()` do?

It returns the reference to the String object in the **String Constant Pool**.

---

### Key Takeaways

* Strings are immutable.
* Every "modification" creates a new object.
* The String Constant Pool safely shares immutable Strings.
* `intern()` retrieves the pooled reference.
* Repeated concatenation with `String` is inefficient; use `StringBuilder` instead.

---

---

# 24. Commonly Used String Methods

The `String` class provides many built-in methods for performing various operations such as searching, comparing, modifying, splitting, and formatting text.

> **Important:** Since Strings are immutable, every method that appears to modify a String actually returns a **new String**.

---

# 1. length()

Returns the total number of characters in the String.

### Syntax

```java
int length()
```

### Example

```java
String s = "Programming";

System.out.println(s.length());
```

### Output

```text
11
```

---

# 2. charAt()

Returns the character present at a specified index.

### Syntax

```java
char charAt(int index)
```

### Example

```java
String s = "Java";

System.out.println(s.charAt(2));
```

### Output

```text
v
```

### Note

* Index starts from **0**
* Throws `StringIndexOutOfBoundsException` for invalid index.

---

# 3. substring()

Extracts part of a String.

### Syntax

```java
substring(int beginIndex)

substring(int beginIndex, int endIndex)
```

### Example

```java
String s = "Programming";

System.out.println(s.substring(3));
```

Output

```text
gramming
```

Example

```java
System.out.println(s.substring(3,7));
```

Output

```text
gram
```

Remember:

* Begin index → Included
* End index → Excluded

---

# 4. concat()

Joins two Strings.

### Example

```java
String s = "Java";

String result = s.concat(" Programming");

System.out.println(result);
```

Output

```text
Java Programming
```

---

# 5. equals()

Compares String contents.

### Example

```java
String s1 = "Java";
String s2 = new String("Java");

System.out.println(s1.equals(s2));
```

Output

```text
true
```

---

# 6. equalsIgnoreCase()

Ignores uppercase and lowercase differences.

Example

```java
System.out.println("JAVA".equalsIgnoreCase("java"));
```

Output

```text
true
```

---

# 7. compareTo()

Performs lexicographical comparison.

### Example

```java
System.out.println("Apple".compareTo("Banana"));
```

Output

```text
-1
```

Example

```java
System.out.println("Banana".compareTo("Apple"));
```

Output

```text
1
```

Example

```java
System.out.println("Java".compareTo("Java"));
```

Output

```text
0
```

### Meaning

| Result   | Meaning                   |
| -------- | ------------------------- |
| 0        | Equal                     |
| Negative | First String comes before |
| Positive | First String comes after  |

---

# 8. compareToIgnoreCase()

Same as compareTo() but ignores case.

Example

```java
System.out.println("JAVA".compareToIgnoreCase("java"));
```

Output

```text
0
```

---

# 9. contains()

Checks whether a String contains another String.

Example

```java
String s = "Java Programming";

System.out.println(s.contains("Program"));
```

Output

```text
true
```

---

# 10. startsWith()

Checks prefix.

Example

```java
String s = "Programming";

System.out.println(s.startsWith("Pro"));
```

Output

```text
true
```

---

# 11. endsWith()

Checks suffix.

Example

```java
System.out.println("Programming".endsWith("ing"));
```

Output

```text
true
```

---

# 12. indexOf()

Returns first occurrence.

Example

```java
String s = "Programming";

System.out.println(s.indexOf('g'));
```

Output

```text
3
```

If not found

```text
-1
```

---

# 13. lastIndexOf()

Returns last occurrence.

Example

```java
String s = "Programming";

System.out.println(s.lastIndexOf('g'));
```

Output

```text
10
```

---

# 14. replace()

Replaces characters or Strings.

Example

```java
String s = "Java";

System.out.println(s.replace('a','o'));
```

Output

```text
Jovo
```

---

# 15. replaceFirst()

Replaces only the first match.

Example

```java
String s = "one one one";

System.out.println(s.replaceFirst("one","two"));
```

Output

```text
two one one
```

---

# 16. replaceAll()

Replaces every matching pattern (Regex supported).

Example

```java
String s = "one one one";

System.out.println(s.replaceAll("one","two"));
```

Output

```text
two two two
```

---

# 17. trim()

Removes leading and trailing spaces.

Example

```java
String s = "   Java   ";

System.out.println(s.trim());
```

Output

```text
Java
```

---

# 18. strip()

Introduced in Java 11.

Removes Unicode whitespace.

Recommended over trim() for international text.

---

# 19. toUpperCase()

Converts to uppercase.

```java
System.out.println("java".toUpperCase());
```

Output

```text
JAVA
```

---

# 20. toLowerCase()

Converts to lowercase.

```java
System.out.println("JAVA".toLowerCase());
```

Output

```text
java
```

---

# 21. isEmpty()

Checks whether length is zero.

Example

```java
String s = "";

System.out.println(s.isEmpty());
```

Output

```text
true
```

---

# 22. isBlank()

Introduced in Java 11.

Returns true for empty or whitespace-only Strings.

Example

```java
String s = "    ";

System.out.println(s.isBlank());
```

Output

```text
true
```

---

# Difference: isEmpty() vs isBlank()

| String   | isEmpty() | isBlank() |
| -------- | --------- | --------- |
| `""`     | ✅         | ✅         |
| `" "`    | ❌         | ✅         |
| `"Java"` | ❌         | ❌         |

---

# 23. split()

Splits a String into an array.

Example

```java
String s = "Java,Python,C++";

String[] arr = s.split(",");
```

Output

```text
Java
Python
C++
```

---

# 24. join()

Joins multiple Strings.

Example

```java
String result = String.join("-", "Java","Python","C++");

System.out.println(result);
```

Output

```text
Java-Python-C++
```

---

# 25. repeat()

Introduced in Java 11.

Example

```java
System.out.println("Java ".repeat(3));
```

Output

```text
Java Java Java
```

---

# 26. matches()

Checks using Regular Expression.

Example

```java
System.out.println("12345".matches("\\d+"));
```

Output

```text
true
```

---

# 27. valueOf()

Converts primitive values into String.

Example

```java
int number = 100;

String s = String.valueOf(number);

System.out.println(s);
```

Output

```text
100
```

---

# 28. toCharArray()

Converts String into character array.

Example

```java
char[] arr = "Java".toCharArray();
```

Output

```text
J
a
v
a
```

---

# 29. getBytes()

Converts String into byte array.

Example

```java
byte[] arr = "Java".getBytes();
```

Useful in:

* File handling
* Networking
* Encryption

---

# 30. format()

Formats Strings like printf().

Example

```java
String s = String.format("Name: %s Age: %d", "Mahesh", 21);

System.out.println(s);
```

Output

```text
Name: Mahesh Age: 21
```

---

# 31. intern()

Returns the pooled String reference.

```java
String s = new String("Java");

System.out.println(s.intern());
```

---

# 32. hashCode()

Returns hash code of the String.

Example

```java
System.out.println("Java".hashCode());
```

Useful in:

* HashMap
* HashSet
* Hashtable

---

# 33. lines() (Java 11)

Converts a multiline String into a Stream.

Example

```java
String text = "Java\nPython\nC++";

text.lines().forEach(System.out::println);
```

---

# 34. stripLeading()

Removes only leading whitespace.

---

# 35. stripTrailing()

Removes only trailing whitespace.

---

# Quick Summary Table

| Method        | Purpose                    |
| ------------- | -------------------------- |
| length()      | Length                     |
| charAt()      | Character at index         |
| substring()   | Extract part               |
| concat()      | Join Strings               |
| equals()      | Compare content            |
| compareTo()   | Lexicographical comparison |
| contains()    | Search                     |
| startsWith()  | Prefix                     |
| endsWith()    | Suffix                     |
| indexOf()     | First occurrence           |
| lastIndexOf() | Last occurrence            |
| replace()     | Replace text               |
| trim()        | Remove spaces              |
| split()       | Divide String              |
| join()        | Merge Strings              |
| repeat()      | Repeat String              |
| isEmpty()     | Empty check                |
| isBlank()     | Blank check                |
| valueOf()     | Primitive → String         |
| format()      | Formatting                 |
| intern()      | Pool reference             |

---

# Interview Notes

### Which methods modify the original String?

**None.** Every method returns a new String because Strings are immutable.

---

### Which String methods are most frequently asked in interviews?

* equals()
* compareTo()
* substring()
* split()
* replace()
* contains()
* indexOf()
* trim()
* isEmpty()
* isBlank()
* intern()

---

### Time Complexity (Important)

| Method        | Complexity                             |
| ------------- | -------------------------------------- |
| length()      | O(1)                                   |
| charAt()      | O(1)                                   |
| equals()      | O(n)                                   |
| compareTo()   | O(n)                                   |
| contains()    | O(n)                                   |
| substring()   | O(n) *(modern Java copies characters)* |
| replace()     | O(n)                                   |
| split()       | O(n)                                   |
| indexOf()     | O(n)                                   |
| lastIndexOf() | O(n)                                   |

---

### Best Practices

✅ Use `equals()` instead of `==` for content comparison.

✅ Use `StringBuilder` for repeated concatenation.

✅ Prefer `isBlank()` over `trim().isEmpty()` (Java 11+).

✅ Avoid calling `split()` repeatedly in performance-critical code when a simpler parser suffices.

---


---

# 25. `==` vs `equals()` vs `compareTo()`

This is one of the **most frequently asked Java interview questions**.

Although all three are used to compare Strings, they compare **different things**.

| Method        | Compares                          | Return Type |
| ------------- | --------------------------------- | ----------- |
| `==`          | Object Reference (Memory Address) | boolean     |
| `equals()`    | Actual String Content             | boolean     |
| `compareTo()` | Lexicographical Order             | int         |

---

# 1. Using `==`

The `==` operator checks whether **both references point to the same object**.

It **does not compare characters**.

### Example 1

```java
String s1 = "Java";
String s2 = "Java";

System.out.println(s1 == s2);
```

### Output

```text
true
```

### Why?

Both references point to the same object in the String Constant Pool.

```
        String Pool

      +-----------+
      |  "Java"   |
      +-----------+
        ^       ^
        |       |
       s1      s2
```

---

## Example 2

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2);
```

Output

```text
false
```

Memory

```
Heap

+-----------+
|  "Java"   | <--- s1
+-----------+

+-----------+
|  "Java"   | <--- s2
+-----------+
```

Different objects.

---

# 2. Using `equals()`

`equals()` compares the **actual sequence of characters**.

Example

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1.equals(s2));
```

Output

```text
true
```

Even though objects are different, their contents are equal.

---

## Another Example

```java
String a = "Hello";
String b = "hello";

System.out.println(a.equals(b));
```

Output

```text
false
```

Because `equals()` is **case-sensitive**.

---

# 3. equalsIgnoreCase()

Ignores uppercase and lowercase differences.

```java
String a = "JAVA";
String b = "java";

System.out.println(a.equalsIgnoreCase(b));
```

Output

```text
true
```

---

# 4. Using `compareTo()`

`compareTo()` compares Strings **lexicographically** (dictionary order).

It returns an integer.

```java
str1.compareTo(str2)
```

Possible return values:

| Value    | Meaning                          |
| -------- | -------------------------------- |
| 0        | Both Strings are equal           |
| Negative | First String comes before second |
| Positive | First String comes after second  |

---

## Example

```java
System.out.println("Apple".compareTo("Banana"));
```

Output

```text
-1
```

Because:

```
Apple

↓

Banana
```

Apple appears first alphabetically.

---

## Example

```java
System.out.println("Cat".compareTo("Bat"));
```

Output

```text
1
```

---

## Example

```java
System.out.println("Java".compareTo("Java"));
```

Output

```text
0
```

---

# How compareTo() Works Internally

It compares characters one by one using Unicode values.

Example

```java
"Ball"
"Bat"
```

Comparison

```
B == B

↓

a == a

↓

l vs t

108 vs 116
```

Difference

```
108 - 116 = -8
```

Therefore

```java
System.out.println("Ball".compareTo("Bat"));
```

returns a negative number.

---

# compareToIgnoreCase()

Ignores case while comparing.

```java
System.out.println("JAVA".compareToIgnoreCase("java"));
```

Output

```text
0
```

---

# equals() vs compareTo()

Example

```java
String a = "Java";
String b = "Java";

System.out.println(a.equals(b));
System.out.println(a.compareTo(b));
```

Output

```text
true
0
```

---

# Complete Comparison

| Feature             | ==      | equals()           | compareTo()           |
| ------------------- | ------- | ------------------ | --------------------- |
| Compare Memory      | ✅       | ❌                  | ❌                     |
| Compare Content     | ❌       | ✅                  | ✅                     |
| Return Type         | boolean | boolean            | int                   |
| Case Sensitive      | Yes     | Yes                | Yes                   |
| Ignore Case Version | ❌       | equalsIgnoreCase() | compareToIgnoreCase() |
| Interview Usage     | Rare    | Very Common        | Very Common           |

---

# Common Mistake

❌

```java
if(username == "admin")
```

Correct

```java
if(username.equals("admin"))
```

Reason:

`==` checks references, not characters.

---

# When to Use Which?

Use `==`

* Checking whether two references point to the same object.
* Singleton checks.
* Enum comparisons.

Use `equals()`

* Comparing usernames.
* Password validation (plain text examples only).
* Searching.
* Business logic.

Use `compareTo()`

* Sorting.
* Collections.
* TreeSet.
* TreeMap.
* Dictionary order.

---

# 26. StringBuilder

## Introduction

`StringBuilder` is a mutable sequence of characters.

Unlike `String`, its content **can be modified**.

Package

```java
java.lang
```

Introduced

```
Java 5
```

---

## Why StringBuilder Was Introduced

Suppose:

```java
String s = "";

for(int i = 1; i <= 1000; i++) {
    s += i;
}
```

Every iteration creates a new String.

```
Old Object

↓

New Object

↓

Old Object

↓

New Object
```

Thousands of unnecessary objects are created.

To solve this problem, Java introduced `StringBuilder`.

---

# Mutable Nature

```java
StringBuilder sb = new StringBuilder("Java");

sb.append(" Programming");

System.out.println(sb);
```

Output

```text
Java Programming
```

The same object is modified.

---

# Memory

```
Before

+------+
| Java |
+------+

↓

append()

↓

+--------------------+
| Java Programming   |
+--------------------+
```

No new String object.

---

# Constructors

```java
new StringBuilder()

new StringBuilder(50)

new StringBuilder("Java")
```

---

# Important Methods

| Method           | Purpose              |
| ---------------- | -------------------- |
| append()         | Add text             |
| insert()         | Insert text          |
| replace()        | Replace text         |
| delete()         | Remove characters    |
| deleteCharAt()   | Remove one character |
| reverse()        | Reverse              |
| capacity()       | Current capacity     |
| ensureCapacity() | Increase capacity    |
| setLength()      | Change length        |
| charAt()         | Character access     |

---

# append()

```java
StringBuilder sb = new StringBuilder("Java");

sb.append(" 21");

System.out.println(sb);
```

Output

```text
Java 21
```

---

# insert()

```java
StringBuilder sb = new StringBuilder("Jva");

sb.insert(1,"a");

System.out.println(sb);
```

Output

```text
Java
```

---

# replace()

```java
StringBuilder sb = new StringBuilder("Jovo");

sb.replace(1,4,"ava");

System.out.println(sb);
```

Output

```text
Java
```

---

# delete()

```java
StringBuilder sb = new StringBuilder("Java Programming");

sb.delete(4,16);

System.out.println(sb);
```

Output

```text
Java
```

---

# reverse()

```java
StringBuilder sb = new StringBuilder("Java");

System.out.println(sb.reverse());
```

Output

```text
avaJ
```

---

# Capacity

Default capacity

```
16
```

Example

```java
StringBuilder sb = new StringBuilder();

System.out.println(sb.capacity());
```

Output

```text
16
```

---

# Capacity Expansion

Formula

```
(New Capacity)

(oldCapacity × 2) + 2
```

Example

```
16

↓

34

↓

70

↓

142

↓

286
```

This reduces frequent memory allocations.

---

# StringBuilder Performance

Appending 10,000 Strings

| Type          | Performance |
| ------------- | ----------- |
| String        | Slow        |
| StringBuilder | Fast        |

Reason:

StringBuilder modifies the existing object.

---

# Interview Notes

### Is StringBuilder Thread-safe?

❌ No.

---

### Why is StringBuilder faster?

Because it is mutable and non-synchronized.

---

### Default Capacity?

```
16
```

---

### Capacity Growth Formula?

```
(oldCapacity × 2) + 2
```

---

### When should you use StringBuilder?

* Loops
* Large text generation
* JSON building
* SQL query creation
* Log generation
* String concatenation in performance-critical code

---


---

# 27. StringBuffer

## Introduction

`StringBuffer` is a **mutable** sequence of characters just like `StringBuilder`.

The major difference is:

> **StringBuffer is synchronized, whereas StringBuilder is not.**

Because of synchronization, `StringBuffer` is **thread-safe**.

Package

```java
java.lang
```

Introduced In

```text
JDK 1.0
```

---

# Why was StringBuffer Introduced?

Since `String` is immutable, repeated modifications create many unnecessary objects.

Example

```java
String s = "";

for(int i = 1; i <= 1000; i++){
    s += i;
}
```

Every iteration creates a new object.

To solve this, Java introduced **StringBuffer**.

It modifies the same object repeatedly.

---

# Mutable Nature

```java
StringBuffer sb = new StringBuffer("Java");

sb.append(" Programming");

System.out.println(sb);
```

Output

```text
Java Programming
```

The original object changes.

---

# Internal Memory

```text
Before

+------+
| Java |
+------+

↓

append()

↓

+--------------------+
| Java Programming   |
+--------------------+
```

No new String object is created.

---

# Constructors

```java
new StringBuffer()

new StringBuffer(20)

new StringBuffer("Java")
```

---

# Common Methods

| Method           | Description           |
| ---------------- | --------------------- |
| append()         | Adds text             |
| insert()         | Inserts text          |
| delete()         | Deletes characters    |
| deleteCharAt()   | Removes one character |
| replace()        | Replaces characters   |
| reverse()        | Reverses content      |
| capacity()       | Returns capacity      |
| ensureCapacity() | Expands capacity      |
| setLength()      | Changes length        |
| charAt()         | Returns character     |

---

## append()

```java
StringBuffer sb = new StringBuffer("Java");

sb.append(" 21");

System.out.println(sb);
```

Output

```text
Java 21
```

---

## insert()

```java
StringBuffer sb = new StringBuffer("Jva");

sb.insert(1,"a");

System.out.println(sb);
```

Output

```text
Java
```

---

## delete()

```java
StringBuffer sb = new StringBuffer("Java Programming");

sb.delete(4,16);

System.out.println(sb);
```

Output

```text
Java
```

---

## replace()

```java
StringBuffer sb = new StringBuffer("Jovo");

sb.replace(1,4,"ava");

System.out.println(sb);
```

Output

```text
Java
```

---

## reverse()

```java
StringBuffer sb = new StringBuffer("Java");

System.out.println(sb.reverse());
```

Output

```text
avaJ
```

---

# Capacity

Default capacity

```text
16
```

Example

```java
StringBuffer sb = new StringBuffer();

System.out.println(sb.capacity());
```

Output

```text
16
```

---

# Capacity Expansion

Same formula as StringBuilder.

```text
(newCapacity)

=

(oldCapacity × 2) + 2
```

Example

```text
16

↓

34

↓

70

↓

142
```

---

# Synchronization

Every public method in `StringBuffer` is synchronized.

Example (conceptually):

```java
public synchronized StringBuffer append(String str)
```

Only one thread can execute a synchronized method on the same object at a time.

Advantages:

* Safe in multithreaded environments.
* Prevents race conditions.

Disadvantages:

* Slightly slower due to synchronization overhead.

---

# StringBuilder vs StringBuffer

| Feature       | StringBuilder   | StringBuffer    |
| ------------- | --------------- | --------------- |
| Mutable       | ✅               | ✅               |
| Thread Safe   | ❌               | ✅               |
| Synchronized  | ❌               | ✅               |
| Performance   | Faster          | Slightly Slower |
| Introduced    | Java 5          | JDK 1.0         |
| Best Use Case | Single-threaded | Multi-threaded  |

---

# When Should You Use StringBuffer?

Use it when:

* Multiple threads modify the same object.
* Thread safety is required.
* Concurrent logging.
* Shared message builders.
* Banking or server-side applications.

Otherwise, prefer **StringBuilder** for better performance.

---

# 28. String vs StringBuilder vs StringBuffer

This is another favorite interview topic.

| Feature      | String                         | StringBuilder          | StringBuffer                |
| ------------ | ------------------------------ | ---------------------- | --------------------------- |
| Mutable      | ❌                              | ✅                      | ✅                           |
| Immutable    | ✅                              | ❌                      | ❌                           |
| Thread Safe  | ✅ *(immutable)*                | ❌                      | ✅                           |
| Synchronized | ❌                              | ❌                      | ✅                           |
| Performance  | Slow for repeated modification | Fast                   | Moderate                    |
| Memory Usage | High (many objects)            | Low                    | Low                         |
| Introduced   | JDK 1.0                        | Java 5                 | JDK 1.0                     |
| Suitable For | Fixed text                     | Frequent modifications | Multithreaded modifications |

---

# Which One Should You Choose?

### Use String

* Constant values
* Configuration values
* File names
* URLs
* User names
* SQL queries (when not repeatedly modified)

---

### Use StringBuilder

* Loops
* Dynamic text
* JSON generation
* HTML generation
* XML generation
* Log building
* SQL query builders

---

### Use StringBuffer

* Shared resources
* Banking software
* Server applications
* Multithreaded logging
* Concurrent message construction

---

# Performance Comparison

Example

```java
// String
String s = "";

for(int i = 0; i < 10000; i++){
    s += i;
}
```

Creates thousands of temporary objects.

Equivalent using `StringBuilder`:

```java
StringBuilder sb = new StringBuilder();

for(int i = 0; i < 10000; i++){
    sb.append(i);
}
```

Only one mutable object is updated.

---

# 29. StringTokenizer

## Introduction

`StringTokenizer` is a **legacy class** used to split a String into multiple tokens.

Package

```java
java.util
```

---

# Constructor

```java
StringTokenizer(String str)

StringTokenizer(String str, String delimiter)

StringTokenizer(String str, String delimiter, boolean returnDelims)
```

---

# Example

```java
import java.util.StringTokenizer;

StringTokenizer st =
new StringTokenizer("Java Python C++");

while(st.hasMoreTokens()){
    System.out.println(st.nextToken());
}
```

Output

```text
Java
Python
C++
```

---

# Custom Delimiter

```java
StringTokenizer st =
new StringTokenizer("Java,Python,C++", ",");
```

Output

```text
Java
Python
C++
```

---

# Important Methods

| Method          | Description                   |
| --------------- | ----------------------------- |
| hasMoreTokens() | Checks if more tokens exist   |
| nextToken()     | Returns next token            |
| countTokens()   | Returns remaining token count |

---

# Example

```java
StringTokenizer st =
new StringTokenizer("Java Python C++");

System.out.println(st.countTokens());
```

Output

```text
3
```

---

# StringTokenizer vs split()

| Feature       | StringTokenizer                    | split()          |
| ------------- | ---------------------------------- | ---------------- |
| Package       | java.util                          | java.lang.String |
| Regex Support | ❌                                  | ✅                |
| Returns       | Tokens one by one                  | String Array     |
| Modern        | ❌ Legacy                           | ✅ Recommended    |
| Performance   | Slightly Faster (simple delimiter) | More Flexible    |

---

# Which Should You Use?

Today, almost all modern Java applications use:

```java
String.split()
```

Use `StringTokenizer` only when maintaining legacy code or when a simple delimiter-based tokenizer is sufficient.

---

# Best Practices

✅ Prefer String literals over `new String()`.

✅ Use `equals()` for content comparison.

✅ Use `StringBuilder` for repeated concatenation.

✅ Use `StringBuffer` only when thread safety is required.

✅ Prefer `split()` over `StringTokenizer` in modern applications.

✅ Avoid unnecessary calls to `intern()`.

✅ Reuse immutable Strings whenever possible.

---

# Common Mistakes

❌ Comparing Strings using `==`.

❌ Using `String` inside large concatenation loops.

❌ Assuming `substring()` modifies the original String.

❌ Creating unnecessary `new String()` objects.

❌ Forgetting that Strings are immutable.

❌ Using `StringBuffer` in single-threaded applications where `StringBuilder` is more efficient.

---

# Frequently Asked Interview Questions

### Beginner

1. What is a String?
2. Why is String immutable?
3. Why is String final?
4. What is the String Constant Pool?
5. Difference between `==` and `equals()`?

---

### Intermediate

6. Difference between String and StringBuilder?
7. Difference between StringBuilder and StringBuffer?
8. What is `intern()`?
9. Why are Strings thread-safe?
10. Explain `compareTo()`.

---

### Advanced (SDE)

11. Why is String used as a key in `HashMap`?
12. How does hashCode caching work in String?
13. Explain compile-time vs runtime concatenation.
14. Explain capacity expansion in StringBuilder.
15. When should `StringBuffer` be preferred over `StringBuilder`?
16. Why is `substring()` O(n) in modern Java?
17. How does the JVM optimize String literals?
18. Explain String Pool internals.
19. What problems does immutability solve?
20. Design a mutable String class—what trade-offs arise?

---

# Final Cheat Sheet

| Topic                           | Key Point                       |
| ------------------------------- | ------------------------------- |
| String                          | Immutable                       |
| StringBuilder                   | Mutable, Fast, Not Thread-safe  |
| StringBuffer                    | Mutable, Thread-safe            |
| String Pool                     | Stores unique String literals   |
| `==`                            | Compares references             |
| `equals()`                      | Compares content                |
| `compareTo()`                   | Lexicographical comparison      |
| `intern()`                      | Returns pooled String reference |
| `split()`                       | Modern String splitting         |
| StringTokenizer                 | Legacy tokenizer                |
| Default Builder/Buffer Capacity | 16                              |
| Capacity Growth                 | `(oldCapacity × 2) + 2`         |

---

# Summary

After completing this chapter, you should be able to:

* Explain String internals confidently.
* Understand the String Constant Pool.
* Differentiate between `String`, `StringBuilder`, and `StringBuffer`.
* Use String APIs effectively.
* Optimize String operations.
* Answer common Java String interview questions from beginner to SDE level.
* Write efficient and memory-conscious Java code.
