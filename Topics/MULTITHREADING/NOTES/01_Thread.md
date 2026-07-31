# 🧵 Java Thread

> [!NOTE]
> A **thread** is the smallest unit of execution within a process.
> Java provides built-in multithreading support through the `Thread` class.

---

## 📌 Table of Contents

* [1. What is a Thread?](#1-what-is-a-thread)
* [2. Process vs Thread](#2-process-vs-thread)
* [3. Main Thread in Java](#3-main-thread-in-java)
* [4. Thread Class](#4-thread-class)
* [5. Creating a Thread](#5-creating-a-thread)
* [6. The run() Method](#6-the-run-method)
* [7. The start() Method](#7-the-start-method)
* [8. start() vs run()](#8-start-vs-run)
* [9. Multiple Threads](#9-multiple-threads)
* [10. Thread Scheduler](#10-thread-scheduler)
* [11. Current Thread](#11-current-thread)
* [12. Thread Name](#12-thread-name)
* [13. Thread ID](#13-thread-id)
* [14. sleep()](#14-sleep)
* [15. join()](#15-join)
* [16. isAlive()](#16-isalive)
* [17. Thread Priority](#17-thread-priority)
* [18. Daemon Threads](#18-daemon-threads)
* [19. Interrupting a Thread](#19-interrupting-a-thread)
* [20. Can We Start a Thread Twice?](#20-can-we-start-a-thread-twice)
* [21. Common Thread Methods](#21-common-thread-methods)
* [22. Common Mistakes](#22-common-mistakes)
* [23. Interview Questions](#23-interview-questions)
* [24. Quick Revision](#24-quick-revision)

---

# 1. What is a Thread?

A **thread** is an independent path of execution inside a process.

A single application can contain multiple threads performing different tasks.

```text
Process
│
├── Thread-1
├── Thread-2
├── Thread-3
└── Thread-4
```

For example, an application might use different threads for:

```text
Application
│
├── User Interface
├── Network Requests
├── File Processing
└── Background Tasks
```

> [!IMPORTANT]
> Threads inside the same process share process resources, but each thread maintains its own execution state.

---

# 2. Process vs Thread

A **process** is a program currently being executed.

A **thread** is a unit of execution inside that process.

### Example

```text
Java Application (Process)
│
├── Main Thread
├── Worker Thread
└── Background Thread
```

### Comparison

| Process                                   | Thread                                 |
| ----------------------------------------- | -------------------------------------- |
| Independent program in execution          | Unit of execution inside a process     |
| Has its own address space                 | Shares process resources               |
| Relatively expensive to create            | Generally lighter to create            |
| Communication often requires IPC          | Can communicate through shared objects |
| Process switching is relatively expensive | Thread switching is generally lighter  |

---

# 3. Main Thread in Java

Every Java application starts with a thread known as the **main thread**.

Consider:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello Java");

    }
}
```

The JVM executes:

```java
main()
```

using the main thread.

We can verify this using:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

    }
}
```

Output:

```text
main
```

So conceptually:

```text
JVM
 │
 ▼
Creates Main Thread
 │
 ▼
main()
 │
 ▼
Program Execution
```

---

# 4. Thread Class

Java provides the:

```java
java.lang.Thread
```

class for working with threads.

Because `Thread` belongs to `java.lang`, it does not normally require an explicit import.

```java
Thread thread = new Thread();
```

The `Thread` class provides functionality for:

* starting threads
* naming threads
* sleeping
* joining
* interruption
* priority
* daemon configuration
* checking thread state

---

# 5. Creating a Thread

One traditional way to create a thread is by extending the `Thread` class.

### Steps

```text
Extend Thread
      ↓
Override run()
      ↓
Create Thread Object
      ↓
Call start()
```

### Example

```java
class MyThread extends Thread {

    @Override
    public void run() {

        System.out.println("Child thread is running");

    }
}

public class Main {

    public static void main(String[] args) {

        MyThread thread = new MyThread();

        thread.start();

    }
}
```

Output:

```text
Child thread is running
```

---

# 6. The `run()` Method

The task performed by a thread is defined inside:

```java
public void run()
```

Example:

```java
class MyThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            System.out.println(i);

        }
    }
}
```

Here:

```text
run()
 │
 ▼
Contains the work
that the thread performs
```

> [!TIP]
> Think of `run()` as the **job** and `start()` as the operation that starts a new thread to perform that job.

---

# 7. The `start()` Method

A thread is started using:

```java
thread.start();
```

Conceptually:

```text
Thread Object
     │
     ▼
   start()
     │
     ▼
JVM requests thread creation
     │
     ▼
Thread becomes schedulable
     │
     ▼
   run()
```

`start()` does not simply behave like an ordinary call to `run()`.

It establishes a new path of execution.

---

# 8. `start()` vs `run()`

This is one of the most important basic Thread interview questions.

Consider:

```java
MyThread thread = new MyThread();

thread.run();
```

Calling `run()` directly behaves like a normal method call.

```text
Main Thread
    │
    ▼
  run()
    │
    ▼
Main Thread
```

No new thread is started.

---

If we use:

```java
thread.start();
```

then:

```text
                 ┌──► Main Thread continues
Main Thread ─────┤
                 │
                 └──► Child Thread
                          │
                          ▼
                        run()
```

### Comparison

| `start()`                             | `run()`                    |
| ------------------------------------- | -------------------------- |
| Starts a new thread                   | Normal method call         |
| New execution path is created         | Uses current thread        |
| JVM/thread scheduler becomes involved | No new scheduling required |
| Eventually executes `run()`           | Executes `run()` directly  |

> [!WARNING]
> Calling `run()` directly does **not** provide multithreading.

---

# 9. Multiple Threads

Consider:

```java
class MyThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            System.out.println("Child: " + i);

        }
    }
}

public class Main {

    public static void main(String[] args) {

        MyThread thread = new MyThread();

        thread.start();

        for (int i = 1; i <= 5; i++) {

            System.out.println("Main: " + i);

        }
    }
}
```

Possible output:

```text
Main: 1
Child: 1
Main: 2
Child: 2
Child: 3
Main: 3
Child: 4
Main: 4
Main: 5
Child: 5
```

But another execution may produce:

```text
Child: 1
Child: 2
Main: 1
Main: 2
Child: 3
Main: 3
Child: 4
Child: 5
Main: 4
Main: 5
```

Both are possible.

Why?

Because the execution order is controlled by thread scheduling.

> [!IMPORTANT]
> Never assume that independently running threads will execute in a fixed order.

---

# 10. Thread Scheduler

The **thread scheduler** determines which runnable thread gets CPU execution time.

Conceptually:

```text
Thread-1 ──┐
Thread-2 ──┤
Thread-3 ──┼──► Thread Scheduler ──► CPU
Thread-4 ──┘
```

Scheduling can depend on:

* operating system
* JVM implementation
* CPU availability
* current thread states
* system load
* thread priorities

Therefore:

```text
start() order
    ≠
guaranteed execution order
```

---

# 11. Current Thread

Java provides:

```java
Thread.currentThread()
```

to obtain the currently executing thread.

Example:

```java
public class Main {

    public static void main(String[] args) {

        Thread current = Thread.currentThread();

        System.out.println(current.getName());

    }
}
```

Output:

```text
main
```

Inside another thread:

```java
class MyThread extends Thread {

    @Override
    public void run() {

        Thread current = Thread.currentThread();

        System.out.println(current.getName());

    }
}
```

`currentThread()` is a static method.

---

# 12. Thread Name

Every thread has a name.

### Get Thread Name

```java
thread.getName();
```

Example:

```java
System.out.println(
    Thread.currentThread().getName()
);
```

Output:

```text
main
```

---

## Setting Thread Name

Use:

```java
thread.setName("Worker-Thread");
```

Example:

```java
class MyThread extends Thread {

    @Override
    public void run() {

        System.out.println(
            Thread.currentThread().getName()
        );

    }
}

public class Main {

    public static void main(String[] args) {

        MyThread thread = new MyThread();

        thread.setName("Worker-Thread");

        thread.start();

    }
}
```

Output:

```text
Worker-Thread
```

### Why Name Threads?

Thread names are useful for:

* debugging
* logs
* monitoring
* stack traces
* production troubleshooting

Instead of seeing:

```text
Thread-7
```

we might see:

```text
Payment-Worker
```

which is much easier to understand.

---

# 13. Thread ID

Threads have unique identifiers during their lifetime.

Modern Java provides:

```java
thread.threadId();
```

Example:

```java
public class Main {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();

        System.out.println(thread.threadId());

    }
}
```

Older Java code commonly uses:

```java
thread.getId();
```

`getId()` has been deprecated in newer Java releases.

> [!NOTE]
> If you're working with older Java versions such as Java 8 or Java 11, you will commonly encounter `getId()`.

---

# 14. `sleep()`

`sleep()` temporarily pauses the **currently executing thread**.

Syntax:

```java
Thread.sleep(milliseconds);
```

Example:

```java
public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("Start");

        Thread.sleep(2000);

        System.out.println("End");

    }
}
```

Execution:

```text
Start
  │
  ▼
Sleep ~2 seconds
  │
  ▼
End
```

---

## Important Point

`sleep()` is static.

```java
Thread.sleep(1000);
```

It pauses:

```text
Current Executing Thread
```

not an arbitrary thread object.

---

## `InterruptedException`

`sleep()` can throw:

```java
InterruptedException
```

Therefore:

```java
try {

    Thread.sleep(1000);

} catch (InterruptedException e) {

    Thread.currentThread().interrupt();

}
```

or:

```java
throws InterruptedException
```

can be used.

> [!IMPORTANT]
> Interruption is a cooperative mechanism. We will understand its basic behavior later in this file.

---

# 15. `join()`

`join()` allows one thread to wait until another thread finishes.

Example:

```java
class MyThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 3; i++) {

            System.out.println("Child: " + i);

        }
    }
}

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        MyThread thread = new MyThread();

        thread.start();

        thread.join();

        System.out.println("Main completed");

    }
}
```

Execution:

```text
Main Thread
    │
    ├──► start()
    │
    │        │
    │        ▼
    │    Child Thread
    │        │
    │      run()
    │        │
    │        ▼
    │    TERMINATES
    │
    ├── join() waits
    │
    ▼
Main continues
```

Output:

```text
Child: 1
Child: 2
Child: 3
Main completed
```

### Meaning

```java
thread.join();
```

essentially means:

> Current thread, wait for `thread` to terminate.

---

# 16. `isAlive()`

`isAlive()` checks whether a thread has been started and has not yet terminated.

```java
thread.isAlive();
```

Example:

```java
class MyThread extends Thread {

    @Override
    public void run() {

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }
    }
}

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        MyThread thread = new MyThread();

        System.out.println(thread.isAlive());

        thread.start();

        System.out.println(thread.isAlive());

        thread.join();

        System.out.println(thread.isAlive());

    }
}
```

Typical output:

```text
false
true
false
```

Conceptually:

```text
Before start()
    ↓
false

Running
    ↓
true

After termination
    ↓
false
```

---

# 17. Thread Priority

Every Java thread has a priority.

Java defines:

```java
Thread.MIN_PRIORITY
Thread.NORM_PRIORITY
Thread.MAX_PRIORITY
```

Their values are:

| Constant        | Value |
| --------------- | ----: |
| `MIN_PRIORITY`  |   `1` |
| `NORM_PRIORITY` |   `5` |
| `MAX_PRIORITY`  |  `10` |

Example:

```java
thread.setPriority(Thread.MAX_PRIORITY);
```

Retrieve priority:

```java
thread.getPriority();
```

---

## Does High Priority Guarantee First Execution?

**No.**

Suppose:

```text
Thread-A → Priority 10
Thread-B → Priority 1
```

This does **not** guarantee:

```text
Thread-A always runs first.
```

Thread scheduling depends heavily on the JVM and operating system.

> [!WARNING]
> Never use thread priority to guarantee application correctness or execution order.

---

# 18. Daemon Threads

Threads can broadly be classified as:

```text
Threads
│
├── User Threads
│
└── Daemon Threads
```

### User Thread

Normal application threads.

Examples:

```text
main
Worker Thread
Request Processing Thread
```

### Daemon Thread

A daemon thread generally performs background/support work.

---

## Creating a Daemon Thread

```java
thread.setDaemon(true);
```

Example:

```java
class BackgroundTask extends Thread {

    @Override
    public void run() {

        while (true) {

            System.out.println("Background task");

        }
    }
}

public class Main {

    public static void main(String[] args) {

        BackgroundTask thread = new BackgroundTask();

        thread.setDaemon(true);

        thread.start();

        System.out.println("Main completed");

    }
}
```

When all non-daemon threads terminate, the JVM may terminate even if daemon threads remain.

> [!CAUTION]
> Do not depend on daemon threads for critical operations that must always finish, such as reliably saving important data.

---

# 19. Interrupting a Thread

Java uses **interruption** as a cooperative mechanism for signalling a thread.

```java
thread.interrupt();
```

It does not mean:

```text
Forcefully kill the thread ❌
```

Instead:

```text
Request the thread to respond to interruption ✅
```

Example:

```java
class Worker extends Thread {

    @Override
    public void run() {

        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {

            System.out.println("Thread interrupted");

        }
    }
}
```

Another thread can call:

```java
worker.interrupt();
```

---

## Checking Interrupt Status

Two commonly encountered methods are:

```java
isInterrupted()
```

and:

```java
Thread.interrupted()
```

### Difference

| Method                 | Behavior                                    |
| ---------------------- | ------------------------------------------- |
| `isInterrupted()`      | Checks a thread's interrupt status          |
| `Thread.interrupted()` | Checks current thread and clears the status |

> [!TIP]
> Remember: `Thread.interrupted()` is static and operates on the **current thread**.

---

# 20. Can We Start a Thread Twice?

No.

Consider:

```java
MyThread thread = new MyThread();

thread.start();

thread.start();
```

The second call throws:

```text
java.lang.IllegalThreadStateException
```

A `Thread` instance can be started only once.

Conceptually:

```text
NEW
 │
 ▼
STARTED
 │
 ▼
RUNNING / WAITING
 │
 ▼
TERMINATED
```

Once the thread has terminated:

```text
TERMINATED
    │
    └──X──► Cannot restart
```

To perform the work again, create another thread object.

```java
MyThread t1 = new MyThread();
t1.start();

MyThread t2 = new MyThread();
t2.start();
```

> [!IMPORTANT]
> The complete thread lifecycle and official Java thread states will be covered separately in `03_Thread_Lifecycle.md`.

---

# 21. Common Thread Methods

| Method            | Purpose                               |
| ----------------- | ------------------------------------- |
| `start()`         | Starts the thread                     |
| `run()`           | Defines thread work                   |
| `currentThread()` | Returns currently executing thread    |
| `sleep()`         | Temporarily pauses current thread     |
| `join()`          | Waits for another thread to terminate |
| `isAlive()`       | Checks whether thread is alive        |
| `getName()`       | Gets thread name                      |
| `setName()`       | Sets thread name                      |
| `threadId()`      | Gets thread identifier in modern Java |
| `getPriority()`   | Gets thread priority                  |
| `setPriority()`   | Sets thread priority                  |
| `interrupt()`     | Sends an interruption request         |
| `isInterrupted()` | Checks interruption status            |
| `isDaemon()`      | Checks daemon status                  |
| `setDaemon()`     | Configures daemon status              |
| `getState()`      | Returns current thread state          |

---

# 22. Common Mistakes

## ❌ Calling `run()` Instead of `start()`

```java
thread.run();
```

This does not start another thread.

Use:

```java
thread.start();
```

---

## ❌ Starting Same Thread Twice

```java
thread.start();
thread.start();
```

Results in:

```text
IllegalThreadStateException
```

---

## ❌ Assuming Execution Order

```java
t1.start();
t2.start();
```

does **not** guarantee:

```text
t1 finishes before t2
```

---

## ❌ Depending on Priority

```java
t1.setPriority(10);
```

does not guarantee that `t1` executes first.

---

## ❌ Using `Thread.stop()`

`Thread.stop()` is deprecated and unsafe.

```java
thread.stop(); // ❌ Avoid
```

Prefer cooperative mechanisms such as interruption.

---

# 23. Interview Questions

### 1. What is a thread?

A thread is a unit/path of execution inside a process.

---

### 2. Which class represents a thread in Java?

```java
java.lang.Thread
```

---

### 3. What is the main thread?

It is the thread that executes the application's `main()` method.

---

### 4. What is the difference between `start()` and `run()`?

```text
start()
   ↓
Starts new thread
   ↓
run() executes on that thread
```

while:

```text
run()
   ↓
Normal method call
   ↓
Current thread executes it
```

---

### 5. Can we start the same thread twice?

No.

The second `start()` causes:

```text
IllegalThreadStateException
```

---

### 6. Can a terminated thread be restarted?

No.

A new `Thread` object must be created.

---

### 7. Is thread execution order guaranteed?

No.

Scheduling is controlled by the JVM/runtime and operating system.

---

### 8. What does `sleep()` do?

It temporarily pauses the currently executing thread.

---

### 9. What does `join()` do?

It causes the current thread to wait for another thread to terminate.

---

### 10. What is a daemon thread?

A daemon thread performs background/support work and does not keep the JVM alive when all non-daemon threads have terminated.

---

### 11. Does high priority guarantee first execution?

No.

Thread priority is only a scheduling hint.

---

### 12. What does `interrupt()` do?

It sends a cooperative interruption request to a thread.

---

### 13. What does `isAlive()` return?

It returns `true` if a thread has been started and has not yet terminated.

---

### 14. Why is `Thread.stop()` deprecated?

Because forcibly terminating a thread can leave shared application state inconsistent.

---

# 24. Quick Revision

```text
                    THREAD
                       │
        ┌──────────────┼───────────────┐
        │              │               │
        ▼              ▼               ▼
      start()         run()          sleep()
        │              │               │
        ▼              ▼               ▼
   New Thread      Thread Task     Pause Current
                                    Thread
        │
        ├──────────────┐
        │              │
        ▼              ▼
      join()       interrupt()
        │              │
        ▼              ▼
 Wait for Thread   Interruption
 to Terminate       Request
```

### Remember

```text
Thread class
    ↓
java.lang.Thread

Every Java program
    ↓
Main Thread

start()
    ↓
Starts new thread

run()
    ↓
Contains thread task

Direct run()
    ↓
No new thread

start() twice
    ↓
IllegalThreadStateException

sleep()
    ↓
Pause current thread

join()
    ↓
Wait for target thread

Priority
    ↓
No execution guarantee

Daemon
    ↓
Background/support thread

interrupt()
    ↓
Cooperative interruption
```

---

## 🎯 SDE Checklist

After completing this file, you should be able to answer:

* [x] What is a thread?
* [x] Process vs thread
* [x] What is the main thread?
* [x] How does the `Thread` class work?
* [x] How do we create a thread?
* [x] `start()` vs `run()`
* [x] Why is thread execution order unpredictable?
* [x] How does `sleep()` work?
* [x] How does `join()` work?
* [x] What is `isAlive()`?
* [x] What is thread priority?
* [x] What is a daemon thread?
* [x] What is thread interruption?
* [x] Why can't a thread be started twice?
* [x] Important `Thread` methods

---

> [!TIP]
> **Core interview rule:**
> `start()` starts a new thread. Calling `run()` directly does not.

> [!IMPORTANT]
> Do not try to memorize thread output order. Unless coordination is explicitly established, thread scheduling should be treated as nondeterministic.

---

### ⏭️ Next Topic

**[02. Runnable](02_Runnable.md)**

Learn how Java separates the **task to execute** from the **thread executing it** using the `Runnable` interface.
