# ☠️ 08. Deadlock

> [!NOTE]
> A **Deadlock** occurs when two or more threads are permanently waiting for each other to release resources.
>
> Since none of the threads can proceed, the program becomes stuck indefinitely.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is Deadlock?](#2-what-is-deadlock)
- [3. Why Does Deadlock Occur?](#3-why-does-deadlock-occur)
- [4. Real-Life Analogy](#4-real-life-analogy)
- [5. Deadlock Visualization](#5-deadlock-visualization)
- [6. First Java Example](#6-first-java-example)
- [7. Step-by-Step Execution](#7-step-by-step-execution)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Thread
- Synchronization
- `synchronized` Keyword
- Object Locks
- Critical Section
- Thread Safety

---

# 2. What is Deadlock?

A **Deadlock** is a situation where two or more threads are waiting forever for resources held by each other.

As a result,

- No thread can continue.
- No thread releases its lock.
- The application stops making progress.

---

## Definition

> A deadlock is a state in which a set of threads are blocked forever because each thread is waiting for a resource held by another thread in the same set.

---

## Simple Visualization

```text
Thread A

Waiting For Lock B

────────────────────────

Thread B

Waiting For Lock A
```

Neither thread can continue.

Both wait forever.

---

## Why Is It Called a Deadlock?

Think of it as a traffic intersection.

Two vehicles block each other's path.

```
        Car A

          ↓

────────┼────────

          ↑

        Car B
```

Neither vehicle can move first.

The road remains blocked.

Similarly,

threads block each other.

---

# 3. Why Does Deadlock Occur?

Deadlocks occur when threads acquire multiple resources in different orders.

Suppose we have two locks.

```text
Lock A

Lock B
```

Two threads execute.

### Thread A

```text
Acquire Lock A

↓

Acquire Lock B
```

### Thread B

```text
Acquire Lock B

↓

Acquire Lock A
```

Now imagine the following sequence.

```
Thread A

↓

Lock A Acquired

──────────────────────

Thread B

↓

Lock B Acquired

──────────────────────

Thread A

↓

Waiting For Lock B

──────────────────────

Thread B

↓

Waiting For Lock A
```

Both threads wait forever.

---

## Key Observation

The problem is **not** that multiple locks exist.

The problem is that the threads acquire them in **different orders**.

> [!TIP]
> Most deadlocks in real applications happen because of inconsistent lock ordering.

---

# 4. Real-Life Analogy

Imagine two people trying to cross a narrow bridge.

```
Person A

────────►

════════ Bridge ════════

◄────────

Person B
```

Neither person is willing to move backward.

Result

```
Nobody Crosses
```

The bridge remains blocked.

This is exactly what happens during a deadlock.

---

## Another Analogy

Imagine two friends.

```
Friend A

"I'll give you my book
after you return my laptop."

──────────────────────────

Friend B

"I'll return your laptop
after you give me my book."
```

Both keep waiting.

Neither acts first.

Nothing happens.

---

# 5. Deadlock Visualization

```
          Lock A

             ▲

             │

      Waiting

             │

        Thread B

             ▲

             │

 Owns Lock B │

             ▼

        Thread A

             │

             ▼

        Waiting

             │

             ▼

          Lock B
```

Each thread owns one lock.

Each thread waits for the other.

The cycle never ends.

---

# 6. First Java Example

```java
class Resource {

}

public class DeadlockDemo {

    private static final Object lockA = new Object();

    private static final Object lockB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {

            synchronized (lockA) {

                System.out.println("Thread 1 acquired Lock A");

                synchronized (lockB) {

                    System.out.println("Thread 1 acquired Lock B");

                }

            }

        });

        Thread thread2 = new Thread(() -> {

            synchronized (lockB) {

                System.out.println("Thread 2 acquired Lock B");

                synchronized (lockA) {

                    System.out.println("Thread 2 acquired Lock A");

                }

            }

        });

        thread1.start();

        thread2.start();

    }

}
```

---

## Possible Output

```text
Thread 1 acquired Lock A

Thread 2 acquired Lock B
```

Program hangs.

Nothing else prints.

---

## Why?

Thread 1

```text
Owns Lock A

↓

Needs Lock B
```

Thread 2

```text
Owns Lock B

↓

Needs Lock A
```

Neither thread can proceed.

---

# 7. Step-by-Step Execution

Let's examine the execution carefully.

### Step 1

```
Thread 1

↓

Acquire Lock A
```

Status

```
Lock A → Thread 1
```

---

### Step 2

```
Thread 2

↓

Acquire Lock B
```

Status

```
Lock B → Thread 2
```

---

### Step 3

Thread 1 attempts

```
Acquire Lock B
```

But

```
Lock B

↓

Already Owned

↓

Thread 2
```

Thread 1 waits.

---

### Step 4

Thread 2 attempts

```
Acquire Lock A
```

But

```
Lock A

↓

Already Owned

↓

Thread 1
```

Thread 2 waits.

---

### Final State

```
Thread 1

Waiting For Lock B

──────────────────────

Thread 2

Waiting For Lock A
```

No thread can continue.

No lock is released.

The application is permanently blocked.

---

# 💡 Interview Insight

Deadlock is **not** caused by the `synchronized` keyword itself.

It is caused by **how locks are acquired**.

Correctly designed synchronization does **not** produce deadlocks.

---

# 8. Coffman Conditions ⭐⭐⭐⭐⭐

In 1971,

computer scientists **Edward G. Coffman Jr.** and his colleagues identified four necessary conditions for a deadlock to occur.

These are known as the **Coffman Conditions**.

> [!IMPORTANT]
> A deadlock can occur **only if all four conditions are true simultaneously**.
>
> If even **one condition is eliminated**, the deadlock cannot occur.

---

## The Four Conditions

1. Mutual Exclusion
2. Hold and Wait
3. No Preemption
4. Circular Wait

---

## Visualization

```text
          Deadlock

              │

    ┌─────────┼─────────┐

    ▼         ▼         ▼

Mutual   Hold & Wait   No Preemption

              │

              ▼

        Circular Wait
```

All four are required.

---

# 9. Mutual Exclusion

A resource can be used by **only one thread at a time**.

If Thread A owns the resource,

Thread B must wait.

---

## Example

```
Printer

↓

Thread A

↓

Printing

-------------------------

Thread B

↓

WAIT
```

Only one thread can use the printer.

---

## Java Example

```java
synchronized (lock) {

    // Critical Section

}
```

The monitor lock can be owned by only one thread.

---

## Why Is It Necessary?

Suppose multiple threads could use the same lock simultaneously.

```
Thread A

↓

Lock

↑

Thread B
```

Then nobody would need to wait.

No deadlock could occur.

Therefore,

mutual exclusion is required.

---

# 10. Hold and Wait

A thread

- already owns one resource
- while waiting for another resource

---

## Example

```
Thread A

Owns Lock A

↓

Waiting For Lock B
```

The thread is

```
Holding

and

Waiting
```

at the same time.

---

## Visualization

```
Thread A

↓

Lock A

↓

WAIT

↓

Lock B
```

---

## Why Is It Necessary?

Suppose the thread released

```
Lock A
```

before waiting.

Another thread could acquire it.

The deadlock would disappear.

Therefore,

holding one resource while waiting for another is required.

---

# 11. No Preemption

Once a thread acquires a resource,

the operating system or JVM cannot forcibly take it away.

Only the thread that owns the resource can release it.

---

## Example

```
Thread A

↓

Owns Lock A

↓

JVM

↓

Cannot Remove It
```

The lock remains with Thread A until it exits the synchronized block.

---

## Java Example

```java
synchronized (lock) {

}
```

The JVM does not forcibly remove the monitor lock.

The thread releases it automatically when leaving the synchronized region.

---

## Why Is It Necessary?

Imagine the JVM could forcefully reclaim locks.

```
Thread A

↓

Lock A

↓

JVM Takes It Back

↓

Thread B Continues
```

Deadlock would never persist.

Therefore,

no preemption is another necessary condition.

---

# 12. Circular Wait ⭐⭐⭐⭐⭐

This is the most recognizable deadlock condition.

A circular chain of waiting threads exists.

Example

```
Thread A

Waiting For Lock B

────────────────────

Thread B

Waiting For Lock C

────────────────────

Thread C

Waiting For Lock A
```

This creates a cycle.

No thread can proceed.

---

## Visualization

```text
          Lock A

             ▲

             │

         Thread C

             ▲

             │

Lock C ◄── Thread B

             ▲

             │

         Lock B

             ▲

             │

         Thread A
```

The waiting relationship forms a circle.

---

## Simplified Example

```
Thread A

↓

Needs Lock B

────────────────────

Thread B

↓

Needs Lock A
```

Even with only two threads,

a circular wait exists.

---

## Why Is It Necessary?

Suppose the waiting chain were not circular.

```
Thread A

↓

Waiting

↓

Thread B

↓

Running
```

Eventually,

Thread B finishes,

releases the lock,

and Thread A continues.

No deadlock occurs.

Only a **cycle** causes permanent waiting.

---

# 13. Why Are All Four Conditions Necessary?

Let's summarize.

| Condition | Why It Is Required |
|-----------|--------------------|
| Mutual Exclusion | Resources cannot be shared simultaneously |
| Hold and Wait | Threads keep one resource while requesting another |
| No Preemption | Resources cannot be forcibly taken away |
| Circular Wait | Threads form a cycle of dependencies |

---

## Important Rule

```
Remove

Any

One

Condition

↓

No Deadlock
```

---

## Memory Trick

Remember

```
M

H

N

C
```

```
Mutual Exclusion

Hold and Wait

No Preemption

Circular Wait
```

or simply

```
MHNC
```

---

# 14. Resource Allocation Graph (High Level)

A Resource Allocation Graph (RAG) is a graphical representation of

- Threads (Processes)
- Resources
- Ownership
- Waiting relationships

---

## Example

```text
(Thread A) ─────► [Lock B]

     ▲

     │

[Lock A] ◄────── (Thread B)
```

Where

- Thread → Resource means **requesting**
- Resource → Thread means **allocated**

A cycle in the graph indicates a potential deadlock.

> [!NOTE]
> In operating systems, Resource Allocation Graphs are commonly used for deadlock analysis and detection.

---

# 💡 Interview Insight

Interviewers often ask:

> **"Do all four Coffman Conditions have to be present?"**

The correct answer is:

**Yes.**

A deadlock can occur **only when all four conditions are satisfied simultaneously**.

If any one condition is eliminated,

deadlock cannot occur.

---

# 15. Deadlock Detection

Sometimes a deadlock cannot be prevented completely.

Instead,

the system detects it and takes corrective action.

This approach is called

```text
Deadlock Detection
```

---

## How Does Detection Work?

The operating system or application periodically checks

- Which thread owns which resource
- Which thread is waiting
- Whether a circular dependency exists

If a cycle is detected,

a deadlock has occurred.

---

## Visualization

```text
Thread A

↓

Waiting For

↓

Lock B

▲          │

│          ▼

Lock A ◄── Thread B
```

A cycle exists.

Deadlock detected.

---

## Java Detection

The JVM provides APIs through

```java
ThreadMXBean
```

to detect deadlocked threads.

Example

```java
ThreadMXBean bean =
    ManagementFactory.getThreadMXBean();

long[] ids =
    bean.findDeadlockedThreads();
```

If

```java
ids != null
```

deadlocked threads exist.

> [!NOTE]
> This API is commonly used by monitoring tools and profilers rather than in application logic.

---

# 16. Deadlock Prevention

Instead of detecting deadlocks,

we can design the program so they never occur.

This is called

```text
Deadlock Prevention
```

The idea is simple:

> Break at least one Coffman Condition.

---

## Prevention Strategies

### Eliminate Hold and Wait

Acquire all required resources at once.

Instead of

```text
Lock A

↓

Lock B
```

acquire

```text
Lock A + Lock B
```

together.

---

### Allow Preemption

In some systems,

resources can be taken back.

Example

```text
Database Transaction

↓

Rollback

↓

Release Resources
```

Although Java monitor locks are not preempted,

other systems may support this.

---

### Remove Circular Wait

Always acquire locks in the same order.

This is the most common technique in Java.

We'll study it next.

---

# 17. Deadlock Avoidance

Deadlock avoidance is different from prevention.

Instead of permanently removing a condition,

the system checks whether granting a resource request would create an unsafe state.

If yes,

the request is delayed.

---

## Simple Idea

```text
Request Resource

↓

Safe?

↓

Yes → Grant

No → Wait
```

---

## Operating Systems

A famous avoidance algorithm is

```text
Banker's Algorithm
```

It is widely studied in Operating Systems.

Java applications rarely implement it directly,

but understanding the idea is useful.

---

# 18. Lock Ordering ⭐⭐⭐⭐⭐

The simplest and most effective way to avoid deadlocks in Java is

```text
Consistent Lock Ordering
```

---

## Bad Example

Thread A

```text
Lock A

↓

Lock B
```

Thread B

```text
Lock B

↓

Lock A
```

Different order.

Possible deadlock.

---

## Good Example

Thread A

```text
Lock A

↓

Lock B
```

Thread B

```text
Lock A

↓

Lock B
```

Same order.

Deadlock cannot occur.

---

## Java Example

```java
synchronized (lockA) {

    synchronized (lockB) {

        // Critical Section

    }

}
```

Every thread follows the same order.

---

## Visualization

```text
Thread A

↓

Lock A

↓

Lock B

────────────────────

Thread B

↓

Wait For Lock A

↓

Lock B
```

No circular wait.

No deadlock.

> [!TIP]
> Consistent lock ordering is one of the most widely used deadlock prevention techniques in enterprise Java applications.

---

# 19. Timeout Locks (`tryLock()`)

Monitor locks acquired with

```java
synchronized
```

cannot time out.

A thread waits indefinitely.

---

## Solution

`ReentrantLock`

provides

```java
tryLock()
```

Example

```java
if (lock.tryLock()) {

    try {

        // Critical Section

    } finally {

        lock.unlock();

    }

}
```

---

## Timed Lock

```java
lock.tryLock(5, TimeUnit.SECONDS);
```

The thread waits

```
5 Seconds
```

If the lock is still unavailable,

it gives up instead of waiting forever.

---

## Benefit

Timeouts reduce the risk of permanent deadlocks.

> [!NOTE]
> We'll study `ReentrantLock` in detail later in this handbook.

---

# 20. Real-World Examples

---

## Banking

Two bank accounts.

```
Account A

↓

Transfer

↓

Account B
```

Another thread performs

```
Account B

↓

Transfer

↓

Account A
```

Different lock order.

Deadlock possible.

---

## Database Transactions

Transaction A

```
Locks Row 1

↓

Needs Row 2
```

Transaction B

```
Locks Row 2

↓

Needs Row 1
```

The database detects the deadlock.

One transaction is rolled back.

---

## Microservices

Service A

```
Waiting For

Service B
```

Service B

```
Waiting For

Service A
```

Both services remain blocked.

This is a distributed form of deadlock.

---

# 21. Best Practices

✅ Acquire locks in a consistent order.

✅ Keep critical sections short.

✅ Avoid nested locking when possible.

✅ Use `tryLock()` when timeout behavior is desirable.

✅ Minimize the number of locks.

✅ Prefer immutable objects where practical.

✅ Document lock ordering in complex systems.

---

# 22. Common Misconceptions

---

## ❌ Every Waiting Thread Is Deadlocked

False.

Waiting is normal.

Deadlock occurs only when there is a circular dependency.

---

## ❌ Deadlocks Only Happen with Two Threads

False.

Any number of threads may participate.

---

## ❌ `synchronized` Causes Deadlocks

False.

Improper lock usage causes deadlocks.

Correct synchronization does not.

---

## ❌ Deadlocks Always Crash the JVM

False.

The JVM usually continues running,

but the affected threads remain blocked indefinitely.

---

# 23. Interview Questions

### 1. What is a Deadlock?

A deadlock occurs when two or more threads wait forever for resources held by each other.

---

### 2. What are the Coffman Conditions?

- Mutual Exclusion
- Hold and Wait
- No Preemption
- Circular Wait

---

### 3. Can a Deadlock occur if one Coffman Condition is missing?

No.

All four conditions must exist simultaneously.

---

### 4. What is the easiest way to prevent deadlocks in Java?

Acquire locks in a consistent order.

---

### 5. What is the difference between Prevention and Avoidance?

Prevention removes one or more Coffman Conditions.

Avoidance checks whether granting a resource request would lead to an unsafe state.

---

### 6. How can Java detect deadlocks?

Using `ThreadMXBean`.

---

### 7. Why is `tryLock()` useful?

It allows timeout-based lock acquisition instead of waiting forever.

---

# 24. Quick Revision

```text
Deadlock

↓

Threads Waiting Forever

↓

Each Holds One Resource

↓

Each Waits For Another

↓

No Progress
```

---

## Prevention

```text
Deadlock

↓

Break One Coffman Condition

↓

No Deadlock
```

---

## Best Prevention

```text
Thread A

↓

Lock A

↓

Lock B

────────────────────

Thread B

↓

Lock A

↓

Lock B
```

Same order.

No circular wait.

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What is Deadlock?
- [x] Coffman Conditions
- [x] Circular Wait
- [x] Resource Allocation Graph
- [x] Deadlock Detection
- [x] Deadlock Prevention
- [x] Deadlock Avoidance
- [x] Lock Ordering
- [x] `tryLock()`
- [x] Real-world examples

---

# 📌 Key Takeaways

- Deadlocks occur because threads wait on each other's resources.
- All four Coffman Conditions must be present for a deadlock to occur.
- Consistent lock ordering is one of the simplest and most effective prevention techniques.
- `ThreadMXBean` can detect deadlocked threads.
- `ReentrantLock.tryLock()` helps avoid waiting indefinitely.
- Good concurrent design minimizes nested locking and shared mutable state.

> [!TIP]
> **Interview Rule**
>
> If you're asked:
>
> **"How do you prevent deadlocks in Java?"**
>
> Mention these first:
>
> 1. Consistent lock ordering
> 2. Minimize nested locks
> 3. Use `tryLock()` with timeout when appropriate
> 4. Reduce shared mutable state

---

# 📖 Next Topic

➡️ **09_Inter_Thread_Communication.md**

In the next chapter, you'll learn how threads **cooperate** instead of competing:

- Why communication between threads is needed
- Producer–Consumer problem
- `wait()`
- `notify()`
- `notifyAll()`
- Monitor wait set
- Thread coordination
