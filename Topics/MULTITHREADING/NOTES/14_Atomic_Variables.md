# ⚛️ 14. Atomic Variables

> [!NOTE]
> **Atomic Variables** provide **lock-free, thread-safe operations** on single variables using **Compare-And-Set (CAS)**.
>
> They are faster than traditional synchronization for many simple concurrent operations and are part of the **`java.util.concurrent.atomic`** package.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What are Atomic Variables?](#2-what-are-atomic-variables)
- [3. Why Do We Need Atomic Variables?](#3-why-do-we-need-atomic-variables)
- [4. Why `volatile` Is Not Enough](#4-why-volatile-is-not-enough)
- [5. Compare-And-Set (CAS)](#5-compare-and-set-cas)
- [6. How CAS Works](#6-how-cas-works)
- [7. Lock-Free Programming](#7-lock-free-programming)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Threads
- Race Condition
- Synchronization
- `volatile`
- Visibility vs Atomicity

---

# 2. What are Atomic Variables?

Atomic Variables are special classes provided by Java that perform **atomic operations** without using the `synchronized` keyword.

They internally use

```text
CAS (Compare-And-Set)
```

to ensure thread safety.

---

## Definition

> An Atomic Variable is a thread-safe variable that supports atomic operations without explicit locking.

---

## Package

```java
java.util.concurrent.atomic
```

---

## Common Atomic Classes

```java
AtomicInteger

AtomicLong

AtomicBoolean

AtomicReference

AtomicIntegerArray

AtomicLongArray
```

---

# 3. Why Do We Need Atomic Variables?

Consider a shared counter.

```java
int count = 0;
```

Multiple threads execute

```java
count++;
```

Problem

```
Read

↓

Increment

↓

Write
```

This operation is **not atomic**.

Multiple threads may overwrite each other's updates.

---

## Solution 1

```java
synchronized
```

Makes the operation thread-safe,

but uses locking.

---

## Solution 2

```java
AtomicInteger
```

Provides thread safety **without explicit locking**.

---

# 4. Why `volatile` Is Not Enough

Suppose we write

```java
volatile int count = 0;
```

Then

```java
count++;
```

Is it thread-safe?

**No.**

Because

```text
Read

↓

Increment

↓

Write
```

is still three separate steps.

`volatile`

guarantees visibility,

not atomicity.

---

## Example

```java
volatile int count = 0;

count++;
```

Possible execution

```text
Thread A

Read 5

────────────────────

Thread B

Read 5

────────────────────

Thread A

Write 6

────────────────────

Thread B

Write 6
```

Expected

```text
7
```

Actual

```text
6
```

Lost update.

---

## Better Solution

```java
AtomicInteger count =
        new AtomicInteger(0);

count.incrementAndGet();
```

The increment is atomic.

---

# 5. Compare-And-Set (CAS)

CAS is the core algorithm behind atomic variables.

It works like this:

```text
Compare

↓

Expected Value?

↓

YES

↓

Update Value

↓

Success

──────────────

NO

↓

Do Not Update

↓

Retry
```

---

## Definition

> Compare-And-Set (CAS) updates a value only if it is still equal to the expected value.

This prevents lost updates without locking.

---

## Example

Current value

```text
10
```

Thread wants to change it to

```text
11
```

CAS checks

```text
Current == Expected ?

↓

YES

↓

Update
```

If another thread already changed the value,

CAS fails,

and the operation is retried.

---

# 6. How CAS Works

Suppose

```
Value = 100
```

Thread A

expects

```
100
```

and wants

```
101
```

CAS

```text
100 == 100

↓

YES

↓

Update To 101
```

Success.

---

Now suppose another thread already changed the value.

```
Current = 102

Expected = 100
```

CAS

```text
102 == 100

↓

NO

↓

Fail

↓

Retry
```

---

## Visualization

```text
Current Value

↓

Compare

↓

Equal?

↓

YES

↓

Update

──────────────

NO

↓

Retry
```

---

# 7. Lock-Free Programming

Traditional synchronization

```text
Thread

↓

Acquire Lock

↓

Critical Section

↓

Release Lock
```

Atomic variables

```text
Thread

↓

CAS

↓

Success?

↓

YES

↓

Done

──────────────

NO

↓

Retry
```

No monitor locks are involved.

---

## Advantages

- Better scalability
- Reduced lock contention
- Lower context-switch overhead
- High performance for simple operations

---

## Limitations

Atomic variables work best for **single-variable operations**.

If multiple variables must be updated together,

additional synchronization may still be required.

---

# 8. `AtomicInteger`

`AtomicInteger` is the most commonly used atomic class.

It provides thread-safe operations on an integer without using explicit locks.

---

## Declaration

```java
AtomicInteger counter =
        new AtomicInteger();
```

or

```java
AtomicInteger counter =
        new AtomicInteger(100);
```

---

## Common Methods

| Method | Description |
|---------|-------------|
| `get()` | Returns the current value |
| `set(int)` | Sets a new value |
| `incrementAndGet()` | Increments and returns the updated value |
| `getAndIncrement()` | Returns current value, then increments |
| `decrementAndGet()` | Decrements and returns the updated value |
| `getAndDecrement()` | Returns current value, then decrements |
| `addAndGet(int)` | Adds a value and returns the updated value |
| `compareAndSet()` | Performs CAS operation |

---

## Example

```java
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

    public static void main(String[] args) {

        AtomicInteger counter =
                new AtomicInteger(0);

        System.out.println(
                counter.incrementAndGet());

        System.out.println(
                counter.incrementAndGet());

    }

}
```

Output

```text
1

2
```

---

## Why Is It Thread-Safe?

Internally,

```java
incrementAndGet()
```

uses

```text
CAS

↓

Retry Until Success
```

No explicit synchronization is required.

---

# 9. `AtomicLong`

`AtomicLong`

works exactly like

```java
AtomicInteger
```

but stores

```java
long
```

values.

---

## Example

```java
AtomicLong totalUsers =
        new AtomicLong(1000);

totalUsers.incrementAndGet();
```

---

## Use Cases

- Total requests
- Page views
- Download counters
- Large identifiers

---

# 10. `AtomicBoolean`

`AtomicBoolean`

provides thread-safe operations on

```java
boolean
```

values.

---

## Example

```java
AtomicBoolean running =
        new AtomicBoolean(true);
```

---

## Example

```java
if (running.compareAndSet(true, false)) {

    System.out.println(
        "Stopped Successfully");

}
```

Only one thread can successfully change the value from

```text
true

↓

false
```

---

## Real-World Uses

- Shutdown flags
- Initialization guards
- Feature switches
- One-time execution

---

# 11. `AtomicReference`

Sometimes we need to atomically update an object reference instead of a primitive value.

For this,

Java provides

```java
AtomicReference<T>
```

---

## Example

```java
AtomicReference<String> name =
        new AtomicReference<>("Mahesh");
```

---

## CAS Example

```java
name.compareAndSet(
        "Mahesh",
        "Rahul");
```

The update succeeds only if the current value is still

```text
Mahesh
```

---

## Real-World Uses

- Configuration objects
- Immutable object references
- Cached values
- State transitions

---

# 12. Important Methods

---

## `get()`

Returns the current value.

```java
counter.get();
```

---

## `set()`

Updates the value.

```java
counter.set(50);
```

---

## `incrementAndGet()`

```java
counter.incrementAndGet();
```

Flow

```text
Increment

↓

Return New Value
```

---

## `getAndIncrement()`

```java
counter.getAndIncrement();
```

Flow

```text
Return Current Value

↓

Increment
```

---

## Example

Initial Value

```text
10
```

```java
counter.getAndIncrement();
```

Returns

```text
10
```

Current Value

```text
11
```

---

## `compareAndSet()`

The most important method.

```java
compareAndSet(
    expected,
    newValue
);
```

Example

```java
AtomicInteger counter =
        new AtomicInteger(5);

counter.compareAndSet(5, 10);
```

CAS checks

```text
Current == Expected

↓

YES

↓

Update
```

---

# 13. Real-World Examples

---

## Visitor Counter

```java
AtomicInteger visitors =
        new AtomicInteger();
```

Every request

```java
visitors.incrementAndGet();
```

---

## API Request Counter

```java
AtomicLong requests =
        new AtomicLong();
```

---

## One-Time Initialization

```java
AtomicBoolean initialized =
        new AtomicBoolean(false);
```

```java
if (initialized.compareAndSet(false, true)) {

    initialize();

}
```

Only one thread performs initialization.

---

## Configuration Reference

```java
AtomicReference<Config> config =
        new AtomicReference<>();
```

A new immutable configuration object can be swapped atomically.

---

# 💡 Interview Insight

One of the most common interview questions is:

> **What is the difference between `incrementAndGet()` and `getAndIncrement()`?**

Suppose

```text
Value = 5
```

---

### `incrementAndGet()`

```text
Increment

↓

6

↓

Return 6
```

---

### `getAndIncrement()`

```text
Return 5

↓

Increment

↓

Current Value = 6
```
---

# 14. Performance Comparison

Let's compare different approaches for making a counter thread-safe.

| Approach | Visibility | Atomicity | Lock Required | Performance |
|----------|------------|-----------|---------------|-------------|
| Normal Variable | ❌ | ❌ | ❌ | ⭐⭐⭐⭐⭐ |
| `volatile` | ✅ | ❌ | ❌ | ⭐⭐⭐⭐ |
| `AtomicInteger` | ✅ | ✅ | ❌ | ⭐⭐⭐⭐⭐ |
| `synchronized` | ✅ | ✅ | ✅ | ⭐⭐⭐ |

---

## Summary

```text
Need Only Visibility

↓

volatile

────────────────────

Need Atomic Counter

↓

AtomicInteger

────────────────────

Need Critical Section

↓

synchronized
```

---

# 15. ABA Problem (Introduction)

One limitation of CAS is the

```
ABA Problem
```

---

## What Is It?

Suppose

```
Value = A
```

Thread A reads

```
A
```

Before it updates,

another thread changes

```
A → B → A
```

Now

Thread A performs CAS.

```
Expected = A

Current = A

↓

CAS Success
```

CAS believes nothing changed,

even though the value changed twice.

---

## Visualization

```text
Initial

↓

A

↓

Thread B

↓

B

↓

Thread B

↓

A

↓

Thread A

↓

CAS Success
```

The intermediate change was not detected.

---

## Solution

Java provides classes such as

```java
AtomicStampedReference
```

and

```java
AtomicMarkableReference
```

to address this issue.

They attach additional metadata (such as a version number or mark) to detect changes.

> [!NOTE]
> The ABA problem mainly appears in advanced lock-free algorithms. For most application code, `AtomicInteger`, `AtomicLong`, and similar classes are sufficient.

---

# 16. Common Mistakes

---

## ❌ Using `AtomicInteger` for Multiple Variables

Example

```java
AtomicInteger balance;

AtomicInteger transactions;
```

Updating both together is **not** atomic.

If multiple variables must change together,

use synchronization or another coordination mechanism.

---

## ❌ Replacing Every `int` with `AtomicInteger`

Atomic variables introduce overhead.

Use them only for shared mutable state accessed concurrently.

---

## ❌ Mixing Atomic Operations with Non-Atomic Logic

Example

```java
if (counter.get() < 10) {

    counter.incrementAndGet();

}
```

Between

```java
get()
```

and

```java
incrementAndGet()
```

another thread may update the value.

This sequence is **not atomic**.

---

## Better Approach

```java
while (true) {

    int current = counter.get();

    if (current >= 10) {

        break;

    }

    if (counter.compareAndSet(current, current + 1)) {

        break;

    }

}
```

The loop retries until the CAS operation succeeds.

---

## ❌ Assuming Atomic Variables Replace All Synchronization

Atomic variables are excellent for **single-variable atomic operations**.

They are **not** a replacement for synchronization when:

- Multiple variables must remain consistent.
- Complex business logic must execute atomically.
- Entire critical sections require protection.

---

# 17. Best Practices

✅ Use atomic variables for simple shared counters and flags.

✅ Prefer `AtomicInteger` over `volatile int` when incrementing or decrementing.

✅ Use `compareAndSet()` for lock-free state transitions.

✅ Use immutable objects with `AtomicReference` where appropriate.

✅ Keep atomic operations focused on a single shared value.

---

# 18. Interview Questions

### 1. What are Atomic Variables?

They are thread-safe classes that provide atomic operations without explicit locking.

---

### 2. Which package contains Atomic Variables?

```java
java.util.concurrent.atomic
```

---

### 3. Why are Atomic Variables faster than `synchronized`?

They avoid monitor locking and typically rely on the Compare-And-Set (CAS) algorithm.

---

### 4. What is CAS?

Compare-And-Set updates a value only if it still matches the expected value.

---

### 5. Does `AtomicInteger` use locks?

Generally, no.

It relies on lock-free CAS operations provided by the JVM and underlying hardware.

---

### 6. Difference between `AtomicInteger` and `volatile int`?

| `AtomicInteger` | `volatile int` |
|-----------------|----------------|
| Visibility + Atomicity | Visibility only |
| Supports atomic increment | `count++` is not atomic |
| CAS based | No CAS support |

---

### 7. What is the ABA Problem?

A value changes from

```text
A → B → A
```

A CAS operation may incorrectly assume the value never changed.

---

### 8. When should you use `AtomicReference`?

When an object reference must be updated atomically.

---

# 19. Quick Revision

```text
Need Visibility?

↓

volatile

────────────────────

Need Atomic Counter?

↓

AtomicInteger

────────────────────

Need Critical Section?

↓

synchronized
```

---

## CAS Flow

```text
Read Current

↓

Compare

↓

Equal?

↓

YES

↓

Update

────────────

NO

↓

Retry
```

---

## Atomic Classes

```text
AtomicInteger

AtomicLong

AtomicBoolean

AtomicReference
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What Atomic Variables are
- [x] Why `volatile` is not enough
- [x] CAS (Compare-And-Set)
- [x] Lock-Free Programming
- [x] `AtomicInteger`
- [x] `AtomicLong`
- [x] `AtomicBoolean`
- [x] `AtomicReference`
- [x] ABA Problem (Introduction)
- [x] Best practices

---

# 📌 Key Takeaways

- Atomic Variables provide **thread-safe operations without explicit locks**.
- They rely on the **Compare-And-Set (CAS)** algorithm.
- `AtomicInteger` is the preferred choice for concurrent counters.
- `AtomicBoolean` is useful for state flags and one-time initialization.
- `AtomicReference` enables atomic updates of object references.
- Atomic Variables work best for **single-variable operations**.
- For multi-variable consistency or complex critical sections, synchronization or locks are still required.

> [!TIP]
> **Interview Rule**
>
> Ask yourself:
>
> - Need only visibility? → `volatile`
> - Need atomic operations on one variable? → `AtomicInteger` / other atomic classes
> - Need to protect multiple variables or a critical section? → `synchronized` or `Lock`

---

# 📖 Next Topic

➡️ **15. ReentrantLock**

In the next chapter, we'll explore one of the most powerful locking mechanisms in Java:

- What is `ReentrantLock`?
- Why use it instead of `synchronized`?
- Reentrancy
- Fair vs Non-Fair Locks
- `lock()`
- `unlock()`
- `tryLock()`
- `lockInterruptibly()`
- `Condition`
- Best practices
- Interview questions

> ⭐ **`ReentrantLock` provides advanced features that are not available with the `synchronized` keyword, making it a common topic in enterprise Java applications and SDE interviews.**

- Performance comparison
