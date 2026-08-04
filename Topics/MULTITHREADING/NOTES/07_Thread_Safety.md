# 🛡️ 07. Thread Safety

> [!NOTE]
> A program is **thread-safe** if it behaves correctly when accessed by multiple threads simultaneously, without producing incorrect results, corrupting data, or causing unexpected behavior.
>
> Thread safety is **not a feature of threads**—it is a property of your code and design.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is Thread Safety?](#2-what-is-thread-safety)
- [3. Why Thread Safety Matters](#3-why-thread-safety-matters)
- [4. Characteristics of Thread-Safe Code](#4-characteristics-of-thread-safe-code)
- [5. Thread-Safe vs Non-Thread-Safe](#5-thread-safe-vs-non-thread-safe)
- [6. Stateless Objects](#6-stateless-objects)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Thread
- Runnable
- Thread Lifecycle
- Synchronization
- synchronized Keyword
- Race Condition
- Shared Resources
- Critical Section

---

# 2. What is Thread Safety?

A class, method, or program is **thread-safe** if multiple threads can use it simultaneously without causing incorrect behavior.

In other words,

> No matter how many threads execute the code, the result remains correct.

---

## Definition

> Thread Safety is the property of a program that guarantees correct behavior when multiple threads execute concurrently.

---

## Example

Suppose two threads deposit money into the same account.

```
Initial Balance

₹1000
```

Thread A

```
Deposit ₹500
```

Thread B

```
Deposit ₹300
```

Correct Result

```
₹1800
```

If the final balance is always

```
₹1800
```

regardless of thread scheduling,

the implementation is thread-safe.

---

## Real-Life Analogy

Imagine a bank locker.

```
Locker

      │

 ┌────┴────┐

 ▼         ▼

Alice     Bob
```

The bank ensures

- no data is lost
- no corruption occurs
- operations remain consistent

Even when many customers use lockers every day.

A thread-safe class behaves similarly.

---

# 3. Why Thread Safety Matters

Modern applications are highly concurrent.

Examples

- Web Servers
- Banking Systems
- E-Commerce
- Gaming
- Chat Applications
- Cloud Services

Hundreds or even thousands of threads may access the same objects.

Without thread safety,

applications may produce

- Incorrect calculations
- Lost updates
- Data corruption
- Security issues
- Application crashes

---

## Example

Suppose an online shopping website has

```
Stock = 1
```

Two customers purchase simultaneously.

Without thread safety

```
Customer A

↓

Purchase

----------------

Customer B

↓

Purchase
```

Both orders may succeed.

Now

```
Inventory = -1
```

This is incorrect.

---

# 4. Characteristics of Thread-Safe Code

A thread-safe program generally has the following properties.

---

## Correctness

Regardless of execution order,

results remain correct.

---

## Data Consistency

Shared data never enters an invalid state.

---

## Predictability

Execution timing does not change correctness.

---

## Safe Concurrent Access

Multiple threads can access the program safely.

---

## No Race Conditions

Shared mutable state is properly protected.

---

## Visualization

```
Multiple Threads

        │

        ▼

Thread-Safe Code

        │

        ▼

Correct Result
```

---

# 5. Thread-Safe vs Non-Thread-Safe

## Thread-Safe

```
Thread A

↓

Shared Object

↑

Thread B

↓

Correct Result
```

No corruption occurs.

---

## Non-Thread-Safe

```
Thread A

↓

Shared Object

↑

Thread B

↓

Race Condition

↓

Incorrect Result
```

---

## Comparison

| Thread-Safe | Non-Thread-Safe |
|-------------|-----------------|
| Correct under concurrency | May fail under concurrency |
| Predictable | Timing dependent |
| Shared data protected | Shared data unprotected |
| Suitable for multiple threads | Unsafe for concurrent access |

---

# 6. Stateless Objects

One of the easiest ways to achieve thread safety is to create **stateless objects**.

A stateless object stores **no mutable instance data**.

Example

```java
class Calculator {

    public int add(int a, int b) {

        return a + b;

    }

}
```

Notice

The class contains

- No instance variables
- No shared mutable state

Every method depends only on its parameters.

---

## Why Is It Thread-Safe?

Suppose

```
Thread A

↓

add(5,10)

Thread B

↓

add(100,200)
```

Neither thread affects the other.

Each invocation has its own local variables.

---

## Visualization

```
Thread A

↓

Local Variables

---------------------

Thread B

↓

Local Variables
```

Since local variables live on each thread's own stack,

they are **not shared**.

Therefore,

stateless classes are naturally thread-safe.

> [!TIP]
> Stateless classes are the easiest classes to make thread-safe because there is no shared mutable state to protect.

---

## Real-World Examples

Examples of stateless services include

- Mathematical utilities
- Validators
- String formatters
- Unit converters
- Password strength checkers

These classes typically operate only on method parameters and return results without storing state.

---

# 7. Immutable Objects ⭐⭐⭐⭐⭐

One of the most powerful techniques for achieving thread safety is **immutability**.

An **immutable object** is an object whose state **cannot be changed after it is created**.

Once an immutable object is constructed,

its data remains constant throughout its lifetime.

---

## Definition

> An immutable object is an object whose internal state cannot be modified after construction.

---

## Mutable vs Immutable

### Mutable Object

The state can change.

Example

```java
class Student {

    String name;

}
```

```java
Student student = new Student();

student.name = "Mahesh";

student.name = "Rahul";
```

The object's state changes.

---

### Immutable Object

The state cannot change.

```java
final class Student {

    private final String name;

    public Student(String name) {

        this.name = name;

    }

    public String getName() {

        return name;

    }

}
```

Usage

```java
Student student = new Student("Mahesh");
```

There is **no setter**.

The value can never change.

---

## Characteristics of Immutable Objects

An immutable class usually follows these rules.

- Class is `final`
- Fields are `private`
- Fields are `final`
- No setter methods
- State initialized through constructor
- Mutable fields are defensively copied

---

## Why Are Immutable Objects Thread-Safe?

Suppose

```
Student student = new Student("Mahesh");
```

Two threads access the object.

```
Thread A

↓

Read Name

-----------------------

Thread B

↓

Read Name
```

Both threads only read data.

Nobody modifies it.

Therefore,

no race condition can occur.

---

## Visualization

```
                Student

        name = "Mahesh"

               Read Only

         ▲              ▲

         │              │

     Thread A      Thread B
```

Multiple threads can safely access immutable objects simultaneously.

---

## Real-World Examples

Many Java classes are immutable.

Examples include

```java
String

Integer

Long

Double

BigInteger

BigDecimal

LocalDate

LocalTime

LocalDateTime

UUID
```

These classes can safely be shared among multiple threads.

---

## Example

```java
String name = "Mahesh";
```

Suppose

```
Thread A

↓

Print Name

--------------------

Thread B

↓

Convert to Uppercase
```

Methods like

```java
toUpperCase()
```

do **not** modify the original object.

Instead,

they create a **new String**.

```java
String upper = name.toUpperCase();
```

Original

```text
Mahesh
```

New Object

```text
MAHESH
```

The original object remains unchanged.

---

## Advantages

✅ Naturally thread-safe

✅ Easy to reason about

✅ No synchronization required

✅ Can be safely shared

✅ Suitable for caching

✅ Excellent for concurrent applications

---

## Disadvantages

❌ New objects are created instead of modifying existing ones.

❌ May increase memory usage in some situations.

However,

modern JVMs optimize object allocation efficiently,

so immutability is often preferred over mutable shared state.

---

# 8. Thread Confinement

Instead of protecting shared data,

another strategy is

> **Don't share it at all.**

This is called

```
Thread Confinement
```

---

## Definition

Thread Confinement means

> Each thread owns its own data.

No other thread can access it.

---

## Example

Suppose every thread creates its own object.

```java
class Counter {

    int count = 0;

}
```

```
Thread A

↓

Counter A

-------------------

Thread B

↓

Counter B
```

Each thread modifies its own object.

Nothing is shared.

Therefore,

no synchronization is needed.

---

## Visualization

```
Thread A

↓

Counter A



Thread B

↓

Counter B



Thread C

↓

Counter C
```

Every thread has its own copy.

---

## ThreadLocal

Java provides

```java
ThreadLocal
```

to implement thread confinement.

Example

```java
ThreadLocal<Integer> counter =
        ThreadLocal.withInitial(() -> 0);
```

Each thread gets

its own independent value.

We'll study

```java
ThreadLocal
```

in a later chapter.

---

## Real-World Uses

- Database Connections
- User Sessions
- Request Context
- Date Formatting
- Transaction Information

---

# 9. Defensive Copying

Suppose a class stores a mutable object.

Example

```java
class Student {

    private Date dob;

}
```

Problem

The caller still holds a reference to

```java
dob
```

and can modify it.

---

## Bad Design

```java
this.dob = dob;
```

Now

both objects share the same

```java
Date
```

instance.

---

## Good Design

```java
this.dob = new Date(dob.getTime());
```

A copy is created.

External code cannot modify the internal state.

---

## Getter

Instead of

```java
return dob;
```

return

```java
return new Date(dob.getTime());
```

This protects internal data.

---

## Why?

Without defensive copying

```
Caller

↓

Date Object

↑

Student
```

The caller can change

Student's internal state.

With defensive copying

```
Caller

↓

Copy

Student

↓

Own Copy
```

No shared mutable object exists.

---

# 10. Safe Publication

Creating a thread-safe object is not enough.

Other threads must also receive it safely.

This is called

```
Safe Publication
```

---

## Example

```java
final Student student =
        new Student("Mahesh");
```

Publishing through

- final fields
- static initialization
- synchronized blocks
- concurrent collections

ensures other threads observe the correctly constructed object.

---

> [!IMPORTANT]
> An immutable object that is **unsafely published** may still lead to visibility issues.
>
> We'll revisit this concept when studying the **Java Memory Model (JMM)** and the `volatile` keyword.

---

# 11. Singleton Thread Safety

A **Singleton** ensures that only one instance of a class exists in the application.

Since the same instance is shared by multiple threads,

it must be designed carefully.

---

## Unsafe Singleton

```java
class Singleton {

    private static Singleton instance;

    public static Singleton getInstance() {

        if (instance == null) {

            instance = new Singleton();

        }

        return instance;

    }

}
```

### Problem

Suppose two threads execute

```java
getInstance();
```

simultaneously.

```
Thread A

↓

instance == null

-------------------------

Thread B

↓

instance == null

-------------------------

Thread A

Creates Object

-------------------------

Thread B

Creates Object
```

Now,

two different objects exist.

The Singleton property is violated.

---

## Thread-Safe Singleton

```java
class Singleton {

    private static final Singleton INSTANCE =
            new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {

        return INSTANCE;

    }

}
```

This approach is

- Simple
- Thread-safe
- Recommended in most cases

> [!TIP]
> We'll study Singleton patterns and lazy initialization in detail in the Design Patterns module.

---

# 12. Thread-Safe Collections

Java provides several thread-safe collections.

These collections allow multiple threads to access them safely.

Examples

```java
Vector

Hashtable

Collections.synchronizedList()

Collections.synchronizedMap()

Collections.synchronizedSet()
```

---

## Example

```java
List<Integer> list =
    Collections.synchronizedList(
        new ArrayList<>()
    );
```

Now,

multiple threads can safely modify

```java
list
```

using the synchronized wrapper.

---

## Limitation

Thread-safe collections may still require external synchronization during iteration.

Example

```java
synchronized (list) {

    for (Integer value : list) {

        System.out.println(value);

    }

}
```

We'll study this in detail with collections.

---

# 13. Concurrent Collections (Overview)

Instead of synchronizing the entire collection,

Java provides highly optimized concurrent collections.

Examples

```java
ConcurrentHashMap

CopyOnWriteArrayList

ConcurrentLinkedQueue

BlockingQueue
```

These classes are designed specifically for concurrent programming.

---

## Why Use Them?

Traditional synchronized collections lock the entire collection.

Concurrent collections often use finer-grained synchronization or lock-free techniques,

allowing higher concurrency.

---

## Comparison

| Synchronized Collections | Concurrent Collections |
|---------------------------|------------------------|
| Lock entire collection | Reduce lock contention |
| Lower concurrency | Higher concurrency |
| Older approach | Modern concurrent design |

> [!NOTE]
> We'll study these collections in dedicated chapters later in this handbook.

---

# 14. Common Thread-Safety Mistakes

---

## ❌ Sharing Mutable Objects

Bad

```java
Student student = new Student();
```

Shared by many threads.

Instead,

consider making the object immutable or protecting access appropriately.

---

## ❌ Synchronizing Everything

Bad

```java
public synchronized void method1() {

}

public synchronized void method2() {

}

public synchronized void method3() {

}
```

Unnecessary synchronization reduces concurrency.

Protect only shared mutable state.

---

## ❌ Assuming Local Variables Need Synchronization

Local variables are stored on each thread's stack.

```java
public void calculate() {

    int sum = 0;

}
```

Each thread has its own

```java
sum
```

No synchronization required.

---

## ❌ Returning Mutable Internal Objects

Bad

```java
public Date getDob() {

    return dob;

}
```

The caller can modify the object's internal state.

Return a defensive copy instead.

---

## ❌ Ignoring Safe Publication

Even correctly designed objects should be safely published to other threads.

Improper publication can lead to visibility problems.

---

# 15. Best Practices

✅ Prefer immutable objects whenever possible.

✅ Minimize shared mutable state.

✅ Keep critical sections small.

✅ Use thread confinement when practical.

✅ Prefer concurrent collections over manual synchronization for shared collections.

✅ Use `AtomicInteger` for simple counters instead of synchronizing entire methods.

✅ Document thread-safety guarantees in public APIs.

---

# 16. Interview Questions

### 1. What is Thread Safety?

Thread safety is the property of a program that guarantees correct behavior when accessed concurrently by multiple threads.

---

### 2. Why are Stateless Objects thread-safe?

Because they contain no shared mutable state.

Each method works only with its parameters and local variables.

---

### 3. Why are Immutable Objects thread-safe?

Because their state cannot change after construction.

Multiple threads can safely read the same object without synchronization.

---

### 4. What is Thread Confinement?

Each thread owns its own data,

so no sharing occurs.

---

### 5. What is Defensive Copying?

Creating copies of mutable objects to prevent external code from modifying internal state.

---

### 6. What is Safe Publication?

Safely making an object visible to other threads so they observe its correctly initialized state.

---

### 7. Difference between Synchronized Collections and Concurrent Collections?

Synchronized collections generally use a single lock.

Concurrent collections use more advanced techniques to improve concurrency.

---

### 8. Which is better?

```
Synchronization

or

Immutable Objects
```

Whenever practical,

immutable objects are generally preferred because they eliminate many concurrency problems instead of protecting against them.

---

# 17. Quick Revision

```text
                  Thread Safety

                        │

                        ▼

          Correct Under Concurrency

                        │

        ┌───────────────┼────────────────┐

        ▼               ▼                ▼

 Stateless      Immutable Objects   Thread Confinement

                        │

                        ▼

          Less Shared Mutable State

                        │

                        ▼

        Fewer Race Conditions
```

---

## Design Pyramid

```text
               Best

        Immutable Objects
               ▲
               │
      Thread Confinement
               ▲
               │
    Concurrent Collections
               ▲
               │
       Synchronization

            Foundation
```

The higher you move in the pyramid,

the fewer synchronization problems you generally need to solve.

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What is Thread Safety?
- [x] Characteristics of Thread-Safe Code
- [x] Stateless Objects
- [x] Immutable Objects
- [x] Thread Confinement
- [x] Defensive Copying
- [x] Safe Publication
- [x] Thread-Safe Singleton
- [x] Thread-Safe Collections
- [x] Concurrent Collections (Overview)
- [x] Best Practices

---

# 📌 Key Takeaways

- Thread safety is a **property of good design**, not just synchronization.
- Stateless classes are naturally thread-safe.
- Immutable objects are among the simplest and most reliable ways to achieve thread safety.
- Thread confinement avoids sharing and often removes the need for synchronization.
- Defensive copying protects mutable internal state.
- Concurrent collections are generally preferred over older synchronized collection wrappers for highly concurrent applications.
- Minimize shared mutable state whenever possible.

> [!TIP]
> **Interview Rule**
>
> When asked how to make code thread-safe, don't immediately answer **"use `synchronized`."**
>
> First ask:
>
> - Can the object be immutable?
> - Can each thread have its own copy?
> - Can a concurrent collection or atomic class solve the problem?
> - Only then consider explicit synchronization.

---

# 📖 Next Topic

➡️ **08_Deadlock.md**

In the next chapter, you'll learn:

- What is Deadlock?
- Coffman Conditions
- Circular Wait
- Deadlock Example
- Deadlock Detection
- Deadlock Prevention
- Deadlock Avoidance
- Lock Ordering
- Timeout Locks
- Real-World Examples

> [!IMPORTANT]
> Deadlock is one of the most frequently asked concurrency topics in SDE interviews and a common source of production issues in multithreaded systems.
