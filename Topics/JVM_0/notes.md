# 🚀 JVM (Java Virtual Machine) Internals

JVM is the heart of Java.

It is responsible for:

* Loading classes
* Verifying bytecode
* Allocating memory
* Executing code
* Garbage Collection
* Security

Java achieves:

```text
Write Once

Run Anywhere (WORA)
```

because of JVM.

---

# 1. JDK vs JRE vs JVM

This is the most asked interview question.

## JDK

JDK stands for:

```text
Java Development Kit
```

Contains:

* JRE
* javac Compiler
* javadoc
* jdb debugger
* jar tool

Used for:

```text
Developing Java Applications
```

---

## JRE

JRE stands for:

```text
Java Runtime Environment
```

Contains:

* JVM
* Core Libraries
* Runtime Environment

Used for:

```text
Running Java Programs
```

---

## JVM

JVM stands for:

```text
Java Virtual Machine
```

Responsible for:

```text
Loading

Verifying

Executing

Managing Memory

Garbage Collection
```

---

## Relationship

```text
JDK

|

+---- JRE

      |

      +---- JVM
```

---

# 2. Java Compilation Flow

When:

```java
System.out.println("Hello");
```

is written:

### Step 1

```text
Hello.java
```

↓

### Step 2

Compiler:

```text
javac Hello.java
```

↓

Creates:

```text
Hello.class
```

(Bytecode)

↓

### Step 3

JVM executes:

```text
Hello.class
```

↓

Machine Code

↓

CPU

---

# Complete Flow

```text
.java File

    |

 javac

    |

.class File

(Bytecode)

    |

Class Loader

    |

Bytecode Verifier

    |

Interpreter

    |

JIT Compiler

    |

Machine Code

    |

CPU
```

---

# 3. Why Java is Platform Independent?

Because:

```text
Compiler

Creates

Bytecode
```

NOT:

```text
Windows Code

Linux Code

Mac Code
```

Bytecode:

```text
Runs on any JVM.
```

Hence:

```text
Write Once

Run Anywhere
```

---

# 4. Class Loader Subsystem

Responsible for loading class files.

Three class loaders:

```text
Bootstrap ClassLoader

↓

Platform ClassLoader

↓

Application ClassLoader
```

---

## Bootstrap ClassLoader

Loads:

```text
java.lang.*

java.util.*

java.io.*
```

Example:

```java
String

Integer

Object
```

Loaded by:

```text
Bootstrap ClassLoader
```

---

## Platform ClassLoader

Loads:

```text
jdk.*

javax.*
```

---

## Application ClassLoader

Loads:

```text
User Defined Classes
```

Example:

```java
Student

Employee

Main
```

---

# Delegation Model

```text
Application Loader

      |

Platform Loader

      |

Bootstrap Loader
```

First asks parent.

If parent cannot load:

Child loads class.

---

# 5. JVM Memory Areas

JVM memory is divided into:

```text
                 JVM Memory


      --------------------------------


Heap

Stack

Method Area

PC Register

Native Method Stack
```

---

# Heap Memory

Stores:

```text
Objects

Arrays
```

Example:

```java
Student s = new Student();
```

Object:

```text
Stored in Heap
```

---

# Heap Structure

```text
Heap

|

+--- Young Generation

|      |

|      + Eden

|      + S0

|      + S1


+--- Old Generation


+--- Metaspace
```

---

# Young Generation

New objects are created here.

Contains:

```text
Eden Space

Survivor 0

Survivor 1
```

---

# Old Generation

Objects surviving long time are moved here.

Example:

```text
Singleton Objects

Long Lived Objects
```

---

# Metaspace

Stores:

```text
Class Metadata

Methods

Static Variables

Constant Pool
```

Before Java 8:

```text
PermGen
```

After Java 8:

```text
Metaspace
```

---

# 6. Stack Memory

Stores:

```text
Method Frames

Local Variables

References
```

Example:

```java
void test(){

int x = 10;

}
```

x stored in:

```text
Stack
```

---

# Stack Frame

Each method call creates:

```text
Stack Frame
```

Contains:

```text
Local Variables

Operand Stack

Return Address
```

---

Example

```java
main()

↓

fun()

↓

add()
```

Memory:

```text
Stack


+-------+

add()

+-------+

fun()

+-------+

main()

+-------+
```

---

# StackOverflowError

Example:

```java
void fun(){

fun();

}
```

Infinite recursion.

Output:

```text
StackOverflowError
```

Because:

```text
Stack becomes full.
```

---

# Heap vs Stack

| Heap       | Stack           |
| ---------- | --------------- |
| Objects    | Local Variables |
| Shared     | Thread Specific |
| GC Managed | Auto Managed    |
| Bigger     | Smaller         |
| Slower     | Faster          |

---

# 7. Program Counter Register

Stores:

```text
Current Executing Instruction Address
```

Every thread has:

```text
Separate PC Register
```

---

# 8. Native Method Stack

Stores:

```text
Native Methods
```

Methods written in:

```text
C

C++
```

Example:

```java
System.arraycopy()
```

Uses native code.

---

# 9. Garbage Collection

Garbage Collector removes:

```text
Unused Objects
```

Example:

