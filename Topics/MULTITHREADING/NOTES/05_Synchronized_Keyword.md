# 🔐 05. synchronized Keyword

> [!NOTE]
> The `synchronized` keyword is Java's built-in mechanism for implementing synchronization.
>
> It ensures that **only one thread at a time** can execute a protected section of code for a given lock, helping maintain data consistency in multithreaded applications.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. Why Do We Need the synchronized Keyword?](#2-why-do-we-need-the-synchronized-keyword)
- [3. What is synchronized?](#3-what-is-synchronized)
- [4. Synchronization vs synchronized](#4-synchronization-vs-synchronized)
- [5. How synchronized Works (High Level)](#5-how-synchronized-works-high-level)
- [6. First Example](#6-first-example)
- [7. Why Doesn't synchronized Stop All Threads?](#7-why-doesnt-synchronized-stop-all-threads)
- [8. High-Level Locking Flow](#8-high-level-locking-flow)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Thread
- Runnable
- Thread Lifecycle
- Synchronization
- Critical Section
- Shared Resources
- Mutual Exclusion

If these concepts are new to you, read the previous chapters first.

---

# 2. Why Do We Need the synchronized Keyword?

In the previous chapter, we learned **why synchronization is necessary**.

The next question is:

> **How does Java actually implement synchronization?**

Java provides the answer through the

```java
synchronized
```

keyword.

It allows the JVM to coordinate multiple threads by protecting critical sections.

---

## Consider This Example

Suppose two threads try to withdraw money from the same bank account.

Initial balance

```text
₹1000
```

Both threads attempt

```text
Withdraw ₹700
```

Without synchronization

```text
Thread A

Reads Balance = 1000

-------------------------

Thread B

Reads Balance = 1000
```

Both think enough money exists.

Final balance becomes incorrect.

---

## Desired Behaviour

We want

```text
Thread A

↓

Withdraw

↓

Update Balance

↓

Finish

↓

Thread B Starts
```

Only one thread should modify the balance at a time.

This is exactly what `synchronized` provides.

---

# 3. What is synchronized?

The `synchronized` keyword tells the JVM:

> **Allow only one thread at a time to execute this protected code for a particular lock.**

Example

```java
public synchronized void withdraw(int amount) {

    // Critical Section

}
```

When one thread enters this method,

another thread attempting to enter the same synchronized region (protected by the same lock) must wait.

---

## Definition

> `synchronized` is a Java keyword that protects a critical section by allowing only one thread at a time to execute it for a given monitor lock.

---

## Important Point

Synchronization is the **concept**.

`synchronized` is one **implementation** of that concept.

Think of it like

```text
Transportation

↓

Concept

Car

↓

Implementation
```

Similarly,

```text
Synchronization

↓

Concept

synchronized

↓

Java Implementation
```

---

# 4. Synchronization vs synchronized

Many beginners think these are the same.

They are not.

| Synchronization | synchronized |
|-----------------|--------------|
| Concept | Java Keyword |
| Coordinates threads | Implements synchronization |
| General idea | Language feature |
| Can be achieved in different ways | One built-in mechanism |

Examples of synchronization mechanisms include:

- `synchronized`
- `ReentrantLock`
- `Semaphore`
- `ReadWriteLock`
- Atomic classes

We'll study these later.

> [!TIP]
> **Interview Insight**
>
> Synchronization is the goal.
>
> `synchronized` is one tool used to achieve that goal.

---

# 5. How synchronized Works (High Level)

At a high level, every synchronized region is associated with a **lock**.

When a thread wants to execute synchronized code:

1. It requests the lock.
2. If the lock is free, it acquires it.
3. The thread executes the protected code.
4. The lock is released.
5. Another waiting thread may acquire the lock.

---

## Visualization

```text
Thread A
     │
Requests Lock
     │
     ▼
 Lock Available?
     │
 ┌───┴────┐
 │        │
Yes       No
 │        │
 ▼        ▼
Enter     Wait
Code
 │
 ▼
Finish
 │
 ▼
Release Lock
```

---

## Real-Life Analogy

Imagine a meeting room.

There is only **one key**.

```text
Meeting Room

        🔑
         │
 ┌───────┴────────┐

 Alice         Bob

```

Alice takes the key.

Bob must wait.

After Alice returns the key,

Bob can enter.

The **key** behaves like a lock.

---

# 6. First Example

Let's use the same example throughout this chapter.

```java
class BankAccount {

    private int balance = 1000;

    public synchronized void withdraw(int amount) {

        if (balance >= amount) {

            System.out.println(
                Thread.currentThread().getName()
                + " is withdrawing ₹" + amount
            );

            balance -= amount;

            System.out.println(
                "Remaining Balance : ₹" + balance
            );

        } else {

            System.out.println(
                "Insufficient Balance"
            );

        }

    }

}
```

Now suppose

```java
BankAccount account = new BankAccount();

Thread t1 = new Thread(() -> account.withdraw(700), "Alice");

Thread t2 = new Thread(() -> account.withdraw(700), "Bob");

t1.start();

t2.start();
```

Possible Output

```text
Alice is withdrawing ₹700

Remaining Balance : ₹300

Insufficient Balance
```

Notice

Bob waited until Alice completed the synchronized method.

This prevented both threads from updating the balance simultaneously.

> [!IMPORTANT]
> The exact thread that executes first is determined by the scheduler.
>
> `synchronized` guarantees **mutual exclusion**, not execution order.

---

# 7. Why Doesn't synchronized Stop All Threads?

A common misconception is:

> "If one thread enters synchronized code, does the whole application stop?"

**No.**

Only threads that need the **same lock** are affected.

Example

```text
Thread A

Using BankAccount Lock

↓

Executing

----------------------------

Thread B

Needs Same Lock

↓

WAIT

----------------------------

Thread C

Working on Different Object

↓

Runs Normally
```

Thread C continues because it doesn't require the same lock.

---

## Important Observation

`synchronized` protects access to a **particular object or class**, not the entire JVM.

We'll study object locks and class locks in the next section.

---

# 8. High-Level Locking Flow

The complete flow is

```text
Thread

    │

Requests Entry

    │

Needs Lock

    │

┌───────────────┐

│ Lock Free ?   │

└──────┬────────┘

       │

  Yes  │  No

       │

       ▼

 Acquire Lock

       │

 Execute Protected Code

       │

 Release Lock

       │

 Next Waiting Thread
```

---

## 💡 Interview Insight

Many developers say:

> "`synchronized` locks the method."

This is **not technically correct**.

The method itself is **never locked**.

The **associated monitor lock** is acquired before entering the protected code.

We'll explore this in depth in the next section when we study **Object Locks**, **Intrinsic Locks**, and **Monitors**.

---

# 9. synchronized Method

The simplest way to synchronize code is by synchronizing an entire method.

Syntax

```java
public synchronized void methodName() {

    // Critical Section

}
```

When a thread enters a synchronized method,

it must first acquire the appropriate lock.

If another thread already owns that lock,

the current thread waits until the lock is released.

---

## Example

Continuing with our `BankAccount` example:

```java
class BankAccount {

    private int balance = 1000;

    public synchronized void deposit(int amount) {

        System.out.println(
            Thread.currentThread().getName()
            + " deposited ₹" + amount
        );

        balance += amount;

        System.out.println(
            "Balance : ₹" + balance
        );

    }

}
```

Suppose

```java
BankAccount account = new BankAccount();

Thread t1 =
    new Thread(() -> account.deposit(500), "Alice");

Thread t2 =
    new Thread(() -> account.deposit(300), "Bob");

t1.start();

t2.start();
```

Possible Output

```text
Alice deposited ₹500

Balance : ₹1500

Bob deposited ₹300

Balance : ₹1800
```

Notice

Only one thread executed the method at a time.

---

## Visualization

```text
                 BankAccount

              deposit()

                   │

         ┌─────────┴─────────┐

         ▼                   ▼

     Thread A           Thread B

         │                   │

     Acquires Lock       Waiting

         │

         ▼

    Executes Method

         │

         ▼

    Releases Lock

         │

         ▼

     Thread B Enters
```

---

## Important

A synchronized method **does not** lock the entire object forever.

It locks the object **only while the method is executing**.

After the method returns,

the lock is automatically released.

---

# 10. synchronized Block

Sometimes synchronizing an entire method is unnecessary.

Suppose only a few lines modify shared data.

Example

```java
public void withdraw(int amount) {

    // Validation

    // Logging

    synchronized (this) {

        balance -= amount;

    }

    // Notification

}
```

Only

```java
balance -= amount;
```

needs synchronization.

Everything else can execute concurrently.

---

## Why Block Synchronization?

Imagine a method containing

```text
100 Lines
```

Only

```text
5 Lines
```

modify shared data.

Synchronizing all

```text
100 Lines
```

reduces concurrency unnecessarily.

Instead

```text
95 Lines

↓

Normal Execution

5 Lines

↓

Protected
```

This improves performance.

> [!TIP]
> Synchronize the **smallest possible critical section**.

---

## Syntax

```java
synchronized (lockObject) {

    // Critical Section

}
```

---

## Example

```java
class BankAccount {

    private int balance = 1000;

    public void withdraw(int amount) {

        System.out.println("Checking Balance...");

        synchronized (this) {

            if (balance >= amount) {

                balance -= amount;

            }

        }

        System.out.println("Transaction Complete");

    }

}
```

Here,

only the balance update is synchronized.

---

# 11. Method vs Block Synchronization

| synchronized Method | synchronized Block |
|----------------------|--------------------|
| Entire method protected | Only selected code protected |
| Easier to write | More flexible |
| Simpler | Better performance in many cases |
| May synchronize unnecessary code | Protects only the critical section |

---

## Which Should You Use?

Use a synchronized **method** when

- the entire method accesses shared mutable state
- simplicity is more important

Use a synchronized **block** when

- only part of the method needs protection
- you want to reduce lock contention

> [!IMPORTANT]
> In production code, synchronized blocks are often preferred because they keep the locked region as small as possible.

---

# 12. Object Lock

Now we reach an important question.

When we write

```java
public synchronized void withdraw() {

}
```

**What exactly gets locked?**

Answer:

> The **object** on which the method is invoked.

Example

```java
BankAccount account = new BankAccount();

account.withdraw();
```

The lock belongs to

```text
account
```

not

```text
withdraw()
```

---

## Visualization

```text
BankAccount Object

┌─────────────────────┐

│     balance         │

│                     │

│      🔒 Lock        │

└─────────────────────┘
```

Every Java object has an associated monitor lock.

We'll understand monitors in the next section.

---

## Example

```java
BankAccount account1 =
    new BankAccount();

BankAccount account2 =
    new BankAccount();
```

These objects have

```text
Two Different Locks
```

Therefore

```text
Thread A

↓

account1.withdraw()

and

Thread B

↓

account2.withdraw()
```

can execute simultaneously.

---

## Visualization

```text
account1

🔒

↓

Thread A



account2

🔒

↓

Thread B
```

Different objects.

Different locks.

No waiting.

---

# 13. Intrinsic Lock

The lock associated with every Java object is called its

```text
Intrinsic Lock
```

It is also known as

- Built-in Lock
- Monitor Lock

These terms are often used interchangeably.

---

## Important

Every object automatically owns one intrinsic lock.

Example

```java
Object obj = new Object();
```

The object

```text
obj
```

already has its own lock.

You never create it manually.

The JVM manages it automatically.

---

## Visualization

```text
Object

┌───────────────┐

│   Data        │

│               │

│   Monitor     │

└───────────────┘
```

Whenever a synchronized block uses

```java
synchronized(obj)
```

the thread attempts to acquire

```text
obj's intrinsic lock
```

---

# 14. Lock Ownership

Only one thread can own a particular lock at any given time.

Suppose

```text
Thread A
```

already owns

```text
Account Lock
```

Then

```text
Thread B
```

must wait.

Visualization

```text
Thread A

      │

Owns Lock

      │

      ▼

Critical Section

───────────────

Thread B

WAITING
```

Once Thread A exits,

the lock is released automatically.

Then one of the waiting threads acquires it.

---

# 15. Automatic Lock Release

One of the biggest advantages of `synchronized` is that Java automatically releases the lock.

Whether the method finishes normally

or

an exception occurs,

the JVM releases the lock.

Example

```java
public synchronized void update() {

    throw new RuntimeException();

}
```

Even though an exception occurs,

the lock is released.

> [!TIP]
> This automatic release makes `synchronized` safer than manually managing locks in many situations.

---

# 16. Monitor

So far we have learned that every Java object has an **Intrinsic Lock**.

But how does the JVM actually manage that lock?

The answer is:

> **Monitor**

A **Monitor** is an internal synchronization mechanism provided by the JVM.

It is responsible for

- controlling thread access
- maintaining mutual exclusion
- making threads wait
- waking waiting threads

> [!IMPORTANT]
> A Monitor is **not** a Java class.
>
> It is a JVM-level synchronization mechanism automatically associated with every Java object.

---

## Think of a Monitor as a Security Guard

Imagine a conference room.

Only one person may enter at a time.

```
            Conference Room

        🚪 Security Guard

               │

     ┌─────────┴─────────┐

     ▼                   ▼

 Thread A           Thread B
```

The security guard

- allows one person inside
- asks everyone else to wait
- lets the next person enter when the room becomes free

The **Monitor** behaves exactly like that.

---

# 17. Relationship Between Object, Lock and Monitor

Many beginners confuse these terms.

Let's separate them.

```
Java Object

        │

        ▼

Intrinsic Lock

        │

Managed By

        ▼

Monitor (JVM)
```

Or another way

```
Object
   │
   ▼
Monitor
   │
   ▼
Lock
```

Whenever we write

```java
synchronized(account) {

}
```

the JVM uses

```
account's Monitor
```

to control access.

---

## Visualization

```
┌──────────────────────────┐

       BankAccount

----------------------------

 balance = 1000

----------------------------

 Monitor

----------------------------

 Waiting Threads

└──────────────────────────┘
```

The monitor stores information such as

- lock ownership
- waiting threads
- synchronization state

---

# 18. How a Monitor Works

Suppose three threads attempt to enter the same synchronized block.

```
Thread A

Thread B

Thread C
```

Execution

```
Thread A

↓

Requests Monitor

↓

Monitor Available

↓

Enter Critical Section

--------------------------

Thread B

↓

Requests Monitor

↓

WAIT

--------------------------

Thread C

↓

Requests Monitor

↓

WAIT
```

When Thread A finishes

```
Release Monitor

↓

Monitor Chooses Next Thread

↓

Thread B Executes
```

---

## Complete Flow

```
Thread

    │

Requests Monitor

    │

Monitor Free?

┌─────────────┐

│ YES         │

▼             │

Acquire Lock  │

│             │

▼             │

Execute       │

│             │

▼             │

Release Lock◄─┘

NO

↓

WAIT
```

---

# 19. monitorenter

This is where Java becomes interesting.

When the compiler encounters

```java
synchronized(this) {

    // code

}
```

the generated bytecode contains

```
monitorenter
```

instruction.

This instruction tells the JVM

```
Acquire Monitor Lock
```

---

## Visualization

```
Java Code

↓

synchronized

↓

Bytecode

↓

monitorenter

↓

Acquire Monitor
```

The thread cannot execute the synchronized block until

```
monitorenter
```

succeeds.

---

# 20. monitorexit

At the end of every synchronized block,

the JVM automatically inserts

```
monitorexit
```

instruction.

Its job is

```
Release Monitor
```

---

Visualization

```
Execute Critical Section

↓

monitorexit

↓

Release Lock

↓

Next Waiting Thread
```

---

## Why This Matters

Suppose an exception occurs.

Example

```java
synchronized(this) {

    throw new RuntimeException();

}
```

The JVM still executes

```
monitorexit
```

before leaving the synchronized block.

Therefore

the lock is **always released**.

> [!TIP]
> This is one reason `synchronized` is considered safer than manual lock management.

---

# 21. Reentrant Synchronization

Java synchronization is

```
Reentrant
```

This means

> A thread that already owns a lock may acquire the **same lock again**.

---

## Example

```java
class Demo {

    public synchronized void first() {

        second();

    }

    public synchronized void second() {

        System.out.println("Second");

    }

}
```

Question

```
Will Deadlock Occur?
```

Answer

```
No
```

The same thread already owns the lock.

Therefore it is allowed to enter again.

---

## Visualization

```
Thread A

↓

Acquire Lock

↓

first()

↓

Calls

↓

second()

↓

Same Lock

↓

Allowed
```

---

## Why Reentrancy?

Without reentrancy

```
first()

↓

Calls

↓

second()

↓

Wait For Same Lock

↓

Deadlock
```

Every synchronized method calling another synchronized method would deadlock.

Therefore Java uses

```
Reentrant Locks
```

by default.

---

# 22. Lock Count

Internally,

the JVM keeps a

```
Lock Count
```

for every monitor.

Example

```
Thread A

↓

Enter first()

Count = 1

↓

Enter second()

Count = 2

↓

Return

Count = 1

↓

Return

Count = 0

↓

Release Monitor
```

The monitor is released only when the count reaches

```
0
```

---

## Visualization

```
Acquire

Count = 1

↓

Re-enter

Count = 2

↓

Exit

Count = 1

↓

Exit

Count = 0

↓

Monitor Released
```

---

# 23. JVM Internals (High Level)

Internally

```
Thread

↓

monitorenter

↓

Acquire Monitor

↓

Execute Code

↓

monitorexit

↓

Release Monitor
```

Everything happens automatically.

The programmer never writes

```
monitorenter
```

or

```
monitorexit
```

These instructions are generated by the Java compiler.

---

# 💡 JVM Insight

The JVM guarantees that every successful

```
monitorenter
```

has a matching

```
monitorexit
```

even if an exception occurs.

This prevents permanent lock ownership caused by unexpected failures.

---

# 24. Lock on `this`

When a non-static synchronized method is executed, the lock is acquired on the **current object**.

The keyword

```java
this
```

refers to the current object.

Therefore,

```java
public synchronized void deposit() {

}
```

is conceptually equivalent to

```java
public void deposit() {

    synchronized (this) {

        // Critical Section

    }

}
```

---

## Example

```java
class BankAccount {

    private int balance = 1000;

    public void deposit(int amount) {

        synchronized (this) {

            balance += amount;

        }

    }

}
```

Here,

```java
this
```

refers to the current `BankAccount` object.

---

## Visualization

```text
BankAccount Object

      │

      ▼

this

      │

      ▼

Monitor Lock

      │

      ▼

Critical Section
```

---

# 25. Lock on Custom Object

Sometimes locking the entire object is unnecessary.

Instead,

we can create a dedicated lock object.

Example

```java
class BankAccount {

    private int balance = 1000;

    private final Object lock = new Object();

    public void withdraw(int amount) {

        synchronized (lock) {

            balance -= amount;

        }

    }

}
```

---

## Why Use a Custom Lock?

Advantages

- Better encapsulation
- Avoids exposing the object's intrinsic lock
- Allows different parts of a class to use different locks
- Reduces unnecessary lock contention

> [!TIP]
> Using a private final lock object is often considered a best practice in library and framework code.

---

# 26. Static synchronized Method

A **static synchronized** method does **not** lock an object.

It locks the **Class object**.

Syntax

```java
public static synchronized void method() {

}
```

---

## Example

```java
class Bank {

    public static synchronized void updateInterestRate() {

        System.out.println("Interest Rate Updated");

    }

}
```

Here,

the lock belongs to

```java
Bank.class
```

not to any particular `Bank` object.

---

# 27. Class Lock

Every loaded Java class has one `Class` object.

Example

```java
Bank.class
```

This object has its own monitor.

Therefore,

```java
static synchronized
```

methods synchronize on the class object.

---

## Visualization

```text
          Bank.class

             🔒

      ▲              ▲

      │              │

 Thread A        Thread B
```

Only one thread may execute a static synchronized method for the same class at a time.

---

## Object Lock vs Class Lock

| Object Lock | Class Lock |
|-------------|------------|
| Belongs to an object | Belongs to the `Class` object |
| Used by instance synchronized methods | Used by static synchronized methods |
| Multiple objects have different locks | One class has one class lock |

---

## Example

```java
BankAccount account1 = new BankAccount();

BankAccount account2 = new BankAccount();
```

These objects have

```text
Two Object Locks
```

However,

```java
Bank.class
```

has only

```text
One Class Lock
```

---

# 28. Performance Considerations

Synchronization improves correctness,

but it also introduces overhead.

---

## Lock Acquisition

Before executing synchronized code,

the JVM must

- acquire a monitor
- check ownership
- possibly suspend waiting threads

These operations consume time.

---

## Lock Contention

Suppose

100 threads

attempt to enter the same synchronized block.

```text
Thread A

Running

Thread B

Waiting

Thread C

Waiting

...

Thread 100

Waiting
```

High contention reduces throughput.

---

## Keep Critical Sections Small

Instead of

```java
public synchronized void process() {

    // 200 lines

}
```

prefer

```java
public void process() {

    // Normal Code

    synchronized (this) {

        // Shared Data Update

    }

    // Normal Code

}
```

This minimizes waiting.

---

# 29. Best Practices

✅ Synchronize only the critical section.

✅ Keep synchronized blocks short.

✅ Prefer private lock objects when appropriate.

✅ Avoid exposing lock objects publicly.

✅ Minimize shared mutable state.

✅ Consider higher-level concurrency utilities (`ReentrantLock`, `ExecutorService`, etc.) when they better fit the problem.

---

# 30. Common Mistakes

---

## ❌ Synchronizing Every Method

Not every method requires synchronization.

Synchronize only code that accesses shared mutable data.

---

## ❌ Locking Public Objects

Bad

```java
synchronized (somePublicObject) {

}
```

Other classes may also synchronize on the same object, leading to unexpected contention.

Prefer

```java
private final Object lock = new Object();
```

---

## ❌ Long Critical Sections

Holding a lock for too long reduces concurrency.

---

## ❌ Assuming synchronized Improves Speed

Synchronization is for **correctness**, not performance.

---

## ❌ Confusing Object Lock with Class Lock

Remember

```text
Instance synchronized

↓

Object Lock

----------------------

Static synchronized

↓

Class Lock
```

---

# 31. Interview Questions

### 1. What does the `synchronized` keyword do?

It allows only one thread at a time to execute a protected section of code for a given monitor lock.

---

### 2. What is the difference between Synchronization and `synchronized`?

Synchronization is the concept.

`synchronized` is Java's built-in language feature that implements it.

---

### 3. What gets locked in a synchronized method?

For an instance synchronized method,

the current object (`this`) is locked.

For a static synchronized method,

the corresponding `Class` object is locked.

---

### 4. What is an Intrinsic Lock?

Every Java object has a built-in lock managed by the JVM.

---

### 5. What is a Monitor?

A monitor is the JVM mechanism that manages lock ownership and coordinates thread access to synchronized code.

---

### 6. What are `monitorenter` and `monitorexit`?

These are JVM bytecode instructions used to acquire and release monitor locks.

---

### 7. What is Reentrant Synchronization?

A thread that already owns a lock can acquire the same lock again without blocking.

---

### 8. Why is `synchronized` considered safer than manual locking?

The JVM automatically releases the monitor even if an exception occurs.

---

### 9. What is the difference between an Object Lock and a Class Lock?

Object Lock → specific object instance.

Class Lock → `Class` object shared by all instances.

---

### 10. When should you use a synchronized block instead of a synchronized method?

When only part of a method accesses shared mutable state.

---

# 32. Quick Revision

```text
                 synchronized

                       │

                       ▼

             Protect Critical Section

                       │

                       ▼

             Acquire Monitor Lock

                       │

                       ▼

               Execute Protected Code

                       │

                       ▼

             Release Monitor Lock
```

---

## Core Concepts

```text
Synchronization

↓

Concept

---------------------

synchronized

↓

Implementation

---------------------

Monitor

↓

Controls Access

---------------------

Object

↓

Intrinsic Lock

---------------------

monitorenter

↓

Acquire Lock

---------------------

monitorexit

↓

Release Lock

---------------------

Instance Method

↓

Object Lock

---------------------

Static Method

↓

Class Lock
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `synchronized` is
- [x] Why it exists
- [x] synchronized Method
- [x] synchronized Block
- [x] Object Lock
- [x] Intrinsic Lock
- [x] Monitor
- [x] `monitorenter`
- [x] `monitorexit`
- [x] Reentrant Synchronization
- [x] Lock on `this`
- [x] Lock on Custom Object
- [x] Static synchronized
- [x] Class Lock
- [x] Performance considerations
- [x] Best practices
- [x] Common interview questions

---

# 📌 Key Takeaways

- `synchronized` is Java's built-in synchronization mechanism.
- Every Java object has an intrinsic lock managed by a monitor.
- Instance synchronized methods lock the current object (`this`).
- Static synchronized methods lock the corresponding `Class` object.
- The JVM uses `monitorenter` and `monitorexit` to implement synchronization.
- Java synchronization is **reentrant**, allowing the same thread to reacquire the same lock.
- Use synchronized blocks to minimize the size of critical sections.
- Synchronization ensures correctness but may reduce concurrency if overused.

> [!TIP]
> **Interview Rule**
>
> Don't just remember **how** to write `synchronized`. Understand **what is being locked**, **who owns the lock**, and **how the JVM enforces mutual exclusion**.

---

# 📖 Next Topic

➡️ **06_Race_Condition.md**

In the next chapter, we'll study one of the most common concurrency bugs:

- What is a Race Condition?
- Lost Update Problem
- Read-Modify-Write
- Check-Then-Act
- Bank Account Example
- Ticket Booking Example
- Inventory Management
- How `synchronized` prevents race conditions
- Real interview scenarios
