# Queue — Notes

> A practical guide to understanding the Java `Queue` interface, its behavior, operations, implementations, and common interview concepts.

---

## 📚 Table of Contents

1. [Introduction](#1-introduction)
2. [What is a Queue?](#2-what-is-a-queue)
3. [FIFO Principle](#3-fifo-principle)
4. [Queue in Java](#4-queue-in-java)
5. [Queue Hierarchy](#5-queue-hierarchy)
6. [Creating a Queue](#6-creating-a-queue)
7. [Core Queue Operations](#7-core-queue-operations)
8. [`add()` vs `offer()`](#8-add-vs-offer)
9. [`remove()` vs `poll()`](#9-remove-vs-poll)
10. [`element()` vs `peek()`](#10-element-vs-peek)
11. [Queue Operation Summary](#11-queue-operation-summary)
12. [Queue Example](#12-queue-example)
13. [FIFO Behavior](#13-fifo-behavior)
14. [Queue Implementations](#14-queue-implementations)
15. [PriorityQueue](#15-priorityqueue)
16. [Deque](#16-deque)
17. [LinkedList as Queue](#17-linkedlist-as-queue)
18. [ArrayDeque as Queue](#18-arraydeque-as-queue)
19. [Queue with Strings](#19-queue-with-strings)
20. [Queue with Objects](#20-queue-with-objects)
21. [Null Values and Duplicates](#21-null-values-and-duplicates)
22. [Queue Size](#22-queue-size)
23. [Checking if Queue is Empty](#23-checking-if-queue-is-empty)
24. [Iteration](#24-iteration)
25. [Queue vs Stack](#25-queue-vs-stack)
26. [Queue vs List](#26-queue-vs-list)
27. [Time Complexity](#27-time-complexity)
28. [Real-World Applications](#28-real-world-applications)
29. [Common Mistakes](#29-common-mistakes)
30. [Quick Revision](#30-quick-revision)
31. [Final Mental Model](#31-final-mental-model)
32. [Key Interview Statement](#32-key-interview-statement)
33. [Progress](#33-progress)

---

# 1. Introduction

A **Queue** is a collection designed primarily for processing elements in a particular order.

The most common queue behavior is:

> **FIFO — First In, First Out**

The element that enters first is normally the first element removed.

A simple real-world example is a line at a ticket counter:

```text
Person A → Person B → Person C → Person D
   ↓
First person in line gets served first
```

Java provides the `Queue` interface as part of the Collections Framework.

---

# 2. What is a Queue?

A queue is a data structure where:

- Elements are inserted at one end.
- Elements are removed from the other end.
- The normal processing order is FIFO.

Conceptually:

```text
Insertion                         Removal
   ↓                                ↓
   A → B → C → D
                       ←────────────
```

More simply:

```text
Front                         Rear
  ↓                            ↓
[A] [B] [C] [D]
 ↑                            ↑
Remove                       Add
```

If we remove elements:

```text
A
B
C
D
```

The first inserted element is processed first.

---

# 3. FIFO Principle

## FIFO = First In, First Out

Suppose we perform:

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);
```

The queue logically contains:

```text
Front
  ↓
10 → 20 → 30
             ↑
            Rear
```

Removal happens in this order:

```text
10
20
30
```

### Mental Model

```text
First In
   ↓
  10
   ↓
  20
   ↓
  30
   ↓
First Out
```

---

# 4. Queue in Java

`Queue` is an **interface**.

It belongs to:

```java
java.util.Queue
```

You normally create a queue using one of its implementations.

For example:

```java
Queue<Integer> queue = new LinkedList<>();
```

or:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

The interface provides the common queue contract while the implementation determines how the queue is internally managed.

---

# 5. Queue Hierarchy

A simplified Java Collections hierarchy is:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ├── Deque
   │    └── ArrayDeque
   │
   └── PriorityQueue
```

`LinkedList` is also a `Queue` implementation because it implements `Deque`.

```text
Collection
   ↓
Queue
   ↓
Deque
   ↓
LinkedList
```

### Important

`Queue` is an interface.

It is not a concrete class.

---

# 6. Creating a Queue

## 6.1 Using LinkedList

```java
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>();
```

---

## 6.2 Using ArrayDeque

```java
import java.util.ArrayDeque;
import java.util.Queue;

Queue<Integer> queue = new ArrayDeque<>();
```

For typical FIFO queue usage, `ArrayDeque` is generally a preferred modern implementation.

---

## 6.3 Using PriorityQueue

```java
import java.util.PriorityQueue;
import java.util.Queue;

Queue<Integer> queue = new PriorityQueue<>();
```

Be careful:

> `PriorityQueue` does **not** follow normal FIFO ordering.

It processes elements according to priority/natural ordering or a supplied comparator.

---

# 7. Core Queue Operations

The `Queue` interface provides paired methods for several operations.

The main methods are:

```text
add()
offer()

remove()
poll()

element()
peek()
```

---

## 7.1 `add()`

`add()` inserts an element into the queue.

```java
Queue<Integer> queue = new LinkedList<>();

queue.add(10);
queue.add(20);
queue.add(30);
```

Queue:

```text
Front
  ↓
10 → 20 → 30
             ↑
            Rear
```

---

## 7.2 `offer()`

`offer()` also attempts to insert an element.

```java
queue.offer(40);
```

For many unbounded queues, `add()` and `offer()` behave similarly.

The distinction becomes important for queues where insertion can fail due to capacity restrictions.

---

# 8. `add()` vs `offer()`

Both are used for insertion.

| Method | If insertion cannot be performed |
|---|---|
| `add()` | Throws an exception |
| `offer()` | Returns `false` |

For successful insertion:

```text
add()   → true-like successful operation through normal return
offer() → true
```

More precisely, `add()` returns `boolean` and normally returns `true` when the element is accepted.

Example:

```java
queue.add(10);
```

and:

```java
queue.offer(10);
```

Both insert the element in a normal unbounded queue.

### Interview Tip

> Prefer `offer()` when you want queue-style insertion semantics where failure is represented by a return value rather than an exception.

---

# 9. `remove()` vs `poll()`

Both remove and return the head of the queue.

Suppose:

```text
10 → 20 → 30
↑
Front
```

Then:

```java
queue.remove();
```

returns:

```text
10
```

and removes it.

Similarly:

```java
queue.poll();
```

returns:

```text
10
```

and removes it.

### Important Difference

When the queue is empty:

| Method | Empty Queue |
|---|---|
| `remove()` | Throws `NoSuchElementException` |
| `poll()` | Returns `null` |

Example:

```java
Queue<Integer> queue = new LinkedList<>();

queue.poll();
```

returns:

```text
null
```

But:

```java
queue.remove();
```

throws:

```text
NoSuchElementException
```

---

# 10. `element()` vs `peek()`

Both inspect the head without removing it.

Suppose:

```text
Front
  ↓
10 → 20 → 30
```

Then:

```java
queue.element();
```

returns:

```text
10
```

without removing it.

Likewise:

```java
queue.peek();
```

returns:

```text
10
```

without removing it.

### Empty Queue Difference

| Method | Empty Queue |
|---|---|
| `element()` | Throws `NoSuchElementException` |
| `peek()` | Returns `null` |

### Interview Tip

The safer pair when you want non-exceptional empty behavior is often:

```text
offer()
poll()
peek()
```

---

# 11. Queue Operation Summary

This table is extremely important for interviews.

| Operation | Throws on failure/empty | Special return value |
|---|---|---|
| `add(e)` | `IllegalStateException` if insertion fails | — |
| `offer(e)` | Usually no exception for capacity failure | `false` |
| `remove()` | `NoSuchElementException` | — |
| `poll()` | No | `null` |
| `element()` | `NoSuchElementException` | — |
| `peek()` | No | `null` |

For an unbounded queue such as a typical `LinkedList` or `ArrayDeque`, capacity-related insertion failure is generally not the issue, but the API distinction still matters.

---

# 12. Queue Example

```java
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println(queue);
        System.out.println(queue.peek());

        System.out.println(queue.poll());

        System.out.println(queue);
    }
}
```

Output:

```text
[10, 20, 30]
10
10
[20, 30]
```

### What happened?

First:

```text
10 → 20 → 30
```

`peek()` viewed:

```text
10
```

`poll()` removed:

```text
10
```

Final queue:

```text
20 → 30
```

---

# 13. FIFO Behavior

Consider:

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);
queue.offer(40);
```

The queue is:

```text
Front
  ↓
10 → 20 → 30 → 40
                  ↑
                 Rear
```

Calling:

```java
queue.poll();
```

returns:

```text
10
```

Calling again:

```java
queue.poll();
```

returns:

```text
20
```

Then:

```text
30
40
```

### Final removal order

```text
10 → 20 → 30 → 40
```

This is FIFO.

---

# 14. Queue Implementations

`Queue` is an interface, so we need an implementation.

Common implementations include:

| Implementation | Main Behavior |
|---|---|
| `LinkedList` | Queue + Deque + List |
| `ArrayDeque` | Efficient general-purpose queue/deque |
| `PriorityQueue` | Priority-based ordering |

---

# 15. PriorityQueue

`PriorityQueue` implements `Queue`, but it does not behave like a normal FIFO queue.

Example:

```java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);
```

The head is determined by priority/natural ordering.

For integers, the smallest element is normally at the head.

```java
System.out.println(queue.poll());
```

Output:

```text
10
```

Then:

```text
20
30
```

### Important

Do not say:

> Every Queue implementation follows FIFO.

Better:

> The `Queue` interface is designed around queue semantics, but specific implementations can define different ordering policies. `PriorityQueue`, for example, orders elements by priority rather than simple insertion order.

---

# 16. Deque

`Deque` means:

> **Double-Ended Queue**

It allows insertion and removal from both ends.

Hierarchy:

```text
Queue
  ↓
Deque
```

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

It can behave as:

### Queue

```text
offerLast()
pollFirst()
```

### Stack

```text
push()
pop()
```

This makes `Deque` extremely useful in Java.

---

# 17. LinkedList as Queue

`LinkedList` implements `Deque`, and therefore can be used as a `Queue`.

```java
Queue<Integer> queue = new LinkedList<>();
```

Example:

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

Output:

```text
10
```

### Advantage

`LinkedList` provides queue and deque behavior.

### Disadvantage

For a simple queue, `ArrayDeque` is generally preferred because it is designed specifically for efficient deque/queue operations and avoids the extra node-based structure of `LinkedList`.

---

# 18. ArrayDeque as Queue

`ArrayDeque` is a resizable-array implementation of `Deque`.

It can be used as a queue:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

Example:

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

Output:

```text
10
```

### Important

`ArrayDeque`:

- supports queue operations
- supports stack operations
- does not permit `null`
- is not synchronized
- is generally preferred for typical single-threaded queue/deque use

---

# 19. Queue with Strings

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("Alice");
queue.offer("Bob");
queue.offer("Charlie");

System.out.println(queue.peek());
```

Output:

```text
Alice
```

Then:

```java
System.out.println(queue.poll());
```

Output:

```text
Alice
```

Remaining:

```text
Bob
Charlie
```

---

# 20. Queue with Objects

A queue can store custom objects.

Example:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Create the queue:

```java
Queue<Student> queue = new ArrayDeque<>();
```

Add students:

```java
queue.offer(new Student(1, "Amit"));
queue.offer(new Student(2, "Rahul"));
queue.offer(new Student(3, "Priya"));
```

The students will normally be processed in insertion order because `ArrayDeque` is being used as a FIFO queue.

---

# 21. Null Values and Duplicates

## 21.1 Duplicates

Most queue implementations allow duplicate elements.

Example:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(10);
queue.offer(20);
```

Queue:

```text
10 → 10 → 20
```

---

## 21.2 Null

Null support depends on the implementation.

### LinkedList

`LinkedList` permits `null`.

```java
Queue<String> queue = new LinkedList<>();

queue.offer(null);
```

### ArrayDeque

`ArrayDeque` does not permit `null`.

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer(null);
```

This results in:

```text
NullPointerException
```

### Why is this important?

For queues where `poll()` and `peek()` use `null` to indicate that the queue is empty, allowing `null` as an actual element would create ambiguity.

---

# 22. Queue Size

Use:

```java
queue.size();
```

Example:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.size());
```

Output:

```text
3
```

After:

```java
queue.poll();
```

size becomes:

```text
2
```

---

# 23. Checking if Queue is Empty

Use:

```java
queue.isEmpty();
```

Example:

```java
if (queue.isEmpty()) {
    System.out.println("Queue is empty");
}
```

You can also check:

```java
queue.size() == 0
```

but:

```java
isEmpty()
```

expresses the intention more clearly.

---

# 24. Iteration

A queue can be traversed using a for-each loop.

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

for (Integer value : queue) {
    System.out.println(value);
}
```

Output for `ArrayDeque` in queue order:

```text
10
20
30
```

### Important

Iteration does not remove elements.

After iteration:

```java
System.out.println(queue.size());
```

the size is still:

```text
3
```

---

## Destructive Traversal

To process and remove every element:

```java
while (!queue.isEmpty()) {

    Integer value = queue.poll();

    System.out.println(value);
}
```

This follows FIFO removal order.

After the loop:

```text
Queue is empty
```

---

# 25. Queue vs Stack

Queue:

```text
FIFO
First In → First Out
```

Stack:

```text
LIFO
Last In → First Out
```

### Example

Insert:

```text
10
20
30
```

Queue removal:

```text
10
20
30
```

Stack removal:

```text
30
20
10
```

### Comparison

| Feature | Queue | Stack |
|---|---|---|
| Principle | FIFO | LIFO |
| Insert | Rear | Top |
| Remove | Front | Top |
| Main insert method | `offer()` | `push()` |
| Main remove method | `poll()` | `pop()` |
| View next | `peek()` | `peek()` |
| Modern implementation | `ArrayDeque` | `ArrayDeque` |

---

# 26. Queue vs List

A `List` focuses on:

- indexed access
- positional insertion
- positional removal
- maintaining sequence

A `Queue` focuses on:

- processing order
- adding elements
- removing the next element
- inspecting the next element

### Example

List:

```java
list.get(3);
```

Queue:

```java
queue.poll();
```

The abstractions solve different problems.

---

# 27. Time Complexity

Complexity depends on the implementation.

For a typical `ArrayDeque` used as a queue:

| Operation | Typical Complexity |
|---|---:|
| `offer()` | O(1) amortized |
| `poll()` | O(1) |
| `peek()` | O(1) |
| `isEmpty()` | O(1) |
| `size()` | O(1) |

For `LinkedList` queue operations:

| Operation | Typical Complexity |
|---|---:|
| `offer()` | O(1) |
| `poll()` | O(1) |
| `peek()` | O(1) |
| `isEmpty()` | O(1) |
| `size()` | O(1) |

For `PriorityQueue`:

| Operation | Typical Complexity |
|---|---:|
| `offer()` | O(log n) |
| `poll()` | O(log n) |
| `peek()` | O(1) |

### Important

Do not memorize one complexity table for every Queue implementation.

> Always consider the concrete implementation.

---

# 28. Real-World Applications

Queues are useful whenever work should generally be processed in arrival order.

## 28.1 Printer Queue

```text
Document A
Document B
Document C
```

Normally:

```text
A → B → C
```

---

## 28.2 CPU Scheduling

Processes may wait in a queue before receiving CPU time, depending on the scheduling algorithm.

---

## 28.3 Request Processing

A server can place incoming tasks into a queue:

```text
Request 1
Request 2
Request 3
Request 4
```

Workers process them according to the application's scheduling policy.

---

## 28.4 Breadth-First Search

BFS uses a queue.

Conceptually:

```text
Start Node
    ↓
Queue
    ↓
Process nodes level by level
```

Typical implementation:

```java
Queue<Node> queue = new ArrayDeque<>();
```

---

## 28.5 Message Processing

Applications can place messages into a queue and process them sequentially.

---

## 28.6 Customer Service Systems

Customers waiting for service naturally form a queue:

```text
Customer A → Customer B → Customer C
```

---

# 29. Common Mistakes

## Mistake 1 — Confusing Queue with Stack

Queue:

```text
FIFO
```

Stack:

```text
LIFO
```

---

## Mistake 2 — Assuming Queue is a class

`Queue` is an interface.

Correct:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

---

## Mistake 3 — Using `remove()` without considering empty behavior

`remove()` throws:

```text
NoSuchElementException
```

For non-exceptional empty behavior, consider:

```java
poll()
```

---

## Mistake 4 — Confusing `peek()` and `poll()`

`peek()`:

```text
view
```

`poll()`:

```text
remove + return
```

---

## Mistake 5 — Assuming `PriorityQueue` is FIFO

It is priority-based, not simple insertion-order FIFO.

---

## Mistake 6 — Assuming all Queue implementations allow null

They do not.

For example:

```text
LinkedList → allows null
ArrayDeque → does not allow null
```

---

## Mistake 7 — Assuming iteration removes elements

It doesn't.

```java
for (Integer value : queue) {
    System.out.println(value);
}
```

does not empty the queue.

Use:

```java
while (!queue.isEmpty()) {
    queue.poll();
}
```

if you want destructive processing.

---

## Mistake 8 — Using LinkedList automatically

Although `LinkedList` can act as a queue, `ArrayDeque` is generally preferred for typical queue/deque use.

---

# 30. Quick Revision

```text
Queue
  ↓
Interface
  ↓
Normally FIFO
  ↓
First In → First Out
  ↓
Core operations
  ├── add()
  ├── offer()
  ├── remove()
  ├── poll()
  ├── element()
  └── peek()
```

### Important pairs

```text
Insertion:
add()   → exception on insertion failure
offer() → false on insertion failure

Removal:
remove() → exception if empty
poll()   → null if empty

Inspection:
element() → exception if empty
peek()    → null if empty
```

### Common implementations

```text
Queue
 ├── LinkedList
 ├── ArrayDeque
 └── PriorityQueue
```

---

# 31. Final Mental Model

```text
                  QUEUE
                    │
                    ▼
             FIFO PRINCIPLE
                    │
          First In → First Out
                    │
        ┌───────────┼───────────┐
        ▼           ▼           ▼
      offer()      poll()      peek()
        │           │           │
        ▼           ▼           ▼
       Add        Remove       View
                    │
                    ▼
                  Front
```

Think of a queue as a line:

```text
                 Queue
                   │
                   ▼
       Front → [A][B][C][D] ← Rear
                 ↑       ↑
               remove    add
```

---

# 32. Key Interview Statement

> **Queue is an interface in the Java Collections Framework that represents a collection designed for processing elements in a particular order, commonly FIFO — First In, First Out. Its main operations are `offer()`, `poll()`, and `peek()`. Common implementations include `LinkedList`, `ArrayDeque`, and `PriorityQueue`, although `PriorityQueue` uses priority-based ordering rather than normal FIFO ordering. For typical FIFO queue usage, `ArrayDeque` is generally a preferred modern implementation.**

---

# 33. Progress

```text
Java Collections Framework
│
├── 01-Iterable
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 02-Collection-Interface
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 03-List
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 04-ArrayList
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 05-LinkedList
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 06-Vector
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 07-Stack
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 08-Queue
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 09-PriorityQueue
├── 10-Deque
├── 11-ArrayDeque
├── 12-Set
├── 13-HashSet
├── 14-LinkedHashSet
├── 15-TreeSet
├── 16-Map
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**Next:** `08-Queue/PRACTICE.md`
