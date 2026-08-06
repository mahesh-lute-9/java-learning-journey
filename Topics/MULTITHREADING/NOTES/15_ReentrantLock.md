# 🔒 15. ReentrantLock

> [!NOTE]
> `ReentrantLock` is an implementation of the **`Lock` interface** that provides explicit locking with advanced features such as **fairness**, **interruptible locking**, **timed lock acquisition**, and **multiple condition variables**.
>
> It offers greater flexibility than the `synchronized` keyword.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is `ReentrantLock`?](#2-what-is-reentrantlock)
- [3. Why Do We Need `ReentrantLock`?](#3-why-do-we-need-reentrantlock)
- [4. Why `synchronized` Is Not Always Enough](#4-why-synchronized-is-not-always-enough)
- [5. What Does "Reentrant" Mean?](#5-what-does-reentrant-mean)
- [6. Creating a `ReentrantLock`](#6-creating-a-reentrantlock)
- [7. `lock()` and `unlock()`](#7-lock-and-unlock)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Threads
- Synchronization
- `synchronized`
- Deadlock
- Atomic Variables

---

# 2. What is `ReentrantLock`?

`ReentrantLock` is a class provided by Java that allows threads to acquire and release locks explicitly.

Unlike

```java
synchronized
```

where the JVM manages locking automatically,

with

```java
ReentrantLock
```

the programmer explicitly controls when the lock is acquired and released.

---

## Package

```java
java.util.concurrent.locks
```

---

## Definition

> `ReentrantLock` is a lock implementation that provides mutual exclusion along with advanced locking features not available in the `synchronized` keyword.

---

# 3. Why Do We Need `ReentrantLock`?

The

```java
synchronized
```

keyword is simple and effective,

but it has limitations.

For example,

it cannot

- attempt to acquire a lock without waiting,
- wait only for a limited time,
- respond to interruption while waiting,
- create multiple waiting conditions for the same lock.

`ReentrantLock`

provides all these capabilities.

---

## Comparison

```text
synchronized

↓

Simple

↓

Automatic Lock Management

────────────────────────

ReentrantLock

↓

Advanced Features

↓

Manual Lock Management
```

---

# 4. Why `synchronized` Is Not Always Enough

Suppose Thread A owns a lock.

Thread B tries to enter the synchronized block.

```
Thread B

↓

Wait Forever
```

There is no timeout.

There is no way to cancel the wait.

---

With

```java
ReentrantLock
```

you can write

```java
lock.tryLock(5, TimeUnit.SECONDS);
```

If the lock is unavailable after

```
5 Seconds
```

Thread B can perform some other action instead of waiting forever.

---

# 5. What Does "Reentrant" Mean?

A **reentrant** lock allows the thread that already owns the lock to acquire it again without causing a deadlock.

---

## Example

```java
class Demo {

    private final ReentrantLock lock =
            new ReentrantLock();

    public void methodA() {

        lock.lock();

        try {

            methodB();

        } finally {

            lock.unlock();

        }

    }

    public void methodB() {

        lock.lock();

        try {

            System.out.println(
                "Inside methodB");

        } finally {

            lock.unlock();

        }

    }

}
```

---

## What Happens?

```
Thread A

↓

lock()

↓

methodA()

↓

methodB()

↓

lock() Again

↓

Allowed
```

The same thread can acquire the lock multiple times.

---

## Hold Count

Every successful acquisition by the same thread increases an internal **hold count**.

```
First lock()

↓

Hold Count = 1

────────────────

Second lock()

↓

Hold Count = 2

────────────────

unlock()

↓

Hold Count = 1

────────────────

unlock()

↓

Hold Count = 0

↓

Lock Released
```

The lock is actually released only when the hold count reaches **0**.

---

# 6. Creating a `ReentrantLock`

Import

```java
import java.util.concurrent.locks.ReentrantLock;
```

---

## Create Lock

```java
ReentrantLock lock =
        new ReentrantLock();
```

---

## Fair Lock

```java
ReentrantLock lock =
        new ReentrantLock(true);
```

This creates a **fair lock**.

Threads acquire the lock approximately in the order they requested it.

---

## Non-Fair Lock

```java
ReentrantLock lock =
        new ReentrantLock(false);
```

or simply

```java
new ReentrantLock();
```

The default is **non-fair**.

A newly arrived thread may acquire the lock before threads that have been waiting longer.

---

> [!NOTE]
> Fair locks improve predictability but generally reduce throughput due to increased scheduling overhead.

---

# 7. `lock()` and `unlock()`

The basic usage pattern is

```java
lock.lock();

try {

    // Critical Section

} finally {

    lock.unlock();

}
```

---

## Why `finally`?

Suppose an exception occurs.

Without

```java
finally
```

the lock might never be released.

This can cause other threads to wait indefinitely.

---

## Incorrect Example

```java
lock.lock();

process();

lock.unlock();
```

If

```java
process();
```

throws an exception,

```java
unlock();
```

is never executed.

---

## Correct Example

```java
lock.lock();

try {

    process();

} finally {

    lock.unlock();

}
```

The lock is always released,

even if an exception occurs.

---

## Visualization

```text
Thread

↓

lock()

↓

Critical Section

↓

finally

↓

unlock()
```

---

# 💡 Interview Insight

A very common interview question is:

> **Why should `unlock()` be placed inside a `finally` block?**

**Answer:**

To guarantee that the lock is released even if an exception is thrown inside the critical section.

Failing to release the lock can cause threads to block indefinitely.

---

# 8. `tryLock()` ⭐⭐⭐⭐⭐

One limitation of

```java
synchronized
```

is that a thread waits indefinitely if the lock is unavailable.

`ReentrantLock`

solves this with

```java
tryLock()
```

---

## What is `tryLock()`?

`tryLock()` attempts to acquire the lock **without waiting forever**.

If the lock is available,

the method returns

```text
true
```

Otherwise,

it immediately returns

```text
false
```

---

## Syntax

```java
boolean acquired = lock.tryLock();
```

---

## Example

```java
ReentrantLock lock =
        new ReentrantLock();

if (lock.tryLock()) {

    try {

        System.out.println(
            "Lock Acquired");

    } finally {

        lock.unlock();

    }

} else {

    System.out.println(
        "Lock Not Available");

}
```

---

## Visualization

```text
Thread

↓

tryLock()

↓

Lock Available?

↓

YES

↓

Acquire Lock

──────────────

NO

↓

Return false
```

---

# 9. Timed Lock Acquisition

Sometimes,

a thread should wait,

but only for a limited time.

---

## Syntax

```java
lock.tryLock(
        5,
        TimeUnit.SECONDS
);
```

---

## Working

```text
Thread

↓

Request Lock

↓

Wait Up To

5 Seconds

↓

Lock Available?

↓

YES

↓

Acquire Lock

──────────────

NO

↓

Return false
```

---

## Example

```java
if (lock.tryLock(5,
        TimeUnit.SECONDS)) {

    try {

        process();

    } finally {

        lock.unlock();

    }

} else {

    System.out.println(
        "Could Not Acquire Lock");

}
```

---

## Benefits

- Prevents indefinite waiting
- Helps reduce deadlock risk
- Improves application responsiveness

---

# 10. `lockInterruptibly()`

Normally,

if a thread is waiting for a lock,

it cannot respond immediately to interruption.

`lockInterruptibly()`

changes this behavior.

---

## Syntax

```java
lock.lockInterruptibly();
```

---

## Example

```java
try {

    lock.lockInterruptibly();

    try {

        process();

    } finally {

        lock.unlock();

    }

} catch (InterruptedException e) {

    Thread.currentThread().interrupt();

}
```

---

## Visualization

```text
Thread

↓

Waiting For Lock

↓

interrupt()

↓

InterruptedException

↓

Stop Waiting
```

---

## Why Is It Useful?

Suppose a thread waits for a database lock.

The application is shutting down.

Instead of waiting indefinitely,

the thread can be interrupted and terminate gracefully.

---

# 11. Fair vs Non-Fair Locks

When multiple threads wait for the same lock,

who gets it first?

That depends on the lock policy.

---

## Fair Lock

```java
ReentrantLock lock =
        new ReentrantLock(true);
```

Threads acquire the lock approximately in **FIFO (First-In, First-Out)** order.

---

### Visualization

```text
Waiting Queue

──────────────

Thread A

Thread B

Thread C

↓

Lock Released

↓

Thread A

Gets Lock
```

---

## Advantages

- Reduces starvation
- Predictable behavior

---

## Disadvantages

- Lower throughput
- More scheduling overhead

---

## Non-Fair Lock

```java
ReentrantLock lock =
        new ReentrantLock();
```

or

```java
new ReentrantLock(false);
```

---

### Visualization

```text
Waiting Queue

──────────────

Thread A

Thread B

Thread C

↓

New Thread D

↓

Lock Released

↓

Thread D Gets Lock
```

A newly arrived thread may "barge" ahead of waiting threads.

---

## Advantages

- Higher throughput
- Better overall performance

---

## Disadvantages

- Some threads may wait longer
- Starvation is possible in rare cases

---

## Comparison

| Fair Lock | Non-Fair Lock |
|------------|---------------|
| FIFO ordering | No ordering guarantee |
| Lower throughput | Higher throughput |
| Less starvation | Better performance |
| Predictable | Less predictable |

---

# 12. `Condition`

With

```java
synchronized
```

each monitor has only one wait set.

`ReentrantLock`

allows multiple waiting conditions using

```java
Condition
```

objects.

---

## Creating a Condition

```java
ReentrantLock lock =
        new ReentrantLock();

Condition condition =
        lock.newCondition();
```

---

## Common Methods

```java
await()

signal()

signalAll()
```

These methods are similar to

```java
wait()

notify()

notifyAll()
```

but belong to

```java
Condition
```

instead of

```java
Object
```

---

## Comparison

| `Object` | `Condition` |
|-----------|-------------|
| `wait()` | `await()` |
| `notify()` | `signal()` |
| `notifyAll()` | `signalAll()` |

---

# 13. Multiple Condition Objects

One major advantage of

```java
Condition
```

is that a single lock can have multiple independent waiting queues.

---

## Example

```java
ReentrantLock lock =
        new ReentrantLock();

Condition notFull =
        lock.newCondition();

Condition notEmpty =
        lock.newCondition();
```

---

## Visualization

```text
             ReentrantLock

                 │

      ┌──────────┴──────────┐

      ▼                     ▼

 Not Full Queue      Not Empty Queue

      ▲                     ▲

      │                     │

 Producer             Consumer
```

Unlike

```java
synchronized
```

where all waiting threads share one wait set,

`Condition`

lets you separate different waiting conditions.

This improves clarity and efficiency.

---

# 14. Real-World Examples

---

## Resource Pool

A thread tries to acquire a database connection.

```java
tryLock(2,
        TimeUnit.SECONDS);
```

If unavailable,

the application reports a timeout instead of hanging.

---

## Graceful Shutdown

A worker thread waits for a lock using

```java
lockInterruptibly();
```

If the application is interrupted,

the worker exits cleanly.

---

## Producer–Consumer

Two separate

```java
Condition
```

objects

- `notEmpty`
- `notFull`

allow producers and consumers to wait independently.

---

# 💡 Interview Insight

A popular interview question is:

> **What advantages does `ReentrantLock` have over `synchronized`?**

Good answers include:

- Timed lock acquisition (`tryLock`)
- Interruptible locking (`lockInterruptibly`)
- Fair locking
- Multiple `Condition` objects
- Explicit lock management

---

# 15. `ReentrantLock` vs `synchronized`

Both provide **mutual exclusion**, but they differ in flexibility and features.

---

## Comparison

| `synchronized` | `ReentrantLock` |
|----------------|-----------------|
| Built into Java language | Part of `java.util.concurrent.locks` |
| Automatic lock release | Manual lock release |
| No timeout support | `tryLock()` supports timeout |
| Not interruptible while waiting | `lockInterruptibly()` supported |
| Single wait set | Multiple `Condition` objects |
| Simpler syntax | More flexible API |

---

## Decision Guide

```text
Simple Critical Section?

↓

YES

↓

Use synchronized

────────────────────────

Need Timeout?

↓

Use ReentrantLock

────────────────────────

Need Interruptible Lock?

↓

Use ReentrantLock

────────────────────────

Need Multiple Conditions?

↓

Use ReentrantLock
```

---

# 16. Performance Considerations

There is no universal winner.

Performance depends on the workload.

---

## Low Contention

When very few threads compete,

```
synchronized
```

performs extremely well.

Modern JVMs optimize it aggressively.

---

## High Contention

When many threads compete,

```
ReentrantLock
```

often provides better scalability because of its advanced locking mechanisms.

---

## Rule

Choose based on

- Required features
- Code readability
- Maintainability

Not just raw performance.

---

# 17. Common Mistakes

---

## ❌ Forgetting `unlock()`

Wrong

```java
lock.lock();

process();
```

If

```java
process();
```

throws an exception,

the lock remains held.

---

## Correct

```java
lock.lock();

try {

    process();

} finally {

    lock.unlock();

}
```

---

## ❌ Calling `unlock()` Without Owning the Lock

```java
lock.unlock();
```

If the current thread does not own the lock,

Java throws

```text
IllegalMonitorStateException
```

---

## ❌ Ignoring the Return Value of `tryLock()`

Wrong

```java
lock.tryLock();

process();
```

Correct

```java
if (lock.tryLock()) {

    try {

        process();

    } finally {

        lock.unlock();

    }

}
```

Always check whether the lock was actually acquired.

---

## ❌ Using Fair Locks Everywhere

Fair locks reduce starvation,

but they also reduce throughput.

Use them only when fairness is an actual requirement.

---

## ❌ Using `ReentrantLock` for Simple Cases

If all you need is a straightforward critical section,

```java
synchronized
```

is often simpler and easier to maintain.

---

# 18. Best Practices

✅ Always release the lock in a `finally` block.

✅ Use `tryLock()` when waiting forever is undesirable.

✅ Use `lockInterruptibly()` for interruptible tasks.

✅ Prefer non-fair locks unless fairness is required.

✅ Keep critical sections short.

✅ Use `Condition` objects instead of manual polling.

---

# 19. Interview Questions

### 1. What is `ReentrantLock`?

`ReentrantLock` is an implementation of the `Lock` interface that provides explicit locking with advanced features such as timed locking, interruptible locking, fairness, and multiple conditions.

---

### 2. Why is it called "Reentrant"?

Because the same thread can acquire the same lock multiple times without deadlocking.

The lock maintains a **hold count**.

---

### 3. Why should `unlock()` be placed inside a `finally` block?

To guarantee that the lock is released even if an exception occurs.

---

### 4. What is the difference between `lock()` and `tryLock()`?

| `lock()` | `tryLock()` |
|-----------|-------------|
| Waits indefinitely | Attempts to acquire the lock |
| No immediate failure | Returns `false` if unavailable |

---

### 5. What is `lockInterruptibly()`?

It allows a waiting thread to be interrupted while waiting for the lock.

---

### 6. Difference between Fair and Non-Fair Lock?

| Fair | Non-Fair |
|------|----------|
| FIFO-style acquisition | No ordering guarantee |
| Less starvation | Higher throughput |
| Lower performance | Better performance |

---

### 7. What is a `Condition`?

A `Condition` is an object associated with a `ReentrantLock` that provides waiting and signaling operations similar to `wait()`, `notify()`, and `notifyAll()`.

---

### 8. Can `ReentrantLock` replace `synchronized`?

Yes, in many cases.

However,

choose the one that best matches your requirements.

For simple synchronization,

`synchronized`

is often preferred because it is simpler.

---

# 20. Quick Revision

```text
Need Simple Lock?

↓

synchronized

────────────────────────

Need Timeout?

↓

tryLock()

────────────────────────

Need Interruptible Waiting?

↓

lockInterruptibly()

────────────────────────

Need Multiple Wait Queues?

↓

Condition
```

---

## Features

```text
✔ Reentrant

✔ Fair Lock

✔ Non-Fair Lock

✔ tryLock()

✔ lockInterruptibly()

✔ Condition

✔ Explicit Locking
```

---

## Usage Pattern

```java
lock.lock();

try {

    // Critical Section

} finally {

    lock.unlock();

}
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `ReentrantLock` is
- [x] Why it is needed
- [x] Reentrancy
- [x] Hold Count
- [x] `lock()` and `unlock()`
- [x] `tryLock()`
- [x] `lockInterruptibly()`
- [x] Fair vs Non-Fair Locks
- [x] `Condition`
- [x] Multiple Condition Objects
- [x] Best practices

---

# 📌 Key Takeaways

- `ReentrantLock` provides all the capabilities of `synchronized` plus several advanced features.
- Always pair `lock()` with `unlock()` in a `finally` block.
- `tryLock()` avoids waiting indefinitely.
- `lockInterruptibly()` allows waiting threads to respond to interruptions.
- Fair locks improve predictability but may reduce throughput.
- `Condition` objects support multiple independent waiting queues.
- Use `ReentrantLock` when you need advanced locking features; otherwise, `synchronized` is often the simpler choice.

> [!TIP]
> **Interview Rule**
>
> If asked:
>
> **"When should you choose `ReentrantLock` over `synchronized`?"**
>
> Mention these features:
>
> - Timed lock acquisition (`tryLock()`)
> - Interruptible locking (`lockInterruptibly()`)
> - Fair locking
> - Multiple `Condition` objects
> - Explicit lock management

---

# 📖 Next Topic

➡️ **16. Executor Framework**

In the next chapter, you'll learn one of the biggest improvements introduced in Java 5:

- Why creating threads manually is inefficient
- Thread Pools
- `Executor`
- `ExecutorService`
- `Executors`
- Task submission
- Thread pool types
- Graceful shutdown
- Best practices
- Interview questions

> ⭐ **The Executor Framework is the foundation of modern Java concurrency. Most production Java applications use thread pools instead of creating threads manually.**
