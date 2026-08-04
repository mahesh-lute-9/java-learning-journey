# ⚡ 13. volatile Keyword

> [!NOTE]
> The `volatile` keyword is a **visibility modifier** in Java that ensures changes made to a variable by one thread are immediately visible to other threads.
>
> It **does not provide atomicity** and **does not replace synchronization**.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is `volatile`?](#2-what-is-volatile)
- [3. Why Do We Need `volatile`?](#3-why-do-we-need-volatile)
- [4. Memory Visibility Problem](#4-memory-visibility-problem)
- [5. CPU Cache vs Main Memory](#5-cpu-cache-vs-main-memory)
- [6. How `volatile` Works](#6-how-volatile-works)
- [7. Happens-Before Relationship](#7-happens-before-relationship)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Thread
- Synchronization
- Race Condition
- Thread Safety
- Java Memory Model (basic idea)
- `synchronized`

---

# 2. What is `volatile`?

The

```java
volatile
```

keyword tells the JVM

> **Always read the latest value of this variable from main memory, and always write updates back to main memory immediately.**

Without

```java
volatile
```

different threads may observe different values of the same variable.

---

## Definition

> `volatile` is a Java keyword that guarantees **visibility** of variable updates across threads.

---

## Syntax

```java
volatile boolean running = true;
```

---

## Important

`volatile`

provides

```text
✔ Visibility

✔ Ordering (Happens-Before)

✘ Atomicity
```

---

# 3. Why Do We Need `volatile`?

Suppose two threads share a variable.

```java
boolean running = true;
```

---

## Thread A

```java
while (running) {

    // Work

}
```

---

## Thread B

```java
running = false;
```

Expected

```
Thread A Stops
```

---

## But Sometimes...

Thread A may continue forever.

Why?

Because it may never see the updated value.

This is called the

```
Visibility Problem
```

---

# 4. Memory Visibility Problem

Modern CPUs use multiple cache levels to improve performance.

Each thread may read values from a CPU cache instead of main memory.

---

## Visualization

```text
              Main Memory

            running = false

                 ▲

        ┌────────┴────────┐

        │                 │

CPU Cache A          CPU Cache B

running=true      running=false

        ▲                 ▲

        │                 │

    Thread A         Thread B
```

Thread B updates the variable.

Thread A still reads an old cached value.

Result

```
Incorrect Behaviour
```

---

## Example

```java
class Task {

    boolean running = true;

}
```

Thread A

```java
while (task.running) {

}
```

Thread B

```java
task.running = false;
```

Without

```java
volatile
```

Thread A may never observe

```text
false
```

---

# 5. CPU Cache vs Main Memory

To understand `volatile`,

we must understand memory hierarchy.

---

## Main Memory (RAM)

Shared by all threads.

```
Main Memory

↓

Shared Data
```

---

## CPU Cache

Each processor core maintains its own cache.

Reading from cache is much faster than reading from RAM.

```
CPU Cache

↓

Fast Access
```

---

## Problem

Suppose

```
Main Memory

count = 10
```

Thread A reads

```
10
```

into Cache A.

Later,

Thread B updates

```
count = 20
```

Main memory now contains

```
20
```

Thread A may still read

```
10
```

from its cache.

---

## Visualization

```text
              RAM

           count = 20

           ▲       ▲

           │       │

      Cache A   Cache B

     count=10  count=20

           ▲       ▲

           │       │

      Thread A Thread B
```

Without proper synchronization,

cached values may become stale.

---

# 6. How `volatile` Works

Suppose we declare

```java
volatile boolean running = true;
```

Now

every write

```java
running = false;
```

is written directly to main memory.

Every read

```java
running
```

is read from main memory.

---

## Visualization

Without `volatile`

```text
Thread

↓

CPU Cache

↓

Old Value
```

With `volatile`

```text
Thread

↓

Main Memory

↓

Latest Value
```

---

## High-Level Flow

```text
Thread B

↓

Write

↓

Main Memory

────────────────────────

Thread A

↓

Read

↓

Main Memory

↓

Latest Value
```

This guarantees visibility.

---

# 7. Happens-Before Relationship

One of the most important guarantees provided by

```java
volatile
```

is the

```
Happens-Before Relationship
```

---

## Simple Meaning

If

Thread B writes

```java
running = false;
```

to a volatile variable,

and

Thread A later reads

that same volatile variable,

then

Thread A is guaranteed to see the updated value.

---

## Visualization

```text
Thread B

↓

Write volatile

↓

Main Memory

↓

Happens-Before

↓

Thread A

↓

Read volatile

↓

Latest Value
```

---

## Important

This guarantee applies to **visibility and ordering**.

It does **not** make compound operations like

```java
count++;
```

atomic.

---

# 8. Why `volatile` Is Not Thread-Safe ⭐⭐⭐⭐⭐

Many beginners believe

```java
volatile
```

makes every operation thread-safe.

This is **incorrect**.

`volatile`

only guarantees

- Visibility
- Ordering

It does **not** guarantee

```
Atomicity
```

---

## Example

```java
class Counter {

    volatile int count = 0;

    public void increment() {

        count++;

    }

}
```

At first glance,

this looks thread-safe.

It is **not**.

---

## Why?

Because

```java
count++;
```

is **not** a single operation.

Internally,

it is approximately

```text
Read count

↓

Add 1

↓

Write count
```

Even though every read and write is visible,

another thread can still modify

```java
count
```

between those steps.

---

## Timeline

Initial Value

```text
count = 0
```

```
Time →

Thread A

Read 0

────────────────────

Thread B

Read 0

────────────────────

Thread A

Write 1

────────────────────

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

This is a **Lost Update**.

`volatile`

did **not** prevent it.

---

# 9. Visibility vs Atomicity ⭐⭐⭐⭐⭐

Understanding this difference is essential.

---

## Visibility

Visibility means

```
One Thread Updates

↓

Other Threads See It
```

Example

```java
volatile boolean running = true;
```

Thread B

```java
running = false;
```

Thread A immediately observes

```text
false
```

---

## Atomicity

Atomicity means

```
Operation

↓

Cannot Be Interrupted
```

Example

```java
count++;
```

is **not atomic**.

Another thread may interrupt between

- Read
- Modify
- Write

---

## Comparison

| Visibility | Atomicity |
|------------|-----------|
| Latest value is visible | Entire operation executes as one indivisible unit |
| Provided by `volatile` | Not provided by `volatile` |
| Prevents stale reads | Prevents lost updates |

---

## Visualization

```text
volatile

↓

Visibility

──────────────────

synchronized

↓

Visibility

+

Atomicity
```

---

# 10. `volatile` vs `synchronized`

Both keywords are related to concurrency,

but they solve different problems.

---

## Comparison

| `volatile` | `synchronized` |
|-------------|----------------|
| Visibility | Visibility + Mutual Exclusion |
| No locking | Uses monitor locks |
| No atomicity | Protects critical sections |
| Faster for simple visibility use cases | Higher overhead due to locking |
| Suitable for status flags and configuration values | Suitable for shared mutable state requiring compound operations |

---

## Example

### Good Candidate for `volatile`

```java
volatile boolean shutdown = false;
```

One thread updates

```java
shutdown
```

Other threads simply read it.

---

### Poor Candidate for `volatile`

```java
volatile int counter = 0;

counter++;
```

This still has a race condition.

---

### Better Solution

```java
AtomicInteger counter =
        new AtomicInteger();

counter.incrementAndGet();
```

or

```java
synchronized
```

---

# 11. JVM Memory Barriers (High Level)

Internally,

the JVM uses

```
Memory Barriers
```

to implement

```java
volatile
```

---

## What Is a Memory Barrier?

A memory barrier is a CPU/JVM instruction that controls the ordering and visibility of memory operations.

It prevents certain optimizations that could make updates invisible to other threads.

---

## High-Level Flow

Without

```java
volatile
```

```
Write

↓

CPU Cache

↓

Maybe Main Memory
```

With

```java
volatile
```

```
Write

↓

Memory Barrier

↓

Main Memory

↓

Visible To Other Threads
```

---

## Simplified Visualization

```text
Thread

↓

Write

↓

Memory Barrier

↓

RAM

↓

Other Threads Read
```

> [!NOTE]
> The JVM inserts the required memory barriers automatically.
>
> As a Java developer, you don't write them yourself.

---

# 12. Real-World Examples

---

## Stop Flag

```java
class Worker {

    private volatile boolean running = true;

    public void stop() {

        running = false;

    }

}
```

Worker thread

```java
while (running) {

    // Do Work

}
```

Perfect use case.

---

## Configuration Refresh

```java
volatile boolean configChanged;
```

One thread updates configuration.

Other threads observe the latest value.

---

## Status Indicators

Examples

```java
volatile boolean connected;

volatile boolean loggedIn;

volatile boolean active;
```

These variables are typically read frequently and written occasionally.

---

# 💡 Interview Insight

A classic interview question is:

> **Can `volatile` replace `synchronized`?**

**Answer:**

No.

`volatile`

provides

- Visibility
- Ordering

It does **not**

- provide mutual exclusion
- make compound operations atomic
- prevent race conditions involving multiple steps

---

# 13. When Should You Use `volatile`?

Use

```java
volatile
```

when

- Multiple threads **read** the variable.
- One or a few threads **update** the variable.
- Updating the variable is a **single write operation**.
- No compound operations (like `++`, `+=`, etc.) are performed.

---

## Good Candidates

### Status Flag

```java
private volatile boolean running = true;
```

---

### Shutdown Signal

```java
private volatile boolean shutdownRequested;
```

---

### Configuration Flag

```java
private volatile boolean configChanged;
```

---

### Connection State

```java
private volatile boolean connected;
```

These are simple state variables where **visibility** is the main requirement.

---

# 14. When Should You NOT Use `volatile`?

Do **not** use

```java
volatile
```

when multiple operations must behave as a single unit.

---

## Counter

```java
volatile int count;

count++;
```

Not thread-safe.

---

## Bank Balance

```java
balance = balance - amount;
```

Multiple steps are involved.

Synchronization or atomic classes are required.

---

## Collection Updates

```java
list.add(item);
```

Making the reference

```java
volatile List<String> list;
```

does **not** make the list operations thread-safe.

---

## Rule

If an operation performs

```
Read

↓

Modify

↓

Write
```

`volatile`

is usually **not enough**.

---

# 15. Common Mistakes

---

## ❌ Believing `volatile` Makes Everything Thread-Safe

Wrong

```java
volatile int count;

count++;
```

Still has race conditions.

---

## ❌ Using `volatile` Instead of Synchronization

Wrong

```java
volatile int balance;

balance -= 100;
```

Multiple threads can still interfere with each other.

---

## ❌ Declaring Every Variable `volatile`

Adding

```java
volatile
```

everywhere does not improve thread safety.

It may also reduce optimization opportunities.

Use it only when its visibility guarantee is actually needed.

---

## ❌ Forgetting That Objects Can Still Be Mutable

Example

```java
volatile List<String> names =
        new ArrayList<>();
```

The reference is visible.

The list itself is **not** thread-safe.

Threads can still modify the list concurrently.

---

# 16. Best Practices

✅ Use `volatile` for simple state flags.

✅ Keep volatile variables simple and independent.

✅ Use `AtomicInteger`, `AtomicLong`, or other atomic classes for counters.

✅ Use synchronization or locks for compound operations.

✅ Choose the simplest concurrency mechanism that satisfies the requirement.

---

# 17. Interview Questions

### 1. What is `volatile`?

`volatile` is a Java keyword that guarantees visibility and ordering of updates to a variable across threads.

---

### 2. Does `volatile` make a variable thread-safe?

No.

It guarantees visibility,

not atomicity.

---

### 3. Can `volatile` replace `synchronized`?

No.

`synchronized` provides

- mutual exclusion
- visibility
- atomicity for protected critical sections

`volatile`

provides only

- visibility
- ordering

---

### 4. Is `count++` atomic if `count` is volatile?

No.

`count++`

still performs

```text
Read

↓

Increment

↓

Write
```

Multiple threads can interleave these steps.

---

### 5. Why is `volatile` faster than `synchronized`?

Because it does not acquire monitor locks.

It only enforces memory visibility and ordering.

---

### 6. What problem does `volatile` solve?

The **memory visibility problem**, where one thread may not observe updates made by another thread.

---

### 7. Give a real-world use case for `volatile`.

Examples include:

- Stop flags
- Shutdown signals
- Configuration refresh flags
- Connection status indicators

---

# 18. Quick Revision

```text
Without volatile

Thread A

↓

CPU Cache

↓

Old Value

────────────────────

Thread B

↓

Main Memory

↓

New Value
```

---

```text
With volatile

Thread A

↓

Main Memory

↓

Latest Value
```

---

## Visibility vs Atomicity

```text
volatile

↓

Visibility

────────────────────────

AtomicInteger

↓

Visibility

+

Atomicity

────────────────────────

synchronized

↓

Visibility

+

Mutual Exclusion
```

---

## `volatile` Checklist

```text
✔ Visibility

✔ Ordering

✘ Atomicity

✘ Mutual Exclusion

✔ Simple State Flags
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `volatile` is
- [x] Memory visibility problem
- [x] CPU cache vs main memory
- [x] Happens-Before relationship
- [x] Why `volatile` is not thread-safe
- [x] Visibility vs atomicity
- [x] `volatile` vs `synchronized`
- [x] Memory barriers (high level)
- [x] Real-world use cases
- [x] Best practices

---

# 📌 Key Takeaways

- `volatile` guarantees that all threads observe the **latest value** of a variable.
- It solves the **visibility** problem, not the **atomicity** problem.
- Compound operations like `count++` are **not** made thread-safe by `volatile`.
- `volatile` is ideal for status flags and simple shared state.
- Use atomic classes or synchronization when multiple operations must execute as a single unit.
- Understanding the distinction between **visibility** and **atomicity** is essential for writing correct concurrent programs.

> [!TIP]
> **Interview Rule**
>
> If someone asks:
>
> **"Should I use `volatile` or `synchronized`?"**
>
> Ask yourself:
>
> - Do I only need **visibility**? → `volatile`
> - Do I need **mutual exclusion** or **atomicity**? → `synchronized`, `Lock`, or atomic classes

---

# 📖 Next Topic

➡️ **14. Atomic Variables**

In the next chapter, you'll learn:

- What are Atomic Variables?
- Why `volatile` is not enough
- Compare-And-Set (CAS)
- `AtomicInteger`
- `AtomicLong`
- `AtomicBoolean`
- Lock-Free Programming
- ABA Problem (Introduction)
- Performance comparison
- Interview questions

> ⭐ **Atomic Variables are one of the biggest improvements introduced in `java.util.concurrent.atomic`, providing thread-safe operations without traditional locking.**
