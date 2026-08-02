# 🏁 06. Race Condition

> [!NOTE]
> A **Race Condition** occurs when multiple threads access and modify shared mutable data concurrently, and the **final result depends on the timing (race) between those threads**.
>
> Race conditions are one of the most common and dangerous problems in concurrent programming.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is a Race Condition?](#2-what-is-a-race-condition)
- [3. Why Does a Race Condition Happen?](#3-why-does-a-race-condition-happen)
- [4. Race Condition vs Data Race](#4-race-condition-vs-data-race)
- [5. Shared Mutable State](#5-shared-mutable-state)
- [6. Timeline Visualization](#6-timeline-visualization)
- [7. First Example – Counter](#7-first-example--counter)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Thread
- Runnable
- Thread Lifecycle
- Synchronization
- `synchronized` Keyword
- Critical Section
- Shared Resources

---

# 2. What is a Race Condition?

A **Race Condition** occurs when two or more threads access the same shared data concurrently, and the program's correctness depends on **which thread executes first**.

The threads are effectively "racing" to access or modify the same resource.

---

## Definition

> A race condition is a concurrency bug in which the outcome of a program depends on the unpredictable timing or interleaving of multiple threads.

---

## Why Is It Called a "Race"?

Imagine two runners.

```text
Thread A  🏃

Thread B  🏃

        │

        ▼

Shared Resource
```

Both threads race toward the same shared resource.

Whichever thread reaches a critical step first can change the final result.

---

## Real-Life Analogy

Imagine the last available concert ticket.

```
Last Ticket

      │

 ┌────┴────┐

 ▼         ▼

Alice     Bob
```

If both customers click **Buy** at nearly the same time,

the system must ensure that only one purchase succeeds.

Otherwise,

the same seat might be sold twice.

---

# 3. Why Does a Race Condition Happen?

A race condition usually occurs when **all three** of the following conditions are true:

### 1. Shared Data Exists

Example

```java
int balance = 1000;
```

---

### 2. Multiple Threads Access It

```text
Thread A

↓

balance

↑

Thread B
```

---

### 3. At Least One Thread Modifies It

```text
Thread A

Read

Modify

Write

↓

balance

↑

Thread B

Read

Modify

Write
```

Without proper synchronization,

the operations may overlap in unpredictable ways.

> [!IMPORTANT]
> If threads only read shared data and never modify it, a race condition generally does not occur.

---

# 4. Race Condition vs Data Race

These two terms are often used interchangeably, but they are **not exactly the same**.

| Race Condition | Data Race |
|---------------|-----------|
| Broader concurrency problem | Specific unsynchronized memory access problem |
| Final result depends on timing | Two threads access the same memory concurrently without proper synchronization, and at least one access is a write |
| May involve higher-level logic | Focuses on concurrent memory access |

---

## Example

### Race Condition

Two users try to reserve the last hotel room.

The final booking depends on timing.

---

### Data Race

```java
count++;
```

executed simultaneously by multiple threads without synchronization.

Multiple threads read and write the same variable concurrently.

---

> [!TIP]
> Every **data race** is a concurrency bug, but not every race condition is simply a data race. Some race conditions arise from higher-level application logic.

---

# 5. Shared Mutable State

Race conditions require **shared mutable state**.

Let's break this term into three parts.

---

## Shared

Accessible by more than one thread.

Example

```java
class Counter {

    int count = 0;

}
```

If multiple threads use the same `Counter` object,

`count` is shared.

---

## Mutable

The value can change.

Example

```java
count++;
```

or

```java
balance -= amount;
```

---

## State

The current value stored inside an object.

Example

```text
count = 5
```

The value `5` represents the current state.

---

## Together

```
Shared

+

Mutable

+

State

=

Potential Race Condition
```

---

# 6. Timeline Visualization

Understanding race conditions becomes much easier when viewed over time.

Suppose

```java
count = 0;
```

Two threads execute

```java
count++;
```

at nearly the same time.

---

## Expected Execution

```
Time →

Thread A

Read 0

↓

Add 1

↓

Write 1

↓

--------------------

Thread B

Read 1

↓

Add 1

↓

Write 2

Final Value = 2
```

---

## Actual Execution

```
Time →

Thread A

Read 0

----------------------------

Thread B

Read 0

----------------------------

Thread A

Add 1

----------------------------

Thread B

Add 1

----------------------------

Thread A

Write 1

----------------------------

Thread B

Write 1

Final Value = 1
```

---

## Why?

Both threads read the same initial value before either thread updated it.

The second write overwrites the first.

This is known as a **Lost Update**.

We'll study this pattern in detail later in this chapter.

---

# 7. First Example – Counter

Consider a simple counter.

```java
class Counter {

    int count = 0;

    void increment() {

        count++;

    }

}
```

Suppose

Thread A

and

Thread B

both execute

```java
increment();
```

simultaneously.

At first glance,

we expect

```text
0

↓

1

↓

2
```

However,

the result may become

```text
0

↓

1
```

---

## Why?

Because

```java
count++;
```

is **not** a single operation.

It is approximately equivalent to

```text
Read count

↓

Add 1

↓

Write count
```

Since these steps are separate,

another thread can interrupt between them.

This creates a race condition.

> [!WARNING]
> Even a simple statement like `count++` is **not atomic**.
>
> We'll learn about **Atomic Variables** later and see how they solve this problem.

---

# 8. Read-Modify-Write (RMW)

One of the most common causes of race conditions is the **Read-Modify-Write** pattern.

The operation consists of three separate steps.

```text
Read Value

↓

Modify Value

↓

Write Value
```

Although it appears to be a single statement in Java,

it is actually multiple operations.

---

## Example

```java
count++;
```

looks simple,

but internally it is approximately

```text
Read count

↓

count + 1

↓

Write Updated Value
```

Since these steps are separate,

another thread can execute between them.

---

## Timeline

Initial Value

```text
count = 10
```

Two threads execute

```java
count++;
```

simultaneously.

```
Time →

Thread A

Read 10

────────────────────────

Thread B

Read 10

────────────────────────

Thread A

Compute 11

────────────────────────

Thread B

Compute 11

────────────────────────

Thread A

Write 11

────────────────────────

Thread B

Write 11
```

Final Value

```text
11
```

Expected

```text
12
```

One increment has been lost.

---

## Common Read-Modify-Write Operations

```java
count++;

count--;

balance += amount;

balance -= amount;

total = total + price;

marks = marks + bonus;
```

All of these are vulnerable to race conditions when shared between multiple threads.

---

# 9. Check-Then-Act Pattern

Another common source of race conditions is the **Check-Then-Act** pattern.

Execution follows two steps.

```text
Check Condition

↓

Perform Action
```

The problem is that another thread may change the data between these two steps.

---

## Example

```java
if (balance >= amount) {

    balance -= amount;

}
```

This appears correct,

but in a multithreaded program it is dangerous.

---

## Timeline

Initial Balance

```text
₹1000
```

Two threads each attempt to withdraw

```text
₹700
```

```
Time →

Thread A

Checks Balance = 1000

────────────────────────────

Thread B

Checks Balance = 1000

────────────────────────────

Thread A

Withdraws 700

Balance = 300

────────────────────────────

Thread B

Withdraws 700

Balance = -400
```

Both threads believed enough money existed.

The account becomes overdrawn.

---

## Real-Life Analogy

Imagine a library with only one copy of a book.

```
Book Available

        │

 ┌──────┴──────┐

 ▼             ▼

Student A   Student B
```

Both students check

```
Available
```

Both decide to borrow it.

Only one copy exists.

The check and the action were not performed atomically.

---

# 10. Lost Update Problem

The **Lost Update Problem** occurs when one thread's update is overwritten by another thread.

This is one of the most common race conditions.

---

## Example

Initial Value

```text
count = 50
```

Thread A

```text
Read 50

↓

Write 51
```

Thread B

```text
Read 50

↓

Write 51
```

Expected

```text
52
```

Actual

```text
51
```

One update disappeared.

Hence the name

```
Lost Update
```

---

## Visualization

```
Initial

50

↓

Thread A

51

↓

Thread B

51

↓

Expected

52

Actual

51
```

---

# 11. Bank Account Example

Let's continue with our `BankAccount` example.

```java
class BankAccount {

    private int balance = 1000;

    public void withdraw(int amount) {

        if (balance >= amount) {

            balance -= amount;

        }

    }

}
```

Suppose two threads execute

```java
withdraw(700);
```

at the same time.

---

## Timeline

```
Balance = ₹1000

────────────────────────

Thread A

Checks 1000

────────────────────────

Thread B

Checks 1000

────────────────────────

Thread A

Withdraws

Balance = 300

────────────────────────

Thread B

Withdraws

Balance = -400
```

The business rule

```
Balance should never become negative.
```

has been violated.

---

## Why Did It Happen?

The operation

```java
if (balance >= amount)
```

and

```java
balance -= amount;
```

were not protected as a single critical section.

---

# 12. Ticket Booking Example

Suppose an online ticket booking system has only

```
1 Seat Remaining
```

Database

```text
Available Seats = 1
```

Two customers click

```
Book Now
```

simultaneously.

---

## Timeline

```
Customer A

Reads

Seats = 1

────────────────────────

Customer B

Reads

Seats = 1

────────────────────────

Customer A

Books Seat

Seats = 0

────────────────────────

Customer B

Books Seat

Seats = -1
```

Now the system has sold

```
Two Tickets

for

One Seat
```

This is another race condition.

---

## Real Systems

Booking systems for

- Flights
- Trains
- Movie Tickets
- Hotel Rooms

must prevent this situation using proper synchronization or transactional mechanisms.

---

# 13. Inventory Example

Suppose an e-commerce website has

```text
Stock = 5
```

Two warehouse workers update inventory simultaneously.

```
Thread A

Ships 3

────────────────────────

Thread B

Ships 4
```

Both read

```
5
```

Both update independently.

Final stock becomes incorrect.

Possible result

```
2
```

or

```
1
```

depending on execution order.

---

# 💡 Interview Insight

Whenever you see a sequence like

```text
Read

↓

Decision

↓

Update
```

or

```text
Read

↓

Modify

↓

Write
```

you should immediately ask:

> **"Can another thread modify this data between these steps?"**

If the answer is **yes**, there is a potential race condition.

---

# 14. How `synchronized` Prevents Race Conditions

In the previous chapter, we learned that the `synchronized` keyword ensures that **only one thread at a time** can execute a critical section protected by the same monitor lock.

Let's revisit our counter example.

Without synchronization

```java
class Counter {

    private int count = 0;

    public void increment() {

        count++;

    }

}
```

Multiple threads may execute

```java
count++;
```

simultaneously.

This creates a race condition.

---

## Solution

```java
class Counter {

    private int count = 0;

    public synchronized void increment() {

        count++;

    }

}
```

Now,

before executing

```java
count++;
```

a thread must acquire the object's monitor lock.

---

## Timeline

Without synchronization

```text
Thread A

Read 10

────────────────────

Thread B

Read 10

────────────────────

Thread A

Write 11

────────────────────

Thread B

Write 11
```

Final Value

```text
11
```

---

With synchronization

```text
Thread A

Acquire Lock

↓

Read 10

↓

Write 11

↓

Release Lock

────────────────────

Thread B

Acquire Lock

↓

Read 11

↓

Write 12

↓

Release Lock
```

Final Value

```text
12
```

---

## Why It Works

Synchronization makes the entire operation

```text
Read

↓

Modify

↓

Write
```

behave as one protected critical section.

No other thread can enter until the first thread completes.

> [!IMPORTANT]
> `synchronized` does **not** make `count++` atomic.
>
> It prevents other threads from executing the same critical section simultaneously.

---

# 15. How Atomic Variables Prevent Race Conditions

Java also provides **Atomic Classes** in the

```java
java.util.concurrent.atomic
```

package.

Example

```java
AtomicInteger count = new AtomicInteger();
```

Instead of writing

```java
count++;
```

we write

```java
count.incrementAndGet();
```

---

## Why It Works

Atomic classes use low-level CPU instructions (such as Compare-And-Swap) to perform updates atomically.

From the programmer's perspective,

the update behaves as one indivisible operation.

> [!NOTE]
> We'll study Atomic Variables in detail in **12_Atomic_Variables.md**.

---

# 16. How `ReentrantLock` Prevents Race Conditions

Another solution is

```java
ReentrantLock
```

Example

```java
lock.lock();

try {

    count++;

} finally {

    lock.unlock();

}
```

Unlike `synchronized`,

`ReentrantLock` provides additional features such as

- Fair locking
- Timed lock acquisition
- Interruptible locking
- Condition variables

> [!NOTE]
> `ReentrantLock` is covered in detail in **13_ReentrantLock.md**.

---

# 17. Comparing the Solutions

| Solution | Best For | Notes |
|-----------|----------|-------|
| `synchronized` | General critical sections | Simple and built into the language |
| `AtomicInteger` | Single-variable atomic updates | Very efficient for counters and similar operations |
| `ReentrantLock` | Advanced locking requirements | More flexible but requires manual lock management |

> [!TIP]
> Choose the **simplest solution** that correctly solves the problem.
>
> Don't use `ReentrantLock` just because it's more powerful.

---

# 18. Best Practices

✅ Identify shared mutable state.

✅ Keep critical sections as small as possible.

✅ Prefer immutable objects where possible.

✅ Use `AtomicInteger` for simple counters.

✅ Use `synchronized` for protecting critical sections.

✅ Use `ReentrantLock` only when you need its additional capabilities.

✅ Minimize shared mutable state instead of synchronizing everything.

---

# 19. Common Misconceptions

---

## ❌ Race Conditions Only Occur in Large Programs

False.

Even this simple statement can create a race condition.

```java
count++;
```

---

## ❌ `count++` Is Atomic

False.

It consists of multiple steps.

```text
Read

↓

Modify

↓

Write
```

---

## ❌ Synchronization Eliminates All Concurrency Problems

False.

Synchronization helps prevent race conditions,

but incorrect synchronization may introduce

- Deadlocks
- Lock contention
- Reduced scalability

---

## ❌ Reading Shared Data Is Always Safe

Not always.

If another thread modifies the data concurrently,

a read may observe inconsistent or stale values depending on the synchronization strategy.

---

# 20. Interview Questions

### 1. What is a Race Condition?

A race condition occurs when the correctness of a program depends on the unpredictable timing or interleaving of multiple threads.

---

### 2. Why does a race condition occur?

Because multiple threads access shared mutable data concurrently without proper coordination.

---

### 3. What is the Lost Update Problem?

One thread's update is overwritten by another thread, causing an expected update to disappear.

---

### 4. Why is `count++` not thread-safe?

Because it is a Read-Modify-Write operation, not a single atomic operation.

---

### 5. Difference between Race Condition and Data Race?

A race condition is a broader concurrency problem.

A data race specifically involves unsynchronized concurrent memory access where at least one access is a write.

---

### 6. How does `synchronized` prevent race conditions?

It ensures that only one thread at a time executes the protected critical section for the same monitor lock.

---

### 7. When should `AtomicInteger` be preferred?

For simple atomic operations such as counters.

---

### 8. Is every race condition a data race?

No.

Some race conditions arise from higher-level application logic rather than direct unsynchronized memory access.

---

# 21. Quick Revision

```text
Race Condition

↓

Multiple Threads

↓

Shared Mutable Data

↓

Concurrent Access

↓

Timing Matters

↓

Incorrect Result
```

---

## Common Patterns

```text
Read

↓

Modify

↓

Write

=====================

Check

↓

Then

↓

Act
```

Both patterns are vulnerable to race conditions.

---

## Solutions

```text
Race Condition

↓

Synchronization

↓

synchronized

OR

Atomic Variables

OR

ReentrantLock
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What is a Race Condition?
- [x] Why race conditions occur
- [x] Difference between Race Condition and Data Race
- [x] Shared Mutable State
- [x] Read-Modify-Write
- [x] Check-Then-Act
- [x] Lost Update Problem
- [x] Bank Account example
- [x] Ticket Booking example
- [x] Inventory example
- [x] How `synchronized` prevents race conditions
- [x] How `AtomicInteger` helps
- [x] When to use `ReentrantLock`

---

# 📌 Key Takeaways

- Race conditions occur because of concurrent access to **shared mutable state**.
- Many race conditions follow common patterns such as **Read-Modify-Write** and **Check-Then-Act**.
- `count++` is not atomic.
- Correct synchronization ensures that critical sections execute safely.
- Different tools solve race conditions in different situations:
  - `synchronized` for general critical sections
  - `AtomicInteger` for atomic updates
  - `ReentrantLock` for advanced locking requirements

> [!TIP]
> **Interview Rule**
>
> Don't memorize examples.
>
> Learn to recognize the **pattern**:
>
> - Read → Modify → Write
> - Check → Then → Act
>
> Once you can spot these patterns, you'll identify race conditions in real-world code much more easily.

---

# 📖 Next Topic

➡️ **07_Thread_Safety.md**

In the next chapter, we'll answer:

> **How do we design classes and applications that remain correct when accessed by multiple threads?**

Topics include:

- What is Thread Safety?
- Stateless Classes
- Immutable Objects
- Thread Confinement
- Defensive Copying
- Thread-safe Collections
- Singleton Thread Safety
- Design Best Practices
