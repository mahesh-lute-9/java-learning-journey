# 🏃 02. Runnable Interface

> [!NOTE]
> The `Runnable` interface represents a **task** that can be executed by a thread.
>
> Unlike extending the `Thread` class, implementing `Runnable` separates the **task** from the **thread** that executes it.
>
> This is the preferred approach in modern Java and forms the foundation of the **Executor Framework**, **Thread Pools**, and **CompletableFuture**.

---

# 📚 Table of Contents

- [1. What is Runnable?](#1-what-is-runnable)
- [2. Why Runnable?](#2-why-runnable)
- [3. Runnable Interface](#3-runnable-interface)
- [4. Thread vs Runnable](#4-thread-vs-runnable)
- [5. How Runnable Works](#5-how-runnable-works)
- [6. Creating a Thread using Runnable](#6-creating-a-thread-using-runnable)
- [7. Execution Flow](#7-execution-flow)
- [8. Internal Working](#8-internal-working)
- [9. Why Runnable is Preferred](#9-why-runnable-is-preferred)

---

# 1. What is Runnable?

`Runnable` is a **Functional Interface** present in the `java.lang` package.

It represents a **task** that can be executed by a thread.

Unlike the `Thread` class, the `Runnable` interface **does not create a new thread**.

Instead, it only defines **what work should be done**.

Package:

```java
java.lang.Runnable
```

Declaration:

```java
@FunctionalInterface
public interface Runnable {

    void run();

}
```

---

## Key Points

- Functional Interface
- Contains only one abstract method
- Represents a task
- Does not create a thread
- Executed by a `Thread`
- Preferred approach for multithreading

---

## Real-Life Analogy

Imagine a restaurant.

```text
Recipe
   │
   ▼
Runnable

Chef
   │
   ▼
Thread
```

The recipe tells **what to cook**.

The chef actually cooks it.

Similarly,

```text
Runnable
      │
      ▼
Defines Task

Thread
      │
      ▼
Executes Task
```

---

# 2. Why Runnable?

Before Java developers started using `Runnable`, many programs extended the `Thread` class.

Example:

```java
class MyThread extends Thread {

}
```

Although this works, it introduces some problems.

---

## Problem 1 : No Multiple Inheritance

Suppose we already have

```java
class Employee {

}
```

Now we want multithreading.

```java
class MyThread extends Employee {

}
```

If we also extend Thread

```java
class MyThread extends Employee, Thread {

}
```

Compilation Error ❌

Java doesn't support multiple inheritance of classes.

---

## Runnable Solves This

```java
class Employee extends Person implements Runnable {

}
```

Now we get

- Inheritance
- Multithreading

Both together.

---

## Problem 2 : Poor Design

Suppose a delivery company has

```text
Delivery Boy
```

and

```text
Package
```

They are different things.

Similarly,

```text
Thread
```

is

```text
Worker
```

while

```text
Runnable
```

is

```text
Work
```

Separating them produces cleaner software design.

---

## Problem 3 : Better Reusability

One Runnable object can be executed by multiple threads.

Example

```text
          Runnable
              │
      ┌───────┴────────┐
      ▼                ▼
 Thread-1          Thread-2
```

This is not possible when we tightly couple the task with Thread inheritance.

---

# 3. Runnable Interface

The Runnable interface contains only one abstract method.

```java
public interface Runnable {

    void run();

}
```

Since Java 8,

Runnable is also a

```text
Functional Interface
```

because it contains only one abstract method.

Therefore we can use

- Lambda Expressions
- Method References

---

## The run() Method

The

```java
run()
```

method contains the task that the thread should perform.

Example

```java
class MyTask implements Runnable {

    @Override
    public void run() {

        System.out.println("Task Executing");

    }

}
```

Notice carefully

The Runnable object still hasn't started any thread.

It only describes the task.

---

# 4. Thread vs Runnable

| Thread | Runnable |
|---------|----------|
| Class | Interface |
| Represents Worker | Represents Task |
| Uses Inheritance | Uses Interface |
| Cannot extend another class | Can extend another class |
| Tightly coupled | Loosely coupled |
| Less Flexible | More Flexible |
| Older Style | Preferred Style |

---

## Easy Way to Remember

```text
Thread
   │
Worker

Runnable
   │
Task
```

---

## Which Should We Prefer?

Modern Java recommends

```text
Runnable
```

because

- Better Design
- Better Reusability
- Supports Lambda Expressions
- Used by Executor Framework

---

# 5. How Runnable Works

Suppose we write

```java
class MyTask implements Runnable {

    @Override
    public void run() {

        System.out.println("Running");

    }

}
```

Now create object

```java
MyTask task = new MyTask();
```

Still,

No thread exists.

Now

```java
Thread thread = new Thread(task);
```

A Thread object exists.

Still,

Nothing executes.

Finally

```java
thread.start();
```

Now

```text
Thread
      │
Creates New Thread
      │
Calls
      │
run()
```

---

## Important

Runnable

```java
task.run();
```

does NOT create a new thread.

It behaves exactly like

```java
myObject.someMethod();
```

Only

```java
thread.start();
```

creates another path of execution.

---

# 6. Creating a Thread using Runnable

### Step 1

Implement Runnable

```java
class MyTask implements Runnable {

    @Override
    public void run() {

        System.out.println("Task Running");

    }

}
```

---

### Step 2

Create Runnable Object

```java
MyTask task = new MyTask();
```

---

### Step 3

Pass Runnable to Thread

```java
Thread thread = new Thread(task);
```

---

### Step 4

Start Thread

```java
thread.start();
```

---

## Complete Program

```java
class MyTask implements Runnable {

    @Override
    public void run() {

        System.out.println("Child Thread Executing");

    }

}

public class Main {

    public static void main(String[] args) {

        MyTask task = new MyTask();

        Thread thread = new Thread(task);

        thread.start();

    }

}
```

Output

```text
Child Thread Executing
```

---

# 7. Execution Flow

```text
Runnable Object
      │
      ▼
Thread Constructor
      │
      ▼
Thread Object
      │
      ▼
start()
      │
      ▼
JVM Creates New Thread
      │
      ▼
run()
```

Another way to visualize

```text
MyTask
   │
implements Runnable
   │
   ▼
Thread(MyTask)
   │
   ▼
start()
   │
   ▼
run()
```

---

# 8. Internal Working

Suppose we write

```java
Runnable task = new MyTask();

Thread thread = new Thread(task);

thread.start();
```

Internally

```text
Runnable Object
      │
Stored inside
      │
Thread Object
      │
start()
      │
JVM Creates Thread
      │
Scheduler Selects Thread
      │
Thread Executes
      │
task.run()
```

Notice

The Thread object internally invokes

```java
task.run();
```

The Runnable object itself never starts the thread.

---

# 9. Why Runnable is Preferred

Modern Java applications almost always prefer Runnable over extending Thread.

Reasons

✅ Better Object-Oriented Design

✅ Better Code Reusability

✅ Supports Multiple Inheritance through Interfaces

✅ Cleaner Separation of Responsibilities

✅ Supports Lambda Expressions

✅ Used by ExecutorService

✅ Used by Thread Pools

✅ Used by CompletableFuture

> [!TIP]
> **Interview Rule**
>
> Think of **Runnable as the task** and **Thread as the worker**.
>
> The worker performs the task.

---

# 10. Anonymous Runnable

Instead of creating a separate class that implements `Runnable`, we can create an anonymous class.

This is useful when the task is small and used only once.

## Example

```java
public class Main {

    public static void main(String[] args) {

        Runnable task = new Runnable() {

            @Override
            public void run() {

                System.out.println("Anonymous Runnable");

            }

        };

        Thread thread = new Thread(task);

        thread.start();

    }

}
```

Output

```text
Anonymous Runnable
```

---

## Execution Flow

```text
Anonymous Runnable
        │
        ▼
Thread Object
        │
        ▼
start()
        │
        ▼
run()
```

---

## Advantages

- No separate class required
- Cleaner for small tasks
- Common before Java 8

---

# 11. Runnable using Lambda Expression

Since `Runnable` is a **Functional Interface**, Java 8 allows us to use **Lambda Expressions**.

Instead of writing

```java
Runnable task = new Runnable() {

    @Override
    public void run() {

        System.out.println("Hello");

    }

};
```

we can simply write

```java
Runnable task = () -> {

    System.out.println("Hello");

};
```

This is shorter, cleaner, and easier to read.

---

## Example

```java
public class Main {

    public static void main(String[] args) {

        Runnable task = () -> {

            System.out.println("Running using Lambda");

        };

        Thread thread = new Thread(task);

        thread.start();

    }

}
```

Output

```text
Running using Lambda
```

---

## Why Lambdas Work?

Because Runnable has only **one abstract method**.

```java
void run();
```

Java knows that the lambda expression is the implementation of `run()`.

---

## Execution Flow

```text
Lambda Expression
        │
        ▼
Runnable Object
        │
        ▼
Thread
        │
        ▼
start()
        │
        ▼
run()
```

---

# 12. Passing Runnable to Thread

The `Thread` class provides constructors that accept a Runnable object.

Common constructor

```java
Thread(Runnable target)
```

Example

```java
Runnable task = () -> {

    System.out.println("Working");

};

Thread thread = new Thread(task);

thread.start();
```

The Thread stores the Runnable object internally.

When `start()` is called,

the Thread executes

```java
task.run();
```

---

# 13. Can Runnable Start a Thread?

**No.**

Runnable only represents a task.

It does not create a new thread.

Example

```java
Runnable task = new MyTask();

task.run();
```

Output

```text
Task Running
```

This executes on the **current thread**.

No new thread is created.

To create another thread

```java
Thread thread = new Thread(task);

thread.start();
```

---

## Important Difference

| Runnable | Thread |
|-----------|---------|
| Defines work | Executes work |
| Cannot start a thread | Can start a thread |
| Contains `run()` | Contains `start()` |

---

# 14. Multiple Threads using the Same Runnable

One Runnable object can be shared by multiple Thread objects.

Example

```java
class MyTask implements Runnable {

    @Override
    public void run() {

        System.out.println(

            Thread.currentThread().getName()

        );

    }

}

public class Main {

    public static void main(String[] args) {

        MyTask task = new MyTask();

        Thread t1 = new Thread(task);

        Thread t2 = new Thread(task);

        Thread t3 = new Thread(task);

        t1.start();

        t2.start();

        t3.start();

    }

}
```

Possible Output

```text
Thread-0

Thread-1

Thread-2
```

Order may change every execution.

---

## Visualization

```text
              Runnable
                  │
      ┌───────────┼───────────┐
      ▼           ▼           ▼
   Thread-1    Thread-2    Thread-3
      │           │           │
      ▼           ▼           ▼
     run()       run()       run()
```

---

## Why Share Runnable?

Sharing the same Runnable object

- avoids duplicate code
- improves reusability
- separates task from execution

This design is heavily used by

- Thread Pools
- Executor Framework
- Scheduled Executors

---

# 15. Advantages of Runnable

## 1. Better Design

Separates

```text
Task

from

Thread
```

---

## 2. Supports Multiple Inheritance

```java
class Employee extends Person
        implements Runnable
```

Possible because Runnable is an interface.

---

## 3. Reusable

Same Runnable can be executed by multiple threads.

---

## 4. Lambda Support

Runnable is a Functional Interface.

```java
Runnable task = () -> {

};
```

---

## 5. Used by Modern Java APIs

Examples

- ExecutorService
- ThreadPoolExecutor
- CompletableFuture
- ScheduledExecutorService

---

## 6. Easier Testing

The task can be tested independently from the thread.

---

# 16. Limitations of Runnable

Runnable has only one method

```java
void run();
```

Therefore

- cannot return a value
- cannot throw checked exceptions directly

To solve these limitations,

Java introduced

```text
Callable<V>
```

which we will study later.

---

# 17. Runnable vs Thread (Interview Comparison)

| Thread | Runnable |
|---------|----------|
| Class | Interface |
| Represents Worker | Represents Task |
| Uses Inheritance | Uses Interface |
| Cannot extend another class | Can extend another class |
| Less Flexible | More Flexible |
| Older Approach | Preferred Approach |
| Creates Thread | Does Not Create Thread |

---

## Which One Should We Use?

For modern Java applications,

prefer

```text
Runnable
```

unless there is a very specific reason to extend Thread.

> [!IMPORTANT]
> Extending `Thread` is mainly useful for learning or in rare specialized cases.
>
> In production applications, `Runnable` (or higher-level concurrency APIs) is generally preferred.

---

# 18. Common Mistakes

Understanding the common mistakes while using `Runnable` is important for interviews and real-world development.

---

## ❌ Mistake 1: Calling `run()` Instead of `start()`

Many beginners write:

```java
Runnable task = new MyTask();

Thread thread = new Thread(task);

thread.run();
```

Output

```text
Task Running
```

Although the output appears correct, **no new thread is created**.

Execution Flow

```text
Main Thread
      │
      ▼
run()
      │
      ▼
Main Thread Continues
```

### Correct Way

```java
thread.start();
```

Execution Flow

```text
Main Thread
      │
      ├─────────────► Continues
      │
      ▼
New Thread
      │
      ▼
run()
```

> [!WARNING]
> `run()` is just a normal method call.
>
> `start()` creates a new thread.

---

## ❌ Mistake 2: Forgetting to Create a Thread

```java
Runnable task = new MyTask();
```

Many beginners expect the task to execute automatically.

It won't.

A Runnable object only represents the work.

You must pass it to a Thread.

Correct

```java
Thread thread = new Thread(task);

thread.start();
```

---

## ❌ Mistake 3: Extending Thread Unnecessarily

Some developers write

```java
class MyThread extends Thread {

}
```

even when no Thread-specific behavior is required.

Preferred

```java
class MyTask implements Runnable {

}
```

This provides better design and flexibility.

---

## ❌ Mistake 4: Assuming Runnable Creates a Thread

Many interview candidates think

```java
Runnable task = new MyTask();
```

creates another thread.

It does not.

Only

```java
thread.start();
```

creates another path of execution.

---

## ❌ Mistake 5: Mixing Task and Thread

Bad Design

```text
Thread
     │
Contains Business Logic
```

Better Design

```text
Runnable
      │
Business Logic

Thread
      │
Execution
```

Keeping them separate makes the application easier to maintain.

---

# 19. Internal Summary

When we write

```java
Runnable task = new MyTask();

Thread thread = new Thread(task);

thread.start();
```

Internally the flow is approximately

```text
Create Runnable Object
          │
          ▼
Create Thread Object
          │
Stores Runnable Reference
          │
          ▼
start()
          │
JVM Creates New Thread
          │
Scheduler Selects Thread
          │
Thread Executes
          │
task.run()
```

Notice carefully

The Thread object invokes

```java
task.run();
```

The Runnable object never creates the thread.

---

# 20. Interview Questions

## 1. What is Runnable?

Runnable is a Functional Interface that represents a task to be executed by a thread.

---

## 2. Is Runnable a Functional Interface?

Yes.

It contains only one abstract method.

```java
void run();
```

---

## 3. Which package contains Runnable?

```java
java.lang
```

---

## 4. Does Runnable create a new thread?

No.

Runnable only defines the task.

The Thread class creates the thread.

---

## 5. Which method does Runnable contain?

```java
run()
```

---

## 6. Difference between Runnable and Thread?

| Runnable | Thread |
|----------|---------|
| Interface | Class |
| Task | Worker |
| Cannot create thread | Creates thread |
| Preferred | Older approach |

---

## 7. Why is Runnable preferred?

- Better Object-Oriented Design
- Code Reusability
- Supports Multiple Inheritance
- Supports Lambda Expressions
- Used by Executor Framework

---

## 8. Can multiple threads execute the same Runnable?

Yes.

One Runnable object can be shared by multiple Thread objects.

---

## 9. Can Runnable return a value?

No.

Its `run()` method returns

```java
void
```

To return a value, Java provides

```java
Callable<V>
```

---

## 10. Why does ExecutorService use Runnable?

ExecutorService executes **tasks**, not Thread subclasses.

Runnable represents the task.

---

## 11. Can Runnable throw checked exceptions?

No.

The `run()` method does not declare checked exceptions.

---

## 12. Which is preferred in modern Java?

Runnable.

Higher-level concurrency APIs like `ExecutorService` and `CompletableFuture` are built around the idea of submitting tasks rather than extending `Thread`.

---

# 21. Quick Revision

```text
Runnable
      │
Functional Interface
      │
Contains
      │
run()

Runnable
      │
Represents Task

Thread
      │
Represents Worker

Runnable
      │
Cannot Create Thread

Thread
      │
Creates Thread

Runnable
      │
Passed to Thread

Thread
      │
Calls run()

Modern Java
      │
Runnable
      │
ExecutorService
      │
Thread Pool
      │
CompletableFuture
```

---

# 🎯 SDE Checklist

After completing this topic, you should be able to answer:

- [x] What is Runnable?
- [x] Why Runnable was introduced
- [x] Runnable Interface
- [x] How to create a thread using Runnable
- [x] Thread vs Runnable
- [x] Execution Flow
- [x] Anonymous Runnable
- [x] Lambda Runnable
- [x] Passing Runnable to Thread
- [x] Multiple Threads using Same Runnable
- [x] Advantages of Runnable
- [x] Limitations of Runnable
- [x] Common Mistakes
- [x] Interview Questions

---

# 💡 Key Takeaways

- `Runnable` represents **what to execute**.
- `Thread` represents **who executes it**.
- `Runnable` does **not** create a thread.
- `Thread.start()` creates a new thread and eventually invokes `run()`.
- One Runnable object can be executed by multiple threads.
- `Runnable` supports lambda expressions because it is a Functional Interface.
- Modern Java applications prefer `Runnable` over extending `Thread`.

> [!TIP]
> **Interview Rule**
>
> **Thread = Worker**
>
> **Runnable = Task**
>
> A worker performs a task.

---

# 📖 What's Next?

➡️ **03_Thread_Lifecycle.md**

In the next chapter, we'll study:

- Thread States
- NEW State
- RUNNABLE State
- BLOCKED State
- WAITING State
- TIMED_WAITING State
- TERMINATED State
- State Transition Diagram
- Lifecycle Flow
- Thread Scheduler Interaction
- Interview Questions
- JVM Internals of Thread Lifecycle

This is one of the most frequently asked Java multithreading interview topics.
