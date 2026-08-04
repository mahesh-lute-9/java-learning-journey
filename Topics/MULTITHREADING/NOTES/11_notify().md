# 🔔 11. notify()

> [!NOTE]
> The `notify()` method wakes **one** thread waiting on the same object's monitor.
>
> It **does not release the monitor immediately** and **does not guarantee** which waiting thread will be awakened.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is `notify()`?](#2-what-is-notify)
- [3. Why Do We Need `notify()`?](#3-why-do-we-need-notify)
- [4. How `notify()` Works](#4-how-notify-works)
- [5. Does `notify()` Release the Monitor?](#5-does-notify-release-the-monitor)
- [6. Wait Set vs Entry Set](#6-wait-set-vs-entry-set)
- [7. Which Thread Gets Notified?](#7-which-thread-gets-notified)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Synchronization
- Monitor
- Inter Thread Communication
- `wait()`
- Wait Set

---

# 2. What is `notify()`?

The

```java
notify()
```

method is used to wake **one** thread waiting on the monitor of the current object.

It is used when a thread changes the shared state and another waiting thread may now continue.

---

## Definition

> `notify()` wakes a single thread waiting on the current object's monitor. The awakened thread becomes eligible to compete for the monitor lock.

---

## Syntax

```java
notify();
```

---

## Important

`notify()` only affects threads waiting on **the same object**.

Example

```java
synchronized (lock) {

    lock.notify();

}
```

Only threads waiting on

```java
lock
```

can be notified.

---

# 3. Why Do We Need `notify()`?

Suppose a consumer thread is waiting because the queue is empty.

```
Consumer

↓

wait()

↓

WAITING
```

The producer adds an item.

Without notification,

the consumer would continue waiting forever.

Therefore,

the producer executes

```java
notify();
```

The waiting consumer can now continue.

---

## High-Level Flow

```text
Consumer

↓

wait()

↓

WAITING

────────────────────────

Producer

↓

Adds Item

↓

notify()

────────────────────────

Consumer

↓

Eligible To Continue
```

---

# 4. How `notify()` Works

Suppose

Thread A

has called

```java
wait();
```

Current state

```text
Thread A

↓

WAITING
```

Now

Thread B

updates the shared resource.

Then

```java
notify();
```

is executed.

---

## Internal Flow

```text
Thread A

↓

WAITING

──────────────────────

Thread B

↓

Acquire Monitor

↓

Update Shared Data

↓

notify()

↓

Release Monitor

──────────────────────

Thread A

↓

Acquire Monitor

↓

Continue Execution
```

Notice

Thread A does **not** continue immediately after `notify()`.

It must wait until Thread B releases the monitor.

---

# 5. Does `notify()` Release the Monitor?

**No.**

This is one of the biggest misconceptions.

Suppose

```java
synchronized (lock) {

    lock.notify();

    // More Code

}
```

Even after

```java
notify();
```

the current thread still owns the monitor.

The awakened thread **cannot** continue until the synchronized block ends.

---

## Visualization

```text
Thread B

↓

Acquire Monitor

↓

notify()

↓

Still Owns Monitor

↓

Execute Remaining Code

↓

Release Monitor

────────────────────

Thread A

↓

Acquire Monitor

↓

Continue
```

---

## Interview Rule

> `notify()` **wakes** a thread.
>
> It does **not** transfer the monitor immediately.

---

# 6. Wait Set vs Entry Set

This is an advanced JVM concept that helps explain `notify()`.

---

## Wait Set

Threads that execute

```java
wait();
```

enter the

```text
Wait Set
```

They are waiting for a notification.

---

## Entry Set

After a thread is notified,

it leaves the Wait Set.

However,

it still needs the monitor.

It enters the

```text
Entry Set
```

where it waits to reacquire the monitor.

---

## Visualization

```text
             Monitor

     ┌──────────────────┐

     │    Wait Set      │

     │──────────────────│

     │ Thread A         │
     │ Thread B         │

     └──────────────────┘

             │

          notify()

             ▼

     ┌──────────────────┐

     │    Entry Set     │

     │──────────────────│

     │ Thread A         │

     └──────────────────┘

             │

      Monitor Available?

             │

             ▼

        Continue
```

---

## Complete Flow

```text
WAITING

↓

notify()

↓

Entry Set

↓

Acquire Monitor

↓

RUNNABLE

↓

Running
```

---

# 7. Which Thread Gets Notified?

Suppose three threads are waiting.

```text
Thread A

Thread B

Thread C
```

Now,

another thread executes

```java
notify();
```

Which thread wakes?

Answer

```
Unknown
```

The Java Language Specification does **not** guarantee which waiting thread will be selected.

The choice depends on the JVM implementation and thread scheduling.

---

## Visualization

```text
Wait Set

──────────────

Thread A

Thread B

Thread C

↓

notify()

↓

?

↓

One Thread Selected
```

---

## Important

You should **never** write code that depends on a particular waiting thread being notified.

Correct programs work regardless of which thread is chosen.

> [!TIP]
> If multiple waiting threads may need to proceed, `notifyAll()` is often safer than `notify()`.

---

# 8. IllegalMonitorStateException

Just like `wait()`,

the

```java
notify()
```

method can only be called by a thread that **owns the object's monitor**.

If not,

the JVM throws

```text
IllegalMonitorStateException
```

---

## Incorrect Example

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

## Correct Example

```java
Object lock = new Object();

synchronized (lock) {

    lock.notify();

}
```

Now,

the current thread owns

```java
lock
```

therefore,

calling

```java
notify();
```

is valid.

---

# 9. Producer–Consumer Example

Let's see

```java
notify()
```

in action.

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

## Flow

Initially

```text
Queue Empty
```

Consumer

```
↓

wait()

↓

WAITING
```

Producer

```
↓

Produce Item

↓

notify()
```

Consumer

```
↓

Entry Set

↓

Acquire Monitor

↓

Continue
```

---

# 10. `notify()` vs `notifyAll()`

One of the most common interview questions.

| `notify()` | `notifyAll()` |
|------------|---------------|
| Wakes one waiting thread | Wakes all waiting threads |
| Thread selection is unspecified | Every waiting thread becomes eligible to compete |
| May be more efficient | Often safer when multiple conditions exist |

---

## Example

Suppose

three threads are waiting.

```text
Thread A

Thread B

Thread C
```

---

### `notify()`

```text
Thread A

↓

Continue

──────────────

Thread B

WAITING

──────────────

Thread C

WAITING
```

---

### `notifyAll()`

```text
Thread A

RUNNABLE

──────────────

Thread B

RUNNABLE

──────────────

Thread C

RUNNABLE
```

All awakened threads compete for the monitor.

Only one acquires it first.

---

> [!IMPORTANT]
> Waking every thread does **not** mean every thread executes immediately.
>
> Each thread must still reacquire the monitor before continuing.

---

# 11. Common Mistakes

---

## ❌ Assuming `notify()` Releases the Lock

Wrong

```text
notify()

↓

Other Thread Runs
```

Correct

```text
notify()

↓

Current Thread Continues

↓

Releases Monitor

↓

Waiting Thread Acquires Monitor

↓

Runs
```

---

## ❌ Calling `notify()` Without a Waiting Thread

This is legal.

Nothing happens.

No exception is thrown.

Example

```java
synchronized (lock) {

    lock.notify();

}
```

If nobody is waiting,

the notification is simply lost.

---

## ❌ Depending on Which Thread Is Notified

Never assume

```
Thread A
```

or

```
Thread B
```

will always be selected.

The JVM makes no such guarantee.

---

## ❌ Forgetting to Update Shared State Before `notify()`

Wrong

```java
notify();

available = true;
```

Correct

```java
available = true;

notify();
```

The shared state should represent the new condition **before** notifying waiting threads.

---

# 12. Best Practices

✅ Call `notify()` only while holding the monitor.

✅ Update shared state before notifying.

✅ Use `while` around `wait()`.

✅ Keep synchronized blocks short.

✅ Use `notifyAll()` if multiple waiting threads may need to proceed or when different waiting conditions share the same monitor.

---

# 13. Interview Questions

### 1. What does `notify()` do?

It wakes one thread waiting on the current object's monitor.

---

### 2. Does `notify()` release the monitor?

No.

The current thread continues executing until it exits the synchronized block or method.

---

### 3. Which thread does `notify()` wake?

The Java specification does not define which waiting thread is chosen.

---

### 4. Can `notify()` be called outside a synchronized block?

No.

The calling thread must own the object's monitor,

otherwise

```text
IllegalMonitorStateException
```

is thrown.

---

### 5. What happens if `notify()` is called when no thread is waiting?

Nothing.

The notification has no effect.

---

### 6. What is the Entry Set?

The Entry Set contains threads that have been notified and are waiting to reacquire the monitor.

---

### 7. Difference between Wait Set and Entry Set?

| Wait Set | Entry Set |
|-----------|-----------|
| Threads waiting after calling `wait()` | Threads notified and waiting to reacquire the monitor |
| Waiting for notification | Waiting for the monitor lock |

---

# 14. Quick Revision

```text
Thread

↓

wait()

↓

Wait Set

↓

notify()

↓

Entry Set

↓

Acquire Monitor

↓

Continue
```

---

## `notify()` Checklist

```text
✔ Wakes One Thread

✔ Does Not Release Monitor

✔ Must Be Inside synchronized

✔ Belongs To Object

✔ Selection Is Unspecified
```

---

## JVM Flow

```text
WAITING

↓

notify()

↓

Entry Set

↓

Monitor Available

↓

RUNNABLE

↓

Running
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `notify()` is
- [x] Why it is needed
- [x] Internal working
- [x] Wait Set
- [x] Entry Set
- [x] Why `notify()` does not release the monitor
- [x] `IllegalMonitorStateException`
- [x] `notify()` vs `notifyAll()`
- [x] Producer–Consumer usage
- [x] Best practices

---

# 📌 Key Takeaways

- `notify()` wakes **one** waiting thread.
- The awakened thread does **not** execute immediately.
- A notified thread first moves from the **Wait Set** to the **Entry Set**.
- It must reacquire the monitor before continuing.
- The current thread retains the monitor until it exits the synchronized region.
- The JVM does not guarantee which waiting thread will be selected.
- Always update shared state **before** calling `notify()`.

> [!TIP]
> **Interview Rule**
>
> Remember this sequence:
>
> **`wait()` → Wait Set → `notify()` → Entry Set → Reacquire Monitor → Continue**
>
> Understanding this flow is far more important than memorizing the method definition.

---

# 📖 Next Topic

➡️ **12. `notifyAll()`**

In the next chapter, we'll answer:

- What is `notifyAll()`?
- Why wake all waiting threads?
- When should `notifyAll()` be preferred over `notify()`?
- Performance considerations
- Common misconceptions
- Real-world examples
- Interview questions

> ⭐ **Choosing between `notify()` and `notifyAll()` is a common interview topic and an important design decision in concurrent programming.**
