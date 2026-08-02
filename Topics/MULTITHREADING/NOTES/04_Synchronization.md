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

---

# 9. Advantages of Synchronization

Synchronization provides several important benefits in multithreaded applications.

---

## 9.1 Data Consistency

The primary goal of synchronization is to maintain **consistent data**.

Consider a bank account.

Without synchronization

```text
Balance = ₹1000

Thread A Withdraws ₹500

Thread B Withdraws ₹700
```

Both threads may read the same balance simultaneously.

Final balance becomes incorrect.

With synchronization,

only one thread updates the balance at a time.

```text
Balance = ₹1000

↓

Thread A Executes

↓

Balance = ₹500

↓

Thread B Executes

↓

Balance = -₹200
```

The operations happen in a predictable order.

---

## 9.2 Prevents Data Corruption

Suppose two threads write into the same file simultaneously.

Without synchronization

```text
Thread A

Hello World

Thread B

Java Programming
```

Possible output

```text
Hello PrograWorldmming
```

The file becomes corrupted because writes overlap.

Synchronization ensures only one thread writes at a time.

---

## 9.3 Ensures Thread Safety

Synchronization protects shared mutable data from concurrent modification.

Example

```java
counter++;
```

Without synchronization,

multiple threads may update the counter incorrectly.

Synchronization ensures each update completes before another begins.

> [!TIP]
> Synchronization is one of the most common techniques used to achieve **thread safety**.

---

## 9.4 Prevents Race Conditions

When multiple threads compete to update shared data,

unexpected results may occur.

Synchronization prevents multiple threads from entering the critical section simultaneously.

> [!NOTE]
> Race Conditions will be covered in detail in **06_Race_Condition.md**.

---

## 9.5 Predictable Program Behavior

Concurrent programs are naturally difficult to reason about.

Synchronization makes program execution more predictable by controlling access to shared resources.

Although thread scheduling remains nondeterministic,

the protected critical section behaves correctly.

---

## Summary

Synchronization helps by

- Maintaining data consistency
- Preventing corruption
- Improving thread safety
- Preventing race conditions
- Making concurrent programs reliable

---

# 10. Disadvantages of Synchronization

Although synchronization solves many problems,

it is **not free**.

Every synchronization operation introduces some overhead.

---

## 10.1 Performance Overhead

Before entering a synchronized section,

a thread must acquire a lock.

After finishing,

it must release the lock.

These additional operations consume time.

Example

```text
Without Synchronization

Execute

↓

Finish

----------------------------

With Synchronization

Acquire Lock

↓

Execute

↓

Release Lock
```

More work means slightly lower performance.

---

## 10.2 Reduced Parallelism

Suppose five threads want to execute the same synchronized method.

```text
Thread A

Running

Thread B

Waiting

Thread C

Waiting

Thread D

Waiting

Thread E

Waiting
```

Only one thread proceeds.

The remaining threads remain idle.

This reduces parallel execution.

---

## 10.3 Waiting Time

Threads often spend time waiting instead of performing useful work.

Example

```text
Thread A
│
Uses Resource

↓

Thread B

WAIT

↓

Thread C

WAIT
```

Waiting threads consume system resources.

---

## 10.4 Possibility of Deadlock

Poor synchronization design may lead to

```text
Deadlock
```

Example

```text
Thread A

Waiting for Lock B

Thread B

Waiting for Lock A
```

Neither thread can continue.

> [!WARNING]
> Incorrect synchronization can introduce deadlocks.

Deadlocks will be discussed in

```text
08_Deadlock.md
```

---

## 10.5 Scalability Issues

As the number of threads increases,

heavy synchronization may become a bottleneck.

Instead of improving performance,

it may reduce throughput.

---

## Summary

Synchronization may

- Reduce performance
- Increase waiting
- Reduce concurrency
- Increase contention
- Cause deadlocks if misused

---

# 11. Real-World Use Cases

Synchronization is widely used in enterprise applications.

---

## Banking Systems

Example

```text
Deposit

Withdraw

Transfer Money
```

Only one transaction should modify an account balance at a time.

---

## E-Commerce

