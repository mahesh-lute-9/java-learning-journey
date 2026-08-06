# 🔮 18. Future

> [!NOTE]
> A **`Future`** represents the **result of an asynchronous computation**.
>
> It acts as a placeholder for a value that may not be available immediately.
>
> A `Future` allows you to:
>
> - Retrieve the result later
> - Check whether the task has completed
> - Cancel the task
> - Wait for completion with or without a timeout

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is Future?](#2-what-is-future)
- [3. Why Do We Need Future?](#3-why-do-we-need-future)
- [4. How Future Works](#4-how-future-works)
- [5. Creating a Future](#5-creating-a-future)
- [6. Future Lifecycle](#6-future-lifecycle)
- [7. Blocking Nature of `get()`](#7-blocking-nature-of-get)

---

# 1. Prerequisites

Before reading this chapter, you should understand:

- Executor Framework
- ExecutorService
- Callable
- Runnable
- Basic multithreading

---

# 2. What is Future?

A

```java
Future<V>
```

represents the result of a computation that is executing asynchronously.

The task may

- still be running,
- have completed successfully,
- have failed with an exception,
- have been cancelled.

---

## Definition

> A `Future` is an object that represents the eventual outcome of an asynchronous task.

---

## Package

```java
java.util.concurrent
```

---

# 3. Why Do We Need Future?

Suppose we calculate a factorial.

Without

```java
Future
```

the main thread must wait.

```text
Main Thread

↓

Calculate

↓

Wait

↓

Result
```

Nothing else can happen.

---

Using

```java
Future
```

the computation runs in the background.

Meanwhile,

the main thread can continue doing useful work.

---

## Visualization

```text
Main Thread

↓

Submit Task

↓

Continue Other Work

↓

Later

↓

Get Result
```

This improves responsiveness.

---

# 4. How Future Works

Suppose we submit

```java
Callable<Integer>
```

to an executor.

---

## Flow

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

The

```java
Future
```

stores the eventual result.

---

## High-Level Idea

```text
Task Submitted

↓

Running

↓

Future

↓

Completed

↓

Result Available
```

---

# 5. Creating a Future

The most common way is through

```java
submit()
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

The task begins executing asynchronously.

---

# 6. Future Lifecycle

A

```java
Future
```

passes through several states.

---

## Initial

```text
Created
```

Task has been submitted.

---

## Running

```text
Executing
```

Worker thread is executing

```java
call()
```

---

## Completed

```text
Finished Successfully
```

Result is available.

---

## Cancelled

Task was cancelled before completion.

---

## Visualization

```text
Created

↓

Running

↓

Completed

──────────────

OR

──────────────

Cancelled
```

---

# 7. Blocking Nature of `get()`

The

```java
get()
```

method returns the result.

---

## Syntax

```java
future.get();
```

---

## Important

If the computation has **not** finished,

```java
get()
```

blocks the calling thread until the result becomes available.

---

## Visualization

```text
Main Thread

↓

future.get()

↓

Task Finished?

↓

NO

↓

WAIT

──────────────

YES

↓

Return Result
```

---

## Example

```java
Future<Integer> future =
        executor.submit(() -> {

            Thread.sleep(3000);

            return 50;

        });

System.out.println(

        future.get()

);
```

Output appears **after approximately 3 seconds**, because `get()` waits for the task to complete.

---

## Blocking vs Asynchronous

Submitting the task is asynchronous.

Calling

```java
get()
```

is synchronous because it waits for completion if necessary.

---

# 💡 Interview Insight

A common interview question is:

> **If `Future` is asynchronous, why does `get()` block?**

**Answer:**

The task executes asynchronously,

but retrieving the result with

```java
get()
```

is a blocking operation.

If the result is not ready,

the calling thread waits.

---

# 8. `get()`

The

```java
get()
```

method retrieves the result of the asynchronous computation.

If the task has not completed,

the calling thread waits.

---

## Syntax

```java
V result = future.get();
```

---

## Example

```java
ExecutorService executor =
        Executors.newSingleThreadExecutor();

Future<Integer> future =
        executor.submit(() -> 200);

System.out.println(

        future.get()

);
```

Output

```text
200
```

---

## Working

```text
Future

↓

Completed?

↓

YES

↓

Return Result

──────────────

NO

↓

Wait

↓

Return Result
```

---

# 9. `get(timeout)`

Sometimes,

waiting forever is not acceptable.

Java provides

```java
get(timeout, unit)
```

---

## Syntax

```java
future.get(
        5,
        TimeUnit.SECONDS
);
```

---

## Working

```text
Future

↓

Completed Within

5 Seconds?

↓

YES

↓

Return Result

──────────────

NO

↓

TimeoutException
```

---

## Example

```java
Future<Integer> future =
        executor.submit(() -> {

            Thread.sleep(10000);

            return 100;

        });

future.get(
        3,
        TimeUnit.SECONDS
);
```

Output

```text
TimeoutException
```

because the task needs

```
10 Seconds
```

but we waited only

```
3 Seconds
```

---

# 10. `isDone()`

The

```java
isDone()
```

method checks whether the task has completed.

---

## Syntax

```java
future.isDone();
```

---

## Returns

```text
true

↓

Completed

──────────────

false

↓

Still Running
```

---

## Example

```java
while (!future.isDone()) {

    System.out.println(
            "Waiting...");

}
```

Once the task completes,

```java
isDone()
```

returns

```text
true
```

---

# 11. `cancel()`

Sometimes,

a running task is no longer needed.

The

```java
cancel()
```

method requests cancellation.

---

## Syntax

```java
future.cancel(true);
```

---

## Parameters

```java
true
```

Attempt to interrupt the running task.

---

```java
false
```

Do not interrupt if already running.

Cancel only if the task has not started.

---

## Visualization

```text
Running Task

↓

cancel(true)

↓

Interrupt

↓

Task Stops

(if it responds to interruption)
```

---

## Important

Cancellation is **best effort**.

A task that ignores interruption may continue running.

---

# 12. `isCancelled()`

Checks whether a task was cancelled.

---

## Syntax

```java
future.isCancelled();
```

---

## Returns

```text
true

↓

Cancelled

──────────────

false

↓

Not Cancelled
```

---

## Example

```java
future.cancel(true);

System.out.println(

        future.isCancelled()

);
```

Output

```text
true
```

---

# 13. Exception Handling

When using

```java
Future
```

there are several important exceptions.

---

## `InterruptedException`

Thrown if the current thread is interrupted while waiting.

---

## `ExecutionException`

Thrown when the asynchronous task itself throws an exception.

Example

```java
Callable<Integer> task = () -> {

    throw new RuntimeException();

};
```

Calling

```java
future.get();
```

throws

```text
ExecutionException
```

whose cause is the original exception.

---

## `TimeoutException`

Thrown when

```java
get(timeout)
```

waits too long.

---

## Summary

| Exception | Reason |
|------------|--------|
| `InterruptedException` | Waiting thread interrupted |
| `ExecutionException` | Task failed with an exception |
| `TimeoutException` | Timed wait expired |

---

# 14. Real-World Examples

---

## Download File

```text
Submit Download

↓

Continue UI

↓

Future

↓

Download Complete

↓

Display File
```

---

## Database Query

```text
Submit Query

↓

Continue Processing

↓

Future

↓

Result Ready

↓

Process Data
```

---

## Machine Learning Prediction

```text
Submit Prediction

↓

Continue Application

↓

Future

↓

Prediction Ready
```

---

## Image Processing

```text
Submit Image Task

↓

Future

↓

Processed Image
```

---

# 💡 Interview Insight

A common interview question is:

> **What is the difference between `isDone()` and `get()`?**

| `isDone()` | `get()` |
|-------------|---------|
| Checks completion | Retrieves result |
| Non-blocking | Blocking if necessary |
| Returns boolean | Returns task result |

---

Another common question:

> **What happens if the task throws an exception?**

Answer:

The exception is wrapped inside an

```java
ExecutionException
```

and thrown by

```java
future.get()
```

---

# 15. Limitations of `Future` ⭐⭐⭐⭐⭐

Although

```java
Future
```

was a major improvement over manual thread management,

it has several limitations.

These limitations led to the introduction of

```java
CompletableFuture
```

in Java 8.

---

## 1. Blocking `get()`

The biggest limitation.

```java
future.get();
```

blocks until the computation completes.

---

### Visualization

```text
Main Thread

↓

future.get()

↓

WAIT

↓

Result Ready

↓

Continue
```

The calling thread cannot perform other work while waiting.

---

## 2. No Task Chaining

Suppose we need

```text
Download File

↓

Process File

↓

Save Result
```

With

```java
Future
```

this becomes

```java
Future<File> future =
        executor.submit(download);

File file = future.get();

process(file);

save(file);
```

Each step waits for the previous one.

There is no built-in support for chaining asynchronous operations.

---

## 3. No Built-in Callbacks

Suppose you want

```text
Task Finished

↓

Automatically Execute Another Task
```

`Future`

does not support this directly.

You must manually call

```java
get();
```

and then execute the next operation.

---

## 4. Difficult Combination of Multiple Futures

Suppose

```
Future A

Future B

Future C
```

You need all three results.

With

```java
Future
```

you typically write

```java
futureA.get();

futureB.get();

futureC.get();
```

This becomes verbose and difficult to manage.

---

## Summary

```text
Future

↓

Blocking

↓

No Chaining

↓

No Callbacks

↓

Poor Composition
```

---

# 16. Performance Considerations

`Future`

is excellent for

- background computation
- asynchronous execution
- retrieving results

However,

excessive blocking reduces concurrency.

---

## Example

```java
future1.get();

future2.get();

future3.get();
```

Each blocking call may reduce overall responsiveness.

Modern applications often prefer non-blocking approaches.

---

# 17. Common Mistakes

---

## ❌ Calling `get()` Immediately

Wrong

```java
Future<Integer> future =
        executor.submit(task);

future.get();
```

The main thread waits immediately,

eliminating much of the benefit of asynchronous execution.

---

## Better

```java
Future<Integer> future =
        executor.submit(task);

doOtherWork();

future.get();
```

Do useful work while the background task is executing.

---

## ❌ Forgetting Timeouts

Wrong

```java
future.get();
```

If the task never completes,

the caller waits indefinitely.

Better

```java
future.get(
        5,
        TimeUnit.SECONDS
);
```

---

## ❌ Ignoring Exceptions

Always handle

- `InterruptedException`
- `ExecutionException`
- `TimeoutException`

appropriately.

---

## ❌ Forgetting to Shutdown the Executor

```java
executor.shutdown();
```

should always be called when the executor is no longer needed.

---

# 18. Best Practices

✅ Use `Future` for background tasks that return results.

✅ Delay calling `get()` until the result is actually needed.

✅ Use timeouts whenever appropriate.

✅ Handle exceptions carefully.

✅ Shut down the executor gracefully.

✅ Consider `CompletableFuture` for complex asynchronous workflows.

---

# 19. Interview Questions

### 1. What is `Future`?

A `Future` represents the eventual result of an asynchronous computation.

---

### 2. Why does `get()` block?

Because the result may not yet be available.

The calling thread waits until the computation completes.

---

### 3. What does `isDone()` do?

It checks whether the task has completed.

It does **not** block.

---

### 4. What happens if the task throws an exception?

`future.get()`

throws

```java
ExecutionException
```

whose cause is the original exception.

---

### 5. Difference between `cancel(true)` and `cancel(false)`?

| `true` | `false` |
|----------|----------|
| Attempts to interrupt a running task | Does not interrupt a running task |
| Best-effort cancellation | Cancels only if the task hasn't started |

---

### 6. What are the limitations of `Future`?

- Blocking `get()`
- No chaining
- No callbacks
- Difficult to combine multiple asynchronous tasks

---

### 7. Why was `CompletableFuture` introduced?

To overcome the limitations of `Future` by providing

- non-blocking callbacks
- task composition
- asynchronous pipelines
- better error handling

---

# 20. Quick Revision

## Future Lifecycle

```text
Task Submitted

↓

Running

↓

Completed

↓

Result Available

──────────────

OR

──────────────

Cancelled
```

---

## Important Methods

```text
get()

↓

Return Result

──────────────────────

get(timeout)

↓

Wait Limited Time

──────────────────────

isDone()

↓

Task Finished?

──────────────────────

cancel()

↓

Cancel Task

──────────────────────

isCancelled()

↓

Cancelled?
```

---

## Future Limitations

```text
Blocking

↓

No Chaining

↓

No Callbacks

↓

No Composition
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `Future` is
- [x] Future lifecycle
- [x] `get()`
- [x] `get(timeout)`
- [x] `isDone()`
- [x] `cancel()`
- [x] `isCancelled()`
- [x] Exception handling
- [x] Limitations of `Future`
- [x] Best practices

---

# 📌 Key Takeaways

- `Future` represents the result of an asynchronous computation.
- `submit()` returns a `Future` that can later provide the task's result.
- `get()` retrieves the result but blocks if the computation is still running.
- `isDone()` allows completion checks without blocking.
- `cancel()` requests task cancellation, but successful cancellation depends on the task's state and interruption handling.
- `Future` is useful for simple asynchronous tasks but becomes cumbersome for complex asynchronous workflows.
- The limitations of `Future` directly motivated the introduction of `CompletableFuture`.

> [!TIP]
> **Interview Rule**
>
> Remember this progression:
>
> ```text
> Runnable
>      ↓
> Callable
>      ↓
> Future
>      ↓
> CompletableFuture
> ```
>
> Each step solves limitations of the previous one.

---

# 📖 Next Topic

➡️ **19. CompletableFuture ⭐⭐⭐⭐⭐**

In the final chapter, you'll learn one of the most important APIs in modern Java:

- What is `CompletableFuture`?
- Why `Future` is not enough
- Non-blocking programming
- Async task chaining
- `thenApply()`
- `thenAccept()`
- `thenCompose()`
- `thenCombine()`
- Exception handling
- Parallel execution
- Best practices
- Interview questions

> ⭐ **`CompletableFuture` is one of the most frequently asked Java concurrency topics because it enables powerful, non-blocking asynchronous programming.**
