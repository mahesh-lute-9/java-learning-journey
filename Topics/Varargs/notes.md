# Variable Arguments (Varargs) in Java

> **Java Version:** Java 5+  

## What are Varargs?

Variable Arguments (**Varargs**) allow a method to accept **zero or more arguments** of the same type.

Instead of writing multiple overloaded methods, you can write a **single method** that accepts a variable number of arguments.

---

## Syntax

```java
returnType methodName(dataType... variableName) {
    // body
}
```

### Example

```java
public static void printNumbers(int... nums) {
    for (int num : nums) {
        System.out.print(num + " ");
    }
}
```

```java
printNumbers();
printNumbers(10);
printNumbers(10, 20, 30, 40);
```

**Output**

```text
10
10 20 30 40
```

---

# How Varargs Works Internally

```java
void show(int... nums)
```

The compiler converts it to:

```java
void show(int[] nums)
```

A call like:

```java
show(1, 2, 3, 4);
```

becomes:

```java
show(new int[]{1, 2, 3, 4});
```

> **Key Point:** Varargs are implemented internally using **arrays**.

---

# Example

```java
class Demo {

    static int sum(int... numbers) {
        int total = 0;

        for (int n : numbers) {
            total += n;
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(sum());
        System.out.println(sum(10));
        System.out.println(sum(10, 20));
        System.out.println(sum(10, 20, 30, 40));
    }
}
```

**Output**

```text
0
10
30
100
```

---

# Rules of Varargs

## Rule 1: Varargs must be the last parameter

✅ Valid

```java
void show(String name, int... marks) {}
```

❌ Invalid

```java
void show(int... marks, String name) {}
```

---

## Rule 2: Only one varargs parameter is allowed

✅ Valid

```java
void test(String name, int... marks) {}
```

❌ Invalid

```java
void test(int... a, String... b) {}
```

Reason: The compiler cannot determine where one varargs list ends and the other begins.

---

## Rule 3: Varargs can accept

- Zero arguments
- One argument
- Multiple arguments
- An array

```java
show();
show(10);
show(10, 20);
show(new int[]{10,20,30});
```

---

# Passing an Array

```java
int[] arr = {10,20,30};

show(arr);
```

```java
static void show(int... nums) {
    for (int n : nums) {
        System.out.println(n);
    }
}
```

---

# Method Overloading

```java
class Demo {

    static void show(int a) {
        System.out.println("int");
    }

    static void show(int... a) {
        System.out.println("varargs");
    }

    public static void main(String[] args) {
        show(10);
    }
}
```

**Output**

```text
int
```

### Method Resolution Priority

1. Exact Match
2. Primitive Widening
3. Autoboxing
4. Varargs

> Varargs always has the **lowest priority**.

---

# Ambiguity

```java
void show(int... a) {}
void show(String... a) {}

show();
```

❌ Compile-time Error

```text
Reference to show is ambiguous
```

---

# Primitive Varargs

```java
void add(int... nums) {}

add(1);
add(1,2);
add(1,2,3,4,5);
```

---

# Object Varargs

```java
void print(String... names) {}

print("Mahesh");
print("Mahesh", "Rahul", "Amit");
```

---

# Wrapper Class Varargs

```java
void test(Integer... nums) {}

test(10,20,30);
```

Autoboxing:

```java
10 -> Integer.valueOf(10)
```

---

# Real-World Example

```java
System.out.printf("%s is %d years old", "Mahesh", 21);
```

Method declaration:

```java
public PrintStream printf(String format, Object... args)
```

---

# Memory Representation

```text
sum(10,20,30)

Compiler
    │
    ▼
sum(new int[]{10,20,30})

Stack
------
nums ─────► Heap
            +----+----+----+
            | 10 | 20 | 30 |
            +----+----+----+
```

---

# Varargs vs Arrays

| Arrays | Varargs |
|--------|----------|
| Caller creates array | Compiler creates array |
| `show(int[] a)` | `show(int... a)` |
| Need `new int[]{}` | Direct values allowed |
| Less convenient | More convenient |

---

# Interview Questions

### Q1. What are Varargs?

A feature introduced in Java 5 that allows methods to accept a variable number of arguments of the same type.

---

### Q2. Are Varargs internally arrays?

✅ Yes.

---

### Q3. Can we declare multiple Varargs?

❌ No.

---

### Q4. Why must Varargs be the last parameter?

To avoid ambiguity during method invocation.

---

### Q5. Can we pass an array?

✅ Yes.

```java
int[] arr = {1,2,3};
show(arr);
```

---

### Q6. Which has higher priority?

```
Exact Match
    ↓
Primitive Widening
    ↓
Autoboxing
    ↓
Varargs
```

---

### Q7. Output?

```java
void show(Integer... a) {
    System.out.println("Integer");
}

void show(int... a) {
    System.out.println("int");
}

show(10);
```

**Answer**

```text
int
```

Reason: Primitive match is preferred over autoboxing.

---

# Quick Revision

- ✅ Introduced in **Java 5**
- ✅ Syntax: `type... variableName`
- ✅ Internally converted to an **array**
- ✅ Accepts **0, 1, or many** arguments
- ✅ Only **one** varargs parameter is allowed
- ✅ Must be the **last parameter**
- ✅ Supports primitives, objects, wrapper classes, and arrays
- ✅ Widely used in `System.out.printf()`, logging APIs, testing frameworks, and utility libraries
