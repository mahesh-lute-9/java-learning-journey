# 🚀 19. CompletableFuture ⭐⭐⭐⭐⭐

> [!NOTE]
> `CompletableFuture` is a powerful class introduced in **Java 8** that enables **asynchronous, non-blocking programming**.
>
> Unlike `Future`, it supports
>
> - Task Chaining
> - Callbacks
> - Combining Multiple Tasks
> - Exception Handling
> - Parallel Execution
> - Functional Programming Style
>
> It is one of the most important concurrency APIs in modern Java.

---

# 📚 Table of Contents

- [1. Prerequisites](#1-prerequisites)
- [2. What is CompletableFuture?](#2-what-is-completablefuture)
- [3. Why Was CompletableFuture Introduced?](#3-why-was-completablefuture-introduced)
- [4. Future vs CompletableFuture](#4-future-vs-completablefuture)
- [5. Asynchronous Programming](#5-asynchronous-programming)
- [6. Creating CompletableFuture](#6-creating-completablefuture)
- [7. Common Factory Methods](#7-common-factory-methods)

---

# 1. Prerequisites

Before reading this chapter, you should understand

- Executor Framework
- Callable
- Future
- Lambda Expressions
- Functional Interfaces

---

# 2. What is CompletableFuture?

`CompletableFuture`

is an implementation of

```java
Future
```

that supports

- asynchronous execution
- callbacks
- task chaining
- combining tasks
- exception handling

Unlike

```java
Future
```

it allows you to continue processing **without blocking**.

---

## Definition

> `CompletableFuture` represents a future result that can be completed asynchronously and supports building asynchronous pipelines.

---

## Package

```java
java.util.concurrent
```

---

# 3. Why Was CompletableFuture Introduced?

The older

```java
Future
```

API has several limitations.

---

## Future Problems

```text
Blocking get()

↓

No Chaining

↓

No Callbacks

↓

Difficult Composition

↓

Poor Error Handling
```

---

Java 8 introduced

```java
CompletableFuture
```

to solve these problems.

---

## CompletableFuture Features

```text
Non-blocking

↓

Callbacks

↓

Task Chaining

↓

Parallel Composition

↓

Better Exception Handling
```

---

# 4. Future vs CompletableFuture

| Future | CompletableFuture |
|----------|-------------------|
| Blocking `get()` | Supports non-blocking callbacks |
| No chaining | Supports chaining |
| No callbacks | Supports callbacks |
| Difficult to combine tasks | Easy task composition |
| Limited exception handling | Rich exception handling |
| Read result | Read + Transform + Compose + Combine |

---

## Visualization

### Future

```text
Task

↓

Future

↓

get()

↓

WAIT

↓

Result
```

---

### CompletableFuture

```text
Task

↓

thenApply()

↓

thenAccept()

↓

thenRun()

↓

Done
```

Notice

No explicit waiting is required between stages.

---

# 5. Asynchronous Programming

Traditional programming

```text
Task A

↓

Wait

↓

Task B

↓

Wait

↓

Task C
```

Everything executes sequentially.

---

Asynchronous programming

```text
Task A

──────────────

Task B

──────────────

Task C

↓

Combine Results
```

Multiple tasks can execute independently.

---

## Benefits

- Better responsiveness
- Better CPU utilization
- Improved scalability
- Higher throughput

---

# 6. Creating CompletableFuture

There are several ways.

---

## Completed Future

```java
CompletableFuture<String> future =
        CompletableFuture.completedFuture(
                "Hello");
```

The future is already completed.

---

## Asynchronous Task

```java
CompletableFuture<Void> future =
        CompletableFuture.runAsync(() -> {

            System.out.println(
                    "Running");

        });
```

---

## Returning Result

```java
CompletableFuture<Integer> future =
        CompletableFuture.supplyAsync(() -> {

            return 100;

        });
```

Unlike

```java
runAsync()
```

this returns a value.

---

# 7. Common Factory Methods

---

## `runAsync()`

Used for tasks that

**do not return a result.**

---

### Example

```java
CompletableFuture.runAsync(() -> {

    System.out.println(
            "Background Task");

});
```

Return Type

```java
CompletableFuture<Void>
```

---

## `supplyAsync()`

Used for tasks that

**return a result.**

---

### Example

```java
CompletableFuture<Integer> future =
        CompletableFuture.supplyAsync(() -> {

            return 500;

        });
```

Return Type

```java
CompletableFuture<Integer>
```

---

## Comparison

| `runAsync()` | `supplyAsync()` |
|---------------|-----------------|
| No return value | Returns a value |
| Runnable | Supplier |
| `CompletableFuture<Void>` | `CompletableFuture<T>` |

---

# 💡 Interview Insight

One of the most common interview questions is

> **Why was CompletableFuture introduced when Future already existed?**

Good Answer

Because

`Future`

- blocks with `get()`
- has no callbacks
- has no chaining
- cannot easily combine multiple tasks

`CompletableFuture`

solves all these problems.

---

# 8. `thenApply()` ⭐⭐⭐⭐⭐

`thenApply()`

is used to **transform** the result of a completed task.

Think of it like the

```text
map()
```

operation in functional programming.

---

## Syntax

```java
future.thenApply(result -> {

    return transformedResult;

});
```

---

## Example

```java
CompletableFuture<Integer> future =
        CompletableFuture
                .supplyAsync(() -> 10)
                .thenApply(value -> value * 2);
```

Result

```text
20
```

---

## Flow

```text
10

↓

thenApply()

↓

20
```

---

## Use Cases

- Format strings
- Convert DTOs
- Calculate totals
- Process API responses

---

# 9. `thenAccept()`

Sometimes,

you don't need another result.

You simply want to **consume** the completed value.

---

## Syntax

```java
future.thenAccept(result -> {

    System.out.println(result);

});
```

---

## Example

```java
CompletableFuture
        .supplyAsync(() -> "Java")

        .thenAccept(System.out::println);
```

Output

```text
Java
```

---

## Difference

```text
thenApply()

↓

Returns New Value

────────────────────

thenAccept()

↓

Consumes Value

↓

Returns Void
```

---

# 10. `thenRun()`

Suppose you don't need the previous result.

You simply want another task to execute.

---

## Syntax

```java
future.thenRun(() -> {

    System.out.println(
            "Completed");

});
```

---

## Example

```java
CompletableFuture
        .runAsync(() -> {

            System.out.println(
                    "Task");

        })

        .thenRun(() -> {

            System.out.println(
                    "Done");

        });
```

---

## Flow

```text
Task

↓

Complete

↓

Run Next Task
```

---

# 11. `thenCompose()` ⭐⭐⭐⭐⭐

One of the most important methods.

Suppose

Task A

returns another

```java
CompletableFuture
```

Without

```java
thenCompose()
```

you get

```java
CompletableFuture<
    CompletableFuture<T>>
```

This is called

```
Nested Future
```

---

## Example

```java
CompletableFuture<String> future =
        CompletableFuture
                .supplyAsync(() -> "Mahesh")

                .thenCompose(name ->

                        CompletableFuture
                                .supplyAsync(() ->

                                        name + " Lute"

                                )

                );
```

Result

```text
Mahesh Lute
```

---

## Visualization

Without

```text
Future

↓

Future

↓

Result
```

With

```text
Future

↓

Result
```

---

## Think Of It As

```text
FlatMap
```

for asynchronous programming.

---

# 12. `thenCombine()` ⭐⭐⭐⭐⭐

Sometimes,

two independent tasks should execute in parallel,

then their results should be combined.

---

## Example

```java
CompletableFuture<Integer> a =
        CompletableFuture
                .supplyAsync(() -> 10);

CompletableFuture<Integer> b =
        CompletableFuture
                .supplyAsync(() -> 20);

CompletableFuture<Integer> sum =
        a.thenCombine(

                b,

                Integer::sum

        );
```

Result

```text
30
```

---

## Visualization

```text
Task A

──────────────

Task B

↓

thenCombine()

↓

Combined Result
```

---

## Real-World Example

```
User Details

──────────────

Order Details

↓

Combine

↓

Dashboard
```

---

# 13. `allOf()`

Suppose

five tasks

must all complete.

---

## Syntax

```java
CompletableFuture.allOf(

        future1,

        future2,

        future3

);
```

---

## Working

```text
Task A

──────────────

Task B

──────────────

Task C

↓

Wait

↓

Continue
```

---

## Use Cases

- Download multiple files
- Call multiple APIs
- Process many images

---

# 14. `anyOf()`

Sometimes,

the first completed task is enough.

---

## Syntax

```java
CompletableFuture.anyOf(

        future1,

        future2,

        future3

);
```

---

## Working

```text
Task A

──────────────

Task B

──────────────

Task C

↓

First Finished

↓

Return Result
```

---

## Real-World Example

```
Server A

──────────────

Server B

──────────────

Server C

↓

Fastest Response
```

---

# 15. Building an Async Pipeline

One of the greatest strengths of

```java
CompletableFuture
```

is building pipelines.

---

## Example

```java
CompletableFuture

    .supplyAsync(() -> 10)

    .thenApply(x -> x * 2)

    .thenApply(x -> x + 5)

    .thenAccept(System.out::println);
```

Execution

```text
10

↓

20

↓

25

↓

Print
```

No manual waiting.

No nested callbacks.

Everything is composed elegantly.

---

# 💡 Interview Insight

One of the most common interview questions is

> **Difference between `thenApply()` and `thenCompose()`?**

| `thenApply()` | `thenCompose()` |
|---------------|-----------------|
| Transforms a value | Chains another asynchronous task |
| Similar to `map()` | Similar to `flatMap()` |
| Returns transformed value | Flattens nested `CompletableFuture` |

---

Another common question

> **Difference between `thenAccept()` and `thenRun()`?**

| `thenAccept()` | `thenRun()` |
|----------------|-------------|
| Uses previous result | Ignores previous result |
| Consumer | Runnable |
| Returns `CompletableFuture<Void>` | Returns `CompletableFuture<Void>` |

---

# 16. Exception Handling ⭐⭐⭐⭐⭐

One of the biggest advantages of

```java
CompletableFuture
```

is its built-in exception handling.

Unlike

```java
Future
```

you don't need to wrap everything around

```java
try-catch
```

after

```java
get()
```

---

## Why?

Suppose

```text
Task

↓

Throws Exception

↓

What Next?
```

`CompletableFuture`

provides several methods.

---

## Methods

```java
exceptionally()

handle()

whenComplete()
```

---

# 17. `exceptionally()`

Used to recover from failures.

---

## Syntax

```java
future.exceptionally(ex -> {

    return defaultValue;

});
```

---

## Example

```java
CompletableFuture<Integer> future =

        CompletableFuture

                .supplyAsync(() -> {

                    throw new RuntimeException();

                })

                .exceptionally(ex -> 0);
```

Result

```text
0
```

Instead of failing,

the pipeline continues.

---

## Flow

```text
Task

↓

Exception

↓

exceptionally()

↓

Default Value
```

---

# 18. `handle()`

`handle()`

runs whether the task

- succeeds
- fails

---

## Example

```java
CompletableFuture<String> future =

        CompletableFuture

                .supplyAsync(() -> "Java")

                .handle((result, ex) -> {

                    if (ex != null) {

                        return "Error";

                    }

                    return result.toUpperCase();

                });
```

Output

```text
JAVA
```

---

## Visualization

```text
Success?

↓

YES

↓

Process Result

──────────────

NO

↓

Recover
```

---

# 19. `whenComplete()`

Used for

- logging
- auditing
- cleanup

It **does not change** the result.

---

## Example

```java
CompletableFuture

        .supplyAsync(() -> 10)

        .whenComplete(

                (result, ex) ->

                        System.out.println(

                                result

                        )

        );
```

---

## Difference

| Method | Can Change Result? |
|----------|-------------------|
| exceptionally() | ✅ Yes |
| handle() | ✅ Yes |
| whenComplete() | ❌ No |

---

# 20. Async Variants

Most methods have

```text
Normal

↓

Async Version
```

Examples

```java
thenApply()

↓

thenApplyAsync()

────────────────────

thenAccept()

↓

thenAcceptAsync()

────────────────────

thenRun()

↓

thenRunAsync()
```

---

## Why Async?

Without Async

```text
Current Thread

↓

Execute Next Stage
```

With Async

```text
Current Thread

↓

Executor

↓

Another Worker Thread
```

---

# 21. Custom Executor

By default,

`CompletableFuture`

uses

```text
ForkJoinPool.commonPool()
```

You can provide your own executor.

---

## Example

```java
ExecutorService executor =

        Executors.newFixedThreadPool(4);

CompletableFuture

        .supplyAsync(

                () -> 100,

                executor

        );
```

---

## Benefits

- Better thread control
- Thread naming
- Resource isolation
- Predictable performance

---

# 22. Performance Considerations

`CompletableFuture`

works best when

- tasks are independent,
- tasks spend time waiting (I/O),
- work can be pipelined.

---

## Avoid

```text
Long Blocking get()

↓

Defeats Async Programming
```

---

## Prefer

```text
thenApply()

↓

thenCompose()

↓

thenCombine()
```

Keep the workflow asynchronous as long as possible.

---

# 23. Common Mistakes

---

## ❌ Calling `get()` Immediately

Wrong

```java
CompletableFuture<Integer> future =

        CompletableFuture

                .supplyAsync(() -> 100);

future.get();
```

The thread blocks immediately.

---

Better

```java
CompletableFuture

        .supplyAsync(() -> 100)

        .thenApply(x -> x * 2)

        .thenAccept(System.out::println);
```

---

## ❌ Deeply Nested Futures

Wrong

```java
Future

↓

Future

↓

Future
```

Use

```java
thenCompose()
```

instead.

---

## ❌ Ignoring Exceptions

Always use

```java
exceptionally()

handle()

whenComplete()
```

when appropriate.

---

## ❌ Using the Common Pool for Every Workload

For production applications,

consider a custom

```java
ExecutorService
```

especially for blocking I/O tasks.

---

# 24. Best Practices

✅ Prefer chaining over blocking.

✅ Use `thenCompose()` for dependent asynchronous operations.

✅ Use `thenCombine()` for independent asynchronous operations.

✅ Handle exceptions explicitly.

✅ Use custom executors when appropriate.

✅ Keep asynchronous tasks small and focused.

✅ Shut down custom executors gracefully.

---

# 25. Interview Questions

### 1. What is `CompletableFuture`?

A class that supports asynchronous, non-blocking programming with task composition, callbacks, and exception handling.

---

### 2. Why was it introduced?

To overcome the limitations of `Future`.

---

### 3. Difference between `thenApply()` and `thenCompose()`?

| `thenApply()` | `thenCompose()` |
|---------------|-----------------|
| Transform value | Chain another async task |
| Similar to `map()` | Similar to `flatMap()` |

---

### 4. Difference between `thenAccept()` and `thenRun()`?

| `thenAccept()` | `thenRun()` |
|----------------|-------------|
| Uses previous result | Ignores previous result |

---

### 5. Difference between `allOf()` and `anyOf()`?

| `allOf()` | `anyOf()` |
|------------|-----------|
| Waits for all tasks | Returns first completed task |

---

### 6. Difference between `Future` and `CompletableFuture`?

| Future | CompletableFuture |
|----------|-------------------|
| Blocking | Non-blocking APIs available |
| No chaining | Task chaining |
| No callbacks | Callbacks |
| Limited composition | Rich composition |

---

### 7. Which thread pool is used by default?

```text
ForkJoinPool.commonPool()
```

unless a custom executor is supplied.

---

# 26. Quick Revision

## Evolution

```text
Thread

↓

Runnable

↓

Callable

↓

Future

↓

CompletableFuture
```

---

## Transformation

```text
thenApply()

↓

Transform Result
```

---

## Consumption

```text
thenAccept()

↓

Use Result
```

---

## Next Task

```text
thenRun()

↓

Run Task
```

---

## Chaining

```text
thenCompose()

↓

Async FlatMap
```

---

## Combining

```text
thenCombine()

↓

Merge Results
```

---

## Multiple Tasks

```text
allOf()

↓

Wait All

────────────────────

anyOf()

↓

First Result
```

---

## Exception Handling

```text
exceptionally()

↓

Recover

────────────────────

handle()

↓

Success Or Failure

────────────────────

whenComplete()

↓

Observe Result
```

---

# 🎯 SDE Checklist

After completing this chapter, you should be able to explain:

- [x] What `CompletableFuture` is
- [x] Why it was introduced
- [x] `runAsync()`
- [x] `supplyAsync()`
- [x] `thenApply()`
- [x] `thenAccept()`
- [x] `thenRun()`
- [x] `thenCompose()`
- [x] `thenCombine()`
- [x] `allOf()`
- [x] `anyOf()`
- [x] Exception handling
- [x] Async variants
- [x] Custom executors
- [x] Best practices

---

# 📌 Key Takeaways

- `CompletableFuture` is the preferred API for asynchronous programming in modern Java.
- It extends the ideas of `Future` by supporting callbacks, chaining, composition, and richer exception handling.
- `thenApply()` transforms results, while `thenCompose()` chains dependent asynchronous tasks.
- `thenCombine()` merges independent computations, and `allOf()` / `anyOf()` coordinate multiple tasks.
- Prefer asynchronous pipelines over blocking `get()` calls whenever possible.
- Use custom executors for better control in production applications.
- Understanding `CompletableFuture` is essential for Spring Boot, microservices, and enterprise Java development.

> [!TIP]
> **Interview Rule**
>
> Remember this progression:
>
> ```text
> Thread
>      ↓
> Runnable
>      ↓
> Callable
>      ↓
> Future
>      ↓
> CompletableFuture
> ```
>
> Each abstraction solves limitations of the previous one while making concurrent programming more expressive.
