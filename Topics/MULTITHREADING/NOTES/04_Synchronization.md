# 🔒 04. Synchronization

> [!NOTE]
> **Synchronization** is a mechanism that controls access to shared resources in a multithreaded environment.
>
> It ensures that only one thread executes a critical section at a time, preventing inconsistent data and unexpected behavior.

---

# 📚 Table of Contents

- [1. What is Synchronization?](#1-what-is-synchronization)
- [2. Why Do We Need Synchronization?](#2-why-do-we-need-synchronization)
- [3. The Problem Without Synchronization](#3-the-problem-without-synchronization)
- [4. Shared Resources](#4-shared-resources)
- [5. Concurrent Access](#5-concurrent-access)
- [6. Critical Section](#6-critical-section)
- [7. Mutual Exclusion](#7-mutual-exclusion)
- [8. High-Level Working of Synchronization](#8-high-level-working-of-synchronization)

---

# 1. What is Synchronization?

Synchronization is a mechanism that **coordinates multiple threads** when they access the same shared resource.

Its primary goal is to ensure that shared data remains **consistent and correct**, even when many threads are running simultaneously.

Without synchronization, multiple threads may try to modify the same data at the same time, leading to unpredictable results.

> [!IMPORTANT]
> Synchronization is **not about making code faster**.
>
> It is about making concurrent code **correct and reliable**.

---

## Definition

> **Synchronization is the process of controlling concurrent access to shared resources so that only one thread executes a critical section at a time.**

---

## Real-Life Analogy

Imagine a single ATM machine.

```text
             ATM Machine

      Person A
          │
          ▼

      ┌─────────┐
      │   ATM   │
      └─────────┘

          ▲
          │

      Person B
```

Both people cannot use the ATM simultaneously.

The ATM allows only one customer at a time.

Synchronization works in the same way.

```text
Shared Resource
       │
       ▼
Only One Thread
Can Access At A Time
```

---

# 2. Why Do We Need Synchronization?

Suppose two threads access the same bank account.

Initial Balance

```text
₹1000
```

Thread A

```text
Withdraw ₹500
```

Thread B

```text
Withdraw ₹700
```

If both execute at exactly the same time,

they may both read

```text
₹1000
```

before either updates the balance.

This can produce incorrect results.

> [!WARNING]
> Multiple threads reading and writing shared data simultaneously can lead to inconsistent program state.

---

## Another Example

Suppose two employees update the same spreadsheet.

```text
Employee A
        │
        ▼

      Salary = 60000

Employee B
        │
        ▼

      Salary = 65000
```

If both save their changes simultaneously,

one update may overwrite the other.

The same problem exists in multithreaded applications.

---

# 3. The Problem Without Synchronization

Consider a simple counter.

```java
class Counter {

    int count = 0;

    void increment() {

        count++;

    }

}
```

Suppose two threads execute

```java
increment();
```

at the same time.

We expect

```text
0

↓

1

↓

2
```

But sometimes we get

```text
0

↓

1
```

instead.

Why?

Because

```java
count++;
```

is **not** a single operation.

Internally it is approximately

```text
Read count

↓

Add 1

↓

Write count
```

If two threads perform these three steps simultaneously,

the final result can become incorrect.

> [!TIP]
> This type of problem is called a **Race Condition**.
>
> We'll study it in depth in **06_Race_Condition.md**.

---

## Visualization

Without Synchronization

```text
Thread A

Read count = 0
        │
        │
        ▼

Thread B

Read count = 0

        │
        ▼

Thread A

Write 1

        │
        ▼

Thread B

Write 1
```

Expected

```text
2
```

Actual

```text
1
```

---

# 4. Shared Resources

A **shared resource** is any object, variable, file, or database that can be accessed by multiple threads.

Examples

- Shared Object
- Static Variable
- File
- Database Record
- Network Connection
- Collection
- Counter
- Cache

Example

```java
class Counter {

    int count = 0;

}
```

If multiple threads access

```java
count
```

then

```text
count
```

is a shared resource.

---

## Visualization

```text
             Shared Resource

                  count

        ▲           ▲           ▲

        │           │           │

    Thread A    Thread B    Thread C
```

Whenever multiple threads access the same resource,

care must be taken to avoid inconsistent updates.

---

# 5. Concurrent Access

Concurrent access means multiple threads attempt to use the same resource during overlapping periods of execution.

Example

```text
Thread A

Updating Balance

--------------------------

Thread B

Updating Balance
```

Without proper coordination,

their operations may interleave unpredictably.

This is known as **concurrent access**.

---

## Concurrent Access Doesn't Always Mean a Problem

If multiple threads only **read** the same data,

there is generally no issue.

Example

```text
Thread A

Read Student Name

Thread B

Read Student Name

Thread C

Read Student Name
```

No thread modifies the data.

Therefore,

this is usually safe.

Problems arise when at least one thread performs a **write** operation.

---

# 6. Critical Section

A **Critical Section** is the part of a program that accesses shared resources.

Only one thread should execute the critical section at a time.

Example

```java
count++;
```

is a critical section because

multiple threads modify the same variable.

---

## Visualization

```text
Thread

      │

      ▼

Normal Code

      │

      ▼

**********************
* Critical Section   *
**********************

      │

      ▼

Normal Code
```

The goal of synchronization is to protect this critical section.

---

## Important Point

Not every line of code requires synchronization.

Only the code that accesses **shared mutable data** needs protection.

Synchronizing unnecessary code can reduce performance.

---

# 7. Mutual Exclusion

**Mutual Exclusion** means

> Only one thread can enter the critical section at a time.

If one thread is already inside,

other threads must wait.

Visualization

```text
Critical Section

        │

        ▼

 ┌──────────────┐

 │  Thread A    │

 └──────────────┘

 Thread B → WAIT

 Thread C → WAIT
```

Once Thread A leaves,

another waiting thread may enter.

---

## Real-Life Example

Imagine a single-person elevator.

```text
Elevator

      │

      ▼

Person A

Person B → WAIT

Person C → WAIT
```

Only one person can occupy the elevator at a time.

This is mutual exclusion.

---

# 8. High-Level Working of Synchronization

Conceptually,

Synchronization works like this.

```text
Thread Requests Access

          │

          ▼

Is Resource Available?

      ┌───────────────┐

      │      Yes      │

      ▼               │

Enter Critical Section│

      │               │

Finish Work           │

      │               │

Leave Critical Section│

      ▼               │

Release Access ◄──────┘

No

↓

Wait Until Resource Becomes Available
```

At a high level,

Synchronization ensures that

- shared data remains consistent,
- only one thread modifies shared state at a time,
- other threads wait until the protected section becomes available.

> [!IMPORTANT]
> In Java, this coordination is implemented using **monitors** and **locks**.
>
> We'll study these in detail in the next chapter:
>
> **05_Synchronized_Keyword.md**

- Performance Considerations
