# 📞 17. Callable

> [!NOTE]
> `Callable` is a functional interface introduced in **Java 5** that represents a task capable of **returning a result** and **throwing checked exceptions**.
>
> Unlike `Runnable`, a `Callable` task produces a value that can later be obtained through a `Future`.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is Callable?](#2-what-is-callable)
- [3. Why Do We Need Callable?](#3-why-do-we-need-callable)
- [4. Why Runnable Is Not Enough](#4-why-runnable-is-not-enough)
- [5. Callable Interface](#5-callable-interface)
- [6. How Callable Works](#6-how-callable-works)
- [7. Runnable vs Callable](#7-runnable-vs-callable)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Threads
- Runnable
- Executor Framework
- ExecutorService

---

# 2. What is Callable?

`Callable` represents a task that

- returns a value
- may throw checked exceptions
- executes asynchronously through an `ExecutorService`

Unlike

```java
Runnable
```

it is designed for computations whose results are needed.

---

## Definition

> `Callable<V>` is a generic functional interface whose `call()` method returns a value of type `V`.

---

## Package

```java
java.util.concurrent
```

---

## Declaration

```java
Callable<Integer> task;
```

---

# 3. Why Do We Need Callable?

Suppose you want to calculate

```text
Factorial

Prime Numbers

Database Query

API Response

File Processing
```

The task should return a result.

---

With

```java
Runnable
```

there is no return value.

With

```java
Callable
```

the result is returned directly.

---

## Example

```text
Task

↓

Calculate Sum

↓

Return Result
```

---

# 4. Why Runnable Is Not Enough

`Runnable`

contains only one method

```java
void run();
```

Notice

```java
void
```

There is no return value.

---

## Runnable Example

```java
Runnable task = () -> {

    System.out.println(
        "Task Completed");

};
```

The caller cannot obtain a computed result.

---

## Another Limitation

`Runnable`

cannot throw checked exceptions directly.

---

### Example

```java
public void run() {

    throw new IOException();

}
```

This does **not** compile because `run()` does not declare checked exceptions.

---

# 5. Callable Interface

The `Callable` interface defines one method.

```java
V call() throws Exception;
```

---

## Breakdown

```java
V
```

The return type.

---

```java
call()
```

The method executed by the executor.

---

```java
throws Exception
```

Allows checked exceptions.

---

## Example

```java
Callable<String> task = () -> {

    return "Hello";

};
```

---

## Another Example

```java
Callable<Integer> task = () -> {

    return 100;

};
```

---

# 6. How Callable Works

A `Callable` task is submitted to an

```java
ExecutorService
```

The executor executes the task using a worker thread.

The returned value is stored in a

```java
Future
```

object.

---

## High-Level Flow

```text
Callable

↓

submit()

↓

ExecutorService

↓

Worker Thread

↓

call()

↓

Result

↓

Future
```

---

## Example

```java
ExecutorService executor =
        Executors.newSingleThreadExecutor();

Callable<Integer> task = () -> 100;

Future<Integer> future =
        executor.submit(task);
```

Notice

```java
submit()
```

returns

```java
Future<Integer>
```

The actual result can be retrieved later.

> [!NOTE]
> We'll study `Future` in detail in the next chapter.

---

# 7. Runnable vs Callable

| Runnable | Callable |
|-----------|----------|
| `run()` | `call()` |
| Returns `void` | Returns a value |
| Cannot throw checked exceptions | Can throw checked exceptions |
| Fire-and-forget tasks | Tasks that produce results |
| Used with `execute()` | Usually used with `submit()` |

---

## Visualization

### Runnable

```text
Task

↓

run()

↓

Done
```

---

### Callable

```text
Task

↓

call()

↓

Result

↓

Future
```

---

# 💡 Interview Insight

One of the most frequently asked interview questions is:

> **Why was `Callable` introduced when `Runnable` already existed?**

**Answer:**

Because `Runnable`

- cannot return results
- cannot throw checked exceptions

`Callable`

solves both problems while integrating with the Executor Framework.

---

# 8. Creating a `Callable`

There are multiple ways to create a `Callable`.

---

## Using a Lambda Expression

```java
Callable<Integer> task = () -> {

    return 100;

};
```

Since

```java
Callable
```

is a functional interface,

lambda expressions are commonly used.

---

## Using an Anonymous Class

```java
Callable<String> task =
        new Callable<String>() {

    @Override

    public String call() {

        return "Hello";

    }

};
```

---

## Using a Separate Class

```java
class SumTask
        implements Callable<Integer> {

    @Override

    public Integer call() {

        return 10 + 20;

    }

}
```

---

# 9. Submitting a `Callable`

Unlike

```java
Runnable
```

a

```java
Callable
```

is submitted using

```java
submit()
```

---

## Example

```java
ExecutorService executor =
        Executors.newSingleThreadExecutor();

Callable<Integer> task = () -> 500;

Future<Integer> future =
        executor.submit(task);
```

Execution Flow

```text
Callable

↓

submit()

↓

ExecutorService

↓

Worker Thread

↓

call()

↓

Future
```

---

## Why `submit()`?

Because the executor needs a way to return the task's result.

That result is represented by a

```java
Future
```

object.

---

# 10. `Future` (Overview)

Whenever a

```java
Callable
```

is submitted,

Java returns

```java
Future<V>
```

---

## What Is a Future?

A `Future` represents the **result of an asynchronous computation**.

It acts as a placeholder until the computation completes.

---

## Visualization

```text
Callable

↓

Running

↓

Future

↓

Result Ready?

↓

YES

↓

Return Value
```

---

> [!NOTE]
> We'll study the `Future` API in detail in the next chapter.

---

# 11. Multiple Callable Tasks

Suppose you have

```text
Task A

Task B

Task C
```

Each task returns a value.

You can submit all of them to the same executor.

```java
ExecutorService executor =
        Executors.newFixedThreadPool(3);

executor.submit(taskA);

executor.submit(taskB);

executor.submit(taskC);
```

Each task executes independently.

---

## Visualization

```text
Callable A

Callable B

Callable C

↓

Thread Pool

↓

Worker 1

Worker 2

Worker 3

↓

Results
```

---

# 12. `invokeAll()`

Sometimes,

multiple `Callable` tasks should all execute,

and you need **every result**.

For this,

Java provides

```java
invokeAll()
```

---

## Syntax

```java
List<Future<Integer>> results =
        executor.invokeAll(tasks);
```

---

## Working

```text
Task List

↓

invokeAll()

↓

Execute All Tasks

↓

Wait Until All Finish

↓

Return List Of Futures
```

---

## Characteristics

- Executes all tasks.
- Waits until every task completes.
- Returns a list of `Future` objects.

---

# 13. `invokeAny()`

Sometimes,

you only need **one successful result**.

Java provides

```java
invokeAny()
```

---

## Syntax

```java
String result =
        executor.invokeAny(tasks);
```

---

## Working

```text
Task List

↓

Execute All

↓

First Successful Result

↓

Return Result

↓

Cancel Remaining Tasks
```

---

## Example Use Cases

- Querying multiple servers
- Searching multiple replicas
- Fastest response wins

---

# 14. Real-World Examples

---

## Database Query

```java
Callable<List<Employee>> task;
```

Returns

```text
Employee List
```

---

## API Request

```java
Callable<Response> task;
```

Returns

```text
HTTP Response
```

---

## Prime Number Calculation

```java
Callable<List<Integer>> task;
```

Returns

```text
Prime Numbers
```

---

## File Processing

```java
Callable<String> task;
```

Returns

```text
Processed File Content
```

---

# 💡 Interview Insight

A common interview question is:

> **What is the difference between `submit()` and `invokeAll()`?**

| `submit()` | `invokeAll()` |
|-------------|---------------|
| Submits one task | Submits multiple tasks |
| Returns one `Future` | Returns a list of `Future` objects |
| Does not wait for all submitted tasks by itself | Waits until all tasks complete |

---

Another common question:

> **What is the difference between `invokeAll()` and `invokeAny()`?**

| `invokeAll()` | `invokeAny()` |
|----------------|---------------|
| Waits for every task | Returns the first successful result |
| Returns `List<Future>` | Returns a single value |
| All tasks complete | Remaining tasks may be cancelled after one succeeds |

---

# 15. Performance Considerations

`Callable` itself does **not** improve performance.

The performance benefits come from executing multiple `Callable` tasks concurrently using the **Executor Framework**.

---

## Sequential Execution

```text
Task A

↓

Task B

↓

Task C

↓

Done
```

Total Time

```text
A + B + C
```

---

## Parallel Execution

```text
Task A

──────────────

Task B

──────────────

Task C

↓

Done
```

Multiple worker threads execute tasks simultaneously.

This can significantly reduce total execution time for independent tasks.

---

## Best Use Cases

- CPU-intensive computations
- Database queries
- File processing
- Network requests
- Image processing
- Report generation

---

# 16. Common Mistakes

---

## ❌ Using `Runnable` When a Result Is Needed

Wrong

```java
Runnable task = () -> {

    return 100;

};
```

`Runnable`

cannot return a value.

Use

```java
Callable<Integer>
```

instead.

---

## ❌ Forgetting to Shutdown the Executor

Wrong

```java
ExecutorService executor =
        Executors.newFixedThreadPool(2);

executor.submit(task);
```

Always call

```java
executor.shutdown();
```

when the executor is no longer needed.

---

## ❌ Ignoring Exceptions

One advantage of

```java
Callable
```

is that

```java
call()
```

can throw checked exceptions.

Do not silently ignore them.

---

## ❌ Submitting Long Blocking Tasks to a Small Thread Pool

Suppose

```text
2 Worker Threads
```

and

```text
100 Long-Running Tasks
```

The remaining tasks wait in the queue.

Choose an appropriate thread pool size based on your workload.

---

# 17. Best Practices

✅ Use `Callable` whenever a task must return a value.

✅ Submit `Callable` tasks using `submit()`.

✅ Reuse `ExecutorService` instances.

✅ Handle checked exceptions properly.

✅ Always shut down the executor gracefully.

✅ Use `invokeAll()` when every result is required.

✅ Use `invokeAny()` when the first successful result is sufficient.

---

# 18. Interview Questions

### 1. What is `Callable`?

`Callable` is a functional interface that represents a task capable of returning a value and throwing checked exceptions.

---

### 2. Why was `Callable` introduced?

Because `Runnable`

- cannot return results
- cannot throw checked exceptions

---

### 3. What method does `Callable` define?

```java
V call() throws Exception;
```

---

### 4. Can `Callable` be executed directly by a thread?

No.

Unlike `Runnable`,

`Callable` is typically submitted to an

```java
ExecutorService
```

using

```java
submit()
```

---

### 5. What does `submit()` return for a `Callable`?

A

```java
Future<V>
```

representing the eventual result.

---

### 6. Difference between `Runnable` and `Callable`?

| Runnable | Callable |
|-----------|----------|
| `run()` | `call()` |
| Returns `void` | Returns a value |
| Cannot throw checked exceptions | Can throw checked exceptions |
| Used with `execute()` | Usually used with `submit()` |

---

### 7. Difference between `invokeAll()` and `invokeAny()`?

| `invokeAll()` | `invokeAny()` |
|----------------|---------------|
| Executes all tasks | Executes all tasks |
| Waits for all | Returns first successful result |
| Returns `List<Future>` | Returns a single value |

---

### 8. When should you use `Callable`?

Whenever a background task needs to

- return a result
- report success or failure
- throw checked exceptions

---

# 19. Quick Revision

## Runnable vs Callable

```text
Runnable

↓

run()

↓

No Result

────────────────────────

Callable

↓

call()

↓

Result

↓

Future
```

---

## Callable Flow

```text
Callable

↓

submit()

↓

ExecutorService

↓

Worker Thread

↓

call()

↓

Future

↓

Result
```

---

## Execution Methods

```text
submit()

↓

One Task

↓

One Future

────────────────────────

invokeAll()

↓

Many Tasks

↓

List<Future>

────────────────────────

invokeAny()

↓

Many Tasks

↓

First Successful Result
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `Callable` is
- [x] Why it is needed
- [x] `Runnable` vs `Callable`
- [x] `call()` method
- [x] Submitting tasks
- [x] `Future` overview
- [x] `invokeAll()`
- [x] `invokeAny()`
- [x] Best practices

---

# 📌 Key Takeaways

- `Callable` is the preferred choice when asynchronous tasks need to **return results**.
- Unlike `Runnable`, `Callable` can throw checked exceptions.
- `Callable` integrates naturally with the Executor Framework through `submit()`.
- The result of a `Callable` is represented by a `Future`.
- `invokeAll()` is useful when all results are required.
- `invokeAny()` is useful when the first successful result is enough.
- Choosing between `Runnable` and `Callable` depends primarily on whether the task produces a result.

> [!TIP]
> **Interview Rule**
>
> Ask yourself:
>
> **Does the task return a value?**
>
> - **No** → `Runnable`
> - **Yes** → `Callable`

---

# 📖 Next Topic

➡️ **18. Future**

In the next chapter, we'll explore:

- What is `Future`?
- How does it represent asynchronous results?
- `get()`
- `isDone()`
- `cancel()`
- `isCancelled()`
- Timeouts
- Blocking vs Non-blocking
- Best practices
- Interview questions

> ⭐ **`Future` is the bridge between starting an asynchronous task and retrieving its result. Understanding it is essential before learning `CompletableFuture`.**