```java
Student s = new Student();

s = null;
```

Object becomes:

```text
Eligible for GC
```

---

# GC Algorithm

```text
Object Reachable

↓

Yes

↓

Keep Object



No

↓

Delete Object
```

---

# 10. Types of Garbage Collectors

Java provides:

```text
Serial GC

Parallel GC

G1 GC

ZGC

Shenandoah GC
```

---

# Serial GC

Single Threaded.

Suitable for:

```text
Small Applications
```

JVM Option:

```bash
-XX:+UseSerialGC
```

---

# Parallel GC

Multiple threads.

Throughput oriented.

```bash
-XX:+UseParallelGC
```

---

# G1 GC

Default from Java 9.

Stands for:

```text
Garbage First
```

Divides heap into:

```text
Regions
```

Collects garbage from:

```text
Most Garbage Region First
```

---

# ZGC

Low latency collector.

Supports:

```text
Very Large Heap

TBs of Memory
```

Pause Time:

```text
Few milliseconds
```

---

# 11. String Constant Pool

Most famous interview topic.

Example:

```java
String a = "Java";

String b = "Java";
```

Output:

```java
a == b
```

```text
true
```

Because:

```text
String Pool reuses object.
```

---

Memory

```text
String Pool


+---------+

| "Java" |

+---------+

    ↑

a   b
```

---

# new String()

```java
String a = new String("Java");

String b = new String("Java");
```

Output:

```text
a==b

false
```

Because:

```text
Different Heap Objects.
```

---

# 12. Integer Cache

```java
Integer a = 100;

Integer b = 100;

System.out.println(a==b);
```

Output:

```text
true
```

Because:

```text
Integer Cache

-128 to 127
```

---

```java
Integer a = 200;

Integer b = 200;
```

Output:

```text
false
```

Outside cache range.

---

# 13. Class Loading Process

Three phases:

```text
Loading

↓

Linking

↓

Initialization
```

---

### Loading

ClassLoader loads:

```text
.class file
```

---

### Linking

Three steps:

```text
Verification

Preparation

Resolution
```

---

### Initialization

Static variables initialized.

Static blocks executed.

---

Example

```java
class A{

static{

System.out.println("Loaded");

}

}
```

---

# 14. JVM Architecture Diagram

```text
             .java File

                  |

               javac

                  |

             .class File

                  |

           Class Loader

                  |

         Bytecode Verifier

                  |

        Interpreter + JIT

                  |

             JVM Memory

        -------------------

        Heap

        Stack

        Method Area

        PC Register

        Native Stack

                  |

                 CPU
```

---

# 15. Interpreter vs JIT Compiler

Interpreter:

```text
Reads line by line

Slower
```

---

JIT:

```text
Converts bytecode

↓

Machine Code

Stores in Cache

Faster
```

---

# JVM Memory Example

```java
Student s = new Student();
```

Memory:

```text
Stack

s

↓

Heap

+------------+

Student Obj

+------------+
```

---

# ==================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. Difference between JVM, JRE and JDK?

| JVM           | JRE                 | JDK              |
| ------------- | ------------------- | ---------------- |
| Executes code | Runtime Environment | Development Kit  |
| Part of JRE   | Part of JDK         | Complete Package |

---

### Q2. Why Java is Platform Independent?

**Answer**

Because:

```text
Compiler

↓

Bytecode

↓

JVM of Target OS
```

---

### Q3. Heap vs Stack?

| Heap       | Stack           |
| ---------- | --------------- |
| Objects    | Local Variables |
| Shared     | Thread Specific |
| GC Managed | Auto Managed    |

---

### Q4. What causes StackOverflowError?

```java
void fun(){

fun();

}
```

Infinite recursion.

---

### Q5. What causes OutOfMemoryError?

```java
while(true){

new Object();

}
```

Heap becomes full.

---

### Q6. What is String Constant Pool?

Stores:

```text
String Literals
```

to avoid duplicate objects.

---

### Q7. What is IntegerCache?

Caches:

```text
-128 to 127
```

for Integer objects.

---

### Q8. What is Metaspace?

Stores:

```text
Class Metadata

Methods

Static Variables
```

---

### Q9. Difference between Interpreter and JIT?

| Interpreter  | JIT                  |
| ------------ | -------------------- |
| Line by Line | Compiles Native Code |
| Slow         | Fast                 |

---

### Q10. What is G1 GC?

**Answer**

```text
Garbage First Collector
```

Default collector in modern Java.

Collects:

```text
Region with maximum garbage first.
```

---

# 🔥 SDE Takeaway

Remember these:

✅ JDK = JRE + Development Tools

✅ JRE = JVM + Libraries

✅ Heap → Objects

✅ Stack → Local Variables

✅ Metaspace → Class Metadata

✅ String Pool stores literals

✅ Integer Cache = -128 to 127

✅ StackOverflowError → Stack Full

✅ OutOfMemoryError → Heap Full

✅ G1 GC is default GC in modern Java

These JVM internals are among the **most frequently asked SDE interview topics** because they test:

* Memory Management
* Garbage Collection
* String Pool
* IntegerCache
* Heap vs Stack
* JVM Architecture
* Class Loading Process
