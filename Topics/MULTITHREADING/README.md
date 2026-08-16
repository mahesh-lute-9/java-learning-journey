# 🧵 Java Multithreading & Concurrency

This folder is a **scenario-based practice journey** through Java Multithreading and Concurrency.

Instead of learning APIs in isolation, the programs focus on **real-world problems** and use the appropriate Java concurrency mechanism to solve them.

> **Problem → Identify the concurrency issue → Choose the right tool → Implement → Experiment**

---

## 📚 What We Covered

| Section | What We Learned | Where It Can Be Used |
|---|---|---|
| `01_Threads` | Thread creation, lifecycle, `Runnable`, `join`, `sleep` | Background tasks, parallel work |
| `02_Synchronization` | Race conditions, `synchronized`, thread safety | Shared state, counters, banking |
| `03_InterThread_Communication` | `wait()`, `notify()`, `notifyAll()` | Thread coordination |
| `04_Thread_Pools` | `ExecutorService`, `Callable`, `Future`, scheduled tasks | Servers, task processing, background jobs |
| `05_CompletableFuture` | Async pipelines, combining tasks, exceptions, timeouts | APIs, microservices, asynchronous workflows |
| `06_Producer_Consumer` | `BlockingQueue`, producers, consumers | Job queues, task processing, pipelines |
| `07_Concurrent_Collections` | `ConcurrentHashMap` and atomic map operations | Concurrent caches, metrics, shared data |
| `08_Atomic_Variables` | `AtomicInteger`, `AtomicBoolean`, CAS | Counters, flags, lock-free state updates |
| `09_Advanced_Locks` | `ReentrantLock`, `ReadWriteLock`, `Condition`, fairness | Resource protection, read-heavy systems |
| `10_Synchronizers` | `CountDownLatch`, `CyclicBarrier`, `Semaphore`, `Phaser` | Startup coordination, resource limits, multi-stage processing |

---

## 🎯 How We Practiced

The folder contains **~60 scenario-based programs** rather than many small syntax examples.

Some of the scenarios include:

- 🏦 Concurrent bank/resource operations
- 🛒 Producer-consumer order processing
- 🌐 Concurrent API request tracking
- 🗄️ Database connection/resource limiting
- 🚀 Application startup synchronization
- ⚙️ Multi-stage data processing
- 🔄 Asynchronous API calls
- ⏱️ Timeout and fallback handling

The goal is to understand **when and why** to use a concurrency mechanism, not just remember its syntax.

---

## 🧠 Quick Decision Guide

```text
Need a thread?
        ↓
Thread / Runnable / ExecutorService

Need to protect shared state?
        ↓
synchronized / Lock

Need an atomic counter or state?
        ↓
AtomicInteger / AtomicBoolean / CAS

Need a thread-safe collection?
        ↓
ConcurrentHashMap / BlockingQueue

Need asynchronous operations?
        ↓
CompletableFuture

Need to limit access to a resource?
        ↓
Semaphore

Need to wait for multiple tasks to finish?
        ↓
CountDownLatch

Need threads to meet at a checkpoint?
        ↓
CyclicBarrier

Need dynamic multi-phase coordination?
        ↓
Phaser
