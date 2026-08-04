# 🤝 09. Inter Thread Communication

> [!NOTE]
> **Inter Thread Communication (ITC)** is a mechanism that allows multiple threads to **coordinate and communicate** with each other while working on shared resources.
>
> Instead of continuously checking for changes (busy waiting), threads can efficiently wait and notify each other when required.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is Inter Thread Communication?](#2-what-is-inter-thread-communication)
- [3. Why Do We Need It?](#3-why-do-we-need-it)
- [4. Busy Waiting Problem](#4-busy-waiting-problem)
- [5. Producer-Consumer Problem](#5-producer-consumer-problem)
- [6. High-Level Working](#6-high-level-working)
- [7. Communication vs Synchronization](#7-communication-vs-synchronization)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Thread
- Synchronization
- `synchronized`
- Thread Safety
- Deadlock

---

# 2. What is Inter Thread Communication?

Inter Thread Communication (ITC) is a mechanism through which threads **exchange information and coordinate their execution**.

Instead of one thread repeatedly checking whether another thread has completed a task,

the waiting thread can **sleep efficiently** until it receives a notification.

---

## Definition

> Inter Thread Communication is a mechanism that enables threads to cooperate by waiting for specific conditions and notifying each other when those conditions become true.

---

## Real-Life Analogy

Imagine a restaurant.

```
Customer

↓

Places Order

↓

Waits

↓

Chef Prepares Food

↓

Waiter Notifies Customer

↓

Customer Eats
```

The customer does **not** walk into the kitchen every second asking,

> "Is my food ready?"

Instead,

the waiter informs the customer when the food is ready.

This is efficient communication.

---

# 3. Why Do We Need It?

Suppose two threads share a queue.

```
Producer

↓

Queue

↑

Consumer
```

The producer inserts data.

The consumer removes data.

---

## Problem

Suppose the queue is empty.

Should the consumer continuously execute

```java
if(queue.isEmpty()) {

}
```

every millisecond?

No.

That wastes CPU time.

Instead,

the consumer should

```
Wait

↓

Producer Adds Data

↓

Producer Notifies

↓

Consumer Continues
```

This is exactly what Inter Thread Communication provides.

---

# 4. Busy Waiting Problem

Without Inter Thread Communication,

a thread may repeatedly check a condition.

Example

```java
while(queue.isEmpty()) {

}
```

This loop continuously executes.

---

## Visualization

```
Queue Empty

↓

Consumer Checks

↓

Still Empty

↓

Checks Again

↓

Still Empty

↓

Checks Again

↓

Still Empty
```

This is called

```
Busy Waiting
```

or

```
Busy Spinning
```

---

## Why Is It Bad?

Busy waiting

- Wastes CPU cycles
- Reduces performance
- Consumes unnecessary power
- Prevents efficient scheduling

---

## Better Approach

```
Queue Empty

↓

Consumer Waits

↓

Producer Adds Data

↓

Producer Notifies

↓

Consumer Continues
```

Notice

No unnecessary CPU usage.

> [!TIP]
> Good concurrent programs avoid busy waiting whenever possible.

---

# 5. Producer–Consumer Problem

The Producer–Consumer Problem is the classic example of Inter Thread Communication.

---

## Producer

Produces data.

```
Producer

↓

Creates Item

↓

Adds To Queue
```

---

## Consumer

Consumes data.

```
Consumer

↓

Removes Item

↓

Processes Item
```

---

## Shared Buffer

```
        Producer

            │

            ▼

     ┌──────────────┐

     │    Queue     │

     └──────────────┘

            ▲

            │

        Consumer
```

---

## Problem

If

```
Queue Empty
```

Consumer should wait.

If

```
Queue Full
```

Producer should wait.

Otherwise,

- Data may be lost.
- Memory may overflow.
- CPU may be wasted.

---

## Goal

Threads should **cooperate**, not compete.

---

# 6. High-Level Working

Inter Thread Communication follows a simple cycle.

```
Thread A

↓

Needs Resource

↓

Condition False

↓

Wait

────────────────────

Thread B

↓

Updates Resource

↓

Condition True

↓

Notify

────────────────────

Thread A

↓

Resumes Execution
```

---

## Visualization

```text
Thread A

↓

Waiting

────────────────────────

Thread B

↓

Updates Shared Resource

↓

Signals Waiting Thread

────────────────────────

Thread A

↓

Continues
```

---

# 7. Communication vs Synchronization

Many beginners confuse these concepts.

| Synchronization | Inter Thread Communication |
|-----------------|----------------------------|
| Prevents simultaneous access | Coordinates thread execution |
| Focuses on mutual exclusion | Focuses on cooperation |
| Uses locks | Uses waiting and notification |
| Solves race conditions | Solves coordination problems |

---

## Example

Imagine a meeting room.

### Synchronization

```
Only One Person

Can Enter
```

This prevents conflicts.

---

### Inter Thread Communication

```
Person A

↓

Waits

↓

Person B

Finishes Meeting

↓

Signals Person A

↓

Person A Enters
```

Here,

threads are cooperating.

---

## Important Relationship

```text
Synchronization

↓

Protects Shared Resource

────────────────────────

Inter Thread Communication

↓

Coordinates Threads
```

Both are often used together,

but they solve different problems.

> [!IMPORTANT]
> Inter Thread Communication **depends on synchronization**.
>
> The methods `wait()`, `notify()`, and `notifyAll()` must be used while holding the appropriate monitor lock.
>
> We'll understand why in the next chapters.

---

# 8. Java Support for Inter Thread Communication

Java provides three methods for implementing Inter Thread Communication.

These methods belong to the

```java
java.lang.Object
```

class.

```java
wait()

notify()

notifyAll()
```

---

## Why Are These Methods in `Object`?

Every Java object has a **monitor**.

Threads communicate through the monitor associated with an object.

Since every object has a monitor,

these methods belong to

```java
Object
```

instead of

```java
Thread
```

> [!IMPORTANT]
> Threads communicate **through shared objects**, not directly with each other.

---

## Visualization

```text
           Shared Object

        ┌────────────────┐

        │    Monitor     │

        │                │

        │ Waiting Queue  │

        └────────────────┘

           ▲          ▲

           │          │

      Thread A   Thread B
```

The monitor manages

- Lock ownership
- Waiting threads
- Notifications

---

# 9. `wait()`

The

```java
wait()
```

method tells the current thread

> "Pause execution and wait until another thread notifies you."

Unlike

```java
Thread.sleep()
```

`wait()`

**releases the monitor lock** before waiting.

---

## Syntax

```java
wait();
```

or

```java
wait(long timeout);
```

or

```java
wait(long timeout, int nanos);
```

---

## High-Level Working

```
Thread

↓

Acquire Monitor

↓

Condition False

↓

wait()

↓

Release Monitor

↓

WAITING State
```

The thread remains waiting until

- another thread calls `notify()`
- another thread calls `notifyAll()`
- timeout expires (timed waits)

---

## Visualization

```text
Thread A

↓

Monitor Lock

↓

wait()

↓

Lock Released

↓

WAITING
```

---

# 10. `notify()`

The

```java
notify()
```

method wakes **one** thread waiting on the same object's monitor.

Syntax

```java
notify();
```

---

## High-Level Working

```
Producer

↓

Adds Data

↓

notify()

↓

One Waiting Thread Wakes Up
```

---

## Important

Calling

```java
notify();
```

does **not** immediately transfer execution.

The awakened thread must first

- compete for the monitor lock
- reacquire it
- then continue execution

---

## Visualization

```text
Thread A

WAITING

──────────────────

Thread B

notify()

↓

Thread A

RUNNABLE

↓

Waits For Lock

↓

Continues
```

> [!TIP]
> `notify()` moves a waiting thread from the **WAITING** state to a runnable state, but it does **not** guarantee immediate execution.

---

# 11. `notifyAll()`

The

```java
notifyAll()
```

method wakes **all** threads waiting on the same monitor.

Syntax

```java
notifyAll();
```

---

## High-Level Working

```
Thread

↓

notifyAll()

↓

Wake Everyone

↓

Compete For Lock

↓

One Thread Continues

↓

Others Wait For Lock
```

---

## Visualization

```text
Waiting Queue

──────────────

Thread A

Thread B

Thread C

↓

notifyAll()

↓

RUNNABLE

RUNNABLE

RUNNABLE

↓

Only One Gets Lock
```

---

# 12. Waiting Queue (Wait Set)

Every monitor maintains a

```
Waiting Queue
```

also called the

```
Wait Set
```

Threads that call

```java
wait();
```

enter this queue.

---

## Visualization

```text
            Monitor

      ┌───────────────┐

      │ Waiting Queue │

      │───────────────│

      │ Thread A      │

      │ Thread B      │

      │ Thread C      │

      └───────────────┘
```

---

## What Happens Next?

When another thread calls

```java
notify();
```

one thread leaves the queue.

When

```java
notifyAll();
```

is called,

all waiting threads leave the queue and compete for the monitor lock.

---

# 13. State Transitions

These methods directly affect thread states.

---

## `wait()`

```text
RUNNABLE

↓

wait()

↓

WAITING
```

---

## `notify()`

```text
WAITING

↓

notify()

↓

RUNNABLE

↓

Acquire Lock

↓

Continue
```

---

## `notifyAll()`

```text
WAITING

↓

notifyAll()

↓

All Runnable

↓

Compete For Lock

↓

Continue
```

---

# 14. Typical Flow

The Producer–Consumer example follows this sequence.

```text
Consumer

↓

Queue Empty

↓

wait()

↓

WAITING

────────────────────

Producer

↓

Adds Item

↓

notify()

────────────────────

Consumer

↓

Acquire Lock

↓

Remove Item

↓

Continue
```

---

# 15. Rules for Using `wait()`, `notify()`, and `notifyAll()`

These methods have strict rules.

### Rule 1

They must be called on the **same shared object** whose monitor is being used.

---

### Rule 2

The calling thread **must own the monitor**.

In practice,

they are normally used inside a

```java
synchronized
```

block or method.

Example

```java
synchronized (queue) {

    queue.wait();

}
```

---

### Rule 3

Calling

```java
wait()

notify()

notifyAll()
```

without owning the monitor causes

```text
IllegalMonitorStateException
```

---

### Rule 4

Always check the waiting condition in a loop.

Good

```java
while (queue.isEmpty()) {

    queue.wait();

}
```

Avoid

```java
if (queue.isEmpty()) {

    queue.wait();

}
```

We'll explain **spurious wakeups** and why `while` is preferred in the dedicated `wait()` chapter.

---

# 💡 Interview Insight

A very common interview question is:

> **Why are `wait()`, `notify()`, and `notifyAll()` methods of `Object` instead of `Thread`?**

**Answer:**

Because synchronization in Java is based on an object's **monitor**.

Threads communicate through the monitor associated with a shared object, not directly with each other.

---

# 16. Producer–Consumer Walkthrough

Let's understand the complete flow of Inter Thread Communication.

Suppose we have

- One Producer
- One Consumer
- One Shared Queue

Initially,

```text
Queue = Empty
```

---

## Step 1 – Consumer Starts First

The consumer checks the queue.

```text
Queue Empty

↓

Nothing To Consume
```

Instead of continuously checking,

the consumer executes

```java
wait();
```

Current State

```text
Consumer

↓

WAITING
```

The monitor lock is released.

---

## Step 2 – Producer Runs

The producer acquires the monitor.

It creates an item.

```text
Producer

↓

Create Item

↓

Add To Queue
```

Current Queue

```text
Queue

↓

Item Available
```

---

## Step 3 – Producer Calls `notify()`

After adding data,

the producer executes

```java
notify();
```

One waiting consumer is awakened.

```text
Producer

↓

notify()

↓

Consumer Becomes Runnable
```

> [!IMPORTANT]
> The consumer does **not** continue immediately.
>
> It must first reacquire the monitor lock.

---

## Step 4 – Consumer Reacquires the Lock

Once the producer exits the synchronized block,

the monitor becomes available.

The consumer acquires the monitor again.

```text
Consumer

↓

Acquire Lock

↓

Continue Execution
```

---

## Step 5 – Consumer Processes the Item

The consumer removes the item.

```text
Queue

↓

Remove Item

↓

Process Item
```

Execution completes successfully.

---

## Complete Flow

```text
Queue Empty

↓

Consumer Calls wait()

↓

Consumer WAITING

────────────────────────────

Producer Adds Item

↓

Producer Calls notify()

↓

Consumer RUNNABLE

↓

Producer Releases Lock

↓

Consumer Acquires Lock

↓

Consumer Processes Item
```

---

# 17. Best Practices

Writing correct Inter Thread Communication code requires following a few important rules.

---

## ✅ Always Call `wait()` Inside a Loop

Good

```java
while (queue.isEmpty()) {

    queue.wait();

}
```

Bad

```java
if (queue.isEmpty()) {

    queue.wait();

}
```

Using a loop ensures that the condition is checked again after the thread wakes up.

---

## ✅ Hold the Monitor Before Calling ITC Methods

Always execute

```java
wait()

notify()

notifyAll()
```

inside a synchronized block or synchronized method.

Example

```java
synchronized (queue) {

    queue.wait();

}
```

---

## ✅ Protect Shared State

Inter Thread Communication should coordinate access,

not replace synchronization.

Protect shared mutable data appropriately.

---

## ✅ Prefer High-Level Concurrency Utilities

Modern Java provides utilities such as

- `BlockingQueue`
- `CountDownLatch`
- `Semaphore`
- `CompletableFuture`

These often simplify thread coordination compared to manual `wait()` and `notify()`.

---

# 18. Common Mistakes

---

## ❌ Calling `wait()` Outside a Synchronized Block

Wrong

```java
queue.wait();
```

This throws

```text
IllegalMonitorStateException
```

---

## ❌ Assuming `notify()` Releases the Lock

Wrong.

`notify()` only wakes a waiting thread.

The awakened thread must still wait until the monitor becomes available.

---

## ❌ Using `if` Instead of `while`

A waiting thread should always recheck the condition after waking.

This avoids problems caused by

- Spurious wakeups
- Multiple waiting threads
- Condition changes before reacquiring the lock

---

## ❌ Confusing `sleep()` with `wait()`

| `sleep()` | `wait()` |
|-----------|----------|
| Belongs to `Thread` | Belongs to `Object` |
| Does **not** release the monitor | Releases the monitor |
| Used for delaying execution | Used for thread coordination |

---

## ❌ Using `notify()` When Multiple Threads May Be Waiting

Suppose

```text
Thread A

WAITING

Thread B

WAITING

Thread C

WAITING
```

Calling

```java
notify();
```

awakens only one thread.

Sometimes

```java
notifyAll();
```

is the safer choice.

---

# 19. Interview Questions

### 1. What is Inter Thread Communication?

It is a mechanism that allows threads to coordinate by waiting for conditions and notifying each other when those conditions become true.

---

### 2. Why is Inter Thread Communication needed?

To avoid inefficient busy waiting and enable efficient cooperation between threads.

---

### 3. Why are `wait()`, `notify()`, and `notifyAll()` methods of `Object`?

Because they operate on an object's monitor.

Threads communicate through shared objects, not directly with each other.

---

### 4. Can `wait()` be called outside a synchronized block?

No.

Doing so results in an `IllegalMonitorStateException`.

---

### 5. Does `notify()` immediately transfer control to another thread?

No.

It only wakes a waiting thread.

The awakened thread must still reacquire the monitor lock before continuing.

---

### 6. What is the Wait Set?

The Wait Set is the collection of threads waiting on an object's monitor after calling `wait()`.

---

### 7. Why is `while` preferred over `if` with `wait()`?

Because a thread should always verify that the waiting condition is still true after waking up.

---

# 20. Quick Revision

```text
Inter Thread Communication

↓

Thread Coordination

↓

Condition False

↓

wait()

↓

WAITING

↓

Producer Updates State

↓

notify()

↓

RUNNABLE

↓

Acquire Lock

↓

Continue
```

---

## Core Methods

```text
wait()

↓

Release Monitor

↓

WAITING

────────────────────────

notify()

↓

Wake One Thread

────────────────────────

notifyAll()

↓

Wake All Threads
```

---

## Relationship

```text
Synchronization

↓

Protects Shared Resource

────────────────────────

Inter Thread Communication

↓

Coordinates Thread Execution
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What is Inter Thread Communication?
- [x] Why it is needed
- [x] Busy Waiting
- [x] Producer–Consumer Problem
- [x] `wait()`
- [x] `notify()`
- [x] `notifyAll()`
- [x] Wait Set
- [x] State transitions
- [x] Rules for using ITC methods
- [x] Producer–Consumer flow
- [x] Best practices

---

# 📌 Key Takeaways

- Inter Thread Communication enables threads to **cooperate**, not compete.
- Busy waiting wastes CPU time and should be avoided.
- `wait()` releases the monitor and places the thread in the **WAITING** state.
- `notify()` wakes one waiting thread, while `notifyAll()` wakes all waiting threads.
- A notified thread must reacquire the monitor before continuing.
- `wait()`, `notify()`, and `notifyAll()` are monitor-based operations and therefore belong to the `Object` class.
- Always use these methods while holding the appropriate monitor and recheck conditions in a `while` loop.

> [!TIP]
> **Interview Rule**
>
> Remember the sequence:
>
> **Condition False → `wait()` → WAITING → `notify()`/`notifyAll()` → RUNNABLE → Reacquire Lock → Continue**
>
> Understanding this flow is more valuable than memorizing method definitions.

---

# 📖 Next Topic

➡️ **10. `wait()`**

In the next chapter, we'll study `wait()` in depth:

- What exactly happens when `wait()` is called?
- Why does it release the monitor?
- Overloaded versions of `wait()`
- Timed waiting
- Spurious Wakeups
- Why `while` is preferred over `if`
- JVM internals
- Real-world examples
- Interview questions

> ⭐ **`wait()` is one of the most frequently misunderstood methods in Java Multithreading.**
>
> We'll explore it in detail with diagrams, timelines, and practical examples.