Example

```text
Product Quantity

↓

Customer A

Customer B

Customer C
```

Synchronization prevents selling the same item multiple times.

---

## Ticket Booking

Suppose only one seat remains.

```text
Seat 25

↓

Customer A

Customer B
```

Without synchronization,

both customers may receive the same seat.

---

## Inventory Management

Warehouse stock

```text
Current Stock = 10
```

Multiple orders update inventory simultaneously.

Synchronization keeps stock accurate.

---

## Logging Systems

Multiple threads writing to the same log file should coordinate access.

Otherwise,

log entries may overlap.

---

## Database Updates

Concurrent transactions updating the same record require coordination.

Synchronization helps maintain consistency before changes are persisted.

---

# 12. Where Synchronization is Needed

Synchronization is useful whenever

multiple threads

```text
Read

and/or

Write
```

the same mutable resource.

Examples

- Shared Objects
- Counters
- Bank Accounts
- Collections
- Files
- Cache
- Static Variables
- Database Connections

---

## Typical Pattern

```text
Shared Mutable Data

↓

Multiple Threads

↓

Synchronization Required
```

---

# 13. Where Synchronization is NOT Needed

Synchronization is unnecessary in several situations.

---

## Read-Only Data

If data never changes,

multiple threads can safely read it.

Example

```java
final String COMPANY = "OpenAI";
```

No synchronization required.

---

## Local Variables

Every thread has its own stack.

Local variables are not shared.

Example

```java
public void calculate() {

    int sum = 0;

}
```

Each thread has its own copy of

```java
sum
```

---

## Immutable Objects

Objects whose state never changes are naturally thread-safe.

Examples

- String
- LocalDate
- BigInteger

