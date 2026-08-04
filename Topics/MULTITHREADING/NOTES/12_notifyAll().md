# 📢 12. notifyAll()

> [!NOTE]
> The `notifyAll()` method wakes **all threads** waiting on the current object's monitor.
>
> The awakened threads do **not** execute immediately. They become eligible to compete for the monitor lock, and only one thread can acquire the monitor at a time.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is `notifyAll()`?](#2-what-is-notifyall)
- [3. Why Do We Need `notifyAll()`?](#3-why-do-we-need-notifyall)
- [4. How `notifyAll()` Works](#4-how-notifyall-works)
- [5. Does `notifyAll()` Release the Monitor?](#5-does-notifyall-release-the-monitor)
- [6. `notify()` vs `notifyAll()`](#6-notify-vs-notifyall)
- [7. Which Thread Executes First?](#7-which-thread-executes-first)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Monitor
- Wait Set
- Entry Set
- `wait()`
- `notify()`
- Inter Thread Communication

---

# 2. What is `notifyAll()`?

The

```java
notifyAll()
```

method wakes **every thread** waiting on the monitor of the current object.

Unlike

```java
notify()
```

which wakes only one thread,

`notifyAll()`

moves **all waiting threads** out of the Wait Set.

---

## Definition

> `notifyAll()` wakes all threads waiting on the current object's monitor. Those threads then compete to reacquire the monitor before continuing execution.

---

## Syntax

```java
notifyAll();
```

---

## Important

`notifyAll()` affects only the threads waiting on **the same object**.

Example

```java
synchronized (lock) {

    lock.notifyAll();

}
```

Only threads waiting on

```java
lock
```

are awakened.

---

# 3. Why Do We Need `notifyAll()`?

Suppose three threads are waiting.

```text
Thread A

Thread B

Thread C
```

Now,

the shared state changes.

More than one waiting thread may now be able to proceed.

Using

```java
notify();
```

wakes only one thread.

The others continue waiting,

even if they could also make progress.

---

## Better Solution

```java
notifyAll();
```

Every waiting thread wakes,

checks its condition,

and only those whose conditions are satisfied continue.

---

## Example

Imagine a warehouse.

```
Workers

↓

Waiting For Packages
```

A truck arrives with many packages.

Instead of waking

```
One Worker
```

the manager wakes

```
All Workers
```

Each worker checks whether there is work available.

---

# 4. How `notifyAll()` Works

Suppose

three threads are waiting.

```
Thread A

Thread B

Thread C
```

Current State

```text
Wait Set

──────────────

Thread A

Thread B

Thread C
```

Another thread executes

```java
notifyAll();
```

---

## Internal Flow

```text
Wait Set

↓

notifyAll()

↓

Entry Set

↓

Thread A

Thread B

Thread C

↓

Compete For Monitor

↓

One Thread Acquires Lock

↓

Continue

↓

Remaining Threads Wait For Lock
```

---

## Visualization

```text
WAIT SET

──────────────

A

B

C

↓

notifyAll()

↓

ENTRY SET

──────────────

A

B

C

↓

Monitor

↓

One Thread Runs
```

---

# 5. Does `notifyAll()` Release the Monitor?

**No.**

Just like

```java
notify()
```

`notifyAll()`

does **not** release the monitor.

The current thread continues executing until it exits the synchronized block.

---

## Example

```java
synchronized (lock) {

    updateState();

    lock.notifyAll();

    System.out.println("Still Executing");

}
```

Output

```text
Still Executing
```

appears before any awakened thread continues,

because the monitor is still owned by the current thread.

---

## Visualization

```text
Current Thread

↓

notifyAll()

↓

Still Owns Monitor

↓

Finish

↓

Release Monitor

────────────────────────

Waiting Threads

↓

Acquire Monitor One By One
```

---

# 6. `notify()` vs `notifyAll()`

| `notify()` | `notifyAll()` |
|------------|---------------|
| Wakes one waiting thread | Wakes all waiting threads |
| More efficient in some cases | Safer when multiple waiting conditions exist |
| Selection is unspecified | Every waiting thread gets a chance to recheck its condition |
| Lower wake-up overhead | May wake threads that still cannot proceed |

---

## Example

Suppose

```
Thread A

WAITING

Thread B

WAITING

Thread C

WAITING
```

---

### Using `notify()`

```text
WAIT SET

↓

Thread B

↓

Continue

────────────────

Thread A

Still Waiting

────────────────

Thread C

Still Waiting
```

---

### Using `notifyAll()`

```text
WAIT SET

↓

A

B

C

↓

ENTRY SET

↓

All Compete

↓

One Runs

↓

Others Wait
```

---

# 7. Which Thread Executes First?

This is another popular interview question.

Suppose

```java
notifyAll();
```

awakens

```
Thread A

Thread B

Thread C
```

Which thread executes first?

Answer

```
Unknown
```

The JVM scheduler decides.

The Java Language Specification does not define the execution order.

---

## Important

Even though all threads are awakened,

only

```
One Thread
```

can own the monitor at a time.

Therefore,

execution remains mutually exclusive.

---

## Visualization

```text
notifyAll()

↓

A

B

C

↓

Compete

↓

Scheduler Chooses

↓

Acquire Monitor

↓

Run
```
---

# 8. Producer–Consumer Example

Let's see where

```java
notifyAll();
```

can be useful.

```java
class Buffer {

    private int data;
    private boolean available = false;

    public synchronized void produce(int value)
            throws InterruptedException {

        while (available) {

            wait();

        }

        data = value;
        available = true;

        System.out.println(
            "Produced : " + value
        );

        notifyAll();

    }

    public synchronized void consume()
            throws InterruptedException {

        while (!available) {

            wait();

        }

        System.out.println(
            "Consumed : " + data
        );

        available = false;

        notifyAll();

    }

}
```

---

## Flow

Initially

```text
Queue Empty
```

Consumers

```text
↓

WAITING
```

Producer

```text
↓

Produce Item

↓

notifyAll()
```

All waiting consumers wake up.

Each consumer checks

```java
while (!available)
```

Only one consumer removes the item.

The others find

```text
Queue Empty
```

again,

so they execute

```java
wait();
```

once more.

---

## Why Does This Work?

Because every waiting thread rechecks the condition.

This is why

```java
while
```

is always preferred over

```java
if
```

---

# 9. When Should You Use `notifyAll()`?

Use

```java
notifyAll();
```

when

- Multiple threads may be waiting.
- Different threads wait for different conditions.
- You cannot safely determine which thread should wake up.
- Correctness is more important than minimizing wake-ups.

---

## Example

Suppose

```
Thread A

Waiting For Data

────────────────────

Thread B

Waiting For Space

────────────────────

Thread C

Waiting For Connection
```

If only one thread is notified,

it may not be the one whose condition is now true.

Using

```java
notifyAll();
```

ensures that every waiting thread gets a chance to re-evaluate its own condition.

---

# 10. Performance Considerations

Although

```java
notifyAll();
```

is often safer,

it may also wake threads that cannot make progress.

---

## Example

Suppose

100 threads

are waiting.

Only

1 thread

can actually continue.

Using

```java
notifyAll();
```

awakens all 100 threads.

```
WAIT SET

↓

100 Threads

↓

notifyAll()

↓

100 Runnable Threads

↓

Only One Gets Monitor

↓

99 Go Back To Waiting
```

This increases context switching and scheduling overhead.

---

## Trade-off

| `notify()` | `notifyAll()` |
|------------|---------------|
| Fewer wake-ups | More wake-ups |
| Better performance in some situations | Better correctness in complex coordination |
| Risk of waking the "wrong" thread | Safer when multiple conditions exist |

> [!TIP]
> Prefer **correctness first**. Optimize only after measuring performance.

---

# 11. Common Mistakes

---

## ❌ Assuming All Threads Run Together

Wrong

```text
notifyAll()

↓

All Threads Running
```

Correct

```text
notifyAll()

↓

All Threads Wake

↓

Compete For Monitor

↓

One Thread Runs
```

---

## ❌ Forgetting to Recheck the Condition

Wrong

```java
if (!available) {

    wait();

}
```

Correct

```java
while (!available) {

    wait();

}
```

Every awakened thread must verify that its condition is actually satisfied.

---

## ❌ Calling `notifyAll()` Outside a Synchronized Block

Wrong

```java
lock.notifyAll();
```

This throws

```text
IllegalMonitorStateException
```

Correct

```java
synchronized (lock) {

    lock.notifyAll();

}
```

---

## ❌ Thinking `notifyAll()` Is Always Better

Not necessarily.

If only one waiting thread should proceed,

and you can identify that safely,

`notify()` may be more efficient.

---

# 12. Best Practices

✅ Use `notifyAll()` when different waiting conditions share the same monitor.

✅ Always protect waiting conditions with a `while` loop.

✅ Update shared state before calling `notifyAll()`.

✅ Keep synchronized blocks small.

✅ Prefer higher-level concurrency utilities (`BlockingQueue`, `Semaphore`, etc.) when they provide a clearer solution.

---

# 13. Interview Questions

### 1. What does `notifyAll()` do?

It wakes all threads waiting on the current object's monitor.

---

### 2. Does `notifyAll()` release the monitor?

No.

The current thread continues executing until it exits the synchronized block or method.

---

### 3. Do all awakened threads execute immediately?

No.

They first compete to reacquire the monitor.

Only one thread can own the monitor at a time.

---

### 4. When should `notifyAll()` be preferred over `notify()`?

When multiple waiting threads or multiple waiting conditions exist and it's unsafe to choose a single thread.

---

### 5. Why can `notifyAll()` reduce performance?

Because it may wake many threads that cannot actually proceed, increasing context switching and scheduling overhead.

---

### 6. Can `notifyAll()` be called outside a synchronized block?

No.

Doing so throws

```text
IllegalMonitorStateException
```

---

### 7. Why is `while` still required after `notifyAll()`?

Because every awakened thread must verify that its own waiting condition is now true before continuing.

---

# 14. Quick Revision

```text
WAIT SET

↓

notifyAll()

↓

ENTRY SET

↓

All Threads

↓

Compete For Monitor

↓

One Thread Runs

↓

Others Wait
```

---

## `notifyAll()` Checklist

```text
✔ Wakes All Waiting Threads

✔ Does Not Release Monitor

✔ Must Be Inside synchronized

✔ Uses Object Monitor

✔ All Threads Recheck Conditions
```

---

## `notify()` vs `notifyAll()`

| `notify()` | `notifyAll()` |
|------------|---------------|
| Wake one | Wake all |
| Lower overhead | Higher overhead |
| May be sufficient for simple coordination | Safer for multiple waiting conditions |
| Unspecified thread selection | Every waiting thread gets a chance |

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `notifyAll()` is
- [x] Internal working
- [x] Why it doesn't release the monitor
- [x] `notify()` vs `notifyAll()`
- [x] Wait Set and Entry Set
- [x] Producer–Consumer usage
- [x] Performance considerations
- [x] Best practices

---

# 📌 Key Takeaways

- `notifyAll()` wakes **every** thread waiting on the current object's monitor.
- Waking threads is **not** the same as allowing them to run immediately.
- Each awakened thread must reacquire the monitor before continuing.
- `notifyAll()` is often safer than `notify()` when multiple waiting conditions share the same monitor.
- Always guard waiting conditions with a `while` loop.
- Use `notifyAll()` for correctness first; consider performance only after verifying the design.

> [!TIP]
> **Interview Rule**
>
> If you're unsure whether `notify()` is sufficient,
>
> choose **`notifyAll()`** for correctness.
>
> It's generally easier to optimize a correct program than to debug one that occasionally deadlocks or leaves threads waiting forever.

---

# 📖 Next Topic

➡️ **13. `volatile` Keyword**

In the next chapter, we'll explore one of the most important concepts in the **Java Memory Model (JMM)**:

- What is `volatile`?
- Visibility Problem
- CPU Caches
- Main Memory
- Happens-Before Relationship
- `volatile` vs `synchronized`
- Atomicity vs Visibility
- Real-world examples
- Interview questions

> ⭐ **`volatile` is one of the most frequently asked Java concurrency topics because it introduces memory visibility without using locks.**
