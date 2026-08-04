# 😴 10. wait()

> [!NOTE]
> The `wait()` method causes the current thread to **release the monitor lock** and enter the **WAITING** state until another thread notifies it or a timeout occurs.
>
> It is one of the core methods used for **Inter Thread Communication (ITC)** in Java.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is `wait()`?](#2-what-is-wait)
- [3. Why Do We Need `wait()`?](#3-why-do-we-need-wait)
- [4. Why `sleep()` Is Not Enough](#4-why-sleep-is-not-enough)
- [5. How `wait()` Works](#5-how-wait-works)
- [6. Method Signatures](#6-method-signatures)
- [7. Thread State Changes](#7-thread-state-changes)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Synchronization
- Monitor
- Inter Thread Communication
- `notify()`
- `notifyAll()`

---

# 2. What is `wait()`?

The

```java
wait()
```

method tells the current thread

> **"I cannot continue right now. Release the monitor lock and wait until another thread notifies me."**

Unlike

```java
Thread.sleep()
```

`wait()`

releases the monitor lock before waiting.

---

## Definition

> `wait()` is an instance method of the `Object` class that causes the current thread to release the object's monitor and enter the WAITING state.

---

## Basic Syntax

```java
wait();
```

---

## High-Level Flow

```text
Acquire Monitor

↓

Condition False

↓

wait()

↓

Release Monitor

↓

WAITING

↓

notify()

↓

Runnable

↓

Reacquire Monitor

↓

Continue
```

---

# 3. Why Do We Need `wait()`?

Suppose a consumer thread needs data from a producer.

Initially

```text
Queue Empty
```

Without

```java
wait()
```

the consumer repeatedly checks

```java
while(queue.isEmpty()) {

}
```

This wastes CPU time.

---

## Better Solution

Consumer

```text
Queue Empty

↓

wait()

↓

WAITING
```

Producer

```text
Adds Item

↓

notify()
```

Consumer wakes only when useful work is available.

---

## Benefits

- No busy waiting
- Better CPU utilization
- Efficient thread coordination
- Reduced power consumption

---

# 4. Why `sleep()` Is Not Enough

Many beginners confuse

```java
sleep()
```

and

```java
wait()
```

Although both pause a thread,

their purposes are different.

---

## `sleep()`

```java
Thread.sleep(1000);
```

Purpose

```
Pause for Time
```

The thread sleeps for a specified duration.

It **does not release** the monitor lock.

---

## `wait()`

```java
wait();
```

Purpose

```
Wait for Condition
```

The thread waits until another thread signals that it can continue.

It **releases** the monitor lock.

---

## Comparison

| `sleep()` | `wait()` |
|-----------|----------|
| Class: `Thread` | Class: `Object` |
| Time-based | Condition-based |
| Does not release lock | Releases lock |
| Used for delaying execution | Used for thread coordination |

> [!IMPORTANT]
> Use `sleep()` when you want to **pause**.
>
> Use `wait()` when you want to **coordinate**.

---

# 5. How `wait()` Works

Suppose a thread owns the monitor.

```
Thread A

↓

Acquire Lock

↓

Critical Section
```

Now,

it encounters

```java
wait();
```

The JVM performs these steps.

---

## Step 1

Release the monitor.

```
Thread A

↓

Release Lock
```

---

## Step 2

Move the thread to the object's

```
Wait Set
```

---

## Step 3

Thread enters

```
WAITING
```

state.

---

## Step 4

Another thread acquires the monitor,

updates the shared state,

and calls

```java
notify();
```

---

## Step 5

The waiting thread becomes runnable.

It must still reacquire the monitor before continuing.

---

## Visualization

```text
Thread A

↓

Acquire Lock

↓

wait()

↓

Release Lock

↓

WAITING

────────────────────────

Thread B

↓

Acquire Lock

↓

Update State

↓

notify()

↓

Release Lock

────────────────────────

Thread A

↓

Acquire Lock Again

↓

Continue
```

---

# 6. Method Signatures

The `Object` class provides three overloaded versions of `wait()`.

---

## `wait()`

```java
wait();
```

Wait indefinitely until notified.

---

## `wait(long timeout)`

```java
wait(5000);
```

Wait for

```
5000 milliseconds
```

or until notified,

whichever happens first.

---

## `wait(long timeout, int nanos)`

```java
wait(5000, 500000);
```

Provides finer timeout precision using milliseconds and nanoseconds.

---

## Summary

| Method | Description |
|---------|-------------|
| `wait()` | Wait indefinitely |
| `wait(long)` | Wait with timeout |
| `wait(long, int)` | Wait with millisecond + nanosecond precision |

---

# 7. Thread State Changes

Calling

```java
wait();
```

changes the thread's state.

---

## Before

```
RUNNABLE
```

---

## During Wait

```
WAITING
```

---

## After Notification

```
RUNNABLE
```

---

## Final Execution

```
RUNNABLE

↓

WAITING

↓

RUNNABLE

↓

Acquire Monitor

↓

Continue
```

---

## Visualization

```text
RUNNABLE

↓

wait()

↓

WAITING

↓

notify()

↓

RUNNABLE

↓

Monitor Acquired

↓

Running
```

> [!TIP]
> A notified thread does **not** immediately execute.
>
> It first competes to reacquire the monitor lock.

---

# 8. Wait Set

When a thread calls

```java
wait();
```

it does **not** disappear.

Instead,

the JVM places it into a special waiting area associated with the object's monitor.

This waiting area is called the

```text
Wait Set
```

Every Java object has its own monitor,

and every monitor maintains its own Wait Set.

---

## Visualization

```text
              Object

      ┌─────────────────────┐

      │      Monitor        │

      │─────────────────────│

      │ Wait Set            │

      │─────────────────────│

      │ Thread A            │

      │ Thread B            │

      │ Thread C            │

      └─────────────────────┘
```

Threads remain here until

- another thread calls `notify()`
- another thread calls `notifyAll()`
- a timeout expires (timed wait)
- the thread is interrupted

---

## Important

Threads in the Wait Set

- are **not executing**
- do **not own the monitor**
- consume very little CPU

---

# 9. Why Must `wait()` Be Called Inside `synchronized`?

This is one of the most common interview questions.

Suppose we write

```java
Object lock = new Object();

lock.wait();
```

What happens?

The JVM throws

```text
IllegalMonitorStateException
```

---

## Why?

Because the current thread does **not** own the monitor.

Remember

```text
wait()

↓

Release Monitor
```

A thread cannot release a monitor that it never acquired.

---

## Correct Usage

```java
synchronized (lock) {

    lock.wait();

}
```

Execution

```text
Acquire Monitor

↓

Own Monitor

↓

wait()

↓

Release Monitor

↓

WAITING
```

---

## Interview Rule

> A thread must own an object's monitor before calling
>
> - `wait()`
> - `notify()`
> - `notifyAll()`

---

# 10. IllegalMonitorStateException

If a thread calls

```java
wait()

notify()

notifyAll()
```

without owning the monitor,

Java throws

```text
IllegalMonitorStateException
```

---

## Example

```java
Object lock = new Object();

lock.notify();
```

Output

```text
Exception in thread "main"

java.lang.IllegalMonitorStateException
```

---

## Correct Version

```java
synchronized (lock) {

    lock.notify();

}
```

---

## Why Does Java Enforce This?

Imagine two unrelated threads calling

```java
notify();
```

on the same object without synchronization.

The JVM would have no reliable way to coordinate ownership of the monitor.

Requiring monitor ownership ensures that waiting and notification are coordinated correctly.

---

# 11. Timed Waiting

Sometimes waiting forever is undesirable.

Java allows a timeout.

Example

```java
wait(3000);
```

The thread waits

```text
3 Seconds
```

or until notified,

whichever happens first.

---

## Timeline

```text
Thread

↓

wait(3000)

↓

WAITING

───────────────

notify()

↓

Continue

OR

───────────────

Timeout

↓

Continue
```

---

## Benefit

Timed waiting prevents a thread from remaining blocked indefinitely if a notification never arrives.

---

# 12. Spurious Wakeups ⭐⭐⭐⭐⭐

One of the most confusing topics in Java concurrency is the

```text
Spurious Wakeup
```

---

## What Is a Spurious Wakeup?

A thread waiting on

```java
wait();
```

may occasionally wake up **without**

- `notify()`
- `notifyAll()`
- timeout

This rare event is called a

```text
Spurious Wakeup
```

---

## Does It Really Happen?

Yes.

The Java Language Specification explicitly allows it.

Although uncommon,

correct programs must handle it.

---

## Why Does Java Allow It?

The specification gives JVM implementations flexibility to map Java synchronization onto different operating systems and hardware.

Instead of guaranteeing that wakeups occur **only** because of notifications,

the specification allows occasional unexpected wakeups.

---

# 13. Why `while` Is Preferred Over `if` ⭐⭐⭐⭐⭐

Consider

```java
if (queue.isEmpty()) {

    queue.wait();

}
```

Looks correct.

But suppose

the thread wakes up unexpectedly.

Execution continues.

The queue may still be empty.

The consumer now attempts to remove an item that doesn't exist.

---

## Correct Version

```java
while (queue.isEmpty()) {

    queue.wait();

}
```

---

## Why?

After waking,

the thread checks the condition again.

```
Queue Empty?

↓

YES

↓

wait()

──────────────

Queue Empty?

↓

NO

↓

Continue
```

---

## Another Reason

Suppose multiple consumers are waiting.

```
Consumer A

WAITING

Consumer B

WAITING
```

Producer adds

```
One Item
```

and executes

```java
notifyAll();
```

Both consumers wake.

Consumer A removes the item.

Consumer B now finds

```
Queue Empty
```

Again,

the condition must be checked.

Using

```java
while
```

handles this correctly.

---

## Visualization

```text
WAITING

↓

Wake Up

↓

Check Condition

↓

Still False?

↓

YES

↓

wait()

──────────────

NO

↓

Continue
```

---

# 14. Thread Interruption

A waiting thread may also be interrupted.

Example

```java
thread.interrupt();
```

If a thread is waiting,

`wait()` throws

```java
InterruptedException
```

---

## Example

```java
try {

    lock.wait();

} catch (InterruptedException e) {

    Thread.currentThread().interrupt();

}
```

---

## Why Re-interrupt?

Calling

```java
Thread.currentThread().interrupt();
```

restores the interrupted status so higher-level code can respond appropriately.

> [!TIP]
> Swallowing `InterruptedException` silently is generally considered a bad practice.

---

# 💡 Interview Insight

A classic interview question is:

> **Why is `while` preferred over `if` with `wait()`?**

Correct Answer:

- Spurious wakeups
- Multiple waiting threads
- The condition may change before the thread reacquires the monitor

Therefore,

the waiting condition must always be rechecked.

---

# 15. Complete Producer–Consumer Example

Let's combine everything we've learned into a simple Producer–Consumer implementation.

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

        notify();

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

        notify();

    }

}
```

---

## How It Works

### Producer

```
Buffer Full?

↓

YES

↓

wait()

──────────────

NO

↓

Store Item

↓

notify()
```

---

### Consumer

```
Buffer Empty?

↓

YES

↓

wait()

──────────────

NO

↓

Consume Item

↓

notify()
```

The producer and consumer cooperate efficiently without wasting CPU time.

---

# 16. JVM Working of `wait()`

Although we simply write

```java
lock.wait();
```

a lot happens inside the JVM.

---

## Internal Flow

```text
Thread Owns Monitor

↓

wait()

↓

Release Monitor

↓

Move Thread To Wait Set

↓

WAITING State

────────────────────

notify()

↓

Move Thread To Entry Set

↓

BLOCKED (Waiting For Monitor)

↓

Acquire Monitor

↓

RUNNABLE

↓

Continue Execution
```

---

## Important Observation

Many developers think

```
notify()

↓

Running
```

This is incorrect.

The actual flow is

```
WAITING

↓

notify()

↓

BLOCKED

↓

Acquire Monitor

↓

RUNNABLE

↓

Running
```

The thread must reacquire the monitor before it can continue.

---

# 17. Common Mistakes

---

## ❌ Using `if` Instead of `while`

Wrong

```java
if (queue.isEmpty()) {

    wait();

}
```

Correct

```java
while (queue.isEmpty()) {

    wait();

}
```

Reason

- Spurious wakeups
- Multiple waiting threads
- Condition may change before the thread reacquires the monitor

---

## ❌ Calling `wait()` Without Synchronization

Wrong

```java
lock.wait();
```

Throws

```text
IllegalMonitorStateException
```

Correct

```java
synchronized (lock) {

    lock.wait();

}
```

---

## ❌ Forgetting That `wait()` Releases the Monitor

Many beginners think

```
wait()

↓

Thread Sleeps

↓

Lock Still Held
```

Wrong.

The correct behavior is

```
wait()

↓

Release Monitor

↓

WAITING
```

This release allows other threads to enter the synchronized block and make progress.

---

## ❌ Ignoring `InterruptedException`

Bad

```java
catch (InterruptedException e) {

}
```

Good

```java
catch (InterruptedException e) {

    Thread.currentThread().interrupt();

}
```

Restoring the interrupt status allows higher-level code to handle interruption correctly.

---

# 18. Best Practices

✅ Always call `wait()` inside a synchronized block or synchronized method.

✅ Always check the waiting condition in a `while` loop.

✅ Keep synchronized blocks as small as practical.

✅ Restore the interrupted status when catching `InterruptedException`, unless your method intentionally handles it.

✅ Prefer higher-level concurrency utilities (`BlockingQueue`, `CountDownLatch`, etc.) for new code when appropriate.

---

# 19. Interview Questions

### 1. What does `wait()` do?

It releases the monitor lock and places the current thread into the object's Wait Set until it is notified, interrupted, or a timeout occurs.

---

### 2. Why is `wait()` defined in `Object` instead of `Thread`?

Because waiting is associated with an object's monitor, and every Java object has a monitor.

---

### 3. Does `wait()` release the monitor lock?

Yes.

This is the key difference between `wait()` and `Thread.sleep()`.

---

### 4. Can `wait()` be called outside a synchronized block?

No.

The calling thread must own the object's monitor, otherwise an `IllegalMonitorStateException` is thrown.

---

### 5. Why is `while` preferred over `if`?

Because of

- Spurious wakeups
- Multiple waiting threads
- Rechecking the condition after reacquiring the monitor

---

### 6. Does `notify()` immediately resume a waiting thread?

No.

The notified thread becomes eligible to run but must first reacquire the monitor.

---

### 7. What thread state does `wait()` cause?

The thread enters the **WAITING** state (or **TIMED_WAITING** when using a timeout).

---

### 8. Can `wait()` wake up without `notify()`?

Yes.

The Java specification allows **spurious wakeups**.

---

### 9. What exception does `wait()` throw?

```java
InterruptedException
```

if the waiting thread is interrupted.

It may also result in

```java
IllegalMonitorStateException
```

if called without owning the monitor.

---

# 20. Quick Revision

```text
Thread Owns Monitor

↓

wait()

↓

Release Monitor

↓

WAITING

↓

notify()

↓

BLOCKED

↓

Acquire Monitor

↓

RUNNABLE

↓

Continue
```

---

## `wait()` Checklist

```text
✔ Releases Monitor

✔ Enters WAITING

✔ Belongs To Object

✔ Must Be Inside synchronized

✔ Throws InterruptedException

✔ Use while, Not if
```

---

## `wait()` vs `sleep()`

| `wait()` | `sleep()` |
|-----------|-----------|
| `Object` method | `Thread` method |
| Releases monitor | Keeps monitor |
| Used for coordination | Used for delaying execution |
| Requires monitor ownership | No monitor ownership required |
| Wakes by notification, timeout, or interruption | Wakes after timeout or interruption |

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `wait()` is
- [x] Why it is needed
- [x] How it works internally
- [x] Wait Set
- [x] Method overloads
- [x] Thread state transitions
- [x] Why `wait()` releases the monitor
- [x] Why `while` is preferred over `if`
- [x] Spurious wakeups
- [x] `IllegalMonitorStateException`
- [x] `InterruptedException`
- [x] Producer–Consumer example

---

# 📌 Key Takeaways

- `wait()` is the foundation of monitor-based thread coordination in Java.
- It **releases the monitor**, allowing other threads to enter the synchronized region.
- Waiting threads are placed in the object's **Wait Set**.
- A notified thread must **reacquire the monitor** before continuing.
- Always guard `wait()` with a **`while` loop**.
- `wait()` should only be used while owning the appropriate monitor.
- Modern concurrency utilities often provide simpler alternatives, but understanding `wait()` remains essential for interviews and understanding Java's concurrency model.

> [!TIP]
> **Interview Rule**
>
> The sequence to remember is:
>
> **Acquire Monitor → `wait()` → Release Monitor → WAITING → `notify()` → BLOCKED → Reacquire Monitor → Continue**
>
> This is the exact lifecycle interviewers expect you to understand.

---

# 📖 Next Topic

➡️ **11. `notify()`**

In the next chapter, we'll study:

- What `notify()` actually does
- Which thread gets notified?
- Entry Set vs Wait Set
- Does `notify()` release the monitor?
- Internal JVM behavior
- Common mistakes
- Best practices
- Interview questions

> ⭐ **Understanding `notify()` requires understanding that waking a thread and allowing it to run are two different things.**
>
> That's exactly what we'll explore next.