(assuming the reference itself isn't being replaced unsafely).

---

## Independent Resources

Suppose each thread has its own object.

```text
Thread A

Counter A

Thread B

Counter B
```

Nothing is shared.

Synchronization is unnecessary.

> [!TIP]
> Synchronize **shared mutable state**, not everything.

---

# 14. Common Misconceptions

---

## ❌ Synchronization Makes Programs Faster

False.

Synchronization often **reduces** performance because of locking overhead.

Its purpose is **correctness**, not speed.

---

## ❌ Every Method Should Be Synchronized

False.

Only protect code that accesses shared mutable data.

Synchronizing unnecessary code reduces concurrency.

---

## ❌ Read Operations Always Need Synchronization

Not necessarily.

If data is immutable or safely published and only being read,

synchronization may not be required.

---

## ❌ More Synchronization Means Better Safety

Excessive synchronization can

- reduce performance
- increase lock contention
- make code harder to maintain

Good synchronization is **minimal and focused**.

---

# 15. Best Practices

✅ Synchronize only the critical section.

✅ Keep synchronized code as short as possible.

✅ Minimize lock contention.

✅ Prefer immutable objects whenever possible.

✅ Avoid synchronizing unnecessary operations.

✅ Document shared mutable state clearly.

✅ Use higher-level concurrency utilities when appropriate.

> [!TIP]
> The best synchronization is often **avoiding shared mutable state** altogether.

---

# 16. Interview Questions

Below are some of the most frequently asked interview questions related to Synchronization.

---

## 1. What is Synchronization?

Synchronization is a mechanism used to control concurrent access to shared resources so that only one thread executes the critical section at a time.

Its primary purpose is to maintain data consistency and prevent unexpected behavior in multithreaded applications.

---

## 2. Why is Synchronization Needed?

Synchronization is needed because multiple threads may access and modify the same shared resource simultaneously.

Without proper coordination, this can lead to

- Data inconsistency
- Race conditions
- Unexpected results
- Data corruption

---

## 3. What is a Shared Resource?

A shared resource is any object, variable, file, database record, or collection that can be accessed by multiple threads.

Examples:

- Counter
- Bank Account
- File
- List
- Cache
- Database Connection

---

## 4. What is a Critical Section?

A critical section is the portion of code that accesses shared mutable data.

Only one thread should execute the critical section at a time.

---

## 5. What is Mutual Exclusion?

Mutual exclusion means that only one thread is allowed to execute a critical section at a given time.

Other threads must wait until the current thread leaves the critical section.

---

## 6. Does Synchronization Improve Performance?

No.

Synchronization usually introduces additional overhead because threads must acquire and release locks.

Its purpose is correctness, not performance.

---

## 7. Does Every Variable Need Synchronization?

No.

Only **shared mutable data** requires synchronization.

Local variables are thread-safe because every thread has its own stack.

---

## 8. Is Reading Shared Data Always Unsafe?

No.

Reading immutable or safely published data is generally safe.

Problems usually occur when one or more threads modify shared data.

---

## 9. What Problems Does Synchronization Solve?

Synchronization helps prevent:

- Data inconsistency
- Race conditions
- Lost updates
- Data corruption

---

## 10. Can Synchronization Cause Problems?

Yes.

Improper synchronization may lead to

- Deadlock
- Reduced performance
- Lock contention
- Reduced scalability

---

## 11. Is Synchronization Required for Immutable Objects?

No.

Immutable objects cannot change after creation, making them naturally thread-safe.

---

## 12. What Is the Main Goal of Synchronization?

The main goal is

```text
Correctness
```

not

```text
Performance
```

---

# 17. Quick Revision

```text
                 SYNCHRONIZATION

                         │
                         ▼

        Controls Access To Shared Resources

                         │

        ┌────────────────┼────────────────┐

        ▼                ▼                ▼

 Shared Resource    Critical Section   Mutual Exclusion

        │                │                │

        └────────────────┼────────────────┘

                         ▼

             Only One Thread At A Time

                         │

                         ▼

               Consistent Program State
```

---

## One-Line Revision

```text
Synchronization

↓

Controls concurrent access

↓

Protects shared mutable data

↓

Prevents multiple threads from entering the critical section simultaneously

↓

Maintains data consistency
```

---

## Remember

```text
Synchronization

✔ Correctness

✔ Consistency

✔ Reliability

✘ Speed
```

---

## Synchronization Workflow

```text
Shared Resource

↓

Multiple Threads

↓

Need Coordination

↓

Synchronization

↓

One Thread Enters

↓

Other Threads Wait

↓

Thread Finishes

↓

Next Thread Enters
```

---

# 18. Key Takeaways

- Synchronization coordinates multiple threads accessing shared resources.
- Its purpose is to maintain **correctness**, not improve performance.
- Shared mutable data is the primary reason synchronization is needed.
- A **critical section** is the part of the program that accesses shared mutable state.
- **Mutual exclusion** ensures that only one thread executes the critical section at a time.
- Synchronization prevents inconsistent updates and many concurrency bugs.
- Synchronization introduces overhead and should be applied only where necessary.
- Local variables and immutable objects generally do not require synchronization.
- Excessive synchronization can reduce scalability and throughput.
- Good concurrent design minimizes shared mutable state instead of synchronizing everything.

> [!TIP]
> **Interview Rule**
>
> Synchronize **data**, not **methods**.
>
> Protect only the code that accesses shared mutable state.

---

# 📌 Before Moving Ahead

After completing this chapter, you should clearly understand:

- Why synchronization exists
- The problems it solves
- Shared resources
- Critical sections
- Mutual exclusion
- Where synchronization is needed
- Where synchronization is unnecessary

Notice that we **have not discussed how Java performs synchronization internally**.

That implementation is covered in the next chapter.

---

# 📖 Next Topic

# 05. synchronized Keyword

In the next chapter, we'll answer:

> **How does Java actually implement synchronization?**

Topics include:

- What is `synchronized`?
- synchronized Method
- synchronized Block
- Object Lock
- Class Lock
- Lock on `this`
- Lock on Custom Object
- Static Synchronization
- Reentrant Synchronization
- JVM Monitor
- `monitorenter`
- `monitorexit`
- Lock Acquisition
- Lock Release
- Performance Considerations
- Best Practices
- Common Mistakes
- Interview Questions

> [!IMPORTANT]
> This is one of the most important chapters in Java Multithreading.
>
> It explains the implementation details behind everything you learned in this chapter.
