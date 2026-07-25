# Try-with-Resources

> [!IMPORTANT]
> **Try-with-Resources** is a feature introduced in **Java 7** that automatically closes resources after they are no longer needed.
>
> It eliminates the need to manually close resources inside a `finally` block, making code cleaner, safer, and less error-prone.

---

# 📖 Overview

Many Java programs work with external resources such as:

- 📄 Files
- 🗄️ Databases
- 🌐 Network Connections
- 📥 Input Streams
- 📤 Output Streams
- 🔌 Sockets

These resources occupy system memory and must be released after use.

Before Java 7, developers had to close resources manually using the `finally` block.

Java 7 introduced **Try-with-Resources**, which automatically closes resources when execution leaves the `try` block.

---

# ❓ Why Do We Need Try-with-Resources?

Suppose you open a file.

```java
FileReader reader = new FileReader("data.txt");
```

If an exception occurs before calling:

```java
reader.close();
```

the file remains open.

This leads to:

- Resource leaks
- Increased memory usage
- Locked files
- Performance issues

Try-with-Resources solves this problem automatically.

---

# 🔹 Before Java 7

```java
FileReader reader = null;

try {

    reader = new FileReader("data.txt");

    // Read file

}
catch (IOException e) {

    System.out.println("Error");

}
finally {

    if (reader != null) {

        reader.close();

    }

}
```

### Problems

- Lots of boilerplate code
- Easy to forget `close()`
- Difficult to maintain
- Nested try-catch blocks

---

# ✅ Using Try-with-Resources

```java
try (FileReader reader = new FileReader("data.txt")) {

    // Read file

}
catch (IOException e) {

    System.out.println("Error");

}
```

No `finally` block is required.

Java automatically closes the resource.

---

# 📝 Syntax

```java
try (Resource resource = new Resource()) {

    // Use resource

}
catch (ExceptionType e) {

    // Handle exception

}
```

---

# ⚙️ How Try-with-Resources Works

```text
Start
  │
  ▼
Create Resource
  │
  ▼
Enter try Block
  │
  ▼
Execute Statements
  │
  ▼
Exception Occurs?
  │
 ┌───────────────┐
 │               │
No              Yes
 │               │
 ▼               ▼
Complete      Execute catch
Normally
 │               │
 └───────┬───────┘
         ▼
Automatically Close Resource
         │
         ▼
Continue Program
```

---

# ✅ Example 1 — Reading a File

```java
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("data.txt")) {

            System.out.println("File Opened");

        }

        catch (IOException e) {

            System.out.println("File not found.");

        }

    }

}
```

### Output

```text
File Opened
```

After execution,

the `FileReader` is automatically closed.

---

# ✅ Example 2 — Multiple Resources

Multiple resources can be declared.

```java
try (

    FileReader reader = new FileReader("input.txt");

    BufferedReader buffer =
            new BufferedReader(reader)

) {

    System.out.println(buffer.readLine());

}
catch (IOException e) {

    System.out.println(e.getMessage());

}
```

Resources are separated using semicolons.

---

# 🔄 Resource Closing Order

Resources are closed in **reverse order**.

Example

```java
try (

    ResourceA a = new ResourceA();

    ResourceB b = new ResourceB();

    ResourceC c = new ResourceC();

) {

}
```

Closing order

```text
ResourceC

↓

ResourceB

↓

ResourceA
```

This is called **Last In, First Out (LIFO)**.

---

# 📌 What Can Be Used?

Only objects implementing the **AutoCloseable** interface can be used.

Examples include:

- FileReader
- FileWriter
- BufferedReader
- BufferedWriter
- Scanner
- PrintWriter
- Connection
- Statement
- ResultSet
- Socket

---

# 🔍 AutoCloseable Interface

The `AutoCloseable` interface contains one method.

```java
void close() throws Exception;
```

When execution leaves the `try` block,

Java automatically calls:

```java
close();
```

---

# ✅ Custom Resource Example

```java
class Demo implements AutoCloseable {

    @Override
    public void close() {

        System.out.println("Resource Closed");

    }

}
```

Usage

```java
try (Demo d = new Demo()) {

    System.out.println("Using Resource");

}
```

### Output

```text
Using Resource

Resource Closed
```

---

# 🔥 Try-with-Resources vs finally

| Try-with-Resources | finally |
|--------------------|----------|
| Introduced in Java 7 | Available since Java 1.0 |
| Automatically closes resources | Resources closed manually |
| Less code | More boilerplate |
| Safer | Easy to forget `close()` |
| Preferred in modern Java | Used when automatic closing isn't possible |

---

# 💼 Real-World Applications

Try-with-Resources is commonly used in:

- 📄 File Handling
- 🗄️ JDBC Database Connections
- 🌐 Network Programming
- ☁️ Cloud Storage APIs
- 📦 ZIP File Processing
- 🔌 Socket Programming

---

# ✅ Best Practices

> [!TIP]
> Follow these practices for cleaner and safer code.

- Prefer Try-with-Resources over `finally`.
- Keep resource declarations inside the `try`.
- Use meaningful variable names.
- Open only the resources you actually need.
- Handle exceptions appropriately.

---

# ❌ Common Mistakes

### Forgetting that only `AutoCloseable` objects are allowed.

```java
try (String s = "Hello") {

}
```

❌ Invalid

---

### Closing resources manually.

```java
reader.close();
```

Not required inside Try-with-Resources.

---

### Opening unnecessary resources.

Only create resources that will actually be used.

---

# 💡 Interview Questions

### 1. When was Try-with-Resources introduced?

**Java 7**

---

### 2. Which interface is required?

`AutoCloseable`

---

### 3. Does Try-with-Resources replace `finally`?

Not completely.

It mainly replaces `finally` for resource cleanup.

---

### 4. Are multiple resources allowed?

✅ Yes.

```java
try (

    ResourceA a = ...;

    ResourceB b = ...

) {

}
```

---

### 5. In which order are resources closed?

Reverse order (LIFO).

---

# 📚 Summary

- Try-with-Resources was introduced in **Java 7**.
- It automatically closes resources after use.
- Only `AutoCloseable` objects can be used.
- It eliminates boilerplate cleanup code.
- Resources are closed in reverse order.
- It is the preferred approach for resource management in modern Java.

---

## 📂 Related Programs

- `30_BasicTryWithResources.java`
- `31_MultipleResources.java`
- `32_FileReaderExample.java`
- `33_AutoCloseableExample.java`
- `34_TryWithResourcesVsFinally.java`

---

## ➡️ Next Chapter

**10_BestPractices.md**
